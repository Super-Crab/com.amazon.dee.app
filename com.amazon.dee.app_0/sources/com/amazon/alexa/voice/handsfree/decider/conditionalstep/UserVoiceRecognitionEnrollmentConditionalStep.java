package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.UserVoiceRecognitionEnrollmentStepCommand;
/* loaded from: classes11.dex */
public class UserVoiceRecognitionEnrollmentConditionalStep extends ConditionalStep {
    private final StepCommand mStepCommand;
    private final UVRVendorSettings mUVRVendorSettings;

    public UserVoiceRecognitionEnrollmentConditionalStep(@NonNull Context context) {
        this(new UserVoiceRecognitionEnrollmentStepCommand(context), ExecutionStateMediator.getInstance(), UVRModule.INSTANCE.isUVRAvailable() ? UVRModule.INSTANCE.getUVRContract().getVendorSettings() : null);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.USER_VOICE_RECOGNITION_ENROLLMENT;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isLaunchedInFtue() {
        return true;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isRequiredForWakeWord() {
        UVRVendorSettings uVRVendorSettings = this.mUVRVendorSettings;
        return uVRVendorSettings != null && uVRVendorSettings.isUVRAvailable() && this.mUVRVendorSettings.isUVRMandatory();
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean needsExecution() {
        UVRVendorSettings uVRVendorSettings = this.mUVRVendorSettings;
        if (uVRVendorSettings != null && uVRVendorSettings.isUVRMandatory()) {
            return !this.mUVRVendorSettings.isUVREnrolled(UserInfo.DEFAULT_USER);
        }
        return false;
    }

    @VisibleForTesting
    UserVoiceRecognitionEnrollmentConditionalStep(@NonNull StepCommand stepCommand, @NonNull ExecutionState executionState, @Nullable UVRVendorSettings uVRVendorSettings) {
        super(executionState);
        this.mStepCommand = stepCommand;
        this.mUVRVendorSettings = uVRVendorSettings;
    }
}
