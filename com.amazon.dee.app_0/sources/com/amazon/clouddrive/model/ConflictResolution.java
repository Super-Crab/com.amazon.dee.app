package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class ConflictResolution {
    public static final String NONE = "NONE";
    public static final String RENAME = "RENAME";
    private static final String[] values = {"NONE", RENAME};

    private ConflictResolution() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
