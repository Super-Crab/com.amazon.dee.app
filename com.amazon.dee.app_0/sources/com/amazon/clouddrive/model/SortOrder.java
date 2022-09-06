package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class SortOrder {
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    private static final String[] values = {ASC, DESC};

    private SortOrder() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
