package com.amazon.alexa.handsfree.uservoicerecognition.model.errors;

import androidx.annotation.StringRes;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
/* loaded from: classes8.dex */
public enum VerificationError implements EnrollmentErrorContract {
    RECOG_NOT_READY,
    RECOG_ERROR,
    RECOG_FAILED,
    SV_FAILED;
    
    @StringRes
    private final int mReasonRes;

    VerificationError() {
        this(R.string.uvr_start_default_error);
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public String getErrorCode() {
        return "";
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public String getName() {
        return name();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public int getReasonRes() {
        return this.mReasonRes;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public int getReasonTitleRes() {
        return 0;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public boolean isTerminalError() {
        return false;
    }

    VerificationError(@StringRes int i) {
        this.mReasonRes = i;
    }
}
