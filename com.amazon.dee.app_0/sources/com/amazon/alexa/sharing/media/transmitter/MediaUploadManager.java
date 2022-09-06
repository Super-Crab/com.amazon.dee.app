package com.amazon.alexa.sharing.media.transmitter;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingException;
import com.amazon.alexa.sharing.api.exceptions.BridgeError;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.media.model.AmazonPhotosUploadRequest;
import com.amazon.alexa.sharing.media.model.CloudDriveService;
import com.amazon.alexa.sharing.media.model.SetupCompletedListener;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.dee.app.ui.web.JavaScriptResponse;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import rx.functions.Action1;
/* loaded from: classes10.dex */
public class MediaUploadManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MediaUploadManager.class);
    private static final String METRIC_SOURCE = MediaUploadManager.class.getSimpleName();
    private final FileTransmitter mFileTransmitter;
    private final CommsMetricsEmitter metricsEmitter;

    @Inject
    public MediaUploadManager(@NonNull FileTransmitter fileTransmitter, @NonNull CommsMetricsEmitter commsMetricsEmitter) {
        this.mFileTransmitter = fileTransmitter;
        this.metricsEmitter = commsMetricsEmitter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadFilesToCDSInternal(JsonArray jsonArray, final ResponseResolver responseResolver) {
        MediaUploadManager mediaUploadManager = this;
        final JsonArray jsonArray2 = new JsonArray();
        final int size = jsonArray.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        if (size == 0) {
            LOG.i("NO Files to upload -- Notify upload completed");
            mediaUploadManager.rejectWithFailureResponse(BridgeError.InvalidUploadRequestPayload, responseResolver);
            return;
        }
        AmazonPhotosUploadRequest[] extractUploadRequests = extractUploadRequests(jsonArray);
        int i = 0;
        while (i < extractUploadRequests.length) {
            final AmazonPhotosUploadRequest amazonPhotosUploadRequest = extractUploadRequests[i];
            Uri contentUri = amazonPhotosUploadRequest.getContentUri();
            String clientId = amazonPhotosUploadRequest.getClientId();
            LOG.i("[Sharing] PhotoMessage " + clientId + " - Uploading image file " + i + " of " + size);
            mediaUploadManager.mFileTransmitter.uploadImage(contentUri).subscribe(new Action1() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$MediaUploadManager$O97QmjqFpEsN6IsVlWIoHDqNNF8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MediaUploadManager.this.lambda$uploadFilesToCDSInternal$0$MediaUploadManager(jsonArray2, amazonPhotosUploadRequest, atomicInteger, size, responseResolver, (CloudDriveService.UploadResponse) obj);
                }
            }, new Action1() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$MediaUploadManager$6T3gFB--etwW9dxCL43sfzWTlYA
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MediaUploadManager.this.lambda$uploadFilesToCDSInternal$1$MediaUploadManager(jsonArray2, amazonPhotosUploadRequest, atomicInteger, size, responseResolver, (Throwable) obj);
                }
            });
            i++;
            mediaUploadManager = this;
        }
        LOG.i("[Sharing] ALL images have started Uploading.");
    }

    public JsonObject createMediaUploadFailureMap(@NonNull AmazonPhotosUploadRequest amazonPhotosUploadRequest, @NonNull Throwable th) {
        return createMediaUploadFailureMap(amazonPhotosUploadRequest.getConversationId(), amazonPhotosUploadRequest.getClientId(), amazonPhotosUploadRequest.getPhotoMessageMediaDataId(), amazonPhotosUploadRequest.getMediaPath(), th.toString());
    }

    public JsonObject createMediaUploadSuccessObject(@NonNull AmazonPhotosUploadRequest amazonPhotosUploadRequest, @NonNull String str, @NonNull String str2) {
        return createMediaUploadSuccessObject(amazonPhotosUploadRequest.getConversationId(), amazonPhotosUploadRequest.getClientId(), amazonPhotosUploadRequest.getPhotoMessageMediaDataId(), amazonPhotosUploadRequest.getMediaPath(), str, str2);
    }

    @VisibleForTesting
    AmazonPhotosUploadRequest[] extractUploadRequests(JsonArray jsonArray) {
        int size = jsonArray.size();
        AmazonPhotosUploadRequest[] amazonPhotosUploadRequestArr = new AmazonPhotosUploadRequest[size];
        for (int i = 0; i < size; i++) {
            amazonPhotosUploadRequestArr[i] = AmazonPhotosUploadRequest.fromJsonObject(jsonArray.get(i).getAsJsonObject());
        }
        return amazonPhotosUploadRequestArr;
    }

    public /* synthetic */ void lambda$uploadFilesToCDSInternal$0$MediaUploadManager(JsonArray jsonArray, AmazonPhotosUploadRequest amazonPhotosUploadRequest, AtomicInteger atomicInteger, int i, ResponseResolver responseResolver, CloudDriveService.UploadResponse uploadResponse) {
        jsonArray.add(createMediaUploadSuccessObject(amazonPhotosUploadRequest, uploadResponse.getId(), uploadResponse.getOwnerID()));
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Sharing] Uploaded image file ");
        outline107.append(atomicInteger.get() + 1);
        outline107.append(" of ");
        outline107.append(i);
        commsLogger.i(outline107.toString());
        if (atomicInteger.incrementAndGet() == i) {
            responseResolver.resolve(jsonArray);
        }
    }

    public /* synthetic */ void lambda$uploadFilesToCDSInternal$1$MediaUploadManager(JsonArray jsonArray, AmazonPhotosUploadRequest amazonPhotosUploadRequest, AtomicInteger atomicInteger, int i, ResponseResolver responseResolver, Throwable th) {
        LOG.e("[Sharing] An error occurred during the upload. Returning a failed image with error.", th);
        jsonArray.add(createMediaUploadFailureMap(amazonPhotosUploadRequest, th));
        if (atomicInteger.incrementAndGet() == i) {
            responseResolver.resolve(jsonArray);
        }
    }

    protected void rejectWithFailureResponse(@NonNull BridgeError bridgeError, @NonNull ResponseResolver responseResolver) {
        responseResolver.reject(new AlexaSharingException(bridgeError));
    }

    public void uploadFilesToCDS(final JsonArray jsonArray, final ResponseResolver responseResolver) {
        this.mFileTransmitter.init(new SetupCompletedListener() { // from class: com.amazon.alexa.sharing.media.transmitter.MediaUploadManager.1
            @Override // com.amazon.alexa.sharing.media.model.SetupCompletedListener
            public void onError(Exception exc) {
                MediaUploadManager.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_SETUP_ERROR, Collections.EMPTY_MAP);
                MediaUploadManager.this.metricsEmitter.recordCriticalEvent(MetricKeys.CLOUD_DRIVE_UPLOAD_SETUP_ERROR, exc.getClass().getSimpleName(), MediaUploadManager.METRIC_SOURCE, exc);
                MediaUploadManager.this.rejectWithFailureResponse(BridgeError.CDSUploadSetupFailure, responseResolver);
            }

            @Override // com.amazon.alexa.sharing.media.model.SetupCompletedListener
            public void onSuccess() {
                MediaUploadManager.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_SETUP_SUCCESS, Collections.EMPTY_MAP);
                MediaUploadManager.this.metricsEmitter.recordNonOccurrenceMetric(MetricKeys.CLOUD_DRIVE_UPLOAD_SETUP_ERROR, Collections.EMPTY_MAP);
                MediaUploadManager.this.uploadFilesToCDSInternal(jsonArray, responseResolver);
            }
        });
    }

    public JsonObject createMediaUploadSuccessObject(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("conversationId", str);
        jsonObject.addProperty(AuthorizationResponseParser.CLIENT_ID_STATE, str2);
        jsonObject.addProperty("photoMessageMediaDataId", str3);
        jsonObject.addProperty("mediaPath", str4);
        jsonObject.addProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, str5);
        jsonObject.addProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, str6);
        return jsonObject;
    }

    public JsonObject createMediaUploadFailureMap(@NonNull AmazonPhotosUploadRequest amazonPhotosUploadRequest, @NonNull String str) {
        return createMediaUploadFailureMap(amazonPhotosUploadRequest.getConversationId(), amazonPhotosUploadRequest.getClientId(), amazonPhotosUploadRequest.getPhotoMessageMediaDataId(), amazonPhotosUploadRequest.getMediaPath(), str);
    }

    public JsonObject createMediaUploadFailureMap(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull Throwable th) {
        return createMediaUploadFailureMap(str, str2, str3, str4, th.toString());
    }

    public JsonObject createMediaUploadFailureMap(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("conversationId", str);
        jsonObject.addProperty(AuthorizationResponseParser.CLIENT_ID_STATE, str2);
        jsonObject.addProperty("photoMessageMediaDataId", str3);
        jsonObject.addProperty("mediaPath", str4);
        jsonObject.addProperty(JavaScriptResponse.KEY_ERROR_REASON, str5);
        return jsonObject;
    }
}
