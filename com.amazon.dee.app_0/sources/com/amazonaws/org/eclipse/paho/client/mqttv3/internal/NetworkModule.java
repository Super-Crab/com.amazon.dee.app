package com.amazonaws.org.eclipse.paho.client.mqttv3.internal;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes13.dex */
public interface NetworkModule {
    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws IOException;

    String getServerURI();

    void start() throws IOException, MqttException;

    void stop() throws IOException;
}
