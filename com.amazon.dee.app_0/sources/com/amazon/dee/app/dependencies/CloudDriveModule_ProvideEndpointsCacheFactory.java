package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.clouddrive.configuration.EndpointsCache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideEndpointsCacheFactory implements Factory<EndpointsCache> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final CloudDriveModule module;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public CloudDriveModule_ProvideEndpointsCacheFactory(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<PersistentStorage.Factory> provider4) {
        this.module = cloudDriveModule;
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
        this.eventBusProvider = provider3;
        this.storageFactoryProvider = provider4;
    }

    public static CloudDriveModule_ProvideEndpointsCacheFactory create(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<PersistentStorage.Factory> provider4) {
        return new CloudDriveModule_ProvideEndpointsCacheFactory(cloudDriveModule, provider, provider2, provider3, provider4);
    }

    public static EndpointsCache provideInstance(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<PersistentStorage.Factory> provider4) {
        return proxyProvideEndpointsCache(cloudDriveModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static EndpointsCache proxyProvideEndpointsCache(CloudDriveModule cloudDriveModule, Context context, IdentityService identityService, EventBus eventBus, PersistentStorage.Factory factory) {
        return (EndpointsCache) Preconditions.checkNotNull(cloudDriveModule.provideEndpointsCache(context, identityService, eventBus, factory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EndpointsCache mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceProvider, this.eventBusProvider, this.storageFactoryProvider);
    }
}
