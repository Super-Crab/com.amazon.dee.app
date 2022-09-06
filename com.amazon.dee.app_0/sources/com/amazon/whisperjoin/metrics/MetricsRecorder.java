package com.amazon.whisperjoin.metrics;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class MetricsRecorder {
    private static final String LATENCY = "Latency";
    private static final String TAG = "MetricsRecorder";
    private static final String WHISPER_JOIN_CLIENT = "WhisperJoinV2Client";
    protected MetricEvent mMetricEvent;
    private MetricTimerWrapper mMetricTimerWrapper;
    protected MetricsFactory mMetricsFactory;
    private WhisperJoinMetricSourceName mWhisperJoinMetricSourceName;

    /* renamed from: com.amazon.whisperjoin.metrics.MetricsRecorder$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$metrics$WhisperJoinMetricName = new int[WhisperJoinMetricName.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$metrics$WhisperJoinMetricName[WhisperJoinMetricName.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$metrics$WhisperJoinMetricName[WhisperJoinMetricName.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public MetricsRecorder(MetricsFactory metricsFactory, WhisperJoinMetricSourceName whisperJoinMetricSourceName, MetricsProgramName metricsProgramName, String str, String str2, MetricTimerWrapper metricTimerWrapper) {
        this.mMetricsFactory = metricsFactory;
        this.mMetricEvent = metricsFactory.createMetricEvent(metricsProgramName.toString(), whisperJoinMetricSourceName.toString());
        this.mWhisperJoinMetricSourceName = whisperJoinMetricSourceName;
        this.mMetricTimerWrapper = metricTimerWrapper;
        this.mMetricEvent.setAnonymous(false);
        this.mMetricEvent.setNonAnonymousCustomerId(str);
        recordString(WHISPER_JOIN_CLIENT, str2);
    }

    private MetricTimer getAndStoreNewMetricsTimer(String str) {
        MetricTimer metricTimer = this.mMetricTimerWrapper.getMetricTimer();
        if (this.mMetricTimerWrapper.getMetricTimerFromProfilerMap(str) != null) {
            String str2 = TAG;
            WJLog.w(str2, "Replacing metric timer instance for: " + str);
        }
        this.mMetricTimerWrapper.storeMetricTimerInProfilerMap(str, metricTimer);
        return metricTimer;
    }

    private MetricTimer getExistingMetricsTimer(String str) {
        MetricTimer metricTimerFromProfilerMap = this.mMetricTimerWrapper.getMetricTimerFromProfilerMap(str);
        if (metricTimerFromProfilerMap == null) {
            String str2 = TAG;
            WJLog.w(str2, "No metric timer found for: " + str);
            return null;
        }
        return metricTimerFromProfilerMap;
    }

    public void addTimer(String str, long j) {
        String str2 = TAG;
        WJLog.v(str2, "addTimer(metricName:[" + str + "], latency: [" + j + "])");
        this.mMetricEvent.addTimer(str, (double) j);
    }

    public void close() {
        this.mMetricsFactory.record(this.mMetricEvent);
    }

    public WhisperJoinMetricSourceName getMetricSourceName() {
        return this.mWhisperJoinMetricSourceName;
    }

    public void incrementCounter(String str) {
        String str2 = TAG;
        WJLog.v(str2, "incrementCounter(metricName:[" + str + "])");
        this.mMetricEvent.incrementCounter(str, 1.0d);
    }

    public void recordCounter(String str, double d) {
        String str2 = TAG;
        WJLog.v(str2, "recordCounter(metricName: [" + str + "], value: [" + d + "])");
        this.mMetricEvent.addCounter(str, d);
    }

    public void recordString(String str, Object obj) {
        String str2 = TAG;
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("recordString(metricName:[", str, "], value: [");
        outline115.append(obj == null ? "null" : obj);
        outline115.append("])");
        WJLog.v(str2, outline115.toString());
        this.mMetricEvent.addString(str, obj == null ? null : obj.toString());
    }

    public void startProfiling(String str) {
        String str2 = TAG;
        WJLog.v(str2, "startProfiling metricOperationName: [" + str + "])");
        getAndStoreNewMetricsTimer(str).start();
    }

    public void startTimer(String str) {
        String str2 = TAG;
        WJLog.v(str2, "startTimer(metricName:[" + str + "])");
        getAndStoreNewMetricsTimer(str).start();
    }

    public void stopProfiling(WhisperJoinMetricName whisperJoinMetricName, String str) {
        String str2 = TAG;
        WJLog.v(str2, "stopProfiling(metricOperationName: [" + str + "])");
        MetricTimer existingMetricsTimer = getExistingMetricsTimer(str);
        if (existingMetricsTimer != null) {
            this.mMetricEvent.addTimer(GeneratedOutlineSupport1.outline75(str, "_", "Latency"), existingMetricsTimer.stop());
        }
        int ordinal = whisperJoinMetricName.ordinal();
        if (ordinal == 0) {
            recordCounter(whisperJoinMetricName.toString(), 1.0d);
        } else if (ordinal == 1) {
            recordCounter(WhisperJoinMetricName.SUCCESS.toString(), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        }
        close();
    }

    public void stopTimer(String str) {
        MetricTimer existingMetricsTimer = getExistingMetricsTimer(str);
        if (existingMetricsTimer != null) {
            addTimer(str, existingMetricsTimer.stop());
        }
    }

    public MetricsRecorder(MetricsFactory metricsFactory, WhisperJoinMetricSourceName whisperJoinMetricSourceName, MetricsProgramName metricsProgramName, String str, MetricTimerWrapper metricTimerWrapper) {
        this.mMetricsFactory = metricsFactory;
        this.mMetricEvent = metricsFactory.createMetricEvent(metricsProgramName.toString(), whisperJoinMetricSourceName.toString());
        this.mWhisperJoinMetricSourceName = whisperJoinMetricSourceName;
        this.mMetricTimerWrapper = metricTimerWrapper;
        this.mMetricEvent.setAnonymous(true);
        recordString(WHISPER_JOIN_CLIENT, str);
    }
}
