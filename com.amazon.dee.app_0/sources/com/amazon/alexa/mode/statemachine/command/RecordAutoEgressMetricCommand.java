package com.amazon.alexa.mode.statemachine.command;

import androidx.annotation.NonNull;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.routing.api.RouteContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class RecordAutoEgressMetricCommand extends Command {
    private static final String TAG = "RecordAutoEgressMetricCommand";
    private final String mDriveModeType;

    public RecordAutoEgressMetricCommand(@NonNull StateDependencies stateDependencies, @NonNull String str) {
        super(stateDependencies);
        this.mDriveModeType = str;
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("execute | recording auto egress metric | driveModeType : ");
        outline107.append(this.mDriveModeType);
        outline107.toString();
        getDependencies().getDriveModeMetrics().mo358get().logSessionEndedWithTimers(this.mDriveModeType);
        RouteContext currentRoute = getDependencies().getRoutingService().getCurrentRoute();
        if (currentRoute != null) {
            getDependencies().getDriveModeMetrics().mo358get().logSessionEnded(this.mDriveModeType, DriveModeMetrics.EgressType.DEVICEDISCONNECTIONEGRESS, currentRoute.getRoute().getName());
        }
    }

    public String getDriveModeType() {
        return this.mDriveModeType;
    }
}
