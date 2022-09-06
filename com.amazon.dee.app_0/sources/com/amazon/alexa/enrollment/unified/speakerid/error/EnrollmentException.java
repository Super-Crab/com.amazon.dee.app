package com.amazon.alexa.enrollment.unified.speakerid.error;
/* loaded from: classes7.dex */
public abstract class EnrollmentException extends RuntimeException {
    public EnrollmentException(String str) {
        super(str);
    }

    public abstract boolean canShowInlineError();

    public EnrollmentException(String str, Throwable th) {
        super(str, th);
    }
}
