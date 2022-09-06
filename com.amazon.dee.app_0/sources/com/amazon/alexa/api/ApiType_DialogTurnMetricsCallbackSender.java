package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.ApiType_DialogTurnMetricsCallbackArgumentType;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class ApiType_DialogTurnMetricsCallbackSender extends AlexaMessageSender<ApiType_DialogTurnMetricsCallbackMessageType> implements AlexaDialogTurnMetricsCallback {
    private static final String TAG = "ApiType_DialogTurnMetricsCallbackSender";
    private final ExtendedClient client;
    private final MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    private static class OnUserPerceivedLatencyData_com_amazon_alexa_api_UserPerceivedLatencyDataPayload extends BaseMessagePayload {
        OnUserPerceivedLatencyData_com_amazon_alexa_api_UserPerceivedLatencyDataPayload(ExtendedClient extendedClient, UserPerceivedLatencyData userPerceivedLatencyData) {
            super(extendedClient);
            add((Bundles.Key) ApiType_DialogTurnMetricsCallbackArgumentType.OnUserPerceivedLatencyData_com_amazon_alexa_api_UserPerceivedLatencyDataArgumentType.USER_PERCEIVED_LATENCY_DATA, BundleTransformer.getDefaultInstance().toBundle(userPerceivedLatencyData));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DialogTurnMetricsCallbackSender(ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        this.client = extendedClient;
        this.messageReceiversManager = messageReceiversManager;
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurnMetricsCallback
    public void onUserPerceivedLatencyData(UserPerceivedLatencyData userPerceivedLatencyData) {
        try {
            sendMessage(ApiType_DialogTurnMetricsCallbackMessageType.ON_USER_PERCEIVED_LATENCY_DATA_COM_AMAZON_ALEXA_API_USER_PERCEIVED_LATENCY_DATA, new OnUserPerceivedLatencyData_com_amazon_alexa_api_UserPerceivedLatencyDataPayload(this.client, userPerceivedLatencyData).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onUserPerceivedLatencyData_com_amazon_alexa_api_UserPerceivedLatencyData", e);
        }
    }
}
