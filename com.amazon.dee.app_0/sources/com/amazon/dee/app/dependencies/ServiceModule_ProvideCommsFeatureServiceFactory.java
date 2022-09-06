package com.amazon.dee.app.dependencies;

import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCommsFeatureServiceFactory implements Factory<CommsDynamicFeatureService> {
    private final Provider<AlexaCommsService> commsServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideCommsFeatureServiceFactory(ServiceModule serviceModule, Provider<AlexaCommsService> provider) {
        this.module = serviceModule;
        this.commsServiceProvider = provider;
    }

    public static ServiceModule_ProvideCommsFeatureServiceFactory create(ServiceModule serviceModule, Provider<AlexaCommsService> provider) {
        return new ServiceModule_ProvideCommsFeatureServiceFactory(serviceModule, provider);
    }

    public static CommsDynamicFeatureService provideInstance(ServiceModule serviceModule, Provider<AlexaCommsService> provider) {
        return proxyProvideCommsFeatureService(serviceModule, provider.mo10268get());
    }

    public static CommsDynamicFeatureService proxyProvideCommsFeatureService(ServiceModule serviceModule, AlexaCommsService alexaCommsService) {
        return (CommsDynamicFeatureService) Preconditions.checkNotNull(serviceModule.provideCommsFeatureService(alexaCommsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsDynamicFeatureService mo10268get() {
        return provideInstance(this.module, this.commsServiceProvider);
    }
}
