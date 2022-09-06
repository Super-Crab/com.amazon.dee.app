package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.clickstream.ECommerceInfo;
import com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent;
import com.amazon.client.metrics.common.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.common.clickstream.UsageInfo;
/* loaded from: classes11.dex */
public interface ClickStreamMetricsEvent extends GenericClickStreamMetricEvent {
    void setECommerceInfo(ECommerceInfo eCommerceInfo);

    @Deprecated
    void setImpressionTrackingData(ImpressionTrackingData impressionTrackingData);

    void setUsageInfo(UsageInfo usageInfo);
}
