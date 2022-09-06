package com.amazon.alexa.enrollment.unified.speakerid.error;
/* loaded from: classes7.dex */
public class UserIneligibleToEnrollException extends EnrollmentException {
    public UserIneligibleToEnrollException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }
}
