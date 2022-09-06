package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.AISUserVoiceRecognitionEnrollmentStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
/* loaded from: classes11.dex */
public class AISUserVoiceRecognitionEnrollmentConditionalStep extends ConditionalStep {
    private final StepCommand mStepCommand;

    public AISUserVoiceRecognitionEnrollmentConditionalStep(@NonNull Context context) {
        this(new AISUserVoiceRecognitionEnrollmentStepCommand(context), ExecutionStateMediator.getInstance());
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.AIS_USER_VOICE_RECOGNITION_ENROLLMENT;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isLaunchedInFtue() {
        return true;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isRequiredForWakeWord() {
        return true;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean needsExecution() {
        return true;
    }

    @VisibleForTesting
    AISUserVoiceRecognitionEnrollmentConditionalStep(@NonNull StepCommand stepCommand, @NonNull ExecutionState executionState) {
        super(executionState);
        this.mStepCommand = stepCommand;
    }
}
