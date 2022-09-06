package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class EdgeSVSpeakerIDCheckMetricsReporter {
    @VisibleForTesting
    static final String SEPARATOR = ":";
    @VisibleForTesting
    static final String SPEAKER_ID_CHECK_PREFIX = "SpeakerIDCheck";
    static final String START_SPEAKER_ID_CHECK = "StartSpeakerIDCheckJobService";
    static final String TAG = "EdgeSVSpeakerIDCheckMetricsReporter";
    public static final String UVR_MODEL_REMOVED_AFTER_CHECK = "UVRModelRemovedAfterCheck";
    private final Context mContext;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    public EdgeSVSpeakerIDCheckMetricsReporter(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context));
    }

    @VisibleForTesting
    String createSpeakerIDCheckMetricName(@NonNull String str) {
        String outline72 = GeneratedOutlineSupport1.outline72("SpeakerIDCheck:", str);
        GeneratedOutlineSupport1.outline167("Creating metric name ", outline72, TAG);
        return outline72;
    }

    public void recordPercentileMetricFailure(@NonNull String str) {
        GeneratedOutlineSupport1.outline167("Reporting percentile metric ", str, TAG);
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, str).emit(this.mContext);
        GeneratedOutlineSupport1.outline167("Emitted failure percentile metric ", str, TAG);
    }

    public void recordPercentileMetricSuccess(@NonNull String str) {
        GeneratedOutlineSupport1.outline167("Reporting percentile metric ", str, TAG);
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, str).emit(this.mContext);
        GeneratedOutlineSupport1.outline167("Emitted success percentile metric ", str, TAG);
    }

    @VisibleForTesting
    void recordPerformanceMetric(@NonNull String str) {
        String str2 = TAG;
        Log.i(str2, "Reporting metric " + str);
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, str).emit(this.mContext);
    }

    public void sendSpeakerIDCheckStart() {
        recordPerformanceMetric(createSpeakerIDCheckMetricName(START_SPEAKER_ID_CHECK));
    }

    public void sendUVRModelDeleteFailure() {
        recordPercentileMetricFailure(createSpeakerIDCheckMetricName(UVR_MODEL_REMOVED_AFTER_CHECK));
    }

    public void sendUVRModelDeleteSuccess() {
        recordPercentileMetricSuccess(createSpeakerIDCheckMetricName(UVR_MODEL_REMOVED_AFTER_CHECK));
    }

    @VisibleForTesting
    EdgeSVSpeakerIDCheckMetricsReporter(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
    }
}
