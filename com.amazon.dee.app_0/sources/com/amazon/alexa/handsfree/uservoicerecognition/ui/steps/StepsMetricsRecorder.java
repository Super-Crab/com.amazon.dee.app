package com.amazon.alexa.handsfree.uservoicerecognition.ui.steps;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.SetupCompleteMetricMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.VoiceTrainingMetricMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceTrainingMetadata;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.model.UVRComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class StepsMetricsRecorder {
    private static final String METRIC_NAME_STEPS_FRAGMENT_CALLBACK_FAILURE_PREFIX = "StepsFragmentCallback:Failure:%s";
    private static final String TAG = "StepsMetricsRecorder";
    static final String UVR_ENROLLER_CALL_CANCEL_ENROLLMENT = "UVREnrollerCancelEnrollment";
    static final String UVR_ENROLLER_CALL_CANCEL_UTTERANCE_TRAINING = "UVREnrollerCancelUtteranceTraining";
    static final String UVR_ENROLLER_CALL_ENABLE_UVR = "UVREnrollerEnableUVR";
    private final Context mContext;
    private final Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private EnrollmentType mEnrollmentType;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private Map<LatencyMetadata, Long> mLatencyMetricStartTimestamps;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final UVRComponent mUVRComponent;

    /* loaded from: classes8.dex */
    enum LatencyMetadata {
        START_RECORDING_DELAY("START_RECORDING_DELAY"),
        UTTERANCE_RECORDING_LENGTH("UTTERANCE_RECORDING_LENGTH");
        
        private final String mMetricName;

        LatencyMetadata(@NonNull String str) {
            this.mMetricName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mMetricName;
        }
    }

    @VisibleForTesting
    /* loaded from: classes8.dex */
    enum MetadataKey {
        SNR,
        CONFIDENCE,
        ERROR_CODE,
        SUCCESS,
        QUALITY_SCORE,
        STEP_NUMBER
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public enum PercentileMetadata {
        UVR_ENROLLER_CALL_FINISH_USER_ENROLLMENT("UVREnrollerFinishUserEnrollment"),
        STEPS_FRAGMENT_CALLBACK("StepsFragmentCallback");
        
        private final String mMetricName;

        PercentileMetadata(@NonNull String str) {
            this.mMetricName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mMetricName;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StepsMetricsRecorder(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context), UVRComponent.VOICE_TRAINING, ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).crashReportRecorderLazy(), new HashMap(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    private void recordNonFatalError(@NonNull Throwable th, @NonNull String str) {
        this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(this.mContext, str, th);
    }

    private void recordPercentileMetricFailure(@NonNull PercentileMetadata percentileMetadata) {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, percentileMetadata.toString()).emit(this.mContext);
    }

    private void recordPercentileMetricSuccess(@NonNull PercentileMetadata percentileMetadata) {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, percentileMetadata.toString()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addStartTimestamp(LatencyMetadata latencyMetadata) {
        this.mLatencyMetricStartTimestamps.put(latencyMetadata, Long.valueOf(getCurrentTimestamp()));
    }

    @VisibleForTesting
    long getCurrentTimestamp() {
        return SystemClock.elapsedRealtime();
    }

    @VisibleForTesting
    EnrollmentType getEnrollmentType() {
        if (this.mEnrollmentType == null) {
            this.mEnrollmentType = this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType();
        }
        return this.mEnrollmentType;
    }

    void logSetupComplete() {
        Log.i(TAG, "logSetupComplete: Emitting setup complete metric");
        this.mMetricsBuilderProvider.newBuilder().withHandsFreeSetupMetric(TAG, getEnrollmentType(), new HandsFreeSetupMetricData(SetupCompleteMetricMetadata.ActionType.INTENTION, SetupCompleteMetricMetadata.EventType.SETUP_COMPLETE, SetupCompleteMetricMetadata.Component.HANDSFREE_SETUP, SetupCompleteMetricMetadata.PageType.NONE)).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logUserVoiceEnrollmentError(@NonNull CallbackErrorMetadata callbackErrorMetadata, @NonNull String str) {
        String metricName = callbackErrorMetadata.getMetricName(str);
        Throwable throwable = callbackErrorMetadata.getThrowable(str);
        if (callbackErrorMetadata.getErrorCode() != -1) {
            this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, metricName).emit(this.mContext);
        }
        this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(this.mContext, TAG, throwable);
    }

    void recordDismissInternetDialog() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_INTERNET_DIALOG, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.DISMISS, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordFinishError(CallbackErrorMetadata callbackErrorMetadata) {
        logUserVoiceEnrollmentError(callbackErrorMetadata, PercentileMetadata.UVR_ENROLLER_CALL_FINISH_USER_ENROLLMENT.toString());
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, PercentileMetadata.UVR_ENROLLER_CALL_FINISH_USER_ENROLLMENT.toString()).withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.ERROR_CODE, callbackErrorMetadata.getErrorCode()).emit(this.mContext);
    }

    void recordFinishLater(int i) {
        this.mMetricsBuilderProvider.newBuilder().withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.STEP_NUMBER, i + 1).withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_CANCEL_DIALOG, VoiceTrainingMetricMetadata.SubPageType.DIALOG_CONFIRM_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    void recordFinishLaterClick(int i) {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_MAIN, VoiceTrainingMetricMetadata.SubPageType.FINISH_LATER_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.STEP_NUMBER, i + 1).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordFinishSuccess(double d) {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, PercentileMetadata.UVR_ENROLLER_CALL_FINISH_USER_ENROLLMENT.toString()).withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.QUALITY_SCORE, d).emit(this.mContext);
    }

    void recordInternetRetryClicked(int i) {
        this.mMetricsBuilderProvider.newBuilder().withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.STEP_NUMBER, i + 1).withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_INTERNET_DIALOG, VoiceTrainingMetricMetadata.SubPageType.DIALOG_RETRY_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    void recordInternetSkipClicked(int i) {
        this.mMetricsBuilderProvider.newBuilder().withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.STEP_NUMBER, i + 1).withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_INTERNET_DIALOG, VoiceTrainingMetricMetadata.SubPageType.DIALOG_SKIP_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordLatencyMetric(LatencyMetadata latencyMetadata) {
        long currentTimestamp = getCurrentTimestamp();
        Long l = this.mLatencyMetricStartTimestamps.get(latencyMetadata);
        if (l == null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attempting to emit ");
            outline107.append(latencyMetadata.toString());
            outline107.append(" metric withoutinitializing start timestamp.");
            Log.e(str, outline107.toString());
            return;
        }
        this.mMetricsBuilderProvider.newBuilder().withLatencyMetric(l.longValue(), currentTimestamp, latencyMetadata.toString()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordMaxErrorReached(int i) {
        this.mMetricsBuilderProvider.newBuilder().withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.STEP_NUMBER, i + 1).withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_MAIN, VoiceTrainingMetricMetadata.SubPageType.MAX_ERRORS_DIALOG, VoiceTrainingMetricMetadata.EventType.NONE, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordNextStepShown(int i) {
        this.mMetricsBuilderProvider.newBuilder().withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.STEP_NUMBER, i + 1).withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_MAIN, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.NEXT_STEP, getEnrollmentType()).emit(this.mContext);
    }

    void recordShowInternetDialog() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_INTERNET_DIALOG, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordTerminalError(int i) {
        this.mMetricsBuilderProvider.newBuilder().withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.STEP_NUMBER, i + 1).withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_MAIN, VoiceTrainingMetricMetadata.SubPageType.TERMINAL_ERROR_DIALOG, VoiceTrainingMetricMetadata.EventType.NONE, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordTrainingMetadata(boolean z, @Nullable UtteranceTrainingMetadata utteranceTrainingMetadata, @NonNull Optional<EnrollmentErrorContract> optional, int i) {
        if (utteranceTrainingMetadata != null) {
            MetricsBuilder withVoiceTrainingMetric = this.mMetricsBuilderProvider.newBuilder().withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.SNR, utteranceTrainingMetadata.getSoundToNoiseRatio()).withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.CONFIDENCE, utteranceTrainingMetadata.getUtteranceConfidence()).withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.SUCCESS, z).withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.STEP_NUMBER, i + 1).withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_MAIN, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.UTTERANCE, getEnrollmentType());
            if (optional.isPresent()) {
                withVoiceTrainingMetric.withVoiceMetadataMetric(TAG, (String) this.mUVRComponent, (UVRComponent) MetadataKey.ERROR_CODE, optional.get().getName());
            }
            withVoiceTrainingMetric.emit(this.mContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordViewInitialized() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_MAIN, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.NONE, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportMetricsForFailedStepsFragmentCallback(String str) {
        recordPercentileMetricSuccess(PercentileMetadata.STEPS_FRAGMENT_CALLBACK);
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, String.format(METRIC_NAME_STEPS_FRAGMENT_CALLBACK_FAILURE_PREFIX, str)).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportMetricsForSuccessfulStepsFragmentCallback() {
        recordPercentileMetricSuccess(PercentileMetadata.STEPS_FRAGMENT_CALLBACK);
    }

    @VisibleForTesting
    StepsMetricsRecorder(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull UVRComponent uVRComponent, @NonNull Lazy<CrashReportRecorder> lazy, @NonNull Map<LatencyMetadata, Long> map, @NonNull Lazy<EnrollmentTypeResolver> lazy2) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mUVRComponent = uVRComponent;
        this.mCrashReportRecorderLazy = lazy;
        this.mLatencyMetricStartTimestamps = map;
        this.mEnrollmentTypeResolverLazy = lazy2;
        this.mEnrollmentType = null;
    }
}
