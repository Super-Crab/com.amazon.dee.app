package com.amazon.alexa.enrollment.unified.speakerid.error;

import com.amazon.alexa.enrollment.unified.R;
/* loaded from: classes7.dex */
public class NonRetryableException extends EnrollmentDialogException {
    public NonRetryableException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }

    @Override // com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentDialogException
    public int getMessageResId() {
        return R.string.fe_handle_non_retry_msg;
    }

    @Override // com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentDialogException
    public int getTitleResId() {
        return R.string.fe_handle_non_retry_title;
    }

    public NonRetryableException(String str, Throwable th) {
        super(str, th);
    }
}
