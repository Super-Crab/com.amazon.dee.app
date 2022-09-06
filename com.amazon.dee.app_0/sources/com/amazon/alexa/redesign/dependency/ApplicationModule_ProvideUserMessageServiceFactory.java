package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.protocols.usermessage.UserMessageService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideUserMessageServiceFactory implements Factory<UserMessageService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideUserMessageServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideUserMessageServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideUserMessageServiceFactory(applicationModule);
    }

    public static UserMessageService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideUserMessageService(applicationModule);
    }

    public static UserMessageService proxyProvideUserMessageService(ApplicationModule applicationModule) {
        return (UserMessageService) Preconditions.checkNotNull(applicationModule.provideUserMessageService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserMessageService mo10268get() {
        return provideInstance(this.module);
    }
}
