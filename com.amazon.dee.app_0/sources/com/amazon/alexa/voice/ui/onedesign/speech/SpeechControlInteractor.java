package com.amazon.alexa.voice.ui.onedesign.speech;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract;
import com.amazon.alexa.voice.ui.permissions.VoicePermissions;
import com.amazon.alexa.voice.ui.speech.SpeechController;
/* loaded from: classes11.dex */
public final class SpeechControlInteractor implements SpeechControlContract.Interactor {
    private final SpeechController speechController;
    private final VoicePermissions voicePermissions;

    public SpeechControlInteractor(SpeechController speechController, @Nullable VoicePermissions voicePermissions) {
        this.speechController = speechController;
        this.voicePermissions = voicePermissions;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract.Interactor
    public void cancel() {
        this.speechController.cancel();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract.Interactor
    public void recognizeSpeech() {
        if (this.speechController.hasMinimumRequiredPermission()) {
            this.speechController.recognizeSpeech();
            return;
        }
        VoicePermissions voicePermissions = this.voicePermissions;
        if (voicePermissions != null) {
            voicePermissions.requestPermissions();
            return;
        }
        throw new IllegalStateException("VoicePermissions should not be null, if we don't have a minimum permissions");
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract.Interactor
    public boolean shouldEmbedVoiceIngress() {
        return this.speechController.hasMinimumRequiredPermission() || this.voicePermissions != null;
    }
}
