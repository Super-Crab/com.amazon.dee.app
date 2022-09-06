package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.ApiType_DialogTurnMetricsCallbackArgumentType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
final class ApiType_DialogTurnMetricsCallbackProcessor extends MessageProcessor<ApiType_DialogTurnMetricsCallbackMessageType> {
    private final ApiType_DialogTurnMetricsCallbackWrapper apiType_DialogTurnMetricsCallbackWrapper;

    /* renamed from: com.amazon.alexa.api.ApiType_DialogTurnMetricsCallbackProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$ApiType_DialogTurnMetricsCallbackMessageType = new int[ApiType_DialogTurnMetricsCallbackMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_DialogTurnMetricsCallbackMessageType[ApiType_DialogTurnMetricsCallbackMessageType.ON_USER_PERCEIVED_LATENCY_DATA_COM_AMAZON_ALEXA_API_USER_PERCEIVED_LATENCY_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DialogTurnMetricsCallbackProcessor(AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        this.apiType_DialogTurnMetricsCallbackWrapper = new ApiType_DialogTurnMetricsCallbackWrapper(alexaDialogTurnMetricsCallback);
    }

    private void processOnUserPerceivedLatencyData_com_amazon_alexa_api_UserPerceivedLatencyData(Bundle bundle) {
        UserPerceivedLatencyData userPerceivedLatencyData = (UserPerceivedLatencyData) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, ApiType_DialogTurnMetricsCallbackArgumentType.OnUserPerceivedLatencyData_com_amazon_alexa_api_UserPerceivedLatencyDataArgumentType.USER_PERCEIVED_LATENCY_DATA), UserPerceivedLatencyData.class);
        Preconditions.notNull(userPerceivedLatencyData, "userPerceivedLatencyData cannot be null");
        this.apiType_DialogTurnMetricsCallbackWrapper.onUserPerceivedLatencyData(userPerceivedLatencyData);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ApiType_DialogTurnMetricsCallbackMessageType mo845getMessageType(Message message) {
        try {
            return ApiType_DialogTurnMetricsCallbackMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return ApiType_DialogTurnMetricsCallbackMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ApiType_DialogTurnMetricsCallbackMessageType apiType_DialogTurnMetricsCallbackMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        if (apiType_DialogTurnMetricsCallbackMessageType.ordinal() == 1) {
            processOnUserPerceivedLatencyData_com_amazon_alexa_api_UserPerceivedLatencyData(bundle);
            return;
        }
        String simpleName = ApiType_DialogTurnMetricsCallbackProcessor.class.getSimpleName();
        Log.w(simpleName, "Unsupported message: " + apiType_DialogTurnMetricsCallbackMessageType);
    }
}
