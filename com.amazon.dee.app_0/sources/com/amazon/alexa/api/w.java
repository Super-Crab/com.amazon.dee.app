package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.messages.AlexaMessageType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.utils.Provider;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
abstract class w {
    private final Map<MessageProcessor, MessageReceiver> messageReceivers = new HashMap();
    private final Provider<MessageReceiversManager> messageReceiversManagerProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public w(Provider<MessageReceiversManager> provider) {
        this.messageReceiversManagerProvider = provider;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clear() {
        for (MessageReceiver messageReceiver : this.messageReceivers.values()) {
            this.messageReceiversManagerProvider.mo2864get().removeMessageReceiver(messageReceiver);
        }
        this.messageReceivers.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Bundle getBundle();

    /* JADX INFO: Access modifiers changed from: protected */
    public <K extends AlexaMessageType> MessageReceiver<K> getMessageReceiver(MessageProcessor<K> messageProcessor) {
        MessageReceiver<K> createMessageReceiver = this.messageReceiversManagerProvider.mo2864get().createMessageReceiver(messageProcessor);
        this.messageReceivers.put(messageProcessor, createMessageReceiver);
        return createMessageReceiver;
    }
}
