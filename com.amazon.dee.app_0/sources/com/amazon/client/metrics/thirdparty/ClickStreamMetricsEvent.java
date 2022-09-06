package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.clickstream.ECommerceInfo;
import com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent;
import com.amazon.client.metrics.thirdparty.clickstream.ImpressionTrackingData;
import com.amazon.client.metrics.thirdparty.clickstream.UsageInfo;
/* loaded from: classes11.dex */
public interface ClickStreamMetricsEvent extends GenericClickStreamMetricEvent {
    void setECommerceInfo(ECommerceInfo eCommerceInfo);

    void setImpressionTrackingData(ImpressionTrackingData impressionTrackingData);

    void setUsageInfo(UsageInfo usageInfo);
}
