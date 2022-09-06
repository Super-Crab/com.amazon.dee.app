package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.ApiType_ArtifactDownloadListenerArgumentType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
final class ApiType_ArtifactDownloadListenerProcessor extends MessageProcessor<ApiType_ArtifactDownloadListenerMessageType> {
    private final ApiType_ArtifactDownloadListenerWrapper apiType_ArtifactDownloadListenerWrapper;

    /* renamed from: com.amazon.alexa.api.ApiType_ArtifactDownloadListenerProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$ApiType_ArtifactDownloadListenerMessageType = new int[ApiType_ArtifactDownloadListenerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_ArtifactDownloadListenerMessageType[ApiType_ArtifactDownloadListenerMessageType.ON_ARTIFACT_DOWNLOAD_SUCCESS_JAVA_UTIL_LOCALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_ArtifactDownloadListenerMessageType[ApiType_ArtifactDownloadListenerMessageType.ON_ARTIFACT_ALREADY_UP_TO_DATE_JAVA_UTIL_LOCALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_ArtifactDownloadListenerMessageType[ApiType_ArtifactDownloadListenerMessageType.ON_ARTIFACT_DOWNLOAD_FAILURE_COM_AMAZON_ALEXA_API_ARTIFACT_DOWNLOAD_LISTENER_FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_ArtifactDownloadListenerProcessor(AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        this.apiType_ArtifactDownloadListenerWrapper = new ApiType_ArtifactDownloadListenerWrapper(alexaArtifactDownloadListener);
    }

    private void processOnArtifactAlreadyUpToDate_java_util_Locale(Bundle bundle) {
        this.apiType_ArtifactDownloadListenerWrapper.onArtifactAlreadyUpToDate(Bundles.getLocale(bundle, ApiType_ArtifactDownloadListenerArgumentType.OnArtifactAlreadyUpToDate_java_util_LocaleArgumentType.LOCALE));
    }

    private void processOnArtifactDownloadFailure_com_amazon_alexa_api_ArtifactDownloadListenerFailure(Bundle bundle) {
        ArtifactDownloadListenerFailure artifactDownloadListenerFailure = (ArtifactDownloadListenerFailure) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, ApiType_ArtifactDownloadListenerArgumentType.OnArtifactDownloadFailure_com_amazon_alexa_api_ArtifactDownloadListenerFailureArgumentType.FAILURE), ArtifactDownloadListenerFailure.class);
        Preconditions.notNull(artifactDownloadListenerFailure, "failure cannot be null");
        this.apiType_ArtifactDownloadListenerWrapper.onArtifactDownloadFailure(artifactDownloadListenerFailure);
    }

    private void processOnArtifactDownloadSuccess_java_util_Locale(Bundle bundle) {
        this.apiType_ArtifactDownloadListenerWrapper.onArtifactDownloadSuccess(Bundles.getLocale(bundle, ApiType_ArtifactDownloadListenerArgumentType.OnArtifactDownloadSuccess_java_util_LocaleArgumentType.LOCALE));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ApiType_ArtifactDownloadListenerMessageType mo845getMessageType(Message message) {
        try {
            return ApiType_ArtifactDownloadListenerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return ApiType_ArtifactDownloadListenerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ApiType_ArtifactDownloadListenerMessageType apiType_ArtifactDownloadListenerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        int ordinal = apiType_ArtifactDownloadListenerMessageType.ordinal();
        if (ordinal == 1) {
            processOnArtifactDownloadSuccess_java_util_Locale(bundle);
        } else if (ordinal == 2) {
            processOnArtifactAlreadyUpToDate_java_util_Locale(bundle);
        } else if (ordinal == 3) {
            processOnArtifactDownloadFailure_com_amazon_alexa_api_ArtifactDownloadListenerFailure(bundle);
        } else {
            String simpleName = ApiType_ArtifactDownloadListenerProcessor.class.getSimpleName();
            Log.w(simpleName, "Unsupported message: " + apiType_ArtifactDownloadListenerMessageType);
        }
    }
}
