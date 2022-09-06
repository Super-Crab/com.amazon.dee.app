package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.ApiThreadHelper;
/* loaded from: classes6.dex */
class AlexaApiCallbacksMessageProcessor extends MessageProcessor<AlexaApiCallbacksMessageType> {
    private static final String TAG = "AlexaApiCallbacksMessageProcessor";
    private final AlexaApiCallbacks callbacks;

    /* renamed from: com.amazon.alexa.api.AlexaApiCallbacksMessageProcessor$3  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaApiCallbacksMessageType = new int[AlexaApiCallbacksMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaApiCallbacksMessageType[AlexaApiCallbacksMessageType.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaApiCallbacksMessageType[AlexaApiCallbacksMessageType.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaApiCallbacksMessageType[AlexaApiCallbacksMessageType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private AlexaApiCallbacksMessageProcessor(AlexaApiCallbacks alexaApiCallbacks) {
        this.callbacks = alexaApiCallbacks;
    }

    public static AlexaApiCallbacksMessageProcessor create(AlexaApiCallbacks alexaApiCallbacks) {
        return new AlexaApiCallbacksMessageProcessor(alexaApiCallbacks);
    }

    private void handleFailure(Bundle bundle) {
        final ApiCallFailure createFromBundle = ApiCallFailure.createFromBundle(Bundles.getOptionalBundle(bundle, AlexaApiCallbacksArgumentKey.API_CALL_FAILURE));
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaApiCallbacksMessageProcessor.2
            @Override // java.lang.Runnable
            public void run() {
                AlexaApiCallbacksMessageProcessor.this.callbacks.doOnFailure(createFromBundle);
            }
        });
    }

    private void handleSuccess(Bundle bundle) {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaApiCallbacksMessageProcessor.1
            @Override // java.lang.Runnable
            public void run() {
                AlexaApiCallbacksMessageProcessor.this.callbacks.doOnSuccess();
            }
        });
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaApiCallbacksMessageType mo845getMessageType(Message message) {
        try {
            return AlexaApiCallbacksMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Unrecognized message type");
            return AlexaApiCallbacksMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaApiCallbacksMessageType alexaApiCallbacksMessageType, Bundle bundle, @Nullable Messenger messenger) {
        int ordinal = alexaApiCallbacksMessageType.ordinal();
        if (ordinal == 1) {
            handleSuccess(bundle);
        } else if (ordinal == 2) {
            handleFailure(bundle);
        } else {
            String str = TAG;
            Log.w(str, "Unsupported message " + alexaApiCallbacksMessageType);
        }
    }
}
