package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.util.LogTag;
/* loaded from: classes9.dex */
public class RecordBackgroundForegroundMetricsCommand extends Command {
    private static final String TAG = LogTag.forClass(RecordBackgroundForegroundMetricsCommand.class);
    private final boolean isAppInBackground;

    public RecordBackgroundForegroundMetricsCommand(StateDependencies stateDependencies, boolean z) {
        super(stateDependencies);
        this.isAppInBackground = z;
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        if (getDependencies().getDriveModeMetrics() == null || getDependencies().getDriveModeMetrics().mo358get() == null) {
            Log.e(TAG, "drive mode metrics are null");
        }
        if (this.isAppInBackground) {
            getDependencies().getDriveModeMetrics().mo358get().logBackgrounded();
        } else {
            getDependencies().getDriveModeMetrics().mo358get().logForegrounded();
        }
    }
}
