package com.codahale.metrics;
/* loaded from: classes9.dex */
public abstract class DerivativeGauge<F, T> implements Gauge<T> {
    private final Gauge<F> base;

    protected DerivativeGauge(Gauge<F> gauge) {
        this.base = gauge;
    }

    @Override // com.codahale.metrics.Gauge
    /* renamed from: getValue */
    public T mo6796getValue() {
        return transform(this.base.mo6796getValue());
    }

    protected abstract T transform(F f);
}
