package com.amazon.alexa.api.messages.messagereceiver;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.AlexaMessageType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.Messages;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class MessageReceiver<T extends AlexaMessageType> {
    private static final String TAG = "MessageReceiver";
    private final MessageProcessor<T> messageProcessor;
    private final Messenger messenger;
    private final SignatureVerifier signatureVerifier;

    @SuppressLint({"HandlerLeak"})
    /* loaded from: classes6.dex */
    private class a extends Handler {
        private final ExceptionReporter b;
        private final HandlerThread c;

        a(HandlerThread handlerThread, ExceptionReporter exceptionReporter) {
            super(handlerThread.getLooper());
            this.c = handlerThread;
            this.b = exceptionReporter;
            String unused = MessageReceiver.TAG;
            String str = "messaging handler is going to live in " + handlerThread;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AlexaMessageType mo845getMessageType = MessageReceiver.this.messageProcessor.mo845getMessageType(message);
            String unused = MessageReceiver.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
            outline107.append(Thread.currentThread());
            outline107.append("] got ");
            outline107.append(mo845getMessageType);
            outline107.append(" message");
            outline107.toString();
            try {
                MessageReceiver.this.messageProcessor.processMessage(mo845getMessageType, Messages.getPayload(message), Messages.getReplyHandlingMessenger(message));
            } catch (Throwable th) {
                String str = MessageReceiver.TAG;
                Log.e(str, "Unable to handle message: " + mo845getMessageType, th);
                ExceptionReporter exceptionReporter = this.b;
                if (exceptionReporter == null) {
                    return;
                }
                exceptionReporter.onError(th);
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j) {
            if (MessageReceiver.this.signatureVerifier != null && !MessageReceiver.this.signatureVerifier.verify(Binder.getCallingUid())) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Signature Verification failed for client UID: ");
                outline107.append(Binder.getCallingUid());
                outline107.append(" and PID: ");
                outline107.append(Binder.getCallingPid());
                String sb = outline107.toString();
                String unused = MessageReceiver.TAG;
                throw new SecurityException(sb);
            }
            AlexaMessageType mo845getMessageType = MessageReceiver.this.messageProcessor.mo845getMessageType(message);
            if (!this.c.isAlive()) {
                String str = MessageReceiver.TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("HandlerThread ");
                outline1072.append(this.c.getThreadId());
                outline1072.append(" is no longer alive. Dropping message: ");
                outline1072.append(mo845getMessageType);
                Log.w(str, outline1072.toString());
                return false;
            }
            String unused2 = MessageReceiver.TAG;
            String str2 = "Received " + mo845getMessageType + " on looper " + super.getLooper();
            return super.sendMessageAtTime(message, j);
        }
    }

    public MessageReceiver(MessageProcessor<T> messageProcessor, HandlerThread handlerThread) {
        this(messageProcessor, handlerThread, null, null);
    }

    public MessageReceiver(MessageProcessor<T> messageProcessor, HandlerThread handlerThread, @Nullable SignatureVerifier signatureVerifier, @Nullable ExceptionReporter exceptionReporter) {
        Preconditions.notNull(messageProcessor, "message processor is null");
        Preconditions.notNull(handlerThread, "handler thread is null");
        this.messageProcessor = messageProcessor;
        this.messenger = new Messenger(new a(handlerThread, exceptionReporter));
        this.signatureVerifier = signatureVerifier;
    }

    public MessageProcessor<T> getMessageProcessor() {
        return this.messageProcessor;
    }

    public Messenger getMessenger() {
        return this.messenger;
    }

    protected void reply(Messenger messenger, T t, Bundle bundle) throws RemoteException {
        Preconditions.notNull(messenger, "response receiver is null");
        Preconditions.notNull(t, "message type is null");
        Preconditions.notNull(bundle, "payload is null");
        String str = TAG;
        Log.i(str, "sending a reply for " + t);
        Messages.sendMessage(messenger, Messages.createMessage(t, bundle));
    }
}
