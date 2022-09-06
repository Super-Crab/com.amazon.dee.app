package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.ApiType_ArtifactDownloadListenerArgumentType;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class ApiType_ArtifactDownloadListenerSender extends AlexaMessageSender<ApiType_ArtifactDownloadListenerMessageType> implements AlexaArtifactDownloadListener {
    private static final String TAG = "ApiType_ArtifactDownloadListenerSender";
    private final ExtendedClient client;
    private final MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    private static class OnArtifactAlreadyUpToDate_java_util_LocalePayload extends BaseMessagePayload {
        OnArtifactAlreadyUpToDate_java_util_LocalePayload(ExtendedClient extendedClient, java.util.Locale locale) {
            super(extendedClient);
            add(ApiType_ArtifactDownloadListenerArgumentType.OnArtifactAlreadyUpToDate_java_util_LocaleArgumentType.LOCALE, locale);
        }
    }

    /* loaded from: classes6.dex */
    private static class OnArtifactDownloadFailure_com_amazon_alexa_api_ArtifactDownloadListenerFailurePayload extends BaseMessagePayload {
        OnArtifactDownloadFailure_com_amazon_alexa_api_ArtifactDownloadListenerFailurePayload(ExtendedClient extendedClient, ArtifactDownloadListenerFailure artifactDownloadListenerFailure) {
            super(extendedClient);
            add((Bundles.Key) ApiType_ArtifactDownloadListenerArgumentType.OnArtifactDownloadFailure_com_amazon_alexa_api_ArtifactDownloadListenerFailureArgumentType.FAILURE, BundleTransformer.getDefaultInstance().toBundle(artifactDownloadListenerFailure));
        }
    }

    /* loaded from: classes6.dex */
    private static class OnArtifactDownloadSuccess_java_util_LocalePayload extends BaseMessagePayload {
        OnArtifactDownloadSuccess_java_util_LocalePayload(ExtendedClient extendedClient, java.util.Locale locale) {
            super(extendedClient);
            add(ApiType_ArtifactDownloadListenerArgumentType.OnArtifactDownloadSuccess_java_util_LocaleArgumentType.LOCALE, locale);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_ArtifactDownloadListenerSender(ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        this.client = extendedClient;
        this.messageReceiversManager = messageReceiversManager;
    }

    @Override // com.amazon.alexa.api.AlexaArtifactDownloadListener
    public void onArtifactAlreadyUpToDate(java.util.Locale locale) {
        try {
            sendMessage(ApiType_ArtifactDownloadListenerMessageType.ON_ARTIFACT_ALREADY_UP_TO_DATE_JAVA_UTIL_LOCALE, new OnArtifactAlreadyUpToDate_java_util_LocalePayload(this.client, locale).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onArtifactAlreadyUpToDate_java_util_Locale", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaArtifactDownloadListener
    public void onArtifactDownloadFailure(ArtifactDownloadListenerFailure artifactDownloadListenerFailure) {
        try {
            sendMessage(ApiType_ArtifactDownloadListenerMessageType.ON_ARTIFACT_DOWNLOAD_FAILURE_COM_AMAZON_ALEXA_API_ARTIFACT_DOWNLOAD_LISTENER_FAILURE, new OnArtifactDownloadFailure_com_amazon_alexa_api_ArtifactDownloadListenerFailurePayload(this.client, artifactDownloadListenerFailure).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onArtifactDownloadFailure_com_amazon_alexa_api_ArtifactDownloadListenerFailure", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaArtifactDownloadListener
    public void onArtifactDownloadSuccess(java.util.Locale locale) {
        try {
            sendMessage(ApiType_ArtifactDownloadListenerMessageType.ON_ARTIFACT_DOWNLOAD_SUCCESS_JAVA_UTIL_LOCALE, new OnArtifactDownloadSuccess_java_util_LocalePayload(this.client, locale).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onArtifactDownloadSuccess_java_util_Locale", e);
        }
    }
}
