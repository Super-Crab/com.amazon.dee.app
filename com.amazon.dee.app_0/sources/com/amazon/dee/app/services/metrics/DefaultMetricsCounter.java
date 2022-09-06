package com.amazon.dee.app.services.metrics;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.dee.app.metrics.MetricsCounter;
import java.util.Map;
/* loaded from: classes12.dex */
public class DefaultMetricsCounter extends DefaultAlexaMetricsEvent implements MetricsCounter {
    double currentCount;

    public DefaultMetricsCounter(String str, String str2, Map<String, Object> map) {
        super(str, str2, map);
        init();
    }

    private void init() {
        this.currentCount = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // com.dee.app.metrics.MetricsCounter
    public double getCount() {
        return this.currentCount;
    }

    @Override // com.dee.app.metrics.MetricsCounter
    public void incrementCounter() {
        this.currentCount += 1.0d;
    }

    @Override // com.dee.app.metrics.MetricsCounter
    public void incrementCounterByValue(double d) {
        this.currentCount += d;
    }

    @Override // com.dee.app.metrics.MetricsCounter
    public void restartCounter() {
        this.currentCount = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }
}
