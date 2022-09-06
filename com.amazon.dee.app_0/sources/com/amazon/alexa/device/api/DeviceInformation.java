package com.amazon.alexa.device.api;

import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import java.util.Map;
/* loaded from: classes.dex */
public interface DeviceInformation {
    String getAndroidId();

    String getBrand();

    String getDeviceSerialNumber() throws DeviceInformationException;

    @NonNull
    String getDeviceType() throws DeviceInformationException;

    DisplayMetrics getDisplayMetrics();

    @NonNull
    Map<String, Object> getDynamicDeviceProfileData(boolean z);

    String getFireOSVersion();

    String getId();

    String getLanguage();

    String getManufacturer();

    int getMemoryClass();

    String getModel();

    String getPlatformType();

    double getScreenSize();

    String getSerial();

    @NonNull
    Map<String, Object> getStaticDeviceProfileData();

    @Deprecated
    String getVersionName();

    String getVersionRelease();

    int getVersionSdkInt();

    boolean isFireOS();

    boolean isPhoneFormFactor();

    void refreshDeviceInfoData();
}
