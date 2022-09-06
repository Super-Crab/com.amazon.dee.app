package com.amazon.alexa.fitness.utils;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n\u001a\u001e\u0010\u000b\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\f\u001a\u00060\rj\u0002`\u000e\u001a\u001a\u0010\u000f\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n\u001a\u001a\u0010\u0010\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"CHANNEL_NAME", "", "createTimer", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "metric", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "recordCounter", "", "value", "", "recordError", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "recordTimer", "recordUserInteractionEvent", "eventType", "Lcom/amazon/alexa/fitness/utils/EventType;", "AlexaMobileAndroidFitnessUI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricHelperKt {
    private static final String CHANNEL_NAME = "fitness";

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[EventType.values().length];

        static {
            $EnumSwitchMapping$0[EventType.TAP.ordinal()] = 1;
            $EnumSwitchMapping$0[EventType.VIEW.ordinal()] = 2;
        }
    }

    @NotNull
    public static final MobilyticsMetricsTimer createTimer(@NotNull Mobilytics createTimer, @NotNull MetricComponent metric) {
        Intrinsics.checkParameterIsNotNull(createTimer, "$this$createTimer");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        String qualifiedComponentName = metric.getQualifiedComponentName();
        MobilyticsMetricsTimer createTimer2 = createTimer.createTimer(metric.getQualifiedMetricName(), qualifiedComponentName, qualifiedComponentName, OwnerIdentifier.OTG_RAVEN);
        Intrinsics.checkExpressionValueIsNotNull(createTimer2, "createTimer(\n           …dentifier.OTG_RAVEN\n    )");
        createTimer2.setChannelName(CHANNEL_NAME);
        return createTimer2;
    }

    public static final void recordCounter(@NotNull Mobilytics recordCounter, @NotNull MetricComponent metric, long j) {
        Intrinsics.checkParameterIsNotNull(recordCounter, "$this$recordCounter");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        String qualifiedComponentName = metric.getQualifiedComponentName();
        MobilyticsMetricsCounter createCounter = recordCounter.createCounter(metric.getQualifiedMetricName(), qualifiedComponentName, qualifiedComponentName, OwnerIdentifier.OTG_RAVEN);
        Intrinsics.checkExpressionValueIsNotNull(createCounter, "createCounter(\n         …wnerIdentifier.OTG_RAVEN)");
        createCounter.setChannelName(CHANNEL_NAME);
        createCounter.incrementCounterByValue(j);
        recordCounter.recordCounter(createCounter);
    }

    public static /* synthetic */ void recordCounter$default(Mobilytics mobilytics, MetricComponent metricComponent, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 1;
        }
        recordCounter(mobilytics, metricComponent, j);
    }

    public static final void recordError(@NotNull Mobilytics recordError, @NotNull MetricComponent metric, @NotNull Exception error) {
        Intrinsics.checkParameterIsNotNull(recordError, "$this$recordError");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        Intrinsics.checkParameterIsNotNull(error, "error");
        recordError.recordCriticalEvent(metric.getQualifiedMetricName(), error.toString(), metric.getQualifiedComponentName(), metric.getQualifiedComponentName(), error, OwnerIdentifier.OTG_RAVEN);
    }

    public static final void recordTimer(@NotNull Mobilytics recordTimer, @NotNull MetricComponent metric, long j) {
        Intrinsics.checkParameterIsNotNull(recordTimer, "$this$recordTimer");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        MobilyticsMetricsTimer createTimer = createTimer(recordTimer, metric);
        createTimer.finishTimer(Long.valueOf(System.currentTimeMillis() + j));
        recordTimer.recordTimer(createTimer);
    }

    public static final void recordUserInteractionEvent(@NotNull Mobilytics recordUserInteractionEvent, @NotNull MetricComponent metric, @NotNull EventType eventType) {
        String str;
        Intrinsics.checkParameterIsNotNull(recordUserInteractionEvent, "$this$recordUserInteractionEvent");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        Intrinsics.checkParameterIsNotNull(eventType, "eventType");
        int i = WhenMappings.$EnumSwitchMapping$0[eventType.ordinal()];
        if (i == 1) {
            str = "click";
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            str = "view";
        }
        MobilyticsUserInteractionEvent createUserInteractionEvent = recordUserInteractionEvent.createUserInteractionEvent(metric.getMetricName(), str, metric.getComponentName(), metric.getSubComponentName(), OwnerIdentifier.OTG_RAVEN);
        Intrinsics.checkExpressionValueIsNotNull(createUserInteractionEvent, "createUserInteractionEve…dentifier.OTG_RAVEN\n    )");
        createUserInteractionEvent.setChannelName(CHANNEL_NAME);
        recordUserInteractionEvent.recordUserInteractionEvent(createUserInteractionEvent);
    }
}
