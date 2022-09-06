package com.amazon.device.utils.thirdparty;

import java.security.SecureRandom;
/* loaded from: classes12.dex */
public class NullDeviceUtil implements DeviceUtil {
    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchCountryOfResidence() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchCustomerID() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceLanguage() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceMode() {
        return null;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceSerialNumber() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceSerialNumberOrAnonymous() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchDeviceType() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchOSFileTag() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchOTAGroupName() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchPreferredMarketplace() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchRemoteIP() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchRsmGroupName() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchSessionID() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchSoftwareVersion() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchStaticCredential() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchUserAgent() {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchWANSupportedDeviceMode() {
        return null;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String getRandomDigits(int i, SecureRandom secureRandom) {
        return "";
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public boolean isDeviceSerialNumberAnonymous() {
        return false;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public void updateCountryOfResidenceAndPreferredMarketplace() {
    }
}
