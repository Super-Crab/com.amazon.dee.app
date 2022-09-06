package com.amazon.alexa.accessory.capabilities.speech;

import com.amazon.alexa.accessory.io.Sink;
/* loaded from: classes.dex */
public interface SpeechSession {

    /* loaded from: classes.dex */
    public interface SpeechSessionCallback {
        void onEndpointSpeech(SpeechSession speechSession);

        void onError(SpeechSession speechSession, Throwable th);

        void onRelease(SpeechSession speechSession);
    }

    void abort();

    void addCallback(SpeechSessionCallback speechSessionCallback);

    void endpointSpeech();

    Sink getSink();

    void release();

    void removeCallback(SpeechSessionCallback speechSessionCallback);
}
