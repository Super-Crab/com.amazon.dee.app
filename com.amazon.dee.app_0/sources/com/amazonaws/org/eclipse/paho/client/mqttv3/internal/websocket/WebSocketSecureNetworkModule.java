package com.amazonaws.org.eclipse.paho.client.mqttv3.internal.websocket;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.Logger;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.nio.ByteBuffer;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes13.dex */
public class WebSocketSecureNetworkModule extends SSLNetworkModule {
    private static final String CLASS_NAME;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private String host;
    private ByteArrayOutputStream outputStream;
    private PipedInputStream pipedInputStream;
    private int port;
    ByteBuffer recievedPayload;
    private String uri;
    private WebSocketReceiver webSocketReceiver;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("com.amazonaws.org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    public WebSocketSecureNetworkModule(SSLSocketFactory sSLSocketFactory, String str, String str2, int i, String str3) {
        super(sSLSocketFactory, str2, i, str3);
        this.outputStream = new ByteArrayOutputStream() { // from class: com.amazonaws.org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule.1
            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                ByteBuffer wrap;
                synchronized (this) {
                    wrap = ByteBuffer.wrap(toByteArray());
                    reset();
                }
                WebSocketSecureNetworkModule.this.getSocketOutputStream().write(new WebSocketFrame((byte) 2, true, wrap.array()).encodeFrame());
                WebSocketSecureNetworkModule.this.getSocketOutputStream().flush();
            }
        };
        this.uri = str;
        this.host = str2;
        this.port = i;
        this.pipedInputStream = new PipedInputStream();
        log.setResourceName(str3);
    }

    private InputStream getSocketInputStream() throws IOException {
        return super.getInputStream();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OutputStream getSocketOutputStream() throws IOException {
        return super.getOutputStream();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, com.amazonaws.org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() throws IOException {
        return this.pipedInputStream;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, com.amazonaws.org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() throws IOException {
        return this.outputStream;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule, com.amazonaws.org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, com.amazonaws.org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        StringBuffer stringBuffer = new StringBuffer("wss://");
        stringBuffer.append(this.host);
        stringBuffer.append(":");
        stringBuffer.append(this.port);
        return stringBuffer.toString();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule, com.amazonaws.org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, com.amazonaws.org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws IOException, MqttException {
        super.start();
        new WebSocketHandshake(super.getInputStream(), super.getOutputStream(), this.uri, this.host, this.port).execute();
        this.webSocketReceiver = new WebSocketReceiver(getSocketInputStream(), this.pipedInputStream);
        this.webSocketReceiver.start("WssSocketReceiver");
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, com.amazonaws.org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() throws IOException {
        getSocketOutputStream().write(new WebSocketFrame((byte) 8, true, "1000".getBytes()).encodeFrame());
        getSocketOutputStream().flush();
        WebSocketReceiver webSocketReceiver = this.webSocketReceiver;
        if (webSocketReceiver != null) {
            webSocketReceiver.stop();
        }
        super.stop();
    }
}
