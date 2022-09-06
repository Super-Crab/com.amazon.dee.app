package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.AISProfileHandsFreeIntroStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
/* loaded from: classes11.dex */
public class AISProfileHandsFreeIntroConditionalStep extends ConditionalStep {
    private final Context mContext;
    private final StepCommand mStepCommand;

    public AISProfileHandsFreeIntroConditionalStep(@NonNull Context context) {
        this(context, new AISProfileHandsFreeIntroStepCommand(context), ExecutionStateMediator.getInstance());
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.AIS_PROFILE_HANDS_FREE_INTRO;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isLaunchedInFtue() {
        return false;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isRequiredForWakeWord() {
        return true;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean needsExecution() {
        return this.mContext.getSharedPreferences(SettingsSetupFlowContract.PREFERENCE_FILE_NAME, 0).getBoolean(SettingsSetupFlowContract.AIS_FLOW_ONGOING, true);
    }

    @VisibleForTesting
    AISProfileHandsFreeIntroConditionalStep(@NonNull Context context, @NonNull StepCommand stepCommand, @NonNull ExecutionState executionState) {
        super(executionState);
        this.mContext = context;
        this.mStepCommand = stepCommand;
    }
}
