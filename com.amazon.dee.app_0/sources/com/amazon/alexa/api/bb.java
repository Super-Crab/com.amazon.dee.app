package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.utils.Provider;
/* loaded from: classes6.dex */
abstract class bb<K, T extends AlexaMessageType> extends az<K, MessageReceiver<T>> {
    private final Provider<MessageReceiversManager> messageReceiversManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bb(Provider<MessageReceiversManager> provider) {
        this.messageReceiversManager = provider;
    }

    protected void clearValue(K k, MessageReceiver<T> messageReceiver) {
        this.messageReceiversManager.mo2864get().removeMessageReceiver(messageReceiver);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.api.az
    protected /* bridge */ /* synthetic */ void clearValue(Object obj, Object obj2) {
        clearValue((bb<K, T>) obj, (MessageReceiver) ((MessageReceiver) obj2));
    }

    protected abstract MessageProcessor<T> createMessageProcessor(K k);

    @Override // com.amazon.alexa.api.az
    /* renamed from: createValueFor */
    protected MessageReceiver<T> mo846createValueFor(K k) {
        return this.messageReceiversManager.mo2864get().createMessageReceiver(createMessageProcessor(k));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.api.az
    /* renamed from: createValueFor  reason: collision with other method in class */
    protected /* bridge */ /* synthetic */ Object mo846createValueFor(Object obj) {
        return mo846createValueFor((bb<K, T>) obj);
    }
}
