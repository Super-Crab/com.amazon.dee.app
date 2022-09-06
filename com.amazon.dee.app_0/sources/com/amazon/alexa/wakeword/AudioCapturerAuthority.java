package com.amazon.alexa.wakeword;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.audiocapturer.AudioCapturer;
import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.audiocapturer.SimpleAudioCapturerProvider;
import com.amazon.alexa.wakeword.WakeWordDetector;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
/* loaded from: classes11.dex */
public class AudioCapturerAuthority extends SimpleAudioCapturerProvider {
    private static final String TAG = "AudioCapturerAuthority";
    private final Object lock = new Object();
    private final RecordingTracker recordingTracker;
    private final WakeWordDetectorProvider wakeWordDetectorProvider;

    private AudioCapturerAuthority(RecordingTracker recordingTracker, WakeWordDetectorProvider wakeWordDetectorProvider) {
        this.recordingTracker = recordingTracker;
        this.wakeWordDetectorProvider = wakeWordDetectorProvider;
    }

    public static AudioCapturerAuthority create(RecordingTracker recordingTracker, WakeWordDetectorProvider wakeWordDetectorProvider) {
        return new AudioCapturerAuthority(recordingTracker, wakeWordDetectorProvider);
    }

    @Override // com.amazon.alexa.audiocapturer.SimpleAudioCapturerProvider, com.amazon.alexa.audiocapturer.AudioCapturerProvider
    public AudioCapturer getAudioCapturer() {
        return getAudioCapturer(null);
    }

    @Nullable
    public WakeWordDetectingAudioCapturer getWakeWordAudioCapturer(WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        return getWakeWordAudioCapturer(wakeWordDetectionListener, null, null, wakeWordDetectionMetricsListener);
    }

    public void teardown() {
        synchronized (this.lock) {
            this.wakeWordDetectorProvider.release();
        }
    }

    @Override // com.amazon.alexa.audiocapturer.SimpleAudioCapturerProvider, com.amazon.alexa.audiocapturer.AudioCapturerProvider
    public AudioCapturer getAudioCapturer(@Nullable MetricsListener metricsListener) {
        this.recordingTracker.onCapturerCreated();
        return new TrackableAudioCapturer(this.recordingTracker, metricsListener);
    }

    @Nullable
    public WakeWordDetectingAudioCapturer getWakeWordAudioCapturer(WakeWordDetector.WakeWordDetectionListener wakeWordDetectionListener, @Nullable WakeWordDetector.AudioCaptureListener audioCaptureListener, @Nullable MetricsListener metricsListener, WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        synchronized (this.lock) {
            this.wakeWordDetectorProvider.resetPryon();
            if (this.wakeWordDetectorProvider.mo2864get() == null) {
                Log.w(TAG, "pryon initialization failed. can not init wake word detection");
                return null;
            }
            return new WakeWordDetectingAudioCapturer(this.wakeWordDetectorProvider, wakeWordDetectionListener, audioCaptureListener, metricsListener, wakeWordDetectionMetricsListener);
        }
    }
}
