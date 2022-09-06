package com.amazonaws.org.eclipse.paho.client.mqttv3.internal;

import com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener;
import com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallback;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttMessage;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttToken;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.Logger;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import com.here.sdk.search.PlaceCategory;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
/* loaded from: classes13.dex */
public class CommsCallback implements Runnable {
    private static final String CLASS_NAME;
    private static final int INBOUND_QUEUE_SIZE = 10;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private Thread callbackThread;
    private ClientComms clientComms;
    private ClientState clientState;
    private MqttCallback mqttCallback;
    private MqttCallbackExtended reconnectInternalCallback;
    public boolean running = false;
    private boolean quiescing = false;
    private Object lifecycle = new Object();
    private Object workAvailable = new Object();
    private Object spaceAvailable = new Object();
    private boolean manualAcks = false;
    private Vector messageQueue = new Vector(10);
    private Vector completeQueue = new Vector(10);
    private Hashtable callbacks = new Hashtable();

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("com.amazonaws.org.eclipse.paho.client.mqttv3.internal.CommsCallback");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CommsCallback(ClientComms clientComms) {
        this.clientComms = clientComms;
        log.setResourceName(clientComms.getClient().getClientId());
    }

    private void handleActionComplete(MqttToken mqttToken) throws MqttException {
        synchronized (mqttToken) {
            log.fine(CLASS_NAME, "handleActionComplete", "705", new Object[]{mqttToken.internalTok.getKey()});
            if (mqttToken.isComplete()) {
                this.clientState.notifyComplete(mqttToken);
            }
            mqttToken.internalTok.notifyComplete();
            if (!mqttToken.internalTok.isNotified()) {
                if (this.mqttCallback != null && (mqttToken instanceof MqttDeliveryToken) && mqttToken.isComplete()) {
                    this.mqttCallback.deliveryComplete((MqttDeliveryToken) mqttToken);
                }
                fireActionEvent(mqttToken);
            }
            if (mqttToken.isComplete() && ((mqttToken instanceof MqttDeliveryToken) || (mqttToken.getActionCallback() instanceof IMqttActionListener))) {
                mqttToken.internalTok.setNotified(true);
            }
        }
    }

    private void handleMessage(MqttPublish mqttPublish) throws MqttException, Exception {
        String topicName = mqttPublish.getTopicName();
        log.fine(CLASS_NAME, "handleMessage", "713", new Object[]{new Integer(mqttPublish.getMessageId()), topicName});
        deliverMessage(topicName, mqttPublish.getMessageId(), mqttPublish.getMessage());
        if (!this.manualAcks) {
            if (mqttPublish.getMessage().getQos() == 1) {
                this.clientComms.internalSend(new MqttPubAck(mqttPublish), new MqttToken(this.clientComms.getClient().getClientId()));
            } else if (mqttPublish.getMessage().getQos() != 2) {
            } else {
                this.clientComms.deliveryComplete(mqttPublish);
                MqttPubComp mqttPubComp = new MqttPubComp(mqttPublish);
                ClientComms clientComms = this.clientComms;
                clientComms.internalSend(mqttPubComp, new MqttToken(clientComms.getClient().getClientId()));
            }
        }
    }

    public void asyncOperationComplete(MqttToken mqttToken) {
        if (this.running) {
            this.completeQueue.addElement(mqttToken);
            synchronized (this.workAvailable) {
                log.fine(CLASS_NAME, "asyncOperationComplete", "715", new Object[]{mqttToken.internalTok.getKey()});
                this.workAvailable.notifyAll();
            }
            return;
        }
        try {
            handleActionComplete(mqttToken);
        } catch (Throwable th) {
            log.fine(CLASS_NAME, "asyncOperationComplete", "719", null, th);
            this.clientComms.shutdownConnection(null, new MqttException(th));
        }
    }

    public void connectionLost(MqttException mqttException) {
        try {
            if (this.mqttCallback != null && mqttException != null) {
                log.fine(CLASS_NAME, "connectionLost", "708", new Object[]{mqttException});
                this.mqttCallback.connectionLost(mqttException);
            }
            if (this.reconnectInternalCallback == null || mqttException == null) {
                return;
            }
            this.reconnectInternalCallback.connectionLost(mqttException);
        } catch (Throwable th) {
            log.fine(CLASS_NAME, "connectionLost", "720", new Object[]{th});
        }
    }

    protected boolean deliverMessage(String str, int i, MqttMessage mqttMessage) throws Exception {
        Enumeration keys = this.callbacks.keys();
        boolean z = false;
        while (keys.hasMoreElements()) {
            String str2 = (String) keys.nextElement();
            if (MqttTopic.isMatched(str2, str)) {
                mqttMessage.setId(i);
                ((IMqttMessageListener) this.callbacks.get(str2)).messageArrived(str, mqttMessage);
                z = true;
            }
        }
        if (this.mqttCallback == null || z) {
            return z;
        }
        mqttMessage.setId(i);
        this.mqttCallback.messageArrived(str, mqttMessage);
        return true;
    }

