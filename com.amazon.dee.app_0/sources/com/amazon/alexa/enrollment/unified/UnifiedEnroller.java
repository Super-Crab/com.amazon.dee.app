package com.amazon.alexa.enrollment.unified;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.enrollment.unified.api.EnrollmentProvider;
import com.amazon.alexa.enrollment.unified.api.SpeechletTimeoutListener;
import com.amazon.alexa.enrollment.unified.edgesv.EdgeSVEnrollmentProvider;
import com.amazon.alexa.enrollment.unified.model.EnrollmentStateProvider;
import com.amazon.alexa.enrollment.unified.speakerid.SpeakerIDEnrollmentProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.List;
/* loaded from: classes7.dex */
public class UnifiedEnroller implements EnrollmentProvider, SpeechletTimeoutListener {
    @VisibleForTesting
    static final String ALEXA_SERVICES_CONNECTION_METRIC_NAME = "UnifiedEnroller:AlexaServicesConnection";
    @VisibleForTesting
    static final String SEPARATOR = ":";
    private static final String TAG = "UnifiedEnroller";
    private final AlexaServicesConnection mAlexaServicesConnection;
    private AlexaServicesConnection.ConnectionListener mConnectionListener;
    private final Context mContext;
    private final EdgeSVEnrollmentProvider mEdgeSVEnrollmentProvider;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private boolean mIsEligibleForDualEnrollment;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final SpeakerIDEnrollmentProvider mSpeakerIDEnrollmentProvider;
    private EnrollmentProvider.UtteranceTrainingCallback mUtteranceTrainingCallback;

    public UnifiedEnroller(@NonNull Context context, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        EnrollmentStateProvider enrollmentStateProvider = new EnrollmentStateProvider();
        HandsFreeUserIdentity handsFreeUserIdentity = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider().getHandsFreeUserIdentity();
        this.mAlexaServicesConnection = new AlexaServicesConnection(context);
        this.mEdgeSVEnrollmentProvider = new EdgeSVEnrollmentProvider(context, this.mAlexaServicesConnection, speakerVerificationMetricsListener, wakeWordDetectionMetricsListener, handsFreeUserIdentity, enrollmentStateProvider);
        this.mSpeakerIDEnrollmentProvider = new SpeakerIDEnrollmentProvider(context, enrollmentStateProvider, this.mEdgeSVEnrollmentProvider, this.mAlexaServicesConnection, handsFreeUserIdentity, this);
        this.mContext = context;
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(context);
        this.mEnrollmentTypeResolverLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy();
    }

