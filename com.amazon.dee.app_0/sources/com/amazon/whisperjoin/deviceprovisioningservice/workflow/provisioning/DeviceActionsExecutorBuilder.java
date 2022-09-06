package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.DeviceOperation;
/* loaded from: classes13.dex */
public class DeviceActionsExecutorBuilder {
    private DeviceOperation<Action.BlessDiscoveredDevice> mBlessDiscoveredDeviceDeviceOperation;
    private DeviceOperation<Action.ConnectToDevice> mConnectToDeviceDeviceOperation;
    private DeviceOperation<Action.Disconnect> mDisconnectFromDeviceOperation;
    private DeviceOperation<Action.GetProvisioningDetails> mGetProvisioningDetailsDeviceOperation;
    private DeviceOperation<Action.ProvisionDevice> mProvisionDeviceOperation;
    private DeviceOperation<Action.StartDiscovery> mStartDiscoveryOperation;
    private DeviceOperation<Action.StopDiscovery> mStopDiscoveryOperation;
    private DeviceOperation<Action.TerminateSetup> mTerminateSetupDeviceOperation;
    private DeviceOperation<Action.VerifyProvisioning> mVerifyProvisioningOperation;

    public DeviceActionsExecutor createDeviceActionsExecutor() {
        DeviceOperation<Action.StartDiscovery> deviceOperation = this.mStartDiscoveryOperation;
        if (deviceOperation != null) {
            DeviceOperation<Action.StopDiscovery> deviceOperation2 = this.mStopDiscoveryOperation;
            if (deviceOperation2 != null) {
                DeviceOperation<Action.BlessDiscoveredDevice> deviceOperation3 = this.mBlessDiscoveredDeviceDeviceOperation;
                if (deviceOperation3 != null) {
                    DeviceOperation<Action.ConnectToDevice> deviceOperation4 = this.mConnectToDeviceDeviceOperation;
                    if (deviceOperation4 != null) {
                        DeviceOperation<Action.Disconnect> deviceOperation5 = this.mDisconnectFromDeviceOperation;
                        if (deviceOperation5 != null) {
                            DeviceOperation<Action.GetProvisioningDetails> deviceOperation6 = this.mGetProvisioningDetailsDeviceOperation;
                            if (deviceOperation6 != null) {
                                DeviceOperation<Action.ProvisionDevice> deviceOperation7 = this.mProvisionDeviceOperation;
                                if (deviceOperation7 != null) {
                                    DeviceOperation<Action.VerifyProvisioning> deviceOperation8 = this.mVerifyProvisioningOperation;
                                    if (deviceOperation8 != null) {
                                        DeviceOperation<Action.TerminateSetup> deviceOperation9 = this.mTerminateSetupDeviceOperation;
                                        if (deviceOperation9 != null) {
                                            return new DeviceActionsExecutor(deviceOperation, deviceOperation2, deviceOperation3, deviceOperation4, deviceOperation6, deviceOperation7, deviceOperation8, deviceOperation9, deviceOperation5);
                                        }
                                        throw new IllegalArgumentException("mTerminateSetupDeviceOperation can't be null");
                                    }
                                    throw new IllegalArgumentException("mVerifyProvisioningOperation can't be null");
                                }
                                throw new IllegalArgumentException("mProvisionDeviceOperation can't be null");
                            }
                            throw new IllegalArgumentException("mGetProvisioningDetailsDeviceOperation can't be null");
                        }
                        throw new IllegalArgumentException("mDisconnectFromDeviceOperation can't be null");
                    }
                    throw new IllegalArgumentException("mConnectToDeviceDeviceOperation can't be null");
                }
                throw new IllegalArgumentException("mBlessDiscoveredDeviceOperation can't be null");
            }
            throw new IllegalArgumentException("mStopDiscoveryOperation can't be null");
        }
        throw new IllegalArgumentException("mStartDiscoveryOperation can't be null");
    }

    public DeviceActionsExecutorBuilder setBlessDiscoveredDeviceOperation(DeviceOperation<Action.BlessDiscoveredDevice> deviceOperation) {
        this.mBlessDiscoveredDeviceDeviceOperation = deviceOperation;
        return this;
    }

    public DeviceActionsExecutorBuilder setConnectToDeviceDeviceOperation(DeviceOperation<Action.ConnectToDevice> deviceOperation) {
        this.mConnectToDeviceDeviceOperation = deviceOperation;
        return this;
    }

    public DeviceActionsExecutorBuilder setDisconnectFromDeviceOperation(DeviceOperation<Action.Disconnect> deviceOperation) {
        this.mDisconnectFromDeviceOperation = deviceOperation;
        return this;
    }

    public DeviceActionsExecutorBuilder setGetProvisioningDetailsOperation(DeviceOperation<Action.GetProvisioningDetails> deviceOperation) {
        this.mGetProvisioningDetailsDeviceOperation = deviceOperation;
        return this;
    }

    public DeviceActionsExecutorBuilder setProvisionDeviceOperation(DeviceOperation<Action.ProvisionDevice> deviceOperation) {
        this.mProvisionDeviceOperation = deviceOperation;
        return this;
    }

    public DeviceActionsExecutorBuilder setStartDiscoveryOperation(DeviceOperation<Action.StartDiscovery> deviceOperation) {
        this.mStartDiscoveryOperation = deviceOperation;
        return this;
    }

    public DeviceActionsExecutorBuilder setStopDiscoveryOperation(DeviceOperation<Action.StopDiscovery> deviceOperation) {
        this.mStopDiscoveryOperation = deviceOperation;
        return this;
    }

    public DeviceActionsExecutorBuilder setTerminateSetupDeviceOperation(DeviceOperation<Action.TerminateSetup> deviceOperation) {
        this.mTerminateSetupDeviceOperation = deviceOperation;
        return this;
    }

    public DeviceActionsExecutorBuilder setVerifyProvisioningOperation(DeviceOperation<Action.VerifyProvisioning> deviceOperation) {
        this.mVerifyProvisioningOperation = deviceOperation;
        return this;
    }
}
