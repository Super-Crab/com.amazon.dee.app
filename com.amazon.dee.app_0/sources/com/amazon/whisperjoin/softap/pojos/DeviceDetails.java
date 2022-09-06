package com.amazon.whisperjoin.softap.pojos;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes13.dex */
public class DeviceDetails {
    @SerializedName("amzn_macid")
    private String deviceMacAddress;
    @SerializedName("amzn_devid")
    private String deviceSerialNumber;
    @SerializedName("amzn_error")
    private int errorCode;
    @SerializedName("amzn_networks")
    private List<Network> networks;
    @SerializedName("amzn_nonce")
    private int nonce;
    @SerializedName("schemes")
    private List<Integer> schemes;

    /* loaded from: classes13.dex */
    public static class DeviceDetailsBuilder {
        private String deviceMacAddress;
        private String deviceSerialNumber;
        private int errorCode;
        private List<Network> networks;
        private int nonce;
        private List<Integer> schemes;

        DeviceDetailsBuilder() {
        }

        public DeviceDetails build() {
            return new DeviceDetails(this.networks, this.deviceSerialNumber, this.deviceMacAddress, this.nonce, this.errorCode, this.schemes);
        }

        public DeviceDetailsBuilder deviceMacAddress(String str) {
            this.deviceMacAddress = str;
            return this;
        }

        public DeviceDetailsBuilder deviceSerialNumber(String str) {
            this.deviceSerialNumber = str;
            return this;
        }

        public DeviceDetailsBuilder errorCode(int i) {
            this.errorCode = i;
            return this;
        }

        public DeviceDetailsBuilder networks(List<Network> list) {
            this.networks = list;
            return this;
        }

        public DeviceDetailsBuilder nonce(int i) {
            this.nonce = i;
            return this;
        }

        public DeviceDetailsBuilder schemes(List<Integer> list) {
            this.schemes = list;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceDetails.DeviceDetailsBuilder(networks=");
            outline107.append(this.networks);
            outline107.append(", deviceSerialNumber=");
            outline107.append(this.deviceSerialNumber);
            outline107.append(", deviceMacAddress=");
            outline107.append(this.deviceMacAddress);
            outline107.append(", nonce=");
            outline107.append(this.nonce);
            outline107.append(", errorCode=");
            outline107.append(this.errorCode);
            outline107.append(", schemes=");
            return GeneratedOutlineSupport1.outline95(outline107, this.schemes, ")");
        }
    }

    DeviceDetails(List<Network> list, String str, String str2, int i, int i2, List<Integer> list2) {
        this.networks = list;
        this.deviceSerialNumber = str;
        this.deviceMacAddress = str2;
        this.nonce = i;
        this.errorCode = i2;
        this.schemes = list2;
    }

    public static DeviceDetailsBuilder builder() {
        return new DeviceDetailsBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DeviceDetails;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceDetails)) {
            return false;
        }
        DeviceDetails deviceDetails = (DeviceDetails) obj;
        if (!deviceDetails.canEqual(this)) {
            return false;
        }
        List<Network> networks = getNetworks();
        List<Network> networks2 = deviceDetails.getNetworks();
        if (networks != null ? !networks.equals(networks2) : networks2 != null) {
            return false;
        }
        String deviceSerialNumber = getDeviceSerialNumber();
        String deviceSerialNumber2 = deviceDetails.getDeviceSerialNumber();
        if (deviceSerialNumber != null ? !deviceSerialNumber.equals(deviceSerialNumber2) : deviceSerialNumber2 != null) {
            return false;
        }
        String deviceMacAddress = getDeviceMacAddress();
        String deviceMacAddress2 = deviceDetails.getDeviceMacAddress();
        if (deviceMacAddress != null ? !deviceMacAddress.equals(deviceMacAddress2) : deviceMacAddress2 != null) {
            return false;
        }
        if (getNonce() != deviceDetails.getNonce() || getErrorCode() != deviceDetails.getErrorCode()) {
            return false;
        }
        List<Integer> schemes = getSchemes();
        List<Integer> schemes2 = deviceDetails.getSchemes();
        return schemes != null ? schemes.equals(schemes2) : schemes2 == null;
    }

    public String getDeviceMacAddress() {
        return this.deviceMacAddress;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public List<Network> getNetworks() {
        return this.networks;
    }

    public int getNonce() {
        return this.nonce;
    }

    public List<Integer> getSchemes() {
        return this.schemes;
    }

    public int hashCode() {
        List<Network> networks = getNetworks();
        int i = 43;
        int hashCode = networks == null ? 43 : networks.hashCode();
        String deviceSerialNumber = getDeviceSerialNumber();
        int hashCode2 = ((hashCode + 59) * 59) + (deviceSerialNumber == null ? 43 : deviceSerialNumber.hashCode());
        String deviceMacAddress = getDeviceMacAddress();
        int i2 = hashCode2 * 59;
        int hashCode3 = deviceMacAddress == null ? 43 : deviceMacAddress.hashCode();
        int errorCode = getErrorCode() + ((getNonce() + ((i2 + hashCode3) * 59)) * 59);
        List<Integer> schemes = getSchemes();
        int i3 = errorCode * 59;
        if (schemes != null) {
            i = schemes.hashCode();
        }
        return i3 + i;
    }

    public void setDeviceMacAddress(String str) {
        this.deviceMacAddress = str;
    }

    public void setDeviceSerialNumber(String str) {
        this.deviceSerialNumber = str;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setNetworks(List<Network> list) {
        this.networks = list;
    }

    public void setNonce(int i) {
        this.nonce = i;
    }

    public void setSchemes(List<Integer> list) {
        this.schemes = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceDetails(networks=");
        outline107.append(getNetworks());
        outline107.append(", deviceSerialNumber=");
        outline107.append(getDeviceSerialNumber());
        outline107.append(", deviceMacAddress=");
        outline107.append(getDeviceMacAddress());
        outline107.append(", nonce=");
        outline107.append(getNonce());
        outline107.append(", errorCode=");
        outline107.append(getErrorCode());
        outline107.append(", schemes=");
        outline107.append(getSchemes());
        outline107.append(")");
        return outline107.toString();
    }
}
