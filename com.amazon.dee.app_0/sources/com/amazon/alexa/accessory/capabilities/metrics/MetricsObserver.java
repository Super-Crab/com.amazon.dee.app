package com.amazon.alexa.accessory.capabilities.metrics;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.protocol.Metrics;
import java.util.List;
/* loaded from: classes.dex */
public interface MetricsObserver {
    void onMetricsAvailable(AccessorySession accessorySession, List<AccessoryMetric> list, List<Metrics.MetricsEvent> list2);
}
