package com.amazon.alexa.handsfree.uservoicerecognition.ui.steps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.amazon.alexa.handsfree.uservoicerecognition.audio.TrainingSound;
import com.amazon.alexa.handsfree.uservoicerecognition.model.EnrollmentStep;
/* loaded from: classes8.dex */
public interface StepsContract {

    /* loaded from: classes8.dex */
    public interface SoundPlayer {

        /* loaded from: classes8.dex */
        public interface SoundCallback {
            void onSilence();
        }

        void playSound(@NonNull TrainingSound trainingSound, @Nullable SoundCallback soundCallback);
    }

    /* loaded from: classes8.dex */
    public interface View {
        void initializeSteps(int i);

        void onFinishEnrollment();

        void readyToListen(@NonNull String str);

        void removeErrorMessageAndUtteranceText();

        void removeUtteranceText();

        void restartTraining();

        void setStepState(int i, EnrollmentStep.TrainingState trainingState);

        void showAnimatedErrorText(@StringRes int i);

        void showAnimatedErrorText(String str);

        void showInstructionAndUtteranceText(@NonNull String str);

        void showLoadingAfterVoiceTraining();

        void showLoadingBeforeVoiceTraining();

        void showNotWorkingDialog();

        void showNotWorkingDialog(@StringRes int i);

        void showNotWorkingDialog(@StringRes int i, @StringRes int i2, String str);

        void showUtteranceText(@NonNull String str);
    }

    /* loaded from: classes8.dex */
    public interface ViewListener {
        void cancelEnrollment();

        void initializeView();

        void onVoiceChromeDismissed();

        void pauseEnrollment();

        void resumeEnrollment();
    }

    /* loaded from: classes8.dex */
    public interface VoiceChrome {
        void dismissVoiceChrome(@NonNull ViewListener viewListener);

        void setVoiceChromeLevelOnFeedback(float f);

        void setVoiceChromeNotRecording();

        void setVoiceChromeRecording();

        void showVoiceChrome();
    }
}
