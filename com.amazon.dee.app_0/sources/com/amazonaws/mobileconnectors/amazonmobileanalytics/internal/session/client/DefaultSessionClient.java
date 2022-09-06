package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client;

import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.Preconditions;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.InternalSessionClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.Session;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.SessionStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
@Deprecated
/* loaded from: classes13.dex */
public class DefaultSessionClient implements InternalSessionClient {
    protected static final long DEFAULT_RESTART_DELAY = 30000;
    protected static final long DEFAULT_RESUME_DELAY = 5000;
    protected static final String RESTART_DELAY_CONFIG_KEY = "sessionRestartDelay";
    protected static final String RESUME_DELAY_CONFIG_KEY = "sessionResumeDelay";
    public static final String SESSION_PAUSE_EVENT_TYPE = "_session.pause";
    public static final String SESSION_RESUME_EVENT_TYPE = "_session.resume";
    public static final String SESSION_START_EVENT_TYPE = "_session.start";
    public static final String SESSION_STOP_EVENT_TYPE = "_session.stop";
    protected final AnalyticsContext context;
    protected final InternalEventClient eventClient;
    private final long restartDelay;
    private final long resumeDelay;
    protected Session session;
    protected final SessionStore sessionStore;
    protected SessionClientState state;
    private final SessionClientState INACTIVE_STATE = new InactiveSessionState(this);
    private final SessionClientState ACTIVE_STATE = new ActiveSessionState(this);
    private final SessionClientState PAUSED_STATE = new PausedSessionState(this);

    /* renamed from: com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.DefaultSessionClient$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobileconnectors$amazonmobileanalytics$internal$session$client$DefaultSessionClient$SessionState = new int[SessionState.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$amazonmobileanalytics$internal$session$client$DefaultSessionClient$SessionState[SessionState.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$amazonmobileanalytics$internal$session$client$DefaultSessionClient$SessionState[SessionState.ACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$amazonmobileanalytics$internal$session$client$DefaultSessionClient$SessionState[SessionState.PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes13.dex */
    protected enum SessionState {
        INACTIVE,
        ACTIVE,
        PAUSED
    }

    public DefaultSessionClient(AnalyticsContext analyticsContext, InternalEventClient internalEventClient, SessionStore sessionStore) {
        Preconditions.checkNotNull(analyticsContext, "A valid InsightsContext must be provided!");
        Preconditions.checkNotNull(internalEventClient, "A valid EventClient must be provided!");
        Preconditions.checkNotNull(sessionStore, "A valid SessionStore must be provided!");
        this.sessionStore = sessionStore;
        this.eventClient = internalEventClient;
        this.context = analyticsContext;
        this.session = this.sessionStore.getSession();
        Session session = this.session;
        if (session != null) {
            internalEventClient.setSessionId(session.getSessionID());
            internalEventClient.setSessionStartTime(this.session.getStartTime());
        }
        this.state = this.session == null ? this.INACTIVE_STATE : this.PAUSED_STATE;
        this.restartDelay = analyticsContext.getConfiguration().optLong(RESTART_DELAY_CONFIG_KEY, 30000L).longValue();
        this.resumeDelay = analyticsContext.getConfiguration().optLong(RESUME_DELAY_CONFIG_KEY, 5000L).longValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void changeState(SessionState sessionState) {
        int ordinal = sessionState.ordinal();
        if (ordinal == 0) {
            this.state = this.INACTIVE_STATE;
        } else if (ordinal == 1) {
            this.state = this.ACTIVE_STATE;
        } else if (ordinal == 2) {
            this.state = this.PAUSED_STATE;
        }
    }

    public long getRestartDelay() {
        return this.restartDelay;
    }

    public long getResumeDelay() {
        return this.resumeDelay;
    }

    protected Session getSession() {
        return this.session;
    }

    protected SessionState getSessionState() {
        Session session = this.session;
        if (session != null) {
            return session.isPaused() ? SessionState.PAUSED : SessionState.ACTIVE;
        }
        return SessionState.INACTIVE;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.SessionClient
    public synchronized void pauseSession() {
        this.state.pause();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.SessionClient
    public synchronized void resumeSession() {
        this.state.resume();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.InternalSessionClient
    public synchronized void startSession() {
        this.state.start();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.InternalSessionClient
    public synchronized void stopSession() {
        this.state.stop();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[DefaultSessionClient]\n- session: ");
        Session session = this.session;
        outline107.append(session == null ? DefaultRecordChecker.Regex.EMPTY : session.getSessionID());
        Session session2 = this.session;
        outline107.append((session2 == null || !session2.isPaused()) ? "" : ": paused");
        return outline107.toString();
    }
}
