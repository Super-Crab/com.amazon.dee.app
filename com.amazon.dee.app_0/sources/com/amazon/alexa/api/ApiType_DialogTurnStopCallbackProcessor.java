package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
final class ApiType_DialogTurnStopCallbackProcessor extends MessageProcessor<ApiType_DialogTurnStopCallbackMessageType> {
    private final ApiType_DialogTurnStopCallbackWrapper apiType_DialogTurnStopCallbackWrapper;

    /* renamed from: com.amazon.alexa.api.ApiType_DialogTurnStopCallbackProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$ApiType_DialogTurnStopCallbackMessageType = new int[ApiType_DialogTurnStopCallbackMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_DialogTurnStopCallbackMessageType[ApiType_DialogTurnStopCallbackMessageType.STOP_RECORDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DialogTurnStopCallbackProcessor(AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        this.apiType_DialogTurnStopCallbackWrapper = new ApiType_DialogTurnStopCallbackWrapper(alexaDialogTurnStopCallback);
    }

    private void processStopRecording(Bundle bundle) {
        this.apiType_DialogTurnStopCallbackWrapper.stopRecording();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ApiType_DialogTurnStopCallbackMessageType mo845getMessageType(Message message) {
        try {
            return ApiType_DialogTurnStopCallbackMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return ApiType_DialogTurnStopCallbackMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ApiType_DialogTurnStopCallbackMessageType apiType_DialogTurnStopCallbackMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        if (apiType_DialogTurnStopCallbackMessageType.ordinal() == 1) {
            processStopRecording(bundle);
            return;
        }
        String simpleName = ApiType_DialogTurnStopCallbackProcessor.class.getSimpleName();
        Log.w(simpleName, "Unsupported message: " + apiType_DialogTurnStopCallbackMessageType);
    }
}
