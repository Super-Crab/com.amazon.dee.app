package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AlexaApiCallbacksMessageSender extends AlexaMessageSender<AlexaApiCallbacksMessageType> {
    private final ExtendedClient client;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ResultMessagePayload extends BaseMessagePayload {
        ResultMessagePayload(ExtendedClient extendedClient) {
            super(extendedClient);
        }

        ResultMessagePayload(ExtendedClient extendedClient, ApiCallFailure apiCallFailure) {
            super(extendedClient);
            add((Bundles.Key) AlexaApiCallbacksArgumentKey.API_CALL_FAILURE, apiCallFailure.getBundle());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaApiCallbacksMessageSender(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.client = extendedClient;
    }

    public void onFailure(ApiCallFailure apiCallFailure) throws RemoteException {
        sendMessage(AlexaApiCallbacksMessageType.FAILURE, new ResultMessagePayload(this.client, apiCallFailure).getBundle());
    }

    public void onSuccess() throws RemoteException {
        sendMessage(AlexaApiCallbacksMessageType.SUCCESS, new ResultMessagePayload(this.client).getBundle());
    }
}
