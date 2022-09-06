package com.amazon.alexa.preload.attribution.utils;

import android.os.Build;
/* loaded from: classes9.dex */
public class DeviceUtils {
    private boolean isCurrentDeviceEmma() {
        return Build.BRAND.equals("lge") && Build.DEVICE.equals("judyp") && Build.MANUFACTURER.equals("LGE") && Build.MODEL.equals("LM-V350") && Build.PRODUCT.equals("judyp_lao_com");
    }

    private boolean isCurrentDeviceIbis() {
        return Build.BRAND.equals("motorola") && Build.DEVICE.equals("payton") && Build.MANUFACTURER.equals("motorola") && Build.MODEL.equals("moto x4");
    }

    public String getBrand() {
        return Build.BRAND;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getProduct() {
        return Build.PRODUCT;
    }

    public boolean isCurrentDeviceAmokEnabled() {
        return isCurrentDeviceEmma() || isCurrentDeviceIbis();
    }
}
