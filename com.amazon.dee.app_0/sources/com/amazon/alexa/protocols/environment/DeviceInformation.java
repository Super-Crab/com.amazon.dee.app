package com.amazon.alexa.protocols.environment;

import androidx.annotation.NonNull;
import java.util.Map;
@Deprecated
/* loaded from: classes9.dex */
public interface DeviceInformation {
    String getAndroidId();

    String getBrand();

    @NonNull
    Map<String, Object> getDynamicDeviceProfileData(boolean z);

    String getFireOSVersion();

    String getId();

    String getLanguage();

    String getManufacturer();

    int getMemoryClass();

    String getModel();

    String getPlatformType();

    String getSerial();

    @NonNull
    Map<String, Object> getStaticDeviceProfileData();

    String getVersionName();

    String getVersionRelease();

    int getVersionSdkInt();

    boolean isFireOS();

    boolean isPhoneFormFactor();

    void refreshDeviceInfoData();
}
