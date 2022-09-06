package com.amazon.deecomms.ndt.state;

import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.DropInDisableReason;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeviceState.java */
/* loaded from: classes12.dex */
public class NotInShowMode extends DeviceState {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NotInShowMode() {
        this.deviceNameColor = Utils.getColorFromResource(R.color.device_targeting_inactive_device);
        this.deviceStatusColor = Utils.getColorFromResource(R.color.device_targeting_inactive_device);
        this.deviceStatusText = Utils.getStringFromResource(R.string.device_targeting_Not_In_Show_Mode);
        this.canBeCalled = false;
    }

    @Override // com.amazon.deecomms.ndt.state.DeviceStateConditionsChecker
    public boolean checkMainCondition(@NonNull DeviceModel deviceModel) {
        return DropInDisableReason.NotInShowMode.getReason().equals(deviceModel.getDropInUnsupportedReason());
    }

    @Override // com.amazon.deecomms.ndt.state.DeviceStateConditionsChecker
    public boolean checkPreConditions(@NonNull DeviceModel deviceModel) {
        return sanityCheckConditions(deviceModel);
    }
}
