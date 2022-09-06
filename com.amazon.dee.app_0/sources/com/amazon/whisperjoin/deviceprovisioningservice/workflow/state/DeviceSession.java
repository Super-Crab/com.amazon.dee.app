package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state;

import androidx.annotation.NonNull;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.DiscoveredProvisionable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningDetails;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class DeviceSession {
    private final WifiConfiguration mChosenWifiConfiguration;
    private final boolean mConnected;
    private final DeviceConnectionConfiguration mDeviceConnectionConfiguration;
    private final String mDeviceIdentity;
    private final String mDiscoverySessionToken;
    private final SetupError mLastSetupError;
    private final ProvisionableWifiNetworkConnectionError mLastWifiConnectionError;
    private final String mProductIndex;
    private final String mProvisionableReportUrl;
    private final String mProvisionerReportUrl;
    private final ProvisioningDetails mProvisioningDetails;
    private final DeviceState mState;

    /* loaded from: classes13.dex */
    public enum DeviceState {
        WJ_DEVICE_DISCOVERED,
        CONNECTING,
        CONNECTED,
        SECURE_CHANNEL_ESTABLISHED,
        GETTING_PROVISIONING_DETAILS,
        PROVISIONING_DETAILS_RECEIVED,
        VALIDATING_WIFI_SYNC_AUTH_TOKEN,
        VALIDATED_WIFI_SYNC_AUTH_TOKEN,
        PROVISIONING,
        PROVISIONED,
        VERIFYING_PROVISIONING,
        VERIFIED_PROVISIONING,
        CONNECTING_TO_INTERNET,
        CONNECTED_TO_INTERNET,
        REGISTERING,
        REGISTERED,
        SETUP_SUCCESS,
        SETUP_FAILURE
    }

    /* loaded from: classes13.dex */
    public static class Mutator {
        private WifiConfiguration mChosenWifiConfiguration;
        private boolean mConnected;
        private DeviceConnectionConfiguration mDeviceConnectionConfiguration;
        private final String mDeviceIdentity;
        private final String mDiscoverySessionToken;
        private SetupError mLastSetupError;
        private ProvisionableWifiNetworkConnectionError mLastWifiConnectionError;
        private final String mProductIndex;
        private final String mProvisionableReportUrl;
        private final String mProvisionerReportUrl;
        private ProvisioningDetails mProvisioningDetails;
        private DeviceState mState;

        public Mutator(DeviceSession deviceSession) {
            this.mDeviceIdentity = deviceSession.mDeviceIdentity;
            this.mProductIndex = deviceSession.mProductIndex;
            this.mProvisionerReportUrl = deviceSession.mProvisionerReportUrl;
            this.mProvisionableReportUrl = deviceSession.mProvisionableReportUrl;
            this.mState = deviceSession.mState;
            this.mConnected = deviceSession.mConnected;
            this.mDeviceConnectionConfiguration = deviceSession.mDeviceConnectionConfiguration;
            this.mProvisioningDetails = deviceSession.mProvisioningDetails;
            this.mChosenWifiConfiguration = deviceSession.mChosenWifiConfiguration;
            this.mLastWifiConnectionError = deviceSession.mLastWifiConnectionError;
            this.mLastSetupError = deviceSession.mLastSetupError;
            this.mDiscoverySessionToken = deviceSession.mDiscoverySessionToken;
        }

        public DeviceSession create() {
            return new DeviceSession(this.mDeviceIdentity, this.mProductIndex, this.mState, this.mProvisionerReportUrl, this.mProvisionableReportUrl, this.mConnected, this.mDeviceConnectionConfiguration, this.mProvisioningDetails, this.mChosenWifiConfiguration, this.mLastWifiConnectionError, this.mLastSetupError, this.mDiscoverySessionToken);
        }

        public Mutator setChosenWifiConfiguration(WifiConfiguration wifiConfiguration) {
            this.mChosenWifiConfiguration = wifiConfiguration;
            return this;
        }

        public Mutator setConnected(boolean z) {
            this.mConnected = z;
            return this;
        }

        public Mutator setDeviceConnectionConfiguration(DeviceConnectionConfiguration deviceConnectionConfiguration) {
            this.mDeviceConnectionConfiguration = deviceConnectionConfiguration;
            return this;
        }

        public Mutator setLastSetupError(SetupError setupError) {
            this.mLastSetupError = setupError;
            return this;
        }

        public Mutator setLastWifiConnectionError(ProvisionableWifiNetworkConnectionError provisionableWifiNetworkConnectionError) {
            this.mLastWifiConnectionError = provisionableWifiNetworkConnectionError;
            return this;
        }

        public Mutator setProvisioningDetails(ProvisioningDetails provisioningDetails) {
            this.mProvisioningDetails = provisioningDetails;
            return this;
        }

        public Mutator setState(DeviceState deviceState) {
            this.mState = deviceState;
            return this;
        }
    }

    /* loaded from: classes13.dex */
    public static class SetupError {
        private Throwable mCause;
        private DeviceState mState;

        public SetupError(Throwable th, DeviceState deviceState) {
            this.mCause = th;
            this.mState = deviceState;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || SetupError.class != obj.getClass()) {
                return false;
            }
            SetupError setupError = (SetupError) obj;
            return new EqualsBuilder().append(this.mCause, setupError.mCause).append(this.mState, setupError.mState).isEquals();
        }

        public Throwable getCause() {
            return this.mCause;
        }

        public DeviceState getState() {
            return this.mState;
        }

        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.mCause).append(this.mState).toHashCode();
        }
    }

    public WifiConfiguration getChosenWifiConfiguration() {
        return this.mChosenWifiConfiguration;
    }

    public DeviceConnectionConfiguration getDeviceConnectionConfiguration() {
        return this.mDeviceConnectionConfiguration;
    }

    public DeviceDetails getDeviceDetails() {
        if (getProvisioningDetails() != null) {
            return getProvisioningDetails().getDeviceDetails();
        }
        return null;
    }

    public String getDeviceIdentity() {
        return this.mDeviceIdentity;
    }

    public String getDiscoverySessionToken() {
        return this.mDiscoverySessionToken;
    }

    public SetupError getLastSetupError() {
        return this.mLastSetupError;
    }

    public ProvisionableWifiNetworkConnectionError getLastWifiConnectionError() {
        return this.mLastWifiConnectionError;
    }

    public String getProductIndex() {
        return this.mProductIndex;
    }

    public String getProvisionableReportUrl() {
        return this.mProvisionableReportUrl;
    }

    public String getProvisionerReportUrl() {
        return this.mProvisionerReportUrl;
    }

    public ProvisioningDetails getProvisioningDetails() {
        return this.mProvisioningDetails;
    }

    public DeviceState getState() {
        return this.mState;
    }

    public boolean isConnected() {
        return this.mConnected;
    }

    public boolean isState(DeviceState deviceState) {
        if (deviceState == null) {
            return false;
        }
        return deviceState.equals(this.mState);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mDeviceIdentity", this.mDeviceIdentity).add("mProductIndex", this.mProductIndex).add("mProvisionerReportUrl", this.mProvisionerReportUrl).add("mProvisionableReportUrl", this.mProvisionableReportUrl).add("mState", this.mState).add("mConnected", this.mConnected).add("mDeviceConnectionConfiguration", this.mDeviceConnectionConfiguration).add("mProvisioningDetails", this.mProvisioningDetails).add("mChosenWifiConfiguration", this.mChosenWifiConfiguration).add("mLastWifiConnectionError", this.mLastWifiConnectionError).add("mLastSetupError", this.mLastSetupError).add("mDiscoverySessionToken", this.mDiscoverySessionToken).toString();
    }

    public DeviceSession(DiscoveredProvisionable discoveredProvisionable) {
        this(discoveredProvisionable.getWJProvisionee().getPeripheralDeviceDetails().getDeviceIdentity(), discoveredProvisionable.getWJProvisionee().getPeripheralDeviceDetails().getProductIndex(), DeviceState.WJ_DEVICE_DISCOVERED, discoveredProvisionable.getProvisionerReportingUrl(), discoveredProvisionable.getProvisionableReportingUrl(), false, null, null, null, null, null, discoveredProvisionable.getDiscoverySessionToken());
    }

    private DeviceSession(@NonNull String str, @NonNull String str2, @NonNull DeviceState deviceState, String str3, String str4, boolean z, DeviceConnectionConfiguration deviceConnectionConfiguration, ProvisioningDetails provisioningDetails, WifiConfiguration wifiConfiguration, ProvisionableWifiNetworkConnectionError provisionableWifiNetworkConnectionError, SetupError setupError, String str5) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("Product Index can not be null");
            }
            if (deviceState != null) {
                this.mDeviceIdentity = str;
                this.mProductIndex = str2;
                this.mProvisionerReportUrl = str3;
                this.mProvisionableReportUrl = str4;
                this.mState = deviceState;
                this.mConnected = z;
                this.mDeviceConnectionConfiguration = deviceConnectionConfiguration;
                this.mProvisioningDetails = provisioningDetails;
                this.mChosenWifiConfiguration = wifiConfiguration;
                this.mLastWifiConnectionError = provisionableWifiNetworkConnectionError;
                this.mLastSetupError = setupError;
                this.mDiscoverySessionToken = str5;
                return;
            }
            throw new IllegalArgumentException("Device State can not be null");
        }
        throw new IllegalArgumentException("Device Identity can not be null");
    }
}
