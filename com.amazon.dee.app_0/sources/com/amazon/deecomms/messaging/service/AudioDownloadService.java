package com.amazon.deecomms.messaging.service;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.service.CommsJobIntentService;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.MediaStorageServiceClient;
import com.amazon.deecomms.messaging.controller.AudioStateManager;
import com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class AudioDownloadService extends CommsJobIntentService {
    @Inject
    AudioStateManager audioStateManager;
    private MediaStorageServiceClient mStorageServiceClient;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioDownloadService.class);
    private static final int JOB_ID = CommsJobIntentService.generateJobId(AudioDownloadService.class);

    public AudioDownloadService() {
        CommsDaggerWrapper.getComponent().inject(this);
        String commsId = CommsInternal.getInstance().getCommsId();
        if (!TextUtils.isEmpty(commsId)) {
            this.mStorageServiceClient = new MediaStorageServiceClient(commsId);
            return;
        }
        throw new IllegalArgumentException("CommsId cannot be null or empty while instantiating AudioContentManager");
    }

    private void downloadAudioContent(String str, ClientMessageIdentifier clientMessageIdentifier, String str2, ResultReceiver resultReceiver, String str3) {
        this.audioStateManager.setAudioMessageState(clientMessageIdentifier, 0);
        AudioDownloadCallback audioDownloadCallback = new AudioDownloadCallback(this.audioStateManager, str, clientMessageIdentifier, str2, resultReceiver);
        if (str2.equals(Constants.SOUND_EFFECT_TYPE)) {
            this.mStorageServiceClient.downloadAudioFile(str, audioDownloadCallback);
        } else {
            this.mStorageServiceClient.downloadAudioFile(str, str3, str2, audioDownloadCallback);
        }
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, AudioDownloadService.class, JOB_ID, intent);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Handling intent for media ");
        outline1.append(LOG.sensitive(intent.getStringExtra(Constants.AUDIO_MEDIA_ID)));
        commsLogger.i(outline1.toString());
        downloadAudioContent(intent.getStringExtra(Constants.AUDIO_MEDIA_ID), (ClientMessageIdentifier) intent.getExtras().getParcelable(Constants.CLIENT_MESSAGE), intent.getStringExtra(Constants.CLIENT_MESSAGE_TYPE), (ResultReceiver) intent.getParcelableExtra(Constants.AUDIO_DOWNLOAD_RESULT_RECEIVER), intent.getStringExtra(Constants.DOWNLOAD_ACT_AS_COMMS_ID));
    }
}
