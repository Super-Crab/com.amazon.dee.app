package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class IngressDefaultModeFTUECommand extends Command {
    private static final String TAG = "IngressDefaultModeFTUECommand";

    public IngressDefaultModeFTUECommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        getDependencies().getMainThreadHandler().post(new Runnable() { // from class: com.amazon.alexa.mode.statemachine.command.-$$Lambda$IngressDefaultModeFTUECommand$afYsLuZR0zZMh06o483R5zk0kNM
            @Override // java.lang.Runnable
            public final void run() {
                IngressDefaultModeFTUECommand.this.lambda$execute$0$IngressDefaultModeFTUECommand();
            }
        });
    }

    public /* synthetic */ void lambda$execute$0$IngressDefaultModeFTUECommand() {
        try {
            Log.i(TAG, "Route to default mode FTUE");
            getDependencies().getRoutingService().match(Constants.DEFAULT_MODE_FTUE).clearBackStack().navigate();
        } catch (IllegalArgumentException | NullPointerException e) {
            String str = TAG;
            Log.e(str, "error when navigating to default mode FTUE: " + e);
        }
    }
}
