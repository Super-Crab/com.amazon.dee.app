package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.ApiType_MediaPlaybackListenerArgumentType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class ApiType_MediaPlaybackListenerProcessor extends MessageProcessor<ApiType_MediaPlaybackListenerMessageType> {
    private final ApiType_MediaPlaybackListenerWrapper apiType_MediaPlaybackListenerWrapper;

    /* renamed from: com.amazon.alexa.api.ApiType_MediaPlaybackListenerProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$ApiType_MediaPlaybackListenerMessageType = new int[ApiType_MediaPlaybackListenerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_MediaPlaybackListenerMessageType[ApiType_MediaPlaybackListenerMessageType.ON_MEDIA_METADATA_COM_AMAZON_ALEXA_API_ALEXA_MEDIA_PLAYBACK_METADATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_MediaPlaybackListenerMessageType[ApiType_MediaPlaybackListenerMessageType.ON_MEDIA_PLAYBACK_STATE_UPDATE_COM_AMAZON_ALEXA_API_ALEXA_MEDIA_PLAYBACK_STATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_MediaPlaybackListenerProcessor(AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        this.apiType_MediaPlaybackListenerWrapper = new ApiType_MediaPlaybackListenerWrapper(alexaMediaPlaybackListener);
    }

    private void processOnMediaMetadata_com_amazon_alexa_api_AlexaMediaPlaybackMetadata(Bundle bundle) {
        AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata = (AlexaMediaPlaybackMetadata) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, ApiType_MediaPlaybackListenerArgumentType.OnMediaMetadata_com_amazon_alexa_api_AlexaMediaPlaybackMetadataArgumentType.MEDIA_PLAYBACK_DATA), AlexaMediaPlaybackMetadata.class);
        Preconditions.notNull(alexaMediaPlaybackMetadata, "mediaPlaybackData cannot be null");
        this.apiType_MediaPlaybackListenerWrapper.onMediaMetadata(alexaMediaPlaybackMetadata);
    }

    private void processOnMediaPlaybackStateUpdate_com_amazon_alexa_api_AlexaMediaPlaybackState(Bundle bundle) {
        AlexaMediaPlaybackState alexaMediaPlaybackState = (AlexaMediaPlaybackState) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, ApiType_MediaPlaybackListenerArgumentType.OnMediaPlaybackStateUpdate_com_amazon_alexa_api_AlexaMediaPlaybackStateArgumentType.MEDIA_PLAYBACK_STATE), AlexaMediaPlaybackState.class);
        Preconditions.notNull(alexaMediaPlaybackState, "mediaPlaybackState cannot be null");
        this.apiType_MediaPlaybackListenerWrapper.onMediaPlaybackStateUpdate(alexaMediaPlaybackState);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ApiType_MediaPlaybackListenerMessageType mo845getMessageType(Message message) {
        try {
            return ApiType_MediaPlaybackListenerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return ApiType_MediaPlaybackListenerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ApiType_MediaPlaybackListenerMessageType apiType_MediaPlaybackListenerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        int ordinal = apiType_MediaPlaybackListenerMessageType.ordinal();
        if (ordinal == 1) {
            processOnMediaMetadata_com_amazon_alexa_api_AlexaMediaPlaybackMetadata(bundle);
        } else if (ordinal == 2) {
            processOnMediaPlaybackStateUpdate_com_amazon_alexa_api_AlexaMediaPlaybackState(bundle);
        } else {
            String simpleName = ApiType_MediaPlaybackListenerProcessor.class.getSimpleName();
            Log.w(simpleName, "Unsupported message: " + apiType_MediaPlaybackListenerMessageType);
        }
    }
}
