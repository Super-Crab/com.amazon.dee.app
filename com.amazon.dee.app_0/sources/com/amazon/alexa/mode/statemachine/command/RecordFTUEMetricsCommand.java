package com.amazon.alexa.mode.statemachine.command;

import android.util.Log;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.util.DriveModeFTUEHelper;
import com.amazon.alexa.mode.util.LogTag;
/* loaded from: classes9.dex */
public class RecordFTUEMetricsCommand extends Command {
    public static final int FTUE_CANCELLED = 2;
    public static final int FTUE_COMPLETED = 1;
    public static final int FTUE_COMPLETED_STANDALONE = 3;
    public static final int FTUE_STARTED = 0;
    private static final String TAG = LogTag.forClass(RecordFTUEMetricsCommand.class);
    private final int mFtueEvent;
    private final DriveModeFTUEHelper.FTUEType mFtueType;

    public RecordFTUEMetricsCommand(StateDependencies stateDependencies, int i, DriveModeFTUEHelper.FTUEType fTUEType) {
        super(stateDependencies);
        this.mFtueEvent = i;
        this.mFtueType = fTUEType;
    }

    @Override // com.amazon.alexa.mode.statemachine.command.Command
    public void execute() {
        if (getDependencies().getDriveModeMetrics() == null || getDependencies().getDriveModeMetrics().mo358get() == null) {
            Log.e(TAG, "drive mode metrics are null");
        }
        DriveModeMetrics mo358get = getDependencies().getDriveModeMetrics().mo358get();
        int i = this.mFtueEvent;
        if (i == 0) {
            DriveModeFTUEHelper.FTUEType fTUEType = this.mFtueType;
            if (fTUEType == DriveModeFTUEHelper.FTUEType.AccessoryFTUE) {
                mo358get.logAccessoryFTUEStartedWithTimers();
            } else if (fTUEType == DriveModeFTUEHelper.FTUEType.AutoBluetoothFTUE) {
                mo358get.logAutoBluetoothFTUEStartedWithTimers();
            } else {
                mo358get.logDefaultFTUEStartedWithTimers();
            }
        } else if (i == 2) {
            DriveModeFTUEHelper.FTUEType fTUEType2 = this.mFtueType;
            if (fTUEType2 == DriveModeFTUEHelper.FTUEType.AccessoryFTUE) {
                mo358get.logAccessoryFTUECancelledWithTimers();
            } else if (fTUEType2 == DriveModeFTUEHelper.FTUEType.AutoBluetoothFTUE) {
                mo358get.logAutoBluetoothFTUECancelledWithTimers();
            } else {
                mo358get.logDefaultFTUECancelledWithTimers();
            }
        } else if (i == 1) {
            if (this.mFtueType == DriveModeFTUEHelper.FTUEType.AccessoryFTUE) {
                mo358get.logAccessoryFTUECompletedWithTimers();
            } else {
                mo358get.logDefaultFTUECompletedWithTimers();
            }
        } else if (i != 3) {
        } else {
            if (this.mFtueType == DriveModeFTUEHelper.FTUEType.AutoBluetoothFTUE) {
                mo358get.logAutoBluetoothFTUECompletedWithTimers();
            } else {
                mo358get.logDefaultFTUECompletedWithTimers();
            }
        }
    }

    public int getFTUEEvent() {
        return this.mFtueEvent;
    }

    public DriveModeFTUEHelper.FTUEType getFtueType() {
        return this.mFtueType;
    }
}
