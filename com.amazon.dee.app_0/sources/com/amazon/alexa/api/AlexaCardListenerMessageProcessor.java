package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.ApiThreadHelper;
/* loaded from: classes6.dex */
public class AlexaCardListenerMessageProcessor extends MessageProcessor<AlexaCardListenerMessageType> {
    private static final String TAG = "AlexaCardListenerMessageProcessor";
    private final AlexaCardListener cardListener;

    /* renamed from: com.amazon.alexa.api.AlexaCardListenerMessageProcessor$2  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaCardListenerMessageType = new int[AlexaCardListenerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaCardListenerMessageType[AlexaCardListenerMessageType.ON_RECEIVED_RENDER_CARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaCardListenerMessageType[AlexaCardListenerMessageType.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private AlexaCardListenerMessageProcessor(AlexaCardListener alexaCardListener) {
        this.cardListener = alexaCardListener;
    }

    public static AlexaCardListenerMessageProcessor create(AlexaCardListener alexaCardListener) {
        return new AlexaCardListenerMessageProcessor(alexaCardListener);
    }

    private void handleOnReceivedRenderCard(Bundle bundle) {
        final String string = Bundles.getString(bundle, AlexaCardListenerArgumentKey.CARD_DATA);
        final AlexaCardExtras alexaCardExtras = new AlexaCardExtras(Bundles.getBundle(bundle, AlexaCardListenerArgumentKey.CARD_EXTRAS));
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaCardListenerMessageProcessor.1
            @Override // java.lang.Runnable
            public void run() {
                AlexaCardListenerMessageProcessor.this.cardListener.onReceivedRenderCard(string, alexaCardExtras);
            }
        });
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaCardListenerMessageType mo845getMessageType(Message message) {
        if (AlexaCardListenerMessageType.ON_RECEIVED_RENDER_CARD.ordinal() == message.what) {
            return AlexaCardListenerMessageType.ON_RECEIVED_RENDER_CARD;
        }
        Log.e(TAG, "Unrecognized message type");
        return AlexaCardListenerMessageType.UNKNOWN;
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaCardListenerMessageType alexaCardListenerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (alexaCardListenerMessageType.ordinal() == 1) {
            handleOnReceivedRenderCard(bundle);
            return;
        }
        String str = TAG;
        Log.w(str, "Unsupported message " + alexaCardListenerMessageType);
    }
}
