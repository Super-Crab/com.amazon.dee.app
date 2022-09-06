package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.metrics.service.MetricsTimer;
import com.amazon.alexa.voice.metrics.service.VoxDefaultMetricsService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class MetricsModule {
    private MetricsModule() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MetricsTimer lambda$provideMetricsTimerFactory$0(String str, String str2) {
        return new VoiceMetricsTimer(str, str2, null);
    }

    @Provides
    @Singleton
    public static MetricsService provideMetricsService(Mobilytics mobilytics) {
        return new VoxDefaultMetricsService(mobilytics);
    }

    @Provides
    @Singleton
    public static MetricsTimerFactory provideMetricsTimerFactory() {
        return $$Lambda$MetricsModule$yoMbxHV6co2jSOsiYnu9hvj5yHU.INSTANCE;
    }

    @Provides
    @Singleton
    public static VoxMetricEventProcessingService provideVoxMetricEventProcessingService(MetricsService metricsService) {
        return new VoxMetricEventProcessingService(metricsService);
    }
}
