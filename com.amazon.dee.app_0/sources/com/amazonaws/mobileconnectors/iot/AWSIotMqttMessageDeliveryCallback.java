package com.amazonaws.mobileconnectors.iot;
/* loaded from: classes13.dex */
public interface AWSIotMqttMessageDeliveryCallback {

    /* loaded from: classes13.dex */
    public enum MessageDeliveryStatus {
        Success,
        Fail
    }

    void statusChanged(MessageDeliveryStatus messageDeliveryStatus, Object obj);
}
