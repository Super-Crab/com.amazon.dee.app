package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.TextResponseListenerArgumentType;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
public class TextResponseListenerSender extends AlexaMessageSender<TextResponseListenerMessageType> implements AlexaTextResponseListener {
    private static final String TAG = "TextResponseListenerSender";
    private final ExtendedClient extendedClient;

    /* loaded from: classes6.dex */
    private static class a extends BaseMessagePayload {
        a(ExtendedClient extendedClient, TextResponse textResponse) {
            super(extendedClient);
            add((Bundles.Key) TextResponseListenerArgumentType.a.RESPONSE, BundleTransformer.getDefaultInstance().toBundle(textResponse));
        }
    }

    public TextResponseListenerSender(ExtendedClient extendedClient, IBinder iBinder) {
        super(iBinder);
        this.extendedClient = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaTextResponseListener
    public void onTextReceived(TextResponse textResponse) {
        try {
            sendMessage(TextResponseListenerMessageType.ON_TEXT_RECEIVED, new a(this.extendedClient, textResponse).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onTextReceived", e);
        }
    }
}
