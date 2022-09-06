package com.amazon.alexa.enrollment.unified.speakerid.error;

import com.amazon.alexa.enrollment.unified.R;
/* loaded from: classes7.dex */
public class NonRetryableSilenceException extends EnrollmentDialogException {
    public NonRetryableSilenceException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }

    @Override // com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentDialogException
    public int getMessageResId() {
        return R.string.handle_silence_message;
    }

    @Override // com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentDialogException
    public int getTitleResId() {
        return R.string.handle_silence_title;
    }
}
