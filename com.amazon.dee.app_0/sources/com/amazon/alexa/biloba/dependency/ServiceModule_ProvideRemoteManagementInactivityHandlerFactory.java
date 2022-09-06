package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.dashboard.RemoteManagementInactivityHandler;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvideRemoteManagementInactivityHandlerFactory implements Factory<RemoteManagementInactivityHandler> {
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MainActivityLifecycleObserverRegistrar> mainActivityLifecycleObserverRegistrarProvider;
    private final ServiceModule module;
    private final Provider<RoutingService> routingServiceProvider;

    public ServiceModule_ProvideRemoteManagementInactivityHandlerFactory(ServiceModule serviceModule, Provider<EventBus> provider, Provider<IdentityService> provider2, Provider<MainActivityLifecycleObserverRegistrar> provider3, Provider<RoutingService> provider4, Provider<CareActorsStore> provider5) {
        this.module = serviceModule;
        this.eventBusProvider = provider;
        this.identityServiceProvider = provider2;
        this.mainActivityLifecycleObserverRegistrarProvider = provider3;
        this.routingServiceProvider = provider4;
        this.careActorsStoreProvider = provider5;
    }

    public static ServiceModule_ProvideRemoteManagementInactivityHandlerFactory create(ServiceModule serviceModule, Provider<EventBus> provider, Provider<IdentityService> provider2, Provider<MainActivityLifecycleObserverRegistrar> provider3, Provider<RoutingService> provider4, Provider<CareActorsStore> provider5) {
        return new ServiceModule_ProvideRemoteManagementInactivityHandlerFactory(serviceModule, provider, provider2, provider3, provider4, provider5);
    }

    public static RemoteManagementInactivityHandler provideInstance(ServiceModule serviceModule, Provider<EventBus> provider, Provider<IdentityService> provider2, Provider<MainActivityLifecycleObserverRegistrar> provider3, Provider<RoutingService> provider4, Provider<CareActorsStore> provider5) {
        return proxyProvideRemoteManagementInactivityHandler(serviceModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static RemoteManagementInactivityHandler proxyProvideRemoteManagementInactivityHandler(ServiceModule serviceModule, Lazy<EventBus> lazy, Lazy<IdentityService> lazy2, Lazy<MainActivityLifecycleObserverRegistrar> lazy3, Lazy<RoutingService> lazy4, Lazy<CareActorsStore> lazy5) {
        return (RemoteManagementInactivityHandler) Preconditions.checkNotNull(serviceModule.provideRemoteManagementInactivityHandler(lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RemoteManagementInactivityHandler mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.identityServiceProvider, this.mainActivityLifecycleObserverRegistrarProvider, this.routingServiceProvider, this.careActorsStoreProvider);
    }
}
