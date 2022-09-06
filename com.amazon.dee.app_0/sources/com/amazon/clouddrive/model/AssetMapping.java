package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class AssetMapping {
    public static final String ALL = "ALL";
    public static final String DESKTOP = "DESKTOP";
    public static final String MOBILE = "MOBILE";
    public static final String NONE = "NONE";
    private static final String[] values = {"NONE", "ALL", "MOBILE", "DESKTOP"};

    private AssetMapping() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
