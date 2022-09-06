package com.amazon.whisperjoin.provisioning.metrics.client.helpers;

import com.amazon.whisperjoin.provisioning.metrics.client.MetricHelper;
/* loaded from: classes13.dex */
public class DeviceMetrics {
    static final String TARGET_DEVICE_SERIAL_METRIC = "TargetDeviceSerial";
    static final String TARGET_DEVICE_TYPE_METRIC = "TargetDeviceType";
    static final String TARGET_DEVICE_VERSION_METRIC = "TargetDeviceVersion";
    private final MetricHelper mMetricHelper;

    public DeviceMetrics(MetricHelper metricHelper) {
        this.mMetricHelper = metricHelper;
    }

    public void onTargetDeviceSerialDiscovered(String str) {
        this.mMetricHelper.recordString(TARGET_DEVICE_SERIAL_METRIC, str, new Object[0]);
    }

    public void onTargetDeviceTypeDiscovered(String str) {
        this.mMetricHelper.recordString(TARGET_DEVICE_TYPE_METRIC, str, new Object[0]);
    }

    public void onTargetDeviceVersionDiscovered(String str) {
        this.mMetricHelper.recordString(TARGET_DEVICE_VERSION_METRIC, str, new Object[0]);
    }
}
