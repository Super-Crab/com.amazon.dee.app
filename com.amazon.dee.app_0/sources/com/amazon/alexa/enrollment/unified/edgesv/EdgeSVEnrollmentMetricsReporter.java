package com.amazon.alexa.enrollment.unified.edgesv;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EdgeSVEnrollmentMetricsReporter {
    @VisibleForTesting
    static final String EDGE_SV_ENROLLMENT_PREFIX = "EdgeSVEnrollment";
    @VisibleForTesting
    static final String FAILURE_SUFFIX = "Failure";
    @VisibleForTesting
    static final String FINISH_ENROLLMENT = "FinishEnrollment";
    @VisibleForTesting
    static final String SEPARATOR = ":";
    @VisibleForTesting
    static final String START_ENROLLMENT = "StartEnrollment";
    @VisibleForTesting
    static final String SUCCESS_SUFFIX = "Success";
    static final String TAG = "EdgeSVEnrollmentMetricsReporter";
    @VisibleForTesting
    static final String UTTERANCE_TRAINING = "UtteranceTraining";
    @VisibleForTesting
    static final String WAKEWORD_DETECTION = "WakewordDetection";
    private final Context mContext;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    public EdgeSVEnrollmentMetricsReporter(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context));
    }

    @VisibleForTesting
    String createEdgeSVErrorMetricName(@NonNull String str, @NonNull TrainingFailure trainingFailure) {
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("EdgeSVEnrollment:", str, ":");
        outline115.append(trainingFailure.name());
        String sb = outline115.toString();
        GeneratedOutlineSupport1.outline167("Creating metric name ", sb, TAG);
        return sb;
    }

    @VisibleForTesting
    String createEdgeSVMetricName(@NonNull String str, @NonNull String str2) {
        String outline76 = GeneratedOutlineSupport1.outline76("EdgeSVEnrollment:", str, ":", str2);
        GeneratedOutlineSupport1.outline167("Creating metric name ", outline76, TAG);
        return outline76;
    }

    @VisibleForTesting
    String createEdgeSVPercentileMetricName(@NonNull String str) {
        String outline72 = GeneratedOutlineSupport1.outline72("EdgeSVEnrollment:", str);
        GeneratedOutlineSupport1.outline167("Creating metric name ", outline72, TAG);
        return outline72;
    }

    @VisibleForTesting
    void recordEnrollmentWakeWordDetectionEventMetric(boolean z, @NonNull String str, @NonNull String str2, long j) {
        this.mMetricsBuilderProvider.newBuilder().withEnrollmentDetectionEventMetric(TAG, createEdgeSVMetricName(WAKEWORD_DETECTION, z ? "Success" : "Failure"), str, str2, j).emit(this.mContext);
    }

    public void recordPercentileMetricFailure(@NonNull String str) {
        String str2 = TAG;
        Log.i(str2, "Reporting percentile metric " + str);
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, str).emit(this.mContext);
    }

    public void recordPercentileMetricSuccess(@NonNull String str) {
        String str2 = TAG;
        Log.i(str2, "Reporting percentile metric " + str);
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, str).emit(this.mContext);
    }

    @VisibleForTesting
    void recordPerformanceMetric(@NonNull String str) {
        String str2 = TAG;
        Log.i(str2, "Reporting metric " + str);
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, str).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportFinishEnrollmentFailure() {
        recordPercentileMetricFailure(createEdgeSVPercentileMetricName(FINISH_ENROLLMENT));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportFinishEnrollmentFailureReason(@NonNull TrainingFailure trainingFailure) {
        recordPerformanceMetric(createEdgeSVErrorMetricName(FINISH_ENROLLMENT, trainingFailure));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportFinishEnrollmentSuccess() {
        recordPercentileMetricSuccess(createEdgeSVPercentileMetricName(FINISH_ENROLLMENT));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportStartEnrollmentFailure() {
        recordPercentileMetricFailure(createEdgeSVPercentileMetricName("StartEnrollment"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportStartEnrollmentFailureReason(@NonNull TrainingFailure trainingFailure) {
        recordPerformanceMetric(createEdgeSVErrorMetricName("StartEnrollment", trainingFailure));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportStartEnrollmentSuccess() {
        recordPercentileMetricSuccess(createEdgeSVPercentileMetricName("StartEnrollment"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportUtteranceTrainingFailure() {
        recordPercentileMetricFailure(createEdgeSVPercentileMetricName("UtteranceTraining"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportUtteranceTrainingFailureReason(@NonNull TrainingFailure trainingFailure) {
        recordPerformanceMetric(createEdgeSVErrorMetricName("UtteranceTraining", trainingFailure));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportUtteranceTrainingSuccess() {
        recordPercentileMetricSuccess(createEdgeSVPercentileMetricName("UtteranceTraining"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportWakeWordDetectionFailure(@NonNull String str, @NonNull String str2, long j) {
        recordPercentileMetricFailure(createEdgeSVPercentileMetricName(WAKEWORD_DETECTION));
        recordEnrollmentWakeWordDetectionEventMetric(false, str, str2, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportWakeWordDetectionSuccess(@NonNull String str, @NonNull String str2, long j) {
        recordPercentileMetricSuccess(createEdgeSVPercentileMetricName(WAKEWORD_DETECTION));
        recordEnrollmentWakeWordDetectionEventMetric(true, str, str2, j);
    }

    @VisibleForTesting
    EdgeSVEnrollmentMetricsReporter(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mContext = context.getApplicationContext();
        this.mMetricsBuilderProvider = metricsBuilderProvider;
    }
}
