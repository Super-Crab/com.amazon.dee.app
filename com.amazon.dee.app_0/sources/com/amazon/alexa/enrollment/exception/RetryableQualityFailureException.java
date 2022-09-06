package com.amazon.alexa.enrollment.exception;
/* loaded from: classes7.dex */
public class RetryableQualityFailureException extends EnrollmentException {
    public RetryableQualityFailureException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentException
    public boolean canShowInlineError() {
        return true;
    }

    public RetryableQualityFailureException(String str, Throwable th) {
        super(str, th);
    }

    public RetryableQualityFailureException(Throwable th) {
        super(th);
    }
}
