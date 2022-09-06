package com.amazon.alexa.presence.library;

import android.util.Log;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsServiceV2;
/* loaded from: classes9.dex */
public final class MetricsRecorder {
    public static final String CATEGORY = "presence";
    public static final String MODULE = "presence";
    public static final String SOURCE = "native";
    private static final String TAG = "com.amazon.alexa.presence.library.MetricsRecorder";
    private MetricsServiceV2 metricsService;

    private MetricsRecorder() {
        this.metricsService = null;
        try {
            this.metricsService = (MetricsServiceV2) ComponentRegistry.getInstance().get(MetricsServiceV2.class).orNull();
        } catch (Throwable unused) {
            Log.w(TAG, "Unable to setup metrics service.");
        }
    }

    private static MetricDescriptor getMetricDescriptor(String str, String str2, String str3, String str4, String str5) {
        return new MetricDescriptor.Builder(new MetricName.Builder(str).withModule(str2).withSource(str3).build(), new MetricComponentName.Builder().withCategoryId(str4).withMethod(str5).build()).build();
    }

    public static MetricsRecorder getMetricsUtil() {
        return new MetricsRecorder();
    }

    public void recordCount(String str, String str2) {
        MetricsServiceV2 metricsServiceV2 = this.metricsService;
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordCount(getMetricDescriptor(str, "presence", "native", "presence", str2), 1.0d);
    }

    public void recordFailure(String str, String str2, String str3) {
        MetricsServiceV2 metricsServiceV2 = this.metricsService;
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordFailure(getMetricDescriptor(str, "presence", "native", "presence", str2), str3);
    }

    public void recordSuccess(String str, String str2) {
        MetricsServiceV2 metricsServiceV2 = this.metricsService;
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordSuccess(getMetricDescriptor(str, "presence", "native", "presence", str2));
    }

    public void recordTime(String str, String str2, long j) {
        MetricsServiceV2 metricsServiceV2 = this.metricsService;
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordTime(getMetricDescriptor(str, "presence", "native", "presence", str2), j);
    }

    public void recordZeroCount(String str, String str2) {
        MetricsServiceV2 metricsServiceV2 = this.metricsService;
        if (metricsServiceV2 == null) {
            return;
        }
        metricsServiceV2.recordCount(getMetricDescriptor(str, "presence", "native", "presence", str2), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }
}
