package com.amazon.alexa.handsfree.uservoicerecognition.quebec.enrollment;

import androidx.annotation.StringRes;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.R;
/* loaded from: classes8.dex */
enum EnrollError implements EnrollmentErrorContract {
    ENROLL_ERROR_GENERIC(-1, R.string.enroll_error_default, false),
    ENROLL_ERROR_AUDIO_RECORD(-23, R.string.enroll_error_audio_record, true),
    ENROLL_ERROR_NO_SPEECH(-25, R.string.enroll_error_weak, false),
    ENROLL_ERROR_TOO_MUCH_NOISE(-26, R.string.enroll_error_noisy, false),
    ENROLL_ERROR_DIFF(-27, R.string.enroll_error_default, false),
    ENROLL_ERROR_LOW_CONFIDENCE(-28, R.string.enroll_error_default, false),
    ENROLL_ERROR_LOW_SNR(-29, R.string.enroll_error_default_title, false);
    
    private static final String UNSUPPORTED_ERROR = "No error corresponding to error value %d";
    private final int mErrorResource;
    private final int mErrorTitleResource;
    private final int mErrorValue;
    private final boolean mIsTerminalError;

    EnrollError(int i, @StringRes int i2, boolean z) {
        this(i, 0, i2, z);
    }

    public static EnrollmentErrorContract from(int i) {
        EnrollError[] values;
        for (EnrollError enrollError : values()) {
            if (enrollError.mErrorValue == i) {
                return enrollError;
            }
        }
        throw new IllegalArgumentException(String.format(UNSUPPORTED_ERROR, Integer.valueOf(i)));
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
    @StringRes
    public int getReasonRes() {
        return this.mErrorResource;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    @StringRes
    public int getReasonTitleRes() {
        return this.mErrorTitleResource;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public boolean isTerminalError() {
        return this.mIsTerminalError;
    }

    EnrollError(int i, @StringRes int i2, @StringRes int i3, boolean z) {
        this.mErrorValue = i;
        this.mErrorTitleResource = i2;
        this.mErrorResource = i3;
        this.mIsTerminalError = z;
    }
}
