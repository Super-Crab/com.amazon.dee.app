package com.amazon.deecomms.messaging.sync;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.service.CommsJobIntentService;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.client.ClientConversation;
import com.amazon.deecomms.messaging.model.payload.AudioMessagePayload;
import com.amazon.deecomms.messaging.model.status.TranscriptStatus;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import com.amazon.deecomms.notifications.model.TranscriptUpdatePush;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class TranscriptionUpdateService extends CommsJobIntentService {
    @Inject
    CommsIdentityManager commsIdentityManager;
    private JacksonJSONConverter jsonConverter = new JacksonJSONConverter();
    @Inject
    TranscriptLatencyMetricHelper mTranscriptLatencyMetricHelper;
    private MessagingProviderWrapper messagingProviderWrapper;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TranscriptionUpdateService.class);
    private static final int JOB_ID = CommsJobIntentService.generateJobId(TranscriptionUpdateService.class);

    /* renamed from: com.amazon.deecomms.messaging.sync.TranscriptionUpdateService$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus = new int[TranscriptStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.Success.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.Transcribing.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.UnsupportedMediaType.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.MediaOversized.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.TranscodingError.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.BluefrontError.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.LowConfidence.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.TimedOut.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$status$TranscriptStatus[TranscriptStatus.UnknownError.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, TranscriptionUpdateService.class, JOB_ID, intent);
    }

    private void handleFailedTranscript(@NonNull ClientConversation clientConversation, long j, @NonNull AudioMessagePayload audioMessagePayload, @NonNull TranscriptStatus transcriptStatus) {
        audioMessagePayload.setTranscript("");
        this.messagingProviderWrapper.updateMessagePayload(clientConversation, j, audioMessagePayload);
        recordFailureMetric(clientConversation.getConversationId(), j, transcriptStatus);
        LOG.w(String.format("Got a failed Transcript Update push for message %s/%d: %s", clientConversation.getConversationId(), Long.valueOf(j), transcriptStatus));
    }

    private void handleSuccessfulTranscript(@NonNull ClientConversation clientConversation, long j, @NonNull AudioMessagePayload audioMessagePayload, @NonNull TranscriptUpdatePush transcriptUpdatePush) {
        String transcript = transcriptUpdatePush.getTranscript();
        if (transcript == null) {
            LOG.w("Got a Transcript Update push but transcript was null.");
            recordFailureMetric(clientConversation.getConversationId(), j, TranscriptStatus.InvalidTranscript);
            return;
        }
        audioMessagePayload.setTranscript(transcript);
        this.messagingProviderWrapper.updateMessagePayload(clientConversation, j, audioMessagePayload);
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.MSG_AUD_TRANSCRIPT_SUCCESS);
        LOG.i(String.format("Got a Transcript Update push and updated message %s/%d", clientConversation.getConversationId(), Long.valueOf(j)));
    }

    private void recordFailureMetric(String str, long j, @NonNull TranscriptStatus transcriptStatus) {
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.MSG_AUD_TRANSCRIPT_FAILED);
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put(MetricKeys.META_COMMS_ITEM_ID, str + "/" + j);
        generateOperational.getMetadata().put("source", transcriptStatus);
        MetricsHelper.recordSingleOccurrence(generateOperational);
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        CommsDaggerWrapper.getComponent().inject(this);
        this.messagingProviderWrapper = new MessagingProviderWrapper(this, this.commsIdentityManager.getCommsId("TranscriptionUpdateService", false), this.commsIdentityManager.getHomeGroupId("TranscriptionUpdateService", false));
        super.onCreate();
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        String string = intent.getExtras().getString(Constants.AMP_KEY);
        if (TextUtils.isEmpty(string)) {
            LOG.w("Got a Transcript Update push but no amznMessage was included.");
            return;
        }
        TranscriptUpdatePush transcriptUpdatePush = (TranscriptUpdatePush) this.jsonConverter.fromJson(string, TranscriptUpdatePush.class);
        this.mTranscriptLatencyMetricHelper.stopTimer(transcriptUpdatePush.getConversationId(), transcriptUpdatePush.getMessageId());
        String conversationId = transcriptUpdatePush.getConversationId();
        long messageId = transcriptUpdatePush.getMessageId();
        TranscriptStatus transcriptStatus = transcriptUpdatePush.getTranscriptStatus();
        if (transcriptStatus == TranscriptStatus.Transcribing) {
            LOG.d(String.format("Got a Transcript Update push for an operation in progress for message %s/%d", conversationId, Long.valueOf(messageId)));
            return;
        }
        ClientConversation conversation = this.messagingProviderWrapper.getConversation(conversationId, null, null);
        if (conversation == null) {
            CommsLogger commsLogger = LOG;
            commsLogger.w("Got a Transcript Update push but unable to find conversation with provided id: " + conversationId);
            recordFailureMetric(conversationId, messageId, TranscriptStatus.InvalidConversation);
            return;
        }
        AudioMessagePayload audioMessagePayload = (AudioMessagePayload) this.messagingProviderWrapper.getMessagePayload(conversationId, messageId, AudioMessagePayload.class);
        if (audioMessagePayload == null) {
            LOG.w(String.format("Got a Transcript Update push but unable to find payload for message %s/%d", conversationId, Long.valueOf(messageId)));
            recordFailureMetric(conversationId, messageId, TranscriptStatus.InvalidMessage);
            return;
        }
        int ordinal = transcriptStatus.ordinal();
        if (ordinal == 0) {
            handleSuccessfulTranscript(conversation, messageId, audioMessagePayload, transcriptUpdatePush);
        } else if (ordinal == 11) {
        } else {
            handleFailedTranscript(conversation, messageId, audioMessagePayload, transcriptStatus);
        }
    }
}
