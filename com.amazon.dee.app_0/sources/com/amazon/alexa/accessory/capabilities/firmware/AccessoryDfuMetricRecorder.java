package com.amazon.alexa.accessory.capabilities.firmware;
/* loaded from: classes.dex */
public interface AccessoryDfuMetricRecorder {
    void recordApplyFirmwareErrorCode(String str);

    void recordDfuSuccessMetric(boolean z);

    void recordMetric(String str);
}
