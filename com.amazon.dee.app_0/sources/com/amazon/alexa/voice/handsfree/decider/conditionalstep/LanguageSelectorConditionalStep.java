package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.LanguageSelectorStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
import com.amazon.alexa.voice.handsfree.settings.locale.HandsFreeLocaleAuthority;
import com.amazon.alexa.voice.handsfree.settings.locale.HandsFreeLocaleAuthorityProvider;
/* loaded from: classes11.dex */
public class LanguageSelectorConditionalStep extends ConditionalStep {
    private static final String TAG = "LanguageSelectorConditionalStep";
    private final HandsFreeLocaleAuthority mHandsFreeLocaleAuthority;
    private final StepCommand mStepCommand;

    public LanguageSelectorConditionalStep(@NonNull Context context) {
        this(ExecutionStateMediator.getInstance(), new LanguageSelectorStepCommand(context), HandsFreeLocaleAuthorityProvider.getInstance(context));
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.LANGUAGE_SELECTOR;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isLaunchedInFtue() {
        return true;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isRequiredForWakeWord() {
        return false;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean needsExecution() {
        boolean areThereMorePendingStepsInFtue = getExecutionState().areThereMorePendingStepsInFtue();
        getExecutionState().flushReportedStepsBuffer();
        return areThereMorePendingStepsInFtue && this.mHandsFreeLocaleAuthority.shouldShowLanguageSelector();
    }

    @VisibleForTesting
    LanguageSelectorConditionalStep(@NonNull ExecutionState executionState, @NonNull StepCommand stepCommand, @NonNull HandsFreeLocaleAuthority handsFreeLocaleAuthority) {
        super(executionState);
        this.mStepCommand = stepCommand;
        this.mHandsFreeLocaleAuthority = handsFreeLocaleAuthority;
    }
}
