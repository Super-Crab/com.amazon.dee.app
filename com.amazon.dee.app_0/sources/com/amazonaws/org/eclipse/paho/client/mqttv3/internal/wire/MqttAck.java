package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;
/* loaded from: classes13.dex */
public abstract class MqttAck extends MqttWireMessage {
    public MqttAck(byte b) {
        super(b);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte getMessageInfo() {
        return (byte) 0;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append(" msgId ");
        stringBuffer.append(this.msgId);
        return stringBuffer.toString();
    }
}
