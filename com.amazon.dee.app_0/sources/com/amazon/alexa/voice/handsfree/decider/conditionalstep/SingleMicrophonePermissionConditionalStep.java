package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.SingleMicrophonePermissionStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
/* loaded from: classes11.dex */
public class SingleMicrophonePermissionConditionalStep extends ConditionalStep {
    private final Context mContext;
    private final StepCommand mStepCommand;

    public SingleMicrophonePermissionConditionalStep(@NonNull Context context) {
        this(context, ExecutionStateMediator.getInstance(), new SingleMicrophonePermissionStepCommand(context));
    }

    private boolean isPermissionGranted() {
        return ContextCompat.checkSelfPermission(this.mContext, "android.permission.RECORD_AUDIO") == 0;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.ALEXA_APP_AUDIO_PERMISSION;
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
        return !isPermissionGranted();
    }

    @VisibleForTesting
    SingleMicrophonePermissionConditionalStep(@NonNull Context context, @NonNull ExecutionState executionState, @NonNull StepCommand stepCommand) {
        super(executionState);
        this.mContext = context;
        this.mStepCommand = stepCommand;
    }
}
