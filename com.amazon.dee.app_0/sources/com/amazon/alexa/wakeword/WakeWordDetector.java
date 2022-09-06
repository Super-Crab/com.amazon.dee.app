package com.amazon.alexa.wakeword;

import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import java.io.IOException;
/* loaded from: classes11.dex */
public interface WakeWordDetector {

    /* loaded from: classes11.dex */
    public interface AudioCaptureListener {
        void onAudioCaptured(short[] sArr);
    }

    /* loaded from: classes11.dex */
    public enum DetectingStatus {
        STARTED,
        FAILED_TO_START
    }

    /* loaded from: classes11.dex */
    public interface WakeWordDetectionListener {
        void onClassificationEvent(ClassificationData classificationData);

        void onEnrollmentExampleEvent(EnrollmentData enrollmentData);

        void onWakewordDetected(WakeWordData wakeWordData);
    }

    boolean isDetectingWakeWord();

    void pauseDetectingWakeWord();

    void resumeDetectingWakeWord();

    void setWakeWordDetectionListener(WakeWordDetectionListener wakeWordDetectionListener);

    DetectingStatus startDetectingWakeWord(MetricsListener metricsListener, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) throws IOException;

    DetectingStatus startDetectingWakeWord(WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) throws IOException;

    void stopCapturing();

    void stopDetectingWakeWord();
}
