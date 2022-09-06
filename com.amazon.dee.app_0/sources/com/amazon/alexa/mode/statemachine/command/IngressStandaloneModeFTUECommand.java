package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class IngressStandaloneModeFTUECommand extends Command {
    private static final String TAG = "IngressStandaloneModeFTUECommand";

    public IngressStandaloneModeFTUECommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        Log.i(TAG, "execute");
        getDependencies().getMainThreadHandler().post(new Runnable() { // from class: com.amazon.alexa.mode.statemachine.command.-$$Lambda$IngressStandaloneModeFTUECommand$UtadEbvWFgCJMD9N5qk4NOnIMSI
            @Override // java.lang.Runnable
            public final void run() {
                IngressStandaloneModeFTUECommand.this.lambda$execute$0$IngressStandaloneModeFTUECommand();
            }
        });
    }

    public /* synthetic */ void lambda$execute$0$IngressStandaloneModeFTUECommand() {
        try {
            getDependencies().getRoutingService().match(Constants.STANDALONE_MODE_FTUE).with("headunit", getDependencies().getAutoBluetoothObserver().getHeadUnitType()).clearBackStack().navigate();
        } catch (IllegalArgumentException | NullPointerException e) {
            String str = TAG;
            Log.e(str, "error when navigating to standalone mode FTUE: " + e);
        }
    }
}
