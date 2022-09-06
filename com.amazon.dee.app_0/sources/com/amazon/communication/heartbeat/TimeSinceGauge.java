package com.amazon.communication.heartbeat;

import android.os.SystemClock;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricBuilder;
/* loaded from: classes12.dex */
public class TimeSinceGauge implements Gauge<Long> {
    public static final Builder BUILDER = new Builder();
    private long mT = SystemClock.elapsedRealtime();

    /* loaded from: classes12.dex */
    public static class Builder implements MetricBuilder<TimeSinceGauge> {
        @Override // com.codahale.metrics.MetricBuilder
        public boolean isInstance(Metric metric) {
            return metric instanceof TimeSinceGauge;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.codahale.metrics.MetricBuilder
        /* renamed from: newMetric */
        public TimeSinceGauge mo6799newMetric() {
            return new TimeSinceGauge();
        }
    }

    public void update() {
        this.mT = SystemClock.elapsedRealtime();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.codahale.metrics.Gauge
    /* renamed from: getValue */
    public Long mo6796getValue() {
        return Long.valueOf(SystemClock.elapsedRealtime() - this.mT);
    }

    public void update(long j) {
        this.mT = j;
    }
}
