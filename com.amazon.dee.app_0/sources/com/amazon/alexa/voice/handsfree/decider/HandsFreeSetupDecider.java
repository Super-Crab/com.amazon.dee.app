package com.amazon.alexa.voice.handsfree.decider;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.NoSuchElementException;
/* loaded from: classes11.dex */
public class HandsFreeSetupDecider {
    private static final String TAG = "HandsFreeSetupDecider";
    private final List<ConditionalStep> mConditionalSteps;
    private final HandsFreeUserIdentityProvider mHandsFreeUserProvider;

    public HandsFreeSetupDecider(@NonNull List<ConditionalStep> list, @NonNull Context context) {
        this(list, ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider());
    }

    public boolean canEnableWakeWord() {
        if (!isHandsFreeFeatureGateTurnedOn()) {
            return false;
        }
        for (ConditionalStep conditionalStep : this.mConditionalSteps) {
            if (conditionalStep.needsExecution() && conditionalStep.isRequiredForWakeWord()) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Step ");
                outline107.append(conditionalStep.getStepType().name());
                outline107.append(" required for wake word!");
                Log.d(str, outline107.toString());
                return false;
            }
        }
        return true;
    }

    @NonNull
    @VisibleForTesting
    List<ConditionalStep> getConditionalSteps() {
        return this.mConditionalSteps;
    }

    @Nullable
    public StepCommand getNextSetupStepCommand() {
        if (!isHandsFreeFeatureGateTurnedOn()) {
            return null;
        }
        for (int i = 0; i < this.mConditionalSteps.size(); i++) {
            if (this.mConditionalSteps.get(i).needsExecution()) {
                return this.mConditionalSteps.get(i).getStepCommand();
            }
        }
        return null;
    }

    @Nullable
    public StepCommand getNextSetupStepCommandByStepType(@NonNull StepType stepType) {
        int intValue;
        if (!isHandsFreeFeatureGateTurnedOn()) {
            return null;
        }
        try {
            intValue = getStepIndex(stepType).intValue();
        } catch (NoSuchElementException e) {
            Log.e(TAG, e.getMessage());
        }
        do {
            intValue++;
            if (intValue >= this.mConditionalSteps.size()) {
                return null;
            }
        } while (!this.mConditionalSteps.get(intValue).needsExecution());
        return this.mConditionalSteps.get(intValue).getStepCommand();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Integer getStepIndex(@NonNull StepType stepType) {
        for (int i = 0; i < this.mConditionalSteps.size(); i++) {
            if (this.mConditionalSteps.get(i).getStepType() == stepType) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    public boolean isHandsFreeFeatureGateTurnedOn() {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUserProvider.getHandsFreeUserIdentity();
        return handsFreeUserIdentity == null || handsFreeUserIdentity.hasComponent(HandsFreeComponent.HANDS_FREE_SETUP_FLOW);
    }

    public boolean isTherePendingStepCommand() {
        return getNextSetupStepCommand() != null;
    }

    @VisibleForTesting
    HandsFreeSetupDecider(@NonNull List<ConditionalStep> list, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        this.mConditionalSteps = list;
        this.mHandsFreeUserProvider = handsFreeUserIdentityProvider;
    }
}
