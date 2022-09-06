package com.amazon.alexa.bluetooth.sco;

import android.os.Build;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes6.dex */
class DeviceInformation {
    @VisibleForTesting
    static final int ANDROID_Q = 29;
    @VisibleForTesting
    static final String MANUFACTURER_SAMSUNG = "samsung";
    public static final String TAG = "DeviceInformation";

    String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getVersionSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSamsungAndAndroidQ() {
        return getManufacturer().equalsIgnoreCase(MANUFACTURER_SAMSUNG) && getVersionSdkInt() >= 29;
    }
}
