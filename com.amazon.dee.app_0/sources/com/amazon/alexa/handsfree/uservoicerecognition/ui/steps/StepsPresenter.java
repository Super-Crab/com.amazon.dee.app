package com.amazon.alexa.handsfree.uservoicerecognition.ui.steps;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceTrainingMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback.UtteranceFeedback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.audio.TrainingSound;
import com.amazon.alexa.handsfree.uservoicerecognition.model.EnrollmentStep;
import com.amazon.alexa.handsfree.uservoicerecognition.model.EnrollmentStepFactory;
import com.amazon.alexa.handsfree.uservoicerecognition.model.errors.StartError;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentResumeChecker;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentResumeStateStore;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsMetricsRecorder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Optional;
/* loaded from: classes8.dex */
public class StepsPresenter implements StepsContract.ViewListener {
    private static final String TAG = "StepsPresenter";
    private int mCurrentStepIndex;
    private final EnrollmentCallback mEnrollmentCallback;
    private final EnrollmentResumeChecker mEnrollmentResumeChecker;
    private EnrollmentResumeStateStore mEnrollmentResumeStateStore;
    private EnrollmentState mEnrollmentState;
    private final boolean mIsEdgeSV;
    private boolean mIsInstructionEmpty;
    private final Resources mResources;
    private boolean mShouldSetViewReadyToListen;
    private boolean mShouldUpdateOnStartRecording;
    private final StepsContract.SoundPlayer mSoundPlayer;
    private final List<EnrollmentStep> mSteps;
    private final StepsMetricsRecorder mStepsMetricsRecorder;
    private final UVREnroller mUVREnroller;
    private final StepsContract.View mView;
    private final StepsContract.VoiceChrome mVoiceChrome;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public enum EnrollmentState {
        IN_PROGRESS,
        PAUSED,
        FINISHED
    }

    @VisibleForTesting
    /* loaded from: classes8.dex */
    class UtteranceCallback implements EnrollmentCallback {
        UtteranceCallback() {
        }

        @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback
        public void onError(@NonNull EnrollmentErrorContract enrollmentErrorContract, @NonNull UtteranceTrainingMetadata utteranceTrainingMetadata) {
            StepsPresenter.this.onUtteranceTrainingError(enrollmentErrorContract);
            StepsPresenter.this.mStepsMetricsRecorder.recordTrainingMetadata(false, utteranceTrainingMetadata, Optional.of(enrollmentErrorContract), StepsPresenter.this.mCurrentStepIndex);
        }

        @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback
        public void onFeedback(@NonNull UtteranceFeedback utteranceFeedback) {
            if (StepsPresenter.this.mCurrentStepIndex == 0 && !StepsPresenter.this.getCurrentStep().hadAnyErrors() && StepsPresenter.this.mShouldSetViewReadyToListen) {
                StepsPresenter.this.mView.readyToListen(StepsPresenter.this.getUtteranceString());
                StepsPresenter.this.mVoiceChrome.setVoiceChromeRecording();
                StepsPresenter.this.mView.setStepState(StepsPresenter.this.mCurrentStepIndex, EnrollmentStep.TrainingState.IN_PROGRESS);
                StepsPresenter.this.mShouldSetViewReadyToListen = false;
                StepsPresenter.this.mShouldUpdateOnStartRecording = false;
            }
            StepsPresenter.this.mVoiceChrome.setVoiceChromeLevelOnFeedback(utteranceFeedback.getScaledVolume());
        }

        @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback
        public void onStartProcessing() {
        }

        @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback
        public void onStartRecording() {
            if (!StepsPresenter.this.mShouldUpdateOnStartRecording) {
                StepsPresenter.this.mShouldUpdateOnStartRecording = true;
                return;
            }
            if (StepsPresenter.this.mCurrentStepIndex != 0 || StepsPresenter.this.getCurrentStep().hadAnyErrors() || !StepsPresenter.this.mShouldSetViewReadyToListen) {
                if (StepsPresenter.this.mIsInstructionEmpty) {
                    StepsPresenter.this.mView.showInstructionAndUtteranceText(StepsPresenter.this.getUtteranceString());
                } else {
                    StepsPresenter.this.mView.showUtteranceText(StepsPresenter.this.getUtteranceString());
                }
            } else {
                StepsPresenter.this.mView.readyToListen(StepsPresenter.this.getUtteranceString());
                StepsPresenter.this.mShouldSetViewReadyToListen = false;
            }
            StepsPresenter.this.mView.setStepState(StepsPresenter.this.mCurrentStepIndex, EnrollmentStep.TrainingState.IN_PROGRESS);
            StepsPresenter.this.mVoiceChrome.setVoiceChromeRecording();
            StepsPresenter.this.mStepsMetricsRecorder.recordLatencyMetric(StepsMetricsRecorder.LatencyMetadata.START_RECORDING_DELAY);
            StepsPresenter.this.mStepsMetricsRecorder.addStartTimestamp(StepsMetricsRecorder.LatencyMetadata.UTTERANCE_RECORDING_LENGTH);
        }

