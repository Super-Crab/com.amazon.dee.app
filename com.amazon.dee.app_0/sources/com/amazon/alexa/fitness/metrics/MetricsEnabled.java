package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.device.messaging.ADMRegistrationConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsEnabled.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u000b\u001a\u00060\fj\u0002`\rH&J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/MetricsEnabled;", "", "createCounter", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsCounter;", "event", "Lcom/amazon/alexa/fitness/metrics/EventMetric;", "createTimer", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "recordClick", "", "recordError", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "recordOccurrence", "recordView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface MetricsEnabled {
    @NotNull
    MobilyticsMetricsCounter createCounter(@NotNull EventMetric eventMetric);

    @NotNull
    MobilyticsMetricsTimer createTimer(@NotNull EventMetric eventMetric);

    void recordClick(@NotNull EventMetric eventMetric);

    void recordError(@NotNull EventMetric eventMetric, @NotNull Exception exc);

    void recordOccurrence(@NotNull EventMetric eventMetric);

    void recordView(@NotNull EventMetric eventMetric);
}
