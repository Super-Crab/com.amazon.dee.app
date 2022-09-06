package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.MetricsTimer;
@FunctionalInterface
/* loaded from: classes11.dex */
public interface MetricsTimerFactory {
    MetricsTimer createInstance(String str, String str2);
}
