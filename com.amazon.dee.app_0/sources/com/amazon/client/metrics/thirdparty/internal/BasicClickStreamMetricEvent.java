package com.amazon.client.metrics.thirdparty.internal;

import com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent;
import com.amazon.client.metrics.thirdparty.MetricEventType;
import com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo;
import com.amazon.client.metrics.thirdparty.clickstream.EventBasedUsageInfo;
import com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.thirdparty.clickstream.UsageInfo;
/* loaded from: classes11.dex */
public class BasicClickStreamMetricEvent extends AbstractClickStreamMetricEvent implements ClickStreamMetricsEvent {
    public BasicClickStreamMetricEvent(String str, String str2) {
        super(str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent
    public void setECommerceInfo(ECommerceInfo eCommerceInfo) {
        if (eCommerceInfo == null) {
            removeClickStreamInfo(ECommerceInfo.class);
        } else {
            addClickStreamInfo(eCommerceInfo);
        }
    }

    public void setEventBasedUsageInfo(EventBasedUsageInfo eventBasedUsageInfo) {
        if (eventBasedUsageInfo == null) {
            removeClickStreamInfo(EventBasedUsageInfo.class);
        } else {
            addClickStreamInfo(eventBasedUsageInfo);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent
    public void setImpressionTrackingData(ImpressionTrackingData impressionTrackingData) {
        if (impressionTrackingData == null) {
            removeClickStreamInfo(ImpressionTrackingData.class);
        } else {
            addClickStreamInfo(impressionTrackingData);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent
    public void setUsageInfo(UsageInfo usageInfo) {
        if (usageInfo == null) {
            removeClickStreamInfo(UsageInfo.class);
        } else {
            addClickStreamInfo(usageInfo);
        }
    }

    public BasicClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        super(str, str2, metricEventType, false);
    }

    public BasicClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        super(str, str2, metricEventType, z);
    }
}
