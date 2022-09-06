package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ConnectionOperationStatusUpdate {
    private final DeviceConnectionConfiguration mDeviceConnectionConfiguration;
    private final State mState;

    /* loaded from: classes13.dex */
    public enum State {
        CONNECTING,
        CONNECTED_TO_DEVICE,
        CONNECTING_TO_DEVICE_FAILURE,
        SECURE_CHANNEL_ESTABLISHED,
        SECURE_CHANNEL_ESTABLISHMENT_FAILURE,
        OTHER_FAILURE
    }

    public ConnectionOperationStatusUpdate(DeviceConnectionConfiguration deviceConnectionConfiguration, State state) {
        this.mDeviceConnectionConfiguration = deviceConnectionConfiguration;
        this.mState = state;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ConnectionOperationStatusUpdate.class != obj.getClass()) {
            return false;
        }
        ConnectionOperationStatusUpdate connectionOperationStatusUpdate = (ConnectionOperationStatusUpdate) obj;
        return Objects.equal(this.mDeviceConnectionConfiguration, connectionOperationStatusUpdate.mDeviceConnectionConfiguration) && this.mState == connectionOperationStatusUpdate.mState;
    }

    public DeviceConnectionConfiguration getDeviceConnectionConfiguration() {
        return this.mDeviceConnectionConfiguration;
    }

    public State getState() {
        return this.mState;
    }

    public int hashCode() {
        return Objects.hashCode(this.mDeviceConnectionConfiguration, this.mState);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mDeviceConnectionConfiguration", this.mDeviceConnectionConfiguration).add("mState", this.mState).toString();
    }
}
