package com.amazon.alexa.voice.handsfree.decider;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.settings.contract.SetupFlow;
import com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStepFactory;
/* loaded from: classes11.dex */
public class HandsFreeSetupDeciderProvider {
    private static HandsFreeSetupDeciderProvider sInstance;
    private final ConditionalStepFactory mConditionalStepFactory;
    private final StepsProvider mStepsProvider;

    @VisibleForTesting
    HandsFreeSetupDeciderProvider(@NonNull ConditionalStepFactory conditionalStepFactory, @NonNull StepsProvider stepsProvider) {
        this.mConditionalStepFactory = conditionalStepFactory;
        this.mStepsProvider = stepsProvider;
    }

    public static synchronized HandsFreeSetupDeciderProvider getInstance() {
        HandsFreeSetupDeciderProvider handsFreeSetupDeciderProvider;
        synchronized (HandsFreeSetupDeciderProvider.class) {
            if (sInstance == null) {
                sInstance = new HandsFreeSetupDeciderProvider(new ConditionalStepFactory(), StepsProvider.getInstance());
            }
            handsFreeSetupDeciderProvider = sInstance;
        }
        return handsFreeSetupDeciderProvider;
    }

    @NonNull
    public HandsFreeSetupDecider getHandsFreeSetupDecider(@NonNull Context context) {
        return new HandsFreeSetupDecider(this.mConditionalStepFactory.getSteps(context), context);
    }

    @NonNull
    public HandsFreeSetupDecider getHandsFreeSetupDecider(@NonNull Context context, @Nullable SetupFlow setupFlow) {
        if (setupFlow != null && setupFlow.isAIS()) {
            return new HandsFreeSetupDecider(this.mConditionalStepFactory.getAISProfileSteps(context), context);
        }
        if (SetupFlow.DEFAULT.equals(setupFlow)) {
            this.mStepsProvider.updateSetupFlow(setupFlow);
        }
        return new HandsFreeSetupDecider(this.mConditionalStepFactory.getSteps(context), context);
    }
}
