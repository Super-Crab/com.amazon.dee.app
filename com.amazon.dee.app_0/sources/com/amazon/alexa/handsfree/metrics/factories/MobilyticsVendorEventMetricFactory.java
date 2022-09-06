package com.amazon.alexa.handsfree.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsMetricsCounterWrapper;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.factories.VendorEventMetricFactory;
/* loaded from: classes8.dex */
class MobilyticsVendorEventMetricFactory implements VendorEventMetricFactory {
    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.VendorEventMetricFactory
    public Metric newVendorEventMetric(@NonNull String str, @NonNull String str2) {
        return new MobilyticsMetricsCounterWrapper(str2, str);
    }
}
