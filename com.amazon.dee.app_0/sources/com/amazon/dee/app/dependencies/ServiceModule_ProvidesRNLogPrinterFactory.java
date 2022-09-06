package com.amazon.dee.app.dependencies;

import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.ui.main.RNLogPrinter;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvidesRNLogPrinterFactory implements Factory<RNLogPrinter> {
    private final ServiceModule module;
    private final Provider<RoutingService> routingServiceProvider;

    public ServiceModule_ProvidesRNLogPrinterFactory(ServiceModule serviceModule, Provider<RoutingService> provider) {
        this.module = serviceModule;
        this.routingServiceProvider = provider;
    }

    public static ServiceModule_ProvidesRNLogPrinterFactory create(ServiceModule serviceModule, Provider<RoutingService> provider) {
        return new ServiceModule_ProvidesRNLogPrinterFactory(serviceModule, provider);
    }

    public static RNLogPrinter provideInstance(ServiceModule serviceModule, Provider<RoutingService> provider) {
        return proxyProvidesRNLogPrinter(serviceModule, DoubleCheck.lazy(provider));
    }

    public static RNLogPrinter proxyProvidesRNLogPrinter(ServiceModule serviceModule, Lazy<RoutingService> lazy) {
        return (RNLogPrinter) Preconditions.checkNotNull(serviceModule.providesRNLogPrinter(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RNLogPrinter mo10268get() {
        return provideInstance(this.module, this.routingServiceProvider);
    }
}
