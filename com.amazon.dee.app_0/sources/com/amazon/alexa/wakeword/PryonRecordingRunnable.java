package com.amazon.alexa.wakeword;

import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.utils.LazyTimeProvider;
import com.amazon.alexa.voice.pryon.asr.AudioCapturer;
import com.amazon.alexa.voice.pryon.asr.AudioRecordException;
import com.amazon.alexa.wakeword.WakeWordDetector;
import com.amazon.pryon.android.asr.PryonLite5000;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes11.dex */
class PryonRecordingRunnable implements Runnable {
    private static final long SHUTDOWN_TIMEOUT_MS = 1000;
    private static final String TAG = PryonRecordingRunnable.class.getSimpleName();
    private final WakeWordDetector.AudioCaptureListener audioCaptureListener;
    private final MetricsListener metricsListener;
    private final PryonLite5000 pryon;
    private final ShortRingBuffer ringBuffer;
    private volatile boolean wakeWordDetectionPaused;
    private volatile boolean capturing = true;
    private volatile boolean detectingWakeWord = true;
    private AtomicLong samplesPushedToPryon = new AtomicLong(0);
    private final LazyTimeProvider lazyTimeProvider = new LazyTimeProvider();
    private final ConditionVariable shutdownCondition = new ConditionVariable();

    /* JADX INFO: Access modifiers changed from: package-private */
    public PryonRecordingRunnable(PryonLite5000 pryonLite5000, ShortRingBuffer shortRingBuffer, @Nullable WakeWordDetector.AudioCaptureListener audioCaptureListener, @Nullable MetricsListener metricsListener) {
        this.pryon = pryonLite5000;
        this.ringBuffer = shortRingBuffer;
        this.audioCaptureListener = audioCaptureListener;
        this.metricsListener = metricsListener;
    }

    private long getCurrentTime() {
        if (isReportingMetric()) {
            return this.lazyTimeProvider.mo2864get().elapsedRealTime();
        }
        return 0L;
    }

    private boolean isReportingMetric() {
        return this.metricsListener != null;
    }

    private void transferAudio(AudioCapturer audioCapturer) {
        Log.i(TAG, "Beginning transfer of audio buffer to Pryon native component.");
        while (this.capturing) {
            try {
                try {
                    short[] read = audioCapturer.read();
                    this.ringBuffer.write(read, read.length);
                    if (this.detectingWakeWord && !isWakeWordDetectionPaused()) {
                        if (this.audioCaptureListener != null) {
                            this.audioCaptureListener.onAudioCaptured(read);
                        }
                        int pushAudio = this.pryon.pushAudio(read);
                        this.samplesPushedToPryon.getAndAdd(read.length);
                        if (pushAudio != 0) {
                            throw new IOException("Failed to push audio samples to Pryon. Error code = " + pushAudio);
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Failed to transfer audio to Wake Word engine.", e);
                    this.capturing = false;
                    this.detectingWakeWord = false;
                }
            } finally {
                audioCapturer.release();
                this.shutdownCondition.open();
            }
        }
        Log.i(TAG, "Transfer of audio buffer to Pryon is ending.");
    }

    @VisibleForTesting
    AudioCapturer createAudioCapturer() throws AudioRecordException {
        return new AudioCapturer(this.pryon.getSamplesPerFrame(), false, false);
    }

    public long getSamplesPushedToPryon() {
        return this.samplesPushedToPryon.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCapturing() {
        return this.capturing;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDetectingWakeWord() {
        return this.capturing && this.detectingWakeWord;
    }

    @VisibleForTesting
    boolean isWakeWordDetectionPaused() {
        return this.wakeWordDetectionPaused;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            long currentTime = getCurrentTime();
            AudioCapturer createAudioCapturer = createAudioCapturer();
            if (isReportingMetric()) {
                this.metricsListener.onMicInitializationSuccess(getCurrentTime() - currentTime);
            }
            transferAudio(createAudioCapturer);
        } catch (AudioRecordException e) {
            Log.e(TAG, "Failed to initialize AudioCapturer.", e);
            if (!isReportingMetric()) {
                return;
            }
            this.metricsListener.onMicInitializationFailure();
        }
    }

    public void setWakeWordDetectionPaused(boolean z) {
        this.wakeWordDetectionPaused = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopCapturing() {
        Log.i(TAG, "stopCapturing");
        if (isDetectingWakeWord()) {
            stopDetectingWakeWord();
        }
        this.shutdownCondition.close();
        this.capturing = false;
        if (!this.shutdownCondition.block(1000L)) {
            Log.e(TAG, "Failed to stop.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopDetectingWakeWord() {
        Log.i(TAG, "stopDetectingWakeWord");
        this.detectingWakeWord = false;
    }
}
