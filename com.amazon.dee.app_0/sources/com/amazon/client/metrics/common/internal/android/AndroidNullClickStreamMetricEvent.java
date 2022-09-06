package com.amazon.client.metrics.common.internal.android;

import com.amazon.client.metrics.common.ClickStreamMetricsEvent;
import com.amazon.client.metrics.common.clickstream.ClickStreamDataConverter;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.ECommerceInfo;
import com.amazon.client.metrics.common.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.UsageInfo;
import com.amazon.client.metrics.thirdparty.NullClickStreamMetricEvent;
import java.util.Collection;
/* loaded from: classes11.dex */
public class AndroidNullClickStreamMetricEvent extends AndroidNullMetricEvent implements ClickStreamMetricsEvent {
    private final NullClickStreamMetricEvent mDelegateThirdPartyNullClickStreamMetricEvent;

    public AndroidNullClickStreamMetricEvent(NullClickStreamMetricEvent nullClickStreamMetricEvent) {
        super(nullClickStreamMetricEvent);
        this.mDelegateThirdPartyNullClickStreamMetricEvent = nullClickStreamMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public void addClickStreamInfo(ClickStreamInfo clickStreamInfo) {
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public Collection<ClickStreamInfo> getClickStreamInfo() {
        return ClickStreamDataConverter.convertClickStreamInfoCollection_fromThirdPartyToCommon(this.mDelegateThirdPartyNullClickStreamMetricEvent.getClickStreamInfo());
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public String getRequestId() {
        return this.mDelegateThirdPartyNullClickStreamMetricEvent.getRequestId();
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
        return this.mDelegateThirdPartyNullClickStreamMetricEvent.validateMetricEvent();
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidNullMetricEvent, com.amazon.client.metrics.common.internal.android.AndroidMetricEvent
    /* renamed from: getDelegateMetricEvent  reason: collision with other method in class */
    public NullClickStreamMetricEvent mo2883getDelegateMetricEvent() {
        return this.mDelegateThirdPartyNullClickStreamMetricEvent;
    }
}
