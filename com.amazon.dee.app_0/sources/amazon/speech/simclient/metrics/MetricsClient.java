package amazon.speech.simclient.metrics;

import amazon.speech.simclient.metrics.recorder.AsyncMetricsRecorder;
import amazon.speech.simclient.metrics.recorder.DcmMetricsRecorder;
import amazon.speech.simclient.metrics.recorder.MetricsRecorder;
import android.content.Context;
/* loaded from: classes.dex */
public class MetricsClient {
    private final String mDefaultProgramName;
    private final String mDefaultSourceName;
    private final MetricsRecorder mRecorder;

    public MetricsClient(Context context, String str, String str2) {
        this(new AsyncMetricsRecorder(new DcmMetricsRecorder(context)), str, str2);
    }

    private <T extends MetricsRecord> T createInstance(Class<? extends T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private <T extends MetricsRecord<?>> T obtain(Class<T> cls, String str) {
        return (T) createInstance(cls).setName(str).setProgramName(this.mDefaultProgramName).setSourceName(this.mDefaultSourceName).setMetricsRecorder(this.mRecorder);
    }

    public Counter obtainCounter(String str) {
        return (Counter) obtain(Counter.class, str);
    }

    public Timer obtainTimer(String str) {
        return (Timer) obtain(Timer.class, str);
    }

    public MetricsClient(MetricsRecorder metricsRecorder, String str, String str2) {
        if (metricsRecorder != null) {
            this.mDefaultProgramName = str;
            this.mDefaultSourceName = str2;
            this.mRecorder = metricsRecorder;
            return;
        }
        throw new IllegalArgumentException("recorder is null");
    }
}
