package com.amazonaws.org.eclipse.paho.client.mqttv3;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
/* loaded from: classes13.dex */
public class BufferedMessage {
    private MqttWireMessage message;
    private MqttToken token;

    public BufferedMessage(MqttWireMessage mqttWireMessage, MqttToken mqttToken) {
        this.message = mqttWireMessage;
        this.token = mqttToken;
    }

    public MqttWireMessage getMessage() {
        return this.message;
    }

    public MqttToken getToken() {
        return this.token;
    }
}
