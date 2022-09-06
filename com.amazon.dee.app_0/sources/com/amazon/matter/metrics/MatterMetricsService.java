package com.amazon.matter.metrics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.matter.data.MatterErrorType;
import com.amazon.matter.eventbus.MatterEventType;
import javax.inject.Singleton;
import kotlinx.serialization.json.internal.JsonReaderKt;
@Singleton
/* loaded from: classes9.dex */
public class MatterMetricsService {
    private static final String COMPONENT_NAME = "Matter";
    private static final String ERROR = "Error";
    private static final String SUB_COMPONENT_NAME = "AlexaMobileMatter";
    private static final String TIME = "Time";
    private final Mobilytics mobilytics;

    public MatterMetricsService(Mobilytics mobilytics) {
        this.mobilytics = mobilytics;
    }

    private String getErrorMetricName(MatterEventType matterEventType, String str) {
        return String.format("%s.%s.%s", matterEventType.toString().replace("Matter:", "").replace(":request", ""), "Error", str);
    }

    private String getMetricNameFromEvent(MatterEventType matterEventType) {
        return matterEventType.toString().replace("Matter:", "").replace("response:", "").replace(JsonReaderKt.COLON, '.');
    }

    private String getTimeMetricName(MatterEventType matterEventType) {
        return String.format("%s.%s", matterEventType.toString().replace("Matter:", "").replace(":request", ""), TIME);
    }

    public MobilyticsMetricsTimer createAndStartTimer(MatterEventType matterEventType) {
        return this.mobilytics.createTimer(getTimeMetricName(matterEventType), COMPONENT_NAME, SUB_COMPONENT_NAME);
    }

    public void recordErrorMetric(MatterEventType matterEventType, MatterErrorType matterErrorType) {
        recordMetric(getErrorMetricName(matterEventType, matterErrorType.name()));
    }

    public void recordEventTime(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        this.mobilytics.recordTimer(mobilyticsMetricsTimer);
    }

    public void recordMetric(String str) {
        MobilyticsMetricsCounter createCounter = this.mobilytics.createCounter(str, COMPONENT_NAME, SUB_COMPONENT_NAME);
        createCounter.incrementCounter();
        this.mobilytics.recordCounter(createCounter);
    }

    public void recordRequestMetric(MatterEventType matterEventType) {
        recordMetric(getMetricNameFromEvent(matterEventType));
    }

    public void recordSuccessMetric(MatterEventType matterEventType) {
        recordMetric(getMetricNameFromEvent(matterEventType));
    }
}
