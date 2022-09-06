package com.amazon.dee.app.services.metrics;

import com.dee.app.metrics.MetricsTimer;
import java.util.Collections;
/* loaded from: classes12.dex */
public class HistogramMetricsEvent extends DefaultAlexaMetricsEvent implements MetricsTimer {
    private final long value;

    public HistogramMetricsEvent(String str, String str2, long j) {
        super(str, str2, Collections.emptyMap());
        this.value = j;
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public void finishTimer() {
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public void finishTimer(Long l) {
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public long getElapsedTime() {
        return this.value;
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public void pauseTimer() {
        throw new UnsupportedOperationException();
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public void resumeTimer() {
        throw new UnsupportedOperationException();
    }
}
