package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.ApiType_MediaPlaybackListenerArgumentType;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class ApiType_MediaPlaybackListenerSender extends AlexaMessageSender<ApiType_MediaPlaybackListenerMessageType> implements AlexaMediaPlaybackListener {
    private static final String TAG = "ApiType_MediaPlaybackListenerSender";
    private final ExtendedClient client;
    private final MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    private static class OnMediaMetadata_com_amazon_alexa_api_AlexaMediaPlaybackMetadataPayload extends BaseMessagePayload {
        OnMediaMetadata_com_amazon_alexa_api_AlexaMediaPlaybackMetadataPayload(ExtendedClient extendedClient, AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata) {
            super(extendedClient);
            add((Bundles.Key) ApiType_MediaPlaybackListenerArgumentType.OnMediaMetadata_com_amazon_alexa_api_AlexaMediaPlaybackMetadataArgumentType.MEDIA_PLAYBACK_DATA, BundleTransformer.getDefaultInstance().toBundle(alexaMediaPlaybackMetadata));
        }
    }

    /* loaded from: classes6.dex */
    private static class OnMediaPlaybackStateUpdate_com_amazon_alexa_api_AlexaMediaPlaybackStatePayload extends BaseMessagePayload {
        OnMediaPlaybackStateUpdate_com_amazon_alexa_api_AlexaMediaPlaybackStatePayload(ExtendedClient extendedClient, AlexaMediaPlaybackState alexaMediaPlaybackState) {
            super(extendedClient);
            add((Bundles.Key) ApiType_MediaPlaybackListenerArgumentType.OnMediaPlaybackStateUpdate_com_amazon_alexa_api_AlexaMediaPlaybackStateArgumentType.MEDIA_PLAYBACK_STATE, BundleTransformer.getDefaultInstance().toBundle(alexaMediaPlaybackState));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_MediaPlaybackListenerSender(ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        this.client = extendedClient;
        this.messageReceiversManager = messageReceiversManager;
    }

    @Override // com.amazon.alexa.api.AlexaMediaPlaybackListener
    public void onMediaMetadata(AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata) {
        try {
            sendMessage(ApiType_MediaPlaybackListenerMessageType.ON_MEDIA_METADATA_COM_AMAZON_ALEXA_API_ALEXA_MEDIA_PLAYBACK_METADATA, new OnMediaMetadata_com_amazon_alexa_api_AlexaMediaPlaybackMetadataPayload(this.client, alexaMediaPlaybackMetadata).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onMediaMetadata_com_amazon_alexa_api_AlexaMediaPlaybackMetadata", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaMediaPlaybackListener
    public void onMediaPlaybackStateUpdate(AlexaMediaPlaybackState alexaMediaPlaybackState) {
        try {
            sendMessage(ApiType_MediaPlaybackListenerMessageType.ON_MEDIA_PLAYBACK_STATE_UPDATE_COM_AMAZON_ALEXA_API_ALEXA_MEDIA_PLAYBACK_STATE, new OnMediaPlaybackStateUpdate_com_amazon_alexa_api_AlexaMediaPlaybackStatePayload(this.client, alexaMediaPlaybackState).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onMediaPlaybackStateUpdate_com_amazon_alexa_api_AlexaMediaPlaybackState", e);
        }
    }
}
