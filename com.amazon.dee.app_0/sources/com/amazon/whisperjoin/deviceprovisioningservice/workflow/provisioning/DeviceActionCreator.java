package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetProvisioningDetailsOptions;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.subjects.ReplaySubject;
/* loaded from: classes13.dex */
public class DeviceActionCreator {
    private static final String TAG = "DeviceActionCreator";
    private final ReplaySubject<Action> deviceActions = ReplaySubject.create();

    private void onNext(Action action) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sending Action ");
        outline107.append(action.toString());
        WJLog.d(str, outline107.toString());
        this.deviceActions.onNext(action);
    }

    public void blessDiscoveredDevice(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        onNext(new Action.BlessDiscoveredDevice(whisperJoinPeripheralDeviceDetails));
    }

    public void connectToDevice(WJProvisionee wJProvisionee, DeviceConnectionConfiguration deviceConnectionConfiguration) {
        onNext(new Action.ConnectToDevice(wJProvisionee, deviceConnectionConfiguration));
    }

    public void disconnect(WJProvisionee wJProvisionee) {
        onNext(new Action.Disconnect(wJProvisionee));
    }

    public ReplaySubject<Action> getDeviceActionStream() {
        return this.deviceActions;
    }

    public void getProvisioningDetails(WJProvisionee wJProvisionee, GetProvisioningDetailsOptions getProvisioningDetailsOptions) {
        onNext(new Action.GetProvisioningDetails(wJProvisionee, getProvisioningDetailsOptions));
    }

    public void provisionDevice(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
        onNext(new Action.ProvisionDevice(wJProvisionee, provisionableConfiguration));
    }

    public void startDiscovery() {
        onNext(new Action.StartDiscovery());
    }

    public void stopDiscovery() {
        onNext(new Action.StopDiscovery());
    }

    public void terminateSetup(WJWorkflowStateStore wJWorkflowStateStore) {
        onNext(new Action.TerminateSetup(wJWorkflowStateStore));
    }

    public void verifyProvisioning(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
        onNext(new Action.VerifyProvisioning(wJProvisionee, provisionableConfiguration));
    }
}
