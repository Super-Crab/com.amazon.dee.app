package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.util.AssertionException;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricEventFactory.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/DefaultMetricEventFactory;", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "timeSource", "Lcom/amazon/alexa/fitness/metrics/MonotonicallyIncreasingTimeSource;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/metrics/MonotonicallyIncreasingTimeSource;Lcom/amazon/alexa/fitness/logs/ILog;)V", "createMetricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "metricPrefix", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DefaultMetricEventFactory implements MetricEventFactory {
    private final ILog log;
    private final MonotonicallyIncreasingTimeSource timeSource;

    @Inject
    public DefaultMetricEventFactory(@NotNull MonotonicallyIncreasingTimeSource timeSource, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(timeSource, "timeSource");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.timeSource = timeSource;
        this.log = log;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEventFactory
    @NotNull
    public MetricEvent createMetricEvent(@NotNull String metricPrefix) throws AssertionException {
        Intrinsics.checkParameterIsNotNull(metricPrefix, "metricPrefix");
        return new DefaultMetricEvent(this.timeSource, this.log, metricPrefix);
    }
}
