package com.amazon.alexa.dialogcontroller;

import com.amazon.alexa.api.AlexaAudioProviderConnection;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.audiocapturer.AudioCapturerProvider;
/* loaded from: classes7.dex */
public final class DialogControllerFactory {
    private DialogControllerFactory() {
        throw new UnsupportedOperationException("don't instantiate");
    }

    public static DialogController create(AudioCapturerProvider audioCapturerProvider, AlexaServicesConnection alexaServicesConnection) {
        return new InternalDialogController(audioCapturerProvider, alexaServicesConnection);
    }

    public static DialogController create(AudioCapturerProvider audioCapturerProvider, AlexaAudioProviderConnection alexaAudioProviderConnection) {
        return new ExternalDialogController(audioCapturerProvider, alexaAudioProviderConnection);
    }
}
