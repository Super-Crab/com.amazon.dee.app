package com.amazon.dee.app.dependencies;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.dee.app.services.messaging.MessageCrypto;
import com.amazon.dee.app.services.messaging.MessagingHandler;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessagingModule_ProvideMessagingHandlerFactory implements Factory<MessagingHandler> {
    private final Provider<Set<MessagingReceiver>> defaultReceiversProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MessageCrypto> messageCryptoProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final MessagingModule module;

    public MessagingModule_ProvideMessagingHandlerFactory(MessagingModule messagingModule, Provider<Mobilytics> provider, Provider<IdentityService> provider2, Provider<MessageCrypto> provider3, Provider<Set<MessagingReceiver>> provider4, Provider<FeatureServiceV2> provider5) {
        this.module = messagingModule;
        this.mobilyticsProvider = provider;
        this.identityServiceProvider = provider2;
        this.messageCryptoProvider = provider3;
        this.defaultReceiversProvider = provider4;
        this.featureServiceV2LazyProvider = provider5;
    }

    public static MessagingModule_ProvideMessagingHandlerFactory create(MessagingModule messagingModule, Provider<Mobilytics> provider, Provider<IdentityService> provider2, Provider<MessageCrypto> provider3, Provider<Set<MessagingReceiver>> provider4, Provider<FeatureServiceV2> provider5) {
        return new MessagingModule_ProvideMessagingHandlerFactory(messagingModule, provider, provider2, provider3, provider4, provider5);
    }

    public static MessagingHandler provideInstance(MessagingModule messagingModule, Provider<Mobilytics> provider, Provider<IdentityService> provider2, Provider<MessageCrypto> provider3, Provider<Set<MessagingReceiver>> provider4, Provider<FeatureServiceV2> provider5) {
        return proxyProvideMessagingHandler(messagingModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), DoubleCheck.lazy(provider5));
    }

    public static MessagingHandler proxyProvideMessagingHandler(MessagingModule messagingModule, Lazy<Mobilytics> lazy, IdentityService identityService, MessageCrypto messageCrypto, Set<MessagingReceiver> set, Lazy<FeatureServiceV2> lazy2) {
        return (MessagingHandler) Preconditions.checkNotNull(messagingModule.provideMessagingHandler(lazy, identityService, messageCrypto, set, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingHandler mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider, this.identityServiceProvider, this.messageCryptoProvider, this.defaultReceiversProvider, this.featureServiceV2LazyProvider);
    }
}
