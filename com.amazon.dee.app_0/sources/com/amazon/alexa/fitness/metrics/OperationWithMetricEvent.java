package com.amazon.alexa.fitness.metrics;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Operation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\f0\u000e¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/OperationWithMetricEvent;", "", "operation", "Lcom/amazon/alexa/fitness/metrics/Operation;", "metricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "(Lcom/amazon/alexa/fitness/metrics/Operation;Lcom/amazon/alexa/fitness/metrics/MetricEvent;)V", "getMetricEvent$AlexaMobileAndroidFitnessExtension_release", "()Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "getOperation$AlexaMobileAndroidFitnessExtension_release", "()Lcom/amazon/alexa/fitness/metrics/Operation;", "instrumentBlocking", ExifInterface.GPS_DIRECTION_TRUE, "codeBlock", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class OperationWithMetricEvent {
    @NotNull
    private final MetricEvent metricEvent;
    @NotNull
    private final Operation operation;

    public OperationWithMetricEvent(@NotNull Operation operation, @NotNull MetricEvent metricEvent) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(metricEvent, "metricEvent");
        this.operation = operation;
        this.metricEvent = metricEvent;
    }

    @NotNull
    public final MetricEvent getMetricEvent$AlexaMobileAndroidFitnessExtension_release() {
        return this.metricEvent;
    }

    @NotNull
    public final Operation getOperation$AlexaMobileAndroidFitnessExtension_release() {
        return this.operation;
    }

    public final <T> T instrumentBlocking(@NotNull Function1<? super MetricEvent, ? extends T> codeBlock) {
        Intrinsics.checkParameterIsNotNull(codeBlock, "codeBlock");
        OperationKt.decorateOnOperationStart(this.metricEvent, this.operation);
        try {
            T mo12165invoke = codeBlock.mo12165invoke(this.metricEvent);
            OperationKt.decorateOnOperationSuccess(this.metricEvent, this.operation);
            return mo12165invoke;
        } catch (RuntimeException e) {
            OperationKt.decorateOnOperationError$default(this.metricEvent, this.operation, e, (String) null, 4, (Object) null);
            throw e;
        }
    }
}
