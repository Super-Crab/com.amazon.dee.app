package com.amazon.alexa.accessory.frames.service;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.frames.downloader.FileDownloadResponseHandler;
import com.amazon.alexa.accessory.frames.downloader.FileDownloader;
import com.amazon.alexa.accessory.frames.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.frames.topContact.TopContactConstants;
import com.amazon.alexa.accessory.frames.topContact.TopContactManager;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirective;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DirectiveConsumer extends FileDownloadResponseHandler {
    private static final String TAG = "DirectiveConsumer";

    public synchronized boolean consumeDirective(AnnounceWithContentDirective announceWithContentDirective) throws Exception {
        Preconditions.notNull(announceWithContentDirective, "directive");
        if (TtsManager.getInstance().isRequestInIncomingTtsMap(announceWithContentDirective.getNotificationUuid())) {
            Log.d(TAG, "In incoming TTS map");
            consumeDirectiveForTTSRequest(announceWithContentDirective);
            return true;
        }
        return false;
    }

    @VisibleForTesting
    void consumeDirectiveForTTSRequest(AnnounceWithContentDirective announceWithContentDirective) {
        String str;
        String str2;
        String notificationUuid = announceWithContentDirective.getNotificationUuid();
        JSONObject andRemoveIncomingTts = TtsManager.getInstance().getAndRemoveIncomingTts(notificationUuid);
        Log.d(TAG, "consumeDirective - send download request for tts - requestId: " + notificationUuid);
        String optString = andRemoveIncomingTts.optString(NotificationConstants.REQUEST_TYPE, "None");
        new HashMap().put(MetricsConstants.CUSTOM_VALUES_KEY, optString);
        String announcementUri = announceWithContentDirective.getAnnouncementUri();
        String optString2 = andRemoveIncomingTts.optString("locale");
        File absoluteFile = DependencyProvider.getContext().getFilesDir().getAbsoluteFile();
        if (TopContactConstants.TOP_CONTACT_REQUEST_TYPE.equals(optString)) {
            MetricsRecorder.getInstance().recordTimer(TopContactManager.ttsRequestTimer);
            TopContactManager.ttsDownloadTimer = MetricsRecorder.getInstance().startTimer(com.amazon.alexa.accessory.frames.metrics.MetricsConstants.TOP_CONTACT_DOWNLOAD_TTS_LATENCY);
            str = absoluteFile + TopContactConstants.TOP_CONTACT_FOLDER;
            str2 = TopContactConstants.TOP_CONTACT_CALLING_AUDIO;
        } else {
            str = null;
            str2 = null;
        }
        FileDownloader.getInstance().downloadUrl(announcementUri, notificationUuid, optString, this, str, optString2, str2);
    }

    @Override // com.amazon.alexa.accessory.frames.downloader.FileDownloadResponseHandler
    public void handleDownloadResponse(boolean z, String str, String str2) {
        Log.i(TAG, String.format("handleDownloadResponse - tts download - requestId: %s, success: %s", str, Boolean.valueOf(z)));
        if (TopContactConstants.TOP_CONTACT_REQUEST_TYPE.equals(str2)) {
            TopContactManager.getInstance().handleTopContactTTSDownloadResponse(z);
        }
    }
}
