package amazon.speech.simclient.metrics.recorder;

import amazon.speech.simclient.metrics.MetricsRecord;
/* loaded from: classes.dex */
public interface MetricsRecorder {
    void record(MetricsRecord<?> metricsRecord);
}
