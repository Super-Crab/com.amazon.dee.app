package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.ApiType_CaptionListenerArgumentType;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class ApiType_CaptionListenerSender extends AlexaMessageSender<ApiType_CaptionListenerMessageType> implements AlexaCaptionListener {
    private static final String TAG = "ApiType_CaptionListenerSender";
    private final ExtendedClient client;
    private final MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    private static class OnReceivedCaption_com_amazon_alexa_api_CaptionResponsePayload extends BaseMessagePayload {
        OnReceivedCaption_com_amazon_alexa_api_CaptionResponsePayload(ExtendedClient extendedClient, CaptionResponse captionResponse) {
            super(extendedClient);
            add((Bundles.Key) ApiType_CaptionListenerArgumentType.OnReceivedCaption_com_amazon_alexa_api_CaptionResponseArgumentType.CAPTION_DATA, BundleTransformer.getDefaultInstance().toBundle(captionResponse));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_CaptionListenerSender(ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        this.client = extendedClient;
        this.messageReceiversManager = messageReceiversManager;
    }

    @Override // com.amazon.alexa.api.AlexaCaptionListener
    public void onReceivedCaption(CaptionResponse captionResponse) {
        try {
            sendMessage(ApiType_CaptionListenerMessageType.ON_RECEIVED_CAPTION_COM_AMAZON_ALEXA_API_CAPTION_RESPONSE, new OnReceivedCaption_com_amazon_alexa_api_CaptionResponsePayload(this.client, captionResponse).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onReceivedCaption_com_amazon_alexa_api_CaptionResponse", e);
        }
    }
}
