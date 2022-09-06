package com.amazon.alexa.client.metrics.kinesis.system;

import android.os.Build;
import java.util.Locale;
/* loaded from: classes6.dex */
public class AndroidDeviceDetails implements DeviceDetails {
    private final String carrier;

    public AndroidDeviceDetails(String str) {
        this.carrier = str;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails
    public String carrier() {
        return this.carrier;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails
    public String getCountry() {
        return Locale.getDefault() != null ? Locale.getDefault().toString().split("_").length > 1 ? Locale.getDefault().toString().split("_")[1] : "" : "Unknown";
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails
    public String getLanguage() {
        return Locale.getDefault() != null ? Locale.getDefault().toString().split("_").length > 0 ? Locale.getDefault().toString().split("_")[0] : "" : "Unknown";
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails
    public String locale() {
        return Locale.getDefault() != null ? Locale.getDefault().toString() : "Unknown";
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails
    public String manufacturer() {
        return Build.MANUFACTURER;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails
    public String model() {
        return Build.MODEL;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails
    public String platform() {
        return "ANDROID";
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails
    public String platformVersion() {
        return Build.VERSION.RELEASE;
    }
}
