package com.amazon.alexa.accessory.repositories.speech;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.repositories.speech.SpeechProducer;
/* loaded from: classes6.dex */
public class MemorySpeechRepository extends BaseProducer<SpeechProducer.ActionHandler> implements SpeechRepository, SpeechProducer {
    @Override // com.amazon.alexa.accessory.repositories.speech.SpeechRepository
    public void initiateSpeech() {
        getHandler().handleInitiateSpeech();
    }
}
