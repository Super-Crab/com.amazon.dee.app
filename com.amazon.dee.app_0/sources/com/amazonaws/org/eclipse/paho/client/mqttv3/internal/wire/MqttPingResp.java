package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
/* loaded from: classes13.dex */
public class MqttPingResp extends MqttAck {
    public static final String KEY = "Ping";

    public MqttPingResp(byte b, byte[] bArr) {
        super((byte) 13);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String getKey() {
        return "Ping";
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isMessageIdRequired() {
        return false;
    }
}
