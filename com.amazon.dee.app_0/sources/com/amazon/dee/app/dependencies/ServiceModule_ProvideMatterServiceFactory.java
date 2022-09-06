package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.matter.service.MatterService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideMatterServiceFactory implements Factory<MatterService> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideMatterServiceFactory(ServiceModule serviceModule, Provider<EventBus> provider, Provider<Context> provider2) {
        this.module = serviceModule;
        this.eventBusProvider = provider;
        this.contextProvider = provider2;
    }

    public static ServiceModule_ProvideMatterServiceFactory create(ServiceModule serviceModule, Provider<EventBus> provider, Provider<Context> provider2) {
        return new ServiceModule_ProvideMatterServiceFactory(serviceModule, provider, provider2);
    }

    public static MatterService provideInstance(ServiceModule serviceModule, Provider<EventBus> provider, Provider<Context> provider2) {
        return proxyProvideMatterService(serviceModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MatterService proxyProvideMatterService(ServiceModule serviceModule, EventBus eventBus, Context context) {
        return (MatterService) Preconditions.checkNotNull(serviceModule.provideMatterService(eventBus, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MatterService mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.contextProvider);
    }
}
