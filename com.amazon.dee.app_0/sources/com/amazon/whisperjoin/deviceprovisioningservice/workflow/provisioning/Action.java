package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetProvisioningDetailsOptions;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public abstract class Action<T> {
    private T mData;
    private WJProvisionee mWJProvisionee;

    /* loaded from: classes13.dex */
    public static final class BlessDiscoveredDevice extends Action<WhisperJoinPeripheralDeviceDetails> {
        public BlessDiscoveredDevice(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
            super(whisperJoinPeripheralDeviceDetails);
        }
    }

    /* loaded from: classes13.dex */
    public static final class ConnectToDevice extends Action<DeviceConnectionConfiguration> {
        public ConnectToDevice(WJProvisionee wJProvisionee, DeviceConnectionConfiguration deviceConnectionConfiguration) {
            super(wJProvisionee, deviceConnectionConfiguration);
        }
    }

    /* loaded from: classes13.dex */
    public static final class Disconnect extends Action {
        public Disconnect(WJProvisionee wJProvisionee) {
            super(wJProvisionee);
        }
    }

    /* loaded from: classes13.dex */
    public static final class GetProvisioningDetails extends Action<GetProvisioningDetailsOptions> {
        public GetProvisioningDetails(WJProvisionee wJProvisionee, GetProvisioningDetailsOptions getProvisioningDetailsOptions) {
            super(wJProvisionee, getProvisioningDetailsOptions);
        }
    }

    /* loaded from: classes13.dex */
    public static final class ProvisionDevice extends Action<ProvisionableConfiguration> {
        public ProvisionDevice(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
            super(wJProvisionee, provisionableConfiguration);
        }
    }

    /* loaded from: classes13.dex */
    public static final class StartDiscovery extends Action {
        public StartDiscovery() {
            super();
        }
    }

    /* loaded from: classes13.dex */
    public static final class StopDiscovery extends Action {
        public StopDiscovery() {
            super();
        }
    }

    /* loaded from: classes13.dex */
    public static final class TerminateSetup extends Action<WJWorkflowStateStore> {
        public TerminateSetup(WJWorkflowStateStore wJWorkflowStateStore) {
            super(null, wJWorkflowStateStore);
        }
    }

    /* loaded from: classes13.dex */
    public static final class VerifyProvisioning extends Action<ProvisionableConfiguration> {
        public VerifyProvisioning(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
            super(wJProvisionee, provisionableConfiguration);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Action action = (Action) obj;
        return new EqualsBuilder().append(this.mWJProvisionee, action.mWJProvisionee).append(this.mData, action.mData).isEquals();
    }

    public T getData() {
        return this.mData;
    }

    public WJProvisionee getWJProvisionee() {
        return this.mWJProvisionee;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mWJProvisionee).append(this.mData).toHashCode();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("Action Type", getClass().getSimpleName()).add("mWJProvisionee", this.mWJProvisionee).add("mData", this.mData).toString();
    }

    private Action() {
        this((WJProvisionee) null, (Object) null);
    }

    private Action(T t) {
        this((WJProvisionee) null, t);
    }

    private Action(WJProvisionee wJProvisionee) {
        this(wJProvisionee, (Object) null);
    }

    private Action(WJProvisionee wJProvisionee, T t) {
        this.mWJProvisionee = null;
        this.mData = null;
        this.mWJProvisionee = wJProvisionee;
        this.mData = t;
    }
}
