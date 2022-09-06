package com.amazon.client.metrics.common.internal.android;

import com.amazon.client.metrics.common.ClickStreamMetricsEvent;
import com.amazon.client.metrics.common.clickstream.ClickStreamDataConverter;
import com.amazon.client.metrics.common.clickstream.ECommerceInfo;
import com.amazon.client.metrics.common.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.UsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidGenericClickStreamMetricEvent;
/* loaded from: classes11.dex */
public class AndroidClickStreamMetricsEvent extends AndroidGenericClickStreamMetricEvent implements ClickStreamMetricsEvent {
    private final com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent mDelegateThirdPartyClickStreamsMetricEvent;

    public AndroidClickStreamMetricsEvent(com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent clickStreamMetricsEvent) {
        super(clickStreamMetricsEvent);
        this.mDelegateThirdPartyClickStreamsMetricEvent = clickStreamMetricsEvent;
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setECommerceInfo(ECommerceInfo eCommerceInfo) {
        this.mDelegateThirdPartyClickStreamsMetricEvent.setECommerceInfo(ClickStreamDataConverter.convertECommerceInfo_fromCommonToThirdParty(eCommerceInfo));
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setImpressionTrackingData(ImpressionTrackingData impressionTrackingData) {
        this.mDelegateThirdPartyClickStreamsMetricEvent.setImpressionTrackingData(ClickStreamDataConverter.convertImpressionTrackingData_fromCommonToThirdParty(impressionTrackingData));
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setUsageInfo(UsageInfo usageInfo) {
        this.mDelegateThirdPartyClickStreamsMetricEvent.setUsageInfo(ClickStreamDataConverter.convertUsageInfo_fromCommonToThirdParty(usageInfo));
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.android.AndroidGenericClickStreamMetricEvent, com.amazon.client.metrics.common.internal.android.AndroidMetricEvent
    /* renamed from: getDelegateMetricEvent */
    public com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent mo2883getDelegateMetricEvent() {
        return this.mDelegateThirdPartyClickStreamsMetricEvent;
    }
}
