package com.amazon.alexa.devices.speechrecognizer;
/* loaded from: classes6.dex */
public class AudioProcessingNotification {
    private boolean mListeningforWakeWord;

    public AudioProcessingNotification(boolean z) {
        this.mListeningforWakeWord = z;
    }

    public boolean isListeningForWakeWord() {
        return this.mListeningforWakeWord;
    }
}
