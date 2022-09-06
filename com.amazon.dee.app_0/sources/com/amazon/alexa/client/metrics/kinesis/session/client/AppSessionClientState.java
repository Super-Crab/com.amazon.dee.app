package com.amazon.alexa.client.metrics.kinesis.session.client;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent;
import com.amazon.alexa.client.metrics.kinesis.event.KinesisInternalEvent;
import com.amazon.alexa.client.metrics.kinesis.session.AppSession;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.utils.TimeProvider;
/* loaded from: classes6.dex */
public abstract class AppSessionClientState {
    private static final String TAG = "AppSessionClientState";
    protected final AppDefaultSessionClient client;
    protected final TimeProvider timeProvider;

    public AppSessionClientState(AppDefaultSessionClient appDefaultSessionClient) {
        this(appDefaultSessionClient, new TimeProvider());
    }

    protected void addDirectedIdAttribute(KinesisEvent kinesisEvent) {
        if (this.client.kinesisEventClient.isDirectedIdEnabled()) {
            kinesisEvent.addAttribute("DirectedID", this.client.kinesisEventClient.getDirectedId());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executePause() {
        this.client.appSession.pause();
        String str = "AppSession Paused: " + this.client.appSession.getSessionID();
        AppDefaultSessionClient appDefaultSessionClient = this.client;
        KinesisInternalEvent createInternalEvent = appDefaultSessionClient.kinesisEventClient.createInternalEvent("_session.pause", Long.valueOf(appDefaultSessionClient.appSession.getStartTime()), null, this.client.appSession.getSessionDuration());
        createInternalEvent.addAttribute("AppComponent", AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE);
        addDirectedIdAttribute(createInternalEvent);
        this.client.kinesisEventClient.recordEvent(createInternalEvent);
        AppDefaultSessionClient appDefaultSessionClient2 = this.client;
        appDefaultSessionClient2.appSessionStore.storeSession(appDefaultSessionClient2.appSession);
        this.client.changeState(AppDefaultSessionClient.SessionState.PAUSED);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeResume() {
        this.client.appSession.resume();
        KinesisEvent createEvent = this.client.kinesisEventClient.createEvent("_session.resume");
        createEvent.addAttribute("AppComponent", AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE);
        addDirectedIdAttribute(createEvent);
        this.client.kinesisEventClient.recordEvent(createEvent);
        String str = "AppSession Resumed: " + this.client.appSession.getSessionID();
        this.client.changeState(AppDefaultSessionClient.SessionState.ACTIVE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeStart() {
        AppDefaultSessionClient appDefaultSessionClient = this.client;
        appDefaultSessionClient.appSession = AppSession.newInstance(appDefaultSessionClient.kinesisContext, this.timeProvider);
        AppDefaultSessionClient appDefaultSessionClient2 = this.client;
        appDefaultSessionClient2.kinesisEventClient.setSessionId(appDefaultSessionClient2.appSession.getSessionID());
        AppDefaultSessionClient appDefaultSessionClient3 = this.client;
        appDefaultSessionClient3.kinesisEventClient.setSessionStartTime(appDefaultSessionClient3.appSession.getStartTime());
        KinesisEvent createEvent = this.client.kinesisEventClient.createEvent("_session.start");
        createEvent.addAttribute("AppComponent", AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE);
        addDirectedIdAttribute(createEvent);
        this.client.kinesisEventClient.recordEvent(createEvent);
        this.client.changeState(AppDefaultSessionClient.SessionState.ACTIVE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeStop() {
        if (!this.client.appSession.isPaused()) {
            this.client.appSession.pause();
        }
        Long valueOf = Long.valueOf(this.client.appSession.getStopTime() == null ? 0L : this.client.appSession.getStopTime().longValue());
        AppDefaultSessionClient appDefaultSessionClient = this.client;
        KinesisInternalEvent createInternalEvent = appDefaultSessionClient.kinesisEventClient.createInternalEvent("_session.stop", Long.valueOf(appDefaultSessionClient.appSession.getStartTime()), valueOf, this.client.appSession.getSessionDuration());
        createInternalEvent.addAttribute("AppComponent", AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE);
        addDirectedIdAttribute(createInternalEvent);
        this.client.kinesisEventClient.recordEvent(createInternalEvent);
        AppDefaultSessionClient appDefaultSessionClient2 = this.client;
        appDefaultSessionClient2.appSession = null;
        appDefaultSessionClient2.changeState(AppDefaultSessionClient.SessionState.INACTIVE);
    }

    public abstract void pause();

    public abstract void resume();

    public abstract void start();

    public abstract void stop();

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public AppSessionClientState(AppDefaultSessionClient appDefaultSessionClient, TimeProvider timeProvider) {
        this.client = appDefaultSessionClient;
        this.timeProvider = timeProvider;
    }
}
