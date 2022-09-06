package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.useragent.UserAgentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideUserAgentServiceFactory implements Factory<UserAgentService> {
    private final ServiceModule module;

    public ServiceModule_ProvideUserAgentServiceFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideUserAgentServiceFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideUserAgentServiceFactory(serviceModule);
    }

    public static UserAgentService provideInstance(ServiceModule serviceModule) {
        return proxyProvideUserAgentService(serviceModule);
    }

    public static UserAgentService proxyProvideUserAgentService(ServiceModule serviceModule) {
        return (UserAgentService) Preconditions.checkNotNull(serviceModule.provideUserAgentService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserAgentService mo10268get() {
        return provideInstance(this.module);
    }
}
