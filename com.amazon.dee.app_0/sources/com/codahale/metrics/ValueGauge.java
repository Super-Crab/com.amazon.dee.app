package com.codahale.metrics;
/* loaded from: classes9.dex */
public class ValueGauge<T> implements Gauge<T> {
    private T mValue;
    public static final Builder<Boolean> BOOLEAN = new Builder<>();
    public static final Builder<Long> LONG = new Builder<>();
    public static final Builder<String> STRING = new Builder<>();
    public static final Builder<Integer> INTEGER = new Builder<>();

    /* loaded from: classes9.dex */
    public static class Builder<T> implements MetricBuilder<ValueGauge<T>> {
        @Override // com.codahale.metrics.MetricBuilder
        public boolean isInstance(Metric metric) {
            return metric instanceof ValueGauge;
        }

        @Override // com.codahale.metrics.MetricBuilder
        /* renamed from: newMetric  reason: collision with other method in class */
        public ValueGauge<T> mo6799newMetric() {
            return new ValueGauge<>();
        }
    }

    @Override // com.codahale.metrics.Gauge
    /* renamed from: getValue */
    public T mo6796getValue() {
        return this.mValue;
    }

    public void setValue(T t) {
        this.mValue = t;
    }
}
