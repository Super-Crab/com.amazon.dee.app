package com.amazon.alexa.audiocapturer;
/* loaded from: classes6.dex */
public interface AudioCapturerProvider {
    AudioCapturer getAudioCapturer();

    AudioCapturer getAudioCapturer(MetricsListener metricsListener);
}
