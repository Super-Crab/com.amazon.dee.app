package com.amazonaws.mobileconnectors.iot;
/* loaded from: classes13.dex */
public interface AWSIotMqttClientStatusCallback {

    /* loaded from: classes13.dex */
    public enum AWSIotMqttClientStatus {
        Connecting,
        Connected,
        ConnectionLost,
        Reconnecting
    }

    void onStatusChanged(AWSIotMqttClientStatus aWSIotMqttClientStatus, Throwable th);
}
