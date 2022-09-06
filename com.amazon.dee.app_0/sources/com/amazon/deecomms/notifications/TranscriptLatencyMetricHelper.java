package com.amazon.deecomms.notifications;

import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.messaging.model.payload.AudioMessagePayload;
import com.amazon.deecomms.util.StopWatch;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class TranscriptLatencyMetricHelper {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TranscriptLatencyMetricHelper.class);
    private static final int MAX_LATENCY_DURATION_IN_SECONDS = 7200;
    private final StopWatch mStopWatch;
    private final Map<String, Boolean> mTULatencyMap = new ConcurrentHashMap();

    public TranscriptLatencyMetricHelper(Handler handler) {
        this.mStopWatch = new StopWatch(handler, MAX_LATENCY_DURATION_IN_SECONDS);
        this.mStopWatch.getRemovalObservable().subscribe(new Action1<String>() { // from class: com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper.1
            @Override // rx.functions.Action1
            public void call(String str) {
                TranscriptLatencyMetricHelper.this.onRemoved(str);
            }
        });
    }

    @Nullable
    private String generateKey(@NonNull String str, long j) {
        if (TextUtils.isEmpty(str) || j < 0) {
            return null;
        }
        return String.format("%s_%d", str, Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRemoved(String str) {
        if (!TextUtils.isEmpty(str) && this.mTULatencyMap.containsKey(str)) {
            CommsLogger commsLogger = LOG;
            commsLogger.w("Never received transcript-update push for key" + str);
        }
    }

    public void startTimer(@NonNull Message message) {
        AudioMessagePayload audioMessagePayload;
        if (!"message/audio".equals(message.getType()) || (audioMessagePayload = (AudioMessagePayload) message.getPayload()) == null || audioMessagePayload.getTranscript() != null) {
            return;
        }
        String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("TranscriptLatencyMetricHelper.startTimer", false);
        if (!TextUtils.isEmpty(commsId)) {
            startTimer(message.getConversationId(), message.getMessageId(), commsId.equals(message.getSenderCommsId()));
        } else {
            LOG.w("Unable to start timer; no comms id; comms may not initialized yet");
        }
    }

    public void stopTimer(@NonNull String str, long j) {
        stopTimer(generateKey(str, j));
    }

    private void stopTimer(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Boolean remove = this.mTULatencyMap.remove(str);
        long elapsedTime = this.mStopWatch.elapsedTime(str);
        if (remove != null && elapsedTime != -1) {
            TimerMetric generateOperational = TimerMetric.generateOperational(remove.booleanValue() ? MetricKeys.MSG_AUD_TRANS_SEND_DELAY : MetricKeys.MSG_AUD_TRANS_RECV_DELAY);
            generateOperational.setTimeDelta(elapsedTime);
            MetricsHelper.recordTimerMetric(generateOperational);
        }
        this.mStopWatch.stop(str);
    }

    public void startTimer(@NonNull String str, long j, boolean z) {
        startTimer(generateKey(str, j), z);
    }

    private void startTimer(@Nullable String str, boolean z) {
        if (!TextUtils.isEmpty(str) && !this.mTULatencyMap.containsKey(str)) {
            this.mStopWatch.start(str);
            this.mTULatencyMap.put(str, Boolean.valueOf(z));
        }
    }
}
