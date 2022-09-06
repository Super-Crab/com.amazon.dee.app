package com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.api.compat.AlexaDialogTurn;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.api.compat.AlexaNextDialogTurn;
import com.amazon.alexa.api.compat.AlexaUserSpeechProvider;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.compat.SupportedInitiationType;
import com.amazon.alexa.audiocapturer.RecordingRunnable;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.voiceSDK.audio.EnrollmentAudioRecordingRunnable;
import com.amazon.alexa.enrollment.voiceSDK.audio.EnrollmentAudioSinkWrapper;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.EnrollmentTerminateEvent;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.UpdateTrainingPageUIEvent;
import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentAudioRecorderWrapper;
import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentDialogState;
import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentHandlerUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentUserSpeechProvider implements AlexaUserSpeechProvider {
    private static final String ALEXA_INVOCATION_TYPE = "VoiceEnrollment.AlexaApp";
    private static final long DELAY_FOR_DIALOG_RETRY = 400;
    private static final int DIALOG_RETRY_COUNT_ALLOWED = 3;
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentUserSpeechProvider.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private AlexaMobileFrameworkApis alexaMobileFrameworkApis = null;
    private int dialogRetryCount;
    private EnrollmentDialogState dialogState;
    private final EnrollmentAudioRecorderWrapper enrollmentAudioRecorderWrapper;
    private final EnrollmentAudioSinkWrapper enrollmentAudioSinkWrapper;
    private final EnrollmentEventBus enrollmentEventBus;
    private final EnrollmentHandlerUtil enrollmentHandlerUtil;
    private final EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    private final ExecutorService mExecutorService;

    @Inject
    public EnrollmentUserSpeechProvider(EnrollmentEventBus enrollmentEventBus, ExecutorService executorService, EnrollmentAudioSinkWrapper enrollmentAudioSinkWrapper, EnrollmentHandlerUtil enrollmentHandlerUtil, EnrollmentAudioRecorderWrapper enrollmentAudioRecorderWrapper, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        this.enrollmentEventBus = enrollmentEventBus;
        this.mExecutorService = executorService;
        this.enrollmentAudioSinkWrapper = enrollmentAudioSinkWrapper;
        this.enrollmentHandlerUtil = enrollmentHandlerUtil;
        this.enrollmentAudioRecorderWrapper = enrollmentAudioRecorderWrapper;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
        resetDialogRetryCount();
        setDialogState(EnrollmentDialogState.NOT_STARTED);
    }

    private boolean canRequestDialog() {
        return this.dialogState != EnrollmentDialogState.IN_PROGRESS;
    }

    private AlexaAudioMetadata getAlexaAudioMetadata() {
        return new AlexaAudioMetadata.Builder().setAlexaProfile(AlexaProfile.NEAR_FIELD).setAudioFormat(RecordingRunnable.USER_SPEECH_AUDIO_FORMAT).build();
    }

    private AlexaDialogExtras getAlexaDialogExtras() {
        return AlexaDialogExtras.builder().setInvocationType(ALEXA_INVOCATION_TYPE).setLaunchType(LaunchType.TAP_TO_TALK).suppressWakeSound(true).suppressWakewordVerification(true).suppressEndpointSound(true).setAlexaUserInterfaceOptions(AlexaUserInterfaceOptions.builder().setTheme(AlexaUserInterfaceOptions.Theme.MINIMAL).build()).build();
    }

    private AlexaDialogRequest getAlexaDialogRequest() {
        return AlexaDialogRequest.builder().setInvocationType(ALEXA_INVOCATION_TYPE).setLaunchType(LaunchType.TAP_TO_TALK).build();
    }

    private void retryDialogRequestWithDelay() {
        if (this.dialogRetryCount < 3) {
            incrementDialogRetryCount();
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Retrying dialog request with current state ");
            outline107.append(this.dialogState);
            outline107.append(" : count :");
            outline107.append(this.dialogRetryCount);
            Log.w(str, outline107.toString());
            this.enrollmentHandlerUtil.runWithDelay(new Runnable() { // from class: com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider.2
                @Override // java.lang.Runnable
                public void run() {
                    EnrollmentUserSpeechProvider.this.requestDialog();
                }
            }, 400L);
            return;
        }
        Log.e(TAG, "Retry count exhausted, terminating enrollment");
        EnrollmentEventBus enrollmentEventBus = this.enrollmentEventBus;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(MetricsConstants.UserInteractionMetrics.DIALOG_REQUEST_NOT_ALLOWED);
        outline1072.append(this.dialogState);
        enrollmentEventBus.sendEvent(EnrollmentTerminateEvent.create(outline1072.toString()));
    }

    public void deRegister() {
        Log.i(TAG, "Deregistering EnrollmentUserSpeechProvider..!");
        try {
            this.alexaMobileFrameworkApis.getUserSpeechProviders().deregister(this);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Exception while Deregistering EnrollmentUserSpeechProvider "), TAG);
        }
        this.mExecutorService.shutdown();
    }

    @VisibleForTesting
    void incrementDialogRetryCount() {
        this.dialogRetryCount++;
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogFinished() {
        Log.i(TAG, "Inside onDialogFinished");
        setDialogState(EnrollmentDialogState.FINISHED);
        this.enrollmentEventBus.sendEvent(UpdateTrainingPageUIEvent.create(EnrollmentDialogState.IN_PROGRESS.name()));
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogRequestDenied() {
        Log.i(TAG, "Inside onDialogRequestDenied");
        setDialogState(EnrollmentDialogState.DENIED);
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.DIALOG_REQUEST_DENIED);
        retryDialogRequestWithDelay();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogRequested(AlexaDialogTurn alexaDialogTurn) {
        try {
            Log.i(TAG, "Inside onDialogRequested");
            final EnrollmentAudioRecordingRunnable recordingRunnable = this.enrollmentAudioRecorderWrapper.getRecordingRunnable(this.enrollmentAudioSinkWrapper, this.enrollmentEventBus);
            this.mExecutorService.submit(recordingRunnable);
            alexaDialogTurn.startTurn(getAlexaAudioMetadata(), this.enrollmentAudioSinkWrapper.getAudioSink(), new AlexaDialogTurnStopCallback() { // from class: com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider.1
                @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
                public void stopRecording() {
                    String unused = EnrollmentUserSpeechProvider.TAG;
                    recordingRunnable.stop();
                    EnrollmentUserSpeechProvider.this.enrollmentAudioSinkWrapper.resetAudioSink();
                }
            }, getAlexaDialogExtras());
            setDialogState(EnrollmentDialogState.IN_PROGRESS);
            resetDialogRetryCount();
            this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.ON_DIALOG_REQUESTED_SUCCESS);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Exception in onDialogRequested "), TAG);
            this.enrollmentEventBus.sendEvent(EnrollmentTerminateEvent.create(MetricsConstants.UserInteractionMetrics.ON_DIALOG_REQUESTED_ERROR));
        }
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogStarted() {
        Log.i(TAG, "Inside onDialogStarted");
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnFinished() {
        Log.i(TAG, "Inside onDialogTurnFinished");
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnRequested(AlexaNextDialogTurn alexaNextDialogTurn) {
        Log.i(TAG, "Inside onDialogTurnRequested");
        this.enrollmentEventBus.sendEvent(EnrollmentTerminateEvent.create(MetricsConstants.UserInteractionMetrics.ON_DIALOG_TURN_REQUESTED_ERROR));
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnStarted() {
        Log.i(TAG, "Inside onDialogTurnStarted");
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void pauseWakeWordDetection() {
    }

    public void register() {
        Log.i(TAG, "Registering EnrollmentUserSpeechProvider..!");
        this.alexaMobileFrameworkApis.getUserSpeechProviders().register(this, AlexaUserSpeechProviderMetadata.create(Collections.singleton(SupportedInitiationType.SERVER), Collections.emptySet(), AlexaUserSpeechProviderScope.APPLICATION));
    }

    public void requestDialog() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Inside requestDialog with current dialog state ");
        outline107.append(this.dialogState);
        Log.i(str, outline107.toString());
        if (canRequestDialog()) {
            Log.i(TAG, "Requesting dialog...!");
            this.alexaMobileFrameworkApis.getUserSpeechProviders().requestDialog(this, getAlexaDialogRequest());
            return;
        }
        Log.w(TAG, "Dialog is already in progress.");
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.DIALOG_IN_PROGRESS_REQUEST_RETRY);
        retryDialogRequestWithDelay();
    }

    @VisibleForTesting
    void resetDialogRetryCount() {
        this.dialogRetryCount = 0;
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void resumeWakeWordDetection() {
    }

    public void setAlexaMobileFrameworkApisInstance(AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        this.alexaMobileFrameworkApis = alexaMobileFrameworkApis;
    }

    @VisibleForTesting
    void setDialogState(EnrollmentDialogState enrollmentDialogState) {
        this.dialogState = enrollmentDialogState;
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
    }
}
