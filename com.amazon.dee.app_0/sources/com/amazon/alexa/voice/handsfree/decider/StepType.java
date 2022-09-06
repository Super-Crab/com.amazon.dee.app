package com.amazon.alexa.voice.handsfree.decider;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.AISProfileHandsFreeIntroConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.AISUserVoiceRecognitionEnrollmentConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.AlexaAppLocationPermissionConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.AlexaAppSignInConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.DoubleMicrophonePermissionsConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.HandsFreeIntroConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.LanguageSelectorConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.LockScreenSettingsConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.ProfileSelectionConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.SingleMicrophonePermissionConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.UserVoiceRecognitionEnrollmentConditionalStep;
/* loaded from: classes11.dex */
public enum StepType {
    ALEXA_APP_SIGN_IN("ALEXA_APP_SIGN_IN") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.1
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new AlexaAppSignInConditionalStep(context);
        }
    },
    PROFILE_SELECTION("PROFILE_SELECTION") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.2
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new ProfileSelectionConditionalStep(context);
        }
    },
    HANDS_FREE_INTRO("HANDS_FREE_INTRO") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.3
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new HandsFreeIntroConditionalStep(context);
        }
    },
    ALEXA_APP_AUDIO_PERMISSION("ALEXA_APP_AUDIO_PERMISSION") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.4
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new SingleMicrophonePermissionConditionalStep(context);
        }
    },
    ALEXA_APP_LOCATION_PERMISSION("ALEXA_APP_LOCATION_PERMISSION") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.5
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new AlexaAppLocationPermissionConditionalStep(context);
        }
    },
    HANDS_FREE_PERMISSIONS("HANDS_FREE_PERMISSIONS") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.6
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new DoubleMicrophonePermissionsConditionalStep(context);
        }
    },
    LANGUAGE_SELECTOR("LANGUAGE_SELECTOR") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.7
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new LanguageSelectorConditionalStep(context);
        }
    },
    USER_VOICE_RECOGNITION_ENROLLMENT("USER_VOICE_RECOGNITION_ENROLLMENT") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.8
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new UserVoiceRecognitionEnrollmentConditionalStep(context);
        }
    },
    AIS_USER_VOICE_RECOGNITION_ENROLLMENT("AIS_USER_VOICE_RECOGNITION_ENROLLMENT") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.9
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new AISUserVoiceRecognitionEnrollmentConditionalStep(context);
        }
    },
    AIS_PROFILE_HANDS_FREE_INTRO("AIS_PROFILE_HANDS_FREE_INTRO") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.10
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new AISProfileHandsFreeIntroConditionalStep(context);
        }
    },
    LOCK_SCREEN_SETTING("LOCK_SCREEN_SETTING") { // from class: com.amazon.alexa.voice.handsfree.decider.StepType.11
        @Override // com.amazon.alexa.voice.handsfree.decider.StepType
        public ConditionalStep getStep(@NonNull Context context) {
            return new LockScreenSettingsConditionalStep(context);
        }
    };
    
    private final String mKey;

    public String getKey() {
        return this.mKey;
    }

    public abstract ConditionalStep getStep(@NonNull Context context);

    StepType(@NonNull String str) {
        this.mKey = str;
    }
}
