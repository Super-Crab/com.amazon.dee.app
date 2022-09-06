package com.amazon.device.crashmanager.processor;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.utils.AmazonPackageLookup;
import com.amazon.device.utils.det.DetUtil;
/* loaded from: classes12.dex */
public class MetricsHeaderProcessorFactory {
    private AmazonPackageLookup mAmazonPackageLookup;
    private MetricEvent mMetricEvent;

    public MetricsHeaderProcessorFactory(AmazonPackageLookup amazonPackageLookup, MetricEvent metricEvent) {
        if (amazonPackageLookup != null) {
            if (metricEvent != null) {
                this.mAmazonPackageLookup = amazonPackageLookup;
                this.mMetricEvent = metricEvent;
                return;
            }
            throw new IllegalArgumentException("Metric event must not be null.");
        }
        throw new IllegalArgumentException("Amazon Package Lookup must not be null.");
    }

    public MetricsHeaderProcessor construct(DetUtil.HeaderProcessor headerProcessor) {
        if (headerProcessor != null) {
            return new MetricsHeaderProcessor(headerProcessor, this.mAmazonPackageLookup, this.mMetricEvent);
        }
        throw new IllegalArgumentException("Header processor must not be null.");
    }
}
