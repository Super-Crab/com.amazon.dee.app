package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class ApiType_DialogTurnStopCallbackSender extends AlexaMessageSender<ApiType_DialogTurnStopCallbackMessageType> implements AlexaDialogTurnStopCallback {
    private static final String TAG = "ApiType_DialogTurnStopCallbackSender";
    private final ExtendedClient client;
    private final MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    private static class StopRecordingPayload extends BaseMessagePayload {
        StopRecordingPayload(ExtendedClient extendedClient) {
            super(extendedClient);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DialogTurnStopCallbackSender(ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        this.client = extendedClient;
        this.messageReceiversManager = messageReceiversManager;
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
    public void stopRecording() {
        try {
            sendMessage(ApiType_DialogTurnStopCallbackMessageType.STOP_RECORDING, new StopRecordingPayload(this.client).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: stopRecording", e);
        }
    }
}
