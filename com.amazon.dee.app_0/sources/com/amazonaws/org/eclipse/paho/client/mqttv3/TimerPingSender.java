package com.amazonaws.org.eclipse.paho.client.mqttv3;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.ClientComms;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.Logger;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes13.dex */
public class TimerPingSender implements MqttPingSender {
    private static final String CLASS_NAME;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private ClientComms comms;
    private Timer timer;

    /* loaded from: classes13.dex */
    private class PingTask extends TimerTask {
        private static final String methodName = "PingTask.run";

        private PingTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            TimerPingSender.log.fine(TimerPingSender.CLASS_NAME, methodName, "660", new Object[]{new Long(System.currentTimeMillis())});
            TimerPingSender.this.comms.checkForActivity();
        }

        /* synthetic */ PingTask(TimerPingSender timerPingSender, PingTask pingTask) {
            this();
        }
    }

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("com.amazonaws.org.eclipse.paho.client.mqttv3.TimerPingSender");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPingSender
    public void init(ClientComms clientComms) {
        if (clientComms != null) {
            this.comms = clientComms;
            return;
        }
        throw new IllegalArgumentException("ClientComms cannot be null.");
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPingSender
    public void schedule(long j) {
        this.timer.schedule(new PingTask(this, null), j);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPingSender
    public void start() {
        String clientId = this.comms.getClient().getClientId();
        log.fine(CLASS_NAME, "start", "659", new Object[]{clientId});
        StringBuffer stringBuffer = new StringBuffer("MQTT Ping: ");
        stringBuffer.append(clientId);
        this.timer = new Timer(stringBuffer.toString());
        this.timer.schedule(new PingTask(this, null), this.comms.getKeepAlive());
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPingSender
    public void stop() {
        log.fine(CLASS_NAME, "stop", "661", null);
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
    }
}
