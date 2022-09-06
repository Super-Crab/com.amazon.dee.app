package com.amazon.alexa.handsfree.uservoicerecognition.audio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract;
/* loaded from: classes8.dex */
public class ExternalTrainingSoundPlayer implements StepsContract.SoundPlayer {
    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.SoundPlayer
    public void playSound(@NonNull TrainingSound trainingSound, @Nullable StepsContract.SoundPlayer.SoundCallback soundCallback) {
        if (soundCallback != null) {
            soundCallback.onSilence();
        }
    }
}
