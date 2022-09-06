package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
public class AlexaReadinessMessageSender extends AlexaMessageSender<AlexaReadinessMessageType> implements AlexaReadinessListener {
    private static final String TAG = "AlexaReadinessMessageSender";
    private final ExtendedClient client;

    /* loaded from: classes6.dex */
    static class ReadyStateMessagePayload extends BaseMessagePayload {
        ReadyStateMessagePayload(ExtendedClient extendedClient, AlexaReadyState alexaReadyState) {
            super(extendedClient);
            add((Bundles.Key) AlexaReadinessArgumentKey.READY_STATE_DATA, BundleTransformer.getDefaultInstance().toBundle(alexaReadyState));
        }
    }

    public AlexaReadinessMessageSender(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.client = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaReadinessListener
    public void onReadinessChanged(AlexaReadyState alexaReadyState) {
        try {
            sendMessage(AlexaReadinessMessageType.ON_READINESS_CHANGED, new ReadyStateMessagePayload(this.client, alexaReadyState).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send on metrics report event ", e);
        }
    }
}
