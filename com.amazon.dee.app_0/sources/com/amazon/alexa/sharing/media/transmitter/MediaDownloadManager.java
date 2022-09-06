package com.amazon.alexa.sharing.media.transmitter;

import androidx.annotation.NonNull;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingException;
import com.amazon.alexa.sharing.api.exceptions.BridgeError;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.media.model.AmazonPhotosDownloadRequest;
import com.amazon.alexa.sharing.media.model.CloudDriveService;
import com.amazon.alexa.sharing.media.model.SetupCompletedListener;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.dee.app.ui.web.JavaScriptResponse;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes10.dex */
public class MediaDownloadManager {
    private static final String MEDIA_DOWNLOADED = "CommsFileSharingDownloadFinishedNotification";
    private final CommsBridgeService commsBridgeService;
    private final FileTransmitter mFileTransmitter;
    private final CommsMetricsEmitter metricsEmitter;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MediaDownloadManager.class);
    private static final String METRIC_SOURCE = MediaDownloadManager.class.getSimpleName();

    @Inject
    public MediaDownloadManager(@NonNull FileTransmitter fileTransmitter, @NonNull CommsBridgeService commsBridgeService, @NonNull CommsMetricsEmitter commsMetricsEmitter) {
        this.mFileTransmitter = fileTransmitter;
        this.commsBridgeService = commsBridgeService;
        this.metricsEmitter = commsMetricsEmitter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadFilesFromCDSInternal(JsonArray jsonArray, final ResponseResolver responseResolver) {
        Observable<CloudDriveService.DownloadResponse> downloadImage;
        MediaDownloadManager mediaDownloadManager = this;
        JsonArray jsonArray2 = new JsonArray();
        final int size = jsonArray.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        if (size == 0) {
            mediaDownloadManager.returnPromiseRejectionForDownloads(BridgeError.InvalidDownloadRequestPayload, jsonArray, responseResolver);
            return;
        }
        int i = 0;
        for (AmazonPhotosDownloadRequest[] extractDownloadRequests = extractDownloadRequests(jsonArray); i < extractDownloadRequests.length; extractDownloadRequests = extractDownloadRequests) {
            final AmazonPhotosDownloadRequest amazonPhotosDownloadRequest = extractDownloadRequests[i];
            final String source = amazonPhotosDownloadRequest.getSource();
            final String globalMessageId = amazonPhotosDownloadRequest.getGlobalMessageId();
            final String conversationId = amazonPhotosDownloadRequest.getConversationId();
            final String clientId = amazonPhotosDownloadRequest.getClientId();
            final String dimension = amazonPhotosDownloadRequest.getDimension();
            final String nodeId = amazonPhotosDownloadRequest.getNodeId();
            String ownerId = amazonPhotosDownloadRequest.getOwnerId();
            if (amazonPhotosDownloadRequest.isThumbnail()) {
                downloadImage = mediaDownloadManager.mFileTransmitter.downloadThumbnail(nodeId, amazonPhotosDownloadRequest.getViewBox(), ownerId);
            } else {
                downloadImage = mediaDownloadManager.mFileTransmitter.downloadImage(nodeId, dimension, ownerId);
            }
            Observable<CloudDriveService.DownloadResponse> observable = downloadImage;
            LOG.i("[Sharing] PhotoMessage " + clientId + " - Downloading image file " + i + " of " + size + " : " + nodeId);
            final JsonArray jsonArray3 = jsonArray2;
            int i2 = i;
            final AtomicInteger atomicInteger2 = atomicInteger;
            Action1<? super CloudDriveService.DownloadResponse> action1 = new Action1() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$MediaDownloadManager$JziKzFI8T67ggdjyo3OaLKk40v8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MediaDownloadManager.this.lambda$downloadFilesFromCDSInternal$0$MediaDownloadManager(source, globalMessageId, conversationId, clientId, dimension, nodeId, jsonArray3, atomicInteger2, size, responseResolver, (CloudDriveService.DownloadResponse) obj);
                }
            };
            final JsonArray jsonArray4 = jsonArray2;
            final AtomicInteger atomicInteger3 = atomicInteger;
            observable.subscribe(action1, new Action1() { // from class: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$MediaDownloadManager$R9LfjoktWWVz9AgQCF73OLgwyyg
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MediaDownloadManager.this.lambda$downloadFilesFromCDSInternal$1$MediaDownloadManager(clientId, nodeId, jsonArray4, amazonPhotosDownloadRequest, atomicInteger3, size, responseResolver, source, conversationId, globalMessageId, dimension, (Throwable) obj);
                }
            });
            i = i2 + 1;
            mediaDownloadManager = this;
            jsonArray2 = jsonArray2;
            atomicInteger = atomicInteger;
        }
        LOG.i("[Sharing] ALL images have started downloading.");
    }

    private AmazonPhotosDownloadRequest[] extractDownloadRequests(JsonArray jsonArray) {
        int size = jsonArray.size();
        AmazonPhotosDownloadRequest[] amazonPhotosDownloadRequestArr = new AmazonPhotosDownloadRequest[size];
        for (int i = 0; i < size; i++) {
            amazonPhotosDownloadRequestArr[i] = AmazonPhotosDownloadRequest.fromJsonObject(jsonArray.get(i).getAsJsonObject());
        }
        return amazonPhotosDownloadRequestArr;
    }

    public JsonObject createMediaDownloadFailureObject(@NonNull AmazonPhotosDownloadRequest amazonPhotosDownloadRequest, @NonNull Throwable th) {
        return createMediaDownloadFailureObject(amazonPhotosDownloadRequest.getSource(), amazonPhotosDownloadRequest.getGlobalMessageId(), amazonPhotosDownloadRequest.getConversationId(), amazonPhotosDownloadRequest.getClientId(), amazonPhotosDownloadRequest.getDimension(), amazonPhotosDownloadRequest.getNodeId(), th.toString());
    }

    public JsonObject createMediaDownloadSuccessObject(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7, @NonNull String str8, @NonNull String str9, @NonNull Integer num, @NonNull Integer num2) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("source", str);
        jsonObject.addProperty("globalMessageId", str2);
        jsonObject.addProperty("conversationId", str3);
        jsonObject.addProperty(AuthorizationResponseParser.CLIENT_ID_STATE, str4);
        jsonObject.addProperty("dimension", str5);
        jsonObject.addProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, str6);
        jsonObject.addProperty("localMediaPath", "file://" + str7);
        jsonObject.addProperty("mediaType", str8);
        jsonObject.addProperty("width", num2);
        jsonObject.addProperty("height", num);
        jsonObject.addProperty("fileExtension", str9);
        return jsonObject;
    }

    public void downloadFilesFromCDS(final JsonArray jsonArray, final ResponseResolver responseResolver) {
        this.mFileTransmitter.init(new SetupCompletedListener() { // from class: com.amazon.alexa.sharing.media.transmitter.MediaDownloadManager.1
            @Override // com.amazon.alexa.sharing.media.model.SetupCompletedListener
            public void onError(Exception exc) {
                MediaDownloadManager.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_DOWNLOAD_SETUP_ERROR, Collections.EMPTY_MAP);
                MediaDownloadManager.this.metricsEmitter.recordCriticalEvent(MetricKeys.CLOUD_DRIVE_DOWNLOAD_SETUP_ERROR, exc.getClass().getSimpleName(), MediaDownloadManager.METRIC_SOURCE, exc);
                MediaDownloadManager.this.returnPromiseRejectionForDownloads(BridgeError.CDSDownloadSetupFailure, jsonArray, responseResolver);
            }

            @Override // com.amazon.alexa.sharing.media.model.SetupCompletedListener
            public void onSuccess() {
                MediaDownloadManager.this.metricsEmitter.recordOccurrenceMetric(MetricKeys.CLOUD_DRIVE_DOWNLOAD_SETUP_SUCCESS, Collections.EMPTY_MAP);
                MediaDownloadManager.this.metricsEmitter.recordNonOccurrenceMetric(MetricKeys.CLOUD_DRIVE_DOWNLOAD_SETUP_ERROR, Collections.EMPTY_MAP);
                MediaDownloadManager.this.downloadFilesFromCDSInternal(jsonArray, responseResolver);
            }
        });
    }

    public boolean fileExists(String str) {
        return this.mFileTransmitter.fileExists(str);
    }

    public String getLocalUrlForDownloadedMedia(String str, int i, String str2) {
        return this.mFileTransmitter.getLocalFilePath(String.format("%s_%d%s", str, Integer.valueOf(i), str2));
    }

    public /* synthetic */ void lambda$downloadFilesFromCDSInternal$0$MediaDownloadManager(String str, String str2, String str3, String str4, String str5, String str6, JsonArray jsonArray, AtomicInteger atomicInteger, int i, ResponseResolver responseResolver, CloudDriveService.DownloadResponse downloadResponse) {
        JsonObject createMediaDownloadSuccessObject = createMediaDownloadSuccessObject(str, str2, str3, str4, str5, str6, downloadResponse.getLocalMediaPath(), downloadResponse.getMediaType(), downloadResponse.getFileExtension(), Integer.valueOf(downloadResponse.getHeight()), Integer.valueOf(downloadResponse.getWidth()));
        jsonArray.add(createMediaDownloadSuccessObject);
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Sharing] Successfully Downloaded image file ");
        outline107.append(atomicInteger.get() + 1);
        outline107.append(" of ");
        outline107.append(i);
        outline107.append(" : ");
        outline107.append(str6);
        commsLogger.i(outline107.toString());
        notifyDownloadComplete(createMediaDownloadSuccessObject);
        if (atomicInteger.incrementAndGet() == i) {
            if (responseResolver != null) {
                responseResolver.resolve(jsonArray);
            }
            CommsLogger commsLogger2 = LOG;
            commsLogger2.i("[Sharing] PhotoMessage " + str4 + " - ALL images downloaded. Promise will be resolved.");
            return;
        }
        CommsLogger commsLogger3 = LOG;
        commsLogger3.i("[Sharing] PhotoMessage " + str4 + " - Waiting for additional image downloads to complete.");
    }

    public /* synthetic */ void lambda$downloadFilesFromCDSInternal$1$MediaDownloadManager(String str, String str2, JsonArray jsonArray, AmazonPhotosDownloadRequest amazonPhotosDownloadRequest, AtomicInteger atomicInteger, int i, ResponseResolver responseResolver, String str3, String str4, String str5, String str6, Throwable th) {
        LOG.e(GeneratedOutlineSupport1.outline77("[Sharing] PhotoMessage ", str, " - An error occurred during the download of nodeId <", str2, ">. Returning a failed image with error."), th);
        jsonArray.add(createMediaDownloadFailureObject(amazonPhotosDownloadRequest, th));
        if (atomicInteger.incrementAndGet() == i) {
            if (responseResolver != null) {
                responseResolver.resolve(jsonArray);
            }
            CommsLogger commsLogger = LOG;
            commsLogger.i("[Sharing] PhotoMessage " + str + " - ALL images downloaded. Promise will be resolved.");
        }
        notifyDownloadComplete(createMediaDownloadFailureObject(str3, str4, str5, str, str6, str2, th));
    }

    public void notifyDownloadComplete(@NonNull JsonObject jsonObject) {
        this.commsBridgeService.emitEventToReact(MEDIA_DOWNLOADED, jsonObject);
    }

    protected void returnPromiseRejectionForDownloads(BridgeError bridgeError, JsonArray jsonArray, ResponseResolver responseResolver) {
        AmazonPhotosDownloadRequest[] extractDownloadRequests;
        for (AmazonPhotosDownloadRequest amazonPhotosDownloadRequest : extractDownloadRequests(jsonArray)) {
            String source = amazonPhotosDownloadRequest.getSource();
            String globalMessageId = amazonPhotosDownloadRequest.getGlobalMessageId();
            String conversationId = amazonPhotosDownloadRequest.getConversationId();
            String clientId = amazonPhotosDownloadRequest.getClientId();
            String dimension = amazonPhotosDownloadRequest.getDimension();
            String nodeId = amazonPhotosDownloadRequest.getNodeId();
            LOG.e(GeneratedOutlineSupport1.outline75("[Sharing] An error occurred during setup <", nodeId, ">. Returning a failed image with error."), bridgeError.message());
            notifyDownloadComplete(createMediaDownloadFailureObject(source, globalMessageId, conversationId, clientId, dimension, nodeId, bridgeError.message()));
        }
        responseResolver.reject(new AlexaSharingException(bridgeError));
    }

    public JsonObject createMediaDownloadFailureObject(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull Throwable th) {
        return createMediaDownloadFailureObject(str, str2, str3, str4, str5, str6, th.toString());
    }

    public JsonObject createMediaDownloadFailureObject(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("source", str);
        jsonObject.addProperty("globalMessageId", str2);
        jsonObject.addProperty("conversationId", str3);
        jsonObject.addProperty(AuthorizationResponseParser.CLIENT_ID_STATE, str4);
        jsonObject.addProperty("dimension", str5);
        jsonObject.addProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, str6);
        jsonObject.addProperty(JavaScriptResponse.KEY_ERROR_REASON, str7);
        return jsonObject;
    }
}
