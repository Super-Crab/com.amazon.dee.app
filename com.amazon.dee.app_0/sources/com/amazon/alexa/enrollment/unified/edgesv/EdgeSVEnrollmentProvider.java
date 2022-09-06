package com.amazon.alexa.enrollment.unified.edgesv;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.enrollment.unified.api.EnrollmentProvider;
import com.amazon.alexa.enrollment.unified.api.StopRecordingListener;
import com.amazon.alexa.enrollment.unified.model.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.WakeWordData;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentController;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes7.dex */
public class EdgeSVEnrollmentProvider implements EnrollmentProvider, StopRecordingListener, EnrollmentListener.UtteranceTrainingCallback {
    private static final String ALEXA_WAKE_WORD = "Alexa";
    private static final String TAG = "EdgeSVEnrollmentProvider";
    private static final long WAKE_WORD_DETECTION_TIMEOUT = 8000;
    private final ScheduledExecutorService mDetectionTimeoutExecutorService;
    private ScheduledFuture<?> mDetectionTimeoutFuture;
    private final EdgeSVEnrollmentMetricsReporter mEdgeSVEnrollmentMetricsReporter;
    private final AtomicReference<EnrollmentProvider.UtteranceTrainingCallback> mEnrollmentCallback;
    private final EnrollmentController mEnrollmentController;
    private final EnrollmentStateProvider mEnrollmentStateProvider;
    private final HandsFreeUserIdentity mHandsFreeUser;
    private boolean mIsSpeakerIDPreviouslyEnrolled;
    private final AtomicReference<TrainingFailure> mTrainingFailure;
    private final AtomicReference<WakeWordData> mWakeWordData;

    public EdgeSVEnrollmentProvider(@NonNull Context context, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull EnrollmentStateProvider enrollmentStateProvider) {
        this.mEnrollmentCallback = new AtomicReference<>();
        this.mTrainingFailure = new AtomicReference<>();
        this.mWakeWordData = new AtomicReference<>();
        this.mIsSpeakerIDPreviouslyEnrolled = false;
        this.mEnrollmentStateProvider = enrollmentStateProvider;
        this.mEnrollmentController = new EnrollmentController(context, alexaServicesConnection, speakerVerificationMetricsListener, wakeWordDetectionMetricsListener, this);
        this.mEdgeSVEnrollmentMetricsReporter = new EdgeSVEnrollmentMetricsReporter(context);
        this.mDetectionTimeoutExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.mHandsFreeUser = handsFreeUserIdentity;
    }

    private void invokeSuccess() {
        if (this.mEnrollmentCallback.get() != null) {
            Log.d(TAG, "Callback is present");
            this.mEnrollmentStateProvider.setCurrentWakeWordData(this.mWakeWordData.getAndSet(null));
            this.mEnrollmentCallback.getAndSet(null).onSuccess();
        }
    }

    private boolean is1PSVVisualFocus() {
        return this.mHandsFreeUser.hasComponent(HandsFreeComponent.EDGESV_VISUAL_FOCUS);
    }

    private void reset() {
        Log.d(TAG, "reset");
        this.mEnrollmentCallback.set(null);
        this.mTrainingFailure.set(null);
        this.mWakeWordData.set(null);
        this.mEnrollmentStateProvider.setCurrentWakeWordData(null);
    }

