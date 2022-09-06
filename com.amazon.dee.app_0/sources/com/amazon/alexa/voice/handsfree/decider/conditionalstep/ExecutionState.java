package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface ExecutionState {
    boolean areThereMorePendingStepsInFtue();

    void flushReportedStepsBuffer();

    void reportFtueMandatoryExecution(@NonNull ConditionalStep conditionalStep);
}
