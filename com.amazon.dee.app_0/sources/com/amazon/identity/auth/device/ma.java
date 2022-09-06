package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ma {
    private static final String TAG = "com.amazon.identity.auth.device.ma";

    private ma() {
    }

    public static boolean eG(String str) {
        if (isNullOrEmpty(str)) {
            io.i(TAG, "isValidDeviceType: returning false because a null or empty device type was given.");
            return false;
        } else if (eI(str)) {
            return true;
        } else {
            io.i(TAG, "isValidDeviceType: returning false because a non alpha numeric device type was given.");
            return false;
        }
    }

    public static boolean eH(String str) {
        if (isNullOrEmpty(str)) {
            io.i(TAG, "isValidDeviceSerialNumber: returning false because a null or empty device serial number was given.");
            return false;
        } else if (!eI(str)) {
            io.i(TAG, "isValidDeviceSerialNumber: returning false because a non alpha numeric serial number was given.");
            return false;
        } else if (str.length() <= 51) {
            return true;
        } else {
            io.w(TAG, "isValidDeviceSerialNumber: returning false because a serial number that is too long (more than 51 characters) was given.");
            return false;
        }
    }

    public static boolean eI(String str) {
        return str != null && str.matches("[a-zA-Z0-9]*");
    }

    public static boolean eJ(String str) {
        return str != null && str.matches("[0-9]*");
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }
}
