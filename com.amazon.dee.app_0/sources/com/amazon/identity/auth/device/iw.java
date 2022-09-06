package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class iw {
    private static final String TAG = "com.amazon.identity.auth.device.iw";

    private iw() {
    }

    public static String c(Context context, DeviceAttribute deviceAttribute) {
        Object a = bc.bm().a(context, deviceAttribute);
        if (a instanceof String) {
            return (String) a;
        }
        io.c(TAG, "Device Attribute %s is not of type String.", deviceAttribute.toString());
        throw new RuntimeException("Incorrectly called getDeviceAttribute in Platform.  Try calling hasDeviceAttribute.");
    }

    public static boolean d(Context context, DeviceAttribute deviceAttribute) {
        Object a = bc.bm().a(context, deviceAttribute);
        if (a instanceof Boolean) {
            return ((Boolean) a).booleanValue();
        }
        io.c(TAG, "Device Attribute %s is not of type boolean.", deviceAttribute.toString());
        throw new RuntimeException("Incorrectly called hasDeviceAttribute in Platform.  Try calling getDeviceAttribute.");
    }
}
