package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes11.dex */
public class ExecutionStateMediator implements ExecutionState {
    private static ExecutionStateMediator sInstance;
    private final Set<String> mMandatoryStepsReported = new HashSet();

    @VisibleForTesting
    ExecutionStateMediator() {
    }

    public static synchronized ExecutionStateMediator getInstance() {
        ExecutionStateMediator executionStateMediator;
        synchronized (ExecutionStateMediator.class) {
            if (sInstance == null) {
                sInstance = new ExecutionStateMediator();
            }
            executionStateMediator = sInstance;
        }
        return executionStateMediator;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ExecutionState
    public boolean areThereMorePendingStepsInFtue() {
        return !this.mMandatoryStepsReported.isEmpty();
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ExecutionState
    public void flushReportedStepsBuffer() {
        this.mMandatoryStepsReported.clear();
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ExecutionState
    public void reportFtueMandatoryExecution(@NonNull ConditionalStep conditionalStep) {
        this.mMandatoryStepsReported.add(conditionalStep.getClass().getSimpleName());
    }
}
