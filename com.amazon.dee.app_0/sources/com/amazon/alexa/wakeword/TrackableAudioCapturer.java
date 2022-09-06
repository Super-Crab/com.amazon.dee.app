package com.amazon.alexa.wakeword;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.audiocapturer.SimpleAudioCapturer;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes11.dex */
public class TrackableAudioCapturer extends SimpleAudioCapturer {
    private final RecordingTracker capturingListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrackableAudioCapturer(RecordingTracker recordingTracker, @Nullable MetricsListener metricsListener) {
        super(metricsListener);
        Preconditions.notNull(recordingTracker, "listener is null");
        this.capturingListener = recordingTracker;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.audiocapturer.SimpleAudioCapturer
    public void onRelease() {
        super.onRelease();
        this.capturingListener.onCapturingFinished();
    }

    @Override // com.amazon.alexa.audiocapturer.SimpleAudioCapturer, com.amazon.alexa.audiocapturer.AudioCapturer
    public boolean startCapturing(AlexaAudioSink alexaAudioSink) {
        this.capturingListener.onCapturingStarted();
        return super.startCapturing(alexaAudioSink);
    }
}
