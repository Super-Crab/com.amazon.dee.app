package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/* loaded from: classes13.dex */
public class MqttSubscribe extends MqttWireMessage {
    private int count;
    private String[] names;
    private int[] qos;

    public MqttSubscribe(byte b, byte[] bArr) throws IOException {
        super((byte) 8);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        boolean z = false;
        this.count = 0;
        this.names = new String[10];
        this.qos = new int[10];
        while (!z) {
            try {
                this.names[this.count] = decodeUTF8(dataInputStream);
                int[] iArr = this.qos;
                int i = this.count;
                this.count = i + 1;
                iArr[i] = dataInputStream.readByte();
            } catch (Exception unused) {
                z = true;
            }
        }
        dataInputStream.close();
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
                dataOutputStream.writeByte(this.qos[i]);
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
            stringBuffer.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            stringBuffer.append(this.names[i]);
            stringBuffer.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        }
        stringBuffer.append("] qos:[");
        for (int i2 = 0; i2 < this.count; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.qos[i2]);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public MqttSubscribe(String[] strArr, int[] iArr) {
        super((byte) 8);
        this.names = strArr;
        this.qos = iArr;
        if (strArr.length == iArr.length) {
            for (int i : iArr) {
                MqttMessage.validateQos(i);
            }
            return;
        }
        throw new IllegalArgumentException();
    }
}
