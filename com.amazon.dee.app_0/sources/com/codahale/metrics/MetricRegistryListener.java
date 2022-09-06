package com.codahale.metrics;

import java.util.EventListener;
/* loaded from: classes9.dex */
public interface MetricRegistryListener extends EventListener {

    /* loaded from: classes9.dex */
    public static abstract class Base implements MetricRegistryListener {
        @Override // com.codahale.metrics.MetricRegistryListener
        public void onCounterAdded(String str, Counter counter) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onCounterRemoved(String str) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onGaugeAdded(String str, Gauge<?> gauge) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onGaugeRemoved(String str) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onHistogramAdded(String str, Histogram histogram) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onHistogramRemoved(String str) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onMeterAdded(String str, Meter meter) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onMeterRemoved(String str) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onTimerAdded(String str, Timer timer) {
        }

        @Override // com.codahale.metrics.MetricRegistryListener
        public void onTimerRemoved(String str) {
        }
    }

    void onCounterAdded(String str, Counter counter);

    void onCounterRemoved(String str);

    void onGaugeAdded(String str, Gauge<?> gauge);

    void onGaugeRemoved(String str);

    void onHistogramAdded(String str, Histogram histogram);

    void onHistogramRemoved(String str);

    void onMeterAdded(String str, Meter meter);

    void onMeterRemoved(String str);

    void onTimerAdded(String str, Timer timer);

    void onTimerRemoved(String str);
}
