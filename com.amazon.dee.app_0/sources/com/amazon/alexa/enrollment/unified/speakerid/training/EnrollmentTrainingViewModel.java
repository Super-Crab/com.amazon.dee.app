package com.amazon.alexa.enrollment.unified.speakerid.training;

import com.amazon.alexa.enrollment.unified.speakerid.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentConfiguration;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentMetadata;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentStates;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.GetVoiceEnrollmentStatusResponse;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.StartVoiceEnrollmentResponse;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.VoiceEnrollmentGUIConfiguration;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.VoiceEnrollmentStatus;
import com.amazon.alexa.enrollment.unified.speakerid.error.HandleSilenceException;
import com.amazon.alexa.enrollment.unified.speakerid.error.InvalidEnrollmentStatusException;
import com.amazon.alexa.enrollment.unified.speakerid.error.NonRetryableException;
import com.amazon.alexa.enrollment.unified.speakerid.error.NonRetryableQualityFailureException;
import com.amazon.alexa.enrollment.unified.speakerid.error.NonRetryableSilenceException;
import com.amazon.alexa.enrollment.unified.speakerid.error.RetryableQualityFailureException;
import com.amazon.alexa.enrollment.unified.speakerid.error.UserCancelledException;
import com.amazon.alexa.enrollment.unified.speakerid.error.UserIneligibleToEnrollException;
import com.amazon.alexa.enrollment.unified.speakerid.error.VoiceProfileAlreadyExistsException;
import com.amazon.alexa.enrollment.unified.speakerid.metrics.AmpdMetricsRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentTrainingViewModel {
    private static final String TAG = "EnrollmentTrainingViewModel";
    private static final int USER_SPEECH_PROVIDER_STATUS_CHECK_DELAY = 500;
    private EnrollmentTrainingUiModel currentUiModel;
    private final EnrollmentAPI enrollmentAPI;
    private final AmpdMetricsRecorder metricsRecorder;
    private Integer retryCount;
    private EnrollmentConfiguration apiConfiguration = EnrollmentConfiguration.getInstance();
    private boolean userSpeechProviderStatusCheckDelayExecuted = false;

    @Inject
    public EnrollmentTrainingViewModel(EnrollmentAPI enrollmentAPI, AmpdMetricsRecorder ampdMetricsRecorder) {
        this.enrollmentAPI = enrollmentAPI;
        this.metricsRecorder = ampdMetricsRecorder;
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

    private void resetEnrollmentSession() {
        this.retryCount = 0;
        this.currentUiModel = new EnrollmentTrainingUiModel(new ArrayList(), -1, EnrollmentStates.NOT_STARTED, -1L, false);
        this.apiConfiguration.resetConfig();
    }

    private EnrollmentTrainingUiModel transform(VoiceEnrollmentStatus voiceEnrollmentStatus, boolean z) throws Exception {
        String str = TAG;
        Log.i(str, "Inside transform for get voice enrollment status response" + voiceEnrollmentStatus);
        int currentTrainingPhraseIndex = voiceEnrollmentStatus.getCurrentTrainingPhraseIndex();
        EnrollmentStates enrollmentState = voiceEnrollmentStatus.getEnrollmentState();
        EnrollmentTrainingUiModel enrollmentTrainingUiModel = new EnrollmentTrainingUiModel(voiceEnrollmentStatus.getEnrollmentTrainingPhrases(), Integer.valueOf(currentTrainingPhraseIndex), enrollmentState, voiceEnrollmentStatus.getLastUpdatedTime(), z);
        AmpdMetricsRecorder ampdMetricsRecorder = this.metricsRecorder;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ENROLLMENT_STATUS_");
        outline107.append(enrollmentState.name());
        outline107.append("_");
        outline107.append(currentTrainingPhraseIndex);
        ampdMetricsRecorder.recordCounter(outline107.toString());
        updateEnrollmentUiModel(enrollmentTrainingUiModel);
        AmpdMetricsRecorder ampdMetricsRecorder2 = this.metricsRecorder;
        ampdMetricsRecorder2.recordCounter("TRAINING_INDEX_" + currentTrainingPhraseIndex);
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

    public EnrollmentTrainingUiModel getUiModel() {
        return this.currentUiModel;
    }

    public /* synthetic */ EnrollmentTrainingUiModel lambda$refreshUiModel$1$EnrollmentTrainingViewModel(GetVoiceEnrollmentStatusResponse getVoiceEnrollmentStatusResponse) throws Throwable {
        return transform(getVoiceEnrollmentStatusResponse.getVoiceEnrollmentStatus(), true);
    }

    public Single<EnrollmentTrainingUiModel> refreshUiModel(EnrollmentMetadata enrollmentMetadata) {
        if (this.currentUiModel.getCurrentState() == EnrollmentStates.NOT_STARTED) {
            Log.i(TAG, "Starting voice enrollment");
            return this.enrollmentAPI.startEnrollment(enrollmentMetadata).map(new Function() { // from class: com.amazon.alexa.enrollment.unified.speakerid.training.-$$Lambda$EnrollmentTrainingViewModel$kqnt6WdSCvwGRrJ3zDQnTAa6-Gw
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return EnrollmentTrainingViewModel.this.lambda$refreshUiModel$0$EnrollmentTrainingViewModel((StartVoiceEnrollmentResponse) obj);
                }
            });
        }
        Log.i(TAG, "Get voice enrollment status");
        return this.enrollmentAPI.getVoiceEnrollmentStatus(enrollmentMetadata).map(new Function() { // from class: com.amazon.alexa.enrollment.unified.speakerid.training.-$$Lambda$EnrollmentTrainingViewModel$Y6Dn5VyEef1ApBxTymW9bOZYNIA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return EnrollmentTrainingViewModel.this.lambda$refreshUiModel$1$EnrollmentTrainingViewModel((GetVoiceEnrollmentStatusResponse) obj);
            }
        });
    }

    void updateEnrollmentUiModel(EnrollmentTrainingUiModel enrollmentTrainingUiModel) throws Exception {
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
        AmpdMetricsRecorder ampdMetricsRecorder = this.metricsRecorder;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SERVER_TIME_NOT_UPDATED_");
        outline1072.append(enrollmentTrainingUiModel.getCurrentTrainingIndex());
        ampdMetricsRecorder.recordCounter(outline1072.toString());
        AmpdMetricsRecorder ampdMetricsRecorder2 = this.metricsRecorder;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SERVER_TIME_NOT_UPDATED_ENROLLMENT_STATUS_");
        outline1073.append(enrollmentTrainingUiModel.getCurrentState());
        outline1073.append("_");
        outline1073.append(enrollmentTrainingUiModel.getCurrentTrainingIndex());
        ampdMetricsRecorder2.recordCounter(outline1073.toString());
        throw new InvalidEnrollmentStatusException("Invalid enrollment status", null);
    }
}
