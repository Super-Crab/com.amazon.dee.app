package com.amazon.alexa.api.messages;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public final class Messages {
    private Messages() {
    }

    public static Message createMessage(AlexaMessageType alexaMessageType, Bundle bundle) {
        return createMessage(alexaMessageType, bundle, null);
    }

    public static Message createMessage(AlexaMessageType alexaMessageType, Bundle bundle, Messenger messenger) {
        Preconditions.notNull(alexaMessageType, "message type is null");
        Preconditions.notNull(bundle, "payload is null");
        Message obtain = Message.obtain((Handler) null, alexaMessageType.ordinal());
        obtain.setData(bundle);
        if (messenger != null) {
            obtain.replyTo = messenger;
        }
        return obtain;
    }

    public static Bundle getPayload(Message message) {
        Preconditions.notNull(message, "message is null");
        Bundle data = message.getData();
        data.setClassLoader(Messages.class.getClassLoader());
        return data;
    }

    public static Messenger getReplyHandlingMessenger(Message message) {
        Preconditions.notNull(message, "message is null");
        return message.replyTo;
    }

    public static boolean hasReplyReceiver(Message message) {
        Preconditions.notNull(message, "message is null");
        return message.replyTo != null;
    }

    public static void sendMessage(Messenger messenger, Message message) throws RemoteException {
        Preconditions.notNull(messenger, "messenger is null");
        Preconditions.notNull(message, "message is null");
        messenger.send(message);
    }
}
