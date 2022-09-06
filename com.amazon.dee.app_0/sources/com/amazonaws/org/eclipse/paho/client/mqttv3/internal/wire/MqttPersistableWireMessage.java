package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistenceException;
/* loaded from: classes13.dex */
public abstract class MqttPersistableWireMessage extends MqttWireMessage implements MqttPersistable {
    public MqttPersistableWireMessage(byte b) {
        super(b);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public byte[] getHeaderBytes() throws MqttPersistenceException {
        try {
            return getHeader();
        } catch (MqttException e) {
            throw new MqttPersistenceException(e.getCause());
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getHeaderLength() throws MqttPersistenceException {
        return getHeaderBytes().length;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getHeaderOffset() throws MqttPersistenceException {
        return 0;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public byte[] getPayloadBytes() throws MqttPersistenceException {
        try {
            return getPayload();
        } catch (MqttException e) {
            throw new MqttPersistenceException(e.getCause());
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getPayloadLength() throws MqttPersistenceException {
        return 0;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getPayloadOffset() throws MqttPersistenceException {
        return 0;
    }
}
