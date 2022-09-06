package com.amazon.communication;

import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import android.content.Context;
import com.amazon.communication.ScreenEventListener;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.communication.heartbeat.ConnectionHealthManager;
import com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator;
import com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.watchdog.BetterWatchdog;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.ThreadGuard;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class AlwaysOnSocketWatchdogManager {
    private static final String WAKE_LOCK_TAG = "TComm.AlwaysOnSocketWatchdog";
    private MapAccountManagerWrapper mAccountManager;
    private BackoffScheduler mBackoffScheduler;
    private ConnectionHealthManager mConnectionHealthManager;
    private ConnectionHealthStatisticsAggregator mConnectionHealthStatisticsAggregator;
    private ConnectivityMonitor mConnectivityMonitor;
    private Context mContext;
    protected WakeLockHoldingScheduledThreadPoolExecutor mExecutor;
    private HeartbeatIntervalDeterminer mHeartbeatIntervalDeterminer;
    private IdentityResolver mIdentityResolver;
    private NetworkStabilityMonitor mNetworkStabilityMonitor;
    private PowerManagerWrapper mPowerManager;
    private int mRegistrationId;
    private ScreenEventListener mScreenEventListener;
    private ScreenEventMonitor mScreenEventMonitor;
    private Runnable mScreenOffRunnable;
    private SocketDecisionEngine mSocketDecisionEngine;
    private TCommService mTCommService;
    protected final Map<String, AlwaysOnSocketWatchdog> mWatchdogMap = new HashMap();
    private WifiManagerWrapper mWifiManager;
    private static final DPLogger log = new DPLogger("TComm.AlwaysOnSocketWatchdogManager");
    private static final long WAKE_LOCK_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(1);

    /* loaded from: classes12.dex */
    public interface Callback {
        void setWatchdog(AlwaysOnSocketWatchdog alwaysOnSocketWatchdog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class WatchdogAppValueSetting {
        private static final int SCREEN_OFF_DELAY_MS_DEFAULT = 60000;
        private static final String SCREEN_OFF_DELAY_MS_KEY = "Watchdog.ScreenOffDelay";
        private static final Boolean SHUTDOWN_D2D_MESSAGING_GW_SOCKET_SCREEN_OFF_DEFAULT = true;
        private static final String SHUTDOWN_D2D_MESSAGING_GW_SOCKET_SCREEN_OFF_KEY = "Setting.ShutdownD2DMsgGwSocketScreenOff";

        private WatchdogAppValueSetting() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int getScreenOffDelayMs() {
            return RemoteSettingManager.getOptIntValue(SCREEN_OFF_DELAY_MS_KEY, 60000).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean shouldShutdownD2DMessagingGwSocketScreenOff() {
            return RemoteSettingManager.getOptBooleanValue(SHUTDOWN_D2D_MESSAGING_GW_SOCKET_SCREEN_OFF_KEY, SHUTDOWN_D2D_MESSAGING_GW_SOCKET_SCREEN_OFF_DEFAULT).booleanValue();
        }
    }

    private boolean isGatewayEndpoint(EndpointIdentity endpointIdentity) {
        for (EndpointIdentity endpointIdentity2 : GatewayConnectionService.GATEWAY_ALIASES) {
            if (endpointIdentity2.equals(endpointIdentity)) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldShutdownWatchdogWhenScreenOff(AlwaysOnSocketWatchdog alwaysOnSocketWatchdog) {
        log.verbose("shouldShutdownWatchdogWhenScreenOff", "checking if watchdog should be shutdown", "settingValue", Boolean.valueOf(WatchdogAppValueSetting.shouldShutdownD2DMessagingGwSocketScreenOff()), "watchdog", alwaysOnSocketWatchdog, "isGatewayEndpoint", Boolean.valueOf(isGatewayEndpoint(alwaysOnSocketWatchdog.getWatchedEndpoint())));
        if (!isGatewayEndpoint(alwaysOnSocketWatchdog.getWatchedEndpoint())) {
            return true;
        }
        if (!WatchdogAppValueSetting.shouldShutdownD2DMessagingGwSocketScreenOff() || !Purpose.D2D_MESSAGING.equals(alwaysOnSocketWatchdog.getPolicy().getPurpose())) {
            return false;
        }
        log.info("shouldShutdownWatchdogWhenScreenOff", "D2D_MESSAGING gateway connection. Returning true.", new Object[0]);
        return true;
    }

    protected static final String toMapKey(EndpointIdentity endpointIdentity, Purpose purpose) {
        if (GatewayConnectionService.GATEWAY_ALIASES.contains(endpointIdentity)) {
            endpointIdentity = GatewayConnectionService.DIRECTED_GATEWAY_ENDPOINT;
        }
        return String.format("%s:%s", ((ServiceIdentity) EndpointIdentityFactory.createFromUrn(endpointIdentity.toString())).getServiceName(), purpose.toString());
    }

    public AlwaysOnSocketWatchdog getAndRetain(final IRServiceEndpoint iRServiceEndpoint, final EndpointIdentity endpointIdentity, final Policy policy) {
        try {
            return (AlwaysOnSocketWatchdog) this.mExecutor.submit(new Callable<AlwaysOnSocketWatchdog>() { // from class: com.amazon.communication.AlwaysOnSocketWatchdogManager.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                /* renamed from: call */
                public AlwaysOnSocketWatchdog mo3278call() {
                    return AlwaysOnSocketWatchdogManager.this.getAndRetainSync(iRServiceEndpoint, endpointIdentity, policy);
                }
            }).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException(e2.getCause());
        }
    }

    protected AlwaysOnSocketWatchdog getAndRetainSync(IRServiceEndpoint iRServiceEndpoint, EndpointIdentity endpointIdentity, Policy policy) {
        Iterator<AlwaysOnSocketWatchdog> it2;
        char c = 1;
        log.debug("getAndRetain", "getting watchdog", "irEndpoint", iRServiceEndpoint, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "policy", policy);
        ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
        String resolvedUri = iRServiceEndpoint.toResolvedUri(policy.isClearText() && iRServiceEndpoint.getClearTextConnection() == IRServiceEndpoint.ClearTextConnection.ALLOWED ? IRServiceEndpoint.Scheme.WS : IRServiceEndpoint.Scheme.WSS);
        String mapKey = toMapKey(endpointIdentity, policy.getPurpose());
        AlwaysOnSocketWatchdog alwaysOnSocketWatchdog = this.mWatchdogMap.get(mapKey);
        if (alwaysOnSocketWatchdog != null) {
            log.debug("getAndRetain", "found watchdog in map", "mapKey", mapKey, "endpoint", endpointIdentity, "irEndpoint", iRServiceEndpoint, "policy", policy, "watchdog", alwaysOnSocketWatchdog);
            alwaysOnSocketWatchdog.retain();
            return alwaysOnSocketWatchdog;
        }
        Iterator<AlwaysOnSocketWatchdog> it3 = this.mWatchdogMap.values().iterator();
        while (it3.hasNext()) {
            AlwaysOnSocketWatchdog next = it3.next();
            if (next.getWatchedUrl().equals(resolvedUri)) {
                DPLogger dPLogger = log;
                it2 = it3;
                Object[] objArr = new Object[2];
                objArr[0] = "watchdog";
                objArr[c] = next;
                dPLogger.debug("getAndRetain", "found watchdog with matching url, checking for policy compatibility", objArr);
                if (next.getPolicy().getPurpose() != null && next.getPolicy().getPurpose().equals(policy.getPurpose())) {
                    log.debug("getAndRetain", "found watchdog with matching policy", "watchdog", next);
                    next.retain();
                    return next;
                } else if (Purpose.REGULAR.equals(next.getPolicy().getPurpose()) && !policy.isDedicated()) {
                    log.debug("getAndRetain", "using watchdog with AlwaysOn policy", "watchdog", next);
                    next.retain();
                    return next;
                } else if (Purpose.REGULAR.equals(policy.getPurpose()) && !next.getPolicy().isDedicated()) {
                    log.debug("getAndRetain", "replacing existing watchdog with new Regular policy", "watchdog", next);
                    next.updateWatchdogAndReconnect(policy, endpointIdentity);
                    next.retain();
                    return next;
                }
            } else {
                it2 = it3;
            }
            it3 = it2;
            c = 1;
        }
        log.debug("getAndRetain", "creating new watchdog", new Object[0]);
        BetterWatchdog betterWatchdog = new BetterWatchdog(resolvedUri, endpointIdentity, policy);
        betterWatchdog.setContext(this.mContext);
        betterWatchdog.setExecutor(this.mExecutor);
        betterWatchdog.setSocketDecisionEngine(this.mSocketDecisionEngine);
        betterWatchdog.setIdentityResolver(this.mIdentityResolver);
        betterWatchdog.setConnectivityMonitor(this.mConnectivityMonitor);
        betterWatchdog.setScreenEventMonitor(this.mScreenEventMonitor);
        betterWatchdog.setNetworkStabilityMonitor(this.mNetworkStabilityMonitor);
        betterWatchdog.setBackoffScheduler(this.mBackoffScheduler);
        betterWatchdog.setConnectionHealthManager(this.mConnectionHealthManager);
        betterWatchdog.setHeartbeatIntervalDeterminer(this.mHeartbeatIntervalDeterminer);
        betterWatchdog.setConnectionHealthStatisticsAggregator(this.mConnectionHealthStatisticsAggregator);
        betterWatchdog.setWifiManager(this.mWifiManager);
        betterWatchdog.setAlwaysOnSocketWatchdogManager(this);
        betterWatchdog.setTCommService(this.mTCommService);
        betterWatchdog.initialize();
        this.mWatchdogMap.put(mapKey, betterWatchdog);
        log.info("getAndRetain", "returning watchdog", "irEndpoint", iRServiceEndpoint, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "policy", policy, "watchdog", betterWatchdog);
        return betterWatchdog;
    }

    public void initialize() {
        this.mExecutor = new WakeLockHoldingScheduledThreadPoolExecutor(1, ThreadName.WATCHDOG, new TCommUncaughtExceptionHandler(), this.mPowerManager.newWakeLock(1, WAKE_LOCK_TAG), WAKE_LOCK_TIMEOUT_MS);
        this.mScreenEventListener = new ScreenEventListener() { // from class: com.amazon.communication.AlwaysOnSocketWatchdogManager.1
            @Override // com.amazon.communication.ScreenEventListener
            public void onScreenEvent(final ScreenEventListener.Event event) {
                AlwaysOnSocketWatchdogManager.log.info("onScreenEvent", "received screen event", "event", event);
                AlwaysOnSocketWatchdogManager.this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.AlwaysOnSocketWatchdogManager.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (event != ScreenEventListener.Event.OFF) {
                            AlwaysOnSocketWatchdogManager.this.mBackoffScheduler.cancel(AlwaysOnSocketWatchdogManager.this.mRegistrationId);
                            AlwaysOnSocketWatchdogManager.this.restartStoppedWatchdogs(ConnectReason.ReasonString.ScreenEvent);
                            return;
                        }
                        AlwaysOnSocketWatchdogManager.this.scheduleShutdown();
                    }
                });
            }
        };
        this.mScreenEventMonitor.registerScreenEventListener(this.mScreenEventListener);
        this.mScreenOffRunnable = new Runnable() { // from class: com.amazon.communication.AlwaysOnSocketWatchdogManager.2
            @Override // java.lang.Runnable
            public void run() {
                AlwaysOnSocketWatchdogManager.this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.AlwaysOnSocketWatchdogManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AlwaysOnSocketWatchdogManager.this.shutdownAllWatchdogs();
                    }
                });
            }
        };
        this.mRegistrationId = hashCode();
    }

    public void removeWatchdog(AlwaysOnSocketWatchdog alwaysOnSocketWatchdog) {
        String mapKey = toMapKey(alwaysOnSocketWatchdog.getWatchedEndpoint(), alwaysOnSocketWatchdog.getPolicy().getPurpose());
        log.debug("removeWatchdog", "removing watchdog from map", "watchdog", alwaysOnSocketWatchdog);
        ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
        log.debug("removeWatchdog", "removed watchdog", "mapKey", mapKey, "removed", this.mWatchdogMap.remove(mapKey));
    }

    protected void restartStoppedWatchdogs(ConnectReason.ReasonString reasonString) {
        ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
        log.debug("restartStoppedWatchdogs", "restarting stopped watchdogs", new Object[0]);
        for (AlwaysOnSocketWatchdog alwaysOnSocketWatchdog : this.mWatchdogMap.values()) {
            if (shouldShutdownWatchdogWhenScreenOff(alwaysOnSocketWatchdog)) {
                log.debug("restartStoppedWatchdogs", "starting watchdog", "watchdog", alwaysOnSocketWatchdog);
                alwaysOnSocketWatchdog.startWatching(reasonString);
            }
        }
    }

    protected void scheduleShutdown() {
        boolean z;
        ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
        log.debug("scheduleShutdown", "scheduling shutdown of all non-gateway watchdogs", new Object[0]);
        Iterator<AlwaysOnSocketWatchdog> it2 = this.mWatchdogMap.values().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            } else if (shouldShutdownWatchdogWhenScreenOff(it2.next())) {
                z = true;
                break;
            }
        }
        if (z) {
            log.info("scheduleShutdown", "has non-gateway watchdog, scheduling", new Object[0]);
            this.mBackoffScheduler.schedule(this.mRegistrationId, this.mScreenOffRunnable, WatchdogAppValueSetting.getScreenOffDelayMs(), TimeUnit.MILLISECONDS);
            return;
        }
        log.info("scheduleShutdown", "no non-gateway watchdogs found, doing nothing", new Object[0]);
    }

    public void setAccountManager(MapAccountManagerWrapper mapAccountManagerWrapper) {
        this.mAccountManager = mapAccountManagerWrapper;
    }

    public void setBackoffScheduler(BackoffScheduler backoffScheduler) {
        this.mBackoffScheduler = backoffScheduler;
    }

    public void setConnectionHealthManager(ConnectionHealthManager connectionHealthManager) {
        this.mConnectionHealthManager = connectionHealthManager;
    }

    public void setConnectionHealthStatisticsAggregator(ConnectionHealthStatisticsAggregator connectionHealthStatisticsAggregator) {
        this.mConnectionHealthStatisticsAggregator = connectionHealthStatisticsAggregator;
    }

    public void setConnectivityMonitor(ConnectivityMonitor connectivityMonitor) {
        this.mConnectivityMonitor = connectivityMonitor;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setHeartbeatIntervalDeterminer(HeartbeatIntervalDeterminer heartbeatIntervalDeterminer) {
        this.mHeartbeatIntervalDeterminer = heartbeatIntervalDeterminer;
    }

    public void setIdentityResolver(IdentityResolver identityResolver) {
        this.mIdentityResolver = identityResolver;
    }

    public void setNetworkStabilityMonitor(NetworkStabilityMonitor networkStabilityMonitor) {
        this.mNetworkStabilityMonitor = networkStabilityMonitor;
    }

    public void setPowerManager(PowerManagerWrapper powerManagerWrapper) {
        this.mPowerManager = powerManagerWrapper;
    }

    public void setScreenEventMonitor(ScreenEventMonitor screenEventMonitor) {
        this.mScreenEventMonitor = screenEventMonitor;
    }

    public void setSocketDecisionEngine(SocketDecisionEngine socketDecisionEngine) {
        this.mSocketDecisionEngine = socketDecisionEngine;
    }

    public void setTCommService(TCommService tCommService) {
        this.mTCommService = tCommService;
    }

    public void setWifiManager(WifiManagerWrapper wifiManagerWrapper) {
        this.mWifiManager = wifiManagerWrapper;
    }

    public void shutdown() {
        log.info("shutdown", "shutting down AlwaysOnSocketWatchdogManager", new Object[0]);
        for (final AlwaysOnSocketWatchdog alwaysOnSocketWatchdog : this.mWatchdogMap.values()) {
            alwaysOnSocketWatchdog.stopWatching();
            this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.AlwaysOnSocketWatchdogManager.4
                @Override // java.lang.Runnable
                public void run() {
                    alwaysOnSocketWatchdog.shutdown();
                }
            });
        }
        this.mExecutor.properShutdown();
        ScreenEventListener screenEventListener = this.mScreenEventListener;
        if (screenEventListener != null) {
            this.mScreenEventMonitor.deregisterScreenEventListener(screenEventListener);
            this.mScreenEventListener = null;
        }
    }

    protected void shutdownAllWatchdogs() {
        ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
        log.debug("shutdownAllWatchdogs", "shutting down", new Object[0]);
        for (AlwaysOnSocketWatchdog alwaysOnSocketWatchdog : this.mWatchdogMap.values()) {
            if (shouldShutdownWatchdogWhenScreenOff(alwaysOnSocketWatchdog)) {
                log.info("shutdownAllWatchdogs", "shutting down watchdog", "watchdog", alwaysOnSocketWatchdog);
                alwaysOnSocketWatchdog.stopWatching();
            }
        }
    }
}
