package com.amazonaws.mobileconnectors.iot;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class AWSIotMqttQueueMessage {
    private byte[] message;
    private AWSIotMqttQos qos;
    private String topic;
    private PublishMessageUserData userData;

    public AWSIotMqttQueueMessage(String str, byte[] bArr, AWSIotMqttQos aWSIotMqttQos, PublishMessageUserData publishMessageUserData) {
        this.topic = str;
        this.message = bArr;
        this.qos = aWSIotMqttQos;
        this.userData = publishMessageUserData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getMessage() {
        return this.message;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AWSIotMqttQos getQos() {
        return this.qos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTopic() {
        return this.topic;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublishMessageUserData getUserData() {
        return this.userData;
    }
}
