package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
public class AlexaCardListenerMessageSender extends AlexaMessageSender<AlexaCardListenerMessageType> implements AlexaCardListener {
    private static final String TAG = "AlexaCardListenerMessageSender";

    /* loaded from: classes6.dex */
    private static class CardMessagePayload {
        private final Bundle bundle = new Bundle();

        CardMessagePayload(String str, AlexaCardExtras alexaCardExtras) {
            this.bundle.putString(AlexaCardListenerArgumentKey.CARD_DATA.name(), str);
            this.bundle.putBundle(AlexaCardListenerArgumentKey.CARD_EXTRAS.name(), alexaCardExtras.getBundle());
        }

        Bundle getBundle() {
            return this.bundle;
        }
    }

    public AlexaCardListenerMessageSender(IBinder iBinder) {
        super(iBinder);
    }

    @Override // com.amazon.alexa.api.AlexaCardListener
    public void onReceivedRenderCard(String str, AlexaCardExtras alexaCardExtras) {
        try {
            sendMessage(AlexaCardListenerMessageType.ON_RECEIVED_RENDER_CARD, new CardMessagePayload(str, alexaCardExtras).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send render card data", e);
        }
    }
}
