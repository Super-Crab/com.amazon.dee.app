package com.amazon.alexa.wakeword;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes11.dex */
public class WakeWordData {
    private final AlexaWakeWord alexaWakeWord;
    private final AlexaAudioSink audioSink;
    private final byte[] metadata;

    public WakeWordData(AlexaAudioSink alexaAudioSink, AlexaWakeWord alexaWakeWord, @Nullable byte[] bArr) {
        Preconditions.notNull(alexaAudioSink, "audio sink is null");
        Preconditions.notNull(alexaWakeWord, "alexa wake word is null");
        this.audioSink = alexaAudioSink;
        this.alexaWakeWord = alexaWakeWord;
        this.metadata = bArr;
    }

    public AlexaWakeWord getAlexaWakeWord() {
        return this.alexaWakeWord;
    }

    public AlexaAudioSink getAudioSink() {
        return this.audioSink;
    }

    @Nullable
    public byte[] getMetadata() {
        return this.metadata;
    }
}
