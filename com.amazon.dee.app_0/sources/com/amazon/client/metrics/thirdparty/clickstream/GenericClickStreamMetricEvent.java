package com.amazon.client.metrics.thirdparty.clickstream;

import com.amazon.client.metrics.thirdparty.MetricEvent;
import java.util.Collection;
/* loaded from: classes11.dex */
public interface GenericClickStreamMetricEvent extends MetricEvent {
    void addClickStreamInfo(ClickStreamInfo clickStreamInfo);

    Collection<ClickStreamInfo> getClickStreamInfo();

    String getRequestId();

    void removeClickStreamInfo(Class<? extends ClickStreamInfo> cls);

    boolean validateMetricEvent();
}
