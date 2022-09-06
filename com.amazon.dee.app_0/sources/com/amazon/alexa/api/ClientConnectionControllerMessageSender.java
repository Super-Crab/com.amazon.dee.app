package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
public class ClientConnectionControllerMessageSender extends AlexaMessageSender<ClientConnectionControllerMessageType> {
    private final ExtendedClient client;

    public ClientConnectionControllerMessageSender(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.client = extendedClient;
    }

    public void onForceDisconnect() throws RemoteException {
        sendMessage(ClientConnectionControllerMessageType.ON_FORCE_DISCONNECT, new BaseMessagePayload(this.client).getBundle());
    }

    public void onStartService() throws RemoteException {
        sendMessage(ClientConnectionControllerMessageType.ON_START_SERVICE, new BaseMessagePayload(this.client).getBundle());
    }
}
