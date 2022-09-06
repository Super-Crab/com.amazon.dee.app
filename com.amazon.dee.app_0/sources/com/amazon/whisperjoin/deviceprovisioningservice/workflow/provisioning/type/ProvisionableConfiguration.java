package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.Map;
/* loaded from: classes13.dex */
public class ProvisionableConfiguration {
    private final AvailableWifiNetworks mAvailableWifiNetworks;
    private final WifiConfiguration mChosenWifiConfiguration;
    private final Map<String, String> mCustomClientConfiguration;
    private final DeviceDetails mDeviceDetails;
    private final String mProvisionableReportUrl;
    private final RegistrationRequest mRegistrationRequest;
    private final boolean mSaveWifiNetworkToLocker;
    private final String mSessionToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionableConfiguration.class != obj.getClass()) {
            return false;
        }
        ProvisionableConfiguration provisionableConfiguration = (ProvisionableConfiguration) obj;
        return this.mSaveWifiNetworkToLocker == provisionableConfiguration.mSaveWifiNetworkToLocker && Objects.equal(this.mChosenWifiConfiguration, provisionableConfiguration.mChosenWifiConfiguration) && Objects.equal(this.mDeviceDetails, provisionableConfiguration.mDeviceDetails) && Objects.equal(this.mAvailableWifiNetworks, provisionableConfiguration.mAvailableWifiNetworks) && Objects.equal(this.mRegistrationRequest, provisionableConfiguration.mRegistrationRequest) && Objects.equal(this.mProvisionableReportUrl, provisionableConfiguration.mProvisionableReportUrl) && Objects.equal(this.mCustomClientConfiguration, provisionableConfiguration.mCustomClientConfiguration) && Objects.equal(this.mSessionToken, provisionableConfiguration.mSessionToken);
    }

    public AvailableWifiNetworks getAvailableWifiNetworks() {
        return this.mAvailableWifiNetworks;
    }

    public WifiConfiguration getChosenWifiConfiguration() {
        return this.mChosenWifiConfiguration;
    }

    public Map<String, String> getCustomClientConfiguration() {
        return this.mCustomClientConfiguration;
    }

    public DeviceDetails getDeviceDetails() {
        return this.mDeviceDetails;
    }

    public String getProvisionableReportUrl() {
        return this.mProvisionableReportUrl;
    }

    public RegistrationRequest getRegistrationRequest() {
        return this.mRegistrationRequest;
    }

    public String getSessionToken() {
        return this.mSessionToken;
    }

    public int hashCode() {
        return Objects.hashCode(this.mChosenWifiConfiguration, this.mDeviceDetails, this.mAvailableWifiNetworks, this.mRegistrationRequest, this.mProvisionableReportUrl, Boolean.valueOf(this.mSaveWifiNetworkToLocker), this.mCustomClientConfiguration, this.mSessionToken);
    }

    public boolean saveWifiNetworkToLocker() {
        return this.mSaveWifiNetworkToLocker;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mChosenWifiConfiguration", this.mChosenWifiConfiguration).add("mDeviceDetails", this.mDeviceDetails).add("mAvailableWifiNetworks", this.mAvailableWifiNetworks).add("mRegistrationRequest", this.mRegistrationRequest).add("mProvisionableReportUrl", this.mProvisionableReportUrl).add("mSaveWifiNetworkToLocker", this.mSaveWifiNetworkToLocker).add("mCustomClientConfiguration", this.mCustomClientConfiguration).add("mSessionToken", this.mSessionToken).toString();
    }

    private ProvisionableConfiguration(WifiConfiguration wifiConfiguration, DeviceDetails deviceDetails, boolean z, AvailableWifiNetworks availableWifiNetworks, RegistrationRequest registrationRequest, String str, Map<String, String> map, String str2) {
        this.mChosenWifiConfiguration = wifiConfiguration;
        this.mDeviceDetails = deviceDetails;
        this.mSaveWifiNetworkToLocker = z;
        this.mAvailableWifiNetworks = availableWifiNetworks;
        this.mRegistrationRequest = registrationRequest;
        this.mProvisionableReportUrl = str;
        this.mCustomClientConfiguration = map;
        this.mSessionToken = str2;
    }

    /* loaded from: classes13.dex */
    public static class Builder {
        private AvailableWifiNetworks mAvailableNetworks;
        private WifiConfiguration mChosenWifiConfiguration;
        private Map<String, String> mCustomClientConfiguration;
        private DeviceDetails mDeviceDetails;
        private String mProvisionableReportUrl;
        private RegistrationRequest mRegistrationRequest;
        private boolean mSaveWifiNetworkToLocker;
        private String mSessionToken;

        public Builder() {
            this.mChosenWifiConfiguration = null;
            this.mDeviceDetails = null;
            this.mAvailableNetworks = null;
            this.mRegistrationRequest = null;
            this.mProvisionableReportUrl = null;
            this.mSaveWifiNetworkToLocker = false;
            this.mCustomClientConfiguration = Collections.EMPTY_MAP;
            this.mSessionToken = null;
        }

        public ProvisionableConfiguration create() {
            AvailableWifiNetworks availableWifiNetworks = this.mAvailableNetworks;
            if (availableWifiNetworks != null) {
                Map<String, String> map = this.mCustomClientConfiguration;
                if (map != null) {
                    String str = this.mProvisionableReportUrl;
                    if (str != null) {
                        return new ProvisionableConfiguration(this.mChosenWifiConfiguration, this.mDeviceDetails, this.mSaveWifiNetworkToLocker, availableWifiNetworks, this.mRegistrationRequest, str, map, this.mSessionToken);
                    }
                    throw new IllegalArgumentException("mProvisionableReportUrl can not be null");
                }
                throw new IllegalArgumentException("mCustomClientConfiguration can not be null");
            }
            throw new IllegalArgumentException("availableWifiNetworks can not be null");
        }

        public Builder setAvailableNetworks(AvailableWifiNetworks availableWifiNetworks) {
            this.mAvailableNetworks = availableWifiNetworks;
            return this;
        }

        public Builder setChosenWifiConfiguration(WifiConfiguration wifiConfiguration) {
            this.mChosenWifiConfiguration = wifiConfiguration;
            return this;
        }

        public Builder setCustomClientConfiguration(Map<String, String> map) {
            this.mCustomClientConfiguration = map;
            return this;
        }

        public Builder setDeviceDetails(DeviceDetails deviceDetails) {
            this.mDeviceDetails = deviceDetails;
            return this;
        }

        public Builder setProvisionableReportUrl(String str) {
            this.mProvisionableReportUrl = str;
            return this;
        }

        public Builder setRegistrationRequest(RegistrationRequest registrationRequest) {
            this.mRegistrationRequest = registrationRequest;
            return this;
        }

        public Builder setSaveWifiNetworkToLocker(boolean z) {
            this.mSaveWifiNetworkToLocker = z;
            return this;
        }

        public Builder setSessionToken(String str) {
            this.mSessionToken = str;
            return this;
        }

        public Builder(ProvisionableConfiguration provisionableConfiguration) {
            this.mChosenWifiConfiguration = provisionableConfiguration.mChosenWifiConfiguration;
            this.mDeviceDetails = provisionableConfiguration.mDeviceDetails;
            this.mAvailableNetworks = provisionableConfiguration.mAvailableWifiNetworks;
            this.mRegistrationRequest = provisionableConfiguration.mRegistrationRequest;
            this.mProvisionableReportUrl = provisionableConfiguration.mProvisionableReportUrl;
            this.mSaveWifiNetworkToLocker = provisionableConfiguration.mSaveWifiNetworkToLocker;
            this.mCustomClientConfiguration = provisionableConfiguration.mCustomClientConfiguration;
            this.mSessionToken = provisionableConfiguration.mSessionToken;
        }
    }
}
