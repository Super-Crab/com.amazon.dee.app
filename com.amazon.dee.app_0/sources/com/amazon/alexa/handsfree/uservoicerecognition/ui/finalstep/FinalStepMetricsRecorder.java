package com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.SetupCompleteMetricMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.VoiceTrainingMetricMetadata;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import dagger.Lazy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class FinalStepMetricsRecorder {
    private static final String METRIC_NAME_FRAGMENT_CALLBACK_FAILURE_PREFIX = "EnrollmentCompleteFragmentCallback:Failure:%s";
    private static final String TAG = "FinalStepMetricsRecorder";
    static final String UVR_ENROLLER_CALL_CANCEL_ENROLLMENT = "UVREnrollerCancelEnrollment";
    static final String UVR_ENROLLER_CALL_ENABLE_UVR = "UVREnrollerEnableUVR";
    private final Context mContext;
    private final Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private EnrollmentType mEnrollmentType;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FinalStepMetricsRecorder(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).crashReportRecorderLazy());
    }

    @VisibleForTesting
    EnrollmentType getEnrollmentType() {
        if (this.mEnrollmentType == null) {
            this.mEnrollmentType = this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType();
        }
        return this.mEnrollmentType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logAllowRespondOnLockScreenDialog() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.LOCK_SCREEN_CONFIRM_DIALOG, VoiceTrainingMetricMetadata.SubPageType.LOCK_SCREEN_ALLOW_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logClickOnDisableRespondOnLockScreenRadio() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_FINISH, VoiceTrainingMetricMetadata.SubPageType.RADIO_LOCK_SCREEN_OFF, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logClickOnDoneButton() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_FINISH, VoiceTrainingMetricMetadata.SubPageType.DONE_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logClickOnEnableRespondOnLockScreenRadio() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_FINISH, VoiceTrainingMetricMetadata.SubPageType.RADIO_LOCK_SCREEN_ON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logDenyRespondOnLockScreenDialog() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.LOCK_SCREEN_CONFIRM_DIALOG, VoiceTrainingMetricMetadata.SubPageType.LOCK_SCREEN_DENY_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logSetupComplete() {
        Log.i(TAG, "logSetupComplete: Emitting setup complete metric");
        this.mMetricsBuilderProvider.newBuilder().withHandsFreeSetupMetric(TAG, getEnrollmentType(), new HandsFreeSetupMetricData(SetupCompleteMetricMetadata.ActionType.INTENTION, SetupCompleteMetricMetadata.EventType.SETUP_COMPLETE, SetupCompleteMetricMetadata.Component.HANDSFREE_SETUP, SetupCompleteMetricMetadata.PageType.NONE)).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logShowConfirmationDialog() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.LOCK_SCREEN_CONFIRM_DIALOG, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, getEnrollmentType()).emit(this.mContext);
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

    @VisibleForTesting
    FinalStepMetricsRecorder(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy, @NonNull Lazy<CrashReportRecorder> lazy2) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mCrashReportRecorderLazy = lazy2;
        this.mEnrollmentType = null;
    }
}
