package amazon.speech.simclient.metrics.recorder;

import amazon.speech.simclient.metrics.MetricsRecord;
import android.os.Handler;
import android.os.HandlerThread;
/* loaded from: classes.dex */
public class AsyncMetricsRecorder implements MetricsRecorder {
    private static final Handler gHandler;
    private static final HandlerThread gHandlerThread = new HandlerThread("Metrics Handler", 20);
    private final MetricsRecorder mMetricsRecorder;

    static {
        gHandlerThread.setDaemon(true);
        gHandlerThread.start();
        gHandler = new Handler(gHandlerThread.getLooper());
    }

    public AsyncMetricsRecorder(MetricsRecorder metricsRecorder) {
        if (metricsRecorder != null) {
            this.mMetricsRecorder = metricsRecorder;
            return;
        }
        throw new IllegalArgumentException("recorder cannot be null");
    }

    HandlerThread getHandlerThread() {
        return gHandlerThread;
    }

    @Override // amazon.speech.simclient.metrics.recorder.MetricsRecorder
    public void record(final MetricsRecord<?> metricsRecord) {
        gHandler.post(new Runnable() { // from class: amazon.speech.simclient.metrics.recorder.AsyncMetricsRecorder.1
            @Override // java.lang.Runnable
            public void run() {
                AsyncMetricsRecorder.this.mMetricsRecorder.record(metricsRecord);
            }
        });
    }
}
