package com.amazon.alexa.wakeword;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.WakeWordDetector;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import java.io.IOException;
/* loaded from: classes11.dex */
public class WakeWordDetectionController implements WakeWordDetector, WakeWordDetector.WakeWordDetectionListener {
    private static final String TAG = "WakeWordDetectionController";
    private WakeWordDetector.AudioCaptureListener audioCaptureListener;
    private WakeWordDetectingAudioCapturer audioCapturer;
    private final AudioCapturerAuthority audioCapturerAuthority;
    private final Context context;
    private volatile boolean isWakeWordFound;
    private WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener;

    public WakeWordDetectionController(Context context, AudioCapturerAuthority audioCapturerAuthority) {
        Preconditions.notNull(context, "Context is null");
        Preconditions.notNull(audioCapturerAuthority, "AudioCapturerAuthority is null");
        this.context = context;
        this.audioCapturerAuthority = audioCapturerAuthority;
    }

    @Nullable
    private WakeWordDetectingAudioCapturer createWakeWordAudioCapturer(@Nullable MetricsListener metricsListener, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        return this.audioCapturerAuthority.getWakeWordAudioCapturer(this, this.audioCaptureListener, metricsListener, wakeWordDetectionMetricsListener);
    }

    @VisibleForTesting
    AlexaAudioSink createAudioSink() throws IOException {
        return new AlexaAudioSink();
    }

    @VisibleForTesting
    boolean hasRecordingPermissions() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.RECORD_AUDIO") == 0;
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector
    public boolean isDetectingWakeWord() {
        WakeWordDetectingAudioCapturer wakeWordDetectingAudioCapturer = this.audioCapturer;
        return wakeWordDetectingAudioCapturer != null && wakeWordDetectingAudioCapturer.isDetectingWakeWord();
    }

    @VisibleForTesting
    boolean isWakeWordFound() {
        return this.isWakeWordFound;
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
    public void onClassificationEvent(ClassificationData classificationData) {
        WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener = this.wakeWordDetectionListener;
        if (wakeWordDetectionListener != null) {
            wakeWordDetectionListener.onClassificationEvent(classificationData);
        }
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
    public void onEnrollmentExampleEvent(EnrollmentData enrollmentData) {
        WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener = this.wakeWordDetectionListener;
        if (wakeWordDetectionListener != null) {
            wakeWordDetectionListener.onEnrollmentExampleEvent(enrollmentData);
        }
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
    public void onWakewordDetected(WakeWordData wakeWordData) {
        this.isWakeWordFound = true;
        stopDetectingWakeWord();
        WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener = this.wakeWordDetectionListener;
        if (wakeWordDetectionListener != null) {
            wakeWordDetectionListener.onWakewordDetected(wakeWordData);
        }
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector
    public void pauseDetectingWakeWord() {
        Log.i(TAG, "pauseDetectingWakeWord");
        if (!isDetectingWakeWord()) {
            return;
        }
        this.audioCapturer.pauseDetectingWakeWord();
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector
    public void resumeDetectingWakeWord() {
        Log.i(TAG, "resumeDetectingWakeWord");
        if (!isDetectingWakeWord()) {
            return;
        }
        this.audioCapturer.resumeDetectingWakeWord();
    }

    public void setAudioCaptureListener(WakeWordDetector.AudioCaptureListener audioCaptureListener) {
        this.audioCaptureListener = audioCaptureListener;
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector
    public void setWakeWordDetectionListener(WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener) {
        this.wakeWordDetectionListener = wakeWordDetectionListener;
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector
    public synchronized WakeWordDetector.DetectingStatus startDetectingWakeWord(WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) throws IOException {
        return startDetectingWakeWord(null, wakeWordDetectionMetricsListener);
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector
    public synchronized void stopCapturing() {
        Log.i(TAG, "stopCapturing");
        if (this.audioCapturer != null && this.audioCapturer.isCapturing()) {
            this.audioCapturer.stopCapturing();
        }
        this.audioCapturer = null;
        this.isWakeWordFound = false;
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector
    public synchronized void stopDetectingWakeWord() {
        Log.i(TAG, "stopDetectingWakeWord");
        if (!isDetectingWakeWord()) {
            return;
        }
        if (this.audioCapturer != null && isWakeWordFound()) {
            this.audioCapturer.stopDetectingWakeWord();
        } else {
            stopCapturing();
        }
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector
    public WakeWordDetector.DetectingStatus startDetectingWakeWord(@Nullable MetricsListener metricsListener, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) throws IOException {
        Log.i(TAG, "startDetectingWakeWord");
        if (!hasRecordingPermissions()) {
            Log.w(TAG, "User didn't grant audio record permissions");
            return WakeWordDetector.DetectingStatus.FAILED_TO_START;
        } else if (isDetectingWakeWord()) {
            Log.w(TAG, "wanted to start detecting wake word multiple times");
            return WakeWordDetector.DetectingStatus.STARTED;
        } else {
            AlexaAudioSink createAudioSink = createAudioSink();
            this.audioCapturer = createWakeWordAudioCapturer(metricsListener, wakeWordDetectionMetricsListener);
            WakeWordDetectingAudioCapturer wakeWordDetectingAudioCapturer = this.audioCapturer;
            if (wakeWordDetectingAudioCapturer != null && wakeWordDetectingAudioCapturer.startCapturing(createAudioSink)) {
                this.isWakeWordFound = false;
                return WakeWordDetector.DetectingStatus.STARTED;
            }
            Log.w(TAG, "Failed to start recording audio");
            stopCapturing();
            createAudioSink.abandon();
            return WakeWordDetector.DetectingStatus.FAILED_TO_START;
        }
    }
}
