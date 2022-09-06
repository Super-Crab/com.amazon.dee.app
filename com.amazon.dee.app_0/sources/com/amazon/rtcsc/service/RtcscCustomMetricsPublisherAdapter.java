package com.amazon.rtcsc.service;

import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.amazon.rtcsc.wrappers.CounterVector;
import com.amazon.rtcsc.wrappers.MetadataVector;
import com.amazon.rtcsc.wrappers.RTCCustomMetricInterface;
import com.amazon.rtcsc.wrappers.RTCCustomMetricsPublisherAdapterInterface;
import com.amazon.rtcsc.wrappers.TimerVector;
/* loaded from: classes13.dex */
public class RtcscCustomMetricsPublisherAdapter extends RTCCustomMetricsPublisherAdapterInterface {
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscCustomMetricsPublisherAdapter.class);
    private MetricsFactory mMetricsFactory;

    public RtcscCustomMetricsPublisherAdapter(MetricsFactory metricsFactory) {
        this.mMetricsFactory = metricsFactory;
    }

    public MetricEvent createMetricEvent(String str, String str2) {
        return this.mMetricsFactory.createMetricEvent(str, str2);
    }

    @Override // com.amazon.rtcsc.wrappers.RTCCustomMetricsPublisherAdapterInterface
    public boolean recordMetric(RTCCustomMetricInterface rTCCustomMetricInterface) {
        if (rTCCustomMetricInterface == null) {
            this.mLog.e("recordMetric. null metric passed.");
            return false;
        } else if (this.mMetricsFactory == null) {
            this.mLog.e("MetricsFactory is null");
            return false;
        } else {
            String programName = rTCCustomMetricInterface.getProgramName();
            String sourceName = rTCCustomMetricInterface.getSourceName();
            this.mLog.i(String.format("recordMetric: metric program name: %s, metric source name: %s", programName, sourceName));
            TimerVector timers = rTCCustomMetricInterface.getTimers();
            int size = timers.size();
            CounterVector counters = rTCCustomMetricInterface.getCounters();
            int size2 = counters.size();
            MetadataVector metadata = rTCCustomMetricInterface.getMetadata();
            int size3 = metadata.size();
            RTCCustomMetricInterface.Priority priority = rTCCustomMetricInterface.getPriority();
            MetricEvent createMetricEvent = createMetricEvent(programName, sourceName);
            for (int i = 0; i < size; i++) {
                createMetricEvent.addTimer(timers.mo4532get(i).getName(), Double.valueOf(timers.mo4532get(i).getValue()).doubleValue());
            }
            for (int i2 = 0; i2 < size2; i2++) {
                createMetricEvent.addCounter(counters.mo4528get(i2).getName(), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                createMetricEvent.incrementCounter(counters.mo4528get(i2).getName(), counters.mo4528get(i2).getValue());
            }
            for (int i3 = 0; i3 < size3; i3++) {
                createMetricEvent.addString(metadata.mo4530get(i3).getName(), metadata.mo4530get(i3).getValue());
            }
            if (priority == RTCCustomMetricInterface.Priority.CRITICAL) {
                this.mMetricsFactory.record(createMetricEvent, Priority.CRITICAL, Channel.ANONYMOUS);
            } else if (priority == RTCCustomMetricInterface.Priority.HIGH) {
                this.mMetricsFactory.record(createMetricEvent, Priority.HIGH, Channel.ANONYMOUS);
            } else {
                this.mMetricsFactory.record(createMetricEvent, Priority.NORMAL, Channel.ANONYMOUS);
            }
            return true;
        }
    }
}
