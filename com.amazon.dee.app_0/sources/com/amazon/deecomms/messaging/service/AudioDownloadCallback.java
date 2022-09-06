package com.amazon.deecomms.messaging.service;

import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.model.MediaFileContent;
import com.amazon.deecomms.media.model.MediaStreamContent;
import com.amazon.deecomms.messaging.controller.AudioStateManager;
import com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier;
import com.amazon.deecomms.messaging.util.MessageUtils;
import com.amazon.deecomms.nativemodules.imagepicker.util.RealPathUtil;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
/* loaded from: classes12.dex */
class AudioDownloadCallback implements IHttpClient.Callback {
    private final CommsLogger LOG;
    private final AudioStateManager audioStateManager;
    private ResultReceiver mAudioResultReceiver;
    private final ClientMessageIdentifier mClientMessageIdentifier;
    private Context mContext;
    private final String mMediaId;
    private final String mMessageType;

    public AudioDownloadCallback(AudioStateManager audioStateManager, String str, ClientMessageIdentifier clientMessageIdentifier, String str2, ResultReceiver resultReceiver) {
        this(audioStateManager, str, clientMessageIdentifier, str2, CommsDaggerWrapper.getComponent().getContext(), resultReceiver);
    }

    private MediaFileContent createMediaFileContent(IHttpClient.Response response) {
        File writeResponseToMediaFile = writeResponseToMediaFile(response);
        MediaFileContent mediaFileContent = new MediaFileContent();
        mediaFileContent.setMediaId(this.mMediaId);
        mediaFileContent.setFile(writeResponseToMediaFile);
        String contentType = MessageUtils.getContentType(response);
        CommsLogger commsLogger = this.LOG;
        commsLogger.i("Media content type is : " + contentType);
        mediaFileContent.setContentType(contentType);
        return mediaFileContent;
    }

    private MediaStreamContent createMediaStreamContent(IHttpClient.Response response) {
        MediaStreamContent mediaStreamContent = new MediaStreamContent();
        mediaStreamContent.setMediaId(this.mMediaId);
        mediaStreamContent.setInputStream(response.getByteStream());
        String contentType = MessageUtils.getContentType(response);
        CommsLogger commsLogger = this.LOG;
        commsLogger.i("Media content type is : " + contentType);
        mediaStreamContent.setContentType(contentType);
        return mediaStreamContent;
    }

    private void sendAudioDownloadResult(boolean z, ResultReceiver resultReceiver) {
        resultReceiver.send(0, createAudioDownloadBundle(z));
    }

    private File writeResponseToMediaFile(IHttpClient.Response response) {
        return RealPathUtil.writeToFile(this.mContext, Constants.AUDIO_CACHE_DIR, getPrefixForContentType(this.mMessageType) + this.mMediaId, response.getByteStream());
    }

    Bundle createAudioDownloadBundle(boolean z) {
        Bundle outline13 = GeneratedOutlineSupport1.outline13(Constants.AUDIO_DOWNLOAD_RESULT, z);
        outline13.putParcelable(Constants.CLIENT_MESSAGE, this.mClientMessageIdentifier);
        return outline13;
    }

    String getPrefixForContentType(String str) {
        if (str == null) {
            return Constants.AUDIO_GENERIC_PREFIX;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -389653234) {
            if (hashCode == 1689780174 && str.equals("message/audio")) {
                c = 1;
            }
        } else if (str.equals("announcement/audio")) {
            c = 0;
        }
        return c != 0 ? c != 1 ? Constants.AUDIO_GENERIC_PREFIX : Constants.AUDIO_MESSAGE_PREFIX : Constants.AUDIO_ANNOUNCEMENT_PREFIX;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
    public void onFailure(IHttpClient.Call call, ServiceException serviceException) {
        this.LOG.e("Failure of media download", serviceException);
        this.audioStateManager.setAudioMessageState(this.mClientMessageIdentifier, 4);
        sendAudioDownloadResult(false, this.mAudioResultReceiver);
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
    public void onResult(IHttpClient.Call call, IHttpClient.Response response) {
        this.audioStateManager.setAudioMessageState(this.mClientMessageIdentifier, 2);
        if (response == null) {
            this.LOG.e("Audio file download succeeded , but file is null");
            sendAudioDownloadResult(false, this.mAudioResultReceiver);
            return;
        }
        CommsLogger commsLogger = this.LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("In audioDownload Service. Audio file download succeeded: ");
        outline1.append(this.LOG.sensitive(Long.toString(this.mClientMessageIdentifier.getMessageID())));
        outline1.append("media is:");
        outline1.append(this.LOG.sensitive(this.mMediaId));
        commsLogger.i(outline1.toString());
        saveResponseToFile(response);
        sendAudioDownloadResult(true, this.mAudioResultReceiver);
    }

    void saveResponseToFile(IHttpClient.Response response) {
        CommsDaggerWrapper.getComponent().getAudioContentManager().putInCache(createMediaFileContent(response), this.mMediaId, false);
    }

    void saveResponseToMediaStream(IHttpClient.Response response) {
        CommsDaggerWrapper.getComponent().getAudioContentManager().putInCache(createMediaStreamContent(response), this.mMediaId, false);
    }

    public AudioDownloadCallback(AudioStateManager audioStateManager, String str, ClientMessageIdentifier clientMessageIdentifier, String str2, Context context, ResultReceiver resultReceiver) {
        this.LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioDownloadCallback.class);
        this.audioStateManager = audioStateManager;
        this.LOG.i("Creating an instance of AudioDownloadCallback");
        this.mMediaId = str;
        this.mContext = context;
        this.mClientMessageIdentifier = clientMessageIdentifier;
        this.mMessageType = str2;
        this.mAudioResultReceiver = resultReceiver;
    }
}
