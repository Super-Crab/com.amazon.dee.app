package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.ApiType_CaptionListenerArgumentType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
final class ApiType_CaptionListenerProcessor extends MessageProcessor<ApiType_CaptionListenerMessageType> {
    private final ApiType_CaptionListenerWrapper apiType_CaptionListenerWrapper;

    /* renamed from: com.amazon.alexa.api.ApiType_CaptionListenerProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$ApiType_CaptionListenerMessageType = new int[ApiType_CaptionListenerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_CaptionListenerMessageType[ApiType_CaptionListenerMessageType.ON_RECEIVED_CAPTION_COM_AMAZON_ALEXA_API_CAPTION_RESPONSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_CaptionListenerProcessor(AlexaCaptionListener alexaCaptionListener) {
        this.apiType_CaptionListenerWrapper = new ApiType_CaptionListenerWrapper(alexaCaptionListener);
    }

    private void processOnReceivedCaption_com_amazon_alexa_api_CaptionResponse(Bundle bundle) {
        CaptionResponse captionResponse = (CaptionResponse) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, ApiType_CaptionListenerArgumentType.OnReceivedCaption_com_amazon_alexa_api_CaptionResponseArgumentType.CAPTION_DATA), CaptionResponse.class);
        Preconditions.notNull(captionResponse, "captionData cannot be null");
        this.apiType_CaptionListenerWrapper.onReceivedCaption(captionResponse);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ApiType_CaptionListenerMessageType mo845getMessageType(Message message) {
        try {
            return ApiType_CaptionListenerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return ApiType_CaptionListenerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ApiType_CaptionListenerMessageType apiType_CaptionListenerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        if (apiType_CaptionListenerMessageType.ordinal() == 1) {
            processOnReceivedCaption_com_amazon_alexa_api_CaptionResponse(bundle);
            return;
        }
        String simpleName = ApiType_CaptionListenerProcessor.class.getSimpleName();
        Log.w(simpleName, "Unsupported message: " + apiType_CaptionListenerMessageType);
    }
}
