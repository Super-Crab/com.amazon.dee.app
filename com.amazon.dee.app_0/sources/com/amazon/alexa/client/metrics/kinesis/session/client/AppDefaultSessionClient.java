package com.amazon.alexa.client.metrics.kinesis.session.client;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.client.metrics.kinesis.client.KinesisInternalEventClient;
import com.amazon.alexa.client.metrics.kinesis.context.KinesisContext;
import com.amazon.alexa.client.metrics.kinesis.session.AppInternalSessionClient;
import com.amazon.alexa.client.metrics.kinesis.session.AppSession;
import com.amazon.alexa.client.metrics.kinesis.session.AppSessionStore;
import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazon.alexa.utils.TimeProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public class AppDefaultSessionClient implements AppInternalSessionClient {
    @VisibleForTesting
    public static final String CRASH_REPORTER_SESSION_ID_KEY = "sessionId";
    protected static final String RESTART_DELAY_CONFIG_KEY = "sessionRestartDelay";
    protected static final String RESUME_DELAY_CONFIG_KEY = "sessionResumeDelay";
    public static final String SESSION_PAUSE_EVENT_TYPE = "_session.pause";
    public static final String SESSION_RESUME_EVENT_TYPE = "_session.resume";
    public static final String SESSION_START_EVENT_TYPE = "_session.start";
    public static final String SESSION_STOP_EVENT_TYPE = "_session.stop";
    private final AppSessionClientState activeState;
    protected AppSession appSession;
    protected final AppSessionStore appSessionStore;
    private final CrashReporter crashReporter;
    private final AppSessionClientState inactiveState;
    protected final KinesisContext kinesisContext;
    protected final KinesisInternalEventClient kinesisEventClient;
    private final AppSessionClientState pausedState;
    private long restartDelay;
    private long resumeDelay;
    @VisibleForTesting
    public AppSessionClientState state;
    protected static final long DEFAULT_RESUME_DELAY = TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS);
    protected static final long DEFAULT_RESTART_DELAY = TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);
    protected static final long DEFAULT_RESUME_DELAY_MOBILYTICS = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES);
    protected static final long DEFAULT_RESTART_DELAY_MOBILYTICS = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES);

    /* renamed from: com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$client$metrics$kinesis$session$client$AppDefaultSessionClient$SessionState = new int[SessionState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$client$metrics$kinesis$session$client$AppDefaultSessionClient$SessionState[SessionState.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$client$metrics$kinesis$session$client$AppDefaultSessionClient$SessionState[SessionState.ACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$client$metrics$kinesis$session$client$AppDefaultSessionClient$SessionState[SessionState.PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    public enum SessionState {
        INACTIVE,
        ACTIVE,
        PAUSED
    }

    public AppDefaultSessionClient(KinesisContext kinesisContext, KinesisInternalEventClient kinesisInternalEventClient, AppSessionStore appSessionStore, CrashReporter crashReporter) {
        this(kinesisContext, kinesisInternalEventClient, appSessionStore, new TimeProvider(), crashReporter);
    }

    private void updateCrashReporterMetadata() {
        AppSession appSession = this.appSession;
        if (appSession != null) {
            this.crashReporter.putMetadata(CRASH_REPORTER_SESSION_ID_KEY, appSession.getSessionID());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void changeState(SessionState sessionState) {
        int ordinal = sessionState.ordinal();
        if (ordinal == 0) {
            this.state = this.inactiveState;
        } else if (ordinal == 1) {
            this.state = this.activeState;
        } else if (ordinal == 2) {
            this.state = this.pausedState;
        }
    }

    public AppSession getAppSession() {
        return this.appSession;
    }

    public long getRestartDelay() {
        return this.restartDelay;
    }

    public long getResumeDelay() {
        return this.resumeDelay;
    }

    @VisibleForTesting
    public SessionState getSessionState() {
        AppSession appSession = this.appSession;
        if (appSession != null) {
            return appSession.isPaused() ? SessionState.PAUSED : SessionState.ACTIVE;
        }
        return SessionState.INACTIVE;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.AppSessionClient
    public synchronized void pauseSession() {
        this.state.pause();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.AppSessionClient
    public synchronized void resumeSession() {
        this.state.resume();
        updateCrashReporterMetadata();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.AppInternalSessionClient
    public synchronized void startSession() {
        this.state.start();
        updateCrashReporterMetadata();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.AppInternalSessionClient
    public synchronized void stopSession() {
        this.state.stop();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[AppDefaultSessionClient]\n- appSession: ");
        AppSession appSession = this.appSession;
        outline107.append(appSession == null ? DefaultRecordChecker.Regex.EMPTY : appSession.getSessionID());
        AppSession appSession2 = this.appSession;
        outline107.append((appSession2 == null || !appSession2.isPaused()) ? "" : ": paused");
        return outline107.toString();
    }

    public void updateSessionizationStrategy(boolean z) {
        if (z) {
            this.resumeDelay = DEFAULT_RESUME_DELAY_MOBILYTICS;
            this.restartDelay = DEFAULT_RESTART_DELAY_MOBILYTICS;
            return;
        }
        this.resumeDelay = DEFAULT_RESUME_DELAY;
        this.restartDelay = DEFAULT_RESTART_DELAY;
    }

    @VisibleForTesting
    public AppDefaultSessionClient(KinesisContext kinesisContext, KinesisInternalEventClient kinesisInternalEventClient, AppSessionStore appSessionStore, TimeProvider timeProvider, CrashReporter crashReporter) {
        this.appSessionStore = appSessionStore;
        this.kinesisEventClient = kinesisInternalEventClient;
        this.kinesisContext = kinesisContext;
        this.appSession = this.appSessionStore.getSession();
        this.crashReporter = crashReporter;
        AppSession appSession = this.appSession;
        if (appSession != null) {
            kinesisInternalEventClient.setSessionId(appSession.getSessionID());
            kinesisInternalEventClient.setSessionStartTime(this.appSession.getStartTime());
            updateCrashReporterMetadata();
        }
        this.inactiveState = new AppInactiveSessionState(this, timeProvider);
        this.activeState = new AppActiveSessionState(this, timeProvider);
        this.pausedState = new AppPausedSessionState(this, timeProvider);
        this.state = this.appSession == null ? this.inactiveState : this.pausedState;
        this.restartDelay = DEFAULT_RESTART_DELAY;
        this.resumeDelay = DEFAULT_RESUME_DELAY;
    }
}
