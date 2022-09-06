package com.amazon.deecomms.media.audio;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.EventBusEventType;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.common.util.ResultReceiverWrapper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.IMediaCache;
import com.amazon.deecomms.media.MediaCache;
import com.amazon.deecomms.media.model.ContentManagerInterface;
import com.amazon.deecomms.media.model.MediaFileContent;
import com.amazon.deecomms.media.model.MediaStreamContent;
import com.amazon.deecomms.messaging.model.client.ClientMessage;
import com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier;
import com.amazon.deecomms.messaging.service.AudioDownloadService;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
/* loaded from: classes12.dex */
public class AudioContentManager implements ContentManagerInterface {
    private static final String CACHE_DIR_NAME = "audio_cache";
    private static final long CACHE_SIZE = 209715200;
    private static final String LOCAL_MSG_UPLOAD_DIR = "uploaded_audio";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioContentManager.class);
    @NonNull
    private final IMediaCache audioMediaCache;
    @NonNull
    private final Context context;
    @NonNull
    private final MetricsService metricsService;

    /* loaded from: classes12.dex */
    public interface IAudioFetchCompletionCallback {
        void onAudioFetchCompleted(String str, boolean z, ClientMessageIdentifier clientMessageIdentifier, MediaFileContent mediaFileContent);
    }

    public AudioContentManager(@NonNull Context context) {
        this(context, new MediaCache(new File(context.getFilesDir(), CACHE_DIR_NAME), CACHE_SIZE), CommsDaggerWrapper.getComponent().getMetricsService());
    }

    private boolean cacheFileEligibleForRemoval(File file) {
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        return file.getName().startsWith(Constants.AUDIO_ANNOUNCEMENT_PREFIX) && file.lastModified() <= getYesterday().getTime();
    }

    private void getLocalMedia(ClientMessage clientMessage, IAudioFetchCompletionCallback iAudioFetchCompletionCallback) {
        String mediaId = clientMessage.getMediaId();
        long clientID = clientMessage.getClientID();
        ClientMessageIdentifier clientMessageIdentifier = clientMessage.getClientMessageIdentifier();
        File localAudioMessageFileByClientId = getLocalAudioMessageFileByClientId(Long.valueOf(clientID));
        if (localAudioMessageFileByClientId.exists()) {
            MediaFileContent mediaFileContent = new MediaFileContent();
            mediaFileContent.setClientId(clientID);
            mediaFileContent.setFile(localAudioMessageFileByClientId);
            mediaFileContent.setContentType(AudioRecorder.AUDIO_RECORDED_CONTENT_TYPE);
            iAudioFetchCompletionCallback.onAudioFetchCompleted(mediaId, true, clientMessageIdentifier, mediaFileContent);
            return;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("File with clientId ");
        outline1.append(LOG.sensitive(Long.toString(clientID)));
        outline1.append(" does not exist");
        commsLogger.e(outline1.toString());
        iAudioFetchCompletionCallback.onAudioFetchCompleted(mediaId, false, clientMessageIdentifier, null);
    }

    private static File getLocalMsgAudioDir() {
        File file = new File(CommsDaggerWrapper.getComponent().getContext().getFilesDir(), LOCAL_MSG_UPLOAD_DIR);
        if (!file.exists() && !file.mkdir()) {
            CommsLogger commsLogger = LOG;
            commsLogger.w("unable to make directory " + file);
        }
        return file;
    }

    public static File getLocalMsgAudioFile(long j) {
        File file = new File(getLocalMsgAudioDir(), String.valueOf(j));
        if (file.exists()) {
            return file;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.e("Local audio file does not exist for the given client id:" + j);
        return null;
    }

    private boolean isValidMessageType(String str) {
        return Constants.VALID_AUDIO_DOWNLOAD_MESSAGE_TYPES.contains(str);
    }

    @VisibleForTesting
    void audioDownloadServiceEnqueueWork(@NonNull Context context, @NonNull Intent intent, @NonNull ResultReceiverWrapper.Receiver receiver) {
        ResultReceiverWrapper resultReceiverWrapper = new ResultReceiverWrapper(null);
        resultReceiverWrapper.setReceiver(receiver);
        intent.putExtra(Constants.AUDIO_DOWNLOAD_RESULT_RECEIVER, resultReceiverWrapper);
        AudioDownloadService.enqueueWork(context, intent);
    }

    @Override // com.amazon.deecomms.media.model.ContentManagerInterface
    public void clearExpiredMedia() {
        File[] listFiles;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("comms.eventbus.event.received.");
        outline1.append(EventBusEventType.REMOVE_EXPIRED_MEDIA_FROM_CACHE.toString());
        CounterMetric generateOperational = CounterMetric.generateOperational(outline1.toString());
        generateOperational.setCounter(Double.valueOf(1.0d));
        this.metricsService.recordCounterMetric(generateOperational);
        File directory = this.audioMediaCache.getDirectory();
        CommsLogger commsLogger = LOG;
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("[AudioContentManager] - Received a call to clear expired media from: ");
        outline12.append(directory.getName());
        commsLogger.i(outline12.toString());
        for (File file : directory.listFiles()) {
            if (cacheFileEligibleForRemoval(file)) {
                LOG.i("[AudioContentManager] - Removing file from cache: ", file.getName());
                LOG.i("[AudioContentManager] - file removed from cache: ", Boolean.valueOf(file.delete()));
            }
        }
    }

    @Override // com.amazon.deecomms.media.model.ContentManagerInterface
    public synchronized void clearMediaCache() {
        LOG.i("clearing media disk cache");
        try {
            this.audioMediaCache.clearAll();
        } catch (IOException e) {
            LOG.e("IO exception while clearing media cache.", e);
        }
    }

    @VisibleForTesting
    Intent createAudioDownloadServiceIntent(Context context) {
        return new Intent(context, AudioDownloadService.class);
    }

    public File createLocalMsgAudioFile(long j) {
        File file = new File(getLocalMsgAudioDir(), String.valueOf(j));
        try {
            if (!file.createNewFile()) {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("unable to create new file ");
                sb.append(file);
                commsLogger.w(sb.toString());
            }
            return file;
        } catch (IOException e) {
            LOG.e("IOException occurred while creating audio file", e);
            return null;
        }
    }

    @Override // com.amazon.deecomms.media.model.ContentManagerInterface
    public synchronized boolean existsInCache(String str) {
        ThreadUtils.checkNotMainThread();
        try {
        } catch (IOException e) {
            LOG.e("Getting media from cache failed", e);
            return false;
        }
        return this.audioMediaCache.exists(str);
    }

    public void getAudioFileForMessage(@Nullable final String str, @NonNull final ClientMessage clientMessage, @NonNull final IAudioFetchCompletionCallback iAudioFetchCompletionCallback) {
        ClientMessageIdentifier clientMessageIdentifier = clientMessage.getClientMessageIdentifier();
        if (clientMessageIdentifier == null) {
            LOG.e("clientMessageIdentifier is null, returning");
            iAudioFetchCompletionCallback.onAudioFetchCompleted(null, false, null, null);
        } else if (!isValidMessageType(clientMessage.getType())) {
            LOG.e(String.format("Incompatible message type, <%s>. returning", clientMessage.getType()));
            iAudioFetchCompletionCallback.onAudioFetchCompleted(str, false, clientMessage.getClientMessageIdentifier(), null);
        } else if (str == null) {
            if (clientMessage.getClientID() > 0) {
                LOG.i("mediaId cannot be retrieved from payload, but a non-zero clientId indicates retrieval of local media.");
                getLocalMedia(clientMessage, iAudioFetchCompletionCallback);
                return;
            }
            LOG.e("mediaId cannot be retrieved from payload, returning");
            iAudioFetchCompletionCallback.onAudioFetchCompleted(null, false, clientMessageIdentifier, null);
        } else {
            CommsLogger commsLogger = LOG;
            commsLogger.d("Retrieving server media for mediaId", commsLogger.sensitive(str));
            ThreadUtils.runOffMainThread(new Runnable() { // from class: com.amazon.deecomms.media.audio.AudioContentManager.1
                @Override // java.lang.Runnable
                public void run() {
                    AudioContentManager.this.retrieveMediaFromCacheOrServer(str, clientMessage.getViewAsCommsId(), clientMessage.getClientMessageIdentifier(), clientMessage.getType(), iAudioFetchCompletionCallback);
                }
            });
        }
    }

    @VisibleForTesting
    Context getContext() {
        return CommsDaggerWrapper.getComponent().getContext();
    }

    public synchronized int getDuration(String str) {
        MediaFileContent mediaFileContent;
        ThreadUtils.checkNotMainThread();
        try {
            mediaFileContent = this.audioMediaCache.get(str);
        } catch (Exception e) {
            LOG.e("Error retrieving audio file duration.", e);
        }
        if (mediaFileContent != null) {
            Uri fromFile = Uri.fromFile(mediaFileContent.getFile());
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(this.context, fromFile);
            return (int) Math.round(Double.valueOf(Double.parseDouble(mediaMetadataRetriever.extractMetadata(9)) / 1000.0d).doubleValue());
        }
        CommsLogger commsLogger = LOG;
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to get audio media cache for: ");
        sb.append(LOG.sensitive(str));
        commsLogger.w(sb.toString());
        return 0;
    }

    @Override // com.amazon.deecomms.media.model.ContentManagerInterface
    public synchronized MediaFileContent getFromCache(String str) {
        ThreadUtils.checkNotMainThread();
        try {
        } catch (IOException e) {
            LOG.e("Getting media from cache failed", e);
            return null;
        }
        return this.audioMediaCache.get(str);
    }

    @VisibleForTesting
    File getLocalAudioMessageFileByClientId(Long l) {
        if (l == null) {
            return null;
        }
        return new File(getLocalMsgAudioDir(), String.valueOf(l));
    }

    @VisibleForTesting
    Date getYesterday() {
        return new Date(new Date().getTime() - 86400000);
    }

    public /* synthetic */ void lambda$retrieveMediaFromServer$0$AudioContentManager(IAudioFetchCompletionCallback iAudioFetchCompletionCallback, String str, int i, Bundle bundle) {
        iAudioFetchCompletionCallback.onAudioFetchCompleted(str, bundle.getBoolean(Constants.AUDIO_DOWNLOAD_RESULT), (ClientMessageIdentifier) bundle.getParcelable(Constants.CLIENT_MESSAGE), getFromCache(str));
    }

    public synchronized void putInCache(MediaStreamContent mediaStreamContent, String str, boolean z) {
        ThreadUtils.checkNotMainThread();
        try {
            this.audioMediaCache.put(str, mediaStreamContent, z);
        } catch (IOException e) {
            LOG.e("Putting media into cache failed", e);
        }
    }

    @Override // com.amazon.deecomms.media.model.ContentManagerInterface
    public synchronized boolean removeFromCache(String str) {
        ThreadUtils.checkNotMainThread();
        try {
        } catch (IOException e) {
            LOG.e("Getting media from cache failed", e);
            return false;
        }
        return this.audioMediaCache.remove(str);
    }

    void retrieveMediaFromCacheOrServer(String str, String str2, ClientMessageIdentifier clientMessageIdentifier, String str3, IAudioFetchCompletionCallback iAudioFetchCompletionCallback) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("In AudioContentManager fetchMedia, mediaId: ");
        outline1.append(LOG.sensitive(str));
        commsLogger.i(outline1.toString());
        MediaFileContent fromCache = getFromCache(str);
        if (fromCache != null && fromCache.getFile() != null && fromCache.getFile().exists()) {
            LOG.i("Media is present in cache");
            iAudioFetchCompletionCallback.onAudioFetchCompleted(str, true, clientMessageIdentifier, getFromCache(str));
            return;
        }
        LOG.i("Media from cache is null, loading from server");
        retrieveMediaFromServer(str, str2, clientMessageIdentifier, str3, iAudioFetchCompletionCallback);
    }

    void retrieveMediaFromServer(final String str, String str2, ClientMessageIdentifier clientMessageIdentifier, String str3, final IAudioFetchCompletionCallback iAudioFetchCompletionCallback) {
        Context context = getContext();
        Intent createAudioDownloadServiceIntent = createAudioDownloadServiceIntent(context);
        createAudioDownloadServiceIntent.putExtra(Constants.AUDIO_MEDIA_ID, str);
        createAudioDownloadServiceIntent.putExtra(Constants.CLIENT_MESSAGE, clientMessageIdentifier);
        createAudioDownloadServiceIntent.putExtra(Constants.CLIENT_MESSAGE_TYPE, str3);
        createAudioDownloadServiceIntent.putExtra(Constants.DOWNLOAD_ACT_AS_COMMS_ID, str2);
        audioDownloadServiceEnqueueWork(context, createAudioDownloadServiceIntent, new ResultReceiverWrapper.Receiver() { // from class: com.amazon.deecomms.media.audio.-$$Lambda$AudioContentManager$8oOPJlq2BLP_7iEtlc831W9Avrk
            @Override // com.amazon.deecomms.common.util.ResultReceiverWrapper.Receiver
            public final void onReceiveResult(int i, Bundle bundle) {
                AudioContentManager.this.lambda$retrieveMediaFromServer$0$AudioContentManager(iAudioFetchCompletionCallback, str, i, bundle);
            }
        });
    }

    @Override // com.amazon.deecomms.media.model.ContentManagerInterface
    public synchronized void putInCache(MediaFileContent mediaFileContent, String str, boolean z) {
        ThreadUtils.checkNotMainThread();
        try {
            this.audioMediaCache.put(str, mediaFileContent, z);
        } catch (IOException e) {
            LOG.e("Putting media into cache failed", e);
        }
    }

    public AudioContentManager(@NonNull Context context, @NonNull IMediaCache iMediaCache, @NonNull MetricsService metricsService) {
        this.audioMediaCache = iMediaCache;
        this.context = context;
        this.metricsService = metricsService;
    }
}
