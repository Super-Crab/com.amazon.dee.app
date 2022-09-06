package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.gv;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class DeviceDataKeys {
    @FireOsSdk
    public static final String KEY_DEVICE_SERIAL_NUMBER = "Device Serial Number";
    @FireOsSdk
    public static final String KEY_DEVICE_TYPE = "DeviceType";
    @FireOsSdk
    public static final String KEY_KE_DEVICE = "ke_device";
    @FireOsSdk
    public static final String KEY_KE_EDITION = "ke_edition";
    @FireOsSdk
    public static final String KEY_RE_DEVICE = "re_device";

    private DeviceDataKeys() {
    }

    @FireOsSdk
    public static String getDeviceTypeKeyForPackage(String str) {
        return gv.W(str, "DeviceType");
    }
}
