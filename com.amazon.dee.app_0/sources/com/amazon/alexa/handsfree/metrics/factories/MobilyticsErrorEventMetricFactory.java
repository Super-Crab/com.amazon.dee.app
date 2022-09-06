package com.amazon.alexa.handsfree.metrics.factories;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsMetricsCounterWrapper;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.factories.ErrorEventMetricFactory;
/* loaded from: classes8.dex */
class MobilyticsErrorEventMetricFactory implements ErrorEventMetricFactory {
    @VisibleForTesting
    static final String NON_FATAL_ERROR_PREFIX = "NF_ERROR";
    @VisibleForTesting
    static final String SEPARATOR = ":";

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.ErrorEventMetricFactory
    public Metric buildNonFatalErrorMetric(@NonNull String str, @NonNull String str2) {
        return new MobilyticsMetricsCounterWrapper(String.format("%s%s%s", NON_FATAL_ERROR_PREFIX, ":", str2), str);
    }
}
