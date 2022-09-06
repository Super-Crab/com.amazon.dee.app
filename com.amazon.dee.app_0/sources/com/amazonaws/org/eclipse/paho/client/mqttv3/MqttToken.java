package com.amazonaws.org.eclipse.paho.client.mqttv3;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.Token;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
/* loaded from: classes13.dex */
public class MqttToken implements IMqttToken {
    public Token internalTok;

    public MqttToken() {
        this.internalTok = null;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public IMqttActionListener getActionCallback() {
        return this.internalTok.getActionCallback();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public IMqttAsyncClient getClient() {
        return this.internalTok.getClient();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public MqttException getException() {
        return this.internalTok.getException();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public int[] getGrantedQos() {
        return this.internalTok.getGrantedQos();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public int getMessageId() {
        return this.internalTok.getMessageID();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public MqttWireMessage getResponse() {
        return this.internalTok.getResponse();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public boolean getSessionPresent() {
        return this.internalTok.getSessionPresent();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public String[] getTopics() {
        return this.internalTok.getTopics();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public Object getUserContext() {
        return this.internalTok.getUserContext();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public boolean isComplete() {
        return this.internalTok.isComplete();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.internalTok.setActionCallback(iMqttActionListener);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public void setUserContext(Object obj) {
        this.internalTok.setUserContext(obj);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public void waitForCompletion() throws MqttException {
        this.internalTok.waitForCompletion(-1L);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken
    public void waitForCompletion(long j) throws MqttException {
        this.internalTok.waitForCompletion(j);
    }

    public MqttToken(String str) {
        this.internalTok = null;
        this.internalTok = new Token(str);
    }
}
