package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/* loaded from: classes13.dex */
public class MqttConnack extends MqttAck {
    public static final String KEY = "Con";
    private int returnCode;
    private boolean sessionPresent;

    public MqttConnack(byte b, byte[] bArr) throws IOException {
        super((byte) 2);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.sessionPresent = (dataInputStream.readUnsignedByte() & 1) != 1 ? false : true;
        this.returnCode = dataInputStream.readUnsignedByte();
        dataInputStream.close();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String getKey() {
        return "Con";
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public boolean getSessionPresent() {
        return this.sessionPresent;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isMessageIdRequired() {
        return false;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttAck, com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append(" session present:");
        stringBuffer.append(this.sessionPresent);
        stringBuffer.append(" return code: ");
        stringBuffer.append(this.returnCode);
        return stringBuffer.toString();
    }
}
