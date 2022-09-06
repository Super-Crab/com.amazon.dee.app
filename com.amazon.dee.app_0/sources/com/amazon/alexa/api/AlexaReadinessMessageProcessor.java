package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public class AlexaReadinessMessageProcessor extends MessageProcessor<AlexaReadinessMessageType> {
    private static final String TAG = "AlexaReadinessMessageProcessor";
    private final AlexaReadinessListener alexaReadinessListener;

    /* renamed from: com.amazon.alexa.api.AlexaReadinessMessageProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaReadinessMessageType = new int[AlexaReadinessMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaReadinessMessageType[AlexaReadinessMessageType.ON_READINESS_CHANGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public AlexaReadinessMessageProcessor(AlexaReadinessListener alexaReadinessListener) {
        this.alexaReadinessListener = alexaReadinessListener;
    }

    public static AlexaReadinessMessageProcessor create(AlexaReadinessListener alexaReadinessListener) {
        return new AlexaReadinessMessageProcessor(alexaReadinessListener);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaReadinessMessageType mo845getMessageType(Message message) {
        try {
            return AlexaReadinessMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unrecognized message type", e);
            return AlexaReadinessMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaReadinessMessageType alexaReadinessMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (alexaReadinessMessageType.ordinal() == 1) {
            this.alexaReadinessListener.onReadinessChanged((AlexaReadyState) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, AlexaReadinessArgumentKey.READY_STATE_DATA), AlexaReadyState.class));
            return;
        }
        String str = TAG;
        Log.w(str, "Unsupported message " + alexaReadinessMessageType);
    }
}
