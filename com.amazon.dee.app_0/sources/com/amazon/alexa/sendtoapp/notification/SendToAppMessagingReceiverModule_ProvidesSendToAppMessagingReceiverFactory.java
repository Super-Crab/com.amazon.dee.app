package com.amazon.alexa.sendtoapp.notification;

import android.content.Context;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class SendToAppMessagingReceiverModule_ProvidesSendToAppMessagingReceiverFactory implements Factory<MessagingReceiver> {
    private final Provider<Context> contextProvider;
    private final SendToAppMessagingReceiverModule module;

    public SendToAppMessagingReceiverModule_ProvidesSendToAppMessagingReceiverFactory(SendToAppMessagingReceiverModule sendToAppMessagingReceiverModule, Provider<Context> provider) {
        this.module = sendToAppMessagingReceiverModule;
        this.contextProvider = provider;
    }

    public static SendToAppMessagingReceiverModule_ProvidesSendToAppMessagingReceiverFactory create(SendToAppMessagingReceiverModule sendToAppMessagingReceiverModule, Provider<Context> provider) {
        return new SendToAppMessagingReceiverModule_ProvidesSendToAppMessagingReceiverFactory(sendToAppMessagingReceiverModule, provider);
    }

    public static MessagingReceiver provideInstance(SendToAppMessagingReceiverModule sendToAppMessagingReceiverModule, Provider<Context> provider) {
        return proxyProvidesSendToAppMessagingReceiver(sendToAppMessagingReceiverModule, provider.mo10268get());
    }

    public static MessagingReceiver proxyProvidesSendToAppMessagingReceiver(SendToAppMessagingReceiverModule sendToAppMessagingReceiverModule, Context context) {
        return (MessagingReceiver) Preconditions.checkNotNull(sendToAppMessagingReceiverModule.providesSendToAppMessagingReceiver(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingReceiver mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
