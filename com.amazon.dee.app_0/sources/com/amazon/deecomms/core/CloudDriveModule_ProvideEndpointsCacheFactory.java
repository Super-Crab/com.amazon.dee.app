package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
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

    public CloudDriveModule_ProvideEndpointsCacheFactory(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3) {
        this.module = cloudDriveModule;
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
        this.eventBusProvider = provider3;
    }

    public static CloudDriveModule_ProvideEndpointsCacheFactory create(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3) {
        return new CloudDriveModule_ProvideEndpointsCacheFactory(cloudDriveModule, provider, provider2, provider3);
    }

    public static EndpointsCache provideInstance(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3) {
        return (EndpointsCache) Preconditions.checkNotNull(cloudDriveModule.provideEndpointsCache(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static EndpointsCache proxyProvideEndpointsCache(CloudDriveModule cloudDriveModule, Context context, IdentityService identityService, EventBus eventBus) {
        return (EndpointsCache) Preconditions.checkNotNull(cloudDriveModule.provideEndpointsCache(context, identityService, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EndpointsCache mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceProvider, this.eventBusProvider);
    }
}
