package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class MetricBatch {
    private long mBatchSizeInBytes;
    private boolean mContainsUserMetrics;
    private String mDeviceSerialNumber;
    private String mDeviceType;
    private final List<EncodedMetricEntry> mSerializedMetricEntryList = new ArrayList();
    private final Map<String, String> mDeviceInfoMap = new HashMap();

    public MetricBatch() {
    }

    public synchronized void addEntry(EncodedMetricEntry encodedMetricEntry) {
        this.mSerializedMetricEntryList.add(encodedMetricEntry);
        this.mBatchSizeInBytes += encodedMetricEntry.getEncodedSize();
    }

    public synchronized boolean containsUserMetrics() {
        return this.mContainsUserMetrics;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MetricBatch.class != obj.getClass()) {
            return false;
        }
        MetricBatch metricBatch = (MetricBatch) obj;
        if (getMetricEntryCount() != metricBatch.getMetricEntryCount()) {
            return false;
        }
        for (int i = 0; i < getMetricEntryCount(); i++) {
            if (!getMetricEntry(i).equals(metricBatch.getMetricEntry(i))) {
                return false;
            }
        }
        return true;
    }

    public synchronized long getBatchSizeInBytes() {
        return this.mBatchSizeInBytes;
    }

    public Map<String, String> getDeviceInfoMap() {
        return this.mDeviceInfoMap;
    }

    public String getDeviceSerialNumber() {
        return this.mDeviceSerialNumber;
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public synchronized EncodedMetricEntry getMetricEntry(int i) {
        return this.mSerializedMetricEntryList.get(i);
    }

    public synchronized int getMetricEntryCount() {
        return this.mSerializedMetricEntryList.size();
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < getMetricEntryCount(); i2++) {
            i = (i * 31) + getMetricEntry(i2).hashCode();
        }
        return i;
    }

    public void putMetricsDeviceInfo(Map<String, String> map) {
        if (map != null) {
            this.mDeviceInfoMap.putAll(map);
            this.mDeviceSerialNumber = this.mDeviceInfoMap.remove("deviceId");
            this.mDeviceType = this.mDeviceInfoMap.remove("deviceType");
            String str = this.mDeviceSerialNumber;
            if (str != null && !str.trim().isEmpty()) {
                return;
            }
            throw new IllegalArgumentException("Device Info Map missing device ID");
        }
        throw new IllegalArgumentException("Device Info Map is null");
    }

    public synchronized void setContainsUserMetrics(boolean z) {
        this.mContainsUserMetrics = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDeviceSerialNumber);
        sb.append("\n");
        sb.append(this.mDeviceType);
        sb.append("\n");
        sb.append(this.mDeviceInfoMap.toString());
        sb.append("\n");
        sb.append(this.mBatchSizeInBytes);
        sb.append("\n");
        for (int i = 0; i < this.mSerializedMetricEntryList.size(); i++) {
            sb.append(this.mSerializedMetricEntryList.get(i).toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    public MetricBatch(Map<String, String> map) {
        putMetricsDeviceInfo(map);
    }
}
