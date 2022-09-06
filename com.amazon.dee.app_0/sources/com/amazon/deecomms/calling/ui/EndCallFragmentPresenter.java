package com.amazon.deecomms.calling.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.calling.controller.EndCallActionClickListener;
import com.amazon.deecomms.calling.controller.EndCallActionContract;
import com.amazon.deecomms.calling.ui.BaseCallingContract;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
/* loaded from: classes12.dex */
public class EndCallFragmentPresenter implements EndCallActionClickListener, BaseCallingContract.Presenter {
    private final EndCallActionContract mActionContract;
    private String mCallDurationInSeconds;
    private final String mCallId;
    private DriveModeSharedPreferencesUseCase mDriveModeSharedPreferencesUseCase;
    private String mEndCallStatus;
    private boolean mIsCallSuccess;
    private boolean mIsMetricsSent = false;
    private String mParticipantName;
    private String mRatingPrompt;
    private boolean mShouldShowCallRating;
    private String mThankYouText;
    private String mUserRating;
    private EndCallFragmentViewHolder mViewHolder;

    public EndCallFragmentPresenter(@NonNull EndCallActionContract endCallActionContract, @Nullable String str, @NonNull DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        this.mActionContract = endCallActionContract;
        this.mCallId = str;
        this.mDriveModeSharedPreferencesUseCase = driveModeSharedPreferencesUseCase;
    }

    private void showCallRating() {
        if (!isAppInDriveMode()) {
            this.mActionContract.cancelTimer();
        }
        this.mViewHolder.hideRateCallAndShowCallRatingSheet();
        this.mViewHolder.showSkipAndHideThanks(this.mRatingPrompt);
    }

    private void showCallRatingOrFinishActivity() {
        if (this.mIsCallSuccess && !isAppInDriveMode()) {
            showRateCallOrPromptRating();
            return;
        }
        this.mViewHolder.hideBottomSheet();
        this.mActionContract.delayedFinishActivity();
    }

    private void showRateCallOrPromptRating() {
        if (this.mShouldShowCallRating) {
            showCallRating();
        } else {
            this.mViewHolder.showRateCallAndHideCallRatingSheet();
        }
    }

    @Override // com.amazon.deecomms.calling.ui.BaseCallingContract.Presenter
    public boolean isAppInDriveMode() {
        return this.mDriveModeSharedPreferencesUseCase.isInDriveMode();
    }

    @VisibleForTesting
    boolean isMetricsSent() {
        return this.mIsMetricsSent;
    }

    public void onDestroy() {
        this.mViewHolder = null;
        sendMetrics();
    }

    public void onDestroyView() {
        this.mViewHolder = null;
    }

    public void onPause() {
    }

    @Override // com.amazon.deecomms.calling.controller.EndCallActionClickListener
    public void onRateCallClicked() {
        showCallRating();
    }

    public void onResume() {
    }

    @Override // com.amazon.deecomms.calling.controller.EndCallActionClickListener
    public void onSkipClicked() {
        sendMetrics();
        this.mActionContract.delayedFinishActivity();
    }

    @Override // com.amazon.deecomms.calling.controller.EndCallActionClickListener
    public void onStarsClicked(int i) {
        this.mUserRating = String.valueOf(i);
        this.mViewHolder.disableBarRating();
        this.mViewHolder.hideSkipAndShowThanks(this.mThankYouText);
        sendMetrics();
        this.mActionContract.delayedFinishActivity();
    }

    public void onStop() {
        sendMetrics();
        this.mViewHolder = null;
        this.mActionContract.delayedFinishActivity();
    }

    @VisibleForTesting
    void sendMetrics() {
        if (!isMetricsSent()) {
            setMetricsSent(true);
            CommsDaggerWrapper.getComponent().getCallManager().reportCallUICompleted(this.mCallId, this.mUserRating);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void setCallStats(@Nullable String str, @Nullable String str2, @Nullable String str3, boolean z, @Nullable String str4, @Nullable String str5, boolean z2) {
        this.mParticipantName = str;
        this.mCallDurationInSeconds = str2;
        this.mEndCallStatus = str3;
        this.mShouldShowCallRating = z;
        this.mRatingPrompt = str4;
        this.mThankYouText = str5;
        this.mIsCallSuccess = z2;
    }

    @VisibleForTesting
    void setMetricsSent(boolean z) {
        this.mIsMetricsSent = z;
    }

    public void setViewHolder(EndCallFragmentViewHolder endCallFragmentViewHolder) {
        this.mViewHolder = endCallFragmentViewHolder;
        this.mViewHolder.updateViews(this.mParticipantName, this.mCallDurationInSeconds, this.mEndCallStatus);
        showCallRatingOrFinishActivity();
    }
}
