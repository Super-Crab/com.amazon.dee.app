package com.amazon.client.metrics.common.internal.android;

import android.content.Context;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.ClickStreamMetricsEvent;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventConverter;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent;
import com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl;
/* loaded from: classes11.dex */
public class AndroidMetricsFactory implements MetricsFactory {
    private static AndroidMetricsFactory sAndroidMetricsFactory;
    private final com.amazon.client.metrics.thirdparty.MetricsFactory mDelegateThirdPartyMetricsFactory;

    private AndroidMetricsFactory(Context context) {
        if (context != null) {
            this.mDelegateThirdPartyMetricsFactory = AndroidMetricsFactoryImpl.getInstance(context);
            return;
        }
        throw new NullPointerException("Context may not be null");
    }

    public static synchronized AndroidMetricsFactory getInstance(Context context) {
        AndroidMetricsFactory androidMetricsFactory;
        synchronized (AndroidMetricsFactory.class) {
            if (sAndroidMetricsFactory == null) {
                sAndroidMetricsFactory = new AndroidMetricsFactory(context);
            }
            androidMetricsFactory = sAndroidMetricsFactory;
        }
        return androidMetricsFactory;
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2) {
        return MetricEventConverter.convertClickStreamMetricsEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createClickStreamMetricEvent(str, str2));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    /* renamed from: createClickStreamWeblabTrigger */
    public GenericClickStreamMetricEvent mo2878createClickStreamWeblabTrigger(String str, String str2, String str3, String str4) {
        return MetricEventConverter.convertGenericClickStreamMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.mo2889createClickStreamWeblabTrigger(str, str2, str3, str4));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createConcurrentMetricEvent(str, str2));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createMetricEvent(str, str2));
    }

    public com.amazon.client.metrics.thirdparty.MetricsFactory getDelegateMetricsFactory() {
        return this.mDelegateThirdPartyMetricsFactory;
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public String getSessionID() {
        return this.mDelegateThirdPartyMetricsFactory.getSessionID();
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
        this.mDelegateThirdPartyMetricsFactory.record(MetricEventConverter.convertMetricEvent_fromCommonToThirdParty(metricEvent), com.amazon.client.metrics.thirdparty.Priority.valueOf(priority.name()), com.amazon.client.metrics.thirdparty.Channel.valueOf(channel.name()));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return MetricEventConverter.convertClickStreamMetricsEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createClickStreamMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name())));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createConcurrentMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name())));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name())));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return MetricEventConverter.convertClickStreamMetricsEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createClickStreamMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name()), z));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createConcurrentMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name()), z));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name()), z));
    }
}
