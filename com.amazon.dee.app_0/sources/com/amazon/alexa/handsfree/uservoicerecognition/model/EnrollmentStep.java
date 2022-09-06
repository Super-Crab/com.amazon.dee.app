package com.amazon.alexa.handsfree.uservoicerecognition.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceInfo;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
/* loaded from: classes8.dex */
public class EnrollmentStep {
    private final int mMaxAllowedTrainingErrors;
    private final UtteranceInfo mUtteranceInfo;
    private TrainingState mTrainingState = TrainingState.NOT_STARTED;
    private int mNumTrainingErrors = 0;
    private boolean mHadAnyErrors = false;

    /* loaded from: classes8.dex */
    public enum TrainingState {
        NOT_STARTED(R.drawable.ic_not_started_uvr, R.string.step_not_started),
        IN_PROGRESS(R.drawable.ic_in_progress_uvr, R.string.step_in_progress),
        ERROR(R.drawable.ic_cancel_uvr, R.string.step_error),
        COMPLETE(R.drawable.ic_done_uvr, R.string.step_complete);
        
        @StringRes
        private int mContentDescriptionResource;
        @DrawableRes
        private int mDrawableRes;

        TrainingState(@DrawableRes int i, @StringRes int i2) {
            this.mDrawableRes = i;
            this.mContentDescriptionResource = i2;
        }

        @StringRes
        public int getContentDescriptionResource() {
            return this.mContentDescriptionResource;
        }

        @DrawableRes
        public int getDrawableRes() {
            return this.mDrawableRes;
        }
    }

    public EnrollmentStep(@NonNull UtteranceInfo utteranceInfo, int i) {
        this.mUtteranceInfo = utteranceInfo;
        this.mMaxAllowedTrainingErrors = i;
    }

    public TrainingState getTrainingState() {
        return this.mTrainingState;
    }

    public UtteranceInfo getUtteranceInfo() {
        return this.mUtteranceInfo;
    }

    public boolean hadAnyErrors() {
        return this.mHadAnyErrors;
    }

    public boolean hasExceededMaxAllowedErrors() {
        return this.mNumTrainingErrors > this.mMaxAllowedTrainingErrors;
    }

    public void onTrainingError() {
        this.mNumTrainingErrors++;
    }

    public void setHadAnyErrors() {
        this.mHadAnyErrors = true;
    }

    public void setTrainingState(TrainingState trainingState) {
        this.mTrainingState = trainingState;
    }
}
