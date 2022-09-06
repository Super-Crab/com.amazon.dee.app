package com.amazon.deecomms.ndt.state;

import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.ndt.model.DeviceModel;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeviceState.java */
/* loaded from: classes12.dex */
public class NotAvailableForDropInState extends DeviceState {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NotAvailableForDropInState() {
        this.deviceNameColor = Utils.getColorFromResource(R.color.device_targeting_inactive_device);
        this.deviceStatusColor = Utils.getColorFromResource(R.color.device_targeting_inactive_device);
        this.deviceStatusText = "";
        this.canBeCalled = false;
    }

    @Override // com.amazon.deecomms.ndt.state.DeviceStateConditionsChecker
    public boolean checkMainCondition(@NonNull DeviceModel deviceModel) {
        return deviceModel.getDeviceStatus().getDeviceDropInAvailability().equals(DropInAvailability.OFF);
    }

    @Override // com.amazon.deecomms.ndt.state.DeviceStateConditionsChecker
    public boolean checkPreConditions(@NonNull DeviceModel deviceModel) {
        return true;
    }
}
