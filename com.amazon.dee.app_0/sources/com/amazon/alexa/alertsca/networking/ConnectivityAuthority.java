package com.amazon.alexa.alertsca.networking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import com.amazon.alexa.alertsca.AlertsEventBus;
import com.amazon.alexa.alertsca.events.NetworkConnectivityEvent;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class ConnectivityAuthority extends BroadcastReceiver {
    private final AlertsEventBus alertsEventBus;
    private final ConnectivityManager connectivityManager;
    private final Context context;
    private final DefaultNetworkCallback defaultNetworkCallback = new DefaultNetworkCallback();
    private static final String TAG = ConnectivityAuthority.class.getSimpleName();
    static final IntentFilter NETWORK_CHANGED_INTENT_FILTER = new IntentFilter(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION);

    /* loaded from: classes6.dex */
    private class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        private DefaultNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            ConnectivityAuthority.this.sendConnectedEvent(true);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            ConnectivityAuthority.this.sendConnectedEvent(false);
            super.onLost(network);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ConnectivityAuthority(Context context, ConnectivityManager connectivityManager, AlertsEventBus alertsEventBus) {
        this.context = context;
        this.connectivityManager = connectivityManager;
        this.alertsEventBus = alertsEventBus;
    }

    private boolean hasNetworkConnectivity() {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConnectedEvent(boolean z) {
        this.alertsEventBus.postSticky(NetworkConnectivityEvent.create(z));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            sendConnectedEvent(hasNetworkConnectivity());
        }
    }

    public void register() {
        if (Build.VERSION.SDK_INT >= 28) {
            this.connectivityManager.registerDefaultNetworkCallback(this.defaultNetworkCallback);
        } else {
            this.context.registerReceiver(this, NETWORK_CHANGED_INTENT_FILTER);
        }
    }

    public void unregister() {
        if (Build.VERSION.SDK_INT >= 28) {
            this.connectivityManager.unregisterNetworkCallback(this.defaultNetworkCallback);
        } else {
            this.context.unregisterReceiver(this);
        }
    }
}
