package com.amazon.alexa.enrollment.exception;
/* loaded from: classes7.dex */
public class HandleSilenceException extends EnrollmentException {
    public HandleSilenceException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentException
    public boolean canShowInlineError() {
        return true;
    }

    public HandleSilenceException(String str, Throwable th) {
        super(str, th);
    }

    public HandleSilenceException(Throwable th) {
        super(th);
    }
}