    private void disconnect() {
        this.mAlexaServicesConnection.deregisterListener(this.mConnectionListener);
        this.mAlexaServicesConnection.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitAlexaServicesConnectionFailureMetric(@NonNull AlexaConnectingFailedReason alexaConnectingFailedReason) {
        MetricsBuilder withPercentileMetricFailure = this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, ALEXA_SERVICES_CONNECTION_METRIC_NAME);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UnifiedEnroller:AlexaServicesConnection:");
        outline107.append(alexaConnectingFailedReason.name());
        withPercentileMetricFailure.withPerformanceMetric(str, outline107.toString()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitAlexaServicesConnectionSuccessMetric() {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, ALEXA_SERVICES_CONNECTION_METRIC_NAME).emit(this.mContext);
    }

    @NonNull
    private List<String> getEdgeSVUtterances() {
        return this.mEdgeSVEnrollmentProvider.getUtterances();
    }

    private void startDualEnrollment(@NonNull final ResponseCallback responseCallback) {
        this.mSpeakerIDEnrollmentProvider.startUserVoiceEnrollment(new ResponseCallback() { // from class: com.amazon.alexa.enrollment.unified.UnifiedEnroller.2
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                responseCallback.onError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                UnifiedEnroller.this.startEdgeSVEnrollment(responseCallback, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startEdgeSVEnrollment(@NonNull ResponseCallback responseCallback, boolean z) {
        this.mEdgeSVEnrollmentProvider.setSpeakerIDPreviouslyEnrolled(z);
        this.mEdgeSVEnrollmentProvider.startUserVoiceEnrollment(responseCallback);
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void cancelUserVoiceEnrollment(@NonNull ResponseCallback responseCallback) {
        disconnect();
        this.mUtteranceTrainingCallback = null;
        this.mEdgeSVEnrollmentProvider.cancelUserVoiceEnrollment(responseCallback);
        if (this.mIsEligibleForDualEnrollment) {
            this.mSpeakerIDEnrollmentProvider.cancelUserVoiceEnrollment(responseCallback);
        }
        this.mSpeakerIDEnrollmentProvider.stopSpeechletTimer();
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void cancelUtteranceTraining(@NonNull ResponseCallback responseCallback) {
        this.mSpeakerIDEnrollmentProvider.cancelUtteranceTraining(responseCallback);
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void finishUserVoiceEnrollment(@NonNull ResponseCallback responseCallback) {
        disconnect();
        this.mUtteranceTrainingCallback = null;
        this.mEdgeSVEnrollmentProvider.finishUserVoiceEnrollment(responseCallback);
        this.mSpeakerIDEnrollmentProvider.stopSpeechletTimer();
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    @NonNull
    public List<String> getUtterances() {
        if (this.mIsEligibleForDualEnrollment) {
            return this.mSpeakerIDEnrollmentProvider.getUtterances();
        }
        return getEdgeSVUtterances();
    }

    @VisibleForTesting
    boolean is1PSVDecoupledEnrollment() {
        return this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType().equals(EnrollmentType._1PSV_DECOUPLED);
    }

    @VisibleForTesting
    boolean isEligibleForDualEnrollment(@NonNull boolean z) {
        return !is1PSVDecoupledEnrollment() && !z;
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SpeechletTimeoutListener
    public void onSpeechletTimedOut(@NonNull EnrollmentErrorContract enrollmentErrorContract) {
        EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback = this.mUtteranceTrainingCallback;
        if (utteranceTrainingCallback != null) {
            utteranceTrainingCallback.onError(enrollmentErrorContract);
        }
    }

    @VisibleForTesting
    void startDualEnrollmentIfEligible(@NonNull ResponseCallback responseCallback) {
        boolean z = this.mContext.getSharedPreferences(EnrollmentTypeResolver.PREFERENCE_FILE_NAME, 0).getBoolean(EnrollmentTypeResolver.IS_SPEAKER_ID_ALREADY_CREATED, false);
        this.mIsEligibleForDualEnrollment = isEligibleForDualEnrollment(z);
        if (this.mIsEligibleForDualEnrollment) {
            startDualEnrollment(responseCallback);
        } else {
            startEdgeSVEnrollment(responseCallback, z);
        }
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    @WorkerThread
    public void startUserVoiceEnrollment(@NonNull final ResponseCallback responseCallback) {
        AlexaServicesConnection.ConnectionListener connectionListener = this.mConnectionListener;
        if (connectionListener != null) {
            this.mAlexaServicesConnection.deregisterListener(connectionListener);
        }
        this.mConnectionListener = new AlexaServicesConnection.ConnectionListener() { // from class: com.amazon.alexa.enrollment.unified.UnifiedEnroller.1
            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onConnected() {
                Log.d(UnifiedEnroller.TAG, "onConnected");
                UnifiedEnroller.this.emitAlexaServicesConnectionSuccessMetric();
                UnifiedEnroller.this.startDualEnrollmentIfEligible(responseCallback);
            }

            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onConnectingFailed(@NonNull AlexaConnectingFailedReason alexaConnectingFailedReason, @NonNull String str) {
                GeneratedOutlineSupport1.outline167("onConnectingFailed with reason: ", str, UnifiedEnroller.TAG);
                UnifiedEnroller.this.emitAlexaServicesConnectionFailureMetric(alexaConnectingFailedReason);
                responseCallback.onError(new CallbackErrorMetadata(str));
            }

            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onDisconnected() {
                Log.d(UnifiedEnroller.TAG, "onDisconnected");
            }
        };
        this.mAlexaServicesConnection.registerListener(this.mConnectionListener);
        if (!this.mAlexaServicesConnection.isConnected()) {
            this.mAlexaServicesConnection.connect();
        }
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void startUtteranceTraining(@NonNull final EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
        this.mUtteranceTrainingCallback = utteranceTrainingCallback;
        this.mEdgeSVEnrollmentProvider.startUtteranceTraining(new EnrollmentProvider.UtteranceTrainingCallback() { // from class: com.amazon.alexa.enrollment.unified.UnifiedEnroller.3
            @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider.UtteranceTrainingCallback
            public void onAudioCaptured(@NonNull short[] sArr) {
                utteranceTrainingCallback.onAudioCaptured(sArr);
            }

            @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider.UtteranceTrainingCallback
            public void onError(@NonNull EnrollmentErrorContract enrollmentErrorContract) {
                utteranceTrainingCallback.onError(enrollmentErrorContract);
            }

            @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider.UtteranceTrainingCallback
            public void onSuccess() {
                if (UnifiedEnroller.this.mIsEligibleForDualEnrollment) {
                    UnifiedEnroller.this.mSpeakerIDEnrollmentProvider.startUtteranceTraining(utteranceTrainingCallback);
                    return;
                }
                UnifiedEnroller.this.mEdgeSVEnrollmentProvider.onStopRecording();
                utteranceTrainingCallback.onSuccess();
            }
        });
    }

    @VisibleForTesting
    UnifiedEnroller(@NonNull SpeakerIDEnrollmentProvider speakerIDEnrollmentProvider, @NonNull EdgeSVEnrollmentProvider edgeSVEnrollmentProvider, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mSpeakerIDEnrollmentProvider = speakerIDEnrollmentProvider;
        this.mEdgeSVEnrollmentProvider = edgeSVEnrollmentProvider;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
    }
}
