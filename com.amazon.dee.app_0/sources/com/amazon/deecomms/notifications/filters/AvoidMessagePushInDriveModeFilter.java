package com.amazon.deecomms.notifications.filters;

import android.os.Bundle;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.processor.Task;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.amazon.deecomms.notifications.PushProcessStatus;
import com.amazon.deecomms.notifications.PushTypeHelper;
/* loaded from: classes12.dex */
public class AvoidMessagePushInDriveModeFilter implements Task<PushProcessStatus, Bundle> {
    private DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase;

    public AvoidMessagePushInDriveModeFilter(DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        this.driveModeSharedPreferencesUseCase = driveModeSharedPreferencesUseCase;
    }

    @Override // com.amazon.deecomms.common.processor.Task
    public PushProcessStatus execute(Bundle bundle) {
        if (PushTypeHelper.determineType(bundle.getString(Constants.AMP_KEY)) == PushTypeHelper.PushType.Message) {
            return this.driveModeSharedPreferencesUseCase.isInDriveMode() ? PushProcessStatus.IN_DRIVE_MODE : PushProcessStatus.CONTINUE;
        }
        return PushProcessStatus.CONTINUE;
    }
}
