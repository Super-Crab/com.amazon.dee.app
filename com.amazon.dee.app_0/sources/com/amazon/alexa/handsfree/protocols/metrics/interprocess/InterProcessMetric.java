package com.amazon.alexa.handsfree.protocols.metrics.interprocess;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
/* loaded from: classes8.dex */
public abstract class InterProcessMetric implements Parcelable {
    @Nullable
    public abstract MetricsBuilder populateMetricsBuilder(MetricsBuilder metricsBuilder);
}
