package com.amazon.alexa.mode.model;

import com.amazon.dee.app.services.bluetooth.DefaultBluetoothService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes9.dex */
public class AmaDevice {
    @SerializedName(DefaultBluetoothService.AUDIO_DEVICE_NAME)
    @Expose
    private String deviceName;
    @SerializedName("deviceType")
    @Expose
    private String deviceType;

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
