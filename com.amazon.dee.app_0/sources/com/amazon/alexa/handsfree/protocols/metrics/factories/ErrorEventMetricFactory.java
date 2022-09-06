package com.amazon.alexa.handsfree.protocols.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
/* loaded from: classes8.dex */
public interface ErrorEventMetricFactory {
    Metric buildNonFatalErrorMetric(@NonNull String str, @NonNull String str2);
}