        @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback
        public void onStopProcessing() {
        }

        @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback
        public void onStopRecording() {
            StepsPresenter.this.mVoiceChrome.setVoiceChromeNotRecording();
            StepsPresenter.this.mStepsMetricsRecorder.recordLatencyMetric(StepsMetricsRecorder.LatencyMetadata.UTTERANCE_RECORDING_LENGTH);
        }

        @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback
        public void onSuccess(@NonNull UtteranceTrainingMetadata utteranceTrainingMetadata) {
            StepsPresenter.this.onUtteranceTrainingSuccess(utteranceTrainingMetadata);
        }
    }

    public StepsPresenter(@NonNull StepsContract.View view, @NonNull StepsContract.VoiceChrome voiceChrome, @NonNull StepsContract.SoundPlayer soundPlayer, @NonNull Context context, boolean z) {
        this(view, voiceChrome, soundPlayer, context.getResources(), UVRModule.INSTANCE.getUVRContract().getEnroller(), new EnrollmentStepFactory(), null, new StepsMetricsRecorder(context), new EnrollmentResumeChecker(new EnrollmentResumeChecker.TimeInstantProvider(), new EnrollmentResumeStateStore(context), UVRModule.INSTANCE.getUVRContract().getVendorSettings(), context.getResources().getInteger(R.integer.uvr_resume_allowed_max_period_seconds)), new EnrollmentResumeStateStore(context), z);
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.ViewListener
    @VisibleForTesting
    public void cancelEnrollment() {
        EnrollmentState enrollmentState = this.mEnrollmentState;
        EnrollmentState enrollmentState2 = EnrollmentState.FINISHED;
        if (enrollmentState != enrollmentState2) {
            this.mEnrollmentState = enrollmentState2;
            this.mUVREnroller.cancelUserVoiceEnrollment(new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsPresenter.3
                @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cancelUserVoiceEnrollment onError: ");
                    outline107.append(callbackErrorMetadata.getErrorMessage());
                    Log.d(StepsPresenter.TAG, outline107.toString());
                    StepsPresenter.this.mStepsMetricsRecorder.logUserVoiceEnrollmentError(callbackErrorMetadata, "UVREnrollerCancelEnrollment");
                }

                @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                public void onSuccess() {
                    Log.d(StepsPresenter.TAG, "cancelUserVoiceEnrollment onSuccess");
                }
            });
        }
    }

    @VisibleForTesting
    void finishVoiceEnrollment() {
        this.mEnrollmentState = EnrollmentState.FINISHED;
        this.mUVREnroller.finishUserVoiceEnrollment(new ResultCallback<Double>() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsPresenter.5
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("finishUserVoiceEnrollment onError with reason: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.d(StepsPresenter.TAG, outline107.toString());
                StepsPresenter.this.cancelEnrollment();
                StepsPresenter.this.mView.showNotWorkingDialog(R.string.handle_non_retry_title, R.string.handle_non_retry_msg, "");
                StepsPresenter.this.mStepsMetricsRecorder.recordFinishError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@NonNull Double d) {
                Log.d(StepsPresenter.TAG, "finishUserVoiceEnrollment onSuccess.");
                StepsPresenter.this.mView.onFinishEnrollment();
                StepsPresenter.this.mStepsMetricsRecorder.recordFinishSuccess(d.doubleValue());
            }
        });
    }

    @VisibleForTesting
    EnrollmentStep getCurrentStep() {
        return this.mSteps.get(this.mCurrentStepIndex);
    }

    @VisibleForTesting
    EnrollmentState getEnrollmentState() {
        return this.mEnrollmentState;
    }

    @VisibleForTesting
    List<EnrollmentStep> getEnrollmentSteps() {
        return this.mSteps;
    }

    @VisibleForTesting
    EnrollmentStep getPreviousStep() {
        int i = this.mCurrentStepIndex;
        if (i == 0) {
            return null;
        }
        return this.mSteps.get(i - 1);
    }

    @VisibleForTesting
    String getUtteranceString() {
        return getCurrentStep().getUtteranceInfo().getText();
    }

    @VisibleForTesting
    void goToNextStep() {
        if (this.mCurrentStepIndex < this.mSteps.size() - 1) {
            this.mView.setStepState(this.mCurrentStepIndex, EnrollmentStep.TrainingState.COMPLETE);
            this.mCurrentStepIndex++;
            if (getPreviousStep().hadAnyErrors()) {
                this.mView.removeErrorMessageAndUtteranceText();
                this.mIsInstructionEmpty = true;
            } else {
                this.mView.removeUtteranceText();
                this.mIsInstructionEmpty = false;
            }
            this.mSoundPlayer.playSound(TrainingSound.UTTERANCE_COMPLETED, new StepsContract.SoundPlayer.SoundCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsPresenter.4
                @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.SoundPlayer.SoundCallback
                public void onSilence() {
                    StepsPresenter.this.startUtteranceTraining();
                }
            });
            this.mStepsMetricsRecorder.recordNextStepShown(this.mCurrentStepIndex);
        }
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.ViewListener
    public void initializeView() {
        if (this.mSteps.isEmpty()) {
            this.mView.showNotWorkingDialog(StartError.CONNECTION.getReasonRes());
            this.mStepsMetricsRecorder.recordTerminalError(0);
            this.mEnrollmentState = EnrollmentState.FINISHED;
            Log.e(TAG, "The list of utterances cannot be empty.");
            return;
        }
        this.mView.initializeSteps(this.mSteps.size());
        this.mVoiceChrome.showVoiceChrome();
        this.mView.showLoadingBeforeVoiceTraining();
        this.mSoundPlayer.playSound(TrainingSound.NEW_UTTERANCE, new StepsContract.SoundPlayer.SoundCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsPresenter.2
            @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.SoundPlayer.SoundCallback
            public void onSilence() {
                StepsPresenter.this.startUtteranceTraining();
            }
        });
        this.mStepsMetricsRecorder.recordViewInitialized();
    }

    @VisibleForTesting
    boolean isEnrollmentComplete() {
        return ((EnrollmentStep) GeneratedOutlineSupport1.outline25(this.mSteps, 1)).getTrainingState() == EnrollmentStep.TrainingState.COMPLETE;
    }

    @VisibleForTesting
    void onUtteranceTrainingError(@NonNull EnrollmentErrorContract enrollmentErrorContract) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onUtteranceTrainingError with error: ");
        outline107.append(enrollmentErrorContract.getName());
        Log.d(TAG, outline107.toString());
        if (this.mEnrollmentState == EnrollmentState.PAUSED) {
            return;
        }
        EnrollmentStep currentStep = getCurrentStep();
        currentStep.onTrainingError();
        if (currentStep.hasExceededMaxAllowedErrors()) {
            cancelEnrollment();
            this.mView.showNotWorkingDialog();
            this.mStepsMetricsRecorder.recordMaxErrorReached(this.mCurrentStepIndex);
        } else if (enrollmentErrorContract.isTerminalError()) {
            cancelEnrollment();
            this.mView.showNotWorkingDialog(enrollmentErrorContract.getReasonTitleRes(), enrollmentErrorContract.getReasonRes(), enrollmentErrorContract.getErrorCode());
            this.mStepsMetricsRecorder.recordTerminalError(this.mCurrentStepIndex);
        } else {
            currentStep.setTrainingState(EnrollmentStep.TrainingState.ERROR);
            currentStep.setHadAnyErrors();
            this.mView.setStepState(this.mCurrentStepIndex, EnrollmentStep.TrainingState.ERROR);
            int reasonRes = enrollmentErrorContract.getReasonRes();
            if (reasonRes == 0) {
                this.mView.showAnimatedErrorText(enrollmentErrorContract.getName());
            } else {
                this.mView.showAnimatedErrorText(reasonRes);
            }
            this.mIsInstructionEmpty = false;
            this.mSoundPlayer.playSound(TrainingSound.UTTERANCE_ERROR, null);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsPresenter.6
                @Override // java.lang.Runnable
                public void run() {
                    StepsPresenter.this.startUtteranceTraining();
                }
            }, this.mResources.getInteger(R.integer.uvr_error_icon_duration));
        }
    }

    @VisibleForTesting
    void onUtteranceTrainingSuccess(@NonNull UtteranceTrainingMetadata utteranceTrainingMetadata) {
        Log.d(TAG, "onUtteranceTrainingSuccess.");
        this.mStepsMetricsRecorder.recordTrainingMetadata(true, utteranceTrainingMetadata, Optional.empty(), this.mCurrentStepIndex);
        if (this.mEnrollmentState == EnrollmentState.PAUSED) {
            return;
        }
        getCurrentStep().setTrainingState(EnrollmentStep.TrainingState.COMPLETE);
        if (isEnrollmentComplete()) {
            this.mView.setStepState(this.mSteps.size() - 1, EnrollmentStep.TrainingState.COMPLETE);
            this.mSoundPlayer.playSound(TrainingSound.UTTERANCE_COMPLETED, null);
            this.mVoiceChrome.dismissVoiceChrome(this);
            return;
        }
        goToNextStep();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.ViewListener
    public void onVoiceChromeDismissed() {
        this.mView.showLoadingAfterVoiceTraining();
        finishVoiceEnrollment();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.ViewListener
    public void pauseEnrollment() {
        if (!this.mIsEdgeSV && this.mEnrollmentState == EnrollmentState.IN_PROGRESS) {
            this.mUVREnroller.cancelUtteranceTraining(new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsPresenter.1
                @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cancelUtteranceTraining onError: ");
                    outline107.append(callbackErrorMetadata.getErrorMessage());
                    Log.e(StepsPresenter.TAG, outline107.toString());
                    StepsPresenter.this.mStepsMetricsRecorder.logUserVoiceEnrollmentError(callbackErrorMetadata, "UVREnrollerCancelUtteranceTraining");
                }

                @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                public void onSuccess() {
                    Log.d(StepsPresenter.TAG, "cancelUtteranceTraining onSuccess");
                }
            });
            this.mEnrollmentState = EnrollmentState.PAUSED;
            this.mEnrollmentResumeChecker.resetResumeClock();
        }
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.ViewListener
    public void resumeEnrollment() {
        Log.d(TAG, "resume enrollment called");
        if (!this.mIsEdgeSV && this.mEnrollmentState == EnrollmentState.PAUSED) {
            if (this.mEnrollmentResumeChecker.canResumeEnrollment()) {
                Log.d(TAG, "Can resume enrollment");
                startUtteranceTraining();
                this.mEnrollmentState = EnrollmentState.IN_PROGRESS;
                return;
            }
            cancelEnrollment();
            this.mView.restartTraining();
        }
    }

    @VisibleForTesting
    void startUtteranceTraining() {
        Log.i(TAG, "utterance training starting");
        this.mUVREnroller.startUtteranceTraining(this.mSteps.get(this.mCurrentStepIndex).getUtteranceInfo(), this.mEnrollmentCallback);
        this.mStepsMetricsRecorder.addStartTimestamp(StepsMetricsRecorder.LatencyMetadata.START_RECORDING_DELAY);
    }

    @VisibleForTesting
    StepsPresenter(@NonNull StepsContract.View view, @NonNull StepsContract.VoiceChrome voiceChrome, @NonNull StepsContract.SoundPlayer soundPlayer, @NonNull Resources resources, @NonNull UVREnroller uVREnroller, @NonNull EnrollmentStepFactory enrollmentStepFactory, @Nullable EnrollmentCallback enrollmentCallback, @NonNull StepsMetricsRecorder stepsMetricsRecorder, @NonNull EnrollmentResumeChecker enrollmentResumeChecker, @NonNull EnrollmentResumeStateStore enrollmentResumeStateStore, boolean z) {
        this.mCurrentStepIndex = 0;
        this.mEnrollmentState = EnrollmentState.IN_PROGRESS;
        this.mIsInstructionEmpty = false;
        this.mShouldSetViewReadyToListen = true;
        this.mShouldUpdateOnStartRecording = true;
        this.mResources = resources;
        this.mView = view;
        this.mVoiceChrome = voiceChrome;
        this.mSoundPlayer = soundPlayer;
        this.mUVREnroller = uVREnroller;
        this.mEnrollmentResumeChecker = enrollmentResumeChecker;
        this.mStepsMetricsRecorder = stepsMetricsRecorder;
        this.mEnrollmentResumeStateStore = enrollmentResumeStateStore;
        this.mIsEdgeSV = z;
        this.mSteps = enrollmentStepFactory.createEnrollmentSteps(this.mUVREnroller.getUtterances(), this.mResources.getInteger(R.integer.uvr_max_allowed_training_errors));
        if (enrollmentCallback == null) {
            this.mEnrollmentCallback = new UtteranceCallback();
        } else {
            this.mEnrollmentCallback = enrollmentCallback;
        }
    }
}
