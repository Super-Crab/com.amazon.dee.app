package com.amazon.communication;

import com.amazon.communication.NetworkStabilityMonitor;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.dp.logger.DPLogger;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public class NetworkStabilityMonitorImpl implements NetworkStabilityMonitor {
    private static final String NETWORK_DISCONNECTED = "disconnected";
    private final BackoffScheduler mBackoffScheduler;
    private final ConnectivityManagerWrapper mConnectivityManager;
    private final ConnectivityMonitor mConnectivityMonitor;
    private final WakeLockHoldingScheduledThreadPoolExecutor mThreadPool;
    private final WifiManagerWrapper mWifiManager;
    private static final DPLogger log = new DPLogger("TComm.NetworkStabilityMonitorImpl");
    public static final long DEFAULT_NETWORK_STABILITY_THRESHOLD_MILLIS = TimeUnit.MINUTES.toMillis(5);
    private static AtomicLong mStabilityThresholdMillis = new AtomicLong(DEFAULT_NETWORK_STABILITY_THRESHOLD_MILLIS);
    public static final Boolean DEFAULT_NETWORK_STABILITY_ENABLED_OVER_WAN = false;
    private static AtomicBoolean mStabilityOverWanEnabled = new AtomicBoolean(DEFAULT_NETWORK_STABILITY_ENABLED_OVER_WAN.booleanValue());
    private final CopyOnWriteArraySet<NetworkStabilityStateChangeListener> mListeners = new CopyOnWriteArraySet<>();
    private final AtomicReference<String> mCurrentNetworkId = new AtomicReference<>("unknown");
    private final AtomicReference<NetworkStabilityMonitor.NetworkStabilityState> mCurrentState = new AtomicReference<>(NetworkStabilityMonitor.NetworkStabilityState.UNKNOWN);
    private final ConnectivityChangedHandler mConnectivityChangedHandler = new ConnectivityChangedHandler() { // from class: com.amazon.communication.NetworkStabilityMonitorImpl.1
        @Override // com.amazon.communication.ConnectivityChangedHandler
        public void onConnectivityChanged() {
            NetworkStabilityMonitorImpl.log.verbose("ConnectivityChangedHandler.onConnectivityChanged", "received connectivity change notification", "isConnectivityPossible", Boolean.valueOf(NetworkStabilityMonitorImpl.this.mConnectivityMonitor.isConnectivityPossible()));
            if (NetworkStabilityMonitorImpl.this.mConnectivityMonitor.isConnectivityPossible()) {
                String networkId = NetworkStabilityMonitorImpl.this.getNetworkId();
                boolean stabilityOverWanEnabled = NetworkStabilityMonitorImpl.stabilityOverWanEnabled();
                String str = (String) NetworkStabilityMonitorImpl.this.mCurrentNetworkId.get();
                NetworkStabilityMonitorImpl.log.verbose("ConnectivityChangedHandler.onConnectivityChanged", "checking pre-conditions for starting the timer", "mCurrentNetworkId.get()", NetworkStabilityMonitorImpl.this.logSafe(str), "newNetworkId", NetworkStabilityMonitorImpl.this.logSafe(networkId), "isMobileAvailable()", Boolean.valueOf(NetworkStabilityMonitorImpl.this.mConnectivityMonitor.isMobileAvailable()), "isEnabledOverWan", Boolean.valueOf(stabilityOverWanEnabled));
                if (networkId == null || str == null || ((String) NetworkStabilityMonitorImpl.this.mCurrentNetworkId.getAndSet(networkId)).equals(networkId)) {
                    return;
                }
                if (NetworkStabilityMonitorImpl.this.mConnectivityMonitor.isMobileAvailable() && !stabilityOverWanEnabled) {
                    return;
                }
                NetworkStabilityMonitorImpl.this.startStabilityTimer();
                return;
            }
            NetworkStabilityMonitorImpl.this.mCurrentNetworkId.set("disconnected");
            NetworkStabilityMonitorImpl.this.cancelStabilityTimer();
            NetworkStabilityMonitorImpl.this.updateAndNotifyStateChange(NetworkStabilityMonitor.NetworkStabilityState.UNKNOWN);
        }
    };
    private final int mRegistrationId = hashCode();

    public NetworkStabilityMonitorImpl(ConnectivityMonitor connectivityMonitor, ConnectivityManagerWrapper connectivityManagerWrapper, WifiManagerWrapper wifiManagerWrapper, BackoffScheduler backoffScheduler, WakeLockHoldingScheduledThreadPoolExecutor wakeLockHoldingScheduledThreadPoolExecutor) {
        this.mConnectivityMonitor = connectivityMonitor;
        this.mConnectivityManager = connectivityManagerWrapper;
        this.mWifiManager = wifiManagerWrapper;
        this.mBackoffScheduler = backoffScheduler;
        this.mThreadPool = wakeLockHoldingScheduledThreadPoolExecutor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelStabilityTimer() {
        log.debug("cancelStabilityTimer", "canceling the stability timer", new Object[0]);
        this.mBackoffScheduler.cancel(this.mRegistrationId);
    }

    public static void enableStabilityOverWan(Boolean bool) {
        log.info("update", "Stability over wan is set to ", bool);
        mStabilityOverWanEnabled.set(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0036 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getNetworkId() {
        /*
            r1 = this;
            com.amazon.communication.ConnectivityMonitor r0 = r1.mConnectivityMonitor
            boolean r0 = r0.isConnectivityPossible()
            if (r0 == 0) goto L33
            com.amazon.communication.ConnectivityMonitor r0 = r1.mConnectivityMonitor
            boolean r0 = r0.isWiFiAvailable()
            if (r0 == 0) goto L17
            com.amazon.communication.wifi.WifiManagerWrapper r0 = r1.mWifiManager
            java.lang.String r0 = r0.getBssid()
            goto L34
        L17:
            com.amazon.communication.ConnectivityMonitor r0 = r1.mConnectivityMonitor
            boolean r0 = r0.isMobileAvailable()
            if (r0 == 0) goto L26
            com.amazon.communication.ConnectivityManagerWrapper r0 = r1.mConnectivityManager
            java.lang.String r0 = r0.getSimCountryAndNetworkCodes()
            goto L34
        L26:
            com.amazon.communication.ConnectivityManagerWrapper r0 = r1.mConnectivityManager
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            if (r0 == 0) goto L33
            java.lang.String r0 = r0.getTypeName()
            goto L34
        L33:
            r0 = 0
        L34:
            if (r0 != 0) goto L38
            java.lang.String r0 = "disconnected"
        L38:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.NetworkStabilityMonitorImpl.getNetworkId():java.lang.String");
    }

    public static long getStabilityThreshold() {
        return mStabilityThresholdMillis.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String logSafe(String str) {
        return str == null ? "null" : String.valueOf(str.hashCode());
    }

    public static void setStabilityThreshold(long j) {
        log.info("update", "Stability threshold value updated to ", Long.valueOf(j));
        mStabilityThresholdMillis.set(j);
    }

    public static boolean stabilityOverWanEnabled() {
        return mStabilityOverWanEnabled.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startStabilityTimer() {
        log.verbose("startStabilityTimer", "starting the stability timer", "getStabilityThreshold()", Long.valueOf(getStabilityThreshold()));
        this.mBackoffScheduler.schedule(this.mRegistrationId, new Runnable() { // from class: com.amazon.communication.NetworkStabilityMonitorImpl.2
            @Override // java.lang.Runnable
            public void run() {
                if (NetworkStabilityMonitorImpl.this.mConnectivityMonitor.isConnectivityPossible()) {
                    NetworkStabilityMonitorImpl.this.updateAndNotifyStateChange(NetworkStabilityMonitor.NetworkStabilityState.STABLE);
                }
            }
        }, getStabilityThreshold(), TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAndNotifyStateChange(final NetworkStabilityMonitor.NetworkStabilityState networkStabilityState) {
        if (this.mCurrentState.getAndSet(networkStabilityState) != networkStabilityState) {
            log.debug("updateAndNotifyStateChange", "notifying interested parties about network stability state change", "state", networkStabilityState);
            this.mThreadPool.submit(new Runnable() { // from class: com.amazon.communication.NetworkStabilityMonitorImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it2 = NetworkStabilityMonitorImpl.this.mListeners.iterator();
                    while (it2.hasNext()) {
                        ((NetworkStabilityStateChangeListener) it2.next()).onStateChanged(networkStabilityState);
                    }
                }
            });
        }
    }

    @Override // com.amazon.communication.NetworkStabilityMonitor
    public void addListener(NetworkStabilityStateChangeListener networkStabilityStateChangeListener) {
        this.mListeners.add(networkStabilityStateChangeListener);
        log.verbose("addListener", "added listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, networkStabilityStateChangeListener);
    }

    @Override // com.amazon.communication.NetworkStabilityMonitor
    public void removeListener(NetworkStabilityStateChangeListener networkStabilityStateChangeListener) {
        this.mListeners.remove(networkStabilityStateChangeListener);
        log.verbose("removeListener", "removed listener if present", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, networkStabilityStateChangeListener);
    }

    @Override // com.amazon.communication.NetworkStabilityMonitor
    public void start() {
        this.mConnectivityMonitor.registerConnectivityChangedHandler(this.mConnectivityChangedHandler);
        log.debug("start", "registered for connectivity changes", new Object[0]);
    }

    @Override // com.amazon.communication.NetworkStabilityMonitor
    public void stop() {
        cancelStabilityTimer();
        this.mConnectivityMonitor.deregisterConnectivityChangedHandler(this.mConnectivityChangedHandler);
        log.debug("stop", "deregistered from connectivity change monitor", new Object[0]);
    }
}
