package com.amazonaws.org.eclipse.paho.client.mqttv3;
/* loaded from: classes13.dex */
public class MqttDeliveryToken extends MqttToken implements IMqttDeliveryToken {
    public MqttDeliveryToken() {
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
    public MqttMessage getMessage() throws MqttException {
        return this.internalTok.getMessage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setMessage(MqttMessage mqttMessage) {
        this.internalTok.setMessage(mqttMessage);
    }

    public MqttDeliveryToken(String str) {
        super(str);
    }
}
