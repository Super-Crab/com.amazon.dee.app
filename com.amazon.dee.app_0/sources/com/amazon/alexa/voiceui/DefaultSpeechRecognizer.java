package com.amazon.alexa.voiceui;

import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.voiceui.voice.AlexaUserInterfaceOptionsTracker;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
class DefaultSpeechRecognizer implements SpeechRecognizer {
    private final AlexaServicesApis alexaServicesApis;
    private final AlexaUserInterfaceOptionsTracker alexaUserInterfaceOptionsTracker;
    private final AndroidPermissionsChecker androidPermissionsChecker;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DefaultSpeechRecognizer(AndroidPermissionsChecker androidPermissionsChecker, AlexaServicesApis alexaServicesApis, AlexaUserInterfaceOptionsTracker alexaUserInterfaceOptionsTracker) {
        this.androidPermissionsChecker = androidPermissionsChecker;
        this.alexaServicesApis = alexaServicesApis;
        this.alexaUserInterfaceOptionsTracker = alexaUserInterfaceOptionsTracker;
    }

    @Override // com.amazon.alexa.voice.ui.speech.SpeechRecognizer
    public void cancel() {
        this.alexaServicesApis.cancel();
    }

    @Override // com.amazon.alexa.voice.ui.speech.SpeechRecognizer
    public boolean recognizeSpeech(String str) {
        if (!this.androidPermissionsChecker.hasMinimumRequiredPermission() || !this.alexaServicesApis.isConnected()) {
            return false;
        }
        this.alexaServicesApis.start(str, this.alexaUserInterfaceOptionsTracker.getAlexaUserInterfaceOptions());
        return true;
    }
}
