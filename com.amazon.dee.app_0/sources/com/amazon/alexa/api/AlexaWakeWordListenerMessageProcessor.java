package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public class AlexaWakeWordListenerMessageProcessor extends MessageProcessor<AlexaWakeWordListenerMessageType> {
    private static final String TAG = "AlexaWakeWordListenerMessageProcessor";
    private final AlexaWakeWordListener listener;

    /* renamed from: com.amazon.alexa.api.AlexaWakeWordListenerMessageProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaWakeWordListenerMessageType = new int[AlexaWakeWordListenerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaWakeWordListenerMessageType[AlexaWakeWordListenerMessageType.ON_WAKE_WORD_STATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaWakeWordListenerMessageType[AlexaWakeWordListenerMessageType.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public AlexaWakeWordListenerMessageProcessor(AlexaWakeWordListener alexaWakeWordListener) {
        this.listener = alexaWakeWordListener;
    }

    private void handleOnWakeWordState(Bundle bundle) {
        Bundle bundle2 = Bundles.getBundle(bundle, AlexaWakeWordListenerArgumentKey.WAKE_WORD_STATE);
        this.listener.onWakeWordState((WakeWordState) BundleTransformer.getDefaultInstance().getFromBundle(bundle2, WakeWordState.class));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaWakeWordListenerMessageType mo845getMessageType(Message message) {
        try {
            return AlexaWakeWordListenerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unrecognized message type", e);
            return AlexaWakeWordListenerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaWakeWordListenerMessageType alexaWakeWordListenerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        int ordinal = alexaWakeWordListenerMessageType.ordinal();
        if (ordinal == 0) {
            String str = TAG;
            Log.e(str, "Unknown message " + alexaWakeWordListenerMessageType);
        } else if (ordinal == 1) {
            handleOnWakeWordState(bundle);
        } else {
            String str2 = TAG;
            Log.w(str2, "Unsupported message " + alexaWakeWordListenerMessageType);
        }
    }
}
