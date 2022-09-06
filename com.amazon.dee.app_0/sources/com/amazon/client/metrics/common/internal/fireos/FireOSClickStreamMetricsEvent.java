package com.amazon.client.metrics.common.internal.fireos;

import com.amazon.client.metrics.common.ClickStreamMetricsEvent;
import com.amazon.client.metrics.common.clickstream.ClickStreamDataConverter;
import com.amazon.client.metrics.common.clickstream.ECommerceInfo;
import com.amazon.client.metrics.common.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.UsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSGenericClickStreamMetricEvent;
/* loaded from: classes11.dex */
public class FireOSClickStreamMetricsEvent extends FireOSGenericClickStreamMetricEvent implements ClickStreamMetricsEvent {
    private final com.amazon.client.metrics.ClickStreamMetricsEvent mDelegateFirstPartyClickStreamsMetricEvent;

    public FireOSClickStreamMetricsEvent(com.amazon.client.metrics.ClickStreamMetricsEvent clickStreamMetricsEvent) {
        super(clickStreamMetricsEvent);
        this.mDelegateFirstPartyClickStreamsMetricEvent = clickStreamMetricsEvent;
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setECommerceInfo(ECommerceInfo eCommerceInfo) {
        this.mDelegateFirstPartyClickStreamsMetricEvent.setECommerceInfo(ClickStreamDataConverter.convertECommerceInfo_fromCommonToFirstParty(eCommerceInfo));
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setImpressionTrackingData(ImpressionTrackingData impressionTrackingData) {
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setUsageInfo(UsageInfo usageInfo) {
        this.mDelegateFirstPartyClickStreamsMetricEvent.setUsageInfo(ClickStreamDataConverter.convertUsageInfo_fromCommonToFirstParty(usageInfo));
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSGenericClickStreamMetricEvent, com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent
    /* renamed from: getDelegateMetricEvent */
    public com.amazon.client.metrics.ClickStreamMetricsEvent mo2886getDelegateMetricEvent() {
        return this.mDelegateFirstPartyClickStreamsMetricEvent;
    }
}
