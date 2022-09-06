package com.amazon.comms.ringservice.util;

import android.os.Build;
/* loaded from: classes12.dex */
public class DeviceModel {
    public static final boolean AMAZON_AEOKN;
    public static final boolean AMAZON_DEVICE = "Amazon".equalsIgnoreCase(Build.BRAND);

    static {
        AMAZON_AEOKN = AMAZON_DEVICE && "AEOKN".equalsIgnoreCase(Build.MODEL);
    }
}
