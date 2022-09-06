package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/* loaded from: classes13.dex */
public class MqttUnsubscribe extends MqttWireMessage {
    private int count;
    private String[] names;

    public MqttUnsubscribe(String[] strArr) {
        super((byte) 10);
        this.names = strArr;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte getMessageInfo() {
        return (byte) ((this.duplicate ? 8 : 0) | 2);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getPayload() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            for (int i = 0; i < this.names.length; i++) {
                encodeUTF8(dataOutputStream, this.names[i]);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.msgId);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isRetryable() {
        return true;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" names:[");
        for (int i = 0; i < this.count; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            StringBuffer stringBuffer2 = new StringBuffer(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            stringBuffer2.append(this.names[i]);
            stringBuffer2.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            stringBuffer.append(stringBuffer2.toString());
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public MqttUnsubscribe(byte b, byte[] bArr) throws IOException {
        super((byte) 10);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        boolean z = false;
        this.count = 0;
        this.names = new String[10];
        while (!z) {
            try {
                this.names[this.count] = decodeUTF8(dataInputStream);
            } catch (Exception unused) {
                z = true;
            }
        }
        dataInputStream.close();
    }
}
