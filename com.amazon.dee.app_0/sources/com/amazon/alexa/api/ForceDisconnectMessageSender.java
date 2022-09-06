package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class ForceDisconnectMessageSender extends AlexaMessageSender<as> {
    private final ExtendedClient client;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ForceDisconnectMessageSender(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.client = extendedClient;
    }

    public void onForceDisconnect() throws RemoteException {
        sendMessage(as.ON_FORCE_DISCONNECT, new BaseMessagePayload(this.client).getBundle());
    }
}
