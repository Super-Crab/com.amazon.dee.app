package com.amazon.client.metrics.common.internal.fireos;

import android.content.Context;
import com.amazon.client.metrics.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.ClickStreamMetricsEvent;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventConverter;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent;
/* loaded from: classes11.dex */
public class FireOSMetricsFactory implements MetricsFactory {
    private static FireOSMetricsFactory sFireOSMetricsFactory;
    private final com.amazon.client.metrics.MetricsFactory mDelegateFirstPartyMetricsFactory;

    private FireOSMetricsFactory(Context context) {
        if (context != null) {
            this.mDelegateFirstPartyMetricsFactory = AndroidMetricsFactoryImpl.getInstance(context);
            return;
        }
        throw new NullPointerException("Context may not be null");
    }

    public static synchronized FireOSMetricsFactory getInstance(Context context) {
        FireOSMetricsFactory fireOSMetricsFactory;
        synchronized (FireOSMetricsFactory.class) {
            if (sFireOSMetricsFactory == null) {
                sFireOSMetricsFactory = new FireOSMetricsFactory(context);
            }
            fireOSMetricsFactory = sFireOSMetricsFactory;
        }
        return fireOSMetricsFactory;
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2) {
        return MetricEventConverter.convertClickStreamMetricsEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createClickStreamMetricEvent(str, str2));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    /* renamed from: createClickStreamWeblabTrigger */
    public GenericClickStreamMetricEvent mo2878createClickStreamWeblabTrigger(String str, String str2, String str3, String str4) {
        return MetricEventConverter.convertGenericClickStreamMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createClickStreamWeblabTrigger(str, str2, str3, str4));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createConcurrentMetricEvent(str, str2));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createMetricEvent(str, str2));
    }

    public com.amazon.client.metrics.MetricsFactory getDelegateMetricsFactory() {
        return this.mDelegateFirstPartyMetricsFactory;
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public String getSessionID() {
        return this.mDelegateFirstPartyMetricsFactory.getSessionID();
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent) {
        record(metricEvent, Priority.NORMAL, Channel.ANONYMOUS);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent, Priority priority) {
        record(metricEvent, priority, Channel.ANONYMOUS);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent, Priority priority, Channel channel) {
        if (Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.equals(priority)) {
            priority = Priority.NORMAL;
            channel = Channel.NON_ANONYMOUS;
        } else if (Priority.RESERVED_FOR_LOCATION_SERVICE.equals(priority)) {
            priority = Priority.NORMAL;
            channel = Channel.LOCATION;
        }
        this.mDelegateFirstPartyMetricsFactory.record(MetricEventConverter.convertMetricEvent_fromCommonToFirstParty(metricEvent), com.amazon.client.metrics.Priority.valueOf(priority.name()), com.amazon.client.metrics.Channel.valueOf(channel.name()));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return MetricEventConverter.convertClickStreamMetricsEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createClickStreamMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name())));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createConcurrentMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name())));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name())));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return MetricEventConverter.convertClickStreamMetricsEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createClickStreamMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name()), z));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createConcurrentMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name()), z));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name()), z));
    }
}
