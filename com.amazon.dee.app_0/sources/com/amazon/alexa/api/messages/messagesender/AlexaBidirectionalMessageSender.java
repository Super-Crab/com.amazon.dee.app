package com.amazon.alexa.api.messages.messagesender;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import com.amazon.alexa.api.Releasable;
import com.amazon.alexa.api.messages.AlexaMessageType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.Messages;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Set;
/* loaded from: classes6.dex */
public abstract class AlexaBidirectionalMessageSender<T extends AlexaMessageType> extends AlexaMessageSender<T> implements Releasable {
    private static final String TAG = "AlexaBidirectionalMessageSender";
    protected static final long WAIT_TIMEOUT_MILLISECONDS = 1000;
    private MessageReceiver<T> messageReceiver;
    private final Object messageReceiverLock;
    protected final MessageReceiversManager messageReceiversManager;

    public AlexaBidirectionalMessageSender(IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        Preconditions.notNull(messageReceiversManager, "messageReceiversManager is null");
        this.messageReceiversManager = messageReceiversManager;
        this.messageReceiverLock = new Object();
    }

    private Messenger getResponseHandlingMessenger() {
        Messenger messenger;
        synchronized (this.messageReceiverLock) {
            if (this.messageReceiver == null) {
                this.messageReceiver = this.messageReceiversManager.createMessageReceiver(createResponseProcessor());
            }
            messenger = this.messageReceiver.getMessenger();
        }
        return messenger;
    }

    private boolean needResponse(T t) {
        return getExpectedMessageTypes().contains(t);
    }

    protected abstract MessageProcessor<T> createResponseProcessor();

    protected abstract Set<T> getExpectedMessageTypes();

    public MessageReceiversManager getMessageReceiversManager() {
        return this.messageReceiversManager;
    }

    @Override // com.amazon.alexa.api.Releasable
    public void release() {
        synchronized (this.messageReceiverLock) {
            if (this.messageReceiver != null) {
                this.messageReceiversManager.removeMessageReceiver(this.messageReceiver);
            }
        }
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaMessageSender
    public void sendMessage(T t, Bundle bundle) throws RemoteException {
        Preconditions.notNull(t, "message type is null");
        Preconditions.notNull(bundle, "payload is null");
        String str = "[" + Thread.currentThread() + "] sending message " + t;
        if (!needResponse(t)) {
            super.sendMessage(t, bundle);
            return;
        }
        Messages.sendMessage(getMessenger(), Messages.createMessage(t, bundle, getResponseHandlingMessenger()));
        String str2 = "[" + Thread.currentThread() + "] expecting a response " + t;
    }
}