    public void fireActionEvent(MqttToken mqttToken) {
        IMqttActionListener actionCallback;
        if (mqttToken == null || (actionCallback = mqttToken.getActionCallback()) == null) {
            return;
        }
        if (mqttToken.getException() == null) {
            log.fine(CLASS_NAME, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
            actionCallback.onSuccess(mqttToken);
            return;
        }
        log.fine(CLASS_NAME, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
        actionCallback.onFailure(mqttToken, mqttToken.getException());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Thread getThread() {
        return this.callbackThread;
    }

    public boolean isQuiesced() {
        return this.quiescing && this.completeQueue.size() == 0 && this.messageQueue.size() == 0;
    }

    public void messageArrived(MqttPublish mqttPublish) {
        if (this.mqttCallback != null || this.callbacks.size() > 0) {
            synchronized (this.spaceAvailable) {
                while (this.running && !this.quiescing && this.messageQueue.size() >= 10) {
                    try {
                        log.fine(CLASS_NAME, "messageArrived", "709");
                        this.spaceAvailable.wait(200L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            if (this.quiescing) {
                return;
            }
            this.messageQueue.addElement(mqttPublish);
            synchronized (this.workAvailable) {
                log.fine(CLASS_NAME, "messageArrived", "710");
                this.workAvailable.notifyAll();
            }
        }
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        if (i2 == 1) {
            this.clientComms.internalSend(new MqttPubAck(i), new MqttToken(this.clientComms.getClient().getClientId()));
        } else if (i2 != 2) {
        } else {
            this.clientComms.deliveryComplete(i);
            MqttPubComp mqttPubComp = new MqttPubComp(i);
            ClientComms clientComms = this.clientComms;
            clientComms.internalSend(mqttPubComp, new MqttToken(clientComms.getClient().getClientId()));
        }
    }

    public void quiesce() {
        this.quiescing = true;
        synchronized (this.spaceAvailable) {
            log.fine(CLASS_NAME, "quiesce", "711");
            this.spaceAvailable.notifyAll();
        }
    }

    public void removeMessageListener(String str) {
        this.callbacks.remove(str);
    }

    public void removeMessageListeners() {
        this.callbacks.clear();
    }

    @Override // java.lang.Runnable
    public void run() {
        MqttToken mqttToken;
        MqttPublish mqttPublish;
        while (this.running) {
            try {
                try {
                    synchronized (this.workAvailable) {
                        if (this.running && this.messageQueue.isEmpty() && this.completeQueue.isEmpty()) {
                            log.fine(CLASS_NAME, "run", "704");
                            this.workAvailable.wait();
                        }
                    }
                } catch (Throwable th) {
                    try {
                        log.fine(CLASS_NAME, "run", "714", null, th);
                        this.running = false;
                        this.clientComms.shutdownConnection(null, new MqttException(th));
                    } catch (Throwable th2) {
                        synchronized (this.spaceAvailable) {
                            log.fine(CLASS_NAME, "run", "706");
                            this.spaceAvailable.notifyAll();
                            throw th2;
                        }
                    }
                }
            } catch (InterruptedException unused) {
            }
            if (this.running) {
                synchronized (this.completeQueue) {
                    if (!this.completeQueue.isEmpty()) {
                        mqttToken = (MqttToken) this.completeQueue.elementAt(0);
                        this.completeQueue.removeElementAt(0);
                    } else {
                        mqttToken = null;
                    }
                }
                if (mqttToken != null) {
                    handleActionComplete(mqttToken);
                }
                synchronized (this.messageQueue) {
                    if (!this.messageQueue.isEmpty()) {
                        mqttPublish = (MqttPublish) this.messageQueue.elementAt(0);
                        this.messageQueue.removeElementAt(0);
                    } else {
                        mqttPublish = null;
                    }
                }
                if (mqttPublish != null) {
                    handleMessage(mqttPublish);
                }
            }
            if (this.quiescing) {
                this.clientState.checkQuiesceLock();
            }
            synchronized (this.spaceAvailable) {
                log.fine(CLASS_NAME, "run", "706");
                this.spaceAvailable.notifyAll();
            }
        }
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
    }

    public void setClientState(ClientState clientState) {
        this.clientState = clientState;
    }

    public void setManualAcks(boolean z) {
        this.manualAcks = z;
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.callbacks.put(str, iMqttMessageListener);
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.reconnectInternalCallback = mqttCallbackExtended;
    }

    public void start(String str) {
        synchronized (this.lifecycle) {
            if (!this.running) {
                this.messageQueue.clear();
                this.completeQueue.clear();
                this.running = true;
                this.quiescing = false;
                this.callbackThread = new Thread(this, str);
                this.callbackThread.start();
            }
        }
    }

    public void stop() {
        synchronized (this.lifecycle) {
            if (this.running) {
                log.fine(CLASS_NAME, "stop", PlaceCategory.BUSINESS_AND_SERVICES);
                this.running = false;
                if (!Thread.currentThread().equals(this.callbackThread)) {
                    try {
                        synchronized (this.workAvailable) {
                            log.fine(CLASS_NAME, "stop", "701");
                            this.workAvailable.notifyAll();
                        }
                        this.callbackThread.join();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            this.callbackThread = null;
            log.fine(CLASS_NAME, "stop", "703");
        }
    }
}