package com.amazon.commscore.commsidentity.implementation;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsCoreIdentityViewModel_Factory implements Factory<CommsCoreIdentityViewModel> {
    private final Provider<CommsIdentityProvider> commsIdentityProvider;
    private final Provider<IdentityService> identityServiceLazyProvider;

    public CommsCoreIdentityViewModel_Factory(Provider<IdentityService> provider, Provider<CommsIdentityProvider> provider2) {
        this.identityServiceLazyProvider = provider;
        this.commsIdentityProvider = provider2;
    }

    public static CommsCoreIdentityViewModel_Factory create(Provider<IdentityService> provider, Provider<CommsIdentityProvider> provider2) {
        return new CommsCoreIdentityViewModel_Factory(provider, provider2);
    }

    public static CommsCoreIdentityViewModel newCommsCoreIdentityViewModel(Lazy<IdentityService> lazy, CommsIdentityProvider commsIdentityProvider) {
        return new CommsCoreIdentityViewModel(lazy, commsIdentityProvider);
    }

    public static CommsCoreIdentityViewModel provideInstance(Provider<IdentityService> provider, Provider<CommsIdentityProvider> provider2) {
        return new CommsCoreIdentityViewModel(DoubleCheck.lazy(provider), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsCoreIdentityViewModel mo10268get() {
        return provideInstance(this.identityServiceLazyProvider, this.commsIdentityProvider);
    }
}
