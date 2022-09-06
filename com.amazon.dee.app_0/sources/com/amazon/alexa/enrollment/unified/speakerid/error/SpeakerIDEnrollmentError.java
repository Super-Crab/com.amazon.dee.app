package com.amazon.alexa.enrollment.unified.speakerid.error;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.enrollment.unified.speakerid.metrics.SpeakerIDMetricsConstants;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
/* loaded from: classes7.dex */
public class SpeakerIDEnrollmentError implements EnrollmentErrorContract {
    private final SpeakerIDMetricsConstants.ErrorType mErrorType;
    private final boolean mIsTerminalError;
    private final String mName;
    private final int mReasonMessageResId;
    private final int mReasonTitleResId;
    private final SpeakerIDErrorParser mSpeakerIDErrorParser;
    private final Throwable mThrowable;

    public SpeakerIDEnrollmentError(int i, int i2, @NonNull String str, @NonNull Throwable th, boolean z, @Nullable SpeakerIDMetricsConstants.ErrorType errorType, @NonNull SpeakerIDErrorParser speakerIDErrorParser) {
        this.mReasonTitleResId = i;
        this.mReasonMessageResId = i2;
        this.mName = str;
        this.mThrowable = th;
        this.mIsTerminalError = z;
        this.mErrorType = errorType == null ? SpeakerIDMetricsConstants.ErrorType.UNKNOWN_EXCEPTION : errorType;
        this.mSpeakerIDErrorParser = speakerIDErrorParser;
    }

    private boolean isAIS() {
        return this.mSpeakerIDErrorParser.getErrorType(this.mThrowable) != null;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public String getErrorCode() {
        int errorCode = this.mErrorType.getErrorCode();
        return isAIS() ? String.format("%d.%d", Integer.valueOf(EnrollmentErrorContract.ErrorSource.SPEAKER_ID_AIS.getCode()), Integer.valueOf(errorCode)) : this.mErrorType == SpeakerIDMetricsConstants.ErrorType.NETWORK_UNAVAILABLE ? String.format("%d.%d", Integer.valueOf(EnrollmentErrorContract.ErrorSource.SPEAKER_ID_NETWORK_UNAVAILABLE.getCode()), Integer.valueOf(errorCode)) : String.format("%d.%d", Integer.valueOf(EnrollmentErrorContract.ErrorSource.SPEAKER_ID_OTHER.getCode()), Integer.valueOf(errorCode));
    }

    public String getErrorPmetName() {
        return getThrowable().getClass().getSimpleName();
    }

    public SpeakerIDMetricsConstants.ErrorType getErrorType() {
        return this.mErrorType;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public String getName() {
        return this.mName;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public int getReasonRes() {
        return this.mReasonMessageResId;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public int getReasonTitleRes() {
        return this.mReasonTitleResId;
    }

    public Throwable getThrowable() {
        return this.mThrowable;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract
    public boolean isTerminalError() {
        return this.mIsTerminalError;
    }
}
