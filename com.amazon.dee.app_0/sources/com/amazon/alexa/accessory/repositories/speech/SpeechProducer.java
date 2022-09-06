package com.amazon.alexa.accessory.repositories.speech;

import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface SpeechProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleInitiateSpeech();
    }
}
