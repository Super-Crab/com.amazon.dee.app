package com.amazon.communication.heartbeat;

import amazon.speech.simclient.settings.Settings;
import android.os.SystemClock;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.communication.AlwaysOnSocketWatchdog;
import com.amazon.communication.BackoffScheduler;
import com.amazon.communication.ConnectivityChangedHandler;
import com.amazon.communication.ConnectivityMonitor;
import com.amazon.communication.NetworkType;
import com.amazon.communication.heartbeat.HeartbeatIntervalUpdatesListener;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.remotesetting.SettingUpdateListener;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.dp.logger.DPLogger;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class ProbingConnectionLifetimeManager implements HeartbeatIntervalUpdatesListener, ConnectivityChangedHandler {
    protected static final String METRICS_SOURCE_NAME = "Heartbeats";
    private static final long MIN_DELAY_BEFORE_PROBING_MILLIS = 1000;
    private static final DPLogger log = new DPLogger("TComm.ProbingConnectionLifetimeManager");
    private final BackoffScheduler mBackoffScheduler;
    private final ConnectivityMonitor mConnectivityMonitor;
    private final HeartbeatIntervalDeterminer mIntervalDeterminer;
    private final SettingUpdateListener mSettingUpdateListener;
    private final AlwaysOnSocketWatchdog mSocketWatcher;
    protected final Object mStateLock = new Object();
    protected State mState = State.Idle;
    protected long mStateTime = SystemClock.elapsedRealtime();
    private final int mRegistrationId = hashCode();

    /* renamed from: com.amazon.communication.heartbeat.ProbingConnectionLifetimeManager$4  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$heartbeat$HeartbeatIntervalUpdatesListener$SwitchingReason = new int[HeartbeatIntervalUpdatesListener.SwitchingReason.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$heartbeat$HeartbeatIntervalUpdatesListener$SwitchingReason[HeartbeatIntervalUpdatesListener.SwitchingReason.CONSECUTIVE_FAILURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$heartbeat$HeartbeatIntervalUpdatesListener$SwitchingReason[HeartbeatIntervalUpdatesListener.SwitchingReason.INTERVAL_VALIDITY_EXPIRED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$communication$heartbeat$HeartbeatIntervalUpdatesListener$SwitchingReason[HeartbeatIntervalUpdatesListener.SwitchingReason.REMOTE_SETTINGS_DRASTIC_CHANGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    private final class SettingsCacheUpdateListener extends SettingUpdateListener {
        private SettingsCacheUpdateListener() {
        }

        @Override // com.amazon.communication.remotesetting.SettingUpdateListener
        public void onSettingUpdated() {
            ProbingConnectionLifetimeManager.log.info("ProbingConnectionLifetimeManager-SettingUpdateListener", "Reconfiguring probing.", new Object[0]);
            ProbingConnectionLifetimeManager.this.configureProbing(ConnectReason.ReasonString.PolicyChange, When.Later);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public enum State {
        Idle,
        Probing,
        Suspended
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum When {
        Now,
        Later
    }

    public ProbingConnectionLifetimeManager(AlwaysOnSocketWatchdog alwaysOnSocketWatchdog, HeartbeatIntervalDeterminer heartbeatIntervalDeterminer, ConnectivityMonitor connectivityMonitor, BackoffScheduler backoffScheduler) {
        this.mSocketWatcher = alwaysOnSocketWatchdog;
        this.mIntervalDeterminer = heartbeatIntervalDeterminer;
        this.mConnectivityMonitor = connectivityMonitor;
        this.mBackoffScheduler = backoffScheduler;
        this.mIntervalDeterminer.addHeartbeatIntervalUpdatesListener(this);
        this.mSettingUpdateListener = new SettingsCacheUpdateListener();
        RemoteSettingManager.addSettingUpdateListener(this.mSettingUpdateListener);
        this.mConnectivityMonitor.registerConnectivityChangedHandler(this);
        configureProbing(ConnectReason.ReasonString.ServiceStarted, When.Later);
        MetricRegistry orCreate = SharedMetricRegistries.getOrCreate(RouteName.MAIN);
        orCreate.replace("com.amazon.tcomm.probing.state", new Gauge<String>() { // from class: com.amazon.communication.heartbeat.ProbingConnectionLifetimeManager.1
            @Override // com.codahale.metrics.Gauge
            /* renamed from: getValue  reason: collision with other method in class */
            public String mo6796getValue() {
                return ProbingConnectionLifetimeManager.this.mState.toString();
            }
        });
        orCreate.replace("com.amazon.tcomm.probing.duration_ms", new Gauge<Long>() { // from class: com.amazon.communication.heartbeat.ProbingConnectionLifetimeManager.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.codahale.metrics.Gauge
            /* renamed from: getValue */
            public Long mo6796getValue() {
                return Long.valueOf(SystemClock.elapsedRealtime() - ProbingConnectionLifetimeManager.this.mStateTime);
            }
        });
    }

    private void cancelProbingAlarms() {
        this.mBackoffScheduler.cancel(this.mRegistrationId);
        log.debug("cancelProbingAlarms", "Canceling probing alarms", "id", Integer.valueOf(this.mRegistrationId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configureProbing(ConnectReason.ReasonString reasonString, When when) {
        if (!HeartbeatSettings.getBooleanValue(HeartbeatSettings.IS_PROBING_CONNECTION_ENABLED_KEY).booleanValue()) {
            log.info("configureProbing", "probing disabled", Settings.ListeningSettings.EXTRA_REASON, reasonString);
            stopProbing();
        } else if (!this.mConnectivityMonitor.isConnectivityPossible()) {
            log.info("configureProbing", "no connectivity", Settings.ListeningSettings.EXTRA_REASON, reasonString);
            stopProbing();
        } else if (this.mIntervalDeterminer.hasLearntHeartbeatInterval()) {
            log.info("configureProbing", "known heartbeat interval", Settings.ListeningSettings.EXTRA_REASON, reasonString);
            stopProbing();
        } else if (when == When.Later) {
            if (isProbing()) {
                log.info("configureProbing", "already probing", Settings.ListeningSettings.EXTRA_REASON, reasonString);
                return;
            }
            log.info("configureProbing", "configure probing delayed", Settings.ListeningSettings.EXTRA_REASON, reasonString);
            configureProbingDelayed(reasonString);
        } else {
            log.info("configureProbing", "start probing", Settings.ListeningSettings.EXTRA_REASON, reasonString);
            startProbing(reasonString);
        }
    }

    private void configureProbingDelayed(final ConnectReason.ReasonString reasonString) {
        Runnable runnable = new Runnable() { // from class: com.amazon.communication.heartbeat.ProbingConnectionLifetimeManager.3
            @Override // java.lang.Runnable
            public void run() {
                ProbingConnectionLifetimeManager.log.info("run", "configure probing delayed", new Object[0]);
                ProbingConnectionLifetimeManager.this.configureProbing(reasonString, When.Now);
            }
        };
        long random = ((long) (Math.random() * HeartbeatSettings.getLongValue(HeartbeatSettings.PROBING_CONNECTION_MAX_INITIAL_DELAY_MILLIS_KEY).longValue())) + this.mBackoffScheduler.getMinimumDelayMillis() + 1000;
        log.debug("configureProbingDelayed", "Scheduling probing delay alarm", "id", Integer.valueOf(this.mRegistrationId));
        this.mBackoffScheduler.schedule(this.mRegistrationId, runnable, random, TimeUnit.MILLISECONDS);
    }

    private boolean isProbing() {
        boolean z;
        synchronized (this.mStateLock) {
            z = this.mState == State.Probing;
        }
        return z;
    }

    private void startProbing(ConnectReason.ReasonString reasonString) {
        synchronized (this.mStateLock) {
            if (this.mState == State.Idle) {
                this.mSocketWatcher.startWatching(reasonString);
                this.mState = State.Probing;
                this.mStateTime = SystemClock.elapsedRealtime();
            }
        }
    }

    private void stopProbing() {
        synchronized (this.mStateLock) {
            if (this.mState == State.Probing) {
                this.mSocketWatcher.stopWatching();
                log.debug("stopProbing", "Heartbeat probing done.", new Object[0]);
                this.mState = State.Idle;
                this.mStateTime = SystemClock.elapsedRealtime();
            }
            cancelProbingAlarms();
        }
    }

    private static ConnectReason.ReasonString toReasonString(HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason) {
        ConnectReason.ReasonString reasonString = ConnectReason.ReasonString.ConnectionFailed;
        int ordinal = switchingReason.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return ConnectReason.ReasonString.HeartbeatFailure;
            }
            return ordinal != 2 ? reasonString : ConnectReason.ReasonString.RemoteSettingsDrasticChange;
        }
        return ConnectReason.ReasonString.HeartbeatIntervalExpired;
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalUpdatesListener
    public void changedHeartbeatInterval(NetworkType networkType, long j, long j2) {
    }

    @Override // com.amazon.communication.ConnectivityChangedHandler
    public void onConnectivityChanged() {
        configureProbing(ConnectReason.ReasonString.ConnectivityAvailable, When.Later);
    }

    public void restartProbing() {
        synchronized (this.mStateLock) {
            if (this.mState == State.Suspended) {
                RemoteSettingManager.addSettingUpdateListener(this.mSettingUpdateListener);
                this.mConnectivityMonitor.registerConnectivityChangedHandler(this);
                log.debug("restartProbing", "Restarting probing and listeners", new Object[0]);
                this.mState = State.Probing;
                stopProbing();
                configureProbing(ConnectReason.ReasonString.ServiceStarted, When.Later);
            }
        }
    }

    public void shutdown() {
        RemoteSettingManager.removeSettingUpdateListener(this.mSettingUpdateListener);
        this.mConnectivityMonitor.deregisterConnectivityChangedHandler(this);
    }

    public void suspendProbing() {
        synchronized (this.mStateLock) {
            if (this.mState != State.Suspended) {
                cancelProbingAlarms();
                RemoteSettingManager.removeSettingUpdateListener(this.mSettingUpdateListener);
                this.mConnectivityMonitor.deregisterConnectivityChangedHandler(this);
                log.debug("suspendProbing", "Suspending probing alarms and listeners", new Object[0]);
                this.mState = State.Suspended;
            }
        }
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalUpdatesListener
    public void switchedToLearningMode(NetworkType networkType, HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason) {
        log.info("switchedToLearningMode", "recommendation to start probing", Settings.ListeningSettings.EXTRA_REASON, switchingReason);
        configureProbing(toReasonString(switchingReason), When.Now);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalUpdatesListener
    public void switchedToLearntMode(NetworkType networkType, HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason, long j) {
        log.info("switchedToLearntMode", "recommendation to stop probing", Settings.ListeningSettings.EXTRA_REASON, switchingReason, "learntInterval", Long.valueOf(j));
        configureProbing(toReasonString(switchingReason), When.Now);
    }
}
