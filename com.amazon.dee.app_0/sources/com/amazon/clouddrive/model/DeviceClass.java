package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class DeviceClass {
    public static final String DESKTOP = "DESKTOP";
    public static final String MOBILE = "MOBILE";
    public static final String TABLET = "TABLET";
    public static final String TV = "TV";
    public static final String VIRTUAL = "VIRTUAL";
    public static final String WATCH = "WATCH";
    private static final String[] values = {"DESKTOP", "MOBILE", "TABLET", "WATCH", "TV", "VIRTUAL"};

    private DeviceClass() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
