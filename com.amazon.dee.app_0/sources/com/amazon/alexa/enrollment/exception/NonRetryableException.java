package com.amazon.alexa.enrollment.exception;

import com.amazon.alexa.enrollment.R;
/* loaded from: classes7.dex */
public class NonRetryableException extends EnrollmentDialogException {
    public NonRetryableException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getMessageResId() {
        return R.string.fe_handle_non_retry_msg;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getRequestCode() {
        return 3;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getTitleResId() {
        return R.string.fe_handle_non_retry_title;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public boolean hasNegativeButton() {
        return false;
    }

    public NonRetryableException(String str, Throwable th) {
        super(str, th);
    }

    public NonRetryableException(Throwable th) {
        super(th);
    }
}
