package com.amazon.alexa.mode.statemachine.command;

import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class RecordDefaultIngressMetricCommand extends Command {
    private static final String TAG = "RecordDefaultIngressMetricCommand";

    public RecordDefaultIngressMetricCommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getDriveModeMetrics().mo358get().logSessionStartedWithTimers("Default");
    }
}
