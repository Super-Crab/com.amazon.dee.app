package com.amazonaws.org.eclipse.paho.client.mqttv3.internal;

import com.amazon.alexa.comms.mediaInsights.Trace;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener;
import com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttMessage;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.Logger;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.here.sdk.search.PlaceCategory;
/* loaded from: classes13.dex */
public class Token {
    private static final String CLASS_NAME;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private String key;
    private volatile boolean completed = false;
    private boolean pendingComplete = false;
    private boolean sent = false;
    private Object responseLock = new Object();
    private Object sentLock = new Object();
    protected MqttMessage message = null;
    private MqttWireMessage response = null;
    private MqttException exception = null;
    private String[] topics = null;
    private IMqttAsyncClient client = null;
    private IMqttActionListener callback = null;
    private Object userContext = null;
    private int messageID = 0;
    private boolean notified = false;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("com.amazonaws.org.eclipse.paho.client.mqttv3.internal.Token");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    public Token(String str) {
        log.setResourceName(str);
    }

    public boolean checkResult() throws MqttException {
        if (getException() == null) {
            return true;
        }
        throw getException();
    }

    public IMqttActionListener getActionCallback() {
        return this.callback;
    }

    public IMqttAsyncClient getClient() {
        return this.client;
    }

    public MqttException getException() {
        return this.exception;
    }

    public int[] getGrantedQos() {
        int[] iArr = new int[0];
        MqttWireMessage mqttWireMessage = this.response;
        return mqttWireMessage instanceof MqttSuback ? ((MqttSuback) mqttWireMessage).getGrantedQos() : iArr;
    }

    public String getKey() {
        return this.key;
    }

    public MqttMessage getMessage() {
        return this.message;
    }

    public int getMessageID() {
        return this.messageID;
    }

    public MqttWireMessage getResponse() {
        return this.response;
    }

    public boolean getSessionPresent() {
        MqttWireMessage mqttWireMessage = this.response;
        if (mqttWireMessage instanceof MqttConnack) {
            return ((MqttConnack) mqttWireMessage).getSessionPresent();
        }
        return false;
    }

    public String[] getTopics() {
        return this.topics;
    }

    public Object getUserContext() {
        return this.userContext;
    }

    public MqttWireMessage getWireMessage() {
        return this.response;
    }

