package com.amazonaws.org.eclipse.paho.client.mqttv3.internal;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable;
/* loaded from: classes13.dex */
public class MqttPersistentData implements MqttPersistable {
    private int hLength;
    private int hOffset;
    private byte[] header;
    private String key;
    private int pLength;
    private int pOffset;
    private byte[] payload;

    public MqttPersistentData(String str, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.key = null;
        this.header = null;
        this.hOffset = 0;
        this.hLength = 0;
        this.payload = null;
        this.pOffset = 0;
        this.pLength = 0;
        this.key = str;
        this.header = bArr;
        this.hOffset = i;
        this.hLength = i2;
        this.payload = bArr2;
        this.pOffset = i3;
        this.pLength = i4;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public byte[] getHeaderBytes() {
        return this.header;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getHeaderLength() {
        return this.hLength;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getHeaderOffset() {
        return this.hOffset;
    }

    public String getKey() {
        return this.key;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public byte[] getPayloadBytes() {
        return this.payload;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getPayloadLength() {
        if (this.payload == null) {
            return 0;
        }
        return this.pLength;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getPayloadOffset() {
        return this.pOffset;
    }
}
