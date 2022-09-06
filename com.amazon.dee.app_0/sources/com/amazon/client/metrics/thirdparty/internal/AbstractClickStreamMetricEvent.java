package com.amazon.client.metrics.thirdparty.internal;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.DataPointType;
import com.amazon.client.metrics.thirdparty.MetricEventType;
import com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamHelper;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public abstract class AbstractClickStreamMetricEvent extends BasicMetricEvent implements GenericClickStreamMetricEvent {
    private final Map<String, ClickStreamInfo> mInfoStructures;
    private final String mRequestId;

    public AbstractClickStreamMetricEvent(String str, String str2) {
        this(str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public void addClickStreamInfo(ClickStreamInfo clickStreamInfo) {
        if (clickStreamInfo != null) {
            this.mInfoStructures.put(clickStreamInfo.getClass().getName(), clickStreamInfo);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.internal.BasicMetricEvent, com.amazon.client.metrics.thirdparty.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        List<DataPoint> asDataPoints = super.getAsDataPoints();
        for (ClickStreamInfo clickStreamInfo : getClickStreamInfo()) {
            ClickStreamHelper.addDatapointsToList(asDataPoints, clickStreamInfo);
        }
        asDataPoints.add(new DataPoint(ClickStreamData.REQUEST_ID.getName(), this.mRequestId, 1, DataPointType.DV));
        return asDataPoints;
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public Collection<ClickStreamInfo> getClickStreamInfo() {
        return this.mInfoStructures.values();
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public String getRequestId() {
        return this.mRequestId;
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public void removeClickStreamInfo(Class<? extends ClickStreamInfo> cls) {
        if (cls != null) {
            this.mInfoStructures.remove(cls.getName());
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent
    public boolean validateMetricEvent() {
        return !this.mInfoStructures.isEmpty();
    }

    public AbstractClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        this(str, str2, metricEventType, false);
    }

    public AbstractClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        super(str, str2, metricEventType, z);
        this.mInfoStructures = new HashMap();
        this.mRequestId = ClickStreamHelper.generateRequestId();
    }
}
