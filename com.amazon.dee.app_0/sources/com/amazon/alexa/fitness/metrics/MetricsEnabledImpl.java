package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.device.messaging.ADMRegistrationConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsEnabledImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/MetricsEnabledImpl;", "Lcom/amazon/alexa/fitness/metrics/MetricsEnabled;", "metricsService", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "(Lcom/amazon/alexa/mobilytics/Mobilytics;)V", "getMetricsService", "()Lcom/amazon/alexa/mobilytics/Mobilytics;", "createCounter", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsCounter;", "event", "Lcom/amazon/alexa/fitness/metrics/EventMetric;", "createTimer", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "recordClick", "", "recordError", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "recordOccurrence", "recordView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricsEnabledImpl implements MetricsEnabled {
    @NotNull
    private final Mobilytics metricsService;

    public MetricsEnabledImpl(@NotNull Mobilytics metricsService) {
        Intrinsics.checkParameterIsNotNull(metricsService, "metricsService");
        this.metricsService = metricsService;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    @NotNull
    public MobilyticsMetricsCounter createCounter(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        MobilyticsMetricsCounter createCounter = this.metricsService.createCounter(event.getQualifiedMetricName(), event.getQualifiedComponentName(), event.getQualifiedComponentName(), OwnerIdentifier.OTG_RAVEN);
        Intrinsics.checkExpressionValueIsNotNull(createCounter, "metricsService.createCou…ifier.OTG_RAVEN\n        )");
        createCounter.setChannelName(event.getChannelName());
        return createCounter;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    @NotNull
    public MobilyticsMetricsTimer createTimer(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        MobilyticsMetricsTimer createTimer = this.metricsService.createTimer(event.getQualifiedMetricName(), event.getQualifiedComponentName(), event.getQualifiedComponentName(), OwnerIdentifier.OTG_RAVEN);
        Intrinsics.checkExpressionValueIsNotNull(createTimer, "metricsService.createTim…ifier.OTG_RAVEN\n        )");
        createTimer.setChannelName(event.getChannelName());
        return createTimer;
    }

    @NotNull
    public final Mobilytics getMetricsService() {
        return this.metricsService;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordClick(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.metricsService.createUserInteractionEvent(event.getMetricName(), "click", event.getComponentName(), event.getSubComponentName(), OwnerIdentifier.OTG_RAVEN);
        Intrinsics.checkExpressionValueIsNotNull(createUserInteractionEvent, "metricsService.createUse…ifier.OTG_RAVEN\n        )");
        createUserInteractionEvent.setChannelName(event.getChannelName());
        this.metricsService.recordUserInteractionEvent(createUserInteractionEvent);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordError(@NotNull EventMetric event, @NotNull Exception exception) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        this.metricsService.recordCriticalEvent(event.getQualifiedMetricName(), exception.toString(), event.getQualifiedComponentName(), event.getQualifiedComponentName(), exception, OwnerIdentifier.OTG_RAVEN);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordOccurrence(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.metricsService.recordOccurrence(event.getMetricName(), true, event.getComponentName(), event.getSubComponentName(), OwnerIdentifier.OTG_RAVEN);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsEnabled
    public void recordView(@NotNull EventMetric event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.metricsService.createUserInteractionEvent(event.getMetricName(), "view", event.getComponentName(), event.getSubComponentName(), OwnerIdentifier.OTG_RAVEN);
        Intrinsics.checkExpressionValueIsNotNull(createUserInteractionEvent, "metricsService.createUse…ifier.OTG_RAVEN\n        )");
        createUserInteractionEvent.setChannelName(event.getChannelName());
        this.metricsService.recordUserInteractionEvent(createUserInteractionEvent);
    }
}
