package com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype;
/* loaded from: classes8.dex */
public enum EnrollmentStatus {
    SETUP_IN_3PSV(1),
    SETUP_IN_1PSV(0),
    SETUP_NOT_SET(-1);
    
    private final int mValue;

    EnrollmentStatus(int i) {
        this.mValue = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EnrollmentStatus fromValue(int i) {
        EnrollmentStatus[] values;
        for (EnrollmentStatus enrollmentStatus : values()) {
            if (enrollmentStatus.getValue() == i) {
                return enrollmentStatus;
            }
        }
        return SETUP_NOT_SET;
    }

    public int getValue() {
        return this.mValue;
    }
}
