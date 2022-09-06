package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class DeviceStatus {
    public static final String ACTIVE = "ACTIVE";
    public static final String HIDDEN = "HIDDEN";
    private static final String[] values = {"ACTIVE", "HIDDEN"};

    private DeviceStatus() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
