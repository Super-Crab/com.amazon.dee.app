package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.deecomms.api.CommsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsModule_ProvideConversationMessagingReceiverFactory implements Factory<MessagingReceiver> {
    private final Provider<CommsServiceV2> commsServiceV2Provider;
    private final CommsModule module;

    public CommsModule_ProvideConversationMessagingReceiverFactory(CommsModule commsModule, Provider<CommsServiceV2> provider) {
        this.module = commsModule;
        this.commsServiceV2Provider = provider;
    }

    public static CommsModule_ProvideConversationMessagingReceiverFactory create(CommsModule commsModule, Provider<CommsServiceV2> provider) {
        return new CommsModule_ProvideConversationMessagingReceiverFactory(commsModule, provider);
    }

    public static MessagingReceiver provideInstance(CommsModule commsModule, Provider<CommsServiceV2> provider) {
        return proxyProvideConversationMessagingReceiver(commsModule, provider.mo10268get());
    }

    public static MessagingReceiver proxyProvideConversationMessagingReceiver(CommsModule commsModule, CommsServiceV2 commsServiceV2) {
        return (MessagingReceiver) Preconditions.checkNotNull(commsModule.provideConversationMessagingReceiver(commsServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingReceiver mo10268get() {
        return provideInstance(this.module, this.commsServiceV2Provider);
    }
}
