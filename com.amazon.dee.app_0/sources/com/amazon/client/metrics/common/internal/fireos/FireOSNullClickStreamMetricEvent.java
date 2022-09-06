package com.amazon.client.metrics.common.internal.fireos;

import com.amazon.client.metrics.NullClickStreamMetricEvent;
import com.amazon.client.metrics.common.ClickStreamMetricsEvent;
import com.amazon.client.metrics.common.clickstream.ClickStreamDataConverter;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.ECommerceInfo;
import com.amazon.client.metrics.common.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.UsageInfo;
import java.util.Collection;
/* loaded from: classes11.dex */
public class FireOSNullClickStreamMetricEvent extends FireOSNullMetricEvent implements ClickStreamMetricsEvent {
    private final NullClickStreamMetricEvent mDelegateFirstPartyNullClickStreamMetricEvent;

    public FireOSNullClickStreamMetricEvent(NullClickStreamMetricEvent nullClickStreamMetricEvent) {
        super(nullClickStreamMetricEvent);
        this.mDelegateFirstPartyNullClickStreamMetricEvent = nullClickStreamMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public void addClickStreamInfo(ClickStreamInfo clickStreamInfo) {
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public Collection<ClickStreamInfo> getClickStreamInfo() {
        return ClickStreamDataConverter.convertClickStreamInfoCollection_fromFirstPartyToCommon(this.mDelegateFirstPartyNullClickStreamMetricEvent.getClickStreamInfo());
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public String getRequestId() {
        return this.mDelegateFirstPartyNullClickStreamMetricEvent.getRequestId();
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public void removeClickStreamInfo(Class<? extends ClickStreamInfo> cls) {
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setECommerceInfo(ECommerceInfo eCommerceInfo) {
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setImpressionTrackingData(ImpressionTrackingData impressionTrackingData) {
    }

    @Override // com.amazon.client.metrics.common.ClickStreamMetricsEvent
    public void setUsageInfo(UsageInfo usageInfo) {
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public boolean validateMetricEvent() {
        return this.mDelegateFirstPartyNullClickStreamMetricEvent.validateMetricEvent();
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSNullMetricEvent, com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent
    /* renamed from: getDelegateMetricEvent  reason: collision with other method in class */
    public NullClickStreamMetricEvent mo2886getDelegateMetricEvent() {
        return this.mDelegateFirstPartyNullClickStreamMetricEvent;
    }
}
