package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.ApiType_AttentionSystemListenerArgumentType;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class ApiType_AttentionSystemListenerSender extends AlexaMessageSender<ApiType_AttentionSystemListenerMessageType> implements AlexaAttentionSystemListener {
    private static final String TAG = "ApiType_AttentionSystemListenerSender";
    private final ExtendedClient client;
    private final MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    private static class OnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtrasPayload extends BaseMessagePayload {
        OnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtrasPayload(ExtendedClient extendedClient, AlexaState alexaState, AlexaStateExtras alexaStateExtras) {
            super(extendedClient);
            add((Bundles.Key) ApiType_AttentionSystemListenerArgumentType.OnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtrasArgumentType.ALEXA_STATE, BundleTransformer.getDefaultInstance().toBundle(alexaState));
            add(ApiType_AttentionSystemListenerArgumentType.OnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtrasArgumentType.ALEXA_STATE_EXTRAS, alexaStateExtras);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_AttentionSystemListenerSender(ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        this.client = extendedClient;
        this.messageReceiversManager = messageReceiversManager;
    }

    @Override // com.amazon.alexa.api.AlexaAttentionSystemListener
    public void onAlexaStateChanged(AlexaState alexaState, AlexaStateExtras alexaStateExtras) {
        try {
            sendMessage(ApiType_AttentionSystemListenerMessageType.ON_ALEXA_STATE_CHANGED_COM_AMAZON_ALEXA_API_ALEXA_STATE_COM_AMAZON_ALEXA_API_ALEXA_STATE_EXTRAS, new OnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtrasPayload(this.client, alexaState, alexaStateExtras).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtras", e);
        }
    }
}
