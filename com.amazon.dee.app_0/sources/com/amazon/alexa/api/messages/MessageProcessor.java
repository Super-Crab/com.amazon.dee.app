package com.amazon.alexa.api.messages;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.AlexaMessageType;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public abstract class MessageProcessor<T extends AlexaMessageType> {
    private static final String TAG = "MessageProcessor";

    /* renamed from: getMessageType */
    public abstract T mo845getMessageType(Message message);

    public abstract void processMessage(T t, Bundle bundle, @Nullable Messenger messenger);

    /* JADX INFO: Access modifiers changed from: protected */
    public void reply(Messenger messenger, T t, Bundle bundle) throws RemoteException {
        Preconditions.notNull(messenger, "response receiver is null");
        Preconditions.notNull(t, "message type is null");
        Preconditions.notNull(bundle, "payload is null");
        String str = TAG;
        Log.i(str, "sending a reply for " + t);
        Messages.sendMessage(messenger, Messages.createMessage(t, bundle));
    }
}
