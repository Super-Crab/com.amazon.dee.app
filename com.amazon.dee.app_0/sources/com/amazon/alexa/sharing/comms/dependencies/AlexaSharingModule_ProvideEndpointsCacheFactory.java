package com.amazon.alexa.sharing.comms.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.clouddrive.configuration.EndpointsCache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvideEndpointsCacheFactory implements Factory<EndpointsCache> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvideEndpointsCacheFactory(AlexaSharingModule alexaSharingModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3) {
        this.module = alexaSharingModule;
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
        this.eventBusProvider = provider3;
    }

    public static AlexaSharingModule_ProvideEndpointsCacheFactory create(AlexaSharingModule alexaSharingModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3) {
        return new AlexaSharingModule_ProvideEndpointsCacheFactory(alexaSharingModule, provider, provider2, provider3);
    }

    public static EndpointsCache provideInstance(AlexaSharingModule alexaSharingModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3) {
        return proxyProvideEndpointsCache(alexaSharingModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static EndpointsCache proxyProvideEndpointsCache(AlexaSharingModule alexaSharingModule, Context context, IdentityService identityService, EventBus eventBus) {
        return (EndpointsCache) Preconditions.checkNotNull(alexaSharingModule.provideEndpointsCache(context, identityService, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EndpointsCache mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceProvider, this.eventBusProvider);
    }
}
