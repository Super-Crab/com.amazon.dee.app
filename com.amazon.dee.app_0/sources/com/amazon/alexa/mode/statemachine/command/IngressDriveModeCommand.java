package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.statemachine.StateDependencies;
/* loaded from: classes9.dex */
public class IngressDriveModeCommand extends Command {
    private static final String TAG = "IngressDriveModeCommand";

    public IngressDriveModeCommand(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        Log.i(TAG, "execute");
        getDependencies().getMainThreadHandler().post(new Runnable() { // from class: com.amazon.alexa.mode.statemachine.command.-$$Lambda$IngressDriveModeCommand$3mOebpKRFsZRElEh9qGVFPGc-1o
            @Override // java.lang.Runnable
            public final void run() {
                IngressDriveModeCommand.this.lambda$execute$0$IngressDriveModeCommand();
            }
        });
    }

    public /* synthetic */ void lambda$execute$0$IngressDriveModeCommand() {
        try {
            getDependencies().getRoutingService().route("alexa-oobe/drive-mode/main").clearBackStack().navigate();
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "error when navigating to drive mode route");
        }
    }
}
