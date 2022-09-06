package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.usermessage.UserMessageService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MainModule_ProvideMessageUserServiceFactory implements Factory<UserMessageService> {
    private final MainModule module;

    public MainModule_ProvideMessageUserServiceFactory(MainModule mainModule) {
        this.module = mainModule;
    }

    public static MainModule_ProvideMessageUserServiceFactory create(MainModule mainModule) {
        return new MainModule_ProvideMessageUserServiceFactory(mainModule);
    }

    public static UserMessageService provideInstance(MainModule mainModule) {
        return proxyProvideMessageUserService(mainModule);
    }

    public static UserMessageService proxyProvideMessageUserService(MainModule mainModule) {
        return (UserMessageService) Preconditions.checkNotNull(mainModule.provideMessageUserService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserMessageService mo10268get() {
        return provideInstance(this.module);
    }
}
