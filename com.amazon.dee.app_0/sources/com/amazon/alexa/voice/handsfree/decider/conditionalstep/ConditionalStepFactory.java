package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.StepsProvider;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class ConditionalStepFactory {
    public List<ConditionalStep> getAISProfileSteps(@NonNull Context context) {
        return getSteps(StepsProvider.getInstance().getAISProfileSteps(context), context);
    }

    public List<ConditionalStep> getSteps(@NonNull Context context) {
        return getSteps(StepsProvider.getInstance().getSteps(context), context);
    }

    @VisibleForTesting
    List<ConditionalStep> getSteps(@NonNull List<StepType> list, @NonNull Context context) {
        ArrayList arrayList = new ArrayList();
        for (StepType stepType : list) {
            ConditionalStep step = stepType.getStep(context);
            step.mediateExecution();
            arrayList.add(step);
        }
        return arrayList;
    }
}
