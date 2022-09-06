package com.amazon.client.metrics.common.internal.fireos;

import android.text.TextUtils;
import com.amazon.client.metrics.PeriodicMetricReporterImpl;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventConverter;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.client.metrics.common.Priority;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class FireOSPeriodicMetricReporterImpl implements PeriodicMetricReporter {
    private PeriodicMetricReporterImpl mDelegateFirstPartyPeriodicMetricReporter;

    public FireOSPeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority, Channel channel) {
        if (metricsFactory != null) {
            if (metricsFactory instanceof FireOSMetricsFactory) {
                if (!TextUtils.isEmpty(str)) {
                    if (TextUtils.isEmpty(str2)) {
                        throw new IllegalArgumentException("Source may not be null or empty");
                    }
                    if (metricEventType == null) {
                        throw new NullPointerException("MetricEventType may not be null");
                    }
                    if (priority == null) {
                        throw new NullPointerException("Priority may not be null");
                    }
                    if (channel != null) {
                        this.mDelegateFirstPartyPeriodicMetricReporter = createFirstPartyPeriodicMetricReporter((FireOSMetricsFactory) metricsFactory, str, str2, metricEventType, priority, channel);
                        return;
                    }
                    throw new NullPointerException("Channel may not be null");
                }
                throw new IllegalArgumentException("Program may not be null or empty");
            }
            throw new IllegalArgumentException("MetricsFactory must be instance of FireOSMetricsFactory");
        }
        throw new NullPointerException("MetricsFactory may not be null");
    }

    private PeriodicMetricReporterImpl createFirstPartyPeriodicMetricReporter(FireOSMetricsFactory fireOSMetricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority, Channel channel) {
        com.amazon.client.metrics.Channel valueOf;
        com.amazon.client.metrics.Priority valueOf2;
        com.amazon.client.metrics.MetricsFactory delegateMetricsFactory = fireOSMetricsFactory.getDelegateMetricsFactory();
        com.amazon.client.metrics.MetricEventType valueOf3 = com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name());
        if (Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.equals(priority)) {
            valueOf = com.amazon.client.metrics.Channel.NON_ANONYMOUS;
            valueOf2 = com.amazon.client.metrics.Priority.NORMAL;
        } else if (Priority.RESERVED_FOR_LOCATION_SERVICE.equals(priority)) {
            valueOf = com.amazon.client.metrics.Channel.LOCATION;
            valueOf2 = com.amazon.client.metrics.Priority.NORMAL;
        } else {
            valueOf = com.amazon.client.metrics.Channel.valueOf(channel.name());
            valueOf2 = com.amazon.client.metrics.Priority.valueOf(priority.name());
        }
        return new PeriodicMetricReporterImpl(delegateMetricsFactory, str, str2, valueOf3, valueOf2, valueOf);
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent createTrackedMetricEvent(String str, String str2) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyPeriodicMetricReporter.createTrackedMetricEvent(str, str2));
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent getMetricEvent() {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyPeriodicMetricReporter.getMetricEvent());
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void shutdown() {
        this.mDelegateFirstPartyPeriodicMetricReporter.shutdown();
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void startRecordingPeriodically(long j, TimeUnit timeUnit) {
        this.mDelegateFirstPartyPeriodicMetricReporter.startRecordingPeriodically(j, timeUnit);
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void stopTrackingMetricEvent(MetricEvent metricEvent) {
        com.amazon.client.metrics.MetricEvent convertMetricEvent_fromCommonToFirstParty = MetricEventConverter.convertMetricEvent_fromCommonToFirstParty(metricEvent);
        if (convertMetricEvent_fromCommonToFirstParty == null) {
            return;
        }
        this.mDelegateFirstPartyPeriodicMetricReporter.stopTrackingMetricEvent(convertMetricEvent_fromCommonToFirstParty);
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent createTrackedMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyPeriodicMetricReporter.createTrackedMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name())));
    }

    public FireOSPeriodicMetricReporterImpl(PeriodicMetricReporterImpl periodicMetricReporterImpl) {
        if (periodicMetricReporterImpl != null) {
            this.mDelegateFirstPartyPeriodicMetricReporter = periodicMetricReporterImpl;
            return;
        }
        throw new NullPointerException("Delegate FirstParty PeriodicMetricReporter may not be null");
    }
}
