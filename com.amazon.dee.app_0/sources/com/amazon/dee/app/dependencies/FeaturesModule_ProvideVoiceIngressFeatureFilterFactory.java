package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.voice.model.VoiceService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class FeaturesModule_ProvideVoiceIngressFeatureFilterFactory implements Factory<FeatureFilter> {
    private final FeaturesModule module;
    private final Provider<VoiceService> voiceServiceProvider;

    public FeaturesModule_ProvideVoiceIngressFeatureFilterFactory(FeaturesModule featuresModule, Provider<VoiceService> provider) {
        this.module = featuresModule;
        this.voiceServiceProvider = provider;
    }

    public static FeaturesModule_ProvideVoiceIngressFeatureFilterFactory create(FeaturesModule featuresModule, Provider<VoiceService> provider) {
        return new FeaturesModule_ProvideVoiceIngressFeatureFilterFactory(featuresModule, provider);
    }

    public static FeatureFilter provideInstance(FeaturesModule featuresModule, Provider<VoiceService> provider) {
        return proxyProvideVoiceIngressFeatureFilter(featuresModule, DoubleCheck.lazy(provider));
    }

    public static FeatureFilter proxyProvideVoiceIngressFeatureFilter(FeaturesModule featuresModule, Lazy<VoiceService> lazy) {
        return (FeatureFilter) Preconditions.checkNotNull(featuresModule.provideVoiceIngressFeatureFilter(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureFilter mo10268get() {
        return provideInstance(this.module, this.voiceServiceProvider);
    }
}
