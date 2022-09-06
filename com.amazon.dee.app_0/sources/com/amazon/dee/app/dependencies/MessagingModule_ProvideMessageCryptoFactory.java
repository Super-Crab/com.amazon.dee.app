package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.messaging.CryptoFactory;
import com.amazon.dee.app.services.messaging.MessageCrypto;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessagingModule_ProvideMessageCryptoFactory implements Factory<MessageCrypto> {
    private final Provider<Context> contextProvider;
    private final Provider<CryptoFactory> cryptoFactoryProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final MessagingModule module;
    private final Provider<PersistentStorage.Factory> persistentStorageFactoryProvider;

    public MessagingModule_ProvideMessageCryptoFactory(MessagingModule messagingModule, Provider<Mobilytics> provider, Provider<PersistentStorage.Factory> provider2, Provider<Context> provider3, Provider<CryptoFactory> provider4) {
        this.module = messagingModule;
        this.mobilyticsProvider = provider;
        this.persistentStorageFactoryProvider = provider2;
        this.contextProvider = provider3;
        this.cryptoFactoryProvider = provider4;
    }

    public static MessagingModule_ProvideMessageCryptoFactory create(MessagingModule messagingModule, Provider<Mobilytics> provider, Provider<PersistentStorage.Factory> provider2, Provider<Context> provider3, Provider<CryptoFactory> provider4) {
        return new MessagingModule_ProvideMessageCryptoFactory(messagingModule, provider, provider2, provider3, provider4);
    }

    public static MessageCrypto provideInstance(MessagingModule messagingModule, Provider<Mobilytics> provider, Provider<PersistentStorage.Factory> provider2, Provider<Context> provider3, Provider<CryptoFactory> provider4) {
        return proxyProvideMessageCrypto(messagingModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static MessageCrypto proxyProvideMessageCrypto(MessagingModule messagingModule, Lazy<Mobilytics> lazy, PersistentStorage.Factory factory, Context context, CryptoFactory cryptoFactory) {
        return (MessageCrypto) Preconditions.checkNotNull(messagingModule.provideMessageCrypto(lazy, factory, context, cryptoFactory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessageCrypto mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider, this.persistentStorageFactoryProvider, this.contextProvider, this.cryptoFactoryProvider);
    }
}
