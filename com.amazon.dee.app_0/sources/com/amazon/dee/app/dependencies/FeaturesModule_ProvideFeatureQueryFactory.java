package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class FeaturesModule_ProvideFeatureQueryFactory implements Factory<FeatureQuery> {
    private final Provider<IdentityService> identityServiceProvider;
    private final FeaturesModule module;

    public FeaturesModule_ProvideFeatureQueryFactory(FeaturesModule featuresModule, Provider<IdentityService> provider) {
        this.module = featuresModule;
        this.identityServiceProvider = provider;
    }

    public static FeaturesModule_ProvideFeatureQueryFactory create(FeaturesModule featuresModule, Provider<IdentityService> provider) {
        return new FeaturesModule_ProvideFeatureQueryFactory(featuresModule, provider);
    }

    public static FeatureQuery provideInstance(FeaturesModule featuresModule, Provider<IdentityService> provider) {
        return proxyProvideFeatureQuery(featuresModule, DoubleCheck.lazy(provider));
    }

    public static FeatureQuery proxyProvideFeatureQuery(FeaturesModule featuresModule, Lazy<IdentityService> lazy) {
        return (FeatureQuery) Preconditions.checkNotNull(featuresModule.provideFeatureQuery(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureQuery mo10268get() {
        return provideInstance(this.module, this.identityServiceProvider);
    }
}
