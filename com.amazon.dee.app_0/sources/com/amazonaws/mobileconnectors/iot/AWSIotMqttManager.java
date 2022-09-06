package com.amazonaws.mobileconnectors.iot;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttMessageDeliveryCallback;
import com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener;
import com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttToken;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallback;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttMessage;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.amazonaws.org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.amazonaws.regions.Region;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.VersionInfoUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes13.dex */
public class AWSIotMqttManager {
    private final String accountEndpointPrefix;
    private boolean autoReconnect;
    private int autoReconnectsAttempted;
    private AWSCredentialsProvider clientCredentialsProvider;
    private SocketFactory clientSocketFactory;
    private Integer connectionStabilityTime;
    private MqttManagerConnectionState connectionState;
    private int currentReconnectRetryTime;
    private long drainingInterval;
    private final String endpoint;
    private boolean fullQueueKeepsOldest;
    private Boolean isWebSocketClient;
    private Long lastConnackTime;
    private int maxAutoReconnectAttempts;
    private int maxReconnectRetryTime;
    private int minReconnectRetryTime;
    private String mqttBrokerURL;
    private MqttAsyncClient mqttClient;
    private final String mqttClientId;
    private AWSIotMqttLastWillAndTestament mqttLWT;
    private final ConcurrentLinkedQueue<AWSIotMqttQueueMessage> mqttMessageQueue;
    private boolean needResubscribe;
    private Integer offlinePublishQueueBound;
    private boolean offlinePublishQueueEnabled;
    private final Region region;
    private AWSIotWebSocketUrlSigner signer;
    private final Map<String, AWSIotMqttTopic> topicListeners;
    private Long unitTestMillisOverride;
    private boolean userDisconnect;
    private int userKeepAlive;
    private AWSIotMqttClientStatusCallback userStatusCallback;
    private static final Integer ANDROID_API_LEVEL_16 = 16;
    private static final Integer MILLIS_IN_ONE_SECOND = 1000;
    private static final Log LOGGER = LogFactory.getLog(AWSIotMqttManager.class);
    public static final Integer DEFAULT_MIN_RECONNECT_RETRY_TIME_SECONDS = 4;
    public static final Integer DEFAULT_MAX_RECONNECT_RETRY_TIME_SECONDS = 64;
    public static final Boolean DEFAULT_AUTO_RECONNECT_ENABLED = true;
    public static final Integer DEFAULT_AUTO_RECONNECT_ATTEMPTS = 10;
    public static final Integer DEFAULT_KEEP_ALIVE_SECONDS = 300;
    public static final Boolean DEFAULT_OFFLINE_PUBLISH_QUEUE_ENABLED = true;
    public static final Integer DEFAULT_OFFLINE_PUBLISH_QUEUE_BOUND = 100;
    private static final Long DEFAULT_MILLIS_BETWEEN_QUEUE_PUBLISHES = 250L;
    private static final Integer DEFAULT_CONNECTION_STABILITY_TIME_SECONDS = 10;
    private static final String SDK_VERSION = VersionInfoUtils.getVersion();
    private boolean cleanSession = true;
    private boolean metricsIsEnabled = true;

