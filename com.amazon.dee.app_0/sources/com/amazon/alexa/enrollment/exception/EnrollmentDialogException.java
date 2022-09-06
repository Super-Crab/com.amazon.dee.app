package com.amazon.alexa.enrollment.exception;
/* loaded from: classes7.dex */
public abstract class EnrollmentDialogException extends EnrollmentException {
    public EnrollmentDialogException(String str) {
        super(str);
    }

    public abstract int getMessageResId();

    public abstract int getRequestCode();

    public abstract int getTitleResId();

    public abstract boolean hasNegativeButton();

    public EnrollmentDialogException(String str, Throwable th) {
        super(str, th);
    }

    public EnrollmentDialogException(Throwable th) {
        super(th);
    }
}
