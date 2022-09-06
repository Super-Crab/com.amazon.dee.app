package com.amazon.dee.app.services.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.dee.app.metrics.MetricsHelper;
import com.amazon.dee.app.services.metrics.util.PmetUtils;
import com.amazon.dee.app.util.MapUtils;
import com.amazon.deecomms.util.RedShiftTimeZone;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.AlexaMetricsEvent;
import com.dee.app.metrics.MetricsCounter;
import com.dee.app.metrics.MetricsTimer;
import com.google.common.base.Preconditions;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class DCMMetricsConnector extends AsyncMetricsConnector {
    private final String appVersion;
    private final Context context;
    private final EnvironmentService environmentService;
    private final Lazy<MetricsFactory> lazyMetricsFactory;
    private final String serviceName;
    private final String osType = MetricsHelper.getOsType();
    private final RedShiftTimeZone localTimeZone = new RedShiftTimeZone();
    private Map<String, String> pivots = new HashMap();

    public DCMMetricsConnector(@NonNull Context context, @NonNull EnvironmentService environmentService, @NonNull Lazy<MetricsFactory> lazy) {
        this.context = (Context) Preconditions.checkNotNull(context);
        this.environmentService = (EnvironmentService) Preconditions.checkNotNull(environmentService);
        this.lazyMetricsFactory = (Lazy) Preconditions.checkNotNull(lazy);
        this.appVersion = environmentService.getVersionName();
        this.serviceName = defaultServiceName(environmentService);
    }

    private static String defaultServiceName(EnvironmentService environmentService) {
        String str = environmentService.isDebugBuild() ? "_debug" : "";
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaMobileAndroid_");
        outline107.append(environmentService.getBuildStage());
        outline107.append(str);
        return outline107.toString();
    }

    private double getCounterValue(AlexaMetricsEvent alexaMetricsEvent) {
        if (alexaMetricsEvent instanceof MetricsCounter) {
            return ((MetricsCounter) alexaMetricsEvent).getCount();
        }
        Double asDouble = MapUtils.getAsDouble(alexaMetricsEvent.getCustomEntries(), "CounterValue");
        if (asDouble != null) {
            return asDouble.doubleValue();
        }
        return 1.0d;
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onBeginSession() {
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onEndSession() {
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onInitialize() {
    }

    @Override // com.amazon.dee.app.services.metrics.AsyncMetricsConnector
    protected void onRecordEvent(AlexaMetricsEvent alexaMetricsEvent) {
        MetricEvent createMetricEvent = this.lazyMetricsFactory.mo358get().createMetricEvent(this.serviceName, alexaMetricsEvent.getComponentName());
        createMetricEvent.addString("EventName", alexaMetricsEvent.getEventName());
        createMetricEvent.addString("EventTimestamp", Long.toString(alexaMetricsEvent.getEventDate()));
        createMetricEvent.addString("localTimezone", this.localTimeZone.getTimeZone());
        String str = this.environmentService.getMarketplace().toString();
        if (alexaMetricsEvent.getCustomEntries().containsKey("pfm")) {
            str = alexaMetricsEvent.getCustomEntries().get("pfm").toString();
        }
        String countryCode = this.environmentService.getCountryCode();
        if (!GeneratedOutlineSupport1.outline75(countryCode, ":", str).equals(this.pivots.get("CountryCode:MarketplaceIDCode"))) {
            this.pivots = PmetUtils.computePmetPivots(str, countryCode, this.appVersion, this.osType);
        }
        for (Map.Entry<String, String> entry : this.pivots.entrySet()) {
            createMetricEvent.addString(entry.getKey(), entry.getValue());
        }
        Map<String, Object> customEntries = alexaMetricsEvent.getCustomEntries();
        if (customEntries != null) {
            for (Map.Entry<String, Object> entry2 : customEntries.entrySet()) {
                Object value = entry2.getValue();
                if (value != null) {
                    createMetricEvent.addString(entry2.getKey(), String.valueOf(value));
                }
            }
        }
        if (alexaMetricsEvent instanceof MetricsTimer) {
            Object obj = customEntries.get("RecordTimerEnd");
            if (obj instanceof Long) {
                ((MetricsTimer) alexaMetricsEvent).finishTimer(Long.valueOf(((Long) obj).longValue()));
            } else {
                ((MetricsTimer) alexaMetricsEvent).finishTimer();
            }
            createMetricEvent.addTimer(alexaMetricsEvent.getEventName(), ((MetricsTimer) alexaMetricsEvent).getElapsedTime());
        } else {
            Double asDouble = MapUtils.getAsDouble(customEntries, "TimerValue");
            if (asDouble != null) {
                createMetricEvent.addTimer(alexaMetricsEvent.getEventName(), asDouble.doubleValue());
            } else {
                createMetricEvent.addCounter(alexaMetricsEvent.getEventName(), getCounterValue(alexaMetricsEvent));
            }
        }
        this.lazyMetricsFactory.mo358get().record(createMetricEvent, Priority.NORMAL, Channel.ANONYMOUS);
    }
}
