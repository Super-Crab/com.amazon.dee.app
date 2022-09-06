package com.amazon.dee.app.dependencies;

import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.core.decoupling.AlexaCommsServiceWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCommsServiceV2Factory implements Factory<CommsServiceV2> {
    private final Provider<AlexaCommsServiceWrapper> alexaCommsServiceWrapperProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideCommsServiceV2Factory(ServiceModule serviceModule, Provider<AlexaCommsServiceWrapper> provider) {
        this.module = serviceModule;
        this.alexaCommsServiceWrapperProvider = provider;
    }

    public static ServiceModule_ProvideCommsServiceV2Factory create(ServiceModule serviceModule, Provider<AlexaCommsServiceWrapper> provider) {
        return new ServiceModule_ProvideCommsServiceV2Factory(serviceModule, provider);
    }

    public static CommsServiceV2 provideInstance(ServiceModule serviceModule, Provider<AlexaCommsServiceWrapper> provider) {
        return proxyProvideCommsServiceV2(serviceModule, provider.mo10268get());
    }

    public static CommsServiceV2 proxyProvideCommsServiceV2(ServiceModule serviceModule, AlexaCommsServiceWrapper alexaCommsServiceWrapper) {
        return (CommsServiceV2) Preconditions.checkNotNull(serviceModule.provideCommsServiceV2(alexaCommsServiceWrapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsServiceV2 mo10268get() {
        return provideInstance(this.module, this.alexaCommsServiceWrapperProvider);
    }
}
