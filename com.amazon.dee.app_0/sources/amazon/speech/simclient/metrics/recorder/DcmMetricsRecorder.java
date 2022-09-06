package amazon.speech.simclient.metrics.recorder;

import amazon.speech.simclient.metrics.Counter;
import amazon.speech.simclient.metrics.MetricsRecord;
import amazon.speech.simclient.metrics.Timer;
import amazon.speech.simclient.metrics.dcmadapter.DcmAdapterManager;
import amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface;
import amazon.speech.simclient.metrics.util.HighPriorityMetricsWhiteList;
import android.content.Context;
import java.util.Map;
/* loaded from: classes.dex */
public class DcmMetricsRecorder implements MetricsRecorder {
    private static final String TAG = "DcmMetricsRecorder";
    private final Context mContext;

    public DcmMetricsRecorder(Context context) {
        this.mContext = context;
    }

    private MetricEventInterface createMetricsEvent(MetricsRecord metricsRecord) {
        MetricEventInterface createMetricsEvent = createMetricsEvent(metricsRecord.getProgramName(), metricsRecord.getSourceName());
        if (createMetricsEvent == null) {
            return null;
        }
        if (metricsRecord.getMetadata() == null) {
            return createMetricsEvent;
        }
        for (Map.Entry<String, String> entry : metricsRecord.getMetadata().entrySet()) {
            createMetricsEvent.addString(entry.getKey(), entry.getValue());
        }
        return createMetricsEvent;
    }

    private void recordEvent(MetricEventInterface metricEventInterface, boolean z) {
        DcmAdapterManager.getDcmAdapter().recordMetricsEvent(this.mContext, metricEventInterface, z);
    }

    protected void onRecord(Timer timer, MetricEventInterface metricEventInterface, boolean z) {
        metricEventInterface.addTimer(timer.getName(), timer.getDuration());
        recordEvent(metricEventInterface, z);
    }

    @Override // amazon.speech.simclient.metrics.recorder.MetricsRecorder
    public void record(MetricsRecord<?> metricsRecord) {
        boolean isHighPriorityMetric = HighPriorityMetricsWhiteList.isHighPriorityMetric(metricsRecord.getName());
        MetricEventInterface createMetricsEvent = createMetricsEvent(metricsRecord);
        if (createMetricsEvent == null) {
            return;
        }
        if (metricsRecord instanceof Timer) {
            onRecord((Timer) metricsRecord, createMetricsEvent, isHighPriorityMetric);
        } else if (!(metricsRecord instanceof Counter)) {
            return;
        } else {
            onRecord((Counter) metricsRecord, createMetricsEvent, isHighPriorityMetric);
        }
        metricsRecord.recycle();
    }

    protected void onRecord(Timer timer, MetricEventInterface metricEventInterface) {
        onRecord(timer, metricEventInterface, false);
    }

    protected void onRecord(Counter counter, MetricEventInterface metricEventInterface, boolean z) {
        metricEventInterface.addCounter(counter.getName(), counter.getCount());
        recordEvent(metricEventInterface, z);
    }

    protected void onRecord(Counter counter, MetricEventInterface metricEventInterface) {
        onRecord(counter, metricEventInterface, false);
    }

    private MetricEventInterface createMetricsEvent(String str, String str2) {
        return DcmAdapterManager.getDcmAdapter().createMetricEvent(this.mContext, str, str2);
    }
}
