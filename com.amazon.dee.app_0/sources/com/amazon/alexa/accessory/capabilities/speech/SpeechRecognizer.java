package com.amazon.alexa.accessory.capabilities.speech;
/* loaded from: classes.dex */
public interface SpeechRecognizer {
    boolean isSpeechRecognitionEnabled(String str);

    SpeechSession recognizeSpeech(SpeechSettings speechSettings);

    void release();

    void stopSpeech(String str);
}
