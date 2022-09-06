package com.amazon.alexa.wakeword;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.WakeWordDetector;
import com.amazon.pryon.android.asr.PryonLite5000;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ExecutorService;
/* loaded from: classes11.dex */
public class BufferingWakeWordDetector {
    private static final int BUFFER_CAPACITY_IN_SECONDS = 3;
    @VisibleForTesting
    static final int CAPACITY = 48000;
    private static final int SAMPLE_RATE = 16000;
    private static final String TAG = "BufferingWakeWordDetector";
    private final WakeWordDetector.AudioCaptureListener audioCaptureListener;
    private final ExecutorService executor;
    private final MetricsListener metricsListener;
    private PryonLite5000 pryon;
    private PryonRecordingRunnable recordingRunnable;
    private final ShortRingBuffer ringBuffer;

    public BufferingWakeWordDetector(PryonLite5000 pryonLite5000, @Nullable WakeWordDetector.AudioCaptureListener audioCaptureListener, @Nullable MetricsListener metricsListener) {
        this(pryonLite5000, audioCaptureListener, metricsListener, ExecutorFactory.newSingleThreadExecutor("WakeWordDetector"));
    }

    private void release() {
        Log.i(TAG, "release");
        if (this.pryon == null) {
            return;
        }
        this.pryon = null;
        this.recordingRunnable = null;
        this.executor.shutdown();
    }

    @VisibleForTesting
    PryonRecordingRunnable createRecordingRunnable() {
        return new PryonRecordingRunnable(this.pryon, this.ringBuffer, this.audioCaptureListener, this.metricsListener);
    }

    public ShortRingBuffer getRingBuffer() {
        return this.ringBuffer;
    }

    public long getSamplesPushedToPryon() {
        PryonRecordingRunnable pryonRecordingRunnable = this.recordingRunnable;
        if (pryonRecordingRunnable != null) {
            return pryonRecordingRunnable.getSamplesPushedToPryon();
        }
        return 0L;
    }

    @VisibleForTesting
    boolean hasPryon() {
        return PryonLite5000.isAvailable() && this.pryon != null;
    }

    public boolean isCapturing() {
        PryonRecordingRunnable pryonRecordingRunnable = this.recordingRunnable;
        return pryonRecordingRunnable != null && pryonRecordingRunnable.isCapturing();
    }

    public boolean isDetectingWakeWord() {
        PryonRecordingRunnable pryonRecordingRunnable = this.recordingRunnable;
        return pryonRecordingRunnable != null && pryonRecordingRunnable.isDetectingWakeWord();
    }

    public void pauseDetectingWakeWord() {
        if (!hasPryon() || !isDetectingWakeWord()) {
            return;
        }
        this.recordingRunnable.setWakeWordDetectionPaused(true);
    }

    public void resumeDetectingWakeWord() {
        if (!hasPryon() || !isDetectingWakeWord()) {
            return;
        }
        this.recordingRunnable.setWakeWordDetectionPaused(false);
    }

    public boolean startDetectingWakeWord() {
        if (hasPryon() && !isCapturing()) {
            this.recordingRunnable = createRecordingRunnable();
            this.executor.execute(this.recordingRunnable);
            return true;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("can't start detecting. pryon initialized: ");
        outline107.append(hasPryon());
        outline107.append(" already running: ");
        outline107.append(isCapturing());
        Log.w(str, outline107.toString());
        return false;
    }

    public void stopCapturing() {
        if (!hasPryon() || !isCapturing()) {
            return;
        }
        this.recordingRunnable.stopCapturing();
        release();
    }

    public void stopDetectingWakeWord() {
        if (!hasPryon() || !isDetectingWakeWord()) {
            return;
        }
        this.recordingRunnable.stopDetectingWakeWord();
    }

    @VisibleForTesting
    BufferingWakeWordDetector(@Nullable PryonLite5000 pryonLite5000, @Nullable WakeWordDetector.AudioCaptureListener audioCaptureListener, @Nullable MetricsListener metricsListener, ExecutorService executorService) {
        Preconditions.notNull(executorService, "executor is null");
        this.executor = executorService;
        this.ringBuffer = new ShortRingBuffer(48000);
        this.metricsListener = metricsListener;
        this.pryon = pryonLite5000;
        this.audioCaptureListener = audioCaptureListener;
    }
}
