package com.amazon.alexa.wakeword;

import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes11.dex */
public class EnrollmentData {
    private final AlexaWakeWord alexaWakeWord;
    private final short[] example;
    private final byte[] metadata;

    public EnrollmentData(AlexaWakeWord alexaWakeWord, short[] sArr, byte[] bArr) {
        Preconditions.notNull(alexaWakeWord, "alexa wake word is null");
        Preconditions.notNull(sArr, "example is null");
        Preconditions.notNull(bArr, "metadata is null");
        this.alexaWakeWord = alexaWakeWord;
        this.example = sArr;
        this.metadata = bArr;
    }

    public AlexaWakeWord getAlexaWakeWord() {
        return this.alexaWakeWord;
    }

    public short[] getExample() {
        return this.example;
    }

    public byte[] getMetadata() {
        return this.metadata;
    }
}
