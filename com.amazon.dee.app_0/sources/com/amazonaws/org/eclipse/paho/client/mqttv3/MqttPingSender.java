package com.amazonaws.org.eclipse.paho.client.mqttv3;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.ClientComms;
/* loaded from: classes13.dex */
public interface MqttPingSender {
    void init(ClientComms clientComms);

    void schedule(long j);

    void start();

    void stop();
}
