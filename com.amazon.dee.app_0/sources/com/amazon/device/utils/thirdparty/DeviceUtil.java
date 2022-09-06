package com.amazon.device.utils.thirdparty;

import java.security.SecureRandom;
/* loaded from: classes12.dex */
public interface DeviceUtil {
    String fetchCountryOfResidence();

    String fetchCustomerID();

    String fetchDeviceLanguage();

    String fetchDeviceMode();

    String fetchDeviceSerialNumber();

    String fetchDeviceSerialNumberOrAnonymous();

    String fetchDeviceType();

    String fetchOSFileTag();

    String fetchOTAGroupName();

    String fetchPreferredMarketplace();

    String fetchRemoteIP();

    @Deprecated
    String fetchRsmGroupName();

    String fetchSessionID();

    String fetchSoftwareVersion();

    String fetchStaticCredential();

    String fetchUserAgent();

    String fetchWANSupportedDeviceMode();

    String getRandomDigits(int i, SecureRandom secureRandom);

    boolean isDeviceSerialNumberAnonymous();

    void updateCountryOfResidenceAndPreferredMarketplace();
}
