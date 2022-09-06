package com.amazon.deecomms.nativemodules;

import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.audio.MediaStorageServiceAudio;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.MediaStorageServiceClient;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.media.audio.AudioPlayer;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.media.model.MediaCreateResponse;
import com.amazon.deecomms.media.model.MediaFileContent;
import com.amazon.deecomms.media.photos.CloudDriveService;
import com.amazon.deecomms.media.photos.FileTransmitter;
import com.amazon.deecomms.media.photos.SetupCompletedListener;
import com.amazon.deecomms.messaging.model.client.ClientMessage;
import com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier;
import com.amazon.deecomms.messaging.model.payload.AudioMessagePayload;
import com.amazon.deecomms.messaging.util.AudioMessageUtils;
import com.amazon.deecomms.nativemodules.model.MediaMetadata;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.notifications.model.announcement.AudioAnnouncementPayload;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import dagger.Lazy;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class CommsMessagingMediaBridge extends CommsReactContextBaseJavaModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsMessagingMediaBridge.class);
    private final AudioContentManager audioContentManager;
    private final AudioPlayer audioPlayer;
    private final AudioRecorder audioRecorder;
    private final CapabilitiesManager capabilitiesManager;
    private final FeatureServiceV2.FeatureUpdateListener featureUpdateListener;
    private final ReactApplicationContext mContext;
    private final Lazy<FileTransmitter> mFileTransmitter;
    private final ReactBridgeSerializer mReactBridgeSerializer;
    private AudioRecordingState recordingState;
    private final Object recordingStateSync;

    public CommsMessagingMediaBridge(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, CommsDaggerWrapper.getComponent().getAudioContentManager(), CommsDaggerWrapper.getComponent().getAudioPlayer(), CommsDaggerWrapper.getComponent().getAudioRecorder(), new ReactBridgeSerializer(reactApplicationContext), $$Lambda$CommsMessagingMediaBridge$c4A4eJX128rDRRupMK_3nysxyk0.INSTANCE, CommsDaggerWrapper.getComponent().getCapabilitiesManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadFilesFromCDSInternal(ReadableArray readableArray, final Promise promise) {
        Observable<CloudDriveService.DownloadResponse> downloadImage;
        CommsMessagingMediaBridge commsMessagingMediaBridge = this;
        WritableArray createArray = Arguments.createArray();
        final int size = readableArray.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        if (size == 0) {
            commsMessagingMediaBridge.returnPromiseRejectionForDownloads("Error missing downloads", readableArray, promise);
            return;
        }
        AmazonPhotosDownloadRequest[] extractDownloadFromReactNative = extractDownloadFromReactNative(readableArray);
        int i = 0;
        while (i < extractDownloadFromReactNative.length) {
            final AmazonPhotosDownloadRequest amazonPhotosDownloadRequest = extractDownloadFromReactNative[i];
            final String conversationId = amazonPhotosDownloadRequest.getConversationId();
            final String clientId = amazonPhotosDownloadRequest.getClientId();
            final String dimension = amazonPhotosDownloadRequest.getDimension();
            final String nodeId = amazonPhotosDownloadRequest.getNodeId();
            String ownerId = amazonPhotosDownloadRequest.getOwnerId();
            if (amazonPhotosDownloadRequest.isThumbnail()) {
                downloadImage = commsMessagingMediaBridge.mFileTransmitter.mo358get().downloadThumbnail(nodeId, amazonPhotosDownloadRequest.getViewBox(), ownerId);
            } else {
                downloadImage = commsMessagingMediaBridge.mFileTransmitter.mo358get().downloadImage(nodeId, dimension, ownerId);
            }
            Observable<CloudDriveService.DownloadResponse> observable = downloadImage;
            LOG.i("[Sharing] PhotoMessage " + clientId + " - Downloading image file " + i + " of " + size + " : " + nodeId);
            final WritableArray writableArray = createArray;
            final WritableArray writableArray2 = createArray;
            observable.subscribe(new Action1() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$Y6br1Xu5Nf5fiq_8PS9Haa-ZE6M
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommsMessagingMediaBridge.lambda$downloadFilesFromCDSInternal$10(WritableArray.this, conversationId, clientId, dimension, nodeId, atomicInteger, size, promise, (CloudDriveService.DownloadResponse) obj);
                }
            }, new Action1() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$meZ--NFsyZbLh9RXpLo77_K22Jw
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommsMessagingMediaBridge.lambda$downloadFilesFromCDSInternal$11(clientId, nodeId, writableArray2, amazonPhotosDownloadRequest, atomicInteger, size, promise, conversationId, dimension, (Throwable) obj);
                }
            });
            i++;
            commsMessagingMediaBridge = this;
            createArray = createArray;
        }
        LOG.i("[Sharing] ALL images have started downloading.");
    }

    private AmazonPhotosDownloadRequest[] extractDownloadFromReactNative(ReadableArray readableArray) {
        int size = readableArray.size();
        AmazonPhotosDownloadRequest[] amazonPhotosDownloadRequestArr = new AmazonPhotosDownloadRequest[size];
        for (int i = 0; i < size; i++) {
            amazonPhotosDownloadRequestArr[i] = AmazonPhotosDownloadRequest.extractFromReadableMap(readableArray.mo6944getMap(i));
        }
        return amazonPhotosDownloadRequestArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$downloadFilesFromCDSInternal$10(WritableArray writableArray, String str, String str2, String str3, String str4, AtomicInteger atomicInteger, int i, Promise promise, CloudDriveService.DownloadResponse downloadResponse) {
        writableArray.pushMap(ReactBridgeSerializer.createMediaDownloadSuccessMap(str, str2, str3, str4, downloadResponse.getLocalMediaPath(), downloadResponse.getMediaType(), downloadResponse.getFileExtension(), Integer.valueOf(downloadResponse.getHeight()), Integer.valueOf(downloadResponse.getWidth())));
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Sharing] Successfully Downloaded image file ");
        outline1.append(atomicInteger.get() + 1);
        outline1.append(" of ");
        outline1.append(i);
        outline1.append(" : ");
        outline1.append(str4);
        commsLogger.i(outline1.toString());
        CommsEventEmitterBridge.notifyDownloadComplete(ReactBridgeSerializer.createMediaDownloadSuccessMap(str, str2, str3, str4, downloadResponse.getLocalMediaPath(), downloadResponse.getMediaType(), downloadResponse.getFileExtension(), Integer.valueOf(downloadResponse.getHeight()), Integer.valueOf(downloadResponse.getWidth())));
        if (atomicInteger.incrementAndGet() == i) {
            promise.resolve(writableArray);
            CommsLogger commsLogger2 = LOG;
            commsLogger2.i("[Sharing] PhotoMessage " + str2 + " - ALL images downloaded. Promise will be resolved.");
            return;
        }
        CommsLogger commsLogger3 = LOG;
        commsLogger3.i("[Sharing] PhotoMessage " + str2 + " - Waiting for additional image downloads to complete.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$downloadFilesFromCDSInternal$11(String str, String str2, WritableArray writableArray, AmazonPhotosDownloadRequest amazonPhotosDownloadRequest, AtomicInteger atomicInteger, int i, Promise promise, String str3, String str4, Throwable th) {
        LOG.e(GeneratedOutlineSupport1.outline77("[Sharing] PhotoMessage ", str, " - An error occurred during the download of nodeId <", str2, ">. Returning a failed image with error."), th);
        writableArray.pushMap(ReactBridgeSerializer.createMediaDownloadFailureMap(amazonPhotosDownloadRequest, th));
        if (atomicInteger.incrementAndGet() == i) {
            promise.resolve(writableArray);
            CommsLogger commsLogger = LOG;
            commsLogger.i("[Sharing] PhotoMessage " + str + " - ALL images downloaded. Promise will be resolved.");
        }
        CommsEventEmitterBridge.notifyDownloadComplete(ReactBridgeSerializer.createMediaDownloadFailureMap(str3, str, str4, str2, th));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$playAudioFile$5(String str, MediaPlayer mediaPlayer) {
        CommsLogger commsLogger = LOG;
        commsLogger.d("Played media: %s", commsLogger.sensitive(str));
        CommsEventEmitterBridge.notifyAudioFileDidFinishPlaying(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$playAudioFile$6(String str, MediaPlayer mediaPlayer, int i, int i2) {
        CommsLogger commsLogger = LOG;
        commsLogger.e("Failed playing media: %s, what=%s, extra=%d", commsLogger.sensitive(str), Integer.valueOf(i), Integer.valueOf(i2));
        CommsEventEmitterBridge.notifyAudioFileDidFinishPlaying(str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$uploadAudioFile$7(MediaStorageServiceClient mediaStorageServiceClient, MediaStorageServiceAudio mediaStorageServiceAudio, Promise promise, String str, String str2) {
        try {
            MediaCreateResponse uploadAudioFile = mediaStorageServiceClient.uploadAudioFile(AudioRecorder.AUDIO_RECORDED_CONTENT_TYPE, mediaStorageServiceAudio);
            if (uploadAudioFile != null) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("id", uploadAudioFile.getArn());
                createMap.putString("transcript", uploadAudioFile.getTranscript());
                createMap.putString("transcriptStatus", uploadAudioFile.getTranscriptStatus());
                promise.resolve(createMap);
                LOG.i("Uploaded media file %s for id %s", LOG.sensitive(str), LOG.sensitive(str2));
            } else {
                String format = String.format("Null response when uploading media file: %s", LOG.sensitive(str));
                LOG.e(format);
                promise.reject((String) null, format);
            }
        } catch (IOException e) {
            String format2 = String.format("Could not upload media file: %s", LOG.sensitive(str));
            LOG.e(format2, e);
            promise.reject((String) null, format2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$uploadFilesToCDSInternal$8(WritableArray writableArray, AmazonPhotosUploadRequest amazonPhotosUploadRequest, AtomicInteger atomicInteger, int i, Promise promise, CloudDriveService.UploadResponse uploadResponse) {
        writableArray.pushMap(ReactBridgeSerializer.createMediaUploadSuccessMap(amazonPhotosUploadRequest, uploadResponse.getId(), uploadResponse.getOwnerID()));
        CommsEventEmitterBridge.notifyUploadComplete(ReactBridgeSerializer.createMediaUploadSuccessMap(amazonPhotosUploadRequest, uploadResponse.getId(), uploadResponse.getOwnerID()));
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Sharing] Uploaded image file ");
        outline1.append(atomicInteger.get() + 1);
        outline1.append(" of ");
        outline1.append(i);
        commsLogger.i(outline1.toString());
        if (atomicInteger.incrementAndGet() == i) {
            promise.resolve(writableArray);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$uploadFilesToCDSInternal$9(WritableArray writableArray, AmazonPhotosUploadRequest amazonPhotosUploadRequest, AtomicInteger atomicInteger, int i, Promise promise, Throwable th) {
        LOG.e("[Sharing] An error occurred during the upload. Returning a failed image with error.", th);
        writableArray.pushMap(ReactBridgeSerializer.createMediaUploadFailureMap(amazonPhotosUploadRequest, th));
        if (atomicInteger.incrementAndGet() == i) {
            promise.resolve(writableArray);
        }
        CommsEventEmitterBridge.notifyUploadComplete(ReactBridgeSerializer.createMediaUploadFailureMap(amazonPhotosUploadRequest, th));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ClientMessage prepareClientMessage(String str, String str2, String str3) {
        AudioMessagePayload audioMessagePayload;
        if ("announcement/audio".equalsIgnoreCase(str3)) {
            AudioAnnouncementPayload audioAnnouncementPayload = new AudioAnnouncementPayload();
            audioAnnouncementPayload.setMediaId(str);
            audioMessagePayload = audioAnnouncementPayload;
        } else if ("message/audio".equalsIgnoreCase(str3)) {
            AudioMessagePayload audioMessagePayload2 = new AudioMessagePayload();
            audioMessagePayload2.setMediaId(str);
            audioMessagePayload = audioMessagePayload2;
        } else {
            audioMessagePayload = null;
        }
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setType(str3);
        clientMessage.setPayload(audioMessagePayload);
        clientMessage.setViewAsCommsId(str2);
        return clientMessage;
    }

    private void resolvePromiseOnAudioFetchCompletion(String str, boolean z, MediaFileContent mediaFileContent, Promise promise) {
        if (!z) {
            promise.reject((String) null, "Could not retrieve media file");
            CommsLogger commsLogger = LOG;
            commsLogger.e("Failed to download media %s", commsLogger.sensitive(str));
        } else if (mediaFileContent == null) {
            promise.reject((String) null, "Could not retrieve media file");
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e("Download succeeded, but file doesn't exist for media %s", commsLogger2.sensitive(str));
        } else if (!this.audioContentManager.existsInCache(str)) {
            promise.reject((String) null, "Could not retrieve media file");
            CommsLogger commsLogger3 = LOG;
            commsLogger3.e("Download succeeded, but file doesn't exist for media %s", commsLogger3.sensitive(str));
        } else {
            promise.resolve(this.mReactBridgeSerializer.createMediaMetadataMap(new MediaMetadata(str, this.audioContentManager.getDuration(str))));
            CommsLogger commsLogger4 = LOG;
            commsLogger4.i("Downloaded file for media %s", commsLogger4.sensitive(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadFilesToCDSInternal(ReadableArray readableArray, final Promise promise) {
        final WritableArray createArray = Arguments.createArray();
        final int size = readableArray.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        if (size == 0) {
            LOG.i("NO Files to upload -- Notify upload completed");
            returnFailureResponsesAndRejectPromise("Error missing uploads", readableArray, promise);
            return;
        }
        AmazonPhotosUploadRequest[] extractUploadFromReactNative = extractUploadFromReactNative(readableArray);
        for (int i = 0; i < extractUploadFromReactNative.length; i++) {
            final AmazonPhotosUploadRequest amazonPhotosUploadRequest = extractUploadFromReactNative[i];
            Uri contentUri = amazonPhotosUploadRequest.getContentUri();
            String clientId = amazonPhotosUploadRequest.getClientId();
            LOG.i("[Sharing] PhotoMessage " + clientId + " - Uploading image file " + i + " of " + size);
            this.mFileTransmitter.mo358get().uploadImage(contentUri).subscribe(new Action1() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$C4tL_G8yk4R62G9tGxrnONaAF2c
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommsMessagingMediaBridge.lambda$uploadFilesToCDSInternal$8(WritableArray.this, amazonPhotosUploadRequest, atomicInteger, size, promise, (CloudDriveService.UploadResponse) obj);
                }
            }, new Action1() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$YG2yO9iketR34MflcHwvw8Ud8IU
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommsMessagingMediaBridge.lambda$uploadFilesToCDSInternal$9(WritableArray.this, amazonPhotosUploadRequest, atomicInteger, size, promise, (Throwable) obj);
                }
            });
        }
        LOG.i("[Sharing] ALL images have started Uploading.");
    }

    @ReactMethod
    public void downloadAudioFile(String str, String str2, String str3, final Promise promise) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Downloading an %s audio file with ID %s for user %s", str3, commsLogger.sensitive(str), LOG.sensitive(str2));
        this.audioContentManager.getAudioFileForMessage(str, prepareClientMessage(str, str2, str3), new AudioContentManager.IAudioFetchCompletionCallback() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$gKbjWOvHq7Cdq1QUZFdsYHMJaA8
            @Override // com.amazon.deecomms.media.audio.AudioContentManager.IAudioFetchCompletionCallback
            public final void onAudioFetchCompleted(String str4, boolean z, ClientMessageIdentifier clientMessageIdentifier, MediaFileContent mediaFileContent) {
                CommsMessagingMediaBridge.this.lambda$downloadAudioFile$4$CommsMessagingMediaBridge(promise, str4, z, clientMessageIdentifier, mediaFileContent);
            }
        });
    }

    @ReactMethod
    public void downloadFilesFromCDS(final ReadableArray readableArray, final Promise promise) {
        this.mFileTransmitter.mo358get().init(new SetupCompletedListener() { // from class: com.amazon.deecomms.nativemodules.CommsMessagingMediaBridge.2
            @Override // com.amazon.deecomms.media.photos.SetupCompletedListener
            public void onError(Exception exc) {
                MetricsHelper.recordCounterMetricOperational("comms.cds.download", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                CommsMessagingMediaBridge.this.returnPromiseRejectionForDownloads("Error during setup", readableArray, promise);
            }

            @Override // com.amazon.deecomms.media.photos.SetupCompletedListener
            public void onSuccess() {
                MetricsHelper.recordSingleOccurrenceOperational("comms.cds.setup");
                CommsMessagingMediaBridge.this.downloadFilesFromCDSInternal(readableArray, promise);
            }
        });
    }

    @VisibleForTesting
    AmazonPhotosUploadRequest[] extractUploadFromReactNative(ReadableArray readableArray) {
        int size = readableArray.size();
        AmazonPhotosUploadRequest[] amazonPhotosUploadRequestArr = new AmazonPhotosUploadRequest[size];
        for (int i = 0; i < size; i++) {
            amazonPhotosUploadRequestArr[i] = AmazonPhotosUploadRequest.extractFromReadableMap(readableArray.mo6944getMap(i));
        }
        return amazonPhotosUploadRequestArr;
    }

    @ReactMethod
    public void getFilePathForFileName(String str, Promise promise) {
        String str2 = this.mContext.getFilesDir().getPath() + "/" + str;
        String outline0 = new File(str2).exists() ? GeneratedOutlineSupport.outline0("file://", str2) : "";
        LOG.i("[Sharing] Request for fileName: " + str + " returned path: " + outline0);
        promise.resolve(outline0);
    }

    @ReactMethod
    public void getMediaMetaData(String str, Promise promise) {
        if (TextUtils.isEmpty(str)) {
            LOG.v("getMediaMetaData: empty mediaId/fileName");
            promise.resolve(null);
            return;
        }
        File file = new File(str);
        if (this.audioContentManager.existsInCache(str)) {
            CommsLogger commsLogger = LOG;
            commsLogger.v("getMediaMetaData(mediaId=%s): true", commsLogger.sensitive(str));
            promise.resolve(this.mReactBridgeSerializer.createMediaMetadataMap(new MediaMetadata(str, this.audioContentManager.getDuration(str))));
            return;
        }
        if (file.exists()) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.v("getMediaMetaData(fileName=%s): true", commsLogger2.sensitive(str));
            int messageDuration = AudioMessageUtils.getMessageDuration(file);
            if (messageDuration != -1) {
                promise.resolve(this.mReactBridgeSerializer.createMediaMetadataMap(new MediaMetadata(str, (int) Math.round(Double.valueOf(messageDuration / 1000.0d).doubleValue()))));
                return;
            }
        }
        CommsLogger commsLogger3 = LOG;
        commsLogger3.v("getMediaMetaData(%s): false", commsLogger3.sensitive(str));
        promise.resolve(null);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsMessagingMedia";
    }

    @VisibleForTesting
    AudioRecordingState getRecordingState() {
        return this.recordingState;
    }

    @ReactMethod
    public void isRecording(Promise promise) {
        synchronized (this.recordingStateSync) {
            promise.resolve(Boolean.valueOf(this.recordingState != null && this.audioRecorder.isAudioMessageRecording()));
        }
    }

    public /* synthetic */ void lambda$downloadAudioFile$4$CommsMessagingMediaBridge(Promise promise, String str, boolean z, ClientMessageIdentifier clientMessageIdentifier, MediaFileContent mediaFileContent) {
        resolvePromiseOnAudioFetchCompletion(str, z, mediaFileContent, promise);
    }

    public /* synthetic */ void lambda$new$3$CommsMessagingMediaBridge(String str) {
        LOG.d("Sharing Access Feature Update");
        if (this.capabilitiesManager.isSharingEnabled()) {
            this.mFileTransmitter.mo358get().initialize().subscribe($$Lambda$CommsMessagingMediaBridge$rNUo29sNjczUnrYUycnY1taX5GI.INSTANCE, $$Lambda$CommsMessagingMediaBridge$_qhY8el3dufG1NMz26TGYsGWvo.INSTANCE);
        }
    }

    @ReactMethod
    public void playAudioFile(final String str, Promise promise) {
        MediaFileContent mediaFileContent;
        File file = new File(str);
        ClientMessageIdentifier clientMessageIdentifier = new ClientMessageIdentifier(str, 0L, 0L);
        if (this.audioContentManager.existsInCache(str)) {
            CommsLogger commsLogger = LOG;
            commsLogger.i("Playing media with id: %s", commsLogger.sensitive(str));
            mediaFileContent = this.audioContentManager.getFromCache(str);
        } else if (file.exists()) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.i("Playing media file: %s", commsLogger2.sensitive(str));
            MediaFileContent mediaFileContent2 = new MediaFileContent();
            mediaFileContent2.setFile(file);
            mediaFileContent = mediaFileContent2;
        } else {
            CommsLogger commsLogger3 = LOG;
            commsLogger3.e("Media file does not exist: %s", commsLogger3.sensitive(str));
            promise.reject((String) null, "Media file does not exist");
            return;
        }
        this.audioPlayer.playAudio(mediaFileContent, str, clientMessageIdentifier, new MediaPlayer.OnCompletionListener() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$OY7-XBcGdEQmSQcg3ukonCSeFZ0
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                CommsMessagingMediaBridge.lambda$playAudioFile$5(str, mediaPlayer);
            }
        }, new MediaPlayer.OnErrorListener() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$si0lYRLJtM01SKI_J9ms-R9yOCk
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                CommsMessagingMediaBridge.lambda$playAudioFile$6(str, mediaPlayer, i, i2);
                return false;
            }
        });
        promise.resolve(null);
    }

    @ReactMethod
    public void removeFilesFromDisk(@NonNull ReadableArray readableArray, Promise promise) {
        String path;
        Iterator<Object> it2 = readableArray.toArrayList().iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            if ((next instanceof String) && (path = Uri.parse((String) next).getPath()) != null) {
                File file = new File(path);
                if (file.exists()) {
                    if (!file.delete()) {
                        GeneratedOutlineSupport.outline4("[Sharing] File NOT deleted: ", path, LOG);
                    } else {
                        GeneratedOutlineSupport.outline4("[Sharing] Deleted file: ", path, LOG);
                    }
                }
            }
        }
        promise.resolve(null);
    }

    protected void returnFailureResponsesAndRejectPromise(@NonNull String str, @NonNull ReadableArray readableArray, @NonNull Promise promise) {
        AmazonPhotosUploadRequest[] extractUploadFromReactNative;
        for (AmazonPhotosUploadRequest amazonPhotosUploadRequest : extractUploadFromReactNative(readableArray)) {
            String conversationId = amazonPhotosUploadRequest.getConversationId();
            String clientId = amazonPhotosUploadRequest.getClientId();
            String photoMessageMediaDataId = amazonPhotosUploadRequest.getPhotoMessageMediaDataId();
            String mediaPath = amazonPhotosUploadRequest.getMediaPath();
            LOG.e(GeneratedOutlineSupport1.outline75("[Sharing] An error occurred during setup <", photoMessageMediaDataId, ">. Returning a failed image with error."), str);
            CommsEventEmitterBridge.notifyUploadComplete(ReactBridgeSerializer.createMediaUploadFailureMap(conversationId, clientId, photoMessageMediaDataId, mediaPath, str));
        }
        promise.reject((String) null, str);
    }

    protected void returnPromiseRejectionForDownloads(String str, ReadableArray readableArray, Promise promise) {
        AmazonPhotosDownloadRequest[] extractDownloadFromReactNative;
        for (AmazonPhotosDownloadRequest amazonPhotosDownloadRequest : extractDownloadFromReactNative(readableArray)) {
            String conversationId = amazonPhotosDownloadRequest.getConversationId();
            String clientId = amazonPhotosDownloadRequest.getClientId();
            String dimension = amazonPhotosDownloadRequest.getDimension();
            String nodeId = amazonPhotosDownloadRequest.getNodeId();
            LOG.e(GeneratedOutlineSupport1.outline75("[Sharing] An error occurred during setup <", nodeId, ">. Returning a failed image with error."), str);
            CommsEventEmitterBridge.notifyDownloadComplete(ReactBridgeSerializer.createMediaDownloadFailureMap(conversationId, clientId, dimension, nodeId, str));
        }
        promise.reject((String) null, str);
    }

    @ReactMethod
    public void setupRecorder(Promise promise) {
        LOG.d("Setting up recorder");
        synchronized (this.recordingStateSync) {
            LOG.d("Setting up audio recording");
            this.recordingState = new AudioRecordingState(this.audioContentManager, this.audioRecorder);
            File audioRecordFile = this.recordingState.getAudioRecordFile();
            if (audioRecordFile != null && audioRecordFile.exists()) {
                promise.resolve(audioRecordFile.getPath());
            } else {
                promise.reject((String) null, "Could not create audio file");
                this.recordingState.close(true);
            }
        }
    }

    @ReactMethod
    public void startRecording(int i, Promise promise) {
        synchronized (this.recordingStateSync) {
            if (this.recordingState == null) {
                LOG.e("Recording state is not setup");
                promise.reject((String) null, "Recording state is not setup");
                return;
            }
            LOG.d("Starting recording");
            if (this.audioRecorder.startRecording(this.recordingState.getAudioRecordFile().getPath())) {
                LOG.i("Recording was started");
                promise.resolve(null);
            } else {
                LOG.e("Could not start recording");
                promise.reject((String) null, "Could not start recording");
                this.recordingState.close(true);
            }
        }
    }

    @ReactMethod
    public void stopAudioPlayer(Promise promise) {
        String str;
        if (this.audioPlayer.isPlaying()) {
            LOG.d("Stopping audio playback");
            str = this.audioPlayer.getCurrentlyPlayingMediaFileName();
            this.audioPlayer.stopPlaying();
            CommsEventEmitterBridge.notifyAudioFileDidFinishPlaying(str);
        } else {
            LOG.d("Audio playback is already stopped");
            str = null;
        }
        promise.resolve(str);
    }

    @ReactMethod
    public void stopRecording(boolean z, Promise promise) {
        LOG.d("Stopping recording: wasCancelled=%b", Boolean.valueOf(z));
        synchronized (this.recordingStateSync) {
            if (this.recordingState == null) {
                LOG.e("Recording state is not setup");
                promise.reject((String) null, "Recording state is not setup");
                return;
            }
            AudioRecordingState audioRecordingState = this.recordingState;
            audioRecordingState.close(z);
            CommsEventEmitterBridge.notifyAudioRecorderDidFinish(!audioRecordingState.hasEncounteredError(), audioRecordingState.getAudioRecordFile().getPath(), audioRecordingState.wasTimeLimitReached());
            promise.resolve(null);
            LOG.d("Stopped recording");
        }
    }

    @ReactMethod
    public void uploadAudioFile(ReadableMap readableMap, String str, final Promise promise) {
        final MediaStorageServiceAudio createPayloadFromMediaType = MediaStorageServiceAudio.createPayloadFromMediaType(readableMap, str);
        if (!createPayloadFromMediaType.validate()) {
            String errorMessage = createPayloadFromMediaType.getErrorMessage();
            LOG.e(errorMessage);
            promise.reject((String) null, errorMessage);
            return;
        }
        final String filePath = createPayloadFromMediaType.getFilePath();
        final String senderCommsId = createPayloadFromMediaType.getSenderCommsId();
        CommsLogger commsLogger = LOG;
        commsLogger.i("Uploading media file %s of type %s for id %s", commsLogger.sensitive(filePath), LOG.sensitive(str), LOG.sensitive(senderCommsId));
        final MediaStorageServiceClient mediaStorageServiceClient = new MediaStorageServiceClient(senderCommsId);
        ThreadUtils.runOffMainThread(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$WHQt3pN7RW6FmjStKS5YzRqr_fA
            @Override // java.lang.Runnable
            public final void run() {
                CommsMessagingMediaBridge.lambda$uploadAudioFile$7(MediaStorageServiceClient.this, createPayloadFromMediaType, promise, filePath, senderCommsId);
            }
        });
    }

    @ReactMethod
    public void uploadFilesToCDS(final ReadableArray readableArray, final Promise promise) {
        this.mFileTransmitter.mo358get().init(new SetupCompletedListener() { // from class: com.amazon.deecomms.nativemodules.CommsMessagingMediaBridge.1
            @Override // com.amazon.deecomms.media.photos.SetupCompletedListener
            public void onError(Exception exc) {
                MetricsHelper.recordCounterMetricOperational("comms.cds.upload", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                CommsMessagingMediaBridge.this.returnFailureResponsesAndRejectPromise("Error during setup", readableArray, promise);
            }

            @Override // com.amazon.deecomms.media.photos.SetupCompletedListener
            public void onSuccess() {
                MetricsHelper.recordSingleOccurrenceOperational("comms.cds.setup");
                CommsMessagingMediaBridge.this.uploadFilesToCDSInternal(readableArray, promise);
            }
        });
    }

    @VisibleForTesting
    CommsMessagingMediaBridge(ReactApplicationContext reactApplicationContext, @NonNull AudioContentManager audioContentManager, @NonNull AudioPlayer audioPlayer, @NonNull AudioRecorder audioRecorder, @NonNull ReactBridgeSerializer reactBridgeSerializer, @NonNull Lazy<FileTransmitter> lazy, @NonNull CapabilitiesManager capabilitiesManager) {
        super(reactApplicationContext);
        this.recordingStateSync = new Object();
        this.mContext = reactApplicationContext;
        this.audioContentManager = audioContentManager;
        this.audioPlayer = audioPlayer;
        this.audioRecorder = audioRecorder;
        this.mReactBridgeSerializer = reactBridgeSerializer;
        this.mFileTransmitter = lazy;
        this.capabilitiesManager = capabilitiesManager;
        this.featureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$iA2zqhICs1Sza9FU9T8c0xEy4J8
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public final void onFeatureUpdate(String str) {
                CommsMessagingMediaBridge.this.lambda$new$3$CommsMessagingMediaBridge(str);
            }
        };
        this.capabilitiesManager.observeSharingFeature(this.featureUpdateListener);
    }
}
