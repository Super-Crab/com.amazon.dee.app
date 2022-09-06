package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
/* loaded from: classes11.dex */
public abstract class ConditionalStep {
    private ExecutionState mExecutionState;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConditionalStep(@NonNull ExecutionState executionState) {
        this.mExecutionState = executionState;
    }

    public ExecutionState getExecutionState() {
        return this.mExecutionState;
    }

    @NonNull
    public abstract StepCommand getStepCommand();

    @NonNull
    public abstract StepType getStepType();

    public abstract boolean isLaunchedInFtue();

    public abstract boolean isRequiredForWakeWord();

    public void mediateExecution() {
        if (!isRequiredForWakeWord() || !isLaunchedInFtue() || !needsExecution()) {
            return;
        }
        this.mExecutionState.reportFtueMandatoryExecution(this);
    }

    public abstract boolean needsExecution();
}
