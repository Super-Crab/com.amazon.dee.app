package com.amazonaws.org.eclipse.paho.client.mqttv3.internal;

import com.amazonaws.org.eclipse.paho.client.mqttv3.BufferedMessage;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
/* loaded from: classes13.dex */
public interface IDisconnectedBufferCallback {
    void publishBufferedMessage(BufferedMessage bufferedMessage) throws MqttException;
}
