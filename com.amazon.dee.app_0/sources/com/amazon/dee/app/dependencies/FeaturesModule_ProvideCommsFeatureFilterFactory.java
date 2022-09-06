package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class FeaturesModule_ProvideCommsFeatureFilterFactory implements Factory<FeatureFilter> {
    private final Provider<AlexaCommsService> commsServiceProvider;
    private final FeaturesModule module;

    public FeaturesModule_ProvideCommsFeatureFilterFactory(FeaturesModule featuresModule, Provider<AlexaCommsService> provider) {
        this.module = featuresModule;
        this.commsServiceProvider = provider;
    }

    public static FeaturesModule_ProvideCommsFeatureFilterFactory create(FeaturesModule featuresModule, Provider<AlexaCommsService> provider) {
        return new FeaturesModule_ProvideCommsFeatureFilterFactory(featuresModule, provider);
    }

    public static FeatureFilter provideInstance(FeaturesModule featuresModule, Provider<AlexaCommsService> provider) {
        return proxyProvideCommsFeatureFilter(featuresModule, provider.mo10268get());
    }

    public static FeatureFilter proxyProvideCommsFeatureFilter(FeaturesModule featuresModule, AlexaCommsService alexaCommsService) {
        return (FeatureFilter) Preconditions.checkNotNull(featuresModule.provideCommsFeatureFilter(alexaCommsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureFilter mo10268get() {
        return provideInstance(this.module, this.commsServiceProvider);
    }
}
