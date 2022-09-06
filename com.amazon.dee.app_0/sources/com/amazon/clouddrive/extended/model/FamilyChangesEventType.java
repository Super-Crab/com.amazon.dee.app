package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class FamilyChangesEventType {
    public static final String UPDATE_NODE = "UPDATE_NODE";
    public static final String UPDATE_FAMILY = "UPDATE_FAMILY";
    public static final String CHECKPOINT = "CHECKPOINT";
    public static final String RESET = "RESET";
    private static final String[] values = {UPDATE_NODE, UPDATE_FAMILY, CHECKPOINT, RESET};

    private FamilyChangesEventType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
