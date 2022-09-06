package com.amazon.alexa.client.metrics.core;

import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.Map;
/* loaded from: classes6.dex */
public class DefaultMetricsCounter extends DefaultAlexaMetricsEvent implements MetricsCounter {
    private double currentCount;

    public DefaultMetricsCounter(String str, String str2, Map<String, Object> map) {
        this(str, str2, null, map);
    }

    private void init() {
        this.currentCount = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsCounter
    public double getCount() {
        return this.currentCount;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsCounter
    public void incrementCounter() {
        this.currentCount += 1.0d;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsCounter
    public void incrementCounterByValue(double d) {
        this.currentCount += d;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsCounter
    public void restartCounter() {
        this.currentCount = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    public DefaultMetricsCounter(String str, String str2, @Nullable String str3, Map<String, Object> map) {
        super(str, str2, str3, map);
        init();
    }
}
