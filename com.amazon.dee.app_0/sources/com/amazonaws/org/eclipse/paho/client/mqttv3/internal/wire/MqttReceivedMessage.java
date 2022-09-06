package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttMessage;
/* loaded from: classes13.dex */
public class MqttReceivedMessage extends MqttMessage {
    private int messageId;

    public int getMessageId() {
        return this.messageId;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttMessage
    public void setDuplicate(boolean z) {
        super.setDuplicate(z);
    }

    public void setMessageId(int i) {
        this.messageId = i;
    }
}
