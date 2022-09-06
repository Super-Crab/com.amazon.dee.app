package com.codahale.metrics;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes9.dex */
public class SharedMetricRegistries {
    private static final ConcurrentMap<String, MetricRegistry> REGISTRIES = new ConcurrentHashMap();

    private SharedMetricRegistries() {
    }

    public static MetricRegistry add(String str, MetricRegistry metricRegistry) {
        return REGISTRIES.putIfAbsent(str, metricRegistry);
    }

    public static void clear() {
        REGISTRIES.clear();
    }

    public static MetricRegistry getOrCreate(String str) {
        MetricRegistry metricRegistry = REGISTRIES.get(str);
        if (metricRegistry == null) {
            MetricRegistry metricRegistry2 = new MetricRegistry();
            MetricRegistry add = add(str, metricRegistry2);
            return add == null ? metricRegistry2 : add;
        }
        return metricRegistry;
    }

    public static Set<String> names() {
        return REGISTRIES.keySet();
    }

    public static void remove(String str) {
        REGISTRIES.remove(str);
    }
}
