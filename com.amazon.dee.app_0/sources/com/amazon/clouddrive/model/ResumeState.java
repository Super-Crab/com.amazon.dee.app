package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class ResumeState {
    public static final String FAILED = "FAILED";
    public static final String IN_PROGRESS = "IN_PROGRESS";
    public static final String READY_FOR_COMPLETION = "READY_FOR_COMPLETION";
    public static final String COMPLETED = "COMPLETED";
    private static final String[] values = {"IN_PROGRESS", READY_FOR_COMPLETION, COMPLETED, "FAILED"};

    private ResumeState() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
