package com.amazon.alexa.wakeword.speakerverification.errors;

import androidx.annotation.StringRes;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.wakeword.speakerverification.R;
/* loaded from: classes11.dex */
public enum TrainingFailure {
    PRYON_INITIALIZATION(1, true),
    GET_ENROLLMENT_UTTERANCES(2, true),
    SAVE_ENROLLMENT_UTTERANCES(3, true),
    PUSH_FAILED(4, true),
    EXAMPLE_REJECTED(5, false),
    SAVE_PROFILE(6, true),
    GET_PROFILE(7, true),
    WAKE_WORD_NOT_DETECTED(8, false, R.string.handsfree_enrollment_detection_timeout_error_title, R.string.handsfree_enrollment_detection_timeout_error_message),
    WAKE_WORD_DETECTION_IO(9, true),
    INVALID_PERSON_ID(10, true),
    WAKE_WORD_DETECTED_CALLBACK_NOT_INVOKED(11, true),
    PERSIST_SPEAKER_VERIFICATION_MODEL(12, true),
    UPLOAD_ENROLLMENT_UTTERANCES(13, true),
    EXAMPLE_ACCEPTED_BEFORE_WAKE_WORD_DETECTED(14, true);
    
    private final EnrollmentErrorContract mEnrollmentErrorContract;
    private final int mErrorCode;

    TrainingFailure(int i, boolean z) {
        this(i, z, R.string.handsfree_generic_enrollment_error_title, R.string.handsfree_generic_enrollment_error_message);
    }

    public EnrollmentErrorContract getEnrollmentErrorContract() {
        return this.mEnrollmentErrorContract;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    TrainingFailure(final int i, final boolean z, @StringRes final int i2, @StringRes final int i3) {
        this.mErrorCode = i;
        this.mEnrollmentErrorContract = new EnrollmentErrorContract() { // from class: com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure.1
            @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
            public String getErrorCode() {
                return String.format("%d.%d", Integer.valueOf(EnrollmentErrorContract.ErrorSource._1PSV.getCode()), Integer.valueOf(i));
            }

            @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
            public String getName() {
                return TrainingFailure.this.name();
            }

            @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
            public int getReasonRes() {
                return i3;
            }

            @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
            public int getReasonTitleRes() {
                return i2;
            }

            @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
            public boolean isTerminalError() {
                return z;
            }
        };
    }
}
