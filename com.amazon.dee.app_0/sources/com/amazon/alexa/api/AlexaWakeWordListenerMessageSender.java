package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
public class AlexaWakeWordListenerMessageSender extends AlexaMessageSender<AlexaWakeWordListenerMessageType> implements AlexaWakeWordListener {
    private static final String TAG = "AlexaWakeWordListenerMessageSender";
    private final ExtendedClient extendedClient;

    /* loaded from: classes6.dex */
    private static class OnWakeWordState extends BaseMessagePayload {
        public OnWakeWordState(ExtendedClient extendedClient, WakeWordState wakeWordState) {
            super(extendedClient);
            add((Bundles.Key) AlexaWakeWordListenerArgumentKey.WAKE_WORD_STATE, BundleTransformer.getDefaultInstance().toBundle(wakeWordState));
        }
    }

    public AlexaWakeWordListenerMessageSender(ExtendedClient extendedClient, IBinder iBinder) {
        super(iBinder);
        this.extendedClient = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaWakeWordListener
    public void onWakeWordState(WakeWordState wakeWordState) {
        try {
            sendMessage(AlexaWakeWordListenerMessageType.ON_WAKE_WORD_STATE, new OnWakeWordState(this.extendedClient, wakeWordState).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send ON_WAKE_WORD_STATE", e);
        }
    }
}
