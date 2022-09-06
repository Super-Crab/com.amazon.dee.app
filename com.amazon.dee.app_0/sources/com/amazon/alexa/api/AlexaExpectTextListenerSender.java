package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
public class AlexaExpectTextListenerSender extends AlexaMessageSender<ExpectTextMessageType> implements AlexaExpectTextListener {
    private static final String TAG = "AlexaExpectTextListenerSender";
    private final ExtendedClient extendedClient;

    public AlexaExpectTextListenerSender(ExtendedClient extendedClient, IBinder iBinder) {
        super(iBinder);
        this.extendedClient = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaExpectTextListener
    public void onExpectTextReceived() {
        try {
            sendMessage(ExpectTextMessageType.ON_EXPECT_TEXT_RECEIVED, new Bundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onExpectTextReceived", e);
        }
    }
}
