package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class BulkOperationType {
    public static final String add = "add";
    public static final String remove = "remove";
    private static final String[] values = {add, remove};

    private BulkOperationType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
