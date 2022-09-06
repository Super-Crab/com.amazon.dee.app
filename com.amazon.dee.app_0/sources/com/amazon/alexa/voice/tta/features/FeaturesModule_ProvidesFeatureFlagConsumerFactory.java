package com.amazon.alexa.voice.tta.features;

import android.content.Context;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FeaturesModule_ProvidesFeatureFlagConsumerFactory implements Factory<FeatureFlagConsumer> {
    private final Provider<Context> contextProvider;
    private final FeaturesModule module;

    public FeaturesModule_ProvidesFeatureFlagConsumerFactory(FeaturesModule featuresModule, Provider<Context> provider) {
        this.module = featuresModule;
        this.contextProvider = provider;
    }

    public static FeaturesModule_ProvidesFeatureFlagConsumerFactory create(FeaturesModule featuresModule, Provider<Context> provider) {
        return new FeaturesModule_ProvidesFeatureFlagConsumerFactory(featuresModule, provider);
    }

    public static FeatureFlagConsumer provideInstance(FeaturesModule featuresModule, Provider<Context> provider) {
        return proxyProvidesFeatureFlagConsumer(featuresModule, provider.mo10268get());
    }

    public static FeatureFlagConsumer proxyProvidesFeatureFlagConsumer(FeaturesModule featuresModule, Context context) {
        return (FeatureFlagConsumer) Preconditions.checkNotNull(featuresModule.providesFeatureFlagConsumer(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureFlagConsumer mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
