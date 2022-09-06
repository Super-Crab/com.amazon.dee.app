package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.ApiType_AttentionSystemListenerArgumentType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
final class ApiType_AttentionSystemListenerProcessor extends MessageProcessor<ApiType_AttentionSystemListenerMessageType> {
    private final ApiType_AttentionSystemListenerWrapper apiType_AttentionSystemListenerWrapper;

    /* renamed from: com.amazon.alexa.api.ApiType_AttentionSystemListenerProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$ApiType_AttentionSystemListenerMessageType = new int[ApiType_AttentionSystemListenerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_AttentionSystemListenerMessageType[ApiType_AttentionSystemListenerMessageType.ON_ALEXA_STATE_CHANGED_COM_AMAZON_ALEXA_API_ALEXA_STATE_COM_AMAZON_ALEXA_API_ALEXA_STATE_EXTRAS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_AttentionSystemListenerProcessor(AlexaAttentionSystemListener alexaAttentionSystemListener) {
        this.apiType_AttentionSystemListenerWrapper = new ApiType_AttentionSystemListenerWrapper(alexaAttentionSystemListener);
    }

    private void processOnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtras(Bundle bundle) {
        AlexaState alexaState = (AlexaState) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, ApiType_AttentionSystemListenerArgumentType.OnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtrasArgumentType.ALEXA_STATE), AlexaState.class);
        Preconditions.notNull(alexaState, "alexaState cannot be null");
        this.apiType_AttentionSystemListenerWrapper.onAlexaStateChanged(alexaState, (AlexaStateExtras) Bundles.getParcelable(bundle, ApiType_AttentionSystemListenerArgumentType.OnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtrasArgumentType.ALEXA_STATE_EXTRAS, AlexaStateExtras.class));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ApiType_AttentionSystemListenerMessageType mo845getMessageType(Message message) {
        try {
            return ApiType_AttentionSystemListenerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return ApiType_AttentionSystemListenerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ApiType_AttentionSystemListenerMessageType apiType_AttentionSystemListenerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        if (apiType_AttentionSystemListenerMessageType.ordinal() == 1) {
            processOnAlexaStateChanged_com_amazon_alexa_api_AlexaState_com_amazon_alexa_api_AlexaStateExtras(bundle);
            return;
        }
        String simpleName = ApiType_AttentionSystemListenerProcessor.class.getSimpleName();
        Log.w(simpleName, "Unsupported message: " + apiType_AttentionSystemListenerMessageType);
    }
}
