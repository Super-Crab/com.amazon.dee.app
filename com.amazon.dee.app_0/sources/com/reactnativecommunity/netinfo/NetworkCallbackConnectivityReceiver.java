package com.reactnativecommunity.netinfo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.facebook.react.bridge.ReactApplicationContext;
import com.reactnativecommunity.netinfo.types.CellularGeneration;
import com.reactnativecommunity.netinfo.types.ConnectionType;
@TargetApi(24)
/* loaded from: classes3.dex */
public class NetworkCallbackConnectivityReceiver extends ConnectivityReceiver {
    private static final int DELAY_MS = 250;
    private NetworkCapabilities mCapabilities;
    private Network mNetwork;
    private final ConnectivityNetworkCallback mNetworkCallback;

    /* loaded from: classes3.dex */
    private class ConnectivityNetworkCallback extends ConnectivityManager.NetworkCallback {
        private ConnectivityNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            NetworkCallbackConnectivityReceiver.this.mNetwork = network;
            NetworkCallbackConnectivityReceiver.this.asyncUpdateAndSend(250);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            NetworkCallbackConnectivityReceiver.this.mNetwork = network;
            NetworkCallbackConnectivityReceiver.this.mCapabilities = networkCapabilities;
            NetworkCallbackConnectivityReceiver.this.updateAndSend();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            if (NetworkCallbackConnectivityReceiver.this.mNetwork != null) {
                NetworkCallbackConnectivityReceiver.this.mNetwork = network;
            }
            NetworkCallbackConnectivityReceiver.this.asyncUpdateAndSend(250);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int i) {
            NetworkCallbackConnectivityReceiver.this.mNetwork = network;
            NetworkCallbackConnectivityReceiver.this.updateAndSend();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            NetworkCallbackConnectivityReceiver.this.mNetwork = null;
            NetworkCallbackConnectivityReceiver.this.mCapabilities = null;
            NetworkCallbackConnectivityReceiver.this.updateAndSend();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onUnavailable() {
            NetworkCallbackConnectivityReceiver.this.mNetwork = null;
            NetworkCallbackConnectivityReceiver.this.mCapabilities = null;
            NetworkCallbackConnectivityReceiver.this.updateAndSend();
        }
    }

    public NetworkCallbackConnectivityReceiver(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mNetwork = null;
        this.mCapabilities = null;
        this.mNetworkCallback = new ConnectivityNetworkCallback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void asyncUpdateAndSend(int i) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.reactnativecommunity.netinfo.-$$Lambda$NetworkCallbackConnectivityReceiver$epnIZmJvBHP75GMogfWNCsO5OMg
            @Override // java.lang.Runnable
            public final void run() {
                NetworkCallbackConnectivityReceiver.this.lambda$asyncUpdateAndSend$0$NetworkCallbackConnectivityReceiver();
            }
        }, i);
    }

    public /* synthetic */ void lambda$asyncUpdateAndSend$0$NetworkCallbackConnectivityReceiver() {
        this.mCapabilities = getConnectivityManager().getNetworkCapabilities(this.mNetwork);
        updateAndSend();
    }

    @Override // com.reactnativecommunity.netinfo.ConnectivityReceiver
    @SuppressLint({"MissingPermission"})
    public void register() {
        try {
            this.mNetwork = getConnectivityManager().getActiveNetwork();
            asyncUpdateAndSend(0);
            getConnectivityManager().registerDefaultNetworkCallback(this.mNetworkCallback);
        } catch (SecurityException unused) {
        }
    }

    @Override // com.reactnativecommunity.netinfo.ConnectivityReceiver
    public void unregister() {
        try {
            getConnectivityManager().unregisterNetworkCallback(this.mNetworkCallback);
        } catch (IllegalArgumentException | SecurityException unused) {
        }
    }

    @SuppressLint({"MissingPermission"})
    void updateAndSend() {
        boolean z;
        ConnectionType connectionType = ConnectionType.UNKNOWN;
        Network network = this.mNetwork;
        NetworkCapabilities networkCapabilities = this.mCapabilities;
        CellularGeneration cellularGeneration = null;
        boolean z2 = false;
        if (networkCapabilities != null) {
            if (networkCapabilities.hasTransport(2)) {
                connectionType = ConnectionType.BLUETOOTH;
            } else if (networkCapabilities.hasTransport(0)) {
                connectionType = ConnectionType.CELLULAR;
            } else if (networkCapabilities.hasTransport(3)) {
                connectionType = ConnectionType.ETHERNET;
            } else if (networkCapabilities.hasTransport(1)) {
                connectionType = ConnectionType.WIFI;
            } else if (networkCapabilities.hasTransport(4)) {
                connectionType = ConnectionType.VPN;
            }
            NetworkInfo networkInfo = network != null ? getConnectivityManager().getNetworkInfo(network) : null;
            if (Build.VERSION.SDK_INT >= 28) {
                z = !networkCapabilities.hasCapability(21);
            } else {
                z = (network == null || networkInfo == null || networkInfo.getDetailedState().equals(NetworkInfo.DetailedState.CONNECTED)) ? false : true;
            }
            if (networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16) && !z) {
                z2 = true;
            }
            if (network != null && connectionType == ConnectionType.CELLULAR && z2) {
                cellularGeneration = CellularGeneration.fromNetworkInfo(networkInfo);
            }
        } else {
            connectionType = ConnectionType.NONE;
        }
        updateConnectivity(connectionType, cellularGeneration, z2);
    }
}
