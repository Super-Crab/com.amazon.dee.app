package com.amazon.alexa.enrollment.exception;
/* loaded from: classes7.dex */
public class EnrollmentComponentNullException extends EnrollmentException {
    public EnrollmentComponentNullException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }

    public EnrollmentComponentNullException(String str, Throwable th) {
        super(str, th);
    }

    public EnrollmentComponentNullException(Throwable th) {
        super(th);
    }
}
