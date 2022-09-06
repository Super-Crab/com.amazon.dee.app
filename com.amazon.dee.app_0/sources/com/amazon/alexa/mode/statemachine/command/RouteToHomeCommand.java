package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.util.LogTag;
import com.amazon.alexa.routing.data.RouteName;
/* loaded from: classes9.dex */
public class RouteToHomeCommand extends Command {
    private static final String TAG = LogTag.forClass(RouteToHomeCommand.class);

    public RouteToHomeCommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        Log.i(TAG, "execute");
        getDependencies().getRoutingService().route(RouteName.HOME).clearBackStack().navigate();
    }
}
