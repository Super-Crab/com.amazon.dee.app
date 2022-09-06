package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class NodeType {
    public static final String FILE = "FILE";
    public static final String GROUP = "GROUP";
    public static final String SOURCE = "SOURCE";
    private static final String[] values = {"FILE", "GROUP", SOURCE};

    private NodeType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
