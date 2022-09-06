package com.amazon.alexa.enrollment.unified.speakerid.error;
/* loaded from: classes7.dex */
public abstract class EnrollmentDialogException extends EnrollmentException {
    public EnrollmentDialogException(String str) {
        super(str);
    }

    public abstract int getMessageResId();

    public abstract int getTitleResId();

    public EnrollmentDialogException(String str, Throwable th) {
        super(str, th);
    }
}
