package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.ProfileSelectionStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class ProfileSelectionConditionalStep extends ConditionalStep {
    private static final String TAG = "ProfileSelectionStep";
    private final HandsFreeUserIdentityProvider mHandsFreeUserProvider;
    private final IdentityService mIdentityService;
    private final StepCommand mStepCommand;

    public ProfileSelectionConditionalStep(Context context) {
        this(ExecutionStateMediator.getInstance(), new ProfileSelectionStepCommand(context), (IdentityService) GeneratedOutlineSupport1.outline20(IdentityService.class), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider());
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.PROFILE_SELECTION;
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
        UserIdentity user;
        return this.mHandsFreeUserProvider.getHandsFreeUserIdentity().hasComponent(HandsFreeComponent.PROFILE_SELECTION) && (user = this.mIdentityService.getUser(TAG)) != null && user.getUserProfile() == null;
    }

    @VisibleForTesting
    ProfileSelectionConditionalStep(@NonNull ExecutionStateMediator executionStateMediator, @NonNull StepCommand stepCommand, @NonNull IdentityService identityService, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        super(executionStateMediator);
        this.mStepCommand = stepCommand;
        this.mIdentityService = identityService;
        this.mHandsFreeUserProvider = handsFreeUserIdentityProvider;
    }
}
