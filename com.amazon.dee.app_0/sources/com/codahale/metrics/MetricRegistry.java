package com.codahale.metrics;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class MetricRegistry implements MetricSet {
    private final ConcurrentMap<String, Metric> metrics = buildMap();
    private final List<MetricRegistryListener> listeners = new CopyOnWriteArrayList();

    /* loaded from: classes9.dex */
    private interface BuiltinBuilder {
        public static final MetricBuilder<Counter> COUNTERS = new MetricBuilder<Counter>() { // from class: com.codahale.metrics.MetricRegistry.BuiltinBuilder.1
            @Override // com.codahale.metrics.MetricBuilder
            public boolean isInstance(Metric metric) {
                return Counter.class.isInstance(metric);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.codahale.metrics.MetricBuilder
            /* renamed from: newMetric */
            public Counter mo6799newMetric() {
                return new Counter();
            }
        };
        public static final MetricBuilder<Histogram> HISTOGRAMS = new MetricBuilder<Histogram>() { // from class: com.codahale.metrics.MetricRegistry.BuiltinBuilder.2
            @Override // com.codahale.metrics.MetricBuilder
            public boolean isInstance(Metric metric) {
                return Histogram.class.isInstance(metric);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.codahale.metrics.MetricBuilder
            /* renamed from: newMetric */
            public Histogram mo6799newMetric() {
                return new Histogram(new ExponentiallyDecayingReservoir());
            }
        };
        public static final MetricBuilder<Meter> METERS = new MetricBuilder<Meter>() { // from class: com.codahale.metrics.MetricRegistry.BuiltinBuilder.3
            @Override // com.codahale.metrics.MetricBuilder
            public boolean isInstance(Metric metric) {
                return Meter.class.isInstance(metric);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.codahale.metrics.MetricBuilder
            /* renamed from: newMetric */
            public Meter mo6799newMetric() {
                return new Meter();
            }
        };
        public static final MetricBuilder<Timer> TIMERS = new MetricBuilder<Timer>() { // from class: com.codahale.metrics.MetricRegistry.BuiltinBuilder.4
            @Override // com.codahale.metrics.MetricBuilder
            public boolean isInstance(Metric metric) {
                return Timer.class.isInstance(metric);
            }

            @Override // com.codahale.metrics.MetricBuilder
            /* renamed from: newMetric  reason: collision with other method in class */
            public Timer mo6799newMetric() {
                return new Timer();
            }
        };
    }

    private static void append(StringBuilder sb, String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append('.');
        }
        sb.append(str);
    }

    private <T extends Metric> SortedMap<String, T> getMetrics(Class<T> cls, MetricFilter metricFilter) {
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<String, Metric> entry : this.metrics.entrySet()) {
            if (cls.isInstance(entry.getValue()) && metricFilter.matches(entry.getKey(), entry.getValue())) {
                treeMap.put(entry.getKey(), entry.getValue());
            }
        }
        return Collections.unmodifiableSortedMap(treeMap);
    }

    private <T extends Metric> T getOrAdd(String str, MetricBuilder<T> metricBuilder) {
        T t = (T) this.metrics.get(str);
        if (metricBuilder.isInstance(t)) {
            return t;
        }
        if (t == null) {
            try {
                return (T) register(str, metricBuilder.mo6799newMetric());
            } catch (IllegalArgumentException unused) {
                T t2 = (T) this.metrics.get(str);
                if (metricBuilder.isInstance(t2)) {
                    return t2;
                }
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(str, " is already used for a different type of metric"));
    }

    public static String name(Class<?> cls, String... strArr) {
        return name(cls.getName(), strArr);
    }

    public static String name(String str, String... strArr) {
        StringBuilder sb = new StringBuilder();
        append(sb, str);
        if (strArr != null) {
            for (String str2 : strArr) {
                append(sb, str2);
            }
        }
        return sb.toString();
    }

    private void notifyListenerOfAddedMetric(MetricRegistryListener metricRegistryListener, Metric metric, String str) {
        if (metric instanceof Gauge) {
            metricRegistryListener.onGaugeAdded(str, (Gauge) metric);
        } else if (metric instanceof Counter) {
            metricRegistryListener.onCounterAdded(str, (Counter) metric);
        } else if (metric instanceof Histogram) {
            metricRegistryListener.onHistogramAdded(str, (Histogram) metric);
        } else if (metric instanceof Meter) {
            metricRegistryListener.onMeterAdded(str, (Meter) metric);
        } else if (metric instanceof Timer) {
            metricRegistryListener.onTimerAdded(str, (Timer) metric);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown metric type: ");
            outline107.append(metric.getClass());
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    private void notifyListenerOfRemovedMetric(String str, Metric metric, MetricRegistryListener metricRegistryListener) {
        if (metric instanceof Gauge) {
            metricRegistryListener.onGaugeRemoved(str);
        } else if (metric instanceof Counter) {
            metricRegistryListener.onCounterRemoved(str);
        } else if (metric instanceof Histogram) {
            metricRegistryListener.onHistogramRemoved(str);
        } else if (metric instanceof Meter) {
            metricRegistryListener.onMeterRemoved(str);
        } else if (metric instanceof Timer) {
            metricRegistryListener.onTimerRemoved(str);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown metric type: ");
            outline107.append(metric.getClass());
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    private void onMetricAdded(String str, Metric metric) {
        for (MetricRegistryListener metricRegistryListener : this.listeners) {
            notifyListenerOfAddedMetric(metricRegistryListener, metric, str);
        }
    }

    private void onMetricRemoved(String str, Metric metric) {
        for (MetricRegistryListener metricRegistryListener : this.listeners) {
            notifyListenerOfRemovedMetric(str, metric, metricRegistryListener);
        }
    }

    private void registerAll(String str, MetricSet metricSet) throws IllegalArgumentException {
        for (Map.Entry<String, Metric> entry : metricSet.getMetrics().entrySet()) {
            if (entry.getValue() instanceof MetricSet) {
                registerAll(name(str, entry.getKey()), (MetricSet) entry.getValue());
            } else {
                register(name(str, entry.getKey()), entry.getValue());
            }
        }
    }

    public void addListener(MetricRegistryListener metricRegistryListener) {
        this.listeners.add(metricRegistryListener);
        for (Map.Entry<String, Metric> entry : this.metrics.entrySet()) {
            notifyListenerOfAddedMetric(metricRegistryListener, entry.getValue(), entry.getKey());
        }
    }

    protected ConcurrentMap<String, Metric> buildMap() {
        return new ConcurrentHashMap();
    }

    public Counter counter(String str) {
        return (Counter) getOrAdd(str, BuiltinBuilder.COUNTERS);
    }

    public SortedMap<String, Counter> getCounters() {
        return getCounters(MetricFilter.ALL);
    }

    public SortedMap<String, Counter> getCounters(MetricFilter metricFilter) {
        return getMetrics(Counter.class, metricFilter);
    }

    public SortedMap<String, Gauge> getGauges() {
        return getGauges(MetricFilter.ALL);
    }

    public SortedMap<String, Gauge> getGauges(MetricFilter metricFilter) {
        return getMetrics(Gauge.class, metricFilter);
    }

    public SortedMap<String, Histogram> getHistograms() {
        return getHistograms(MetricFilter.ALL);
    }

    public SortedMap<String, Histogram> getHistograms(MetricFilter metricFilter) {
        return getMetrics(Histogram.class, metricFilter);
    }

    public SortedMap<String, Meter> getMeters() {
        return getMeters(MetricFilter.ALL);
    }

    public SortedMap<String, Meter> getMeters(MetricFilter metricFilter) {
        return getMetrics(Meter.class, metricFilter);
    }

    @Override // com.codahale.metrics.MetricSet
    public Map<String, Metric> getMetrics() {
        return Collections.unmodifiableMap(this.metrics);
    }

    public SortedSet<String> getNames() {
        return Collections.unmodifiableSortedSet(new TreeSet(this.metrics.keySet()));
    }

    public SortedMap<String, Timer> getTimers() {
        return getTimers(MetricFilter.ALL);
    }

    public SortedMap<String, Timer> getTimers(MetricFilter metricFilter) {
        return getMetrics(Timer.class, metricFilter);
    }

    public Histogram histogram(String str) {
        return (Histogram) getOrAdd(str, BuiltinBuilder.HISTOGRAMS);
    }

    public Meter meter(String str) {
        return (Meter) getOrAdd(str, BuiltinBuilder.METERS);
    }

    public <T extends Metric> T metric(String str, MetricBuilder<T> metricBuilder) {
        return (T) getOrAdd(str, metricBuilder);
    }

    public <T extends Metric> T register(String str, T t) throws IllegalArgumentException {
        if (t instanceof MetricSet) {
            registerAll(str, (MetricSet) t);
        } else if (this.metrics.putIfAbsent(str, t) != null) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("A metric named ", str, " already exists"));
        } else {
            onMetricAdded(str, t);
        }
        return t;
    }

    public void registerAll(MetricSet metricSet) throws IllegalArgumentException {
        registerAll(null, metricSet);
    }

    public boolean remove(String str) {
        Metric remove = this.metrics.remove(str);
        if (remove != null) {
            onMetricRemoved(str, remove);
            return true;
        }
        return false;
    }

    public void removeListener(MetricRegistryListener metricRegistryListener) {
        this.listeners.remove(metricRegistryListener);
    }

    public void removeMatching(MetricFilter metricFilter) {
        for (Map.Entry<String, Metric> entry : this.metrics.entrySet()) {
            if (metricFilter.matches(entry.getKey(), entry.getValue())) {
                remove(entry.getKey());
            }
        }
    }

    public <T extends Metric> void replace(String str, T t) {
        Metric put = this.metrics.put(str, t);
        if (put != null) {
            onMetricRemoved(str, put);
        }
        onMetricAdded(str, t);
    }

    public Timer timer(String str) {
        return (Timer) getOrAdd(str, BuiltinBuilder.TIMERS);
    }
}
