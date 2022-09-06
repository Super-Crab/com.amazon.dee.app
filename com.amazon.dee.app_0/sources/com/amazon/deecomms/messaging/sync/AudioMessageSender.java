package com.amazon.deecomms.messaging.sync;

import android.content.ContentValues;
import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.AsyncResponseCallback;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.MediaStorageServiceClient;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.media.model.MediaCreateResponse;
import com.amazon.deecomms.media.model.MediaFileContent;
import com.amazon.deecomms.messaging.model.client.ClientMessage;
import com.amazon.deecomms.messaging.model.payload.AudioMessagePayload;
import com.amazon.deecomms.messaging.model.payload.MessagePayload;
import com.amazon.deecomms.messaging.model.response.SendMessagesResponse;
import com.amazon.deecomms.messaging.model.status.TranscriptStatus;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.messaging.util.AudioMessageUtils;
import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class AudioMessageSender extends MessageSender {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioMessageSender.class);
    private static final Map<String, String> mMessageTypeToContentTypeMap = ImmutableMap.of("message/audio", AudioRecorder.AUDIO_RECORDED_CONTENT_TYPE);
    @Inject
    AudioContentManager audioContentManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    private MessagingProviderWrapper mMessagingProviderWrapper;
    @Inject
    TranscriptLatencyMetricHelper mTranscriptLatencyMetricHelper;

    public AudioMessageSender(Context context, AsyncResponseCallback<SendMessagesResponse, Integer> asyncResponseCallback, String str, boolean z) {
        super(context, asyncResponseCallback, str, z);
        CommsDaggerWrapper.getComponent().inject(this);
        this.mMessagingProviderWrapper = new MessagingProviderWrapper(context, this.commsIdentityManager.getCommsId("AudioMessageSender", false), this.commsIdentityManager.getHomeGroupId("AudioMessageSender", false));
    }

    private MessagePayload prepareAudioPayload(long j, String str) {
        try {
            MediaCreateResponse uploadAudioFile = new MediaStorageServiceClient(this.commsIdentityManager.getCommsId("AudioMessageSender.prepareAudioPayload", false)).uploadAudioFile(mMessageTypeToContentTypeMap.get(this.messageType), AudioContentManager.getLocalMsgAudioFile(j), str, MediaStorageServiceClient.MediaType.MESSAGE);
            if (uploadAudioFile == null) {
                LOG.e("Error occurred in uploading media, response is null");
                return null;
            }
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Received response from Media Storage Service, mediaId: ");
            sb.append(uploadAudioFile.getArn());
            commsLogger.i(sb.toString());
            AudioMessagePayload audioMessagePayload = new AudioMessagePayload();
            audioMessagePayload.setMediaId(uploadAudioFile.getArn());
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Transcript status is: ");
            outline1.append(uploadAudioFile.getTranscriptStatus());
            commsLogger2.d(outline1.toString());
            if (TranscriptStatus.Success.toString().equals(uploadAudioFile.getTranscriptStatus())) {
                audioMessagePayload.setTranscript(uploadAudioFile.getTranscript());
            }
            return audioMessagePayload;
        } catch (IOException e) {
            LOG.e("Error occurred while uploading media", e);
            return null;
        }
    }

    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    protected void handlePreProcessFailure(ClientMessage clientMessage) {
        clientMessage.setSyncStatus(3);
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", (Integer) 3);
        this.mMessagingProviderWrapper.updateClientMessage(clientMessage.getUniqueID(), contentValues);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    public <R> void handleSendFailure(@NonNull ClientMessage clientMessage, @NonNull R r) {
        super.handleSendFailure(clientMessage, r);
        recordSuccessAndFailMetrics(clientMessage, r, MetricKeys.MSG_AUD_SENT_ROOT, AudioMessageUtils.getAudioMetadataFromMessage(clientMessage));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    public void handleSendSuccess(@NonNull ClientMessage clientMessage, @NonNull SendMessagesResponse sendMessagesResponse) {
        super.handleSendSuccess(clientMessage, sendMessagesResponse);
        File localMsgAudioFile = AudioContentManager.getLocalMsgAudioFile(clientMessage.getClientID());
        Map<String, Object> metadataFromAudioFile = localMsgAudioFile != null ? AudioMessageUtils.getMetadataFromAudioFile(localMsgAudioFile) : null;
        TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.MSG_AUD_SENT_LATENCY);
        MessageSender.addCommsItemIdToMetric(generateClickstream, clientMessage);
        MessageSender.addMetadataToMetric(generateClickstream, metadataFromAudioFile);
        MessageSender.addMetadataToMetric(generateClickstream, sendMessagesResponse);
        MetricsHelper.stopTimerMetric(generateClickstream);
        recordSuccessAndFailMetrics(clientMessage, sendMessagesResponse, MetricKeys.MSG_AUD_SENT_ROOT, metadataFromAudioFile);
        AudioMessagePayload audioMessagePayload = (AudioMessagePayload) clientMessage.getPayload();
        if (audioMessagePayload == null) {
            LOG.e("Payload is null in the response unexpectedly!!!");
            return;
        }
        String mediaId = audioMessagePayload.getMediaId();
        CommsLogger commsLogger = LOG;
        commsLogger.i("Adding entry to cache, mediaId: " + mediaId);
        MediaFileContent mediaFileContent = new MediaFileContent();
        mediaFileContent.setClientId(clientMessage.getClientID());
        mediaFileContent.setFile(localMsgAudioFile);
        mediaFileContent.setContentType(mMessageTypeToContentTypeMap.get(this.messageType));
        this.audioContentManager.putInCache(mediaFileContent, mediaId, false);
        clientMessage.setMessageId(sendMessagesResponse.getMessageIds().get(0).longValue());
        this.mTranscriptLatencyMetricHelper.startTimer(clientMessage);
    }

    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    protected boolean preProcess(ClientMessage clientMessage, String str) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Setting the clientId to " + str);
        TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.MSG_AUD_SENT_LATENCY);
        MessageSender.addCommsItemIdToMetric(generateClickstream, clientMessage);
        MetricsHelper.startTimerMetric(generateClickstream);
        long parseLong = Long.parseLong(str);
        clientMessage.setClientID(parseLong);
        MessagePayload prepareAudioPayload = prepareAudioPayload(parseLong, clientMessage.getSenderCommsId());
        if (prepareAudioPayload != null) {
            LOG.i("Audio payload available");
            clientMessage.setPayload(prepareAudioPayload);
            return true;
        }
        LOG.i("Audio payload not available");
        return false;
    }

    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    protected boolean preProcessFailedMessage(ClientMessage clientMessage) {
        MessagePayload prepareAudioPayload = prepareAudioPayload(clientMessage.getClientID(), clientMessage.getSenderCommsId());
        if (prepareAudioPayload != null) {
            LOG.i("Audio playback available");
            clientMessage.setPayload(prepareAudioPayload);
            return true;
        }
        LOG.i("Audio payload not available");
        return false;
    }
}
