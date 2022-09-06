package com.amazon.client.metrics.common.internal.android;

import android.text.TextUtils;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventConverter;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.client.metrics.common.Priority;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporterImpl;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class AndroidPeriodicMetricReporterImpl implements PeriodicMetricReporter {
    private PeriodicMetricReporterImpl mDelegateThirdPartyPeriodicMetricReporter;

    public AndroidPeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority, Channel channel) {
        if (metricsFactory != null) {
            if (metricsFactory instanceof AndroidMetricsFactory) {
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
                        this.mDelegateThirdPartyPeriodicMetricReporter = createThirdPartyPeriodicMetricReporter((AndroidMetricsFactory) metricsFactory, str, str2, metricEventType, priority, channel);
                        return;
                    }
                    throw new NullPointerException("Channel may not be null");
                }
                throw new IllegalArgumentException("Program may not be null or empty");
            }
            throw new IllegalArgumentException("MetricsFactory must be instance of AndroidMetricsFactory");
        }
        throw new NullPointerException("MetricsFactory may not be null");
    }

    private PeriodicMetricReporterImpl createThirdPartyPeriodicMetricReporter(AndroidMetricsFactory androidMetricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority, Channel channel) {
        com.amazon.client.metrics.thirdparty.Channel valueOf;
        com.amazon.client.metrics.thirdparty.Priority valueOf2;
        com.amazon.client.metrics.thirdparty.MetricsFactory delegateMetricsFactory = androidMetricsFactory.getDelegateMetricsFactory();
        com.amazon.client.metrics.thirdparty.MetricEventType valueOf3 = com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name());
        if (Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.equals(priority)) {
            valueOf = com.amazon.client.metrics.thirdparty.Channel.NON_ANONYMOUS;
            valueOf2 = com.amazon.client.metrics.thirdparty.Priority.NORMAL;
        } else if (Priority.RESERVED_FOR_LOCATION_SERVICE.equals(priority)) {
            valueOf = com.amazon.client.metrics.thirdparty.Channel.LOCATION;
            valueOf2 = com.amazon.client.metrics.thirdparty.Priority.NORMAL;
        } else {
            valueOf = com.amazon.client.metrics.thirdparty.Channel.valueOf(channel.name());
            valueOf2 = com.amazon.client.metrics.thirdparty.Priority.valueOf(priority.name());
        }
        return new PeriodicMetricReporterImpl(delegateMetricsFactory, str, str2, valueOf3, valueOf2, valueOf);
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent createTrackedMetricEvent(String str, String str2) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyPeriodicMetricReporter.createTrackedMetricEvent(str, str2));
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent getMetricEvent() {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyPeriodicMetricReporter.getMetricEvent());
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void shutdown() {
        this.mDelegateThirdPartyPeriodicMetricReporter.shutdown();
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void startRecordingPeriodically(long j, TimeUnit timeUnit) {
        this.mDelegateThirdPartyPeriodicMetricReporter.startRecordingPeriodically(j, timeUnit);
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void stopTrackingMetricEvent(MetricEvent metricEvent) {
        com.amazon.client.metrics.thirdparty.MetricEvent convertMetricEvent_fromCommonToThirdParty = MetricEventConverter.convertMetricEvent_fromCommonToThirdParty(metricEvent);
        if (convertMetricEvent_fromCommonToThirdParty == null) {
            return;
        }
        this.mDelegateThirdPartyPeriodicMetricReporter.stopTrackingMetricEvent(convertMetricEvent_fromCommonToThirdParty);
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent createTrackedMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyPeriodicMetricReporter.createTrackedMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name())));
    }

    public AndroidPeriodicMetricReporterImpl(PeriodicMetricReporterImpl periodicMetricReporterImpl) {
        if (periodicMetricReporterImpl != null) {
            this.mDelegateThirdPartyPeriodicMetricReporter = periodicMetricReporterImpl;
            return;
        }
        throw new NullPointerException("Delegate ThirdParty PeriodicMetricReporter may not be null");
    }
}
