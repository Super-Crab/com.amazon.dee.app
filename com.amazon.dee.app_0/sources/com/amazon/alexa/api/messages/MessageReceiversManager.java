package com.amazon.alexa.api.messages;

import android.os.HandlerThread;
import com.amazon.alexa.api.messages.messagereceiver.ExceptionReporter;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class MessageReceiversManager {
    private static final String TAG = "MessageReceiversManager";
    private final ExceptionReporter exceptionReporter;
    private final a messageHandlerManager;
    private final Map<HandlerThread, Set<MessageReceiver>> messengers;
    private final SignatureVerifier signatureVerifier;

    public MessageReceiversManager() {
        this(null);
    }

    public MessageReceiversManager(@Nullable SignatureVerifier signatureVerifier) {
        this(signatureVerifier, null);
    }

    public MessageReceiversManager(@Nullable SignatureVerifier signatureVerifier, @Nullable ExceptionReporter exceptionReporter) {
        this(signatureVerifier, exceptionReporter, new a(), new HashMap());
    }

    MessageReceiversManager(@Nullable SignatureVerifier signatureVerifier, @Nullable ExceptionReporter exceptionReporter, a aVar, Map<HandlerThread, Set<MessageReceiver>> map) {
        this.signatureVerifier = signatureVerifier;
        this.exceptionReporter = exceptionReporter;
        this.messageHandlerManager = aVar;
        this.messengers = map;
    }

    private void storeMessageReceiver(HandlerThread handlerThread, MessageReceiver messageReceiver) {
        if (!this.messengers.containsKey(handlerThread)) {
            this.messengers.put(handlerThread, new HashSet());
        }
        this.messengers.get(handlerThread).add(messageReceiver);
    }

    public synchronized void clear() {
        this.messengers.clear();
        this.messageHandlerManager.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized <T extends AlexaMessageType> MessageReceiver<T> createMessageReceiver(MessageProcessor<T> messageProcessor) {
        MessageReceiver<T> instantiateMessageReceiver;
        Preconditions.notNull(messageProcessor, "messageProcessor is null");
        HandlerThread a = this.messageHandlerManager.a(messageProcessor.getClass());
        instantiateMessageReceiver = instantiateMessageReceiver(messageProcessor, a, this.signatureVerifier, this.exceptionReporter);
        storeMessageReceiver(a, instantiateMessageReceiver);
        return instantiateMessageReceiver;
    }

    protected <T extends AlexaMessageType> MessageReceiver<T> instantiateMessageReceiver(MessageProcessor<T> messageProcessor, HandlerThread handlerThread, @Nullable SignatureVerifier signatureVerifier, @Nullable ExceptionReporter exceptionReporter) {
        String str = "creating message receiver for " + messageProcessor + " and assigning it this handlerthread: " + handlerThread.getLooper();
        return new MessageReceiver<>(messageProcessor, handlerThread, signatureVerifier, exceptionReporter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized void removeMessageReceiver(MessageReceiver messageReceiver) {
        Preconditions.notNull(messageReceiver, "message receiver is null");
        String str = "removing message receiver " + messageReceiver;
        MessageProcessor messageProcessor = messageReceiver.getMessageProcessor();
        HandlerThread a = this.messageHandlerManager.a(messageProcessor.getClass());
        Set<MessageReceiver> set = this.messengers.get(a);
        if (set == null) {
            this.messageHandlerManager.c(messageProcessor.getClass());
            return;
        }
        set.remove(messageReceiver);
        if (set.isEmpty()) {
            String str2 = "cleaning the handler thread for " + messageReceiver;
            this.messengers.remove(a);
            this.messageHandlerManager.c(messageProcessor.getClass());
        }
    }
}
