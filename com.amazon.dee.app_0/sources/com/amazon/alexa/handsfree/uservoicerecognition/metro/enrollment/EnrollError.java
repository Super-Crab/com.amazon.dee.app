package com.amazon.alexa.handsfree.uservoicerecognition.metro.enrollment;

import androidx.annotation.StringRes;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.R;
/* loaded from: classes8.dex */
enum EnrollError implements EnrollmentErrorContract {
    ENROLL_ERROR_NOT_ENOUGH(1, R.string.enroll_error_default, false),
    ENROLL_ERROR_NOISY(2, R.string.enroll_error_noisy, false),
    ENROLL_ERROR_WEAK(3, R.string.enroll_error_weak, false),
    ENROLL_ERROR_DIFF(4, R.string.enroll_error_default, false),
    ENROLL_ERROR_EXIST(5, R.string.enroll_error_default_title, R.string.enroll_error_default, true),
    ENROLL_ERROR_TIMEOUT(6, R.string.enroll_error_default, false),
    ENROLL_ERROR_PAUSE_TIMEOUT(7, R.string.enroll_error_default_title, R.string.enroll_error_default, true),
    ENROLL_ERROR_DIFF_CUSTOMER(11, R.string.enroll_error_default, false),
    ENROLL_ERROR_HEADSET_SWAP(100, R.string.enroll_error_headset_swap_title, R.string.enroll_error_headset_swap_body, true);
    
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
