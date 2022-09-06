package com.amazonaws.mobileconnectors.iot;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class AWSIotMqttTopic {
    private AWSIotMqttNewMessageCallback callback;
    private AWSIotMqttQos qos;
    private String topic;

    public AWSIotMqttTopic(String str, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttNewMessageCallback aWSIotMqttNewMessageCallback) {
        this.topic = str;
        this.qos = aWSIotMqttQos;
        this.callback = aWSIotMqttNewMessageCallback;
    }

    public AWSIotMqttNewMessageCallback getCallback() {
        return this.callback;
    }

    public AWSIotMqttQos getQos() {
        return this.qos;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setCallback(AWSIotMqttNewMessageCallback aWSIotMqttNewMessageCallback) {
        this.callback = aWSIotMqttNewMessageCallback;
    }

    public void setQos(AWSIotMqttQos aWSIotMqttQos) {
        this.qos = aWSIotMqttQos;
    }

    public void setTopic(String str) {
        this.topic = str;
    }
}
