package com.amazon.alexa.enrollment.unified.speakerid.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.unified.speakerid.error.GenericSpeakerIDEnrollmentError;
import com.amazon.alexa.enrollment.unified.speakerid.error.SpeakerIDEnrollmentError;
import com.amazon.alexa.enrollment.unified.speakerid.metrics.SpeakerIDMetricsConstants;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes7.dex */
public class SpeakerIDEnrollmentMetricsReporter {
    public static final String ELIGIBILITY_CHECK = "EligibilityCheck";
    public static final String GET_SPEAKER = "GetSpeakerByPersonId";
    @VisibleForTesting
    static final String GET_TRAINING_PHRASES = "GetTrainingPhrases";
    public static final String SCHEDULE_VISUAL_FOCUS = "ScheduleVisualFocus";
    @VisibleForTesting
    static final String SEPARATOR = ":";
    @VisibleForTesting
    static final String SPEAKER_ID_ERROR_PREFIX = "SpeakerIDError";
    @VisibleForTesting
    static final String SPEAKER_ID_PREFIX = "SpeakerID";
    public static final String START_ENROLLMENT = "StartEnrollment";
    static final String TAG = "SpkrIDEnrolMetricReport";
    public static final String TIMEOUT_VISUAL_FOCUS = "TimeoutVisualFocus";
    public static final String UNSCHEDULE_VISUAL_FOCUS = "UnscheduleVisualFocus";
    public static final String UTTERANCE_TRAINING = "UtteranceTraining";
    public static final String VOICE_PRIVACY_CHECK = "VoicePrivacyCheck";
    private final Context mContext;
    private final Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    public SpeakerIDEnrollmentMetricsReporter(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).crashReportRecorderLazy(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    @VisibleForTesting
    String createSpeakerIDErrorMetricName(@NonNull String str, @NonNull SpeakerIDEnrollmentError speakerIDEnrollmentError) {
        String sb;
        if (speakerIDEnrollmentError instanceof GenericSpeakerIDEnrollmentError) {
            sb = GeneratedOutlineSupport1.outline76("SpeakerIDError:", str, ":", speakerIDEnrollmentError.getThrowable().getClass().getSimpleName());
        } else {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("SpeakerIDError:", str, ":");
            outline115.append(speakerIDEnrollmentError.getErrorPmetName());
            sb = outline115.toString();
        }
        GeneratedOutlineSupport1.outline167("Creating metric name ", sb, TAG);
        return sb;
    }

    @VisibleForTesting
    String createSpeakerIDPercentileMetricName(@NonNull String str) {
        String outline72 = GeneratedOutlineSupport1.outline72("SpeakerID:", str);
        GeneratedOutlineSupport1.outline167("Creating metric name ", outline72, TAG);
        return outline72;
    }

    @VisibleForTesting
    void logBusinessMetricIfRequired(@NonNull SpeakerIDEnrollmentError speakerIDEnrollmentError, @NonNull SpeakerIDMetricsConstants.SubPageType subPageType) {
        SpeakerIDMetricsConstants.ErrorType errorType = speakerIDEnrollmentError.getErrorType();
        if (errorType != null) {
            recordSpeakerIDTrainingErrorMetric(subPageType, errorType);
        }
    }

    @VisibleForTesting
    void logNonFatalIfRequired(@NonNull SpeakerIDEnrollmentError speakerIDEnrollmentError, @NonNull String str) {
        if (speakerIDEnrollmentError instanceof GenericSpeakerIDEnrollmentError) {
            String simpleName = speakerIDEnrollmentError.getThrowable().getClass().getSimpleName();
            Throwable throwable = speakerIDEnrollmentError.getThrowable();
            recordNonFatalError(throwable, str + ":" + simpleName);
        } else if (!(speakerIDEnrollmentError.getThrowable() instanceof EnrollmentComponentNullException)) {
        } else {
            recordNonFatalError(speakerIDEnrollmentError.getThrowable(), str);
        }
    }

    public void recordNonFatalError(@NonNull Throwable th, @NonNull String str) {
        String simpleName = th.getClass().getSimpleName();
        Log.d(TAG, "Reporting bugsnag metric " + str + " for exception " + simpleName);
        this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(this.mContext, str, th);
        Log.d(TAG, "Logged bugsnag metric " + str + " for exception " + simpleName);
    }

    public void recordPercentileMetricFailure(@NonNull String str) {
        GeneratedOutlineSupport1.outline167("Reporting percentile metric ", str, TAG);
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, str).emit(this.mContext);
        Log.d(TAG, "Emitted failure percentile metric " + str);
    }

