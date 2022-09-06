package com.amazon.alexa.handsfree.uservoicerecognition.model.errors;

import androidx.annotation.StringRes;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
/* loaded from: classes8.dex */
public enum TrainingError implements EnrollmentErrorContract {
    TOO_SLOW,
    TOO_FAST,
    TOO_QUIET,
    TOO_LOUD,
    TOO_SHORT,
    TOO_LONG,
    TOO_NOISY,
    TOO_MANY_PAUSES,
    INCORRECT_PHRASE,
    CROSS_CORRELATION_FAILED,
    SANITY_FAILED,
    SAVE_FAILED,
    MISSING_START_SILENCE,
    MISSING_END_SILENCE,
    RECOG_EMPTY(R.string.uvr_enroll_error_no_wake_word),
    RECOG_ERROR,
    NOT_ENOUGH_ENROLLMENT,
    PHRASE_QUALITY,
    COMPLETE_FAILED,
    SNR(R.string.uvr_enroll_error_low_snr),
    WIRED_HEADSET,
    INTERRUPTED,
    POOR_RECORDINGS,
    SPOT_FAILED,
    AUDIO_IN_PROGRESS,
    CALL_IN_PROGRESS,
    ALARM_IN_PROGRESS,
    ON_TABLE,
    ACCESSIBILITY_EVENT,
    TOO_FEW_SYLLABLES,
    TOO_MANY_SYLLABLES,
    BLUETOOTH_HEADSET,
    MIC_USE_BY_OTHER_APP,
    PRE_SPEECH_POP,
    POST_SPEECH_POP,
    PRE_SPEECH_NOISE,
    POST_SPEECH_NOISE,
    UNKNOWN;
    
    @StringRes
    private final int mReasonRes;

    TrainingError() {
        this(R.string.uvr_enroll_error_default);
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

    TrainingError(@StringRes int i) {
        this.mReasonRes = i;
    }
}
