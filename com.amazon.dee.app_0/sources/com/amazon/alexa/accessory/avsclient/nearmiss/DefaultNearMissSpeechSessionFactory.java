package com.amazon.alexa.accessory.avsclient.nearmiss;

import com.amazon.alexa.accessory.avsclient.nearmiss.NearMissSpeechSession;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.nearmiss.MlisClient;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
/* loaded from: classes.dex */
public final class DefaultNearMissSpeechSessionFactory implements NearMissSpeechSession.Factory {
    @Override // com.amazon.alexa.accessory.avsclient.nearmiss.NearMissSpeechSession.Factory
    public NearMissSpeechSession create(MlisClient mlisClient, SpeechSettings speechSettings, RegistrationSupplier registrationSupplier) {
        return DefaultNearMissSpeechSession.newBuilder().setMlisClient(mlisClient).setRegistrationSupplier(registrationSupplier).setSettings(speechSettings).build();
    }
}
