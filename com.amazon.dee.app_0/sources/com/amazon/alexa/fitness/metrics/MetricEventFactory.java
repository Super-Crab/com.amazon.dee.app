package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.util.AssertionException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricEventFactory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "", "createMetricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "metricPrefix", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface MetricEventFactory {
    @NotNull
    MetricEvent createMetricEvent(@NotNull String str) throws AssertionException;
}
