package com.amazon.alexa.enrollment.unified.speakerid.error;
/* loaded from: classes7.dex */
public class EnrollmentComponentNullException extends EnrollmentException {
    public EnrollmentComponentNullException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }
}
