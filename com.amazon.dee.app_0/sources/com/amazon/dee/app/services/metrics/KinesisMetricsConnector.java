package com.amazon.dee.app.services.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisDefaultEventClient;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient;
import com.amazon.dee.app.services.metrics.kinesis.event.KinesisEvent;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient;
import com.amazon.dee.app.services.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.dee.app.services.metrics.util.PmetUtils;
import com.amazon.dee.app.util.MapUtils;
import com.amazon.deecomms.util.RedShiftTimeZone;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.AlexaMetricsEvent;
import com.dee.app.metrics.MetricsCounter;
import com.dee.app.metrics.MetricsTimer;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class KinesisMetricsConnector extends AsyncMetricsConnector {
    private static final String ANDROID = "ANDROID";
    private static final String FIRE_OS = "FIRE_OS";
    private final Context context;
    private final DeviceInformation deviceInformation;
    private final EnvironmentService environmentService;
    private final Lazy<IdentityService> identityServiceLazy;
    private final Lazy<KinesisEventClient> kinesisEventClient;
    private final Lazy<AppSessionClient> kinesisSessionClient;
    private String serviceName;
    private UserIdentity userIdentity;
    private static final String TAG = Log.tag(KinesisMetricsConnector.class);
    private static final Integer INITIAL_DELAY_SUBMIT_EVENTS = 10000;
    private Map<String, String> pivots = new HashMap();
    private String userDirectedId = "unknown";
    private final RedShiftTimeZone localTimeZone = new RedShiftTimeZone();

    public KinesisMetricsConnector(@NonNull Context context, @NonNull Lazy<IdentityService> lazy, @NonNull EnvironmentService environmentService, @NonNull DeviceInformation deviceInformation, @NonNull Lazy<AppSessionClient> lazy2, @NonNull Lazy<KinesisEventClient> lazy3) {
        this.context = context;
        this.identityServiceLazy = lazy;
        this.environmentService = environmentService;
        this.deviceInformation = deviceInformation;
        this.kinesisSessionClient = lazy2;
        this.kinesisEventClient = lazy3;
    }

    private void addCounterToKinesisEvent(MetricsCounter metricsCounter, KinesisEvent kinesisEvent) {
        kinesisEvent.addMetric("EventNumericValue", Double.valueOf(metricsCounter.getCount()));
    }

    private void addTimerToKinesisEvent(MetricsTimer metricsTimer, KinesisEvent kinesisEvent) {
        Object obj = metricsTimer.getCustomEntries().get("RecordTimerEnd");
        if (obj instanceof Long) {
            metricsTimer.finishTimer(Long.valueOf(((Long) obj).longValue()));
        } else {
            metricsTimer.finishTimer();
        }
        kinesisEvent.addMetric("EventNumericValue", Double.valueOf(metricsTimer.getElapsedTime()));
    }

    public /* synthetic */ void lambda$onBeginSession$0$KinesisMetricsConnector() {
        this.kinesisEventClient.mo358get().submitEvents();
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onBeginSession() {
        this.kinesisEventClient.mo358get().updateAppBackgroundStatus(false);
        getHandler().postDelayed(new Runnable() { // from class: com.amazon.dee.app.services.metrics.-$$Lambda$KinesisMetricsConnector$w_0-LeZMbX9QvFlxRDpT31V0D9Q
            @Override // java.lang.Runnable
            public final void run() {
                KinesisMetricsConnector.this.lambda$onBeginSession$0$KinesisMetricsConnector();
            }
        }, INITIAL_DELAY_SUBMIT_EVENTS.intValue());
        this.kinesisSessionClient.mo358get().resumeSession();
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onEndSession() {
        this.kinesisSessionClient.mo358get().pauseSession();
        this.kinesisEventClient.mo358get().submitEvents();
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onInitialize() {
        this.userIdentity = this.identityServiceLazy.mo358get().getUser(TAG);
        UserIdentity userIdentity = this.userIdentity;
        if (userIdentity != null) {
            this.userDirectedId = userIdentity.getDirectedId();
        }
        updateDirectedIdInClient();
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onPauseSession() {
        this.kinesisEventClient.mo358get().updateAppBackgroundStatus(true);
        this.kinesisSessionClient.mo358get().pauseSession();
        this.kinesisEventClient.mo358get().submitEvents();
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onRecordEvent(AlexaMetricsEvent alexaMetricsEvent) {
        KinesisEvent createEvent = this.kinesisEventClient.mo358get().createEvent(alexaMetricsEvent.getEventName());
        createEvent.addMetric("EventTimestamp", Double.valueOf(alexaMetricsEvent.getEventDate()));
        createEvent.addAttribute("AppComponent", alexaMetricsEvent.getComponentName());
        createEvent.addAttribute("localTimezone", this.localTimeZone.getTimeZone());
        createEvent.addAttribute(AlexaMetricsConstants.EventConstants.APP_FOREGROUNDED, String.valueOf(AlexaApplication.isAppOnForeground(this.context)));
        createEvent.addAttribute("DirectedID", this.userDirectedId);
        if (this.serviceName == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaMobileAndroid_");
            outline107.append(this.environmentService.getBuildFlavor());
            this.serviceName = outline107.toString();
            if (this.environmentService.isDebugBuild()) {
                this.serviceName = GeneratedOutlineSupport1.outline91(new StringBuilder(), this.serviceName, "_debug");
            }
        }
        String str = this.serviceName;
        createEvent.addAttribute("ServiceName", str + "_mobilytics");
        String str2 = this.environmentService.getMarketplace().toString();
        if (alexaMetricsEvent.getCustomEntries().containsKey("pfm")) {
            str2 = alexaMetricsEvent.getCustomEntries().get("pfm").toString();
        }
        String countryCode = this.environmentService.getCountryCode();
        String versionName = this.environmentService.getVersionName();
        String str3 = this.deviceInformation.isFireOS() ? "FIRE_OS" : "ANDROID";
        if (!GeneratedOutlineSupport1.outline75(countryCode, ":", str2).equals(this.pivots.get("CountryCode:MarketplaceIDCode"))) {
            this.pivots = PmetUtils.computePmetPivots(str2, countryCode, versionName, str3);
        }
        for (Map.Entry<String, String> entry : this.pivots.entrySet()) {
            createEvent.addPivot(entry.getKey(), entry.getValue());
        }
        try {
            Map<String, Object> customEntries = alexaMetricsEvent.getCustomEntries();
            if (customEntries != null) {
                for (Map.Entry<String, Object> entry2 : customEntries.entrySet()) {
                    createEvent.addAttribute(entry2.getKey(), String.valueOf(entry2.getValue()));
                }
            }
            if (alexaMetricsEvent instanceof MetricsCounter) {
                addCounterToKinesisEvent((MetricsCounter) alexaMetricsEvent, createEvent);
                createEvent.addAttribute("EventType", "Counter");
            } else if (alexaMetricsEvent instanceof MetricsTimer) {
                addTimerToKinesisEvent((MetricsTimer) alexaMetricsEvent, createEvent);
                createEvent.addAttribute("EventType", "Timer");
            } else if (customEntries.containsKey("EventNumericValue")) {
                createEvent.addMetric("EventNumericValue", MapUtils.getAsDouble(customEntries, "EventNumericValue"));
            } else if (customEntries.containsKey("TimerValue")) {
                createEvent.addMetric("EventNumericValue", MapUtils.getAsDouble(customEntries, "TimerValue"));
            } else if (customEntries.containsKey("CounterValue")) {
                createEvent.addMetric("EventNumericValue", MapUtils.getAsDouble(customEntries, "CounterValue"));
            }
            this.kinesisEventClient.mo358get().recordEvent(createEvent);
        } catch (Exception e) {
            String str4 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Exception during parsing the AlexaMetricsEvent : ");
            outline1072.append(e.getMessage());
            Log.e(str4, outline1072.toString());
        }
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onResumeSession() {
        this.kinesisEventClient.mo358get().updateAppBackgroundStatus(false);
        this.kinesisSessionClient.mo358get().resumeSession();
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onUserChangedOrNull(UserIdentity userIdentity) {
        this.userIdentity = userIdentity;
        if (userIdentity != null) {
            updateSessionizationLogic(false);
        }
    }

    public void updateDirectedIdDetails(String str) {
        this.userDirectedId = str;
        updateDirectedIdInClient();
    }

    public void updateDirectedIdInClient() {
        if (this.kinesisEventClient.mo358get() instanceof KinesisDefaultEventClient) {
            ((KinesisDefaultEventClient) this.kinesisEventClient.mo358get()).setDirectedId(this.userDirectedId);
        }
    }

    protected void updateSessionizationLogic(boolean z) {
        if (this.kinesisSessionClient.mo358get() instanceof AppDefaultSessionClient) {
            ((AppDefaultSessionClient) this.kinesisSessionClient.mo358get()).updateSessionizationStrategy(z);
        }
    }
}
