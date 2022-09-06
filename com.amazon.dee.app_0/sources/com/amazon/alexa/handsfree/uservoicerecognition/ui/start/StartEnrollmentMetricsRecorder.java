package com.amazon.alexa.handsfree.uservoicerecognition.ui.start;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.VoiceTrainingMetricMetadata;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import dagger.Lazy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class StartEnrollmentMetricsRecorder {
    private static final String TAG = "StartEnrollmentMetricsRecorder";
    @VisibleForTesting
    static final String UVR_ENROLLER_CALL_START_ENROLLMENT = "UVREnrollerStartEnrollment";
    private final Context mContext;
    private final Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private EnrollmentType mEnrollmentType;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartEnrollmentMetricsRecorder(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).crashReportRecorderLazy(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    @VisibleForTesting
    EnrollmentType getEnrollmentType() {
        if (this.mEnrollmentType == null) {
            this.mEnrollmentType = this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType();
        }
        return this.mEnrollmentType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logConfirmationDialogBackClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_START_CONFIRM_DIALOG, VoiceTrainingMetricMetadata.SubPageType.CONFIRM_BACK_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logConfirmationDialogContinueClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_START_CONFIRM_DIALOG, VoiceTrainingMetricMetadata.SubPageType.CONFIRM_START_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logContinueButtonClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_START, VoiceTrainingMetricMetadata.SubPageType.START_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logDisclaimerClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_START, VoiceTrainingMetricMetadata.SubPageType.DISCLAIMER_TEXT, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logLaterButtonClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_START, VoiceTrainingMetricMetadata.SubPageType.START_LATER_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
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
    StartEnrollmentMetricsRecorder(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<CrashReportRecorder> lazy, @NonNull Lazy<EnrollmentTypeResolver> lazy2) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mCrashReportRecorderLazy = lazy;
        this.mEnrollmentTypeResolverLazy = lazy2;
        this.mEnrollmentType = null;
    }
}
