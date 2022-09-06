package com.amazon.alexa.enrollment.exception;

import com.amazon.alexa.enrollment.R;
/* loaded from: classes7.dex */
public class NonRetryableSilenceException extends EnrollmentDialogException {
    public NonRetryableSilenceException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getMessageResId() {
        return R.string.handle_silence_message;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getRequestCode() {
        return 1;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getTitleResId() {
        return R.string.handle_silence_title;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public boolean hasNegativeButton() {
        return true;
    }

    public NonRetryableSilenceException(String str, Throwable th) {
        super(str, th);
    }

    public NonRetryableSilenceException(Throwable th) {
        super(th);
    }
}
