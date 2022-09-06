package com.amazon.alexa.alertsca.device;

import android.os.Build;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Device {
    public static Device create() {
        return create(Build.BRAND, Build.DEVICE, Build.MANUFACTURER, Build.MODEL, Build.PRODUCT);
    }

    public abstract String getBrand();

    public abstract String getDevice();

    public abstract String getManufacturer();

    public abstract String getModel();

    public abstract String getProduct();

    public static Device create(String str, String str2, String str3, String str4, String str5) {
        return new AutoValue_Device(str, str2, str3, str4, str5);
    }
}
