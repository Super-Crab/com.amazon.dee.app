package com.amazon.alexa.handsfree.metrics.factories;

import com.amazon.alexa.handsfree.protocols.metrics.factories.ErrorEventMetricFactory;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricsFactory;
import com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider;
import com.amazon.alexa.handsfree.protocols.metrics.factories.NotificationMetricFactory;
import com.amazon.alexa.handsfree.protocols.metrics.factories.OperationalEventMetricFactory;
import com.amazon.alexa.handsfree.protocols.metrics.factories.PerformanceMetricFactory;
import com.amazon.alexa.handsfree.protocols.metrics.factories.UserActionMetricFactory;
import com.amazon.alexa.handsfree.protocols.metrics.factories.VendorEventMetricFactory;
import com.amazon.alexa.handsfree.protocols.metrics.factories.VoiceMetadataMetricFactory;
import com.amazon.alexa.handsfree.protocols.metrics.factories.VoiceTrainingMetricsFactory;
/* loaded from: classes8.dex */
public class MobilyticsMetricFactoryProvider implements MetricFactoryProvider {
    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public ErrorEventMetricFactory getErrorEventMetricFactory() {
        return new MobilyticsErrorEventMetricFactory();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public HandsFreeSetupMetricsFactory getHandsFreeSetupMetricsFactory() {
        return new MobilyticsHandsFreeSetupMetricsFactory();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public NotificationMetricFactory getNotificationMetricFactory() {
        return new MobilyticsNotificationMetricFactory();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public OperationalEventMetricFactory getOperationalMetricFactory() {
        return new MobilyticsOperationalEventMetricFactory();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public PerformanceMetricFactory getPerformanceMetricFactory() {
        return new MobilyticsPerformanceMetricFactory();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public UserActionMetricFactory getUserActionMetricFactory() {
        return new MobilyticsUserActionMetricFactory();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public VendorEventMetricFactory getVendorEventMetricFactory() {
        return new MobilyticsVendorEventMetricFactory();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public VoiceMetadataMetricFactory getVoiceMetadataMetricFactory() {
        return new MobilyticsVoiceMetadataMetricFactory();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider
    public VoiceTrainingMetricsFactory getVoiceTrainingMetricsFactory() {
        return new MobilyticsVoiceTrainingMetricsFactory();
    }
}
