package com.amazon.client.metrics.thirdparty.utils;

import android.app.UiModeManager;
import android.content.Context;
/* loaded from: classes11.dex */
public class DeviceCategoryUtil {
    public static final String SET_TOP_BOX = "SetTopBox";
    public static final String SMART_PHONE = "Smartphone";
    public static final String TABLET = "Tablet";
    private static String sDeviceCategory;

    public static String getDeviceCategory(Context context) {
        if (sDeviceCategory == null) {
            if (((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 4) {
                sDeviceCategory = SET_TOP_BOX;
            } else if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
                sDeviceCategory = SMART_PHONE;
            } else {
                sDeviceCategory = TABLET;
            }
        }
        return sDeviceCategory;
    }
}
