package com.amazon.alexa.handsfree.protocols.metrics.factories;
/* loaded from: classes8.dex */
public interface MetricFactoryProvider {
    ErrorEventMetricFactory getErrorEventMetricFactory();

    HandsFreeSetupMetricsFactory getHandsFreeSetupMetricsFactory();

    NotificationMetricFactory getNotificationMetricFactory();

    OperationalEventMetricFactory getOperationalMetricFactory();

    PerformanceMetricFactory getPerformanceMetricFactory();

    UserActionMetricFactory getUserActionMetricFactory();

    VendorEventMetricFactory getVendorEventMetricFactory();

    VoiceMetadataMetricFactory getVoiceMetadataMetricFactory();

    VoiceTrainingMetricsFactory getVoiceTrainingMetricsFactory();
}
