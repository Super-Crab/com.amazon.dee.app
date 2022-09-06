package com.amazon.deecomms.ndt.state;

import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.DeviceStatusModel;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeviceState.java */
/* loaded from: classes12.dex */
public class OnACallState extends DeviceState {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnACallState() {
        this.deviceNameColor = Utils.getColorFromResource(R.color.device_targeting_inactive_device);
        this.deviceStatusColor = Utils.getColorFromResource(R.color.device_targeting_inactive_device);
        this.deviceStatusText = Utils.getStringFromResource(R.string.device_targeting_on_a_call);
        this.canBeCalled = false;
    }

    @Override // com.amazon.deecomms.ndt.state.DeviceStateConditionsChecker
    public boolean checkMainCondition(@NonNull DeviceModel deviceModel) {
        return deviceModel.getDeviceStatus().isInCall();
    }

    @Override // com.amazon.deecomms.ndt.state.DeviceStateConditionsChecker
    public boolean checkPreConditions(@NonNull DeviceModel deviceModel) {
        DeviceStatusModel deviceStatus = deviceModel.getDeviceStatus();
        return !deviceStatus.isDnd() && !deviceStatus.getDeviceDropInAvailability().equals(DropInAvailability.OFF) && deviceStatus.isRegistered();
    }
}
