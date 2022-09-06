package com.amazon.deecomms.ndt.state;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.DropInDisableReason;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public final class DeviceStateManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceStateManager.class);
    private List<DeviceState> listOfDeviceStates = new ArrayList();
    private Map<DropInDisableReason, DeviceState> deviceStateMap = new HashMap();

    public DeviceStateManager() {
        DoNotDisturbState doNotDisturbState = new DoNotDisturbState();
        this.listOfDeviceStates.add(doNotDisturbState);
        this.deviceStateMap.put(DropInDisableReason.DND, doNotDisturbState);
        OfflineState offlineState = new OfflineState();
        this.listOfDeviceStates.add(offlineState);
        this.deviceStateMap.put(DropInDisableReason.Offline, offlineState);
        OnACallState onACallState = new OnACallState();
        this.listOfDeviceStates.add(onACallState);
        this.deviceStateMap.put(DropInDisableReason.InCall, onACallState);
        NotAvailableForDropInState notAvailableForDropInState = new NotAvailableForDropInState();
        this.listOfDeviceStates.add(notAvailableForDropInState);
        this.deviceStateMap.put(DropInDisableReason.DropInSettingOff, notAvailableForDropInState);
        NotRegistered notRegistered = new NotRegistered();
        this.listOfDeviceStates.add(notRegistered);
        this.deviceStateMap.put(DropInDisableReason.DeviceNotRegistered, notRegistered);
        this.listOfDeviceStates.add(new IsActive());
        this.listOfDeviceStates.add(new IsActiveUnknown());
        this.listOfDeviceStates.add(new IsInactive());
        this.deviceStateMap.put(DropInDisableReason.AuxConnected, new AuxSpeakerNotSupported());
        this.deviceStateMap.put(DropInDisableReason.BluetoothNotConnected, new SpeakerNotSupported());
        NotInShowMode notInShowMode = new NotInShowMode();
        this.listOfDeviceStates.add(notInShowMode);
        this.deviceStateMap.put(DropInDisableReason.NotInShowMode, notInShowMode);
        IsUnavailable isUnavailable = new IsUnavailable();
        this.listOfDeviceStates.add(isUnavailable);
        this.deviceStateMap.put(DropInDisableReason.Unavailable, isUnavailable);
        UnsupportedUnknown unsupportedUnknown = new UnsupportedUnknown();
        this.listOfDeviceStates.add(unsupportedUnknown);
        this.deviceStateMap.put(DropInDisableReason.Unknown, unsupportedUnknown);
        DropInUnavailable dropInUnavailable = new DropInUnavailable();
        this.listOfDeviceStates.add(dropInUnavailable);
        this.deviceStateMap.put(DropInDisableReason.DropInUnavailable, dropInUnavailable);
    }

    private DeviceState getDeviceStateBasedOnDropinDisableReason(String str) {
        return this.deviceStateMap.get(DropInDisableReason.lookup(str));
    }

    public DeviceState getStateForCurrentDevice(DeviceModel deviceModel) {
        String dropInUnsupportedReason = deviceModel.getDropInUnsupportedReason();
        if (dropInUnsupportedReason != null) {
            return getDeviceStateBasedOnDropinDisableReason(dropInUnsupportedReason);
        }
        for (DeviceState deviceState : this.listOfDeviceStates) {
            if (deviceState.isInThisState(deviceModel)) {
                return deviceState;
            }
        }
        LOG.i("Setting default state of isActiveUnkown ");
        return new IsActiveUnknown();
    }
}
