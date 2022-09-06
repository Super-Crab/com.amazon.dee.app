package com.amazon.dee.app.dependencies;

import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.amazon.deecomms.core.decoupling.AlexaCommsServiceWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCommsServiceFactory implements Factory<AlexaCommsService> {
    private final Provider<AlexaCommsServiceWrapper> alexaCommsServiceWrapperProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideCommsServiceFactory(ServiceModule serviceModule, Provider<AlexaCommsServiceWrapper> provider) {
        this.module = serviceModule;
        this.alexaCommsServiceWrapperProvider = provider;
    }

    public static ServiceModule_ProvideCommsServiceFactory create(ServiceModule serviceModule, Provider<AlexaCommsServiceWrapper> provider) {
        return new ServiceModule_ProvideCommsServiceFactory(serviceModule, provider);
    }

    public static AlexaCommsService provideInstance(ServiceModule serviceModule, Provider<AlexaCommsServiceWrapper> provider) {
        return proxyProvideCommsService(serviceModule, provider.mo10268get());
    }

    public static AlexaCommsService proxyProvideCommsService(ServiceModule serviceModule, AlexaCommsServiceWrapper alexaCommsServiceWrapper) {
        return (AlexaCommsService) Preconditions.checkNotNull(serviceModule.provideCommsService(alexaCommsServiceWrapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsService mo10268get() {
        return provideInstance(this.module, this.alexaCommsServiceWrapperProvider);
    }
}
