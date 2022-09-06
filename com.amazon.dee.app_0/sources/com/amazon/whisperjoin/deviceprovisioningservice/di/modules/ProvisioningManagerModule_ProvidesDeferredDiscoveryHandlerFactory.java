package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningManagerModule_ProvidesDeferredDiscoveryHandlerFactory implements Factory<DeferredDiscoveryHandler> {
    private final Provider<Clock> clockProvider;
    private final ProvisioningManagerModule module;

    public ProvisioningManagerModule_ProvidesDeferredDiscoveryHandlerFactory(ProvisioningManagerModule provisioningManagerModule, Provider<Clock> provider) {
        this.module = provisioningManagerModule;
        this.clockProvider = provider;
    }

    public static ProvisioningManagerModule_ProvidesDeferredDiscoveryHandlerFactory create(ProvisioningManagerModule provisioningManagerModule, Provider<Clock> provider) {
        return new ProvisioningManagerModule_ProvidesDeferredDiscoveryHandlerFactory(provisioningManagerModule, provider);
    }

    public static DeferredDiscoveryHandler provideInstance(ProvisioningManagerModule provisioningManagerModule, Provider<Clock> provider) {
        return proxyProvidesDeferredDiscoveryHandler(provisioningManagerModule, provider.mo10268get());
    }

    public static DeferredDiscoveryHandler proxyProvidesDeferredDiscoveryHandler(ProvisioningManagerModule provisioningManagerModule, Clock clock) {
        return (DeferredDiscoveryHandler) Preconditions.checkNotNull(provisioningManagerModule.providesDeferredDiscoveryHandler(clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeferredDiscoveryHandler mo10268get() {
        return provideInstance(this.module, this.clockProvider);
    }
}
