package com.amazon.alexa.audiocapturer;

import com.amazon.alexa.api.AlexaAudioSink;
/* loaded from: classes6.dex */
public interface AudioCapturer {
    boolean isCapturing();

    boolean startCapturing(AlexaAudioSink alexaAudioSink);

    void stopCapturing();
}
