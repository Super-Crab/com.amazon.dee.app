package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class NodeStatus {
    public static final String AVAILABLE = "AVAILABLE";
    public static final String PENDING = "PENDING";
    public static final String PURGED = "PURGED";
    public static final String TRASH = "TRASH";
    private static final String[] values = {"AVAILABLE", "PENDING", "TRASH", "PURGED"};

    private NodeStatus() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
