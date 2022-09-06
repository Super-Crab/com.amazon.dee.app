package com.amazon.alexa.enrollment.ui.training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import com.amazon.alexa.enrollment.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.api.model.EnrollmentConfiguration;
import com.amazon.alexa.enrollment.api.model.EnrollmentMetadata;
import com.amazon.alexa.enrollment.api.model.EnrollmentStates;
import com.amazon.alexa.enrollment.api.model.GetVoiceEnrollmentStatusResponse;
import com.amazon.alexa.enrollment.api.model.StartVoiceEnrollmentResponse;
import com.amazon.alexa.enrollment.api.model.VoiceEnrollmentGUIConfiguration;
import com.amazon.alexa.enrollment.api.model.VoiceEnrollmentStatus;
import com.amazon.alexa.enrollment.exception.HandleSilenceException;
import com.amazon.alexa.enrollment.exception.InvalidEnrollmentStatusException;
import com.amazon.alexa.enrollment.exception.NonRetryableException;
import com.amazon.alexa.enrollment.exception.NonRetryableQualityFailureException;
import com.amazon.alexa.enrollment.exception.NonRetryableSilenceException;
import com.amazon.alexa.enrollment.exception.RetryableQualityFailureException;
import com.amazon.alexa.enrollment.exception.UserCancelledException;
import com.amazon.alexa.enrollment.exception.UserIneligibleToEnrollException;
import com.amazon.alexa.enrollment.exception.VoiceProfileAlreadyExistsException;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.enrollment.ui.complete.EnrollmentCompleteActivity;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionActivity;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentIntroductionActivity;
import com.amazon.alexa.enrollment.utils.ActivityConstants;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.amazon.alexa.voice.ui.VoiceActivity;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentTrainingViewModel {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentTrainingViewModel.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    public static final int USER_SPEECH_PROVIDER_STATUS_CHECK_DELAY = 500;
    private final Context context;
    private EnrollmentTrainingUiModel currentUiModel;
    private final EnrollmentAPI enrollmentAPI;
    private final EnrollmentMetricsRecorder metricsRecorder;
    private Integer retryCount;
    private EnrollmentConfiguration apiConfiguration = EnrollmentConfiguration.getInstance();
    private boolean isEligibleForUserSpeechProvider = false;
    @VisibleForTesting
    public boolean userSpeechProviderStatusCheckDelayExecuted = false;

    @Inject
    public EnrollmentTrainingViewModel(Context context, EnrollmentAPI enrollmentAPI, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        this.context = context;
        this.enrollmentAPI = enrollmentAPI;
        this.metricsRecorder = enrollmentMetricsRecorder;
        resetEnrollmentSession();
    }

    private void extractError(EnrollmentStates enrollmentStates) {
        Log.i(TAG, "Inside extract error");
        this.metricsRecorder.recordCounter(enrollmentStates.name());
        if (enrollmentStates != EnrollmentStates.QC_FAILURE_NON_TERMINAL) {
            if (enrollmentStates != EnrollmentStates.QC_FAILURE_TERMINAL) {
                if (enrollmentStates != EnrollmentStates.SILENCE_TERMINAL) {
                    if (enrollmentStates != EnrollmentStates.NON_RETRIABLE) {
                        if (enrollmentStates != EnrollmentStates.SILENCE_NON_TERMINAL) {
                            if (enrollmentStates != EnrollmentStates.INELIGIBLE) {
                                if (enrollmentStates != EnrollmentStates.CANCELLED) {
                                    if (enrollmentStates == EnrollmentStates.INELIGIBLE_PERSON_ALREADY_ENROLLED) {
                                        throw new VoiceProfileAlreadyExistsException("Voice profile already exists");
                                    }
                                    return;
                                }
                                throw new UserCancelledException("User cancelled");
                            }
                            throw new UserIneligibleToEnrollException("Server returned user ineligible");
                        }
                        throw new HandleSilenceException("Server returned silence exception");
                    }
                    throw new NonRetryableException("Non retryable exception returned by server");
                }
                throw new NonRetryableSilenceException("Non retryable silence exception");
            }
            throw new NonRetryableQualityFailureException("Non retryable quality failure");
        }
        throw new RetryableQualityFailureException("Server returned non terminal failure");
    }

    private EnrollmentTrainingUiModel transform(VoiceEnrollmentStatus voiceEnrollmentStatus, boolean z) throws Exception {
        String str = TAG;
        Log.i(str, "Inside transform for get voice enrollment status response" + voiceEnrollmentStatus);
        int currentTrainingPhraseIndex = voiceEnrollmentStatus.getCurrentTrainingPhraseIndex();
        EnrollmentStates enrollmentState = voiceEnrollmentStatus.getEnrollmentState();
        EnrollmentTrainingUiModel enrollmentTrainingUiModel = new EnrollmentTrainingUiModel(voiceEnrollmentStatus.getEnrollmentTrainingPhrases(), Integer.valueOf(currentTrainingPhraseIndex), enrollmentState, voiceEnrollmentStatus.getLastUpdatedTime(), z);
        EnrollmentMetricsRecorder enrollmentMetricsRecorder = this.metricsRecorder;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ENROLLMENT_STATUS_");
        outline107.append(enrollmentState.name());
        outline107.append("_");
        outline107.append(currentTrainingPhraseIndex);
        enrollmentMetricsRecorder.recordCounter(outline107.toString());
        updateEnrollmentUiModel(enrollmentTrainingUiModel);
        EnrollmentMetricsRecorder enrollmentMetricsRecorder2 = this.metricsRecorder;
        enrollmentMetricsRecorder2.recordCounter("TRAINING_INDEX_" + currentTrainingPhraseIndex);
        return enrollmentTrainingUiModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: transformStartEnrollmentResponse */
    public EnrollmentTrainingUiModel lambda$refreshUiModel$0$EnrollmentTrainingViewModel(StartVoiceEnrollmentResponse startVoiceEnrollmentResponse) throws Exception {
        String str = TAG;
        Log.i(str, "Inside transform for start voice enrollment response " + startVoiceEnrollmentResponse);
        updateEnrollmentStatusAPIConfig(startVoiceEnrollmentResponse.getVoiceEnrollmentGUIConfiguration());
        return transform(startVoiceEnrollmentResponse.getVoiceEnrollmentStatus(), false);
    }

    private void updateEnrollmentStatusAPIConfig(VoiceEnrollmentGUIConfiguration voiceEnrollmentGUIConfiguration) {
        if (voiceEnrollmentGUIConfiguration == null || !voiceEnrollmentGUIConfiguration.getIsGuiEnrollmentEnabled()) {
            return;
        }
        this.apiConfiguration.updateConfig(voiceEnrollmentGUIConfiguration);
    }

    public EnrollmentMetricsRecorder getMetricsRecorder() {
        return this.metricsRecorder;
    }

    public EnrollmentTrainingUiModel getUiModel() {
        return this.currentUiModel;
    }

    public boolean isEligibleForUserSpeechProvider() {
        return this.isEligibleForUserSpeechProvider;
    }

    public /* synthetic */ void lambda$refreshUiModel$1$EnrollmentTrainingViewModel(Integer num) throws Throwable {
        this.userSpeechProviderStatusCheckDelayExecuted = true;
    }

    public /* synthetic */ SingleSource lambda$refreshUiModel$2$EnrollmentTrainingViewModel(EnrollmentMetadata enrollmentMetadata, Integer num) throws Throwable {
        return this.enrollmentAPI.getVoiceEnrollmentStatus(enrollmentMetadata);
    }

    public /* synthetic */ EnrollmentTrainingUiModel lambda$refreshUiModel$3$EnrollmentTrainingViewModel(GetVoiceEnrollmentStatusResponse getVoiceEnrollmentStatusResponse) throws Throwable {
        return transform(getVoiceEnrollmentStatusResponse.getVoiceEnrollmentStatus(), true);
    }

    public /* synthetic */ EnrollmentTrainingUiModel lambda$refreshUiModel$4$EnrollmentTrainingViewModel(GetVoiceEnrollmentStatusResponse getVoiceEnrollmentStatusResponse) throws Throwable {
        return transform(getVoiceEnrollmentStatusResponse.getVoiceEnrollmentStatus(), true);
    }

    public void moveToCompletionScreen(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment) {
        Log.i(TAG, "Starting enrollment complete screen");
        if (abstractEnrollmentViewFragment.getActivity() == null) {
            Log.w(TAG, "activity provided to moveToCompletionScreen is null");
            this.metricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.TRAINING_ACTIVITY_IS_NULL);
            return;
        }
        moveToScreen(abstractEnrollmentViewFragment.getActivity(), EnrollmentCompleteActivity.class);
    }

    public void moveToIntroductionScreen(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment) {
        if (abstractEnrollmentViewFragment.getActivity() == null) {
            Log.w(TAG, "activity provided to moveToIntroductionScreen is null");
            this.metricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.TRAINING_ACTIVITY_IS_NULL);
        } else if (ActivityConstants.KIDS_ENROLLMENT_CONTEXT.equals(ActivityUtils.getEnrollmentContext(abstractEnrollmentViewFragment.getActivity()))) {
            Log.i(TAG, "move to kids enrollment primer screen");
            moveToScreen(abstractEnrollmentViewFragment.getActivity(), KidsEnrollmentIntroductionActivity.class);
        } else {
            Log.i(TAG, "moveToIntroduction screen");
            moveToScreen(abstractEnrollmentViewFragment.getActivity(), EnrollmentIntroductionActivity.class);
        }
    }

    @VisibleForTesting
    void moveToScreen(Activity activity, Class cls) {
        String enrollmentContext = ActivityUtils.getEnrollmentContext(activity);
        String str = TAG;
        Log.i(str, "moveToIntroduction screen for context: " + enrollmentContext);
        Intent intent = new Intent(activity, cls);
        intent.putExtras(activity.getIntent());
        if (ActivityConstants.KIDS_ENROLLMENT_CONTEXT.equals(enrollmentContext)) {
            intent.putExtra(ActivityConstants.CONSENT_COLLECTED_TOAST, false);
        }
        activity.startActivity(intent);
        activity.finish();
    }

    @VisibleForTesting
    void openActivity(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, Intent intent) {
        FragmentActivity activity = abstractEnrollmentViewFragment.getActivity();
        if (activity == null) {
            Log.w(TAG, "activity provided to openActivity is null");
            this.metricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.TRAINING_ACTIVITY_IS_NULL);
            return;
        }
        activity.startActivityForResult(intent, 1);
    }

    public Single<EnrollmentTrainingUiModel> refreshUiModel(final EnrollmentMetadata enrollmentMetadata) {
        if (this.currentUiModel.getCurrentState() == EnrollmentStates.NOT_STARTED) {
            Log.i(TAG, "Starting voice enrollment");
            return this.enrollmentAPI.startEnrollment(enrollmentMetadata).map(new Function() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$EnrollmentTrainingViewModel$i482K0ROSwEJDQ5r87ahdExHNZE
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return EnrollmentTrainingViewModel.this.lambda$refreshUiModel$0$EnrollmentTrainingViewModel((StartVoiceEnrollmentResponse) obj);
                }
            });
        }
        Log.i(TAG, "Get voice enrollment status");
        if (isEligibleForUserSpeechProvider()) {
            return Single.just(1).delay(500L, TimeUnit.MILLISECONDS).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$EnrollmentTrainingViewModel$S8xH2Z4l_lprKEnr478pNVDJ6DE
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EnrollmentTrainingViewModel.this.lambda$refreshUiModel$1$EnrollmentTrainingViewModel((Integer) obj);
                }
            }).flatMap(new Function() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$EnrollmentTrainingViewModel$m5acbcLqHWGcrIBSjZVSRJHAJxg
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return EnrollmentTrainingViewModel.this.lambda$refreshUiModel$2$EnrollmentTrainingViewModel(enrollmentMetadata, (Integer) obj);
                }
            }).map(new Function() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$EnrollmentTrainingViewModel$j3L9-vh9jW0giQNXYkJ0hc7v5OQ
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return EnrollmentTrainingViewModel.this.lambda$refreshUiModel$3$EnrollmentTrainingViewModel((GetVoiceEnrollmentStatusResponse) obj);
                }
            });
        }
        return this.enrollmentAPI.getVoiceEnrollmentStatus(enrollmentMetadata).map(new Function() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$EnrollmentTrainingViewModel$rcm70eJMYxjYLGWmspD68u5_3TQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return EnrollmentTrainingViewModel.this.lambda$refreshUiModel$4$EnrollmentTrainingViewModel((GetVoiceEnrollmentStatusResponse) obj);
            }
        });
    }

    public void resetEnrollmentSession() {
        this.retryCount = 0;
        this.currentUiModel = new EnrollmentTrainingUiModel(new ArrayList(), -1, EnrollmentStates.NOT_STARTED, -1L, false);
        this.apiConfiguration.resetConfig();
    }

    public void setEligibleForUserSpeechProvider(boolean z) {
        this.isEligibleForUserSpeechProvider = z;
    }

    public boolean startVoiceActivity(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment) {
        if (!abstractEnrollmentViewFragment.isActivityInForeground()) {
            Log.w(TAG, "Activity is not in foreground, so not starting voice activity");
            return false;
        }
        this.metricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.STARTING_VOICE_ACTIVITY);
        Log.i(TAG, "Starting voice activity to capture enrollment training utterences");
        Intent intent = new Intent(VoiceActivity.ACTION_ALEXA_LISTEN_ENROLLMENT);
        intent.putExtra("scrim_hide_cancel_button", true);
        intent.putExtra("scrim_tansparent_background", true);
        intent.putExtra(VoiceActivity.EXTRA_SKIP_FTUE_FLOW, true);
        openActivity(abstractEnrollmentViewFragment, intent);
        return true;
    }

    public void updateEnrollmentUiModel(EnrollmentTrainingUiModel enrollmentTrainingUiModel) throws Exception {
        EnrollmentTrainingUiModel enrollmentTrainingUiModel2 = this.currentUiModel;
        this.currentUiModel = enrollmentTrainingUiModel;
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Previous updated time: ");
        outline107.append(enrollmentTrainingUiModel2.getLastUpdatedTime());
        outline107.append(" current updated time: ");
        outline107.append(enrollmentTrainingUiModel.getLastUpdatedTime());
        Log.i(str, outline107.toString());
        if (enrollmentTrainingUiModel2.getLastUpdatedTime() < enrollmentTrainingUiModel.getLastUpdatedTime()) {
            extractError(enrollmentTrainingUiModel.getCurrentState());
            if (enrollmentTrainingUiModel2.getCurrentTrainingIndex().intValue() == enrollmentTrainingUiModel.getCurrentTrainingIndex().intValue()) {
                this.retryCount = Integer.valueOf(this.retryCount.intValue() + 1);
            } else {
                this.retryCount = 0;
            }
            if (this.retryCount.intValue() < this.apiConfiguration.getMaxRetriesPerTrainingPhrase()) {
                return;
            }
            this.metricsRecorder.recordCounter("TRAINING_RETRY_COUNT_EXCEEDED");
            throw new NonRetryableException("Exceeded max retry attempt. Throwing exception", null);
        }
        this.metricsRecorder.recordCounter("SERVER_TIME_NOT_UPDATED");
        EnrollmentMetricsRecorder enrollmentMetricsRecorder = this.metricsRecorder;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SERVER_TIME_NOT_UPDATED_");
        outline1072.append(enrollmentTrainingUiModel.getCurrentTrainingIndex());
        enrollmentMetricsRecorder.recordCounter(outline1072.toString());
        EnrollmentMetricsRecorder enrollmentMetricsRecorder2 = this.metricsRecorder;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SERVER_TIME_NOT_UPDATED_ENROLLMENT_STATUS_");
        outline1073.append(enrollmentTrainingUiModel.getCurrentState());
        outline1073.append("_");
        outline1073.append(enrollmentTrainingUiModel.getCurrentTrainingIndex());
        enrollmentMetricsRecorder2.recordCounter(outline1073.toString());
        throw new InvalidEnrollmentStatusException("Invalid enrollment status", null);
    }
}
