package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
/* loaded from: classes.dex */
public interface MetricsSupplier {
    void registerObserver(MetricsObserver metricsObserver);

    void unRegisterObserver(MetricsObserver metricsObserver);
}
