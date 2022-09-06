package com.amazon.alexa.mode.statemachine.command;

import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class RecordAutoIngressMetricCommand extends Command {
    private static final String TAG = "RecordAutoIngressMetricCommand";
    private final String mDriveModeType;

    public RecordAutoIngressMetricCommand(StateDependencies stateDependencies, String str) {
        super(stateDependencies);
        this.mDriveModeType = str;
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getDriveModeMetrics().mo358get().logSessionStartedWithIngressType(this.mDriveModeType, DriveModeMetrics.IngressType.AUTOINGRESS);
        getDependencies().getDriveModeMetrics().mo358get().logSessionStartedWithTimers(this.mDriveModeType);
    }

    public String getDriveModeType() {
        return this.mDriveModeType;
    }
}
