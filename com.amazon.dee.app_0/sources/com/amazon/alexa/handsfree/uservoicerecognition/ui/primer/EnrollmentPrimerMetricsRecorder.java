package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.VoiceTrainingMetricMetadata;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class EnrollmentPrimerMetricsRecorder {
    @VisibleForTesting
    static final String DUAL_ENROLLER_CALL_START_ENROLLMENT = "DualEnrollerStartEnrollment";
    @VisibleForTesting
    static final String IS_SPEAKER_ID_ENROLLED = "IsSpeakerIDEnrolled";
    @VisibleForTesting
    static final String TAG = "EnrollmentPrimerMetricsRecorder";
    private final Context mContext;
    private EnrollmentType mEnrollmentType;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    public EnrollmentPrimerMetricsRecorder(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    @VisibleForTesting
    EnrollmentType getEnrollmentType() {
        if (this.mEnrollmentType == null) {
            this.mEnrollmentType = this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType();
        }
        return this.mEnrollmentType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logAlreadyEnrolledDialogShownMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(EnrollmentPrimerFragment.TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_SPEAKER_ID_ALREADY_ENROLLED, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logContinueButtonClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER, VoiceTrainingMetricMetadata.SubPageType.START_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    void logDisclaimerClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER, VoiceTrainingMetricMetadata.SubPageType.DISCLAIMER_TEXT, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void logGettingReadyPageLoadMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(EnrollmentPrimerFragment.TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_GETTING_READY, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logInternetDialogDismissClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_INTERNET_DIALOG, VoiceTrainingMetricMetadata.SubPageType.ALERT_DIALOG_DISMISS, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logInternetDialogNegativeClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_INTERNET_DIALOG, VoiceTrainingMetricMetadata.SubPageType.ALERT_DIALOG_NEGATIVE_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logInternetDialogPositiveClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_INTERNET_DIALOG, VoiceTrainingMetricMetadata.SubPageType.ALERT_DIALOG_POSITIVE_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logInternetDialogShownMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_INTERNET_DIALOG, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logIsSpeakerIDEnrolledErrorMetric() {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, IS_SPEAKER_ID_ENROLLED).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logIsSpeakerIDEnrolledSuccessMetric() {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, IS_SPEAKER_ID_ENROLLED).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logSkipButtonClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER, VoiceTrainingMetricMetadata.SubPageType.START_LATER_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logSkipDialogDismissClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_SKIP_DIALOG, VoiceTrainingMetricMetadata.SubPageType.ALERT_DIALOG_DISMISS, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logSkipDialogNegativeButtonClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_SKIP_DIALOG, VoiceTrainingMetricMetadata.SubPageType.ALERT_DIALOG_NEGATIVE_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logSkipDialogPositiveButtonClickMetric() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_SKIP_DIALOG, VoiceTrainingMetricMetadata.SubPageType.ALERT_DIALOG_POSITIVE_BUTTON, VoiceTrainingMetricMetadata.EventType.CLICK, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logStartEnrollmentErrorDialogShown() {
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(EnrollmentPrimerFragment.TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER_START_ENROLLMENT_ERROR_DIALOG, VoiceTrainingMetricMetadata.SubPageType.NONE, VoiceTrainingMetricMetadata.EventType.PAGE_LOAD, getEnrollmentType()).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logStartUserVoiceEnrollmentErrorMetric() {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, DUAL_ENROLLER_CALL_START_ENROLLMENT).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logStartUserVoiceEnrollmentSuccessMetric() {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, DUAL_ENROLLER_CALL_START_ENROLLMENT).emit(this.mContext);
    }

    public void logVoicePrivacyScreenClosed() {
        this.mMetricsBuilderProvider.newBuilder().withClickMetric(TAG, VoiceTrainingMetricMetadata.Component.VOICE_TRAINING, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER, VoiceTrainingMetricMetadata.SubPageType.VOICE_PRIVACY).emit(this.mContext);
    }

    public void logVoicePrivacyScreenShown() {
        this.mMetricsBuilderProvider.newBuilder().withPageViewMetric(TAG, VoiceTrainingMetricMetadata.Component.VOICE_TRAINING, VoiceTrainingMetricMetadata.PageType.TRAINING_PRIMER, VoiceTrainingMetricMetadata.SubPageType.VOICE_PRIVACY).emit(this.mContext);
    }

    @VisibleForTesting
    EnrollmentPrimerMetricsRecorder(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
    }
}
