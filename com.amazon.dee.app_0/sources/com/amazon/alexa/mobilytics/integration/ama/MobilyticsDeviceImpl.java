package com.amazon.alexa.mobilytics.integration.ama;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.mobilytics.identity.MobilyticsDevice;
/* loaded from: classes9.dex */
public class MobilyticsDeviceImpl implements MobilyticsDevice {
    private final DeviceInformation deviceInformation;

    public MobilyticsDeviceImpl(@NonNull DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDevice
    @NonNull
    public String deviceSerialNumber() {
        try {
            return (this.deviceInformation == null || this.deviceInformation.getDeviceSerialNumber() == null || this.deviceInformation.getDeviceSerialNumber().isEmpty()) ? "Unknown" : this.deviceInformation.getDeviceSerialNumber();
        } catch (DeviceInformationException unused) {
            return "Unknown";
        }
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDevice
    @NonNull
    public String deviceType() {
        try {
            return (this.deviceInformation == null || TextUtils.isEmpty(this.deviceInformation.getDeviceType())) ? "Unknown" : this.deviceInformation.getDeviceType();
        } catch (DeviceInformationException unused) {
            return "Unknown";
        }
    }
}
