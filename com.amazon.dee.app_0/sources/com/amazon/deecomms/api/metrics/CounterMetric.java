package com.amazon.deecomms.api.metrics;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes12.dex */
public class CounterMetric extends CommsMetric {
    private Double mCount;

    public CounterMetric(CommsMetric.MetricType metricType, String str) {
        super(metricType, "Comms", str);
        this.mCount = Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    @NonNull
    public static CounterMetric generateClickstream(String str) {
        return new CounterMetric(CommsMetric.MetricType.ClickStream, str);
    }

    @NonNull
    public static CounterMetric generateCodeSpecificOperationalException(@NonNull Throwable th, @NonNull String str, @NonNull String str2, @NonNull int i, @NonNull String str3) {
        return generateOperationalException(th, GeneratedOutlineSupport1.outline75(str, ".", str3), str2, i, str3);
    }

    @NonNull
    public static CounterMetric generateOperational(String str) {
        return new CounterMetric(CommsMetric.MetricType.Operational, str);
    }

    @NonNull
    public static CounterMetric generateOperationalException(@NonNull Throwable th, @NonNull String str, @NonNull String str2, @NonNull int i) {
        return generateOperationalException(th, str, str2, i, null);
    }

    @NonNull
    public static CounterMetric generateOperationalWithSource(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        CounterMetric generateOperational = generateOperational(str);
        generateOperational.setCounter(Double.valueOf(1.0d));
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", str2);
        metadata.put("errorSource", str3);
        return generateOperational;
    }

    @NonNull
    public static CounterMetric generateStatusCodeSpecificOperationalException(@NonNull Throwable th, @NonNull String str, @NonNull String str2, @NonNull int i, @NonNull String str3) {
        return generateOperationalException(th, GeneratedOutlineSupport1.outline74(str, ".", i), str2, i, str3);
    }

    @Override // com.amazon.deecomms.api.metrics.CommsMetric
    protected void addAttributesForToString(StringBuilder sb) {
        sb.append("count=");
        sb.append(this.mCount);
    }

    @Override // com.amazon.deecomms.api.metrics.CommsMetric
    public String formatMetric() {
        return format(String.valueOf(getCount()));
    }

    public Double getCount() {
        return this.mCount;
    }

    public void setCounter(Double d) {
        this.mCount = d;
    }

    @NonNull
    public static CounterMetric generateOperationalException(@NonNull Throwable th, @NonNull String str, @NonNull String str2, @NonNull int i, @Nullable String str3) {
        String simpleName = th.getClass().getSimpleName();
        String format = String.format("%s:\n%s", simpleName, Log.getStackTraceString(th));
        CounterMetric generateOperational = generateOperational(str);
        generateOperational.setCounter(Double.valueOf(1.0d));
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", simpleName);
        metadata.put("errorSource", format);
        metadata.put("requestId", str2);
        metadata.put("statusCode", Integer.valueOf(i));
        if (str3 != null) {
            metadata.put("EventValue", str3);
        }
        return generateOperational;
    }

    public CounterMetric(CommsMetric.MetricType metricType, String str, String str2, Double d) {
        super(metricType, str, str2);
        this.mCount = d;
    }

    public CounterMetric(CommsMetric.MetricType metricType, String str, String str2) {
        super(metricType, str, str2);
        this.mCount = Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }
}
