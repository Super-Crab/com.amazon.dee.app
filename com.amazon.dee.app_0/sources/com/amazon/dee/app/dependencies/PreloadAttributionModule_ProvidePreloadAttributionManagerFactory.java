package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class PreloadAttributionModule_ProvidePreloadAttributionManagerFactory implements Factory<PreloadAttributionManager> {
    private final Provider<Context> contextProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final PreloadAttributionModule module;

    public PreloadAttributionModule_ProvidePreloadAttributionManagerFactory(PreloadAttributionModule preloadAttributionModule, Provider<Context> provider, Provider<FeatureServiceV2> provider2) {
        this.module = preloadAttributionModule;
        this.contextProvider = provider;
        this.featureServiceV2Provider = provider2;
    }

    public static PreloadAttributionModule_ProvidePreloadAttributionManagerFactory create(PreloadAttributionModule preloadAttributionModule, Provider<Context> provider, Provider<FeatureServiceV2> provider2) {
        return new PreloadAttributionModule_ProvidePreloadAttributionManagerFactory(preloadAttributionModule, provider, provider2);
    }

    public static PreloadAttributionManager provideInstance(PreloadAttributionModule preloadAttributionModule, Provider<Context> provider, Provider<FeatureServiceV2> provider2) {
        return proxyProvidePreloadAttributionManager(preloadAttributionModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static PreloadAttributionManager proxyProvidePreloadAttributionManager(PreloadAttributionModule preloadAttributionModule, Context context, FeatureServiceV2 featureServiceV2) {
        return (PreloadAttributionManager) Preconditions.checkNotNull(preloadAttributionModule.providePreloadAttributionManager(context, featureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PreloadAttributionManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.featureServiceV2Provider);
    }
}
