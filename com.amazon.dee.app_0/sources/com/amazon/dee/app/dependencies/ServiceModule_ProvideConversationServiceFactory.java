package com.amazon.dee.app.dependencies;

import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideConversationServiceFactory implements Factory<ConversationService> {
    private final Provider<AlexaCommsService> commsServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideConversationServiceFactory(ServiceModule serviceModule, Provider<AlexaCommsService> provider) {
        this.module = serviceModule;
        this.commsServiceProvider = provider;
    }

    public static ServiceModule_ProvideConversationServiceFactory create(ServiceModule serviceModule, Provider<AlexaCommsService> provider) {
        return new ServiceModule_ProvideConversationServiceFactory(serviceModule, provider);
    }

    public static ConversationService provideInstance(ServiceModule serviceModule, Provider<AlexaCommsService> provider) {
        return proxyProvideConversationService(serviceModule, provider.mo10268get());
    }

    public static ConversationService proxyProvideConversationService(ServiceModule serviceModule, AlexaCommsService alexaCommsService) {
        return (ConversationService) Preconditions.checkNotNull(serviceModule.provideConversationService(alexaCommsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConversationService mo10268get() {
        return provideInstance(this.module, this.commsServiceProvider);
    }
}
