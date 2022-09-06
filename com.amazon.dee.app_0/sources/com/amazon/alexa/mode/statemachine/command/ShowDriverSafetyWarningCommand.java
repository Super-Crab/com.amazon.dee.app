package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class ShowDriverSafetyWarningCommand extends Command {
    private static final String TAG = "ShowDriverSafetyWarningCommand";
    private final boolean mClearBackStack;
    private final boolean mShowIngressWarningScreen;

    public ShowDriverSafetyWarningCommand(StateDependencies stateDependencies, boolean z, boolean z2) {
        super(stateDependencies);
        this.mClearBackStack = z;
        this.mShowIngressWarningScreen = z2;
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        Log.i(TAG, "execute");
        getDependencies().getMainThreadHandler().post(new Runnable() { // from class: com.amazon.alexa.mode.statemachine.command.-$$Lambda$ShowDriverSafetyWarningCommand$g1tNxEtNorxcMKqAF9ipNowRsmo
            @Override // java.lang.Runnable
            public final void run() {
                ShowDriverSafetyWarningCommand.this.lambda$execute$0$ShowDriverSafetyWarningCommand();
            }
        });
    }

    public /* synthetic */ void lambda$execute$0$ShowDriverSafetyWarningCommand() {
        try {
            if (this.mClearBackStack) {
                getDependencies().getRoutingService().route("drive-mode/driver-interaction-warning").with("SHOW_INGRESS_WARNING_SCREEN", this.mShowIngressWarningScreen).clearBackStack().navigate();
            } else {
                getDependencies().getRoutingService().route("drive-mode/driver-interaction-warning").with("SHOW_INGRESS_WARNING_SCREEN", this.mShowIngressWarningScreen).addToBackStack().navigate();
            }
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "error when navigating to drive mode route");
        }
    }
}
