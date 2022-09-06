package com.amazon.alexa.accessorykit.metrics;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.metrics.AccessoryMetric;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.protocol.Metrics;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes6.dex */
public final class RebounceMetricsObserver implements MetricsObserver {
    final List<MetricsObserver> metricsObservers;

    public RebounceMetricsObserver(MetricsObserver... metricsObserverArr) {
        this.metricsObservers = Arrays.asList(metricsObserverArr);
    }

    @Override // com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver
    public void onMetricsAvailable(AccessorySession accessorySession, List<AccessoryMetric> list, List<Metrics.MetricsEvent> list2) {
        for (MetricsObserver metricsObserver : this.metricsObservers) {
            metricsObserver.onMetricsAvailable(accessorySession, list, list2);
        }
    }
}
