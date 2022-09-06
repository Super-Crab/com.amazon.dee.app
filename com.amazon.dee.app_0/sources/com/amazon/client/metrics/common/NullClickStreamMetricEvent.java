package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.ECommerceInfo;
import com.amazon.client.metrics.common.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.UsageInfo;
import com.amazon.client.metrics.common.internal.android.AndroidNullClickStreamMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSNullClickStreamMetricEvent;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.Collection;
/* loaded from: classes11.dex */
public class NullClickStreamMetricEvent extends NullMetricEvent implements ClickStreamMetricsEvent {
    private final ClickStreamMetricsEvent mDelegateNullClickStreamMetricEvent;

    public NullClickStreamMetricEvent(String str, String str2) {
        this(str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public void addClickStreamInfo(ClickStreamInfo clickStreamInfo) {
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public Collection<ClickStreamInfo> getClickStreamInfo() {
        return this.mDelegateNullClickStreamMetricEvent.getClickStreamInfo();
    }

    @Override // com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent
    public String getRequestId() {
        return this.mDelegateNullClickStreamMetricEvent.getRequestId();
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
        return this.mDelegateNullClickStreamMetricEvent.validateMetricEvent();
    }

    public NullClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        super(str, str2, metricEventType);
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateNullClickStreamMetricEvent = new FireOSNullClickStreamMetricEvent(new com.amazon.client.metrics.NullClickStreamMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name())));
        } else {
            this.mDelegateNullClickStreamMetricEvent = new AndroidNullClickStreamMetricEvent(new com.amazon.client.metrics.thirdparty.NullClickStreamMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name())));
        }
    }

    @Override // com.amazon.client.metrics.common.NullMetricEvent
    /* renamed from: getDelegateMetricEvent */
    public ClickStreamMetricsEvent mo2877getDelegateMetricEvent() {
        return this.mDelegateNullClickStreamMetricEvent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NullClickStreamMetricEvent(com.amazon.client.metrics.NullClickStreamMetricEvent nullClickStreamMetricEvent) {
        super((com.amazon.client.metrics.NullMetricEvent) nullClickStreamMetricEvent);
        this.mDelegateNullClickStreamMetricEvent = new FireOSNullClickStreamMetricEvent(nullClickStreamMetricEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NullClickStreamMetricEvent(com.amazon.client.metrics.thirdparty.NullClickStreamMetricEvent nullClickStreamMetricEvent) {
        super(nullClickStreamMetricEvent);
        this.mDelegateNullClickStreamMetricEvent = new AndroidNullClickStreamMetricEvent(nullClickStreamMetricEvent);
    }
}
