package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.messaging.CryptoFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MessagingModule_ProvideCryptoFactoryFactory implements Factory<CryptoFactory> {
    private final MessagingModule module;

    public MessagingModule_ProvideCryptoFactoryFactory(MessagingModule messagingModule) {
        this.module = messagingModule;
    }

    public static MessagingModule_ProvideCryptoFactoryFactory create(MessagingModule messagingModule) {
        return new MessagingModule_ProvideCryptoFactoryFactory(messagingModule);
    }

    public static CryptoFactory provideInstance(MessagingModule messagingModule) {
        return proxyProvideCryptoFactory(messagingModule);
    }

    public static CryptoFactory proxyProvideCryptoFactory(MessagingModule messagingModule) {
        return (CryptoFactory) Preconditions.checkNotNull(messagingModule.provideCryptoFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CryptoFactory mo10268get() {
        return provideInstance(this.module);
    }
}
