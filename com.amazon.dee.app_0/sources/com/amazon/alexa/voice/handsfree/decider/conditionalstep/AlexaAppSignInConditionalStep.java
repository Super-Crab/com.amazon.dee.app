package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.AlexaAppSignInStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
import dagger.Lazy;
/* loaded from: classes11.dex */
public class AlexaAppSignInConditionalStep extends ConditionalStep {
    private final Lazy<AlexaAppSignInContract> mAlexaAppSignInAuthority;
    private final Context mContext;
    private final StepCommand mStepCommand;

    public AlexaAppSignInConditionalStep(@NonNull Context context) {
        this(context, ExecutionStateMediator.getInstance(), new AlexaAppSignInStepCommand(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).alexaAppSignInContractLazy());
    }

    private boolean isAlexaAppSignedIn() {
        return this.mAlexaAppSignInAuthority.mo358get().getSignInState(this.mContext, false);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.ALEXA_APP_SIGN_IN;
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
        return !isAlexaAppSignedIn();
    }

    @VisibleForTesting
    AlexaAppSignInConditionalStep(@NonNull Context context, @NonNull ExecutionState executionState, @NonNull StepCommand stepCommand, @NonNull Lazy<AlexaAppSignInContract> lazy) {
        super(executionState);
        this.mContext = context;
        this.mStepCommand = stepCommand;
        this.mAlexaAppSignInAuthority = lazy;
    }
}
