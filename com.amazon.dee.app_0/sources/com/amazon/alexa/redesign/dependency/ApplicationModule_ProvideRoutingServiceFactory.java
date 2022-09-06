package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.routing.api.RoutingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideRoutingServiceFactory implements Factory<RoutingService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideRoutingServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideRoutingServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideRoutingServiceFactory(applicationModule);
    }

    public static RoutingService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideRoutingService(applicationModule);
    }

    public static RoutingService proxyProvideRoutingService(ApplicationModule applicationModule) {
        return (RoutingService) Preconditions.checkNotNull(applicationModule.provideRoutingService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingService mo10268get() {
        return provideInstance(this.module);
    }
}
