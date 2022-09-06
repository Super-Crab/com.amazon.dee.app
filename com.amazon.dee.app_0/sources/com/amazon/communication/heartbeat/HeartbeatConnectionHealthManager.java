package com.amazon.communication.heartbeat;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.communication.ConnectivityMonitor;
import com.amazon.communication.ExponentialBackoffWaitCalculator;
import com.amazon.communication.MessageRouter;
import com.amazon.communication.PowerManagerWrapper;
import com.amazon.communication.PowerManagerWrapperImpl;
import com.amazon.communication.TCommUncaughtExceptionHandler;
import com.amazon.communication.ThreadName;
import com.amazon.communication.WakeLockHoldingScheduledThreadPoolExecutor;
import com.amazon.communication.heartbeat.HeartbeatNotificationHandler;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.ProtocolSocketStats;
import com.amazon.communication.socket.SocketManager;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.codahale.metrics.ValueGauge;
import com.dp.utils.ProperShutdown;
import com.dp.utils.ThreadGuard;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class HeartbeatConnectionHealthManager implements ConnectionHealthManager, HeartbeatNotificationHandler, HeartbeatReceivedHandler {
    private static final int BACKOFF_COEFFICEINT = 5000;
    private static final double JITTER_AS_FRACTION_OF_BACKOFF_INTERVAL = 0.1d;
    private static final long MAX_BACKOFF_INTERVAL_MS = 60000;
    private static final long MIN_BACKOFF_INTERVAL_MS = 5000;
    private static final int WILD_CARD_REGISTRATION_ID = -1;
    private final ConnectivityMonitor mConnectivityMonitor;
    private final HeartbeatNotificationScheduler mHeartbeatNotificationScheduler;
    private final HeartbeatCommunicator mHeartbeatSender;
    protected final PowerManagerWrapper mPowerManager;
    private final ExponentialBackoffWaitCalculator mSchedulerWaitCalculator;
    protected final SocketManager mSocketManager;
    protected final ConcurrentHashMap<Integer, SocketHeartbeatState> mSocketsToCheck;
    protected ScheduledThreadPoolExecutor mThreadPool;
    private static final String WAKELOCK_TAG = "TComm.HeartbeatConnectionHealthManager";
    private static final DPLogger log = new DPLogger(WAKELOCK_TAG);
    private static final CloseDetail NO_HEARTBEAT_RESPONSE_RECEIVED = new CloseDetail(CloseStatusCodes.HEARTBEAT_FAILURE, "Heartbeat failure");
    protected final Map<EndpointIdentity, HeartbeatState> mEndpointsToManage = new ConcurrentHashMap();
    protected final ConcurrentHashMap<Integer, SocketHeartbeatState> mScheduledSockets = new ConcurrentHashMap<>();
    protected final ConcurrentHashMap<Integer, Long> mLastHeartbeatTimestamp = new ConcurrentHashMap<>();
    private final AtomicBoolean mIsRunning = new AtomicBoolean(false);

    /* renamed from: com.amazon.communication.heartbeat.HeartbeatConnectionHealthManager$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$heartbeat$HeartbeatNotificationHandler$HeartbeatNotificationAttribute;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState = new int[ProtocolSocket.ProtocolSocketState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocket.ProtocolSocketState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocket.ProtocolSocketState.DISCONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocket.ProtocolSocketState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocket.ProtocolSocketState.CONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocket.ProtocolSocketState.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$communication$socket$ProtocolSocket$ProtocolSocketState[ProtocolSocket.ProtocolSocketState.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$amazon$communication$heartbeat$HeartbeatNotificationHandler$HeartbeatNotificationAttribute = new int[HeartbeatNotificationHandler.HeartbeatNotificationAttribute.values().length];
            try {
                $SwitchMap$com$amazon$communication$heartbeat$HeartbeatNotificationHandler$HeartbeatNotificationAttribute[HeartbeatNotificationHandler.HeartbeatNotificationAttribute.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class BeginHealthChecksCallable implements Runnable {
        private final int mRegistrationId;

        protected BeginHealthChecksCallable(int i) {
            this.mRegistrationId = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ProtocolSocket socket;
            ProtocolSocket.ProtocolSocketState socketState;
            ThreadGuard.ensureThreadPrefix(ThreadName.HEARTBEAT);
            if (!HeartbeatConnectionHealthManager.this.mIsRunning.get()) {
                return;
            }
            HeartbeatConnectionHealthManager.log.info("run", "begin health check", "registrationId", Integer.valueOf(this.mRegistrationId));
            SocketHeartbeatState remove = HeartbeatConnectionHealthManager.this.mScheduledSockets.remove(Integer.valueOf(this.mRegistrationId));
            if (remove != null && (socket = remove.getSocket()) != null && (socketState = socket.socketState()) != ProtocolSocket.ProtocolSocketState.CONNECTED) {
                HeartbeatConnectionHealthManager.log.warn("run", "begin health check: socket is not connected", "registrationId", Integer.valueOf(this.mRegistrationId), "socketState", socketState);
                remove.setSocketBroken();
            }
            SocketHeartbeatState socketToHeartbeat = HeartbeatConnectionHealthManager.this.getSocketToHeartbeat(this.mRegistrationId);
            if (socketToHeartbeat == null) {
                HeartbeatConnectionHealthManager.log.warn("run", "begin health check: no socket info to heartbeat", "isConnectivityPossible", Boolean.valueOf(HeartbeatConnectionHealthManager.this.mConnectivityMonitor.isConnectivityPossible()));
            } else if (socketToHeartbeat.getSocket() == null) {
                HeartbeatConnectionHealthManager.log.warn("run", "begin health check: no socket to heartbeat", "isHeartbeatPaused", Boolean.valueOf(socketToHeartbeat.getHeartbeatState().isHeartbeatPaused()), "isConnectivityPossible", Boolean.valueOf(HeartbeatConnectionHealthManager.this.mConnectivityMonitor.isConnectivityPossible()));
            } else {
                HeartbeatConnectionHealthManager.this.mSocketsToCheck.put(Integer.valueOf(socketToHeartbeat.getSocket().hashCode()), socketToHeartbeat);
                HeartbeatConnectionHealthManager.this.enqueueScheduleHeartbeatNotification(socketToHeartbeat);
                HeartbeatConnectionHealthManager.this.enqueueSendHeartbeats(socketToHeartbeat);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class CancelScheduledHeartbeatNotificationCallable implements Runnable {
        private int mRegistrationId;

        protected CancelScheduledHeartbeatNotificationCallable(int i) {
            this.mRegistrationId = -1;
            this.mRegistrationId = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadGuard.ensureThreadPrefix(ThreadName.HEARTBEAT);
            if (!HeartbeatConnectionHealthManager.this.mIsRunning.get()) {
                return;
            }
            HeartbeatConnectionHealthManager.log.info("run", "cancel scheduled heartbeat", "registrationId", Integer.valueOf(this.mRegistrationId));
            if (this.mRegistrationId != -1) {
                HeartbeatConnectionHealthManager.this.mHeartbeatNotificationScheduler.cancelScheduledNotification(this.mRegistrationId);
            } else {
                HeartbeatConnectionHealthManager.this.mHeartbeatNotificationScheduler.cancelScheduledNotification();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class CheckHeartbeatResponseCallable implements Runnable {
        final SocketHeartbeatState mSocketToCheck;

        protected CheckHeartbeatResponseCallable(SocketHeartbeatState socketHeartbeatState) {
            this.mSocketToCheck = socketHeartbeatState;
        }

        @Override // java.lang.Runnable
        public void run() {
            ProtocolSocket socket;
            ThreadGuard.ensureThreadPrefix(ThreadName.HEARTBEAT);
            if (!HeartbeatConnectionHealthManager.this.mIsRunning.get()) {
                return;
            }
            HeartbeatConnectionHealthManager.log.info("run", "check heartbeat response", "socketHeartbeatState", this.mSocketToCheck);
            if (!this.mSocketToCheck.hasHeartbeatResponseReceived() && (socket = this.mSocketToCheck.getSocket()) != null) {
                if (socket.socketState() != ProtocolSocket.ProtocolSocketState.CONNECTED) {
                    HeartbeatConnectionHealthManager.log.info("run", "check heartbeat response: socket is broken", "socketHeartbeatState", this.mSocketToCheck);
                    this.mSocketToCheck.setSocketBroken();
                } else if (this.mSocketToCheck.getHeartbeatAttempts() < HeartbeatSettings.getIntValue(HeartbeatSettings.MAX_HEARTBEAT_TRIES_KEY).intValue()) {
                    HeartbeatConnectionHealthManager.log.info("run", "check heartbeat response: no response, retry", "socketHeartbeatState", this.mSocketToCheck);
                    HeartbeatConnectionHealthManager.this.enqueueSendHeartbeats(this.mSocketToCheck);
                    return;
                }
            }
            HeartbeatConnectionHealthManager.this.enqueueFinishHealthChecks(this.mSocketToCheck);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class CloseNonResponseSocketCallable implements Runnable {
        private final CloseDetail mCloseDetail;
        private final SocketHeartbeatState mSocketToClose;

        public CloseNonResponseSocketCallable(SocketHeartbeatState socketHeartbeatState, CloseDetail closeDetail) {
            this.mSocketToClose = socketHeartbeatState;
            this.mCloseDetail = closeDetail;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadGuard.ensureThreadPrefix(ThreadName.HEARTBEAT);
            if (!HeartbeatConnectionHealthManager.this.mIsRunning.get()) {
                return;
            }
            HeartbeatConnectionHealthManager.log.info("run", "close unresponsive socket", "socketHeartbeatState", this.mSocketToClose, "closeDetail", this.mCloseDetail);
            HeartbeatConnectionHealthManager.this.closeNonResponsiveSocket(this.mSocketToClose, this.mCloseDetail);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class FinishHealthChecksCallable implements Runnable {
        private final SocketHeartbeatState mSocketToUpdate;

        public FinishHealthChecksCallable(SocketHeartbeatState socketHeartbeatState) {
            this.mSocketToUpdate = socketHeartbeatState;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadGuard.ensureThreadPrefix(ThreadName.HEARTBEAT);
            if (!HeartbeatConnectionHealthManager.this.mIsRunning.get()) {
                return;
            }
            HeartbeatConnectionHealthManager.log.info("run", "finish health check", "socketHeartbeatState", this.mSocketToUpdate);
            SocketHeartbeatState socketHeartbeatState = this.mSocketToUpdate;
            if (socketHeartbeatState != null) {
                ProtocolSocket socket = socketHeartbeatState.getSocket();
                if (socket != null) {
                    HeartbeatConnectionHealthManager.this.mSocketsToCheck.remove(Integer.valueOf(socket.hashCode()));
                }
                if (!this.mSocketToUpdate.hasHeartbeatResponseReceived()) {
                    HeartbeatConnectionHealthManager.this.closeNonResponsiveSocket(this.mSocketToUpdate);
                }
            }
            HeartbeatConnectionHealthManager.this.enqueueScheduleHeartbeatNotification(this.mSocketToUpdate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static class HeartbeatState {
        private final ConnectionHealthStatisticsAggregator mConnectionHealthStatsAggregator;
        private final HeartbeatIntervalDeterminer mHid;
        private volatile boolean mIsHeartbeatPaused = true;
        private volatile int mRegistrationId;

        public HeartbeatState(HeartbeatIntervalDeterminer heartbeatIntervalDeterminer, ConnectionHealthStatisticsAggregator connectionHealthStatisticsAggregator, int i) {
            this.mHid = heartbeatIntervalDeterminer;
            this.mConnectionHealthStatsAggregator = connectionHealthStatisticsAggregator;
            this.mRegistrationId = i;
        }

        public ConnectionHealthStatisticsAggregator getConnectionHealthStatisticsAggregator() {
            return this.mConnectionHealthStatsAggregator;
        }

        public HeartbeatIntervalDeterminer getHeartbeatIntervalDeterminer() {
            return this.mHid;
        }

        public int getRegistrationId() {
            return this.mRegistrationId;
        }

        public boolean isHeartbeatPaused() {
            return this.mIsHeartbeatPaused;
        }

        public void setIsHeartbeatPaused(boolean z) {
            this.mIsHeartbeatPaused = z;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mHid: ");
            outline107.append(this.mHid);
            outline107.append(", mRegistrationId: ");
            outline107.append(this.mRegistrationId);
            outline107.append(", mIsHeartbeatPaused: ");
            outline107.append(this.mIsHeartbeatPaused);
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class ScheduleHeartbeatNotificationCallable implements Runnable {
        private final SocketHeartbeatState mSocketInfo;

        protected ScheduleHeartbeatNotificationCallable(SocketHeartbeatState socketHeartbeatState) {
            if (socketHeartbeatState != null) {
                this.mSocketInfo = socketHeartbeatState;
                return;
            }
            throw new IllegalArgumentException("socketInfo must not be null");
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadGuard.ensureThreadPrefix(ThreadName.HEARTBEAT);
            if (!HeartbeatConnectionHealthManager.this.mIsRunning.get()) {
                return;
            }
            int registrationId = this.mSocketInfo.getRegistrationId();
            long minimumHeartbeatIntervalMillis = this.mSocketInfo.getHeartbeatIntervalDeterminer().getMinimumHeartbeatIntervalMillis();
            long maximumHeartbeatIntervalMillis = this.mSocketInfo.getHeartbeatIntervalDeterminer().getMaximumHeartbeatIntervalMillis();
            ProtocolSocket socket = this.mSocketInfo.getSocket();
            if (socket != null) {
                String lowerCase = socket.getPurpose().toString().toLowerCase();
                MetricRegistry orCreate = SharedMetricRegistries.getOrCreate(RouteName.MAIN);
                String outline72 = GeneratedOutlineSupport1.outline72("com.amazon.tcomm.", lowerCase);
                ((ValueGauge) orCreate.metric(GeneratedOutlineSupport1.outline72(outline72, ".heartbeat.interval_min"), ValueGauge.LONG)).setValue(Long.valueOf(minimumHeartbeatIntervalMillis));
                ((ValueGauge) orCreate.metric(outline72 + ".heartbeat.interval_max", ValueGauge.LONG)).setValue(Long.valueOf(maximumHeartbeatIntervalMillis));
            }
            HeartbeatConnectionHealthManager.log.info("run", "schedule heartbeat", "registrationId", Integer.valueOf(registrationId), "intervalMin", Long.valueOf(minimumHeartbeatIntervalMillis), "intervalMax", Long.valueOf(maximumHeartbeatIntervalMillis), "socketInfo", this.mSocketInfo);
            if (!HeartbeatConnectionHealthManager.this.mHeartbeatNotificationScheduler.scheduleHeartbeatNotification(registrationId, minimumHeartbeatIntervalMillis, maximumHeartbeatIntervalMillis)) {
                HeartbeatConnectionHealthManager.log.warn("run", "failed to schedule heartbeat", "socketInfo", this.mSocketInfo);
                HeartbeatConnectionHealthManager.this.rescheduleHeartbeatNotificationDelayed(this.mSocketInfo);
                return;
            }
            HeartbeatConnectionHealthManager.this.mScheduledSockets.put(Integer.valueOf(this.mSocketInfo.getRegistrationId()), this.mSocketInfo);
            HeartbeatConnectionHealthManager.this.mSchedulerWaitCalculator.reset();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class SendHeartbeatsCallable implements Runnable {
        private final SocketHeartbeatState mSocketToHeartbeat;

        protected SendHeartbeatsCallable(SocketHeartbeatState socketHeartbeatState) {
            this.mSocketToHeartbeat = socketHeartbeatState;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadGuard.ensureThreadPrefix(ThreadName.HEARTBEAT);
            if (!HeartbeatConnectionHealthManager.this.mIsRunning.get()) {
                return;
            }
            HeartbeatConnectionHealthManager.log.info("run", "sending heartbeat", "socketHeartbeatState", this.mSocketToHeartbeat);
            this.mSocketToHeartbeat.incrementHeartbeatAttempts();
            HeartbeatConnectionHealthManager.this.doSendHeartbeats(this.mSocketToHeartbeat);
            HeartbeatConnectionHealthManager.this.scheduleCheckHeartbeatResponses(this.mSocketToHeartbeat);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static class SocketHeartbeatState {
        private volatile int mHeartbeatAttempts;
        private final HeartbeatState mHeartbeatState;
        private volatile long mIdleTimeBeforeHeartbeat;
        private final Map<Integer, Long> mLastHeartbeatTimestampMap;
        private final ProtocolSocket mSocket;
        private final ThreadPoolExecutor mThreadPool;
        private volatile boolean mHeartbeatResponseReceived = false;
        private volatile Future<?> mHeartbeatResponseChecker = null;

        public SocketHeartbeatState(ProtocolSocket protocolSocket, HeartbeatState heartbeatState, Map<Integer, Long> map, ThreadPoolExecutor threadPoolExecutor) {
            this.mSocket = protocolSocket;
            this.mHeartbeatState = heartbeatState;
            this.mLastHeartbeatTimestampMap = map;
            this.mThreadPool = threadPoolExecutor;
        }

        public void beforeSendingHeartbeat() {
            this.mIdleTimeBeforeHeartbeat = HeartbeatConnectionHealthManager.getIdleTime(this.mSocket);
            this.mLastHeartbeatTimestampMap.put(Integer.valueOf(getRegistrationId()), Long.valueOf(GlobalTimeSource.INSTANCE.currentTimeMillis()));
        }

        public int getHeartbeatAttempts() {
            return this.mHeartbeatAttempts;
        }

        public HeartbeatIntervalDeterminer getHeartbeatIntervalDeterminer() {
            return this.mHeartbeatState.getHeartbeatIntervalDeterminer();
        }

        public HeartbeatState getHeartbeatState() {
            return this.mHeartbeatState;
        }

        public int getRegistrationId() {
            return this.mHeartbeatState.getRegistrationId();
        }

        public ProtocolSocket getSocket() {
            return this.mSocket;
        }

        protected long getSocketStalenessTimeout() {
            return HeartbeatSettings.getLongValue(HeartbeatSettings.SOCKET_STALENESS_BUFFER_MILLIS_KEY).longValue();
        }

        public boolean hasHeartbeatResponseReceived() {
            return this.mHeartbeatResponseReceived;
        }

        public void incrementHeartbeatAttempts() {
            this.mHeartbeatAttempts++;
        }

        public boolean isSocketStale() {
            long currentTimeMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
            long maximumHeartbeatIntervalMillis = getHeartbeatIntervalDeterminer().getMaximumHeartbeatIntervalMillis();
            long longValue = this.mLastHeartbeatTimestampMap.containsKey(Integer.valueOf(getRegistrationId())) ? this.mLastHeartbeatTimestampMap.get(Integer.valueOf(getRegistrationId())).longValue() : 0L;
            long j = currentTimeMillis - longValue;
            boolean z = longValue > 0 && j > maximumHeartbeatIntervalMillis && j > getSocketStalenessTimeout() + maximumHeartbeatIntervalMillis;
            HeartbeatConnectionHealthManager.log.verbose("isSocketStale", "finished checking socket", "isStale", Boolean.valueOf(z), "currentTime", Long.valueOf(currentTimeMillis), "lastHeartbeatTime", Long.valueOf(longValue), "timeSinceLastHeartbeat", Long.valueOf(j), "maxHeartbeatInterval", Long.valueOf(maximumHeartbeatIntervalMillis));
            return z;
        }

        public void setHeartbeatResponseChecker(Future<?> future) {
            this.mHeartbeatResponseChecker = future;
        }

        public void setHeartbeatResponseReceived() {
            this.mHeartbeatResponseReceived = true;
            if (this.mHeartbeatResponseChecker != null) {
                this.mHeartbeatResponseChecker.cancel(false);
                this.mThreadPool.purge();
            }
            this.mHeartbeatState.getConnectionHealthStatisticsAggregator().onHealthyConnection(this.mIdleTimeBeforeHeartbeat);
        }

        public void setSocketBroken() {
            this.mHeartbeatState.getConnectionHealthStatisticsAggregator().onUnhealthyConnection(this.mIdleTimeBeforeHeartbeat);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SocketHeartbeatState: socket: ");
            outline107.append(this.mSocket);
            outline107.append(", socket hashCode: ");
            ProtocolSocket protocolSocket = this.mSocket;
            outline107.append(protocolSocket == null ? null : Integer.valueOf(protocolSocket.hashCode()));
            outline107.append(", idleTimeBeforeHeartbeat: ");
            outline107.append(this.mIdleTimeBeforeHeartbeat);
            outline107.append(", heartbeatResponseReceived: ");
            outline107.append(this.mHeartbeatResponseReceived);
            outline107.append(", heartbeatAttempts: ");
            outline107.append(this.mHeartbeatAttempts);
            outline107.append(", heartbeatState: ");
            outline107.append(this.mHeartbeatState);
            return outline107.toString();
        }
    }

    public HeartbeatConnectionHealthManager(SocketManager socketManager, HeartbeatNotificationScheduler heartbeatNotificationScheduler, MessageRouter messageRouter, PowerManagerWrapper powerManagerWrapper, ConnectivityMonitor connectivityMonitor) {
        if (socketManager != null) {
            if (heartbeatNotificationScheduler == null) {
                throw new IllegalArgumentException("HeartbeatNotificationScheduler must not be null");
            }
            if (messageRouter == null) {
                throw new IllegalArgumentException("MessageRouter must not be null");
            }
            if (powerManagerWrapper == null) {
                throw new IllegalArgumentException("PowerManagerWrapper must not be null");
            }
            if (connectivityMonitor != null) {
                this.mHeartbeatNotificationScheduler = heartbeatNotificationScheduler;
                this.mSocketManager = socketManager;
                this.mPowerManager = powerManagerWrapper;
                this.mConnectivityMonitor = connectivityMonitor;
                this.mSchedulerWaitCalculator = new ExponentialBackoffWaitCalculator(5000L, 60000L, 5000, JITTER_AS_FRACTION_OF_BACKOFF_INTERVAL);
                this.mThreadPool = new WakeLockHoldingScheduledThreadPoolExecutor(1, ThreadName.HEARTBEAT, new TCommUncaughtExceptionHandler(), this.mPowerManager.newWakeLock(1, WAKELOCK_TAG), PowerManagerWrapperImpl.WAKE_LOCK_TIMEOUT_MS);
                this.mSocketsToCheck = new ConcurrentHashMap<>();
                setHeartbeatNotificationHandler();
                this.mHeartbeatSender = createHeartbeatSender(messageRouter);
                this.mIsRunning.set(true);
                this.mHeartbeatSender.registerHeartbeatReceivedHandler(this);
                return;
            }
            throw new IllegalArgumentException("ConnectivityMonitor must not be null");
        }
        throw new IllegalArgumentException("SocketManager must not be null");
    }

    private void cancelScheduledHeartbeatNotification(int i) {
        log.debug("cancelScheduledHeartbeatNotification", "enqueue task", "registrationId", Integer.valueOf(i));
        this.mThreadPool.submit(newCancelScheduledHeartbeatNotificationCallable(i));
    }

    private PingPongHeartbeatCommunicator createHeartbeatSender(MessageRouter messageRouter) {
        return new PingPongHeartbeatCommunicator(messageRouter, 101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSendHeartbeats(SocketHeartbeatState socketHeartbeatState) {
        ProtocolSocket socket = socketHeartbeatState.getSocket();
        if (socket == null) {
            return;
        }
        socketHeartbeatState.beforeSendingHeartbeat();
        if (socket.socketState() != ProtocolSocket.ProtocolSocketState.CONNECTED) {
            log.warn("doSendHeartbeats", "socket not CONNECTED", "socket", socket);
            return;
        }
        MetricRegistry orCreate = SharedMetricRegistries.getOrCreate(RouteName.MAIN);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("com.amazon.tcomm.");
        outline107.append(String.valueOf(socket.getPurpose()).toLowerCase());
        ((TimeSinceGauge) orCreate.metric(GeneratedOutlineSupport1.outline72(outline107.toString(), ".heartbeat.last_ms"), TimeSinceGauge.BUILDER)).update();
        try {
            this.mHeartbeatSender.sendHeartbeat(socket);
        } catch (Exception e) {
            log.error("doSendHeartbeats", ADMRegistrationConstants.CALL_EXCEPTION, e);
        }
    }

    protected static long getIdleTime(ProtocolSocket protocolSocket) {
        ProtocolSocketStats protocolSocketStats = protocolSocket.getProtocolSocketStats();
        long timeLastMessageReceived = protocolSocketStats.getTimeLastMessageReceived();
        if (timeLastMessageReceived == 0) {
            timeLastMessageReceived = protocolSocketStats.getTimeEstablished();
        }
        return GlobalTimeSource.INSTANCE.currentTimeMillis() - timeLastMessageReceived;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleCheckHeartbeatResponses(SocketHeartbeatState socketHeartbeatState) {
        long longValue = HeartbeatSettings.getLongValue(HeartbeatSettings.CHECK_HEARTBEAT_RESPONSE_DELAY_MILLIS_KEY).longValue();
        log.debug("scheduleCheckHeartbeatResponses", "schedule task", "delay", Long.valueOf(longValue), "socketToCheck", socketHeartbeatState);
        socketHeartbeatState.setHeartbeatResponseChecker(this.mThreadPool.schedule(newCheckHeartbeatResponseCallable(socketHeartbeatState), longValue, TimeUnit.MILLISECONDS));
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthManager
    public boolean closeIfStale(ProtocolSocket protocolSocket) {
        log.verbose("closeIfStale", "checking socket", "socket", protocolSocket);
        if (protocolSocket != null && protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
            HeartbeatState heartbeatState = this.mEndpointsToManage.get(protocolSocket.getEndpointIdentity());
            if (heartbeatState == null) {
                log.warn("closeIfStale", "no heartbeatState stored for socket", "endpointIdentity", protocolSocket.getEndpointIdentity());
                return false;
            }
            long currentTimeMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
            long maximumHeartbeatIntervalMillis = heartbeatState.getHeartbeatIntervalDeterminer().getMaximumHeartbeatIntervalMillis() + protocolSocket.getProtocolSocketStats().getTimeStartConnection();
            if (currentTimeMillis < maximumHeartbeatIntervalMillis) {
                log.debug("closeIfStale", "heartbeat was not scheduled yet", "currentTime", Long.valueOf(currentTimeMillis), "firstHeartbeatTime", Long.valueOf(maximumHeartbeatIntervalMillis));
                return false;
            }
            SocketHeartbeatState socketHeartbeatState = this.mScheduledSockets.get(Integer.valueOf(heartbeatState.getRegistrationId()));
            if (socketHeartbeatState != null && protocolSocket.equals(socketHeartbeatState.getSocket())) {
                if (!socketHeartbeatState.isSocketStale()) {
                    return false;
                }
                log.info("closeIfStale", "socket is stale", "socketHeartbeatState", socketHeartbeatState);
                this.mThreadPool.submit(newCloseNonResponseSocketCallable(socketHeartbeatState, new CloseDetail(CloseStatusCodes.NO_RECENT_HEARTBEATS, "Socket likely stale due to no recent heartbeats")));
                return true;
            }
            log.warn("closeIfStale", "heartbeats not scheduled for the socket", "socket", protocolSocket, "socketHeartbeatState", socketHeartbeatState);
        }
        return false;
    }

    protected void closeNonResponsiveSocket(SocketHeartbeatState socketHeartbeatState) {
        closeNonResponsiveSocket(socketHeartbeatState, NO_HEARTBEAT_RESPONSE_RECEIVED);
    }

    protected void enqueueBeginHealthChecks(int i) {
        ProtocolSocket socket;
        log.debug("enqueueBeginHealthChecks", "enqueue task", "registrationId", Integer.valueOf(i));
        SocketHeartbeatState socketToHeartbeat = getSocketToHeartbeat(i);
        if (socketToHeartbeat != null && (socket = socketToHeartbeat.getSocket()) != null) {
            this.mSocketsToCheck.remove(Integer.valueOf(socket.hashCode()));
        }
        this.mThreadPool.submit(newBeginHealthChecksCallable(i));
    }

    protected void enqueueFinishHealthChecks(SocketHeartbeatState socketHeartbeatState) {
        log.debug("enqueueFinishHealthChecks", "enqueue task", "socketToUpdate", socketHeartbeatState);
        this.mThreadPool.submit(newFinishHealthChecksCallable(socketHeartbeatState));
    }

    protected void enqueueScheduleHeartbeatNotification(SocketHeartbeatState socketHeartbeatState) {
        log.info("enqueueScheduleHeartbeatNotification", "enqueue task", socketHeartbeatState, socketHeartbeatState);
        this.mThreadPool.submit(newScheduleHeartbeatNotificationCallable(socketHeartbeatState));
    }

    protected void enqueueSendHeartbeats(SocketHeartbeatState socketHeartbeatState) {
        log.debug("enqueueSendHeartbeats", "enqueue task", "socketInfo", socketHeartbeatState);
        this.mThreadPool.submit(newSendHeartbeatsCallable(socketHeartbeatState));
    }

    protected SocketHeartbeatState getSocketToHeartbeat(int i) {
        for (ProtocolSocket protocolSocket : this.mSocketManager.getActiveProtocolSockets()) {
            if (protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
                EndpointIdentity endpointIdentity = protocolSocket.getEndpointIdentity();
                if (this.mEndpointsToManage.containsKey(endpointIdentity) && this.mEndpointsToManage.get(endpointIdentity).getRegistrationId() == i) {
                    SocketHeartbeatState socketHeartbeatState = new SocketHeartbeatState(protocolSocket, this.mEndpointsToManage.get(endpointIdentity), this.mLastHeartbeatTimestamp, this.mThreadPool);
                    log.info("getSocketToHeartbeat", "active socket found", "registrationId", Integer.valueOf(i), "socketHeartbeatState", socketHeartbeatState);
                    return socketHeartbeatState;
                }
            }
        }
        for (Map.Entry<EndpointIdentity, HeartbeatState> entry : this.mEndpointsToManage.entrySet()) {
            if (entry.getValue().getRegistrationId() == i) {
                SocketHeartbeatState socketHeartbeatState2 = new SocketHeartbeatState(null, entry.getValue(), this.mLastHeartbeatTimestamp, this.mThreadPool);
                log.info("getSocketToHeartbeat", "managed endpoint found", "registrationId", Integer.valueOf(i), "socketHeartbeatState", socketHeartbeatState2);
                return socketHeartbeatState2;
            }
        }
        log.info("getSocketToHeartbeat", "not found", "registrationId", Integer.valueOf(i));
        return null;
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthManager
    public void maintainConnection(EndpointIdentity endpointIdentity, HeartbeatIntervalDeterminer heartbeatIntervalDeterminer, ConnectionHealthStatisticsAggregator connectionHealthStatisticsAggregator) {
        log.info("maintainConnection", "maintain connection", "destination", EndpointIdentity.logSafe(endpointIdentity), "hid", heartbeatIntervalDeterminer, "connectionHealthStatsAggregator", connectionHealthStatisticsAggregator);
        this.mEndpointsToManage.put(endpointIdentity, new HeartbeatState(heartbeatIntervalDeterminer, connectionHealthStatisticsAggregator, endpointIdentity.hashCode()));
    }

    protected BeginHealthChecksCallable newBeginHealthChecksCallable(int i) {
        return new BeginHealthChecksCallable(i);
    }

    protected CancelScheduledHeartbeatNotificationCallable newCancelScheduledHeartbeatNotificationCallable(int i) {
        return new CancelScheduledHeartbeatNotificationCallable(i);
    }

    protected CheckHeartbeatResponseCallable newCheckHeartbeatResponseCallable(SocketHeartbeatState socketHeartbeatState) {
        return new CheckHeartbeatResponseCallable(socketHeartbeatState);
    }

    protected CloseNonResponseSocketCallable newCloseNonResponseSocketCallable(SocketHeartbeatState socketHeartbeatState, CloseDetail closeDetail) {
        return new CloseNonResponseSocketCallable(socketHeartbeatState, closeDetail);
    }

    protected FinishHealthChecksCallable newFinishHealthChecksCallable(SocketHeartbeatState socketHeartbeatState) {
        return new FinishHealthChecksCallable(socketHeartbeatState);
    }

    protected ScheduleHeartbeatNotificationCallable newScheduleHeartbeatNotificationCallable(SocketHeartbeatState socketHeartbeatState) {
        return new ScheduleHeartbeatNotificationCallable(socketHeartbeatState);
    }

    protected SendHeartbeatsCallable newSendHeartbeatsCallable(SocketHeartbeatState socketHeartbeatState) {
        return new SendHeartbeatsCallable(socketHeartbeatState);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatNotificationHandler
    public void onHeartbeatNotification(HeartbeatNotificationHandler.HeartbeatNotificationAttribute heartbeatNotificationAttribute, int i) {
        log.info("onHeartbeatNotification", "time to heartbeat", "attribute", heartbeatNotificationAttribute, "registrationId", Integer.valueOf(i));
        if (heartbeatNotificationAttribute.ordinal() != 0) {
            enqueueBeginHealthChecks(i);
        } else {
            scheduleHeartbeatNotification();
        }
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatReceivedHandler
    public void onHeartbeatReceived(EndpointIdentity endpointIdentity, int i) {
        SocketHeartbeatState remove = this.mSocketsToCheck.remove(Integer.valueOf(i));
        if (remove == null) {
            log.warn("onHeartbeatReceived", "socket is not subscribed for heartbeats", "destination", EndpointIdentity.logSafe(endpointIdentity), "socketHashCode", Integer.valueOf(i));
            return;
        }
        log.info("onHeartbeatReceived", "response received", "registrationId", Integer.valueOf(remove.getRegistrationId()), "destination", EndpointIdentity.logSafe(endpointIdentity), "socketHashCode", Integer.valueOf(i));
        remove.setHeartbeatResponseReceived();
        enqueueFinishHealthChecks(remove);
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthManager
    public void pause(EndpointIdentity endpointIdentity) {
        log.info("pause", "pausing heartbeats", "destination", EndpointIdentity.logSafe(endpointIdentity));
        HeartbeatState heartbeatState = this.mEndpointsToManage.get(endpointIdentity);
        if (heartbeatState != null) {
            heartbeatState.setIsHeartbeatPaused(true);
            cancelScheduledHeartbeatNotification(heartbeatState.getRegistrationId());
            log.info("pause", "paused the heartbeats for destination and canceled scheduled notification, if any", "destination", EndpointIdentity.logSafe(endpointIdentity));
        }
    }

    protected void rescheduleHeartbeatNotificationDelayed(SocketHeartbeatState socketHeartbeatState) {
        long waitMs = this.mSchedulerWaitCalculator.getWaitMs();
        if (waitMs >= 60000) {
            log.error("rescheduleHeartbeatNotificationDelayed", "failed too many times, skipping", "delay", Long.valueOf(waitMs));
            return;
        }
        log.debug("rescheduleHeartbeatNotificationDelayed", "schedule task", "delay", Long.valueOf(waitMs), "socketInfo", socketHeartbeatState);
        this.mThreadPool.schedule(newScheduleHeartbeatNotificationCallable(socketHeartbeatState), waitMs, TimeUnit.MILLISECONDS);
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthManager
    public void resume(EndpointIdentity endpointIdentity) {
        HeartbeatState heartbeatState = this.mEndpointsToManage.get(endpointIdentity);
        log.info("resume", "resuming heartbeats", "destination", EndpointIdentity.logSafe(endpointIdentity), "state", heartbeatState);
        if (heartbeatState != null) {
            heartbeatState.setIsHeartbeatPaused(false);
            SocketHeartbeatState socketToHeartbeat = getSocketToHeartbeat(heartbeatState.getRegistrationId());
            if (socketToHeartbeat == null) {
                return;
            }
            enqueueScheduleHeartbeatNotification(socketToHeartbeat);
        }
    }

    protected void scheduleHeartbeatNotification() {
        SocketHeartbeatState socketToHeartbeat;
        log.info("scheduleHeartbeatNotification", "schedule heartbeat for all endpoints", new Object[0]);
        for (HeartbeatState heartbeatState : this.mEndpointsToManage.values()) {
            if (!heartbeatState.isHeartbeatPaused() && (socketToHeartbeat = getSocketToHeartbeat(heartbeatState.getRegistrationId())) != null) {
                enqueueScheduleHeartbeatNotification(socketToHeartbeat);
            }
        }
    }

    protected void setHeartbeatNotificationHandler() {
        HeartbeatNotificationHandlerContainer.getInstance().setHeartbeatNotificationHandler(this);
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthManager
    public void shutdown() {
        log.verbose("shutdown", "shutdown invoked", new Object[0]);
        this.mIsRunning.set(false);
        this.mHeartbeatSender.shutdown();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mThreadPool;
        if (scheduledThreadPoolExecutor instanceof ProperShutdown) {
            ((ProperShutdown) scheduledThreadPoolExecutor).properShutdown();
        }
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthManager
    public void stop(EndpointIdentity endpointIdentity) {
        log.info("stop", "stopping heartbeats", "destination", EndpointIdentity.logSafe(endpointIdentity), "state", this.mEndpointsToManage.remove(endpointIdentity));
        pause(endpointIdentity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeNonResponsiveSocket(SocketHeartbeatState socketHeartbeatState, CloseDetail closeDetail) {
        boolean z;
        ProtocolSocket socket = socketHeartbeatState.getSocket();
        if (socket == null) {
            return;
        }
        ProtocolSocket.ProtocolSocketState socketState = socket.socketState();
        int ordinal = socketState.ordinal();
        if (ordinal == 1) {
            socket.close(closeDetail);
            log.warn("closeNonResponsiveSocket", "unexpected socket state", "socket", socket, "state", socketState);
        } else if (ordinal == 2) {
            socketHeartbeatState.setSocketBroken();
            socket.close(closeDetail);
        } else {
            if (ordinal != 3 && ordinal != 4) {
                log.warn("closeNonResponsiveSocket", "unexpected socket state", "socket", socket, "state", socketState);
            }
            z = true;
            log.info("closeNonResponsiveSocket", "socket closed", "socketHeartbeatState", socketHeartbeatState, "alreadyClosed", Boolean.valueOf(z));
        }
        z = false;
        log.info("closeNonResponsiveSocket", "socket closed", "socketHeartbeatState", socketHeartbeatState, "alreadyClosed", Boolean.valueOf(z));
    }
}
