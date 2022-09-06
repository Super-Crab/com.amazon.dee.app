package com.amazon.dee.app.services.metrics.kinesis.session.client;

import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSession;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSessionStore;
import com.amazon.dee.app.services.metrics.kinesis.session.SessionObserver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class AppDefaultSessionClient implements AppSessionClient {
    protected static final String RESTART_DELAY_CONFIG_KEY = "sessionRestartDelay";
    protected static final String RESUME_DELAY_CONFIG_KEY = "sessionResumeDelay";
    public static final String SESSION_PAUSE_EVENT_TYPE = "_session.pause";
    public static final String SESSION_RESUME_EVENT_TYPE = "_session.resume";
    public static final String SESSION_START_EVENT_TYPE = "_session.start";
    public static final String SESSION_STOP_EVENT_TYPE = "_session.stop";
    protected AppSession appSession;
    protected final AppSessionStore appSessionStore;
    protected final KinesisContext kinesisContext;
    private long restartDelay;
    private long resumeDelay;
    protected AppSessionClientState state;
    protected static final long DEFAULT_RESUME_DELAY = TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS);
    protected static final long DEFAULT_RESTART_DELAY = TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);
    protected static final long DEFAULT_RESUME_DELAY_MOBILYTICS = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES);
    protected static final long DEFAULT_RESTART_DELAY_MOBILYTICS = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES);
    private final AppSessionClientState inactiveState = new AppInactiveSessionState(this);
    private final AppSessionClientState activeState = new AppActiveSessionState(this);
    private final AppSessionClientState pausedState = new AppPausedSessionState(this);
    private final Collection<SessionObserver> sessionObservers = new ArrayList();

    /* renamed from: com.amazon.dee.app.services.metrics.kinesis.session.client.AppDefaultSessionClient$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$services$metrics$kinesis$session$client$AppDefaultSessionClient$SessionState = new int[SessionState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$kinesis$session$client$AppDefaultSessionClient$SessionState[SessionState.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$kinesis$session$client$AppDefaultSessionClient$SessionState[SessionState.ACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$kinesis$session$client$AppDefaultSessionClient$SessionState[SessionState.PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    protected enum SessionState {
        INACTIVE,
        ACTIVE,
        PAUSED
    }

    public AppDefaultSessionClient(KinesisContext kinesisContext, AppSessionStore appSessionStore) {
        this.appSessionStore = appSessionStore;
        this.kinesisContext = kinesisContext;
        this.appSession = this.appSessionStore.getSession();
        this.state = this.appSession == null ? this.inactiveState : this.pausedState;
        this.restartDelay = DEFAULT_RESTART_DELAY;
        this.resumeDelay = DEFAULT_RESUME_DELAY;
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

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public void fireSessionPauseEvent() {
        synchronized (this.sessionObservers) {
            for (SessionObserver sessionObserver : this.sessionObservers) {
                sessionObserver.onSessionPause(this.appSession);
            }
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public void fireSessionResumeEvent() {
        synchronized (this.sessionObservers) {
            for (SessionObserver sessionObserver : this.sessionObservers) {
                sessionObserver.onSessionResume(this.appSession);
            }
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public void fireSessionStartEvent() {
        synchronized (this.sessionObservers) {
            for (SessionObserver sessionObserver : this.sessionObservers) {
                sessionObserver.onSessionStart(this.appSession);
            }
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public void fireSessionStopEvent() {
        synchronized (this.sessionObservers) {
            for (SessionObserver sessionObserver : this.sessionObservers) {
                sessionObserver.onSessionStop(this.appSession);
            }
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

    protected SessionState getSessionState() {
        AppSession appSession = this.appSession;
        if (appSession != null) {
            return appSession.isPaused() ? SessionState.PAUSED : SessionState.ACTIVE;
        }
        return SessionState.INACTIVE;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public synchronized void pauseSession() {
        this.state.pause();
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public void registerSessionObserver(@Nullable SessionObserver sessionObserver) {
        synchronized (this.sessionObservers) {
            if (sessionObserver != null) {
                this.sessionObservers.add(sessionObserver);
            }
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public synchronized void resumeSession() {
        this.state.resume();
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public synchronized void startSession() {
        this.state.start();
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
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

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient
    public void unregisterSessionObserver(@Nullable SessionObserver sessionObserver) {
        synchronized (this.sessionObservers) {
            if (sessionObserver != null) {
                this.sessionObservers.remove(sessionObserver);
            }
        }
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
}
