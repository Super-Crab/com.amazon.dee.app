package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/* loaded from: classes13.dex */
public class MqttPubRec extends MqttAck {
    public MqttPubRec(byte b, byte[] bArr) throws IOException {
        super((byte) 5);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        return encodeMessageId();
    }

    public MqttPubRec(MqttPublish mqttPublish) {
        super((byte) 5);
        this.msgId = mqttPublish.getMessageId();
    }
}
