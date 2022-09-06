package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo;
import com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.thirdparty.clickstream.UsageInfo;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes11.dex */
public class NullClickStreamMetricEvent extends NullMetricEvent implements ClickStreamMetricsEvent {
    private static final String NULL_REQUEST_ID = " ";

    public NullClickStreamMetricEvent(String str, String str2) {
        this(str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public void addClickStreamInfo(ClickStreamInfo clickStreamInfo) {
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public Collection<ClickStreamInfo> getClickStreamInfo() {
        return new ArrayList(0);
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public String getRequestId() {
        return " ";
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public void removeClickStreamInfo(Class<? extends ClickStreamInfo> cls) {
    }

    @Override // com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent
    public void setECommerceInfo(ECommerceInfo eCommerceInfo) {
    }

    @Override // com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent
    public void setImpressionTrackingData(ImpressionTrackingData impressionTrackingData) {
    }

    @Override // com.amazon.client.metrics.thirdparty.ClickStreamMetricsEvent
    public void setUsageInfo(UsageInfo usageInfo) {
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public boolean validateMetricEvent() {
        return false;
    }

    public NullClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        super(str, str2, metricEventType);
    }
}
