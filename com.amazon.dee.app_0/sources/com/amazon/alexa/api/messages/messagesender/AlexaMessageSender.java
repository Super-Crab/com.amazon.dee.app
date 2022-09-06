package com.amazon.alexa.api.messages.messagesender;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import com.amazon.alexa.api.messages.AlexaMessageType;
import com.amazon.alexa.api.messages.Messages;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlexaMessageSender<T extends AlexaMessageType> {
    private final Messenger messenger;

    public AlexaMessageSender(IBinder iBinder) {
        Preconditions.notNull(iBinder, "binder is null");
        this.messenger = createMessenger(iBinder);
    }

    protected Messenger createMessenger(IBinder iBinder) {
        return new Messenger(iBinder);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof AlexaMessageSender) && this.messenger.getBinder().equals(((AlexaMessageSender) obj).messenger.getBinder()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Messenger getMessenger() {
        return this.messenger;
    }

    public int hashCode() {
        return Objects.hashCode(this.messenger.getBinder());
    }

    public void sendMessage(T t, Bundle bundle) throws RemoteException {
        Preconditions.notNull(t, "message type is null");
        Preconditions.notNull(bundle, "payload is null");
        Messages.sendMessage(this.messenger, Messages.createMessage(t, bundle));
    }
}
