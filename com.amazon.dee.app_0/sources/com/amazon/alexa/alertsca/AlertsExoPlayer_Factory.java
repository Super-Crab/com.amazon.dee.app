package com.amazon.alexa.alertsca;

import android.content.Context;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeMultiplyingAudioProcessor;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsExoPlayer_Factory implements Factory<AlertsExoPlayer> {
    private final Provider<AmplitudeMultiplyingAudioProcessor> amplitudeMultiplyingAudioProcessorProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<VolumeAdjuster> volumeAdjusterProvider;

    public AlertsExoPlayer_Factory(Provider<Context> provider, Provider<MetricsService> provider2, Provider<VolumeAdjuster> provider3, Provider<AmplitudeMultiplyingAudioProcessor> provider4) {
        this.contextProvider = provider;
        this.metricsServiceProvider = provider2;
        this.volumeAdjusterProvider = provider3;
        this.amplitudeMultiplyingAudioProcessorProvider = provider4;
    }

    public static AlertsExoPlayer_Factory create(Provider<Context> provider, Provider<MetricsService> provider2, Provider<VolumeAdjuster> provider3, Provider<AmplitudeMultiplyingAudioProcessor> provider4) {
        return new AlertsExoPlayer_Factory(provider, provider2, provider3, provider4);
    }

    public static AlertsExoPlayer newAlertsExoPlayer(Context context, MetricsService metricsService, VolumeAdjuster volumeAdjuster, AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor) {
        return new AlertsExoPlayer(context, metricsService, volumeAdjuster, amplitudeMultiplyingAudioProcessor);
    }

    public static AlertsExoPlayer provideInstance(Provider<Context> provider, Provider<MetricsService> provider2, Provider<VolumeAdjuster> provider3, Provider<AmplitudeMultiplyingAudioProcessor> provider4) {
        return new AlertsExoPlayer(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsExoPlayer mo10268get() {
        return provideInstance(this.contextProvider, this.metricsServiceProvider, this.volumeAdjusterProvider, this.amplitudeMultiplyingAudioProcessorProvider);
    }
}
