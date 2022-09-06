package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureConstraints;
import com.amazon.alexa.protocols.features.FeatureFilter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class FeaturesModule_ProvideFeatureConstraintsFactory implements Factory<FeatureConstraints> {
    private final Provider<EnvironmentService> environmentProvider;
    private final Provider<Set<FeatureFilter>> filtersProvider;
    private final FeaturesModule module;

    public FeaturesModule_ProvideFeatureConstraintsFactory(FeaturesModule featuresModule, Provider<Set<FeatureFilter>> provider, Provider<EnvironmentService> provider2) {
        this.module = featuresModule;
        this.filtersProvider = provider;
        this.environmentProvider = provider2;
    }

    public static FeaturesModule_ProvideFeatureConstraintsFactory create(FeaturesModule featuresModule, Provider<Set<FeatureFilter>> provider, Provider<EnvironmentService> provider2) {
        return new FeaturesModule_ProvideFeatureConstraintsFactory(featuresModule, provider, provider2);
    }

    public static FeatureConstraints provideInstance(FeaturesModule featuresModule, Provider<Set<FeatureFilter>> provider, Provider<EnvironmentService> provider2) {
        return proxyProvideFeatureConstraints(featuresModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static FeatureConstraints proxyProvideFeatureConstraints(FeaturesModule featuresModule, Set<FeatureFilter> set, EnvironmentService environmentService) {
        return (FeatureConstraints) Preconditions.checkNotNull(featuresModule.provideFeatureConstraints(set, environmentService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureConstraints mo10268get() {
        return provideInstance(this.module, this.filtersProvider, this.environmentProvider);
    }
}
