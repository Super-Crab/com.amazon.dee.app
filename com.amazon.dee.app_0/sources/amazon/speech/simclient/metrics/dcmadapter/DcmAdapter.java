package amazon.speech.simclient.metrics.dcmadapter;

import android.content.Context;
/* loaded from: classes.dex */
public interface DcmAdapter {
    MetricEventInterface createMetricEvent(Context context, String str, String str2);

    boolean recordClickStreamMetric(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9);

    void recordMetricsEvent(Context context, MetricEventInterface metricEventInterface, boolean z);
}
