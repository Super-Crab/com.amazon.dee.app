package com.amazon.dee.app.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.dee.app.ui.util.CacheClearOperations;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.HttpResponseWrapper;
import com.dee.app.data.DefaultElementLocalStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCacheClearOperationsFactory implements Factory<CacheClearOperations> {
    private final Provider<DefaultElementLocalStorage> dataCacheProvider;
    private final Provider<DefaultElementLocalStorage> dataStoreProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<Cache<HttpResponseWrapper>> httpCacheProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final ServiceModule module;
    private final Provider<TaskManager> taskManagerProvider;

    public ServiceModule_ProvideCacheClearOperationsFactory(ServiceModule serviceModule, Provider<TaskManager> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<Cache<HttpResponseWrapper>> provider4, Provider<DefaultElementLocalStorage> provider5, Provider<DefaultElementLocalStorage> provider6) {
        this.module = serviceModule;
        this.taskManagerProvider = provider;
        this.identityServiceProvider = provider2;
        this.eventBusProvider = provider3;
        this.httpCacheProvider = provider4;
        this.dataCacheProvider = provider5;
        this.dataStoreProvider = provider6;
    }

    public static ServiceModule_ProvideCacheClearOperationsFactory create(ServiceModule serviceModule, Provider<TaskManager> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<Cache<HttpResponseWrapper>> provider4, Provider<DefaultElementLocalStorage> provider5, Provider<DefaultElementLocalStorage> provider6) {
        return new ServiceModule_ProvideCacheClearOperationsFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static CacheClearOperations provideInstance(ServiceModule serviceModule, Provider<TaskManager> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<Cache<HttpResponseWrapper>> provider4, Provider<DefaultElementLocalStorage> provider5, Provider<DefaultElementLocalStorage> provider6) {
        return proxyProvideCacheClearOperations(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static CacheClearOperations proxyProvideCacheClearOperations(ServiceModule serviceModule, TaskManager taskManager, IdentityService identityService, EventBus eventBus, Cache<HttpResponseWrapper> cache, DefaultElementLocalStorage defaultElementLocalStorage, DefaultElementLocalStorage defaultElementLocalStorage2) {
        return (CacheClearOperations) Preconditions.checkNotNull(serviceModule.provideCacheClearOperations(taskManager, identityService, eventBus, cache, defaultElementLocalStorage, defaultElementLocalStorage2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CacheClearOperations mo10268get() {
        return provideInstance(this.module, this.taskManagerProvider, this.identityServiceProvider, this.eventBusProvider, this.httpCacheProvider, this.dataCacheProvider, this.dataStoreProvider);
    }
}