    private void startDetectionTimer() {
        if (this.mIsSpeakerIDPreviouslyEnrolled || is1PSVVisualFocus()) {
            ScheduledFuture<?> scheduledFuture = this.mDetectionTimeoutFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            this.mDetectionTimeoutFuture = this.mDetectionTimeoutExecutorService.schedule(new Runnable() { // from class: com.amazon.alexa.enrollment.unified.edgesv.-$$Lambda$EdgeSVEnrollmentProvider$mlG4NLb0mSffb89Tz3d38m1uDU8
                @Override // java.lang.Runnable
                public final void run() {
                    EdgeSVEnrollmentProvider.this.lambda$startDetectionTimer$0$EdgeSVEnrollmentProvider();
                }
            }, WAKE_WORD_DETECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        }
    }

    private void stopDetectionTimer() {
        ScheduledFuture<?> scheduledFuture = this.mDetectionTimeoutFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void cancelUserVoiceEnrollment(@NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "cancelUserVoiceEnrollment");
        reset();
        this.mEnrollmentController.release();
        stopDetectionTimer();
        responseCallback.onSuccess();
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void cancelUtteranceTraining(@NonNull ResponseCallback responseCallback) {
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void finishUserVoiceEnrollment(@NonNull final ResponseCallback responseCallback) {
        Log.d(TAG, "finishUserVoiceEnrollment");
        reset();
        stopDetectionTimer();
        this.mEnrollmentController.persistData(new EnrollmentListener.ResponseCallback() { // from class: com.amazon.alexa.enrollment.unified.edgesv.EdgeSVEnrollmentProvider.2
            @Override // com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener.ResponseCallback
            public void onFailure(@NonNull TrainingFailure trainingFailure) {
                String str = EdgeSVEnrollmentProvider.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("finishUserVoiceEnrollment onFailure: ");
                outline107.append(trainingFailure.name());
                Log.d(str, outline107.toString());
                EdgeSVEnrollmentProvider.this.mEdgeSVEnrollmentMetricsReporter.reportFinishEnrollmentFailure();
                EdgeSVEnrollmentProvider.this.mEdgeSVEnrollmentMetricsReporter.reportFinishEnrollmentFailureReason(trainingFailure);
                EdgeSVEnrollmentProvider.this.mEnrollmentController.release();
                responseCallback.onError(new CallbackErrorMetadata(trainingFailure.name()));
            }

            @Override // com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener.ResponseCallback
            public void onSuccess() {
                Log.d(EdgeSVEnrollmentProvider.TAG, "finishUserVoiceEnrollment onSuccess");
                EdgeSVEnrollmentProvider.this.mEdgeSVEnrollmentMetricsReporter.reportFinishEnrollmentSuccess();
                EdgeSVEnrollmentProvider.this.mEnrollmentController.release();
                responseCallback.onSuccess();
            }
        });
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    @NonNull
    public List<String> getUtterances() {
        Log.d(TAG, "getUtterances");
        return Collections.nCopies(this.mEnrollmentStateProvider.getNumUtterances(), "Alexa");
    }

    public /* synthetic */ void lambda$startDetectionTimer$0$EdgeSVEnrollmentProvider() {
        onUtteranceTrainingFailure(TrainingFailure.WAKE_WORD_NOT_DETECTED);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener.UtteranceTrainingCallback
    public void onAudioCaptured(@NonNull short[] sArr) {
        if (this.mEnrollmentCallback.get() != null) {
            this.mEnrollmentCallback.get().onAudioCaptured(sArr);
        }
    }

    @Override // com.amazon.alexa.enrollment.unified.api.StopRecordingListener
    public void onStopRecording() {
        Log.d(TAG, "onStopRecording");
        this.mEnrollmentController.stopRecording();
        this.mEnrollmentController.startUtteranceTraining();
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener.UtteranceTrainingCallback
    public void onUtteranceTrainingFailure(@NonNull TrainingFailure trainingFailure) {
        Log.d(TAG, "onUtteranceTrainingFailure");
        this.mEdgeSVEnrollmentMetricsReporter.reportUtteranceTrainingFailure();
        this.mEdgeSVEnrollmentMetricsReporter.reportUtteranceTrainingFailureReason(trainingFailure);
        this.mEdgeSVEnrollmentMetricsReporter.reportWakeWordDetectionFailure(this.mEnrollmentController.getWakeWordModelId(), this.mEnrollmentController.getSpeakerVerificationModelId(), this.mEnrollmentController.getAverageVolume());
        stopDetectionTimer();
        if (this.mEnrollmentCallback.get() != null) {
            Log.d(TAG, "Callback is present");
            this.mEnrollmentCallback.getAndSet(null).onError(trainingFailure.getEnrollmentErrorContract());
            this.mEnrollmentController.startUtteranceTraining();
            return;
        }
        Log.d(TAG, "Callback is not present");
        this.mTrainingFailure.set(trainingFailure);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener.UtteranceTrainingCallback
    public void onUtteranceTrainingSuccess(@NonNull WakeWordData wakeWordData) {
        Log.d(TAG, "onUtteranceTrainingSuccess");
        this.mWakeWordData.set(wakeWordData);
        this.mEdgeSVEnrollmentMetricsReporter.reportUtteranceTrainingSuccess();
        invokeSuccess();
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener.UtteranceTrainingCallback
    public void onWakeWordDetected(@NonNull WakeWordData wakeWordData, @NonNull String str, @NonNull String str2, long j) {
        Log.d(TAG, "onWakeWordDetected");
        stopDetectionTimer();
        this.mEdgeSVEnrollmentMetricsReporter.reportWakeWordDetectionSuccess(str, str2, j);
        if (this.mEnrollmentController.isEnrollmentComplete()) {
            this.mWakeWordData.set(wakeWordData);
            invokeSuccess();
        }
    }

    @VisibleForTesting
    void setEnrollmentCallback(@NonNull EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
        Log.d(TAG, "setEnrollmentCallback");
        this.mEnrollmentCallback.set(utteranceTrainingCallback);
    }

    public void setSpeakerIDPreviouslyEnrolled(boolean z) {
        this.mIsSpeakerIDPreviouslyEnrolled = z;
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void startUserVoiceEnrollment(@NonNull final ResponseCallback responseCallback) {
        Log.d(TAG, "startUserVoiceEnrollment");
        reset();
        this.mEnrollmentController.initialize(this.mEnrollmentStateProvider.getNumUtterances(), this.mIsSpeakerIDPreviouslyEnrolled, new EnrollmentListener.ResponseCallback() { // from class: com.amazon.alexa.enrollment.unified.edgesv.EdgeSVEnrollmentProvider.1
            @Override // com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener.ResponseCallback
            public void onFailure(@NonNull TrainingFailure trainingFailure) {
                EdgeSVEnrollmentProvider.this.mEdgeSVEnrollmentMetricsReporter.reportStartEnrollmentFailure();
                EdgeSVEnrollmentProvider.this.mEdgeSVEnrollmentMetricsReporter.reportStartEnrollmentFailureReason(trainingFailure);
                responseCallback.onError(new CallbackErrorMetadata(trainingFailure.name()));
            }

            @Override // com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener.ResponseCallback
            public void onSuccess() {
                EdgeSVEnrollmentProvider.this.mEdgeSVEnrollmentMetricsReporter.reportStartEnrollmentSuccess();
                responseCallback.onSuccess();
                EdgeSVEnrollmentProvider.this.mEnrollmentController.startUtteranceTraining();
            }
        });
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void startUtteranceTraining(@NonNull EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
        Log.d(TAG, "startUtteranceTraining");
        setEnrollmentCallback(utteranceTrainingCallback);
        if (this.mWakeWordData.get() != null) {
            invokeSuccess();
        } else if (this.mTrainingFailure.get() != null) {
            onUtteranceTrainingFailure(this.mTrainingFailure.getAndSet(null));
        } else {
            startDetectionTimer();
        }
    }

    @VisibleForTesting
    EdgeSVEnrollmentProvider(@NonNull EnrollmentStateProvider enrollmentStateProvider, @NonNull EnrollmentController enrollmentController, @NonNull EdgeSVEnrollmentMetricsReporter edgeSVEnrollmentMetricsReporter, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull ScheduledExecutorService scheduledExecutorService) {
        this.mEnrollmentCallback = new AtomicReference<>();
        this.mTrainingFailure = new AtomicReference<>();
        this.mWakeWordData = new AtomicReference<>();
        this.mIsSpeakerIDPreviouslyEnrolled = false;
        this.mEnrollmentStateProvider = enrollmentStateProvider;
        this.mEnrollmentController = enrollmentController;
        this.mEdgeSVEnrollmentMetricsReporter = edgeSVEnrollmentMetricsReporter;
        this.mDetectionTimeoutExecutorService = scheduledExecutorService;
        this.mHandsFreeUser = handsFreeUserIdentity;
    }
}