    public boolean isComplete() {
        return this.completed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isCompletePending() {
        return this.pendingComplete;
    }

    protected boolean isInUse() {
        return getClient() != null && !isComplete();
    }

    public boolean isNotified() {
        return this.notified;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void markComplete(MqttWireMessage mqttWireMessage, MqttException mqttException) {
        log.fine(CLASS_NAME, "markComplete", "404", new Object[]{getKey(), mqttWireMessage, mqttException});
        synchronized (this.responseLock) {
            if (mqttWireMessage instanceof MqttAck) {
                this.message = null;
            }
            this.pendingComplete = true;
            this.response = mqttWireMessage;
            this.exception = mqttException;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyComplete() {
        log.fine(CLASS_NAME, "notifyComplete", "404", new Object[]{getKey(), this.response, this.exception});
        synchronized (this.responseLock) {
            if (this.exception == null && this.pendingComplete) {
                this.completed = true;
                this.pendingComplete = false;
            } else {
                this.pendingComplete = false;
            }
            this.responseLock.notifyAll();
        }
        synchronized (this.sentLock) {
            this.sent = true;
            this.sentLock.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifySent() {
        log.fine(CLASS_NAME, "notifySent", "403", new Object[]{getKey()});
        synchronized (this.responseLock) {
            this.response = null;
            this.completed = false;
        }
        synchronized (this.sentLock) {
            this.sent = true;
            this.sentLock.notifyAll();
        }
    }

    public void reset() throws MqttException {
        if (!isInUse()) {
            log.fine(CLASS_NAME, "reset", "410", new Object[]{getKey()});
            this.client = null;
            this.completed = false;
            this.response = null;
            this.sent = false;
            this.exception = null;
            this.userContext = null;
            return;
        }
        throw new MqttException(32201);
    }

    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.callback = iMqttActionListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setClient(IMqttAsyncClient iMqttAsyncClient) {
        this.client = iMqttAsyncClient;
    }

    public void setException(MqttException mqttException) {
        synchronized (this.responseLock) {
            this.exception = mqttException;
        }
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setMessage(MqttMessage mqttMessage) {
        this.message = mqttMessage;
    }

    public void setMessageID(int i) {
        this.messageID = i;
    }

    public void setNotified(boolean z) {
        this.notified = z;
    }

    public void setTopics(String[] strArr) {
        this.topics = strArr;
    }

    public void setUserContext(Object obj) {
        this.userContext = obj;
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("key=");
        outline103.append(getKey());
        outline103.append(" ,topics=");
        if (getTopics() != null) {
            for (int i = 0; i < getTopics().length; i++) {
                outline103.append(getTopics()[i]);
                outline103.append(", ");
            }
        }
        outline103.append(" ,usercontext=");
        outline103.append(getUserContext());
        outline103.append(" ,isComplete=");
        outline103.append(isComplete());
        outline103.append(" ,isNotified=");
        outline103.append(isNotified());
        outline103.append(" ,exception=");
        outline103.append(getException());
        outline103.append(" ,actioncallback=");
        outline103.append(getActionCallback());
        return outline103.toString();
    }

    public void waitForCompletion() throws MqttException {
        waitForCompletion(-1L);
    }

    protected MqttWireMessage waitForResponse() throws MqttException {
        return waitForResponse(-1L);
    }

    public void waitUntilSent() throws MqttException {
        synchronized (this.sentLock) {
            synchronized (this.responseLock) {
                if (this.exception != null) {
                    throw this.exception;
                }
            }
            while (!this.sent) {
                try {
                    log.fine(CLASS_NAME, "waitUntilSent", "409", new Object[]{getKey()});
                    this.sentLock.wait();
                } catch (InterruptedException unused) {
                }
            }
            if (!this.sent) {
                if (this.exception == null) {
                    throw ExceptionHelper.createMqttException(6);
                }
                throw this.exception;
            }
        }
    }

    public void waitForCompletion(long j) throws MqttException {
        log.fine(CLASS_NAME, "waitForCompletion", "407", new Object[]{getKey(), new Long(j), this});
        if (waitForResponse(j) == null && !this.completed) {
            log.fine(CLASS_NAME, "waitForCompletion", "406", new Object[]{getKey(), this});
            this.exception = new MqttException((int) Trace.PAYLOAD_DATA_MAX_LENGTH);
            throw this.exception;
        }
        checkResult();
    }

    protected MqttWireMessage waitForResponse(long j) throws MqttException {
        synchronized (this.responseLock) {
            Logger logger = log;
            String str = CLASS_NAME;
            Object[] objArr = new Object[7];
            objArr[0] = getKey();
            objArr[1] = new Long(j);
            objArr[2] = new Boolean(this.sent);
            objArr[3] = new Boolean(this.completed);
            objArr[4] = this.exception == null ? PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE : "true";
            objArr[5] = this.response;
            objArr[6] = this;
            logger.fine(str, "waitForResponse", PlaceCategory.TRANSPORT, objArr, this.exception);
            while (!this.completed) {
                if (this.exception == null) {
                    try {
                        log.fine(CLASS_NAME, "waitForResponse", "408", new Object[]{getKey(), new Long(j)});
                        if (j <= 0) {
                            this.responseLock.wait();
                        } else {
                            this.responseLock.wait(j);
                        }
                    } catch (InterruptedException e) {
                        this.exception = new MqttException(e);
                    }
                }
                if (!this.completed) {
                    if (this.exception != null) {
                        log.fine(CLASS_NAME, "waitForResponse", "401", null, this.exception);
                        throw this.exception;
                    } else if (j > 0) {
                        break;
                    }
                }
            }
        }
        log.fine(CLASS_NAME, "waitForResponse", "402", new Object[]{getKey(), this.response});
        return this.response;
    }
}
