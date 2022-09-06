package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class DevicePlatform {
    public static final String ANDROID = "ANDROID";
    public static final String FIREOS = "FIREOS";
    public static final String IOS = "IOS";
    public static final String MAC = "MAC";
    public static final String SERVICE = "SERVICE";
    public static final String WEB = "WEB";
    public static final String WINDOWS = "WINDOWS";
    private static final String[] values = {"FIREOS", "IOS", "ANDROID", "WINDOWS", "MAC", "WEB", "SERVICE"};

    private DevicePlatform() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
