package com.amazonaws.mobileconnectors.iot;
/* loaded from: classes13.dex */
public enum AWSIotMqttQos {
    QOS0,
    QOS1;

    public int asInt() {
        return this == QOS0 ? 0 : 1;
    }
}
