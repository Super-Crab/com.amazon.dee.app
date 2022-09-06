package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage;
/* loaded from: classes11.dex */
public class ProtocolBuffersEncodedMetricEntry implements EncodedMetricEntry {
    private DeviceMetricsMessage.MetricEntryMessage mMetricEntryMessage;

    public ProtocolBuffersEncodedMetricEntry(DeviceMetricsMessage.MetricEntryMessage metricEntryMessage) {
        if (metricEntryMessage != null) {
            this.mMetricEntryMessage = metricEntryMessage;
            return;
        }
        throw new IllegalArgumentException("MetricEntryMessage is null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProtocolBuffersEncodedMetricEntry.class != obj.getClass()) {
            return false;
        }
        return this.mMetricEntryMessage.equals(((ProtocolBuffersEncodedMetricEntry) obj).mo2992getEncodedMetricEntry());
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry
    public int getEncodedSize() {
        return this.mMetricEntryMessage.getSerializedSize();
    }

    public int hashCode() {
        return this.mMetricEntryMessage.hashCode();
    }

    public String toString() {
        return this.mMetricEntryMessage.toString();
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry
    /* renamed from: getEncodedMetricEntry */
    public DeviceMetricsMessage.MetricEntryMessage mo2992getEncodedMetricEntry() {
        return this.mMetricEntryMessage;
    }
}
