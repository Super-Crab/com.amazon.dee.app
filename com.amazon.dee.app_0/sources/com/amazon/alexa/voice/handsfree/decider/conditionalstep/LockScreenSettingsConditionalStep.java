package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.LockScreenSettingsStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
/* loaded from: classes11.dex */
public class LockScreenSettingsConditionalStep extends ConditionalStep {
    private static final String TAG = "LockScreenSettingsConditionalStep";
    private final Context mContext;
    private final StepCommand mStepCommand;

    public LockScreenSettingsConditionalStep(@NonNull Context context) {
        this(context, ExecutionStateMediator.getInstance(), new LockScreenSettingsStepCommand(context));
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.LOCK_SCREEN_SETTING;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isLaunchedInFtue() {
        return false;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isRequiredForWakeWord() {
        return false;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean needsExecution() {
        return this.mContext.getSharedPreferences(SettingsSetupFlowContract.PREFERENCE_FILE_NAME, 0).getBoolean(SettingsSetupFlowContract.AIS_FLOW_ONGOING, true);
    }

    @VisibleForTesting
    LockScreenSettingsConditionalStep(@NonNull Context context, @NonNull ExecutionState executionState, @NonNull StepCommand stepCommand) {
        super(executionState);
        this.mContext = context;
        this.mStepCommand = stepCommand;
    }
}
