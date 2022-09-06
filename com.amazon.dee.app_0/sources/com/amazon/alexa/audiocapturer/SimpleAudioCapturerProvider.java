package com.amazon.alexa.audiocapturer;

import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public class SimpleAudioCapturerProvider implements AudioCapturerProvider {
    @Override // com.amazon.alexa.audiocapturer.AudioCapturerProvider
    public AudioCapturer getAudioCapturer() {
        return new SimpleAudioCapturer();
    }

    @Override // com.amazon.alexa.audiocapturer.AudioCapturerProvider
    public AudioCapturer getAudioCapturer(@Nullable MetricsListener metricsListener) {
        return new SimpleAudioCapturer(metricsListener);
    }
}
