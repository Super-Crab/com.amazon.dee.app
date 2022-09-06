package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.metrics.AccessoryMetric;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.protocol.Metrics;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public final class AccessoryMetricsSupplier implements MetricsSupplier, MetricsObserver {
    final Set<MetricsObserver> subscribers = new HashSet();

    @Override // com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver
    public void onMetricsAvailable(AccessorySession accessorySession, List<AccessoryMetric> list, List<Metrics.MetricsEvent> list2) {
        for (MetricsObserver metricsObserver : this.subscribers) {
            metricsObserver.onMetricsAvailable(accessorySession, list, list2);
        }
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsSupplier
    public void registerObserver(MetricsObserver metricsObserver) {
        this.subscribers.add(metricsObserver);
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsSupplier
    public void unRegisterObserver(MetricsObserver metricsObserver) {
        this.subscribers.remove(metricsObserver);
    }
}
