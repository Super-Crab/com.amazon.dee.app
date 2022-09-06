package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/* loaded from: classes13.dex */
public class MqttPubComp extends MqttAck {
    public MqttPubComp(byte b, byte[] bArr) throws IOException {
        super((byte) 7);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        return encodeMessageId();
    }

    public MqttPubComp(MqttPublish mqttPublish) {
        super((byte) 7);
        this.msgId = mqttPublish.getMessageId();
    }

    public MqttPubComp(int i) {
        super((byte) 7);
        this.msgId = i;
    }
}
