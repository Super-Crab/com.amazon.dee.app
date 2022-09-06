package com.amazon.alexa.fitness.metrics;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Operation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/Operation;", "", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", JsonPOJOBuilder.DEFAULT_WITH_PREFIX, "Lcom/amazon/alexa/fitness/metrics/OperationWithMetricEvent;", "metricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Operation {
    @NotNull
    private final String name;

    public Operation(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final OperationWithMetricEvent with(@NotNull MetricEvent metricEvent) {
        Intrinsics.checkParameterIsNotNull(metricEvent, "metricEvent");
        return new OperationWithMetricEvent(this, metricEvent);
    }
}
