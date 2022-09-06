package com.amazon.alexa.audiocapturer;

import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.audiocapturer.RecordingRunnable;
import com.amazon.alexa.utils.LazyTimeProvider;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes6.dex */
public class SimpleAudioCapturer implements AudioCapturer {
    private static final long START_RECORDING_TIMEOUT_MS = 5000;
    private static final String TAG = "SimpleAudioCapturer";
    private final ExecutorService executorService;
    private final LazyTimeProvider lazyTimeProvider;
    private final MetricsListener metricListener;
    private RecordingRunnable recordingRunnable;
    private final AtomicBoolean recordingStarted;
    private final ConditionVariable startRecordingCondition;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes6.dex */
    public class RecordingListener implements RecordingRunnable.RecordingListener {
        private final long micInitializationStartTime;

        RecordingListener(long j) {
            this.micInitializationStartTime = j;
        }

        @Override // com.amazon.alexa.audiocapturer.RecordingRunnable.RecordingListener
        public void onRecordingError(Throwable th) {
            Log.e(SimpleAudioCapturer.TAG, "Error recording audio", th);
            SimpleAudioCapturer.this.startRecordingCondition.open();
            if (SimpleAudioCapturer.this.isReportingMetric()) {
                SimpleAudioCapturer.this.metricListener.onMicInitializationFailure();
            }
        }

        @Override // com.amazon.alexa.audiocapturer.RecordingRunnable.RecordingListener
        public void onRecordingStarted() {
            SimpleAudioCapturer.this.recordingStarted.set(true);
            SimpleAudioCapturer.this.startRecordingCondition.open();
            if (SimpleAudioCapturer.this.isReportingMetric()) {
                SimpleAudioCapturer.this.metricListener.onMicInitializationSuccess(SimpleAudioCapturer.this.getCurrentTime() - this.micInitializationStartTime);
            }
        }

        @Override // com.amazon.alexa.audiocapturer.RecordingRunnable.RecordingListener
        public void onRecordingStopped() {
            SimpleAudioCapturer.this.recordingStarted.set(false);
            SimpleAudioCapturer.this.startRecordingCondition.open();
            SimpleAudioCapturer.this.release();
        }
    }

    public SimpleAudioCapturer() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getCurrentTime() {
        if (isReportingMetric()) {
            return this.lazyTimeProvider.mo2864get().elapsedRealTime();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReportingMetric() {
        return this.metricListener != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        this.recordingRunnable = null;
        this.executorService.shutdown();
        onRelease();
    }

    @VisibleForTesting
    RecordingListener createRecordingListener(long j) {
        return new RecordingListener(j);
    }

    @VisibleForTesting
    RecordingRunnable createRecordingRunnable(AlexaAudioSink alexaAudioSink, RecordingListener recordingListener) {
        return new RecordingRunnable(alexaAudioSink, recordingListener);
    }

    @Override // com.amazon.alexa.audiocapturer.AudioCapturer
    public synchronized boolean isCapturing() {
        boolean z;
        if (this.recordingRunnable != null) {
            if (!this.recordingRunnable.isDone()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRelease() {
    }

    @Override // com.amazon.alexa.audiocapturer.AudioCapturer
    public synchronized boolean startCapturing(AlexaAudioSink alexaAudioSink) {
        Preconditions.notNull(alexaAudioSink, "Audio sink is null");
        this.recordingRunnable = createRecordingRunnable(alexaAudioSink, createRecordingListener(getCurrentTime()));
        this.executorService.execute(this.recordingRunnable);
        if (!this.startRecordingCondition.block(5000L)) {
            Log.e(TAG, "Recording didn't start within 5000 ms");
            if (isReportingMetric()) {
                this.metricListener.onMicInitializationFailure();
            }
        }
        return this.recordingStarted.getAndSet(false);
    }

    @Override // com.amazon.alexa.audiocapturer.AudioCapturer
    public synchronized void stopCapturing() {
        if (isCapturing()) {
            this.recordingRunnable.stop();
        }
        release();
    }

    public SimpleAudioCapturer(@Nullable MetricsListener metricsListener) {
        this(ExecutorFactory.newSingleThreadExecutor("AudioCapturer"), metricsListener);
    }

    @VisibleForTesting
    SimpleAudioCapturer(ExecutorService executorService, @Nullable MetricsListener metricsListener) {
        this.lazyTimeProvider = new LazyTimeProvider();
        Preconditions.notNull(executorService, "ExecutorService is null");
        this.executorService = executorService;
        this.metricListener = metricsListener;
        this.startRecordingCondition = new ConditionVariable();
        this.recordingStarted = new AtomicBoolean();
    }
}
