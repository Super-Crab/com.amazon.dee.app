package com.amazon.alexa.enrollment.exception;

import com.amazon.alexa.enrollment.R;
/* loaded from: classes7.dex */
public class VoiceProfileAlreadyExistsException extends EnrollmentDialogException {
    public VoiceProfileAlreadyExistsException(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentException
    public boolean canShowInlineError() {
        return false;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getMessageResId() {
        return R.string.fe_voice_profile_exist_error_desc;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getRequestCode() {
        return 3;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public int getTitleResId() {
        return R.string.fe_voice_profile_exist_error_title;
    }

    @Override // com.amazon.alexa.enrollment.exception.EnrollmentDialogException
    public boolean hasNegativeButton() {
        return false;
    }

    public VoiceProfileAlreadyExistsException(String str, Throwable th) {
        super(str, th);
    }

    public VoiceProfileAlreadyExistsException(Throwable th) {
        super(th);
    }
}
