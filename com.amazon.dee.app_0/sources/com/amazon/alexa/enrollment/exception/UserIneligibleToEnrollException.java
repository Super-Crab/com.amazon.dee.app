package com.amazon.alexa.enrollment.exception;
/* loaded from: classes7.dex */
public class UserIneligibleToEnrollException extends EnrollmentException {
    public UserIneligibleToEnrollException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }

    public UserIneligibleToEnrollException(String str, Throwable th) {
        super(str, th);
    }

    public UserIneligibleToEnrollException(Throwable th) {
        super(th);
    }
}
