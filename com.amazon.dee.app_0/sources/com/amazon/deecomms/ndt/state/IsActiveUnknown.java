package com.amazon.deecomms.ndt.state;

import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.ndt.enums.ActiveState;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.DeviceStatusModel;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeviceState.java */
/* loaded from: classes12.dex */
public class IsActiveUnknown extends DeviceState {
    /* JADX INFO: Access modifiers changed from: package-private */
    public IsActiveUnknown() {
        this.deviceNameColor = Utils.getColorFromResource(R.color.device_targeting_device_name);
        this.deviceStatusColor = Utils.getColorFromResource(R.color.device_targeting_active_device);
        this.deviceStatusText = "";
        this.canBeCalled = true;
    }

    @Override // com.amazon.deecomms.ndt.state.DeviceStateConditionsChecker
    public boolean checkMainCondition(@NonNull DeviceModel deviceModel) {
        DeviceStatusModel deviceStatus = deviceModel.getDeviceStatus();
        return deviceStatus.getActiveState() != null && deviceStatus.getActiveState().equals(ActiveState.UNKNOWN);
    }

    @Override // com.amazon.deecomms.ndt.state.DeviceStateConditionsChecker
    public boolean checkPreConditions(@NonNull DeviceModel deviceModel) {
        return sanityCheckConditions(deviceModel);
    }
}
