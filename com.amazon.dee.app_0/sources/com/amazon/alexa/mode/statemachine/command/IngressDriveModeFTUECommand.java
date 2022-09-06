package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class IngressDriveModeFTUECommand extends Command {
    private static final String TAG = "IngressDriveModeFTUECommand";

    public IngressDriveModeFTUECommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        Log.i(TAG, "execute");
        getDependencies().getMainThreadHandler().post(new Runnable() { // from class: com.amazon.alexa.mode.statemachine.command.-$$Lambda$IngressDriveModeFTUECommand$B-MMRQUlHxIlv84xt3iqxHnNTnM
            @Override // java.lang.Runnable
            public final void run() {
                IngressDriveModeFTUECommand.this.lambda$execute$0$IngressDriveModeFTUECommand();
            }
        });
    }

    public /* synthetic */ void lambda$execute$0$IngressDriveModeFTUECommand() {
        try {
            getDependencies().getRoutingService().route("v2/alexa-oobe/drivemode-ftue-startup").clearBackStack().navigate();
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "error when navigating to drive mode route");
        }
    }
}
