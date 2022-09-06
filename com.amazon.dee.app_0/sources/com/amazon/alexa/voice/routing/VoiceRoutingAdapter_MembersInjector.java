package com.amazon.alexa.voice.routing;

import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceRoutingAdapter_MembersInjector implements MembersInjector<VoiceRoutingAdapter> {
    private final Provider<MetricsBridge> metricsBridgeProvider;
    private final Provider<TypeToAlexaFeatureEnabler> ttaFeatureEnablerProvider;
    private final Provider<VoiceService> voiceServiceProvider;
    private final Provider<VoxMetricEventProcessingService> voxMetricEventProcessingServiceProvider;

    public VoiceRoutingAdapter_MembersInjector(Provider<VoxMetricEventProcessingService> provider, Provider<VoiceService> provider2, Provider<MetricsBridge> provider3, Provider<TypeToAlexaFeatureEnabler> provider4) {
        this.voxMetricEventProcessingServiceProvider = provider;
        this.voiceServiceProvider = provider2;
        this.metricsBridgeProvider = provider3;
        this.ttaFeatureEnablerProvider = provider4;
    }

    public static MembersInjector<VoiceRoutingAdapter> create(Provider<VoxMetricEventProcessingService> provider, Provider<VoiceService> provider2, Provider<MetricsBridge> provider3, Provider<TypeToAlexaFeatureEnabler> provider4) {
        return new VoiceRoutingAdapter_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectMetricsBridge(VoiceRoutingAdapter voiceRoutingAdapter, MetricsBridge metricsBridge) {
        voiceRoutingAdapter.metricsBridge = metricsBridge;
    }

    public static void injectTtaFeatureEnabler(VoiceRoutingAdapter voiceRoutingAdapter, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler) {
        voiceRoutingAdapter.ttaFeatureEnabler = typeToAlexaFeatureEnabler;
    }

    public static void injectVoiceService(VoiceRoutingAdapter voiceRoutingAdapter, VoiceService voiceService) {
        voiceRoutingAdapter.voiceService = voiceService;
    }

    public static void injectVoxMetricEventProcessingService(VoiceRoutingAdapter voiceRoutingAdapter, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        voiceRoutingAdapter.voxMetricEventProcessingService = voxMetricEventProcessingService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoiceRoutingAdapter voiceRoutingAdapter) {
        injectVoxMetricEventProcessingService(voiceRoutingAdapter, this.voxMetricEventProcessingServiceProvider.mo10268get());
        injectVoiceService(voiceRoutingAdapter, this.voiceServiceProvider.mo10268get());
        injectMetricsBridge(voiceRoutingAdapter, this.metricsBridgeProvider.mo10268get());
        injectTtaFeatureEnabler(voiceRoutingAdapter, this.ttaFeatureEnablerProvider.mo10268get());
    }
}
