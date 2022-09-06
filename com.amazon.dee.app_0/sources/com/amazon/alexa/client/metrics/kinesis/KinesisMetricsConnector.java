package com.amazon.alexa.client.metrics.kinesis;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.metrics.core.AlexaMetricsUtils;
import com.amazon.alexa.client.metrics.core.AsyncMetricsConnector;
import com.amazon.alexa.client.metrics.core.DefaultAlexaMetricsEvent;
import com.amazon.alexa.client.metrics.core.DefaultMetricsCounter;
import com.amazon.alexa.client.metrics.core.DefaultMetricsTimer;
import com.amazon.alexa.client.metrics.core.DirectedIDProvider;
import com.amazon.alexa.client.metrics.core.MetricsCounter;
import com.amazon.alexa.client.metrics.core.RedShiftTimeZone;
import com.amazon.alexa.client.metrics.kinesis.client.KinesisDefaultEventClient;
import com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.clouddrive.model.NodeType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class KinesisMetricsConnector extends AsyncMetricsConnector {
    private static final String MOBILYTICS_METRIC_SUFFIX = "_mobilytics";
    private final Lazy<DirectedIDProvider> authorizationAuthorityDirectedIDProvider;
    private KinesisManager kinesisManager;
    private final Lazy<KinesisManager> kinesisManagerProvider;
    private final RedShiftTimeZone localTimeZone;
    private boolean logDirectedId;
    private String userDirectedId;
    private static final String TAG = KinesisMetricsConnector.class.getSimpleName();
    private static final Integer INITIAL_DELAY_SUBMIT_EVENTS_MS = 10000;
    private static final Map<String, String> keyMappingforV1Table = new HashMap();

    static {
        keyMappingforV1Table.put("EVENT_VALUE", "EventValue");
        keyMappingforV1Table.put("REQUEST_ID", "requestId");
        keyMappingforV1Table.put("STATUS_CODE", "statusCode");
        keyMappingforV1Table.put(NodeType.SOURCE, "source");
        keyMappingforV1Table.put("TITLE", "title");
        keyMappingforV1Table.put("ERROR_SOURCE", "errorSource");
    }

    @Inject
    public KinesisMetricsConnector(Lazy<KinesisManager> lazy, Lazy<DirectedIDProvider> lazy2, Lazy<ClientConfiguration> lazy3) {
        super(lazy3);
        this.localTimeZone = new RedShiftTimeZone();
        this.logDirectedId = true;
        this.kinesisManagerProvider = lazy;
        this.authorizationAuthorityDirectedIDProvider = lazy2;
    }

    private void addCounterToKinesisEvent(MetricsCounter metricsCounter, KinesisEvent kinesisEvent) {
        kinesisEvent.addMetric("EventNumericValue", Double.valueOf(metricsCounter.getCount()));
    }

    private void addTimerToKinesisEvent(DefaultMetricsTimer defaultMetricsTimer, KinesisEvent kinesisEvent) {
        Object obj = defaultMetricsTimer.customEntries.get("RecordTimerEnd");
        if (obj != null && (obj instanceof Long)) {
            defaultMetricsTimer.finishTimer(Long.valueOf(((Long) obj).longValue()));
        } else {
            defaultMetricsTimer.finishTimer();
        }
        kinesisEvent.addMetric("EventNumericValue", Double.valueOf(defaultMetricsTimer.getElapsedTime()));
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onBeginSession() {
        this.kinesisManager.getEventClient().updateAppBackgroundStatus(false);
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.client.metrics.kinesis.KinesisMetricsConnector.1
            @Override // java.lang.Runnable
            public void run() {
                KinesisMetricsConnector.this.kinesisManager.getEventClient().submitEvents();
            }
        }, INITIAL_DELAY_SUBMIT_EVENTS_MS.intValue());
        this.kinesisManager.getAppSessionClient().resumeSession();
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onEndSession() {
        this.kinesisManager.getEventClient().updateAppBackgroundStatus(true);
        this.kinesisManager.getAppSessionClient().pauseSession();
        this.kinesisManager.getEventClient().submitEvents();
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onInitialize() {
        this.kinesisManager = this.kinesisManagerProvider.mo358get();
        this.userDirectedId = this.authorizationAuthorityDirectedIDProvider.mo358get().getDirectedID();
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onPauseSession() {
        this.kinesisManager.getEventClient().updateAppBackgroundStatus(true);
        this.kinesisManager.getAppSessionClient().pauseSession();
        this.kinesisManager.getEventClient().submitEvents();
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onRecordEvent(DefaultAlexaMetricsEvent defaultAlexaMetricsEvent) {
        KinesisEvent createEvent = this.kinesisManager.getEventClient().createEvent(defaultAlexaMetricsEvent.getEventName());
        createEvent.addMetric("EventTimestamp", Double.valueOf(defaultAlexaMetricsEvent.getEventDate()));
        createEvent.addAttribute("AppComponent", defaultAlexaMetricsEvent.getComponentName());
        createEvent.addAttribute("localTimezone", this.localTimeZone.getTimeZone());
        if (this.logDirectedId) {
            createEvent.addAttribute("DirectedID", this.userDirectedId);
        }
        createEvent.addAttribute("ServiceName", getServiceName(defaultAlexaMetricsEvent));
        try {
            Map<String, Object> map = defaultAlexaMetricsEvent.customEntries;
            if (map != null) {
                for (String str : map.keySet()) {
                    Object obj = map.get(str);
                    createEvent.addAttribute(str, String.valueOf(obj));
                    if (keyMappingforV1Table.containsKey(str)) {
                        createEvent.addAttribute(keyMappingforV1Table.get(str), String.valueOf(obj));
                    }
                }
            }
            if (defaultAlexaMetricsEvent instanceof DefaultMetricsCounter) {
                addCounterToKinesisEvent((MetricsCounter) defaultAlexaMetricsEvent, createEvent);
                createEvent.addAttribute("EventType", "Counter");
            } else if (defaultAlexaMetricsEvent instanceof DefaultMetricsTimer) {
                addTimerToKinesisEvent((DefaultMetricsTimer) defaultAlexaMetricsEvent, createEvent);
                createEvent.addAttribute("EventType", "Timer");
            } else if (map.containsKey("EventNumericValue")) {
                createEvent.addMetric("EventNumericValue", AlexaMetricsUtils.getAsDouble(map, "EventNumericValue"));
            }
            this.kinesisManager.getEventClient().recordEvent(createEvent);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Exception during parsing the DefaultAlexaMetricsEvent : "), TAG);
        }
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onResumeSession() {
        this.kinesisManager.getEventClient().updateAppBackgroundStatus(false);
        this.kinesisManager.getAppSessionClient().resumeSession();
    }

    public void updateDirectedIdDetails(boolean z) {
        this.userDirectedId = this.authorizationAuthorityDirectedIDProvider.mo358get().getDirectedID();
        this.logDirectedId = z;
        updateDirectedIdInClient();
    }

    public void updateDirectedIdInClient() {
        KinesisManager kinesisManager = this.kinesisManager;
        if (kinesisManager == null || kinesisManager.getEventClient() == null || !(this.kinesisManager.getEventClient() instanceof KinesisDefaultEventClient)) {
            return;
        }
        ((KinesisDefaultEventClient) this.kinesisManager.getEventClient()).setDirectedId(this.userDirectedId);
        ((KinesisDefaultEventClient) this.kinesisManager.getEventClient()).enableLogDirectedId(this.logDirectedId);
    }

    protected void updateSessionizationLogic(boolean z) {
        if (this.kinesisManager.getAppSessionClient() == null || !(this.kinesisManager.getAppSessionClient() instanceof AppDefaultSessionClient)) {
            return;
        }
        ((AppDefaultSessionClient) this.kinesisManager.getAppSessionClient()).updateSessionizationStrategy(z);
    }
}
