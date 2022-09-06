package com.amazon.deecomms.core;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.deecomms.conversation.ConversationService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesConversationServiceFactory implements Factory<ConversationService> {
    private final Provider<String> deviceNameTemplateProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesConversationServiceFactory(ApplicationModule applicationModule, Provider<IdentityService> provider, Provider<String> provider2) {
        this.module = applicationModule;
        this.identityServiceProvider = provider;
        this.deviceNameTemplateProvider = provider2;
    }

    public static ApplicationModule_ProvidesConversationServiceFactory create(ApplicationModule applicationModule, Provider<IdentityService> provider, Provider<String> provider2) {
        return new ApplicationModule_ProvidesConversationServiceFactory(applicationModule, provider, provider2);
    }

    public static ConversationService provideInstance(ApplicationModule applicationModule, Provider<IdentityService> provider, Provider<String> provider2) {
        return (ConversationService) Preconditions.checkNotNull(applicationModule.providesConversationService(DoubleCheck.lazy(provider), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ConversationService proxyProvidesConversationService(ApplicationModule applicationModule, Lazy<IdentityService> lazy, String str) {
        return (ConversationService) Preconditions.checkNotNull(applicationModule.providesConversationService(lazy, str), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConversationService mo10268get() {
        return provideInstance(this.module, this.identityServiceProvider, this.deviceNameTemplateProvider);
    }
}