    /* renamed from: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager$7  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState = new int[MqttManagerConnectionState.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState[MqttManagerConnectionState.Connected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState[MqttManagerConnectionState.Connecting.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState[MqttManagerConnectionState.Reconnecting.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState[MqttManagerConnectionState.Disconnected.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public AWSIotMqttManager(String str, String str2) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("mqttClientId is null or empty");
        }
        if (str2 != null) {
            this.topicListeners = new ConcurrentHashMap();
            this.mqttMessageQueue = new ConcurrentLinkedQueue<>();
            this.mqttClientId = str;
            this.endpoint = str2;
            this.accountEndpointPrefix = null;
            this.region = AwsIotEndpointUtility.getRegionFromIotEndpoint(str2);
            initDefaults();
            return;
        }
        throw new IllegalArgumentException("endpoint is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Long getSystemTimeMs() {
        Long l = this.unitTestMillisOverride;
        return l == null ? Long.valueOf(System.currentTimeMillis()) : l;
    }

    private void initDefaults() {
        this.connectionState = MqttManagerConnectionState.Disconnected;
        this.autoReconnect = DEFAULT_AUTO_RECONNECT_ENABLED.booleanValue();
        this.minReconnectRetryTime = DEFAULT_MIN_RECONNECT_RETRY_TIME_SECONDS.intValue();
        this.maxReconnectRetryTime = DEFAULT_MAX_RECONNECT_RETRY_TIME_SECONDS.intValue();
        this.maxAutoReconnectAttempts = DEFAULT_AUTO_RECONNECT_ATTEMPTS.intValue();
        this.userKeepAlive = DEFAULT_KEEP_ALIVE_SECONDS.intValue();
        this.mqttLWT = null;
        this.offlinePublishQueueEnabled = DEFAULT_OFFLINE_PUBLISH_QUEUE_ENABLED.booleanValue();
        this.offlinePublishQueueBound = DEFAULT_OFFLINE_PUBLISH_QUEUE_BOUND;
        this.drainingInterval = DEFAULT_MILLIS_BETWEEN_QUEUE_PUBLISHES.longValue();
        setFullQueueToKeepNewestMessages();
        this.connectionStabilityTime = DEFAULT_CONNECTION_STABILITY_TIME_SECONDS;
        this.unitTestMillisOverride = null;
        this.needResubscribe = true;
    }

    static boolean isTopicMatch(String str, String str2) {
        String[] split = str.split("/");
        String[] split2 = str2.split("/");
        if (split.length > split2.length) {
            return false;
        }
        for (int i = 0; i < split.length; i++) {
            String str3 = split[i];
            String str4 = split2[i];
            if (MqttTopic.MULTI_LEVEL_WILDCARD.equals(str3)) {
                return true;
            }
            if (!"+".equals(str3) && !str3.equals(str4)) {
                return false;
            }
        }
        return split.length == split2.length;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mqttConnect(MqttConnectOptions mqttConnectOptions) {
        LOGGER.debug("ready to do mqtt connect");
        mqttConnectOptions.setCleanSession(this.cleanSession);
        mqttConnectOptions.setKeepAliveInterval(this.userKeepAlive);
        if (isMetricsEnabled()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("?SDK=Android&Version=");
            outline107.append(SDK_VERSION);
            mqttConnectOptions.setUserName(outline107.toString());
        }
        Log log = LOGGER;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("metrics collection is ");
        outline1072.append(isMetricsEnabled() ? "enabled" : FeatureState.DISABLED);
        outline1072.append(", username: ");
        outline1072.append(mqttConnectOptions.getUserName());
        log.info(outline1072.toString());
        this.topicListeners.clear();
        this.mqttMessageQueue.clear();
        resetReconnect();
        this.userDisconnect = false;
        setupCallbackForMqttClient();
        try {
            this.connectionState = MqttManagerConnectionState.Connecting;
            userConnectionCallback();
            this.mqttClient.connect(mqttConnectOptions, null, new IMqttActionListener() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.2
                @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    AWSIotMqttManager.LOGGER.warn("onFailure: connection failed.", th);
                    if (!AWSIotMqttManager.this.userDisconnect && AWSIotMqttManager.this.autoReconnect) {
                        AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Reconnecting;
                        AWSIotMqttManager.this.userConnectionCallback(th);
                        AWSIotMqttManager.this.scheduleReconnect();
                        return;
                    }
                    AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                    AWSIotMqttManager.this.userConnectionCallback(th);
                }

                @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    AWSIotMqttManager.LOGGER.info("onSuccess: mqtt connection is successful.");
                    AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Connected;
                    AWSIotMqttManager aWSIotMqttManager = AWSIotMqttManager.this;
                    aWSIotMqttManager.lastConnackTime = aWSIotMqttManager.getSystemTimeMs();
                    if (AWSIotMqttManager.this.mqttMessageQueue.size() > 0) {
                        AWSIotMqttManager.this.publishMessagesFromQueue();
                    }
                    AWSIotMqttManager.this.userConnectionCallback();
                }
            });
        } catch (MqttException e) {
            int reasonCode = e.getReasonCode();
            if (reasonCode == 32100) {
                this.connectionState = MqttManagerConnectionState.Connected;
                userConnectionCallback();
            } else if (reasonCode != 32110) {
                this.connectionState = MqttManagerConnectionState.Disconnected;
                userConnectionCallback(e);
            } else {
                this.connectionState = MqttManagerConnectionState.Connecting;
                userConnectionCallback();
            }
        } catch (Exception e2) {
            this.connectionState = MqttManagerConnectionState.Disconnected;
            userConnectionCallback(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean scheduleReconnect() {
        Log log = LOGGER;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("schedule Reconnect attempt ");
        outline107.append(this.autoReconnectsAttempted);
        outline107.append(" of ");
        outline107.append(this.maxAutoReconnectAttempts);
        outline107.append(" in ");
        outline107.append(this.currentReconnectRetryTime);
        outline107.append(" seconds.");
        log.info(outline107.toString());
        int i = this.maxAutoReconnectAttempts;
        if (i != -1 && this.autoReconnectsAttempted >= i) {
            LOGGER.warn("schedule reconnect returns false");
            return false;
        }
        final HandlerThread handlerThread = new HandlerThread("Reconnect thread");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).postDelayed(new Runnable() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.4
            @Override // java.lang.Runnable
            public void run() {
                Log log2 = AWSIotMqttManager.LOGGER;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("TID: ");
                outline1072.append(handlerThread.getThreadId());
                outline1072.append(" trying to reconnect to session");
                log2.debug(outline1072.toString());
                if (AWSIotMqttManager.this.mqttClient != null && !AWSIotMqttManager.this.mqttClient.isConnected()) {
                    AWSIotMqttManager.this.reconnectToSession();
                }
                handlerThread.quit();
            }
        }, MILLIS_IN_ONE_SECOND.intValue() * this.currentReconnectRetryTime);
        this.currentReconnectRetryTime = Math.min(this.currentReconnectRetryTime * 2, this.maxReconnectRetryTime);
        return true;
    }

    public void connect(KeyStore keyStore, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        if (Build.VERSION.SDK_INT >= ANDROID_API_LEVEL_16.intValue()) {
            if (keyStore != null) {
                this.userStatusCallback = aWSIotMqttClientStatusCallback;
                if (this.connectionState != MqttManagerConnectionState.Disconnected) {
                    userConnectionCallback();
                    return;
                }
                String str = this.endpoint;
                if (str != null) {
                    this.mqttBrokerURL = String.format("ssl://%s:8883", str);
                } else {
                    String str2 = this.accountEndpointPrefix;
                    if (str2 != null) {
                        this.mqttBrokerURL = String.format("ssl://%s.iot.%s.%s:8883", str2, this.region.getName(), this.region.getDomain());
                    } else {
                        throw new IllegalStateException("No valid endpoint information is available. Please pass in a valid endpoint in AWSIotMqttManager.");
                    }
                }
                this.isWebSocketClient = false;
                Log log = LOGGER;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MQTT broker: ");
                outline107.append(this.mqttBrokerURL);
                log.debug(outline107.toString());
                try {
                    if (this.mqttClient == null) {
                        this.mqttClient = new MqttAsyncClient(this.mqttBrokerURL, this.mqttClientId, new MemoryPersistence());
                    }
                    SSLSocketFactory socketFactoryWithKeyStore = AWSIotSslUtility.getSocketFactoryWithKeyStore(keyStore);
                    MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                    if (this.mqttLWT != null) {
                        mqttConnectOptions.setWill(this.mqttLWT.getTopic(), this.mqttLWT.getMessage().getBytes(), this.mqttLWT.getQos().asInt(), false);
                    }
                    this.clientSocketFactory = socketFactoryWithKeyStore;
                    mqttConnectOptions.setSocketFactory(this.clientSocketFactory);
                    mqttConnect(mqttConnectOptions);
                    return;
                } catch (MqttException e) {
                    throw new AmazonClientException("An error occured in the MQTT client.", e);
                } catch (KeyManagementException e2) {
                    throw new AWSIotCertificateException("A certificate error occurred.", e2);
                } catch (KeyStoreException e3) {
                    throw new AWSIotCertificateException("A certificate error occurred.", e3);
                } catch (NoSuchAlgorithmException e4) {
                    throw new AWSIotCertificateException("A certificate error occurred.", e4);
                } catch (UnrecoverableKeyException e5) {
                    throw new AWSIotCertificateException("A certificate error occurred.", e5);
                }
            }
            throw new IllegalArgumentException("keyStore is null");
        }
        throw new UnsupportedOperationException("API Level 16+ required for TLS 1.2 Mutual Auth");
    }

    public boolean disconnect() {
        this.userDisconnect = true;
        reset();
        this.topicListeners.clear();
        this.connectionState = MqttManagerConnectionState.Disconnected;
        userConnectionCallback();
        return true;
    }

    public boolean fullPublishQueueKeepsOldestMessages() {
        return this.fullQueueKeepsOldest;
    }

    public String getAccountEndpointPrefix() {
        return this.accountEndpointPrefix;
    }

    public int getConnectionStabilityTime() {
        return this.connectionStabilityTime.intValue();
    }

    MqttManagerConnectionState getConnectionState() {
        return this.connectionState;
    }

    public Long getDrainingInterval() {
        return Long.valueOf(this.drainingInterval);
    }

    public int getKeepAlive() {
        return this.userKeepAlive;
    }

    public int getMaxAutoReconnectAttempts() {
        return this.maxAutoReconnectAttempts;
    }

    public int getMaxReconnectRetryTime() {
        return this.maxReconnectRetryTime;
    }

    public int getMinReconnectRetryTime() {
        return this.minReconnectRetryTime;
    }

    public AWSIotMqttLastWillAndTestament getMqttLastWillAndTestament() {
        return this.mqttLWT;
    }

    ConcurrentLinkedQueue<AWSIotMqttQueueMessage> getMqttMessageQueue() {
        return this.mqttMessageQueue;
    }

    public Integer getOfflinePublishQueueBound() {
        return this.offlinePublishQueueBound;
    }

    @Deprecated
    public int getReconnectTimeout() {
        return this.minReconnectRetryTime;
    }

    Region getRegion() {
        return this.region;
    }

    public boolean isAutoReconnect() {
        return this.autoReconnect;
    }

    public boolean isMetricsEnabled() {
        return this.metricsIsEnabled;
    }

    public boolean isOfflinePublishQueueEnabled() {
        return this.offlinePublishQueueEnabled;
    }

    boolean isReadyToPublish() {
        MqttAsyncClient mqttAsyncClient = this.mqttClient;
        return mqttAsyncClient != null && mqttAsyncClient.isConnected();
    }

    void notifyPublishResult(AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus messageDeliveryStatus, Object obj, RuntimeException runtimeException) {
        if (aWSIotMqttMessageDeliveryCallback != null) {
            aWSIotMqttMessageDeliveryCallback.statusChanged(messageDeliveryStatus, obj);
        } else if (runtimeException != null) {
            throw runtimeException;
        }
    }

    public void publishData(byte[] bArr, String str, AWSIotMqttQos aWSIotMqttQos) {
        publishData(bArr, str, aWSIotMqttQos, null, null);
    }

    void publishMessagesFromQueue() {
        ConcurrentLinkedQueue<AWSIotMqttQueueMessage> concurrentLinkedQueue;
        if (this.connectionState != MqttManagerConnectionState.Connected || (concurrentLinkedQueue = this.mqttMessageQueue) == null || concurrentLinkedQueue.isEmpty()) {
            return;
        }
        AWSIotMqttQueueMessage poll = this.mqttMessageQueue.poll();
        if (poll != null) {
            try {
                if (poll.getUserData() != null && poll.getUserData().getUserCallback() != null) {
                    this.mqttClient.publish(poll.getTopic(), poll.getMessage(), poll.getQos().asInt(), false, poll.getUserData(), null);
                } else {
                    this.mqttClient.publish(poll.getTopic(), poll.getMessage(), poll.getQos().asInt(), false);
                }
            } catch (MqttException e) {
                notifyPublishResult(poll.getUserData().getUserCallback(), AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, poll.getUserData().getUserData(), new AmazonClientException("Client error while publishing.", e));
            }
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.5
            @Override // java.lang.Runnable
            public void run() {
                if (AWSIotMqttManager.this.mqttMessageQueue.isEmpty() || AWSIotMqttManager.this.connectionState != MqttManagerConnectionState.Connected) {
                    return;
                }
                AWSIotMqttManager.this.publishMessagesFromQueue();
            }
        }, this.drainingInterval);
    }

    public void publishString(String str, String str2, AWSIotMqttQos aWSIotMqttQos) {
        if (str != null) {
            if (str2 == null || str2.isEmpty()) {
                throw new IllegalArgumentException("topic is null or empty");
            }
            if (aWSIotMqttQos != null) {
                publishData(str.getBytes(StringUtils.UTF8), str2, aWSIotMqttQos);
                return;
            }
            throw new IllegalArgumentException("QoS cannot be null");
        }
        throw new IllegalArgumentException("publish string is null");
    }

    void putMessageInQueueAndNotify(byte[] bArr, String str, AWSIotMqttQos aWSIotMqttQos, PublishMessageUserData publishMessageUserData) {
        AWSIotMqttQueueMessage aWSIotMqttQueueMessage = new AWSIotMqttQueueMessage(str, bArr, aWSIotMqttQos, publishMessageUserData);
        if (this.mqttMessageQueue.size() >= this.offlinePublishQueueBound.intValue()) {
            if (this.fullQueueKeepsOldest) {
                notifyPublishResult(publishMessageUserData.getUserCallback(), AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, publishMessageUserData.getUserData(), new AmazonClientException("Failed to publish the message. Queue is full and set to hold onto the oldest messages."));
                return;
            }
            this.mqttMessageQueue.remove(0);
        }
        this.mqttMessageQueue.add(aWSIotMqttQueueMessage);
    }

    void reconnectToSession() {
        String format;
        if (this.mqttClient == null || this.connectionState == MqttManagerConnectionState.Disconnected) {
            return;
        }
        LOGGER.info("attempting to reconnect to mqtt broker");
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(this.cleanSession);
        mqttConnectOptions.setKeepAliveInterval(this.userKeepAlive);
        AWSIotMqttLastWillAndTestament aWSIotMqttLastWillAndTestament = this.mqttLWT;
        if (aWSIotMqttLastWillAndTestament != null) {
            mqttConnectOptions.setWill(aWSIotMqttLastWillAndTestament.getTopic(), this.mqttLWT.getMessage().getBytes(), this.mqttLWT.getQos().asInt(), false);
        }
        if (this.isWebSocketClient.booleanValue()) {
            this.signer = new AWSIotWebSocketUrlSigner("iotdata");
            String str = this.endpoint;
            if (str != null) {
                format = String.format("%s:443", str);
            } else {
                String str2 = this.accountEndpointPrefix;
                if (str2 != null) {
                    format = String.format("%s.iot.%s.%s:443", str2, this.region.getName(), this.region.getDomain());
                } else {
                    throw new IllegalStateException("No valid endpoint information is available. Please pass in a valid endpoint in AWSIotMqttManager.");
                }
            }
            try {
                String signedUrl = this.signer.getSignedUrl(format, this.clientCredentialsProvider.mo6648getCredentials(), System.currentTimeMillis());
                LOGGER.debug("Reconnect to mqtt broker: " + this.endpoint + " mqttWebSocketURL: " + signedUrl);
                mqttConnectOptions.setServerURIs(new String[]{signedUrl});
            } catch (AmazonClientException e) {
                LOGGER.error("Failed to get credentials. AmazonClientException: ", e);
                if (scheduleReconnect()) {
                    this.connectionState = MqttManagerConnectionState.Reconnecting;
                } else {
                    this.connectionState = MqttManagerConnectionState.Disconnected;
                }
                userConnectionCallback(e);
            }
        } else {
            mqttConnectOptions.setSocketFactory(this.clientSocketFactory);
        }
        setupCallbackForMqttClient();
        try {
            this.autoReconnectsAttempted++;
            LOGGER.debug("mqtt reconnecting attempt " + this.autoReconnectsAttempted);
            this.mqttClient.connect(mqttConnectOptions, null, new IMqttActionListener() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.3
                @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    AWSIotMqttManager.LOGGER.warn("Reconnect failed ", th);
                    if (AWSIotMqttManager.this.scheduleReconnect()) {
                        AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Reconnecting;
                        AWSIotMqttManager.this.userConnectionCallback(th);
                        return;
                    }
                    AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                    AWSIotMqttManager.this.userConnectionCallback(th);
                }

                @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    AWSIotMqttManager.LOGGER.info("Reconnect successful");
                    AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Connected;
                    AWSIotMqttManager aWSIotMqttManager = AWSIotMqttManager.this;
                    aWSIotMqttManager.lastConnackTime = aWSIotMqttManager.getSystemTimeMs();
                    if (AWSIotMqttManager.this.needResubscribe) {
                        AWSIotMqttManager.this.resubscribeToTopics();
                    }
                    if (AWSIotMqttManager.this.mqttMessageQueue.size() > 0) {
                        AWSIotMqttManager.this.publishMessagesFromQueue();
                    }
                    AWSIotMqttManager.this.userConnectionCallback();
                }
            });
        } catch (MqttException e2) {
            LOGGER.error("Exception during reconnect, exception: ", e2);
            if (scheduleReconnect()) {
                this.connectionState = MqttManagerConnectionState.Reconnecting;
                userConnectionCallback(e2);
                return;
            }
            this.connectionState = MqttManagerConnectionState.Disconnected;
            userConnectionCallback(e2);
        }
    }

    void reset() {
        MqttAsyncClient mqttAsyncClient = this.mqttClient;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            return;
        }
        try {
            this.mqttClient.disconnect(0L);
        } catch (MqttException e) {
            throw new AmazonClientException("Client error when disconnecting.", e);
        }
    }

    public void resetReconnect() {
        LOGGER.info("resetting reconnect attempt and retry time");
        this.autoReconnectsAttempted = 0;
        this.currentReconnectRetryTime = this.minReconnectRetryTime;
    }

    void resubscribeToTopics() {
        LOGGER.info("Auto-resubscribe is enabled. Resubscribing to previous topics.");
        for (AWSIotMqttTopic aWSIotMqttTopic : this.topicListeners.values()) {
            MqttAsyncClient mqttAsyncClient = this.mqttClient;
            if (mqttAsyncClient != null) {
                try {
                    mqttAsyncClient.subscribe(aWSIotMqttTopic.getTopic(), aWSIotMqttTopic.getQos().asInt());
                } catch (MqttException e) {
                    LOGGER.error("Error while resubscribing to previously subscribed toipcs.", e);
                }
            }
        }
    }

    public void setAutoReconnect(boolean z) {
        this.autoReconnect = z;
    }

    public void setAutoResubscribe(boolean z) {
        this.needResubscribe = z;
    }

    public void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    public void setConnectionStabilityTime(int i) {
        this.connectionStabilityTime = Integer.valueOf(i);
    }

    public void setCredentialsProvider(AWSCredentialsProvider aWSCredentialsProvider) {
        this.clientCredentialsProvider = aWSCredentialsProvider;
    }

    public void setDrainingInterval(Long l) {
        this.drainingInterval = l.longValue();
    }

    public void setFullQueueToKeepNewestMessages() {
        this.fullQueueKeepsOldest = false;
    }

    public void setFullQueueToKeepOldestMessages() {
        this.fullQueueKeepsOldest = true;
    }

    public void setKeepAlive(int i) {
        if (i >= 0) {
            this.userKeepAlive = i;
            return;
        }
        throw new IllegalArgumentException("Keep alive must be >= 0");
    }

    public void setMaxAutoReconnectAttempts(int i) {
        if (i <= 0 && i != -1) {
            throw new IllegalArgumentException("Max reconnection attempts must be postive or -1");
        }
        this.maxAutoReconnectAttempts = i;
    }

    @Deprecated
    public void setMaxAutoReconnectAttepts(int i) {
        setMaxAutoReconnectAttempts(i);
    }

    public void setMetricsIsEnabled(boolean z) {
        this.metricsIsEnabled = z;
        Log log = LOGGER;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Metrics collection is ");
        outline107.append(this.metricsIsEnabled ? "enabled" : FeatureState.DISABLED);
        log.info(outline107.toString());
    }

    void setMqttClient(MqttAsyncClient mqttAsyncClient) {
        this.mqttClient = mqttAsyncClient;
    }

    public void setMqttLastWillAndTestament(AWSIotMqttLastWillAndTestament aWSIotMqttLastWillAndTestament) {
        this.mqttLWT = aWSIotMqttLastWillAndTestament;
    }

    public void setOfflinePublishQueueBound(Integer num) {
        if (num.intValue() > 0) {
            this.offlinePublishQueueBound = num;
            return;
        }
        throw new IllegalArgumentException("Offline queue bound must be > 0");
    }

    public void setOfflinePublishQueueEnabled(boolean z) {
        this.offlinePublishQueueEnabled = z;
    }

    public void setReconnectRetryLimits(int i, int i2) {
        if (i <= i2) {
            this.minReconnectRetryTime = i;
            this.maxReconnectRetryTime = i2;
            return;
        }
        throw new IllegalArgumentException("Minimum reconnect time needs to be less than Maximum.");
    }

    @Deprecated
    public void setReconnectTimeout(int i) {
        this.minReconnectRetryTime = i;
    }

    void setUnitTestMillisOverride(Long l) {
        this.unitTestMillisOverride = l;
    }

    void setupCallbackForMqttClient() {
        LOGGER.debug("Setting up Callback for MqttClient");
        this.mqttClient.setCallback(new MqttCallback() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.6
            @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallback
            public void connectionLost(Throwable th) {
                AWSIotMqttManager.LOGGER.warn("connection is Lost");
                if (!AWSIotMqttManager.this.userDisconnect && AWSIotMqttManager.this.autoReconnect) {
                    if (AWSIotMqttManager.this.lastConnackTime.longValue() + (AWSIotMqttManager.MILLIS_IN_ONE_SECOND.intValue() * AWSIotMqttManager.this.connectionStabilityTime.intValue()) < AWSIotMqttManager.this.getSystemTimeMs().longValue()) {
                        AWSIotMqttManager.this.resetReconnect();
                    }
                    if (AWSIotMqttManager.this.scheduleReconnect()) {
                        AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Reconnecting;
                    } else {
                        AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                    }
                } else {
                    AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                }
                AWSIotMqttManager.this.userConnectionCallback(th);
            }

            @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallback
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                AWSIotMqttManager.LOGGER.info("delivery is complete");
                if (iMqttDeliveryToken != null) {
                    Object userContext = iMqttDeliveryToken.getUserContext();
                    if (!(userContext instanceof PublishMessageUserData)) {
                        return;
                    }
                    PublishMessageUserData publishMessageUserData = (PublishMessageUserData) userContext;
                    AWSIotMqttManager.this.notifyPublishResult(publishMessageUserData.getUserCallback(), AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Success, publishMessageUserData.getUserData(), null);
                }
            }

            @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttCallback
            public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
                AWSIotMqttTopic aWSIotMqttTopic;
                Log log = AWSIotMqttManager.LOGGER;
                log.info("message arrived on topic: " + str);
                byte[] payload = mqttMessage.getPayload();
                for (String str2 : AWSIotMqttManager.this.topicListeners.keySet()) {
                    if (AWSIotMqttManager.isTopicMatch(str2, str) && (aWSIotMqttTopic = (AWSIotMqttTopic) AWSIotMqttManager.this.topicListeners.get(str2)) != null && aWSIotMqttTopic.getCallback() != null) {
                        aWSIotMqttTopic.getCallback().onMessageArrived(str, payload);
                    }
                }
            }
        });
    }

    public void subscribeToTopic(String str, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttNewMessageCallback aWSIotMqttNewMessageCallback) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("topic is null or empty");
        }
        if (aWSIotMqttQos != null) {
            MqttAsyncClient mqttAsyncClient = this.mqttClient;
            if (mqttAsyncClient == null) {
                return;
            }
            try {
                mqttAsyncClient.subscribe(str, aWSIotMqttQos.asInt());
                this.topicListeners.put(str, new AWSIotMqttTopic(str, aWSIotMqttQos, aWSIotMqttNewMessageCallback));
                return;
            } catch (MqttException e) {
                throw new AmazonClientException("Client error when subscribing.", e);
            }
        }
        throw new IllegalArgumentException("QoS cannot be null.");
    }

    public void unsubscribeTopic(String str) {
        if (str != null && !str.isEmpty()) {
            MqttAsyncClient mqttAsyncClient = this.mqttClient;
            if (mqttAsyncClient == null) {
                return;
            }
            try {
                mqttAsyncClient.unsubscribe(str);
                this.topicListeners.remove(str);
                return;
            } catch (MqttException e) {
                throw new AmazonClientException("Client error while unsubscribing.", e);
            }
        }
        throw new IllegalArgumentException("topic is null or empty");
    }

    void userConnectionCallback() {
        userConnectionCallback(null);
    }

    public void publishData(byte[] bArr, String str, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, Object obj) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("topic is null or empty");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (aWSIotMqttQos != null) {
            PublishMessageUserData publishMessageUserData = new PublishMessageUserData(aWSIotMqttMessageDeliveryCallback, obj);
            MqttManagerConnectionState mqttManagerConnectionState = this.connectionState;
            if (mqttManagerConnectionState == MqttManagerConnectionState.Connected) {
                if (this.mqttMessageQueue.isEmpty()) {
                    try {
                        this.mqttClient.publish(str, bArr, aWSIotMqttQos.asInt(), false, publishMessageUserData, null);
                        return;
                    } catch (MqttException e) {
                        notifyPublishResult(aWSIotMqttMessageDeliveryCallback, AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, obj, new AmazonClientException("Client error while publishing.", e));
                        return;
                    }
                }
                putMessageInQueueAndNotify(bArr, str, aWSIotMqttQos, publishMessageUserData);
                return;
            } else if (mqttManagerConnectionState == MqttManagerConnectionState.Reconnecting) {
                if (this.offlinePublishQueueEnabled) {
                    putMessageInQueueAndNotify(bArr, str, aWSIotMqttQos, publishMessageUserData);
                    return;
                } else {
                    notifyPublishResult(aWSIotMqttMessageDeliveryCallback, AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, obj, new AmazonClientException("Client error while publishing : Offline publish queue is not enabled and client is not connected"));
                    return;
                }
            } else {
                notifyPublishResult(aWSIotMqttMessageDeliveryCallback, AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, obj, new AmazonClientException("Client is disconnected or not yet connected."));
                return;
            }
        }
        throw new IllegalArgumentException("QoS cannot be null");
    }

    void userConnectionCallback(Throwable th) {
        if (this.userStatusCallback != null) {
            int ordinal = this.connectionState.ordinal();
            if (ordinal == 0) {
                this.userStatusCallback.onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connecting, th);
            } else if (ordinal == 1) {
                this.userStatusCallback.onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected, th);
            } else if (ordinal == 2) {
                this.userStatusCallback.onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.ConnectionLost, th);
            } else if (ordinal == 3) {
                this.userStatusCallback.onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Reconnecting, th);
            } else {
                throw new IllegalStateException("Unknown connection state.");
            }
        }
    }

    public void publishString(String str, String str2, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, Object obj) {
        if (str != null) {
            if (str2 == null || str2.isEmpty()) {
                throw new IllegalArgumentException("topic is null or empty");
            }
            if (aWSIotMqttQos != null) {
                publishData(str.getBytes(StringUtils.UTF8), str2, aWSIotMqttQos, aWSIotMqttMessageDeliveryCallback, obj);
                return;
            }
            throw new IllegalArgumentException("QoS cannot be null");
        }
        throw new IllegalArgumentException("publish string is null");
    }

    public AWSIotMqttManager(String str, Region region, String str2) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("mqttClientId is null or empty");
        }
        if (region == null) {
            throw new IllegalArgumentException("region is null");
        }
        if (str2 != null) {
            this.topicListeners = new ConcurrentHashMap();
            this.mqttMessageQueue = new ConcurrentLinkedQueue<>();
            this.accountEndpointPrefix = str2;
            this.mqttClientId = str;
            this.region = region;
            this.endpoint = null;
            initDefaults();
            return;
        }
        throw new IllegalArgumentException("accountEndpointPrefix is null");
    }

    public void connect(AWSCredentialsProvider aWSCredentialsProvider, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        this.clientCredentialsProvider = aWSCredentialsProvider;
        if (aWSCredentialsProvider != null) {
            this.userStatusCallback = aWSIotMqttClientStatusCallback;
            if (this.connectionState != MqttManagerConnectionState.Disconnected) {
                userConnectionCallback();
                return;
            } else {
                new Thread(new Runnable() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String format;
                        AWSIotMqttManager.this.signer = new AWSIotWebSocketUrlSigner("iotdata");
                        if (AWSIotMqttManager.this.endpoint != null) {
                            format = String.format("%s:443", AWSIotMqttManager.this.endpoint);
                        } else if (AWSIotMqttManager.this.accountEndpointPrefix != null) {
                            format = String.format("%s.iot.%s.%s:443", AWSIotMqttManager.this.accountEndpointPrefix, AWSIotMqttManager.this.region.getName(), AWSIotMqttManager.this.region.getDomain());
                        } else {
                            throw new IllegalStateException("No valid endpoint information is available. Please pass in a valid endpoint in AWSIotMqttManager.");
                        }
                        AWSIotMqttManager.this.isWebSocketClient = true;
                        Log log = AWSIotMqttManager.LOGGER;
                        log.debug("MQTT broker: " + format);
                        try {
                            String signedUrl = AWSIotMqttManager.this.signer.getSignedUrl(format, AWSIotMqttManager.this.clientCredentialsProvider.mo6648getCredentials(), System.currentTimeMillis());
                            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                            mqttConnectOptions.setServerURIs(new String[]{signedUrl});
                            if (AWSIotMqttManager.this.mqttLWT != null) {
                                mqttConnectOptions.setWill(AWSIotMqttManager.this.mqttLWT.getTopic(), AWSIotMqttManager.this.mqttLWT.getMessage().getBytes(), AWSIotMqttManager.this.mqttLWT.getQos().asInt(), false);
                            }
                            if (AWSIotMqttManager.this.mqttClient == null) {
                                AWSIotMqttManager aWSIotMqttManager = AWSIotMqttManager.this;
                                aWSIotMqttManager.mqttClient = new MqttAsyncClient("wss://" + format, AWSIotMqttManager.this.mqttClientId, new MemoryPersistence());
                            }
                            AWSIotMqttManager.this.mqttConnect(mqttConnectOptions);
                        } catch (MqttException e) {
                            AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                            AWSIotMqttManager.this.userConnectionCallback(new AmazonClientException("An error occurred in the MQTT client.", e));
                        } catch (Exception e2) {
                            AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                            AWSIotMqttManager.this.userConnectionCallback(e2);
                        }
                    }
                }, "Mqtt Connect Thread").start();
                return;
            }
        }
        throw new IllegalArgumentException("credentials provider cannot be null");
    }
}
