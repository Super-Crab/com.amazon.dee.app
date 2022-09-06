package com.amazon.alexa.accessory.avsclient.nearmiss;

import com.amazon.alexa.accessory.capabilities.speech.SpeechSession;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.nearmiss.MlisClient;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
/* loaded from: classes.dex */
public interface NearMissSpeechSession extends SpeechSession {

    /* loaded from: classes.dex */
    public interface Factory {
        NearMissSpeechSession create(MlisClient mlisClient, SpeechSettings speechSettings, RegistrationSupplier registrationSupplier);
    }

    void start();
}