    public void recordPercentileMetricSuccess(@NonNull String str) {
        GeneratedOutlineSupport1.outline167("Reporting percentile metric ", str, TAG);
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, str).emit(this.mContext);
        Log.d(TAG, "Emitted success percentile metric " + str);
    }

    @VisibleForTesting
    void recordPerformanceMetric(@NonNull String str) {
        Log.i(TAG, "Reporting metric " + str);
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, str).emit(this.mContext);
    }

    public void recordSpeakerIDTrainingErrorMetric(@NonNull SpeakerIDMetricsConstants.SubPageType subPageType, @NonNull SpeakerIDMetricsConstants.ErrorType errorType) {
        Log.d(TAG, "Reporting error " + errorType + " for method " + subPageType);
        this.mMetricsBuilderProvider.newBuilder().withVoiceTrainingMetric(TAG, SpeakerIDMetricsConstants.PageType.SPEAKER_ID_ENROLLMENT, subPageType, errorType, this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType()).emit(this.mContext);
    }

    public void reportDialogListenerStatus(@NonNull String str) {
        recordPerformanceMetric(createSpeakerIDPercentileMetricName(str));
    }

    public void reportGetSpeakerFailure() {
        recordPercentileMetricFailure(createSpeakerIDPercentileMetricName(GET_SPEAKER));
    }

    public void reportGetSpeakerFailureException(@NonNull Throwable th) {
        recordPerformanceMetric(createSpeakerIDErrorMetricName(GET_SPEAKER, th));
    }

    public void reportGetSpeakerSuccess() {
        recordPercentileMetricSuccess(createSpeakerIDPercentileMetricName(GET_SPEAKER));
    }

    public void reportSpeakerIDEligibilityCheckFailure() {
        recordPercentileMetricFailure(createSpeakerIDPercentileMetricName(ELIGIBILITY_CHECK));
    }

    public void reportSpeakerIDEligibilityCheckSuccess() {
        recordPercentileMetricSuccess(createSpeakerIDPercentileMetricName(ELIGIBILITY_CHECK));
    }

    @VisibleForTesting
    void reportSpeakerIDFetchEnrollmentStatusFailure() {
        recordPercentileMetricFailure(createSpeakerIDPercentileMetricName(UTTERANCE_TRAINING));
    }

    public void reportSpeakerIDFetchEnrollmentStatusSuccess() {
        recordPercentileMetricSuccess(createSpeakerIDPercentileMetricName(UTTERANCE_TRAINING));
    }

    public void reportSpeakerIDGetTrainingPhrasesFailure() {
        recordPercentileMetricFailure(createSpeakerIDPercentileMetricName(GET_TRAINING_PHRASES));
    }

    public void reportSpeakerIDGetTrainingPhrasesSuccess() {
        recordPercentileMetricSuccess(createSpeakerIDPercentileMetricName(GET_TRAINING_PHRASES));
    }

    @VisibleForTesting
    void reportSpeakerIDStartEnrollmentFailure() {
        recordPercentileMetricFailure(createSpeakerIDPercentileMetricName(START_ENROLLMENT));
    }

    public void reportSpeakerIDStartEnrollmentSuccess() {
        recordPercentileMetricSuccess(createSpeakerIDPercentileMetricName(START_ENROLLMENT));
    }

    public void reportSpeakerIDVisualFocusScheduled() {
        recordPerformanceMetric(createSpeakerIDPercentileMetricName(SCHEDULE_VISUAL_FOCUS));
    }

    public void reportSpeakerIDVisualFocusTimeout() {
        recordPercentileMetricFailure(createSpeakerIDPercentileMetricName(TIMEOUT_VISUAL_FOCUS));
    }

    public void reportSpeakerIDVisualFocusUnscheduled() {
        recordPerformanceMetric(createSpeakerIDPercentileMetricName(UNSCHEDULE_VISUAL_FOCUS));
    }

    public void reportStartEnrollmentException(@NonNull SpeakerIDEnrollmentError speakerIDEnrollmentError) {
        reportSpeakerIDStartEnrollmentFailure();
        recordPerformanceMetric(createSpeakerIDErrorMetricName(START_ENROLLMENT, speakerIDEnrollmentError));
        logNonFatalIfRequired(speakerIDEnrollmentError, START_ENROLLMENT);
        logBusinessMetricIfRequired(speakerIDEnrollmentError, SpeakerIDMetricsConstants.SubPageType.START_ENROLLMENT);
    }

    public void reportUtteranceTrainingException(@NonNull SpeakerIDEnrollmentError speakerIDEnrollmentError) {
        reportSpeakerIDFetchEnrollmentStatusFailure();
        recordPerformanceMetric(createSpeakerIDErrorMetricName(UTTERANCE_TRAINING, speakerIDEnrollmentError));
        logNonFatalIfRequired(speakerIDEnrollmentError, UTTERANCE_TRAINING);
        logBusinessMetricIfRequired(speakerIDEnrollmentError, SpeakerIDMetricsConstants.SubPageType.UTTERANCE_TRAINING);
    }

    public void reportVoicePrivacyCheckFailure() {
        recordPercentileMetricFailure(createSpeakerIDPercentileMetricName(VOICE_PRIVACY_CHECK));
    }

    public void reportVoicePrivacyCheckSuccess() {
        recordPercentileMetricSuccess(createSpeakerIDPercentileMetricName(VOICE_PRIVACY_CHECK));
    }

    @VisibleForTesting
    SpeakerIDEnrollmentMetricsReporter(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<CrashReportRecorder> lazy, @NonNull Lazy<EnrollmentTypeResolver> lazy2) {
        this.mContext = context.getApplicationContext();
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mCrashReportRecorderLazy = lazy;
        this.mEnrollmentTypeResolverLazy = lazy2;
    }

    @VisibleForTesting
    String createSpeakerIDErrorMetricName(@NonNull String str, @NonNull Throwable th) {
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("SpeakerIDError:", str, ":");
        outline115.append(th.getClass().getSimpleName());
        String sb = outline115.toString();
        GeneratedOutlineSupport1.outline167("Creating metric name ", sb, TAG);
        return sb;
    }
}
