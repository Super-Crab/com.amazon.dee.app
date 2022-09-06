package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.util.LogTag;
/* loaded from: classes9.dex */
public class RecordAccessoryConnectedDisconnectedMetric extends Command {
    private static final String TAG = LogTag.forClass(RecordAccessoryConnectedDisconnectedMetric.class);
    private final boolean mAccessoryConnected;

    public RecordAccessoryConnectedDisconnectedMetric(StateDependencies stateDependencies, boolean z) {
        super(stateDependencies);
        this.mAccessoryConnected = z;
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        if (getDependencies().getDriveModeMetrics() == null || getDependencies().getDriveModeMetrics().mo358get() == null) {
            Log.e(TAG, "drive mode metrics are null");
        }
        if (this.mAccessoryConnected) {
            getDependencies().getDriveModeMetrics().mo358get().logAccessoryConnected();
        } else {
            getDependencies().getDriveModeMetrics().mo358get().logAccessoryDisconnected();
        }
    }

    public boolean getAccessoryConnected() {
        return this.mAccessoryConnected;
    }
}
