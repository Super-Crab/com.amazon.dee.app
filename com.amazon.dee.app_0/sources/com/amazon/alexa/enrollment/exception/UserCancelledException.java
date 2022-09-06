package com.amazon.alexa.enrollment.exception;
/* loaded from: classes7.dex */
public class UserCancelledException extends EnrollmentException {
    public UserCancelledException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }

    public UserCancelledException(String str, Throwable th) {
        super(str, th);
    }

    public UserCancelledException(Throwable th) {
        super(th);
    }
}
