package com.amazon.alexa.handsfree.protocols.metrics.caching;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import java.util.List;
/* loaded from: classes8.dex */
public interface MetricSerializer {
    @Nullable
    List<Metric> deserializeMetricsList(@NonNull String str);

    @Nullable
    String serializeMetricsList(@NonNull List<Metric> list);
}
