package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.Timer;
import com.amazon.alexa.fitness.util.AssertionException;
import com.amazon.alexa.fitness.util.PreconditionsKt;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007H\u0016J\u0018\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0007H\u0016J\u0018\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001eH\u0016J\u0014\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070 H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001eH\u0016J\u0010\u0010#\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0007H\u0016J\u0010\u0010$\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0007H\u0016R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/DefaultMetricEvent;", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "timeSource", "Lcom/amazon/alexa/fitness/metrics/MonotonicallyIncreasingTimeSource;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "metricPrefix", "", "(Lcom/amazon/alexa/fitness/metrics/MonotonicallyIncreasingTimeSource;Lcom/amazon/alexa/fitness/logs/ILog;Ljava/lang/String;)V", JsonReportFormat.COUNTERS, "", "Lcom/amazon/alexa/fitness/metrics/Counter;", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "metadata", "getMetricPrefix", "()Ljava/lang/String;", "timers", "Lcom/amazon/alexa/fitness/metrics/Timer;", "attachMetadata", "key", "value", "incrementCounter", "metric", "by", "", "removeTimer", "setCounter", "to", "snapshotCounters", "", "snapshotMetadata", "", "snapshotTimers", "Lcom/amazon/alexa/fitness/metrics/Timer$Stopped;", "startTimer", "stopTimer", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DefaultMetricEvent implements MetricEvent {
    private final Map<String, Counter> counters;
    private final ReentrantLock lock;
    private final ILog log;
    private final Map<String, String> metadata;
    @NotNull
    private final String metricPrefix;
    private final MonotonicallyIncreasingTimeSource timeSource;
    private final Map<String, Timer> timers;

    public DefaultMetricEvent(@NotNull MonotonicallyIncreasingTimeSource timeSource, @NotNull ILog log, @NotNull String metricPrefix) {
        Intrinsics.checkParameterIsNotNull(timeSource, "timeSource");
        Intrinsics.checkParameterIsNotNull(log, "log");
        Intrinsics.checkParameterIsNotNull(metricPrefix, "metricPrefix");
        this.timeSource = timeSource;
        this.log = log;
        this.metricPrefix = metricPrefix;
        this.counters = new LinkedHashMap();
        this.timers = new LinkedHashMap();
        this.metadata = new LinkedHashMap();
        this.lock = new ReentrantLock();
        MetricEventKt.access$assertValidMetricName(PreconditionsKt.assertNotNullOrBlank$default(getMetricPrefix(), null, 1, null));
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public MetricEvent attachMetadata(@NotNull String key, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        String str = getMetricPrefix() + JsonReaderKt.COLON + key;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.metadata.get(str) != null) {
                ILog.DefaultImpls.warn$default(this.log, "DefaultMetricEvent", "Overwriting metadata for key '" + key + Chars.QUOTE, null, 4, null);
            }
            this.metadata.put(str, value);
            Unit unit = Unit.INSTANCE;
            return this;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public String getMetricPrefix() {
        return this.metricPrefix;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public MetricEvent incrementCounter(@NotNull String metric, long j) throws AssertionException {
        Counter counter;
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        MetricEventKt.access$assertValidMetricName(PreconditionsKt.assertNotNullOrBlank$default(metric, null, 1, null));
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Map<String, Counter> map = this.counters;
            Counter counter2 = this.counters.get(metric);
            if (counter2 == null || (counter = counter2.increment(j)) == null) {
                counter = new Counter(getMetricPrefix() + JsonReaderKt.COLON + metric, j);
            }
            map.put(metric, counter);
            Unit unit = Unit.INSTANCE;
            return this;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public MetricEvent removeTimer(@NotNull String metric) {
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            this.timers.remove(metric);
            return this;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public MetricEvent setCounter(@NotNull String metric, long j) throws AssertionException {
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        MetricEventKt.access$assertValidMetricName(PreconditionsKt.assertNotNullOrBlank$default(metric, null, 1, null));
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Map<String, Counter> map = this.counters;
            map.put(metric, new Counter(getMetricPrefix() + JsonReaderKt.COLON + metric, j));
            Unit unit = Unit.INSTANCE;
            return this;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public Collection<Counter> snapshotCounters() {
        ArrayList arrayList = new ArrayList();
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            arrayList.addAll(this.counters.values());
            reentrantLock.unlock();
            List unmodifiableList = Collections.unmodifiableList(arrayList);
            Intrinsics.checkExpressionValueIsNotNull(unmodifiableList, "Collections.unmodifiable….values)\n        }\n    })");
            return unmodifiableList;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public Map<String, String> snapshotMetadata() {
        HashMap hashMap = new HashMap();
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            hashMap.putAll(this.metadata);
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            Map<String, String> unmodifiableMap = Collections.unmodifiableMap(hashMap);
            Intrinsics.checkExpressionValueIsNotNull(unmodifiableMap, "Collections.unmodifiable…etadata)\n        }\n    })");
            return unmodifiableMap;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public Collection<Timer.Stopped> snapshotTimers() {
        int collectionSizeOrDefault;
        Timer.Stopped stopped;
        ArrayList arrayList = new ArrayList();
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            long nowMilli = this.timeSource.nowMilli();
            Collection<Timer> values = this.timers.values();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (Timer timer : values) {
                if (timer instanceof Timer.Started) {
                    stopped = ((Timer.Started) timer).stop(nowMilli);
                } else if (!(timer instanceof Timer.Stopped)) {
                    throw new NoWhenBranchMatchedException();
                } else {
                    stopped = (Timer.Stopped) timer;
                }
                arrayList2.add(stopped);
            }
            arrayList.addAll(arrayList2);
            reentrantLock.unlock();
            List unmodifiableList = Collections.unmodifiableList(arrayList);
            Intrinsics.checkExpressionValueIsNotNull(unmodifiableList, "Collections.unmodifiable…      })\n        }\n    })");
            return unmodifiableList;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public MetricEvent startTimer(@NotNull String metric) throws AssertionException {
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        MetricEventKt.access$assertValidMetricName(PreconditionsKt.assertNotNullOrBlank$default(metric, null, 1, null));
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Timer timer = this.timers.get(metric);
            if (timer == null) {
                Map<String, Timer> map = this.timers;
                Timer.Companion companion = Timer.Companion;
                map.put(metric, companion.start(getMetricPrefix() + JsonReaderKt.COLON + metric, this.timeSource.nowMilli()));
            } else if (timer instanceof Timer.Started) {
                ILog iLog = this.log;
                ILog.DefaultImpls.warn$default(iLog, "DefaultMetricEvent", "Attempting to start Timer '" + metric + "' that was already started!", null, 4, null);
            } else if (timer instanceof Timer.Stopped) {
                this.timers.put(metric, Timer.Companion.start(timer.getMetric(), this.timeSource.nowMilli() - ((Timer.Stopped) timer).getDurationMilli()));
            }
            Unit unit = Unit.INSTANCE;
            return this;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEvent
    @NotNull
    public MetricEvent stopTimer(@NotNull String metric) {
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Timer timer = this.timers.get(metric);
            if (timer == null) {
                ILog iLog = this.log;
                ILog.DefaultImpls.warn$default(iLog, "DefaultMetricEvent", "Attempting to stop Timer '" + metric + "' that was not started!", null, 4, null);
            } else if (timer instanceof Timer.Started) {
                this.timers.put(metric, ((Timer.Started) timer).stop(this.timeSource.nowMilli()));
            } else {
                ILog iLog2 = this.log;
                ILog.DefaultImpls.warn$default(iLog2, "DefaultMetricEvent", "Attempting to stop Timer '" + metric + "' that was already stopped!", null, 4, null);
            }
            Unit unit = Unit.INSTANCE;
            return this;
        } finally {
            reentrantLock.unlock();
        }
    }
}
