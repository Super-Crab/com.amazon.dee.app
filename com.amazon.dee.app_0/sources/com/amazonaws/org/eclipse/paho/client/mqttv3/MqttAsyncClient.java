package com.amazonaws.org.eclipse.paho.client.mqttv3;

import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.comms.ringservice.MetricsSession;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.ClientComms;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.DisconnectedMessageBuffer;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.LocalNetworkModule;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.Logger;
import com.amazonaws.org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import com.amazonaws.org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.amazonaws.org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import com.amazonaws.org.eclipse.paho.client.mqttv3.util.Debug;
import com.here.sdk.search.PlaceCategory;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes13.dex */
public class MqttAsyncClient implements IMqttAsyncClient {
    private static final String CLASS_NAME;
    private static final String CLIENT_ID_PREFIX = "paho";
    private static final long DISCONNECT_TIMEOUT = 10000;
    private static final char MAX_HIGH_SURROGATE = 56319;
    private static final char MIN_HIGH_SURROGATE = 55296;
    private static final long QUIESCE_TIMEOUT = 30000;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private static int reconnectDelay;
    private String clientId;
    protected ClientComms comms;
    private MqttConnectOptions connOpts;
    private MqttCallback mqttCallback;
    private MqttClientPersistence persistence;
    private Timer reconnectTimer;
    private boolean reconnecting;
    private String serverURI;
    private Hashtable topics;
    private Object userContext;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class ReconnectTask extends TimerTask {
        private static final String methodName = "ReconnectTask.run";

        private ReconnectTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, methodName, "506");
            MqttAsyncClient.this.attemptReconnect();
        }

        /* synthetic */ ReconnectTask(MqttAsyncClient mqttAsyncClient, ReconnectTask reconnectTask) {
            this();
        }
    }

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("com.amazonaws.org.eclipse.paho.client.mqttv3.MqttAsyncClient");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
        reconnectDelay = 1000;
    }

    public MqttAsyncClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    protected static boolean Character_isHighSurrogate(char c) {
        return c >= 55296 && c <= 56319;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attemptReconnect() {
        log.fine(CLASS_NAME, "attemptReconnect", PlaceCategory.ACCOMODATION, new Object[]{this.clientId});
        try {
            connect(this.connOpts, this.userContext, new IMqttActionListener() { // from class: com.amazonaws.org.eclipse.paho.client.mqttv3.MqttAsyncClient.2
                @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "502", new Object[]{iMqttToken.getClient().getClientId()});
                    if (MqttAsyncClient.reconnectDelay != 120000) {
                        MqttAsyncClient.reconnectDelay *= 2;
                    }
                    MqttAsyncClient.this.rescheduleReconnectCycle(MqttAsyncClient.reconnectDelay);
                }

                @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "501", new Object[]{iMqttToken.getClient().getClientId()});
                    MqttAsyncClient.this.comms.setRestingState(false);
                    MqttAsyncClient.this.stopReconnectCycle();
                }
            });
        } catch (MqttSecurityException e) {
            log.fine(CLASS_NAME, "attemptReconnect", "804", null, e);
        } catch (MqttException e2) {
            log.fine(CLASS_NAME, "attemptReconnect", "804", null, e2);
        }
    }

    private NetworkModule createNetworkModule(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        SSLSocketFactoryFactory sSLSocketFactoryFactory;
        String[] enabledCipherSuites;
        SSLSocketFactoryFactory sSLSocketFactoryFactory2;
        String[] enabledCipherSuites2;
        log.fine(CLASS_NAME, "createNetworkModule", "115", new Object[]{str});
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        int validateURI = MqttConnectOptions.validateURI(str);
        if (validateURI == 0) {
            String substring = str.substring(6);
            String hostName = getHostName(substring);
            int port = getPort(substring, 1883);
            if (socketFactory == null) {
                socketFactory = SocketFactory.getDefault();
            } else if (socketFactory instanceof SSLSocketFactory) {
                throw ExceptionHelper.createMqttException(32105);
            }
            TCPNetworkModule tCPNetworkModule = new TCPNetworkModule(socketFactory, hostName, port, this.clientId);
            tCPNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
            return tCPNetworkModule;
        } else if (validateURI == 1) {
            String substring2 = str.substring(6);
            String hostName2 = getHostName(substring2);
            int port2 = getPort(substring2, 8883);
            if (socketFactory == null) {
                SSLSocketFactoryFactory sSLSocketFactoryFactory3 = new SSLSocketFactoryFactory();
                Properties sSLProperties = mqttConnectOptions.getSSLProperties();
                if (sSLProperties != null) {
                    sSLSocketFactoryFactory3.initialize(sSLProperties, null);
                }
                sSLSocketFactoryFactory = sSLSocketFactoryFactory3;
                socketFactory = sSLSocketFactoryFactory3.createSocketFactory(null);
            } else if (!(socketFactory instanceof SSLSocketFactory)) {
                throw ExceptionHelper.createMqttException(32105);
            } else {
                sSLSocketFactoryFactory = null;
            }
            SSLNetworkModule sSLNetworkModule = new SSLNetworkModule((SSLSocketFactory) socketFactory, hostName2, port2, this.clientId);
            sSLNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
            if (sSLSocketFactoryFactory != null && (enabledCipherSuites = sSLSocketFactoryFactory.getEnabledCipherSuites(null)) != null) {
                sSLNetworkModule.setEnabledCiphers(enabledCipherSuites);
            }
            return sSLNetworkModule;
        } else if (validateURI == 2) {
            return new LocalNetworkModule(str.substring(8));
        } else {
            if (validateURI == 3) {
                String substring3 = str.substring(5);
                String hostName3 = getHostName(substring3);
                int port3 = getPort(substring3, 80);
                if (socketFactory == null) {
                    socketFactory = SocketFactory.getDefault();
                } else if (socketFactory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                }
                WebSocketNetworkModule webSocketNetworkModule = new WebSocketNetworkModule(socketFactory, str, hostName3, port3, this.clientId);
                webSocketNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                return webSocketNetworkModule;
            } else if (validateURI != 4) {
                return null;
            } else {
                String substring4 = str.substring(6);
                String hostName4 = getHostName(substring4);
                int port4 = getPort(substring4, 443);
                if (socketFactory == null) {
                    SSLSocketFactoryFactory sSLSocketFactoryFactory4 = new SSLSocketFactoryFactory();
                    Properties sSLProperties2 = mqttConnectOptions.getSSLProperties();
                    if (sSLProperties2 != null) {
                        sSLSocketFactoryFactory4.initialize(sSLProperties2, null);
                    }
                    sSLSocketFactoryFactory2 = sSLSocketFactoryFactory4;
                    socketFactory = sSLSocketFactoryFactory4.createSocketFactory(null);
                } else if (!(socketFactory instanceof SSLSocketFactory)) {
                    throw ExceptionHelper.createMqttException(32105);
                } else {
                    sSLSocketFactoryFactory2 = null;
                }
                WebSocketSecureNetworkModule webSocketSecureNetworkModule = new WebSocketSecureNetworkModule((SSLSocketFactory) socketFactory, str, hostName4, port4, this.clientId);
                webSocketSecureNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                if (sSLSocketFactoryFactory2 != null && (enabledCipherSuites2 = sSLSocketFactoryFactory2.getEnabledCipherSuites(null)) != null) {
                    webSocketSecureNetworkModule.setEnabledCiphers(enabledCipherSuites2);
                }
                return webSocketSecureNetworkModule;
            }
        }
    }

    public static String generateClientId() {
        StringBuffer stringBuffer = new StringBuffer(CLIENT_ID_PREFIX);
        stringBuffer.append(System.nanoTime());
        return stringBuffer.toString();
    }

    private String getHostName(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            indexOf = str.indexOf(47);
        }
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return str.substring(0, indexOf);
    }

    private int getPort(String str, int i) {
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf == -1) {
            return i;
        }
        int indexOf = str.indexOf(47);
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return Integer.parseInt(str.substring(lastIndexOf + 1, indexOf));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rescheduleReconnectCycle(int i) {
        log.fine(CLASS_NAME, "rescheduleReconnectCycle", "505", new Object[]{this.clientId, new Long(reconnectDelay)});
        this.reconnectTimer.schedule(new ReconnectTask(this, null), reconnectDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startReconnectCycle() {
        log.fine(CLASS_NAME, "startReconnectCycle", MetricsSession.SERVICE_UNAVAILABLE, new Object[]{this.clientId, new Long(reconnectDelay)});
        StringBuffer stringBuffer = new StringBuffer("MQTT Reconnect: ");
        stringBuffer.append(this.clientId);
        this.reconnectTimer = new Timer(stringBuffer.toString());
        this.reconnectTimer.schedule(new ReconnectTask(this, null), reconnectDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopReconnectCycle() {
        log.fine(CLASS_NAME, "stopReconnectCycle", "504", new Object[]{this.clientId});
        this.reconnectTimer.cancel();
        reconnectDelay = 1000;
    }

    public IMqttToken checkPing(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        log.fine(CLASS_NAME, "ping", "117");
        MqttToken checkForActivity = this.comms.checkForActivity();
        log.fine(CLASS_NAME, "ping", "118");
        return checkForActivity;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void close() throws MqttException {
        log.fine(CLASS_NAME, "close", "113");
        this.comms.close();
        log.fine(CLASS_NAME, "close", "114");
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    protected NetworkModule[] createNetworkModules(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        log.fine(CLASS_NAME, "createNetworkModules", "116", new Object[]{str});
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null) {
            serverURIs = new String[]{str};
        } else if (serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        for (int i = 0; i < serverURIs.length; i++) {
            networkModuleArr[i] = createNetworkModule(serverURIs[i], mqttConnectOptions);
        }
        log.fine(CLASS_NAME, "createNetworkModules", "108");
        return networkModuleArr;
    }

    public void deleteBufferedMessage(int i) {
        this.comms.deleteBufferedMessage(i);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return disconnect(30000L, obj, iMqttActionListener);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly() throws MqttException {
        disconnectForcibly(30000L, 10000L);
    }

    public MqttMessage getBufferedMessage(int i) {
        return this.comms.getBufferedMessage(i);
    }

    public int getBufferedMessageCount() {
        return this.comms.getBufferedMessageCount();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getClientId() {
        return this.clientId;
    }

    public String getCurrentServerURI() {
        return this.comms.getNetworkModules()[this.comms.getNetworkModuleIndex()].getServerURI();
    }

    public Debug getDebug() {
        return new Debug(this.clientId, this.comms);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getServerURI() {
        return this.serverURI;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MqttTopic getTopic(String str) {
        MqttTopic.validate(str, false);
        MqttTopic mqttTopic = (MqttTopic) this.topics.get(str);
        if (mqttTopic == null) {
            MqttTopic mqttTopic2 = new MqttTopic(str, this.comms);
            this.topics.put(str, mqttTopic2);
            return mqttTopic2;
        }
        return mqttTopic;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean isConnected() {
        return this.comms.isConnected();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.comms.messageArrivedComplete(i, i2);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(str, mqttMessage, obj, iMqttActionListener);
    }

    public void reconnect() throws MqttException {
        log.fine(CLASS_NAME, Metrics.RECONNECT, PlaceCategory.ACCOMODATION, new Object[]{this.clientId});
        if (!this.comms.isConnected()) {
            if (!this.comms.isConnecting()) {
                if (!this.comms.isDisconnecting()) {
                    if (!this.comms.isClosed()) {
                        stopReconnectCycle();
                        attemptReconnect();
                        return;
                    }
                    throw new MqttException(32111);
                }
                throw new MqttException(32102);
            }
            throw new MqttException(32110);
        }
        throw ExceptionHelper.createMqttException(32100);
    }

    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.comms.setDisconnectedMessageBuffer(new DisconnectedMessageBuffer(disconnectedBufferOptions));
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
        this.comms.setCallback(mqttCallback);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setManualAcks(boolean z) {
        this.comms.setManualAcks(z);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return unsubscribe(new String[]{str}, obj, iMqttActionListener);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this(str, str2, mqttClientPersistence, new TimerPingSender());
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect() throws MqttException, MqttSecurityException {
        return connect(null, null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect() throws MqttException {
        return disconnect(null, null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j) throws MqttException {
        disconnectForcibly(30000L, j);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str) throws MqttException {
        return unsubscribe(new String[]{str}, (Object) null, (IMqttActionListener) null);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        this.reconnecting = false;
        log.setResourceName(str2);
        if (str2 != null) {
            int i = 0;
            int i2 = 0;
            while (i < str2.length() - 1) {
                if (Character_isHighSurrogate(str2.charAt(i))) {
                    i++;
                }
                i2++;
                i++;
            }
            if (i2 <= 65535) {
                MqttConnectOptions.validateURI(str);
                this.serverURI = str;
                this.clientId = str2;
                this.persistence = mqttClientPersistence;
                if (this.persistence == null) {
                    this.persistence = new MemoryPersistence();
                }
                log.fine(CLASS_NAME, "MqttAsyncClient", "101", new Object[]{str2, str, mqttClientPersistence});
                this.persistence.open(str2, str);
                this.comms = new ClientComms(this, this.persistence, mqttPingSender);
                this.persistence.close();
                this.topics = new Hashtable();
                return;
            }
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        throw new IllegalArgumentException("Null clientId");
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        return connect(mqttConnectOptions, null, null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j) throws MqttException {
        return disconnect(j, null, null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.comms.disconnectForcibly(j, j2);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr) throws MqttException {
        return unsubscribe(strArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        if (!this.comms.isConnected()) {
            if (!this.comms.isConnecting()) {
                if (!this.comms.isDisconnecting()) {
                    if (!this.comms.isClosed()) {
                        this.connOpts = mqttConnectOptions;
                        this.userContext = obj;
                        final boolean isAutomaticReconnect = mqttConnectOptions.isAutomaticReconnect();
                        Logger logger = log;
                        String str = CLASS_NAME;
                        Object[] objArr = new Object[8];
                        objArr[0] = Boolean.valueOf(mqttConnectOptions.isCleanSession());
                        objArr[1] = new Integer(mqttConnectOptions.getConnectionTimeout());
                        objArr[2] = new Integer(mqttConnectOptions.getKeepAliveInterval());
                        objArr[3] = mqttConnectOptions.getUserName();
                        String str2 = "[null]";
                        objArr[4] = mqttConnectOptions.getPassword() == null ? str2 : "[notnull]";
                        if (mqttConnectOptions.getWillMessage() != null) {
                            str2 = "[notnull]";
                        }
                        objArr[5] = str2;
                        objArr[6] = obj;
                        objArr[7] = iMqttActionListener;
                        logger.fine(str, EmulateConnection.EXTRA_CONNECT, "103", objArr);
                        this.comms.setNetworkModules(createNetworkModules(this.serverURI, mqttConnectOptions));
                        this.comms.setReconnectCallback(new MqttCallbackExtended() { // from class: com.amazonaws.org.eclipse.paho.client.mqttv3.MqttAsyncClient.1
                            @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallbackExtended
                            public void connectComplete(boolean z, String str3) {
                            }

                            @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallback
                            public void connectionLost(Throwable th) {
                                if (isAutomaticReconnect) {
                                    MqttAsyncClient.this.comms.setRestingState(true);
                                    MqttAsyncClient.this.reconnecting = true;
                                    MqttAsyncClient.this.startReconnectCycle();
                                }
                            }

                            @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallback
                            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                            }

                            @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallback
                            public void messageArrived(String str3, MqttMessage mqttMessage) throws Exception {
                            }
                        });
                        MqttToken mqttToken = new MqttToken(getClientId());
                        ConnectActionListener connectActionListener = new ConnectActionListener(this, this.persistence, this.comms, mqttConnectOptions, mqttToken, obj, iMqttActionListener, this.reconnecting);
                        mqttToken.setActionCallback(connectActionListener);
                        mqttToken.setUserContext(this);
                        MqttCallback mqttCallback = this.mqttCallback;
                        if (mqttCallback instanceof MqttCallbackExtended) {
                            connectActionListener.setMqttCallbackExtended((MqttCallbackExtended) mqttCallback);
                        }
                        this.comms.setNetworkModuleIndex(0);
                        connectActionListener.connect();
                        return mqttToken;
                    }
                    throw new MqttException(32111);
                }
                throw new MqttException(32102);
            }
            throw new MqttException(32110);
        }
        throw ExceptionHelper.createMqttException(32100);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        log.fine(CLASS_NAME, Metrics.DISCONNECT, "104", new Object[]{new Long(j), obj, iMqttActionListener});
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        try {
            this.comms.disconnect(new MqttDisconnect(), j, mqttToken);
            log.fine(CLASS_NAME, Metrics.DISCONNECT, "108");
            return mqttToken;
        } catch (MqttException e) {
            log.fine(CLASS_NAME, Metrics.DISCONNECT, "105", null, e);
            throw e;
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (strArr.length == iArr.length) {
            for (String str : strArr) {
                this.comms.removeMessageListener(str);
            }
            String str2 = "";
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    StringBuffer stringBuffer = new StringBuffer(String.valueOf(str2));
                    stringBuffer.append(", ");
                    str2 = stringBuffer.toString();
                }
                StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(str2));
                stringBuffer2.append("topic=");
                stringBuffer2.append(strArr[i]);
                stringBuffer2.append(" qos=");
                stringBuffer2.append(iArr[i]);
                str2 = stringBuffer2.toString();
                MqttTopic.validate(strArr[i], true);
            }
            log.fine(CLASS_NAME, "subscribe", "106", new Object[]{str2, obj, iMqttActionListener});
            MqttToken mqttToken = new MqttToken(getClientId());
            mqttToken.setActionCallback(iMqttActionListener);
            mqttToken.setUserContext(obj);
            mqttToken.internalTok.setTopics(strArr);
            this.comms.sendNoWait(new MqttSubscribe(strArr, iArr), mqttToken);
            log.fine(CLASS_NAME, "subscribe", "109");
            return mqttToken;
        }
        throw new IllegalArgumentException();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        String str = "";
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
                stringBuffer.append(", ");
                str = stringBuffer.toString();
            }
            StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(str));
            stringBuffer2.append(strArr[i]);
            str = stringBuffer2.toString();
            MqttTopic.validate(strArr[i], true);
        }
        log.fine(CLASS_NAME, "unsubscribe", "107", new Object[]{str, obj, iMqttActionListener});
        for (String str2 : strArr) {
            this.comms.removeMessageListener(str2);
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttUnsubscribe(strArr), mqttToken);
        log.fine(CLASS_NAME, "unsubscribe", "110");
        return mqttToken;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        return publish(str, bArr, i, z, null, null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        return publish(str, mqttMessage, (Object) null, (IMqttActionListener) null);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        log.fine(CLASS_NAME, "publish", "111", new Object[]{str, obj, iMqttActionListener});
        MqttTopic.validate(str, false);
        MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(getClientId());
        mqttDeliveryToken.setActionCallback(iMqttActionListener);
        mqttDeliveryToken.setUserContext(obj);
        mqttDeliveryToken.setMessage(mqttMessage);
        mqttDeliveryToken.internalTok.setTopics(new String[]{str});
        this.comms.sendNoWait(new MqttPublish(str, mqttMessage), mqttDeliveryToken);
        log.fine(CLASS_NAME, "publish", "112");
        return mqttDeliveryToken;
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        if (iMqttMessageListenerArr.length == iArr.length && iArr.length == strArr.length) {
            IMqttToken subscribe = subscribe(strArr, iArr, obj, iMqttActionListener);
            for (int i = 0; i < strArr.length; i++) {
                this.comms.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
            }
            return subscribe;
        }
        throw new IllegalArgumentException();
    }
}
