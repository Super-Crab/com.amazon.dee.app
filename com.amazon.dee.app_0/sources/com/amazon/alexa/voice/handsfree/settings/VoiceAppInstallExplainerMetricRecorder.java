package com.amazon.alexa.voice.handsfree.settings;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
/* loaded from: classes11.dex */
class VoiceAppInstallExplainerMetricRecorder {
    @VisibleForTesting
    static final MetricsConstants.Component METRIC_COMPONENT = MetricsConstants.Component.HANDS_FREE;
    @VisibleForTesting
    static final MetricsConstants.PageType METRIC_PAGE_TYPE = MetricsConstants.PageType.VOICE_APP_INSTALL_EXPLAINER;
    private static final String TAG = "VoiceAppInstallExplainerMetricRecorder";
    private final Context mContext;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoiceAppInstallExplainerMetricRecorder(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordClick(@NonNull MetricsConstants.SubPageType subPageType) {
        this.mMetricsBuilderProvider.newBuilder().withClickMetric(TAG, METRIC_COMPONENT, METRIC_PAGE_TYPE, subPageType).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordPageView() {
        this.mMetricsBuilderProvider.newBuilder().withPageViewMetric(TAG, METRIC_COMPONENT, METRIC_PAGE_TYPE, MetricsConstants.SubPageType.NONE).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportOperationalMetric(@NonNull String str) {
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, str).emit(this.mContext);
    }

    @VisibleForTesting
    VoiceAppInstallExplainerMetricRecorder(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
    }
}
