package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.MetricsTimer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.metrics.-$$Lambda$MetricsModule$yoMbxHV6co2jSOsiYnu9hvj5yHU  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$MetricsModule$yoMbxHV6co2jSOsiYnu9hvj5yHU implements MetricsTimerFactory {
    public static final /* synthetic */ $$Lambda$MetricsModule$yoMbxHV6co2jSOsiYnu9hvj5yHU INSTANCE = new $$Lambda$MetricsModule$yoMbxHV6co2jSOsiYnu9hvj5yHU();

    private /* synthetic */ $$Lambda$MetricsModule$yoMbxHV6co2jSOsiYnu9hvj5yHU() {
    }

    @Override // com.amazon.alexa.voice.metrics.MetricsTimerFactory
    public final MetricsTimer createInstance(String str, String str2) {
        return MetricsModule.lambda$provideMetricsTimerFactory$0(str, str2);
    }
}
