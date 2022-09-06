package com.amazon.alexa.handsfree.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsUserInteractionEventWrapper;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricsFactory;
/* loaded from: classes8.dex */
class MobilyticsHandsFreeSetupMetricsFactory implements HandsFreeSetupMetricsFactory {
    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricsFactory
    @NonNull
    public Metric buildHandsFreeSetupMetric(@NonNull String str, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData) {
        return new MobilyticsUserInteractionEventWrapper(handsFreeSetupMetricData.getEventType(), "click", handsFreeSetupMetricData.getComponent(), handsFreeSetupMetricData.getPageType()).withInputType2(handsFreeSetupMetricData.getActionType());
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricsFactory
    @NonNull
    public Metric buildHandsFreeSetupMetric(@NonNull String str, @NonNull String str2, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData) {
        return new MobilyticsUserInteractionEventWrapper(handsFreeSetupMetricData.getEventType(), "click", handsFreeSetupMetricData.getComponent(), handsFreeSetupMetricData.getPageType()).withInputType2(handsFreeSetupMetricData.getActionType()).withEnrollmentType(str2);
    }
}
