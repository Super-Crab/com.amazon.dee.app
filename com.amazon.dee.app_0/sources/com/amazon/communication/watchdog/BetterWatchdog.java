package com.amazon.communication.watchdog;

import amazon.communication.connection.KeepAlive;
import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import amazon.speech.simclient.settings.Settings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.provider.Settings;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.communication.AlwaysOnSocketWatchdog;
import com.amazon.communication.AlwaysOnSocketWatchdogManager;
import com.amazon.communication.BackoffScheduler;
import com.amazon.communication.ConnectivityChangedHandler;
import com.amazon.communication.ConnectivityMonitor;
import com.amazon.communication.ExponentialBackoffWaitCalculator;
import com.amazon.communication.NetworkStabilityMonitor;
import com.amazon.communication.NetworkStabilityStateChangeListener;
import com.amazon.communication.ScreenEventListener;
import com.amazon.communication.ScreenEventMonitor;
import com.amazon.communication.SocketDecisionEngine;
import com.amazon.communication.SocketWatchdogAccountListener;
import com.amazon.communication.TCommService;
import com.amazon.communication.ThreadName;
import com.amazon.communication.WakeLockHoldingScheduledThreadPoolExecutor;
import com.amazon.communication.gw.GatewayConnectivityListener;
import com.amazon.communication.heartbeat.ConnectionHealthManager;
import com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator;
import com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer;
import com.amazon.communication.heartbeat.ProbingConnectionLifetimeManager;
import com.amazon.communication.heartbeat.TimeSinceGauge;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.remotesetting.SettingUpdateListener;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.communication.watchdog.SystemSnapshot;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseReason;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.codahale.metrics.ValueGauge;
import com.dp.utils.ThreadGuard;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.VisibleForTesting;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes12.dex */
public class BetterWatchdog implements AlwaysOnSocketWatchdog, ProtocolSocket.ProtocolSocketStateListener {
    @VisibleForTesting
    static final int BACKOFF_COEFFICIENT_DEFAULT = 60000;
    private static final String BACKOFF_COEFFICIENT_KEY = "Watchdog.BackoffCoefficient";
    protected static final long CONSOLIDATION_PERIOD_IN_MS_DEFAULT = 2000;
    private static final String CONSOLIDATION_PERIOD_IN_MS_KEY = "Watchdog.AccountChangeConsolidationPeriod";
    private static final long DELAY_AFTER_THROTTLED_CONNECTION_MS_DEFAULT = 60000;
    private static final String DELAY_AFTER_THROTTLED_CONNECTION_MS_KEY = "Watchdog.ThrottleDelay";
    protected static final String ENTERING_STR_ACTION = "amazon.intent.action.ENTERING_STR_MODE";
    @VisibleForTesting
    static final int JITTER_AS_FRACTION_OF_BACKOFF_INTERVAL_DEFAULT = 30;
    private static final String JITTER_AS_FRACTION_OF_BACKOFF_INTERVAL_KEY = "Watchdog.Jitter";
    @VisibleForTesting
    static final long MAX_BACKOFF_INTERVAL_MS_DEFAULT = 1800000;
    private static final String MAX_BACKOFF_INTERVAL_MS_KEY = "Watchdog.MaxBackoffInterval";
    protected static final String METRICS_SOURCE_NAME = "BetterWatchdog";
    @VisibleForTesting
    static final long MIN_BACKOFF_INTERVAL_MS_DEFAULT = 10000;
    private static final String MIN_BACKOFF_INTERVAL_MS_KEY = "Watchdog.MinBackoffInterval";
    private static final int MIN_CONNECTION_DURATION_MS_DEFAULT = 180000;
    private static final String MIN_CONNECTION_DURATION_MS_KEY = "Watchdog.MinConnectionDuration";
    private static final String NETWORK_INTERFACE_UNKNOWN = "Unknown";
    private static final String SETTINGS_KEY_PREFIX = "Watchdog.";
    private static final boolean SMART_SUSPEND_ENABLED_DEFAULT = true;
    private static final String SMART_SUSPEND_ENABLED_KEY = "Watchdog.EnableSmartSuspend";
    protected static final String SMART_SUSPEND_RADIO_OFF = "com.amazon.smartsuspend.networkmodule.action.RADIO_OFF";
    protected static final String SMART_SUSPEND_RADIO_ON = "com.amazon.smartsuspend.networkmodule.action.RADIO_ON";
    @VisibleForTesting
    static final long STATIC_BACKOFF_INTERVAL_MS_DEFAULT = 5000;
    private static final String STATIC_BACKOFF_INTERVAL_MS_KEY = "Watchdog.StaticBackoffInterval";
    private Future<?> futureSnapshotEnqueueJob;
    protected ValueGauge<Long> mBackoffIntervalGauge;
    private BackoffScheduler mBackoffScheduler;
    protected ConnectionHealthManager mConnectionHealthManager;
    private ConnectionHealthStatisticsAggregator mConnectionHealthStatisticsAggregator;
    private Integer mConnectionType;
    protected ConnectivityMonitor mConnectivityMonitor;
    private Context mContext;
    protected EndpointIdentity mEndpoint;
    protected WakeLockHoldingScheduledThreadPoolExecutor mExecutor;
    private HeartbeatIntervalDeterminer mHeartbeatIntervalDeterminer;
    private IdentityResolver mIdentityResolver;
    protected SystemSnapshot mLatestAccountChangeSnapshot;
    protected boolean mMaxTCommAvailability;
    protected NetworkStabilityMonitor mNetworkStabilityMonitor;
    private WifiManagerWrapper.WifiLock mNonWifiNetworkLock;
    private Policy mPolicy;
    protected ProbingConnectionLifetimeManager mProbingConnectionLifetimeManager;
    protected ScreenEventMonitor mScreenEventMonitor;
    private final String mServiceName;
    protected boolean mSmartSuspendEnabled;
    private long mSocketConnectedTime;
    private SocketDecisionEngine mSocketDecisionEngine;
    protected ValueGauge<String> mSocketFqdnGauge;
    protected ValueGauge<String> mSocketStateGauge;
    private Set<ProtocolSocket.ProtocolSocketStateListener> mSocketStateListeners;
    private TimeSinceGauge mSocketStateSinceGauge;
    private TCommService mTCommService;
    protected final TriggerSet mTriggerSet;
    protected String mUrl;
    protected ExponentialBackoffWaitCalculator mWaitCalculator;
    protected SocketWatchdogAccountListener mWatchdogAccountChangeListener;
    protected AlwaysOnSocketWatchdogManager mWatchdogManager;
    private Set<AlwaysOnSocketWatchdog.WatchdogStateListener> mWatchdogStateListeners;
    private WifiManagerWrapper mWifiManager;
    private WifiManagerWrapper.WifiLock mWifiNetworkLock;
    protected static final IntentFilter SMART_SUSPEND_FILTER = new IntentFilter() { // from class: com.amazon.communication.watchdog.BetterWatchdog.1
        {
            addAction(BetterWatchdog.SMART_SUSPEND_RADIO_ON);
            addAction(BetterWatchdog.SMART_SUSPEND_RADIO_OFF);
        }
    };
    protected static final IntentFilter ENTERING_STR_FILTER = new IntentFilter() { // from class: com.amazon.communication.watchdog.BetterWatchdog.2
        {
            addAction(BetterWatchdog.ENTERING_STR_ACTION);
        }
    };
    private static final Integer TYPE_UNKNOWN = -1;
    private final DPLogger log = new DPLogger("TComm.BetterWatchdog");
    protected ProtocolSocket mProtocolSocket = null;
    private final Lock mProtocolSocketLock = new ReentrantLock();
    private final Condition mProtocolSocketNotNull = this.mProtocolSocketLock.newCondition();
    private final Lock mConnectionTypeLock = new ReentrantLock();
    private long lastAccountChangeEnqueued = TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS) - getAccountChangeConsolidationPeriod();
    private final Lock mSocketMetrics = new ReentrantLock();
    private final Object mAnalyzeLock = new Object();
    protected State mCurrentState = State.IDLE;
    private boolean mInitialized = false;

    /* renamed from: com.amazon.communication.watchdog.BetterWatchdog$15  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass15 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$watchdog$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$watchdog$State[State.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$watchdog$State[State.SUSPENDED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$communication$watchdog$State[State.IDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$communication$watchdog$State[State.CONNECT_NOW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$communication$watchdog$State[State.BACKOFF.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$communication$watchdog$State[State.DISCONNECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public class AnalyzeTask implements Runnable {
        private final SystemSnapshot mSnapshot;

        public AnalyzeTask(SystemSnapshot systemSnapshot) {
            this.mSnapshot = systemSnapshot;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
            if (this.mSnapshot.getTriggerId() == TriggerType.PROTOCOL_SOCKET_STATE_CHANGED && !this.mSnapshot.getSocketAcquisitionFailed()) {
                ProtocolSocket protocolSocket = this.mSnapshot.getProtocolSocket();
                if (protocolSocket != null) {
                    BetterWatchdog betterWatchdog = BetterWatchdog.this;
                    if (protocolSocket != betterWatchdog.mProtocolSocket) {
                        betterWatchdog.log.debug("AnalyzeTask", "getting a notification on a stale socket", new Object[0]);
                        return;
                    } else if (this.mSnapshot.getProtocolSocketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTED || this.mSnapshot.getProtocolSocketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTING) {
                        BetterWatchdog.this.stopWatchingCurrentSocket();
                        CloseDetail closeDetail = protocolSocket.closeDetail();
                        protocolSocket.closeReason();
                        int statusCode = closeDetail.getStatusCode();
                        if (BetterWatchdog.this.mSocketConnectedTime > 0) {
                            long elapsedRealtime = SystemClock.elapsedRealtime() - BetterWatchdog.this.mSocketConnectedTime;
                            if (statusCode == 4501 || statusCode == 4506 || elapsedRealtime > BetterWatchdog.getMinConnectionDurationMs()) {
                                BetterWatchdog.this.resetWaitCalculator();
                            }
                        }
                        BetterWatchdog.this.mSocketConnectedTime = 0L;
                    }
                } else {
                    throw new IllegalStateException("Socket must not be null");
                }
            }
            if (this.mSnapshot.getTriggerId() == TriggerType.STOP_WATCHING) {
                BetterWatchdog betterWatchdog2 = BetterWatchdog.this;
                if (betterWatchdog2.mCurrentState != State.SUSPENDED) {
                    betterWatchdog2.transitionTo(State.IDLE, this.mSnapshot);
                    return;
                }
            }
            if (this.mSnapshot.getTriggerId() == TriggerType.ENTERING_STR_MODE) {
                BetterWatchdog betterWatchdog3 = BetterWatchdog.this;
                if (betterWatchdog3.mCurrentState != State.SUSPENDED) {
                    int readSTRWakeUpSetting = betterWatchdog3.readSTRWakeUpSetting();
                    boolean z = readSTRWakeUpSetting == 0;
                    BetterWatchdog.this.log.info("AnalyzeTask", "Going to STR SUSPENDED", "shouldGoToSuspend", Boolean.valueOf(z), "str wake up setting", Integer.valueOf(readSTRWakeUpSetting));
                    if (z) {
                        BetterWatchdog.this.mTCommService.clearNosAlarms();
                        BetterWatchdog.this.transitionTo(State.SUSPENDED, this.mSnapshot);
                        return;
                    }
                    BetterWatchdog.this.log.info("AnalyzeTask", "Ignoring STR event, will keep working as normal.", new Object[0]);
                    return;
                }
            }
            State state = BetterWatchdog.this.mCurrentState;
            if (state != State.IDLE && state != State.DISCONNECTED && state != State.SUSPENDED && !this.mSnapshot.getIsConnectivityPossible()) {
                BetterWatchdog.this.transitionTo(State.DISCONNECTED, this.mSnapshot);
                return;
            }
            State state2 = BetterWatchdog.this.mCurrentState;
            if ((state2 == State.CONNECT_NOW || state2 == State.CONNECTED || state2 == State.BACKOFF) && (this.mSnapshot.getTriggerId() == TriggerType.UPDATE_AND_RECONNECT || this.mSnapshot.getTriggerId() == TriggerType.ACCOUNT_CHANGED)) {
                BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
                return;
            }
            int ordinal = BetterWatchdog.this.mCurrentState.ordinal();
            if (ordinal == 0) {
                if (this.mSnapshot.getTriggerId() != TriggerType.START_WATCHING) {
                    return;
                }
                if (this.mSnapshot.getIsConnectivityPossible()) {
                    BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
                } else {
                    BetterWatchdog.this.transitionTo(State.DISCONNECTED, this.mSnapshot);
                }
            } else if (ordinal == 1) {
                if (this.mSnapshot.getTriggerId() == TriggerType.PROTOCOL_SOCKET_STATE_CHANGED) {
                    if (this.mSnapshot.getProtocolSocketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
                        BetterWatchdog.this.transitionTo(State.CONNECTED, this.mSnapshot);
                    } else if (this.mSnapshot.getProtocolSocketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTED || this.mSnapshot.getProtocolSocketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTING) {
                        BetterWatchdog.this.transitionTo(State.BACKOFF, this.mSnapshot);
                    }
                }
                if (!this.mSnapshot.getSocketAcquisitionFailed()) {
                    return;
                }
                BetterWatchdog.this.transitionTo(State.BACKOFF, this.mSnapshot);
            } else if (ordinal == 2) {
                if (this.mSnapshot.getTriggerId() != TriggerType.PROTOCOL_SOCKET_STATE_CHANGED) {
                    return;
                }
                ProtocolSocket.ProtocolSocketState protocolSocketState = this.mSnapshot.getProtocolSocketState();
                if (protocolSocketState != ProtocolSocket.ProtocolSocketState.DISCONNECTED && protocolSocketState != ProtocolSocket.ProtocolSocketState.DISCONNECTING) {
                    return;
                }
                BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
            } else if (ordinal == 3) {
                if (!this.mSnapshot.getIsConnectivityPossible()) {
                    return;
                }
                BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
            } else if (ordinal != 4) {
                if (ordinal != 5) {
                    BetterWatchdog.this.log.error("AnalyzeTask", BetterWatchdog.this.addPurposeTag("Unhandled state"), "state", BetterWatchdog.this.mCurrentState.name());
                    throw new IllegalStateException("Unhandled state");
                } else if (this.mSnapshot.getTriggerId() != TriggerType.SCREEN_EVENT || this.mSnapshot.getScreenState() != ScreenEventListener.Event.ON) {
                } else {
                    BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
                }
            } else if (this.mSnapshot.getTriggerId() == TriggerType.SCREEN_EVENT) {
                if (this.mSnapshot.getScreenState() != ScreenEventListener.Event.ON) {
                    return;
                }
                BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
            } else if (this.mSnapshot.getTriggerId() == TriggerType.NETWORK_STABILITY_STATE_CHANGED) {
                if (this.mSnapshot.getNetworkStabilityState() != NetworkStabilityMonitor.NetworkStabilityState.STABLE) {
                    return;
                }
                BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
            } else if (this.mSnapshot.getTriggerId() == TriggerType.ACCOUNT_CHANGED) {
                BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
            } else if (this.mSnapshot.getTriggerId() == TriggerType.SMART_SUSPEND_EVENT) {
                if (this.mSnapshot.getSmartSuspendRadioOn() && this.mSnapshot.getSmartSuspendEnabled()) {
                    BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
                } else if (this.mSnapshot.getSmartSuspendRadioOn() || !this.mSnapshot.getSmartSuspendEnabled()) {
                } else {
                    BetterWatchdog.this.transitionTo(State.DISCONNECTED, this.mSnapshot);
                }
            } else if (this.mSnapshot.getTriggerId() != TriggerType.BACKOFF_TIME_REACHED) {
            } else {
                BetterWatchdog.this.transitionTo(State.CONNECT_NOW, this.mSnapshot);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class OpenSocketTask implements Runnable {
        private final ConnectReason mConnectReason;

        public OpenSocketTask(ConnectReason connectReason) {
            this.mConnectReason = connectReason;
        }

        @Override // java.lang.Runnable
        public void run() {
            IRServiceEndpoint iRServiceEndpoint;
            Purpose purpose;
            try {
                ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
                BetterWatchdog.this.logMetricsAndAcquireLocks();
                try {
                    try {
                        if (BetterWatchdog.this.mEndpoint instanceof ServiceIdentity) {
                            if (BetterWatchdog.this.mPolicy.getPurpose() != null) {
                                purpose = BetterWatchdog.this.mPolicy.getPurpose();
                            } else {
                                purpose = Purpose.REGULAR;
                            }
                            iRServiceEndpoint = BetterWatchdog.this.mIdentityResolver.resolveServiceEndpoint((ServiceIdentity) BetterWatchdog.this.mEndpoint, purpose);
                        } else {
                            iRServiceEndpoint = null;
                        }
                        IRServiceEndpoint iRServiceEndpoint2 = iRServiceEndpoint;
                        BetterWatchdog.this.mProtocolSocketLock.lock();
                        BetterWatchdog.this.mProtocolSocket = BetterWatchdog.this.mSocketDecisionEngine.acquireSocket(BetterWatchdog.this.mEndpoint, iRServiceEndpoint2, BetterWatchdog.this.mPolicy, this.mConnectReason, null);
                        BetterWatchdog.this.mProtocolSocketNotNull.signalAll();
                        BetterWatchdog.this.mProtocolSocketLock.unlock();
                        BetterWatchdog.this.log.debug("OpenSocketTask", BetterWatchdog.this.addPurposeTag("Socket aquired"), Settings.ListeningSettings.EXTRA_REASON, this.mConnectReason, "policy", BetterWatchdog.this.mPolicy, "endpoint", EndpointIdentity.logSafe(BetterWatchdog.this.mEndpoint));
                        BetterWatchdog.this.mProtocolSocket.getProtocolSocketStats();
                        BetterWatchdog.this.startWatchingCurrentSocket(BetterWatchdog.this.mProtocolSocket);
                        BetterWatchdog.this.notifyAllListeners(BetterWatchdog.this.mProtocolSocket);
                    } catch (SocketAcquisitionFailedException e) {
                        BetterWatchdog.this.log.warn("OpenSocketTask", BetterWatchdog.this.addPurposeTag("acquireProtocolSocket failed, will try again respecting backoff"), e);
                        BetterWatchdog.this.mTriggerSet.socketAcquisitionFailed(this.mConnectReason);
                    }
                } catch (Throwable th) {
                    BetterWatchdog.this.logMetricsAndReleaseLocks();
                    BetterWatchdog.this.log.warn("OpenSocketTask", BetterWatchdog.this.addPurposeTag("exception while acquiring socket"), Settings.ListeningSettings.EXTRA_REASON, this.mConnectReason, "policy", BetterWatchdog.this.mPolicy, "endpoint", EndpointIdentity.logSafe(BetterWatchdog.this.mEndpoint));
                    throw th;
                }
            } catch (Exception e2) {
                BetterWatchdog.this.log.error("OpenSocketTask", BetterWatchdog.this.addPurposeTag("Unhandled exception while acquiring socket, will backoff and try again"), e2);
                BetterWatchdog.this.mTriggerSet.socketAcquisitionFailed(this.mConnectReason);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class TriggerSet {
        public final ConnectivityChangedHandler mConnectivityChangedHandler = new ConnectivityChangedHandler() { // from class: com.amazon.communication.watchdog.BetterWatchdog.TriggerSet.1
            @Override // com.amazon.communication.ConnectivityChangedHandler
            public void onConnectivityChanged() {
                synchronized (BetterWatchdog.this.mAnalyzeLock) {
                    BetterWatchdog.this.log.debug("onConnectivityChanged", BetterWatchdog.this.addPurposeTag("connectivity changed"), "available", Boolean.valueOf(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()), "mobile", BetterWatchdog.this.mConnectivityMonitor.getNetworkState(0), "wi-fi", BetterWatchdog.this.mConnectivityMonitor.getNetworkState(1), "this", BetterWatchdog.this);
                    BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.CONNECTIVITY_CHANGED).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setConnectReason(new ConnectReason(ConnectReason.ReasonString.ConnectivityAvailable, BetterWatchdog.this.mWaitCalculator.getRetries() + 1)).build());
                }
            }
        };
        public final NetworkStabilityStateChangeListener mNetworkStabilityChangeListener = new NetworkStabilityStateChangeListener() { // from class: com.amazon.communication.watchdog.BetterWatchdog.TriggerSet.2
            @Override // com.amazon.communication.NetworkStabilityStateChangeListener
            public void onStateChanged(NetworkStabilityMonitor.NetworkStabilityState networkStabilityState) {
                synchronized (BetterWatchdog.this.mAnalyzeLock) {
                    BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.NETWORK_STABILITY_STATE_CHANGED).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setNetworkStabilityState(networkStabilityState).setConnectReason(new ConnectReason(ConnectReason.ReasonString.ConnectivityStabilized, 1)).build());
                }
            }
        };
        public final ScreenEventListener mScreenEventListener = new ScreenEventListener() { // from class: com.amazon.communication.watchdog.BetterWatchdog.TriggerSet.3
            @Override // com.amazon.communication.ScreenEventListener
            public void onScreenEvent(ScreenEventListener.Event event) {
                synchronized (BetterWatchdog.this.mAnalyzeLock) {
                    BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.SCREEN_EVENT).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setScreenState(event).setConnectReason(new ConnectReason(ConnectReason.ReasonString.ScreenEvent, 1)).build());
                }
            }
        };
        public final BroadcastReceiver mSmartSuspendListener = new BroadcastReceiver() { // from class: com.amazon.communication.watchdog.BetterWatchdog.TriggerSet.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                synchronized (BetterWatchdog.this.mAnalyzeLock) {
                    BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.SMART_SUSPEND_EVENT).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setSmartSuspendRadioOn(BetterWatchdog.SMART_SUSPEND_RADIO_ON.equals(intent == null ? null : intent.getAction())).build());
                }
            }
        };
        public final SettingUpdateListener mSettingsCacheListener = new SettingUpdateListener() { // from class: com.amazon.communication.watchdog.BetterWatchdog.TriggerSet.5
            @Override // com.amazon.communication.remotesetting.SettingUpdateListener
            public void onSettingUpdated() {
                BetterWatchdog.this.mSmartSuspendEnabled = BetterWatchdog.isSmartSuspendEnabled();
                BetterWatchdog.this.log.info("onCacheUpdated", BetterWatchdog.this.addPurposeTag("Update SmartSuspendEnabled"), "to", Boolean.valueOf(BetterWatchdog.this.mSmartSuspendEnabled));
            }
        };
        public final BroadcastReceiver mAccountChangeListener = new BroadcastReceiver() { // from class: com.amazon.communication.watchdog.BetterWatchdog.TriggerSet.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                synchronized (BetterWatchdog.this.mAnalyzeLock) {
                    BetterWatchdog.this.mLatestAccountChangeSnapshot = new SystemSnapshot.Builder().setTriggerId(TriggerType.ACCOUNT_CHANGED).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setConnectReason(new ConnectReason(ConnectReason.ReasonString.AccountChange, 1)).build();
                    BetterWatchdog.this.log.debug("AccountChangeListener", BetterWatchdog.this.addPurposeTag("Trigger fired"), "account change snapshot updated", BetterWatchdog.this.mLatestAccountChangeSnapshot, "watchdog", BetterWatchdog.this);
                    long convert = TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
                    if (convert - BetterWatchdog.this.lastAccountChangeEnqueued <= BetterWatchdog.getAccountChangeConsolidationPeriod()) {
                        if (BetterWatchdog.this.futureSnapshotEnqueueJob == null || BetterWatchdog.this.futureSnapshotEnqueueJob.isDone()) {
                            BetterWatchdog.this.futureSnapshotEnqueueJob = BetterWatchdog.this.enqueueAccountChangeSnapshotLocked();
                        }
                    } else {
                        BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(BetterWatchdog.this.mLatestAccountChangeSnapshot);
                        BetterWatchdog.this.lastAccountChangeEnqueued = convert;
                    }
                }
            }
        };
        public final BroadcastReceiver mStrListenerReceiver = new BroadcastReceiver() { // from class: com.amazon.communication.watchdog.BetterWatchdog.TriggerSet.7
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                synchronized (BetterWatchdog.this.mAnalyzeLock) {
                    BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.ENTERING_STR_MODE).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setConnectReason(new ConnectReason(ConnectReason.ReasonString.AccountChange, 1)).build());
                }
            }
        };

        protected TriggerSet() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateWatchdogAndReconnect(Policy policy, EndpointIdentity endpointIdentity) {
            synchronized (BetterWatchdog.this.mAnalyzeLock) {
                BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.UPDATE_AND_RECONNECT).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setConnectReason(new ConnectReason(ConnectReason.ReasonString.PolicyChange, 1)).setPolicy(policy).setEndpoint(endpointIdentity).build());
            }
        }

        public void backOffTimeReached(ConnectReason connectReason) {
            synchronized (BetterWatchdog.this.mAnalyzeLock) {
                BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.BACKOFF_TIME_REACHED).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setConnectReason(connectReason).build());
            }
        }

        public void notifyStateChanged(ProtocolSocket protocolSocket) {
            synchronized (BetterWatchdog.this.mAnalyzeLock) {
                BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.PROTOCOL_SOCKET_STATE_CHANGED).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setProtocolSocketState(protocolSocket.socketState()).setConnectReason(BetterWatchdog.this.determineConnectReason(protocolSocket)).setConnectionThrottled(BetterWatchdog.this.isConnectionThrottled(protocolSocket)).setProtocolSocket(protocolSocket).build());
            }
        }

        public void socketAcquisitionFailed(ConnectReason connectReason) {
            synchronized (BetterWatchdog.this.mAnalyzeLock) {
                BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.PROTOCOL_SOCKET_STATE_CHANGED).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setSocketAcquisitionFailed(true).setConnectReason(new ConnectReason(ConnectReason.ReasonString.ConnectionFailed, connectReason.getAttempt())).build());
            }
        }

        public void startWatching(ConnectReason.ReasonString reasonString) {
            synchronized (BetterWatchdog.this.mAnalyzeLock) {
                BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.START_WATCHING).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).setConnectReason(new ConnectReason(reasonString, 1)).build());
            }
        }

        public void stopWatching() {
            synchronized (BetterWatchdog.this.mAnalyzeLock) {
                BetterWatchdog.this.enqueueAnalyzeSnapshotLocked(new SystemSnapshot.Builder().setTriggerId(TriggerType.STOP_WATCHING).setIsConnectivityPossible(BetterWatchdog.this.mConnectivityMonitor.isConnectivityPossible()).setSmartSuspendEnabled(BetterWatchdog.this.mSmartSuspendEnabled).build());
            }
        }
    }

    public BetterWatchdog(String str, EndpointIdentity endpointIdentity, Policy policy) {
        if (str != null && endpointIdentity != null && policy != null) {
            this.mTriggerSet = new TriggerSet();
            this.mEndpoint = EndpointIdentityFactory.createFromUrn(endpointIdentity.toString());
            this.mUrl = str;
            this.mPolicy = policy;
            EndpointIdentity endpointIdentity2 = this.mEndpoint;
            this.mServiceName = endpointIdentity2 instanceof ServiceIdentity ? ((ServiceIdentity) endpointIdentity2).getServiceName() : "";
            this.log.info(METRICS_SOURCE_NAME, addPurposeTag("Create new watchdog with"), "url", str, "endpoint", endpointIdentity, "policy", policy);
            initMetricGauges();
            return;
        }
        throw new IllegalArgumentException(String.format("Arguments must not be null (url:%s;endpoint:%s;policy:%s)", str, endpointIdentity, policy));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String addPurposeTag(String str) {
        return String.format("%s(Purpose:%s)", str, this.mPolicy.getPurpose().getPurpose());
    }

    private void checkIfInitialized() {
        if (this.mInitialized) {
            return;
        }
        throw new IllegalStateException("Better watchdog not initialized");
    }

    private void enterBackOff(SystemSnapshot systemSnapshot) {
        long max = Math.max(this.mWaitCalculator.getWaitMs() + (systemSnapshot.getConnectionThrottled() ? getDelayForThrottledConnections() : 0L), this.mBackoffScheduler.getMinimumDelayMillis());
        this.mBackoffIntervalGauge.setValue(Long.valueOf(max));
        try {
            ConnectReason nextAttempt = ConnectReason.nextAttempt(systemSnapshot.getConnectReason());
            this.log.debug("enterBackOff", addPurposeTag("enqueue backOff task"), "connectionThrottled", Boolean.valueOf(systemSnapshot.getConnectionThrottled()), "attempt", Integer.valueOf(nextAttempt.getAttempt()), "waitTimeMs", Long.valueOf(max));
            if (nextAttempt.getAttempt() != this.mWaitCalculator.getRetries() + 1) {
                this.log.warn("enterBackOff", addPurposeTag("connectReason.attempt != (waitCalculator.retries + 1)"), "connectReason.attempt", Integer.valueOf(nextAttempt.getAttempt()), "waitCalculator.retries", Integer.valueOf(this.mWaitCalculator.getRetries() + 1));
            }
            enqueueBackOffTask(nextAttempt, max);
        } catch (RuntimeException e) {
            this.log.error("enqueueBackOffTask", addPurposeTag("exception occurred scheduling a BackOffTask"), e);
            throw e;
        }
    }

    private void enterConnected(SystemSnapshot systemSnapshot) {
        this.mSocketConnectedTime = SystemClock.elapsedRealtime();
        this.mConnectionHealthManager.resume(this.mEndpoint);
    }

    private void enterDisconnected(SystemSnapshot systemSnapshot) {
        if (systemSnapshot.getTriggerId() == TriggerType.SMART_SUSPEND_EVENT && !systemSnapshot.getSmartSuspendRadioOn()) {
            this.mBackoffScheduler.cancel(this.mServiceName.hashCode());
            resetWaitCalculator();
        }
        this.mSocketConnectedTime = -1L;
        enqueueCloseSocket(new CloseDetail(CloseStatusCodes.CONNECTIVITY_CHANGE, "No connectivity available"));
    }

    private void enterIdle(SystemSnapshot systemSnapshot) {
        if (systemSnapshot.getTriggerId() == TriggerType.STOP_WATCHING) {
            stopWatchingCurrentSocket();
            enqueueCloseSocket(new CloseDetail(CloseStatusCodes.WATCHDOG_STOP_WATCHING, "stopWatching called"));
            return;
        }
        throw new IllegalStateException("Enter IDLE state with an invalid trigger.");
    }

    private void enterState(SystemSnapshot systemSnapshot) {
        int ordinal = this.mCurrentState.ordinal();
        if (ordinal == 0) {
            enterIdle(systemSnapshot);
        } else if (ordinal == 1) {
            enterConnectNow(systemSnapshot);
        } else if (ordinal == 2) {
            enterConnected(systemSnapshot);
        } else if (ordinal == 3) {
            enterDisconnected(systemSnapshot);
        } else if (ordinal == 4) {
            enterBackOff(systemSnapshot);
        } else if (ordinal == 5) {
            enterSuspended(systemSnapshot);
        } else {
            throw new IllegalStateException("Unhandled state");
        }
    }

    private void enterSuspended(SystemSnapshot systemSnapshot) {
        this.mNetworkStabilityMonitor.stop();
        ProbingConnectionLifetimeManager probingConnectionLifetimeManager = this.mProbingConnectionLifetimeManager;
        if (probingConnectionLifetimeManager != null) {
            probingConnectionLifetimeManager.suspendProbing();
        }
        this.mBackoffScheduler.cancel(this.mServiceName.hashCode());
        resetWaitCalculator();
        this.mSocketConnectedTime = -1L;
        enqueueCloseSocket(new CloseDetail(CloseStatusCodes.ENTERING_SUSPENDED_STATE, "Entering suspend state"));
    }

    private void exitConnected(SystemSnapshot systemSnapshot) {
        this.mConnectionHealthManager.pause(this.mEndpoint);
    }

    private void exitState(SystemSnapshot systemSnapshot) {
        int ordinal = this.mCurrentState.ordinal();
        if (ordinal == 2) {
            exitConnected(systemSnapshot);
        } else if (ordinal != 5) {
        } else {
            exitSuspended(systemSnapshot);
        }
    }

    private void exitSuspended(SystemSnapshot systemSnapshot) {
        this.mNetworkStabilityMonitor.start();
        ProbingConnectionLifetimeManager probingConnectionLifetimeManager = this.mProbingConnectionLifetimeManager;
        if (probingConnectionLifetimeManager != null) {
            probingConnectionLifetimeManager.restartProbing();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getAccountChangeConsolidationPeriod() {
        return RemoteSettingManager.getOptLongValue(CONSOLIDATION_PERIOD_IN_MS_KEY, 2000L).longValue();
    }

    private static int getBackoffCoefficient() {
        return RemoteSettingManager.getOptIntValue(BACKOFF_COEFFICIENT_KEY, 60000).intValue();
    }

    private static int getJitterAsFractionOfBackoffInterval() {
        return RemoteSettingManager.getOptIntValue(JITTER_AS_FRACTION_OF_BACKOFF_INTERVAL_KEY, 30).intValue();
    }

    private static long getMaxBackoffIntervalMs() {
        return RemoteSettingManager.getOptLongValue(MAX_BACKOFF_INTERVAL_MS_KEY, 1800000L).longValue();
    }

    private static long getMinBackoffIntervalMs() {
        return RemoteSettingManager.getOptLongValue(MIN_BACKOFF_INTERVAL_MS_KEY, 10000L).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getMinConnectionDurationMs() {
        return RemoteSettingManager.getOptIntValue(MIN_CONNECTION_DURATION_MS_KEY, Integer.valueOf((int) MIN_CONNECTION_DURATION_MS_DEFAULT)).intValue();
    }

    private static long getStaticBackoffIntervalMs() {
        return RemoteSettingManager.getOptLongValue(STATIC_BACKOFF_INTERVAL_MS_KEY, 5000L).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSmartSuspendEnabled() {
        return RemoteSettingManager.getOptBooleanValue(SMART_SUSPEND_ENABLED_KEY, true).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logMetricsAndAcquireLocks() {
        this.mConnectionTypeLock.lock();
        try {
            if (this.mConnectivityMonitor.isEthernetAvailable()) {
                this.mConnectionType = 9;
            } else if (this.mConnectivityMonitor.isWiFiAvailable()) {
                this.mConnectionType = 1;
            } else if (this.mConnectivityMonitor.isMobileAvailable()) {
                this.mConnectionType = 0;
            } else if (this.mConnectivityMonitor.isConnectivityPossible()) {
                this.mConnectionType = TYPE_UNKNOWN;
                this.log.warn("logMetricsAndAcquireLocks", addPurposeTag("connectivity possible but unknown network type"), "isEthernetAvailable", Boolean.valueOf(this.mConnectivityMonitor.isEthernetAvailable()), "isWiFiAvailable", Boolean.valueOf(this.mConnectivityMonitor.isWiFiAvailable()), "isMobileAvailable", Boolean.valueOf(this.mConnectivityMonitor.isMobileAvailable()));
            }
            if (this.mConnectionType != null) {
                (this.mConnectionType.intValue() == 1 ? this.mWifiNetworkLock : this.mNonWifiNetworkLock).acquire();
            }
        } finally {
            this.mConnectionTypeLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logMetricsAndReleaseLocks() {
        this.mConnectionTypeLock.lock();
        try {
            if (this.mSocketConnectedTime != -1) {
                this.log.verbose("logMetricsAndReleaseLocks", addPurposeTag("recording connection duration"), "connectionType", this.mConnectionType, "time", Long.valueOf(SystemClock.elapsedRealtime() - this.mSocketConnectedTime));
                if (this.mConnectionType != null) {
                    (this.mConnectionType.intValue() == 1 ? this.mWifiNetworkLock : this.mNonWifiNetworkLock).release();
                    this.mConnectionType = null;
                }
            }
        } finally {
            this.mConnectionTypeLock.unlock();
        }
    }

    private ExponentialBackoffWaitCalculator newExponentialWaitCalculator() {
        return new ExponentialBackoffWaitCalculator(getMinBackoffIntervalMs(), getMaxBackoffIntervalMs(), getBackoffCoefficient(), getJitterAsFractionOfBackoffInterval() / 100.0d);
    }

    private ExponentialBackoffWaitCalculator newStaticWaitCalculator() {
        return new ExponentialBackoffWaitCalculator(getStaticBackoffIntervalMs(), getStaticBackoffIntervalMs(), 0, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    private void setupHealthManager() {
        if (this.mPolicy.getKeepAlive() != KeepAlive.NONE) {
            HeartbeatIntervalDeterminer heartbeatIntervalDeterminer = this.mHeartbeatIntervalDeterminer;
            if (heartbeatIntervalDeterminer != null) {
                this.mConnectionHealthManager.maintainConnection(this.mEndpoint, heartbeatIntervalDeterminer, this.mConnectionHealthStatisticsAggregator);
                return;
            }
            throw new IllegalStateException("mHeartbeatIntervalDeterminer cannot be null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWatchingCurrentSocket(ProtocolSocket protocolSocket) {
        addAllListenersToSocket(protocolSocket);
        protocolSocket.retain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopWatchingCurrentSocket() {
        ProtocolSocket protocolSocket = this.mProtocolSocket;
        if (protocolSocket == null) {
            return;
        }
        try {
            try {
                protocolSocket.release();
                this.mProtocolSocket.removeStateListener(this);
                removeGatewayConnectivityListener(this.mProtocolSocket);
            } catch (Exception e) {
                this.log.error(addPurposeTag("Error in stopWatchingCurrentSocket"), e.getMessage(), new Object[0]);
            }
        } finally {
            logMetricsAndReleaseLocks();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void transitionTo(State state, SystemSnapshot systemSnapshot) {
        this.log.info("transitionTo", addPurposeTag("Watchdog transition"), ADMConstants.EXTRA_FROM, this.mCurrentState.name(), "to", state.name());
        exitState(systemSnapshot);
        this.mCurrentState = state;
        enterState(systemSnapshot);
    }

    protected void addAllListenersToSocket(final ProtocolSocket protocolSocket) {
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.11
            @Override // java.lang.Runnable
            public void run() {
                for (ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener : BetterWatchdog.this.mSocketStateListeners) {
                    protocolSocket.addStateListener(protocolSocketStateListener);
                }
            }
        });
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void addSocketStateListener(final ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener) {
        checkIfInitialized();
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.8
            @Override // java.lang.Runnable
            public void run() {
                BetterWatchdog.this.mSocketStateListeners.add(protocolSocketStateListener);
                ProtocolSocket protocolSocket = BetterWatchdog.this.mProtocolSocket;
                if (protocolSocket != null) {
                    protocolSocket.addStateListener(protocolSocketStateListener);
                }
            }
        });
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void addWatchdogStateListener(final AlwaysOnSocketWatchdog.WatchdogStateListener watchdogStateListener) {
        checkIfInitialized();
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.13
            @Override // java.lang.Runnable
            public void run() {
                BetterWatchdog.this.mWatchdogStateListeners.add(watchdogStateListener);
            }
        });
    }

    protected ConnectReason determineConnectReason(ProtocolSocket protocolSocket) {
        ProtocolSocket.ProtocolSocketState socketState = protocolSocket.socketState();
        if (socketState != ProtocolSocket.ProtocolSocketState.CONNECTING && socketState != ProtocolSocket.ProtocolSocketState.CONNECTED) {
            CloseDetail closeDetail = protocolSocket.closeDetail();
            CloseReason closeReason = protocolSocket.closeReason();
            int statusCode = closeDetail.getStatusCode();
            ConnectReason connectReason = protocolSocket.getConnectReason();
            if (this.mSocketConnectedTime <= 0) {
                return connectReason;
            }
            if (closeReason != CloseReason.CLOSE_CALLER) {
                if (closeReason != CloseReason.CLOSE_COMMAND) {
                    return new ConnectReason(ConnectReason.ReasonString.ConnectionFailed, 1);
                }
                if (statusCode != 1000 && statusCode != 4010) {
                    this.log.warn("determineConnectReason", addPurposeTag("unexpected close detail for CLOSE_COMMAND reason"), "closeDetail", closeDetail);
                    return new ConnectReason(ConnectReason.ReasonString.ConnectionFailed, 1);
                }
                return new ConnectReason(ConnectReason.ReasonString.ConnectionClosed, 1);
            } else if (statusCode == 4500) {
                return new ConnectReason(ConnectReason.ReasonString.PreferredInterfaceAvailable, 1);
            } else {
                if (statusCode == 4501) {
                    return new ConnectReason(ConnectReason.ReasonString.AccountChange, 1);
                }
                if (statusCode == 4502) {
                    return new ConnectReason(ConnectReason.ReasonString.HeartbeatFailure, 1);
                }
                if (statusCode == 4506) {
                    return new ConnectReason(ConnectReason.ReasonString.PolicyChange, 1);
                }
                this.log.warn("determineConnectReason", addPurposeTag("unexpected close detail for CLOSE_CALLER reason"), "closeDetail", closeDetail);
                return new ConnectReason(ConnectReason.ReasonString.ConnectionFailed, 1);
            }
        }
        return protocolSocket.getConnectReason();
    }

    protected Future<?> enqueueAccountChangeSnapshotLocked() {
        this.log.info("enqueueAccountChangeSnapshotLocked", addPurposeTag("Trigger fired"), "snapshot", this.mLatestAccountChangeSnapshot, "delay in ms", Long.valueOf(getAccountChangeConsolidationPeriod()), "watchdog", this);
        return this.mExecutor.schedule(new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.7
            @Override // java.lang.Runnable
            public void run() {
                BetterWatchdog betterWatchdog = BetterWatchdog.this;
                betterWatchdog.mExecutor.submit(new AnalyzeTask(betterWatchdog.mLatestAccountChangeSnapshot));
            }
        }, getAccountChangeConsolidationPeriod(), TimeUnit.MILLISECONDS);
    }

    protected void enqueueAnalyzeSnapshotLocked(SystemSnapshot systemSnapshot) {
        this.log.info("enqueueAnalyzeSnapshotLocked", addPurposeTag("Trigger fired"), "snapshot", systemSnapshot, "watchdog", this);
        this.mExecutor.submit(new AnalyzeTask(systemSnapshot));
    }

    protected void enqueueBackOffTask(final ConnectReason connectReason, long j) {
        this.mBackoffScheduler.schedule(this.mServiceName.hashCode(), new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.6
            @Override // java.lang.Runnable
            public void run() {
                BetterWatchdog.this.mTriggerSet.backOffTimeReached(connectReason);
            }
        }, j, TimeUnit.MILLISECONDS);
    }

    protected void enqueueCloseSocket(CloseDetail closeDetail) {
        this.mExecutor.submit(new CloseSocketTask(closeDetail));
    }

    protected void enqueueOpenSocket(ConnectReason connectReason) {
        this.mExecutor.submit(new OpenSocketTask(connectReason));
    }

    @VisibleForTesting
    void enterConnectNow(SystemSnapshot systemSnapshot) {
        if (systemSnapshot.getTriggerId() == TriggerType.UPDATE_AND_RECONNECT) {
            this.mPolicy = systemSnapshot.getPolicy();
            EndpointIdentity endpointIdentity = this.mEndpoint;
            this.mEndpoint = EndpointIdentityFactory.createFromUrn(systemSnapshot.getEndpoint().toString());
            this.mConnectionHealthManager.stop(endpointIdentity);
            setupHealthManager();
            if (this.mProtocolSocket != null) {
                enqueueCloseSocket(new CloseDetail(CloseStatusCodes.POLICY_CHANGE, "Policy updated"));
            }
        } else if (systemSnapshot.getTriggerId() == TriggerType.ACCOUNT_CHANGED) {
            if (this.mProtocolSocket != null) {
                this.log.info("enterConnectNow", addPurposeTag("Change in amazon accounts set detected, tearing down connection."), new Object[0]);
                resetWaitCalculator();
                enqueueCloseSocket(new CloseDetail(CloseStatusCodes.ACCOUNT_CHANGE, "Amazon Account status change"), true);
            }
        } else if (systemSnapshot.getTriggerId() == TriggerType.SCREEN_EVENT || systemSnapshot.getTriggerId() == TriggerType.NETWORK_STABILITY_STATE_CHANGED) {
            resetWaitCalculator();
        }
        enqueueOpenSocket(systemSnapshot.getConnectReason());
    }

    protected long getDelayForThrottledConnections() {
        return RemoteSettingManager.getOptLongValue(DELAY_AFTER_THROTTLED_CONNECTION_MS_KEY, 60000L).longValue();
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public ProtocolSocket getNonNullProtocolSocket(int i, TimeUnit timeUnit) throws AlwaysOnSocketWatchdog.ProtocolSocketAcquisitionTimeout {
        ProtocolSocket protocolSocket;
        checkIfInitialized();
        this.mProtocolSocketLock.lock();
        try {
            if (this.mProtocolSocket != null) {
                protocolSocket = this.mProtocolSocket;
            } else {
                try {
                    if (this.mProtocolSocketNotNull.await(i, timeUnit)) {
                        protocolSocket = this.mProtocolSocket;
                    } else {
                        throw new AlwaysOnSocketWatchdog.ProtocolSocketAcquisitionTimeout(String.format("Failed to get a non-null protocol socket after %d %s", Integer.valueOf(i), timeUnit));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AlwaysOnSocketWatchdog.ProtocolSocketAcquisitionTimeout("Receiving InterruptedException while waiting to get protocol socket", e);
                }
            }
            return protocolSocket;
        } finally {
            this.mProtocolSocketLock.unlock();
        }
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public Policy getPolicy() {
        return this.mPolicy;
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public ProtocolSocket getProtocolSocket() {
        checkIfInitialized();
        return this.mProtocolSocket;
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public EndpointIdentity getWatchedEndpoint() {
        checkIfInitialized();
        return this.mEndpoint;
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public String getWatchedUrl() {
        checkIfInitialized();
        return this.mUrl;
    }

    protected void initMetricGauges() {
        MetricRegistry orCreate = SharedMetricRegistries.getOrCreate(RouteName.MAIN);
        String outline72 = GeneratedOutlineSupport1.outline72("com.amazon.tcomm.", this.mPolicy.getPurpose().toString().toLowerCase());
        this.mBackoffIntervalGauge = (ValueGauge) orCreate.metric(GeneratedOutlineSupport1.outline72(outline72, ".backoff.interval_ms"), ValueGauge.LONG);
        orCreate.replace(GeneratedOutlineSupport1.outline72(outline72, ".backoff.active"), new Gauge<Boolean>() { // from class: com.amazon.communication.watchdog.BetterWatchdog.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.codahale.metrics.Gauge
            /* renamed from: getValue */
            public Boolean mo6796getValue() {
                return Boolean.valueOf(BetterWatchdog.this.mCurrentState == State.BACKOFF);
            }
        });
        this.mSocketStateGauge = (ValueGauge) orCreate.metric(outline72 + ".socket.state", ValueGauge.STRING);
        this.mSocketStateSinceGauge = (TimeSinceGauge) orCreate.metric(GeneratedOutlineSupport1.outline72(outline72, ".socket.in_state_ms"), TimeSinceGauge.BUILDER);
        this.mSocketFqdnGauge = (ValueGauge) orCreate.metric(GeneratedOutlineSupport1.outline72(outline72, ".socket.fqdn"), ValueGauge.STRING);
        orCreate.replace(GeneratedOutlineSupport1.outline72(outline72, ".watchdog.current_state"), new Gauge<String>() { // from class: com.amazon.communication.watchdog.BetterWatchdog.4
            @Override // com.codahale.metrics.Gauge
            /* renamed from: getValue  reason: collision with other method in class */
            public String mo6796getValue() {
                return BetterWatchdog.this.mCurrentState.name();
            }
        });
    }

    public void initialize() {
        if (!this.mInitialized) {
            initializeObjects();
            this.mInitialized = true;
            return;
        }
        throw new IllegalStateException("BetterWatchdog already initialized.");
    }

    protected void initializeObjects() {
        this.log.info("initializeObjects", "initializing", "watchdog", this);
        String str = "TComm." + this.mServiceName + GlobalTimeSource.INSTANCE.currentTimeMillis();
        this.mWifiNetworkLock = this.mWifiManager.createWifiLock(str);
        this.mNonWifiNetworkLock = this.mWifiManager.createWifiLock(WifiManagerWrapper.WifiLockType.SCAN_ONLY, str);
        this.mSmartSuspendEnabled = isSmartSuspendEnabled();
        this.mMaxTCommAvailability = this.mContext.getPackageManager().hasSystemFeature("com.amazon.tcomm.max_availability");
        if (this.mMaxTCommAvailability) {
            this.mWaitCalculator = newStaticWaitCalculator();
        } else {
            this.mWaitCalculator = newExponentialWaitCalculator();
        }
        this.mWatchdogStateListeners = new LinkedHashSet();
        this.mConnectivityMonitor.registerConnectivityChangedHandler(this.mTriggerSet.mConnectivityChangedHandler);
        this.mScreenEventMonitor.registerScreenEventListener(this.mTriggerSet.mScreenEventListener);
        this.mNetworkStabilityMonitor.addListener(this.mTriggerSet.mNetworkStabilityChangeListener);
        this.mContext.registerReceiver(this.mTriggerSet.mSmartSuspendListener, SMART_SUSPEND_FILTER);
        this.mContext.registerReceiver(this.mTriggerSet.mAccountChangeListener, TCommService.ACCOUNT_CHANGE_INTENT_FILTER);
        this.mContext.registerReceiver(this.mTriggerSet.mStrListenerReceiver, ENTERING_STR_FILTER);
        RemoteSettingManager.addSettingUpdateListener(this.mTriggerSet.mSettingsCacheListener);
        this.mSocketStateListeners = new LinkedHashSet<ProtocolSocket.ProtocolSocketStateListener>() { // from class: com.amazon.communication.watchdog.BetterWatchdog.5
            {
                add(BetterWatchdog.this);
            }
        };
    }

    protected boolean isConnectionThrottled(ProtocolSocket protocolSocket) {
        CloseDetail closeDetail = protocolSocket.closeDetail();
        return closeDetail != null && closeDetail.getStatusCode() == 4014;
    }

    protected void notifyAllListeners(final ProtocolSocket protocolSocket) {
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.10
            @Override // java.lang.Runnable
            public void run() {
                for (ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener : BetterWatchdog.this.mSocketStateListeners) {
                    protocolSocketStateListener.notifyStateChanged(protocolSocket);
                }
            }
        });
    }

    @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        if (protocolSocket == this.mProtocolSocket) {
            this.mSocketStateSinceGauge.update();
            this.mSocketFqdnGauge.setValue(protocolSocket.getFqdn());
            this.mSocketStateGauge.setValue(protocolSocket.socketState().name());
        }
        this.mTriggerSet.notifyStateChanged(protocolSocket);
    }

    protected int readSTRWakeUpSetting() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), TCommService.STR_WAKE_UP_ENABLED_KEY, 0);
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void release() {
    }

    protected void removeGatewayConnectivityListener(final ProtocolSocket protocolSocket) {
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.12
            @Override // java.lang.Runnable
            public void run() {
                BetterWatchdog.this.log.info("removeGatewayConnectivityListener", "removing GatewayConnectivityListener from socket", "socket", protocolSocket);
                for (ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener : BetterWatchdog.this.mSocketStateListeners) {
                    if (protocolSocketStateListener instanceof GatewayConnectivityListener) {
                        protocolSocket.removeStateListener(protocolSocketStateListener);
                        BetterWatchdog.this.log.info("removeGatewayConnectivityListener", "Listener from socket removed", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, protocolSocketStateListener, "socket", protocolSocket);
                    }
                }
            }
        });
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void removeSocketStateListener(final ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener) {
        checkIfInitialized();
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.9
            @Override // java.lang.Runnable
            public void run() {
                BetterWatchdog.this.mSocketStateListeners.remove(protocolSocketStateListener);
            }
        });
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void removeWatchdogStateListener(final AlwaysOnSocketWatchdog.WatchdogStateListener watchdogStateListener) {
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.watchdog.BetterWatchdog.14
            @Override // java.lang.Runnable
            public void run() {
                BetterWatchdog.this.mWatchdogStateListeners.remove(watchdogStateListener);
            }
        });
    }

    protected void resetWaitCalculator() {
        this.log.info("resetWaitCalculator", addPurposeTag("Reset wait calculator"), new Object[0]);
        if (this.mMaxTCommAvailability) {
            this.mWaitCalculator.reset(getStaticBackoffIntervalMs(), getStaticBackoffIntervalMs(), 0, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        } else {
            this.mWaitCalculator.reset(getMinBackoffIntervalMs(), getMaxBackoffIntervalMs(), getBackoffCoefficient(), getJitterAsFractionOfBackoffInterval() / 100.0d);
        }
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public int retain() {
        return 1;
    }

    public void setAlwaysOnSocketWatchdogManager(AlwaysOnSocketWatchdogManager alwaysOnSocketWatchdogManager) {
        this.mWatchdogManager = alwaysOnSocketWatchdogManager;
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

    public void setExecutor(WakeLockHoldingScheduledThreadPoolExecutor wakeLockHoldingScheduledThreadPoolExecutor) {
        if (wakeLockHoldingScheduledThreadPoolExecutor.getCorePoolSize() == 1) {
            this.mExecutor = wakeLockHoldingScheduledThreadPoolExecutor;
            return;
        }
        throw new IllegalStateException("There cannot be more than one thread");
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void setHeartbeatIntervalDeterminer(HeartbeatIntervalDeterminer heartbeatIntervalDeterminer) {
        this.mHeartbeatIntervalDeterminer = heartbeatIntervalDeterminer;
    }

    public void setIdentityResolver(IdentityResolver identityResolver) {
        this.mIdentityResolver = identityResolver;
    }

    public void setNetworkStabilityMonitor(NetworkStabilityMonitor networkStabilityMonitor) {
        this.mNetworkStabilityMonitor = networkStabilityMonitor;
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void setProbingConnectionLifetimeManager(ProbingConnectionLifetimeManager probingConnectionLifetimeManager) {
        this.mProbingConnectionLifetimeManager = probingConnectionLifetimeManager;
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

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void shutdown() {
        this.mConnectivityMonitor.deregisterConnectivityChangedHandler(this.mTriggerSet.mConnectivityChangedHandler);
        this.mScreenEventMonitor.deregisterScreenEventListener(this.mTriggerSet.mScreenEventListener);
        this.mNetworkStabilityMonitor.removeListener(this.mTriggerSet.mNetworkStabilityChangeListener);
        this.mContext.unregisterReceiver(this.mTriggerSet.mAccountChangeListener);
        this.mContext.unregisterReceiver(this.mTriggerSet.mSmartSuspendListener);
        this.mContext.unregisterReceiver(this.mTriggerSet.mStrListenerReceiver);
        ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
        this.mConnectionHealthManager.stop(this.mEndpoint);
        this.mWatchdogManager.removeWatchdog(this);
        ProbingConnectionLifetimeManager probingConnectionLifetimeManager = this.mProbingConnectionLifetimeManager;
        if (probingConnectionLifetimeManager != null) {
            probingConnectionLifetimeManager.shutdown();
        }
        RemoteSettingManager.removeSettingUpdateListener(this.mTriggerSet.mSettingsCacheListener);
        this.log.debug("shutdown", "Shutting down", "watchdog", this);
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void startWatching(ConnectReason.ReasonString reasonString) {
        checkIfInitialized();
        setupHealthManager();
        this.mTriggerSet.startWatching(reasonString);
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void stopWatching() {
        checkIfInitialized();
        this.mTriggerSet.stopWatching();
    }

    public String toString() {
        return String.format("BetterWatchdog-%s:%s(socket:%s)", EndpointIdentity.logSafe(this.mEndpoint), this.mPolicy.getPurpose(), this.mProtocolSocket);
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog
    public void updateWatchdogAndReconnect(Policy policy, EndpointIdentity endpointIdentity) {
        checkIfInitialized();
        this.mTriggerSet.updateWatchdogAndReconnect(policy, endpointIdentity);
    }

    protected void enqueueCloseSocket(CloseDetail closeDetail, boolean z) {
        this.mExecutor.submit(new CloseSocketTask(closeDetail, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class CloseSocketTask implements Runnable {
        private final CloseDetail mDetail;
        private boolean mRemoveListeners;

        public CloseSocketTask(CloseDetail closeDetail) {
            this.mRemoveListeners = false;
            this.mDetail = closeDetail;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadGuard.ensureThreadPrefix(ThreadName.WATCHDOG);
            BetterWatchdog betterWatchdog = BetterWatchdog.this;
            if (betterWatchdog.mProtocolSocket != null) {
                betterWatchdog.log.debug("CloseSocketTask", BetterWatchdog.this.addPurposeTag("closing socket"), "socket", BetterWatchdog.this.mProtocolSocket, MessagingControllerConstant.MESSAGING_CONTROLLER_DETAIL_KEY, this.mDetail);
                BetterWatchdog.this.mProtocolSocketLock.lock();
                try {
                    BetterWatchdog.this.mProtocolSocket.close(this.mDetail);
                    if (this.mRemoveListeners) {
                        BetterWatchdog.this.log.info("run", "removing GatewayConnectivityListener from socket", new Object[0]);
                        BetterWatchdog.this.removeGatewayConnectivityListener(BetterWatchdog.this.mProtocolSocket);
                    }
                    BetterWatchdog.this.mProtocolSocket = null;
                    BetterWatchdog.this.mProtocolSocketLock.unlock();
                    BetterWatchdog.this.mSocketStateGauge.setValue(ProtocolSocket.ProtocolSocketState.DISCONNECTED.name());
                } catch (Throwable th) {
                    BetterWatchdog.this.mProtocolSocketLock.unlock();
                    throw th;
                }
            }
        }

        public CloseSocketTask(CloseDetail closeDetail, boolean z) {
            this.mRemoveListeners = false;
            this.mDetail = closeDetail;
            this.mRemoveListeners = z;
        }
    }
}
