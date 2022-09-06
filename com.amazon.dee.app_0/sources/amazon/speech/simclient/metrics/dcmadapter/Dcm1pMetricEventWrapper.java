package amazon.speech.simclient.metrics.dcmadapter;

import com.amazon.client.metrics.MetricEvent;
/* loaded from: classes.dex */
class Dcm1pMetricEventWrapper implements MetricEventInterface {
    private MetricEvent mDcmMetricEvent;

    public Dcm1pMetricEventWrapper(MetricEvent metricEvent) {
        this.mDcmMetricEvent = metricEvent;
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void addCounter(String str, double d) {
        this.mDcmMetricEvent.addCounter(str, d);
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void addString(String str, String str2) {
        this.mDcmMetricEvent.addString(str, str2);
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void addTimer(String str, double d) {
        this.mDcmMetricEvent.addTimer(str, d);
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void clear() {
        this.mDcmMetricEvent.clear();
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public Object getDcmMetricEvent() {
        return this.mDcmMetricEvent;
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void incrementCounter(String str, double d) {
        this.mDcmMetricEvent.incrementCounter(str, d);
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void removeTimer(String str) {
        this.mDcmMetricEvent.removeTimer(str);
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void startTimer(String str) {
        this.mDcmMetricEvent.startTimer(str);
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void stopTimer(String str) {
        this.mDcmMetricEvent.stopTimer(str);
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface
    public void addTimer(String str, double d, int i) {
        this.mDcmMetricEvent.addTimer(str, d, i);
    }
}
