package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.UserIdentityMapper;
import com.amazon.alexa.protocols.features.FeatureConstraints;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideUserIdentityMapperFactory implements Factory<UserIdentityMapper> {
    private final Provider<FeatureConstraints> constraintsProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideUserIdentityMapperFactory(IdentityModule identityModule, Provider<FeatureConstraints> provider) {
        this.module = identityModule;
        this.constraintsProvider = provider;
    }

    public static IdentityModule_ProvideUserIdentityMapperFactory create(IdentityModule identityModule, Provider<FeatureConstraints> provider) {
        return new IdentityModule_ProvideUserIdentityMapperFactory(identityModule, provider);
    }

    public static UserIdentityMapper provideInstance(IdentityModule identityModule, Provider<FeatureConstraints> provider) {
        return proxyProvideUserIdentityMapper(identityModule, provider.mo10268get());
    }

    public static UserIdentityMapper proxyProvideUserIdentityMapper(IdentityModule identityModule, FeatureConstraints featureConstraints) {
        return (UserIdentityMapper) Preconditions.checkNotNull(identityModule.provideUserIdentityMapper(featureConstraints), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserIdentityMapper mo10268get() {
        return provideInstance(this.module, this.constraintsProvider);
    }
}
