package com.amazon.alexa;

import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AudioFormat;
import com.amazon.alexa.client.alexaservice.audioprovider.AlexaAudioSource;
import javax.inject.Singleton;
/* compiled from: SourceFactory.java */
@Singleton
/* loaded from: classes.dex */
public class yaw {
    public AlexaAudioSource zZm(AlexaAudioSink alexaAudioSink, String str) {
        return new AlexaAudioSource(alexaAudioSink, str);
    }

    public AlexaAudioSource zZm(AlexaAudioSink alexaAudioSink) {
        return new AlexaAudioSource(alexaAudioSink, AudioFormat.AUDIO_L16_RATE_16000_CHANNELS_1.name());
    }

    public ZZq zZm(AlexaDataSink alexaDataSink) {
        return new ZZq(alexaDataSink);
    }
}
