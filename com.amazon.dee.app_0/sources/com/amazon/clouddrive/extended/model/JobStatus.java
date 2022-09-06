package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class JobStatus {
    public static final String DONE = "DONE";
    public static final String FAILED = "FAILED";
    public static final String NONE = "NONE";
    public static final String PENDING = "PENDING";
    private static final String[] values = {"DONE", "FAILED", "NONE", "PENDING"};

    public static String[] values() {
        return values;
    }
}
