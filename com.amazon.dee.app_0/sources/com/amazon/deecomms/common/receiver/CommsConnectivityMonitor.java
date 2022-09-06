package com.amazon.deecomms.common.receiver;

import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.StatePoller;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public class CommsConnectivityMonitor {
    private static final long AVAILABLE_NOT_READY_MS = 100;
    private static final long AVAILABLE_READY_MS = 0;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsConnectivityMonitor.class);
    private static final long LOST_NOT_CONNECTED_MS = 1000;
    private static final long LOST_STILL_CONNECTED_MS = 0;
    private static final long POLL_SLEEP_MS = 100;
    private static final String TYPE_MOBILE = "Mobile";
    private static final String TYPE_WIFI = "Wifi";
    private final ConnectivityManager connectivityManager;
    private final PackageManager packageManager;
    private final TelephonyManager telephonyManager;
    private final NetworkRequest.Builder REQUEST_BUILDER_WIFI = new NetworkRequest.Builder().addTransportType(1).addCapability(12);
    private final NetworkRequest.Builder REQUEST_BUILDER_MOBILE = new NetworkRequest.Builder().addTransportType(0).addCapability(12);
    private final Runnable networkChanged = new Runnable() { // from class: com.amazon.deecomms.common.receiver.CommsConnectivityMonitor.1
        @Override // java.lang.Runnable
        public void run() {
            ConnectionType determineConnectionType = CommsConnectivityMonitor.this.determineConnectionType();
            ConnectionType connectionType = (ConnectionType) CommsConnectivityMonitor.this.connectionType.getAndSet(determineConnectionType);
            if (connectionType != determineConnectionType) {
                CommsConnectivityMonitor.LOG.i(String.format("Connection type changed: %s -> %s", connectionType, determineConnectionType));
                for (OnConnectionTypeChangedListener onConnectionTypeChangedListener : CommsConnectivityMonitor.this.listeners) {
                    onConnectionTypeChangedListener.onConnectionTypeChanged(connectionType, determineConnectionType);
                }
                return;
            }
            CommsLogger commsLogger = CommsConnectivityMonitor.LOG;
            commsLogger.v("Connection type unchanged: " + determineConnectionType);
        }
    };
    private final NetworkCallback wifiNetworkCallback = new NetworkCallback(TYPE_WIFI, this.REQUEST_BUILDER_WIFI);
    private final NetworkCallback mobileNetworkCallback = new NetworkCallback(TYPE_MOBILE, this.REQUEST_BUILDER_MOBILE);
    private final Set<OnConnectionTypeChangedListener> listeners = Collections.synchronizedSet(new LinkedHashSet());
    private final AtomicReference<ConnectionType> connectionType = new AtomicReference<>(ConnectionType.NotConnected);
    private final AtomicBoolean subscribed = new AtomicBoolean(false);
    private final Handler handler = new Handler(Looper.getMainLooper());

    /* loaded from: classes12.dex */
    public enum ConnectionType {
        NotConnected(0),
        Cellular(1),
        Wifi(1),
        CellularAndWifi(2);
        
        private final int connectionCount;

        ConnectionType(int i) {
            this.connectionCount = i;
        }

        public int getConnectionCount() {
            return this.connectionCount;
        }

        public boolean hasCellular() {
            return this == Cellular || this == CellularAndWifi;
        }

        public boolean hasWifi() {
            return this == Wifi || this == CellularAndWifi;
        }

        public boolean isConnected() {
            return this.connectionCount > 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class NetworkCallback extends SafeNetworkCallback {
        public NetworkCallback(@NonNull String str, @NonNull NetworkRequest.Builder builder) {
            super(str, builder);
        }

        private void checkForNetworkChange(long j) {
            CommsLogger commsLogger = CommsConnectivityMonitor.LOG;
            commsLogger.v("checkForNetworkChange: delay=" + j);
            CommsConnectivityMonitor.this.handler.removeCallbacks(CommsConnectivityMonitor.this.networkChanged);
            if (j > 0) {
                CommsConnectivityMonitor.this.handler.postDelayed(CommsConnectivityMonitor.this.networkChanged, j);
            } else {
                CommsConnectivityMonitor.this.handler.post(CommsConnectivityMonitor.this.networkChanged);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(@Nullable Network network) {
            if (network == null) {
                CommsConnectivityMonitor.LOG.e("onAvailable: null network");
                return;
            }
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("onAvailable: "), CommsConnectivityMonitor.this.logNetwork(network), CommsConnectivityMonitor.LOG);
            NetworkInfo networkInfo = CommsConnectivityMonitor.this.connectivityManager.getNetworkInfo(network);
            if (networkInfo != null && !CommsConnectivityMonitor.this.isNetworkUsable(networkInfo.getTypeName(), networkInfo)) {
                checkForNetworkChange(100L);
            } else {
                checkForNetworkChange(0L);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(@Nullable Network network) {
            if (network == null) {
                CommsConnectivityMonitor.LOG.e("onLost: null network");
                return;
            }
            CommsLogger commsLogger = CommsConnectivityMonitor.LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("onLost: ");
            outline1.append(CommsConnectivityMonitor.this.logNetwork(network));
            commsLogger.d(outline1.toString());
            if (CommsConnectivityMonitor.this.determineConnectionType() == ConnectionType.NotConnected) {
                CommsConnectivityMonitor.LOG.d("onLost: posting delayed message for nothing connected");
                checkForNetworkChange(1000L);
                return;
            }
            checkForNetworkChange(0L);
        }
    }

    /* loaded from: classes12.dex */
    public interface OnConnectionTypeChangedListener {
        void onConnectionTypeChanged(@NonNull ConnectionType connectionType, @NonNull ConnectionType connectionType2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public final class Poller extends StatePoller {
        Poller() {
        }

        @Override // com.amazon.deecomms.common.util.StatePoller
        @NonNull
        protected CommsLogger getPollerLogger() {
            return CommsConnectivityMonitor.LOG;
        }

        @Override // com.amazon.deecomms.common.util.StatePoller
        protected long getPollerSleepTime() {
            return 100L;
        }

        @Override // com.amazon.deecomms.common.util.StatePoller
        public boolean isInExpectedState() {
            return CommsConnectivityMonitor.this.isConnected();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public class SafeNetworkCallback extends ConnectivityManager.NetworkCallback {
        private final NetworkRequest.Builder networkRequestBuilder;
        private final String networkType;
        private boolean registered = false;

        @VisibleForTesting
        SafeNetworkCallback(@NonNull String str, @NonNull NetworkRequest.Builder builder) {
            this.networkType = str;
            this.networkRequestBuilder = builder;
        }

        @VisibleForTesting
        synchronized void register() {
            if (this.registered) {
                CommsLogger commsLogger = CommsConnectivityMonitor.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Network callback already requested or registered for ");
                outline1.append(this.networkType);
                commsLogger.e(outline1.toString());
                return;
            }
            CommsConnectivityMonitor.this.connectivityManager.registerNetworkCallback(this.networkRequestBuilder.build(), this);
            CommsLogger commsLogger2 = CommsConnectivityMonitor.LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Registered for network callbacks for ");
            outline12.append(this.networkType);
            commsLogger2.i(outline12.toString());
            this.registered = true;
        }

        @VisibleForTesting
        synchronized void unregister() {
            if (!this.registered) {
                CommsLogger commsLogger = CommsConnectivityMonitor.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Network callback already unregistered for ");
                outline1.append(this.networkType);
                commsLogger.e(outline1.toString());
                return;
            }
            CommsLogger commsLogger2 = CommsConnectivityMonitor.LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Unregistering network callback for ");
            outline12.append(this.networkType);
            commsLogger2.d(outline12.toString());
            CommsConnectivityMonitor.this.connectivityManager.unregisterNetworkCallback(this);
            this.registered = false;
        }
    }

    public CommsConnectivityMonitor(@NonNull ConnectivityManager connectivityManager, @NonNull PackageManager packageManager, @NonNull TelephonyManager telephonyManager) {
        this.connectivityManager = connectivityManager;
        this.packageManager = packageManager;
        this.telephonyManager = telephonyManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public String logNetwork(@NonNull Network network) {
        NetworkInfo networkInfo = this.connectivityManager.getNetworkInfo(network);
        if (networkInfo == null) {
            return LOG.sensitive(Integer.toString(network.hashCode()));
        }
        return LOG.sensitive(String.format("%s: %s, connected=%b, available=%b", Integer.valueOf(network.hashCode()), networkInfo.getTypeName(), Boolean.valueOf(networkInfo.isConnected()), Boolean.valueOf(networkInfo.isAvailable())));
    }

    public void deRegisterListener(@NonNull OnConnectionTypeChangedListener onConnectionTypeChangedListener) {
        if (this.listeners.remove(onConnectionTypeChangedListener)) {
            CommsLogger commsLogger = LOG;
            commsLogger.d("Removed listener: " + onConnectionTypeChangedListener);
        }
    }

    @NonNull
    @VisibleForTesting
    ConnectionType determineConnectionType() {
        Network[] allNetworks = this.connectivityManager.getAllNetworks();
        if (allNetworks != null && allNetworks.length != 0) {
            NetworkInfo networkInfo = null;
            NetworkInfo networkInfo2 = null;
            for (Network network : allNetworks) {
                NetworkInfo networkInfo3 = this.connectivityManager.getNetworkInfo(network);
                if (networkInfo3 != null) {
                    int type = networkInfo3.getType();
                    if (type == 0) {
                        networkInfo2 = networkInfo3;
                    } else if (type == 1) {
                        networkInfo = networkInfo3;
                    }
                }
            }
            boolean isNetworkUsable = isNetworkUsable(TYPE_WIFI, networkInfo);
            boolean isNetworkUsable2 = isNetworkUsable(TYPE_MOBILE, networkInfo2);
            if (isNetworkUsable && isNetworkUsable2) {
                return ConnectionType.CellularAndWifi;
            }
            if (isNetworkUsable) {
                return ConnectionType.Wifi;
            }
            if (isNetworkUsable2) {
                return ConnectionType.Cellular;
            }
            return ConnectionType.NotConnected;
        }
        return ConnectionType.NotConnected;
    }

    @NonNull
    public ConnectionType getConnectionType() {
        return this.connectionType.get();
    }

    @VisibleForTesting
    AtomicReference<ConnectionType> getConnectionTypeForTesting() {
        return this.connectionType;
    }

    @VisibleForTesting
    Set<OnConnectionTypeChangedListener> getListenersForTesting() {
        return this.listeners;
    }

    @VisibleForTesting
    ConnectivityManager.NetworkCallback getMobileNetworkCallbackForTesting() {
        return this.mobileNetworkCallback;
    }

    @VisibleForTesting
    ConnectivityManager.NetworkCallback getWifiNetworkCallbackForTesting() {
        return this.wifiNetworkCallback;
    }

    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean isMobileDataEnabled() {
        try {
            Method declaredMethod = Class.forName(this.connectivityManager.getClass().getName()).getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(this.connectivityManager, new Object[0])).booleanValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LOG.e("Could not obtain mobile data enabled", e);
            return true;
        }
    }

    @VisibleForTesting
    boolean isNetworkUsable(@NonNull String str, @Nullable NetworkInfo networkInfo) {
        if (networkInfo == null) {
            LOG.v(String.format("isNetworkUsable: %s -> no info", str));
            return false;
        }
        boolean isConnected = networkInfo.isConnected();
        boolean isAvailable = networkInfo.isAvailable();
        if (TYPE_MOBILE.equals(str)) {
            int dataState = this.telephonyManager.getDataState();
            boolean isMobileDataEnabled = isMobileDataEnabled();
            LOG.i(String.format(Locale.US, "isNetworkUsable: %s -> connected=%b, available=%b , telephony_dataState=%d, mobileDataEnabled=%b", str, Boolean.valueOf(isConnected), Boolean.valueOf(isAvailable), Integer.valueOf(dataState), Boolean.valueOf(isMobileDataEnabled)));
            return isConnected && isAvailable && dataState == 2 && isMobileDataEnabled;
        }
        LOG.i(String.format("isNetworkUsable: %s -> connected=%b, available=%b", str, Boolean.valueOf(isConnected), Boolean.valueOf(isAvailable)));
        return isConnected && isAvailable;
    }

    public boolean isSubscribed() {
        return this.subscribed.get();
    }

    public boolean isTransitioningToConnected(@NonNull ConnectionType connectionType, @NonNull ConnectionType connectionType2) {
        return !connectionType.isConnected() && connectionType2.isConnected();
    }

    public boolean isTransitioningToDisconnected(@NonNull ConnectionType connectionType, @NonNull ConnectionType connectionType2) {
        return connectionType.isConnected() && !connectionType2.isConnected();
    }

    public void registerListener(@NonNull OnConnectionTypeChangedListener onConnectionTypeChangedListener) {
        if (this.listeners.add(onConnectionTypeChangedListener)) {
            CommsLogger commsLogger = LOG;
            commsLogger.d("Added listener: " + onConnectionTypeChangedListener);
        }
    }

    public void subscribeNetworkChangeEvents() {
        if (this.subscribed.getAndSet(true)) {
            LOG.d("subscribeNetworkChangeEvents: Already subscribed");
            return;
        }
        this.connectionType.set(determineConnectionType());
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("subscribeNetworkChangeEvents: ");
        outline1.append(getConnectionType());
        commsLogger.d(outline1.toString());
        this.wifiNetworkCallback.register();
        if (this.packageManager.hasSystemFeature("android.hardware.telephony")) {
            this.mobileNetworkCallback.register();
        } else {
            LOG.d("subscribeNetworkChangeEvents: device doesn't support telephony");
        }
    }

    public void unsubscribeNetworkChangeEvents() {
        if (!this.subscribed.getAndSet(false)) {
            LOG.d("unsubscribeNetworkChangeEvents: Already unsubscribed");
            return;
        }
        this.connectionType.set(ConnectionType.NotConnected);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("unsubscribeNetworkChangeEvents: ");
        outline1.append(getConnectionType());
        commsLogger.d(outline1.toString());
        this.wifiNetworkCallback.unregister();
        this.mobileNetworkCallback.unregister();
        this.handler.removeCallbacks(this.networkChanged);
    }

    public boolean waitForConnected(long j) {
        return new Poller().waitForExpectedState(j);
    }
}
