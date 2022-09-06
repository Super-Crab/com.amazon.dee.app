package com.amazon.deecomms.calling.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.controller.EndCallActionContract;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.PatternUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Timer;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class EndCallFragment extends Fragment implements EndCallActionContract {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, EndCallFragment.class);
    private static final String TIME_DURATION_SPLITTER = ":";
    @Inject
    CallContext callContext;
    @Inject
    CallInitiationAuthority callInitiationAuthority;
    private Activity mActivity;
    @Inject
    CapabilitiesManager mCapabilitiesManager;
    @Inject
    DriveModeSharedPreferencesUseCase mDriveModeSharedPreferencesUseCase;
    private boolean mIsCallSuccess = false;
    private EndCallFragmentPresenter mPresenter;
    private Timer mTimer;

    private boolean isQualifiedCallForRating(@NonNull String str) {
        if (!PatternUtils.isValidDuration(str)) {
            GeneratedOutlineSupport.outline3("Invalid call duration pattern : ", str, LOG);
            return false;
        }
        String[] split = str.split(":");
        if (split.length <= 3 && split.length >= 2) {
            Integer configInteger = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CALL_RATING_MIN_CALL_DURATION);
            int i = 0;
            int i2 = 1;
            for (int length = split.length - 1; length >= 0; length--) {
                i += Integer.parseInt(split[length]) * i2;
                if (i >= configInteger.intValue()) {
                    LOG.d("Call duration of %d is longer than min duration %d for rating.", Integer.valueOf(i), configInteger);
                    return true;
                }
                i2 *= 60;
            }
            LOG.d("Call duration of %d is shorter than min duration %d for rating.", Integer.valueOf(i), configInteger);
            return false;
        }
        GeneratedOutlineSupport.outline3("Invalid call duration pattern : ", str, LOG);
        return false;
    }

    private void startScreenFadeOut(@NonNull Timer timer, long j) {
        timer.schedule(new EndCallScreenTimerTask(this), j);
    }

    @Override // com.amazon.deecomms.calling.controller.EndCallActionContract
    public void cancelTimer() {
        this.mTimer.cancel();
    }

    @Override // com.amazon.deecomms.calling.controller.EndCallActionContract
    public void delayedFinishActivity() {
        startScreenFadeOut(new Timer(), 1000L);
    }

    @Override // com.amazon.deecomms.calling.controller.EndCallActionContract
    public void finishAndRemoveTask() {
        Activity activity = this.mActivity;
        if (activity != null) {
            activity.finishAndRemoveTask();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        String remoteParticipantName;
        boolean shouldShowCallRating;
        super.onCreate(bundle);
        CommsDaggerWrapper.getComponent().inject(this);
        this.mActivity = getActivity();
        Intent intent = this.mActivity.getIntent();
        String stringExtra = intent.hasExtra(Constants.CALL_ID) ? intent.getStringExtra(Constants.CALL_ID) : this.callContext.getCallID();
        String stringExtra2 = intent.hasExtra(Constants.CALL_DURATION_KEY) ? intent.getStringExtra(Constants.CALL_DURATION_KEY) : this.callContext.getCallDuration();
        String stringExtra3 = intent.hasExtra(Constants.CALL_END_STATUS) ? intent.getStringExtra(Constants.CALL_END_STATUS) : this.callContext.getCallEndReason();
        if (intent.hasExtra(Constants.REMOTE_PARTICIPANT_NAME)) {
            remoteParticipantName = CallViewUtils.getRemoteParticipantDisplayName(intent.getExtras(), this.mActivity);
        } else {
            remoteParticipantName = this.callContext.getRemoteParticipantName();
        }
        String str = remoteParticipantName;
        boolean z = false;
        if (intent.hasExtra(Constants.SHOW_CALL_RATING)) {
            shouldShowCallRating = intent.getBooleanExtra(Constants.SHOW_CALL_RATING, false);
        } else {
            shouldShowCallRating = this.callContext.shouldShowCallRating();
        }
        this.mPresenter = new EndCallFragmentPresenter(this, stringExtra, this.mDriveModeSharedPreferencesUseCase);
        if (stringExtra2 != null && isQualifiedCallForRating(stringExtra2) && stringExtra3.equals(Utils.getStringFromResource(R.string.call_end_status_mature))) {
            z = true;
        }
        this.mIsCallSuccess = z;
        this.mPresenter.setCallStats(str, stringExtra2, stringExtra3, shouldShowCallRating, getResources().getString(R.string.rating_prompt), getResources().getString(R.string.thank_you), this.mIsCallSuccess);
        this.callInitiationAuthority.resetPreviousCallState();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        this.mTimer = new Timer();
        startScreenFadeOut(this.mTimer, this.mIsCallSuccess ? 4000L : 1000L);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mCapabilitiesManager.isMosaicThemingEnabled()) {
            inflate = layoutInflater.inflate(R.layout.mosaic_end_call_view, viewGroup, false);
        } else if (this.mCapabilitiesManager.isThemedUIEnabled()) {
            inflate = layoutInflater.inflate(R.layout.fiesta_end_call_view, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.end_call_view, viewGroup, false);
        }
        EndCallFragmentPresenter endCallFragmentPresenter = this.mPresenter;
        endCallFragmentPresenter.setViewHolder(new EndCallFragmentViewHolder(endCallFragmentPresenter, inflate));
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mPresenter.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mPresenter.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mPresenter.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mPresenter.onStop();
    }
}
