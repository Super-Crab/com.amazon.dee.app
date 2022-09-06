package com.amazon.dee.core.tcomm;

import amazon.communication.CommunicationFactory;
import amazon.communication.CommunicationManager;
import amazon.communication.Message;
import amazon.communication.MessageFactory;
import amazon.communication.MessageHandler;
import amazon.communication.connection.Channels;
import amazon.communication.connection.Connection;
import amazon.communication.connection.ConnectionClosedDetails;
import amazon.communication.connection.KeepAlive;
import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import amazon.communication.identity.ServiceIdentity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Publisher;
import com.amazon.client.metrics.NullMetricEvent;
import com.amazon.client.metrics.common.MetricEventConverter;
import com.amazon.clouddrive.model.NodeType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes12.dex */
public abstract class AbstractTCommService extends Service {
    @VisibleForTesting
    static final String ANDROID_ENDPOINT_SERVICE_NAME = "DPGatewayService";
    private static final int COMMAND_START = 0;
    private static final int COMMAND_STOP = 1;
    @VisibleForTesting
    static final int CONNECTION_ATTEMPT_LIMIT = 10;
    @VisibleForTesting
    static final String DWMS_GATEWAY_CONNECTION_MSG = "GWM MSG 0x0000b479 0x0000003b urn:tcomm-endpoint:device:deviceType:0:deviceSerialNumber:0 0x00000041 urn:tcomm-endpoint:service:serviceName:DeeWebsiteMessagingService {\"command\":\"REGISTER_CONNECTION\"}";
    static final String DWMS_REGISTER_CONNECTION_JSON = "{\"command\":\"REGISTER_CONNECTION\"}";
    private static final int ENDPOINT_CONNECT_RETRY_DELAY_MILLIS = 1000;
    private static final int ENDPOINT_CONNECT_TIMEOUT_MILLIS = 10000;
    @VisibleForTesting
    static final String ENDPOINT_DOMAIN_MASTER = "master";
    @VisibleForTesting
    static final String ENDPOINT_DOMAIN_PROD = "prod";
    @VisibleForTesting
    static final String ENDPOINT_DOMAIN_TEST = "test";
    @VisibleForTesting
    static final String ENDPOINT_SERVICE_REALM = "USAmazon";
    private static final String EXTRA_COMMAND = "command";
    @VisibleForTesting
    static final String FIREOS_ENDPOINT_SERVICE_NAME = "DeeWebsiteMessagingService";
    private static final int RAPID_CYCLE_CONNECTION_DURATION_THRESHOLD_MILLIS = 10000;
    @VisibleForTesting
    static final String STAGE_ALPHA = "alpha";
    @VisibleForTesting
    static final String STAGE_BETA = "beta";
    @VisibleForTesting
    static final String STAGE_GAMMA = "gamma";
    @VisibleForTesting
    static final String STAGE_PREPROD = "preprod";
    @VisibleForTesting
    static final String STAGE_PROD = "prod";
    private CommunicationManager communicationManager;
    private Connection connection;
    private Connection.ConnectionListener connectionListener;
    private volatile int failedConsecutiveConnectionAttempts;
    private volatile long lastAcquiredConnectionTimestamp;
    private MessageHandler messageHandler;
    private static final String TAG = AbstractTCommService.class.getSimpleName();
    private static final ReentrantLock startStopLock = new ReentrantLock(true);
    @GuardedBy("startStopLock")
    private static volatile boolean canStartGuarded = true;
    @GuardedBy("startStopLock")
    private static volatile boolean connectedGuarded = false;

    private Message buildTCommMessage(String str) {
        ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());
        wrap.rewind();
        return createMessageFromFactory(wrap);
    }

    private void deregisterMessageHandler() {
        try {
            this.communicationManager.deregisterMessageHandler(Channels.DEE_WEBSITE_MESSAGING);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Caught exception trying to deregister message handler: ", e, TAG);
        }
    }

    @VisibleForTesting
    static boolean getCanStart() {
        return canStartGuarded;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private EndpointIdentity getEndpoint() {
        char c;
        String buildConfigStage = getBuildConfigStage();
        switch (buildConfigStage.hashCode()) {
            case -318354310:
                if (buildConfigStage.equals("preprod")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3020272:
                if (buildConfigStage.equals("beta")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3449687:
                if (buildConfigStage.equals("prod")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 92909918:
                if (buildConfigStage.equals("alpha")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 98120615:
                if (buildConfigStage.equals("gamma")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1) {
            return createEndpointServiceIdentity(ENDPOINT_DOMAIN_TEST);
        }
        if (c == 2 || c == 3) {
            return createEndpointServiceIdentity(ENDPOINT_DOMAIN_MASTER);
        }
        if (c == 4) {
            return createEndpointServiceIdentity("prod");
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isConnected() {
        return connectedGuarded;
    }

    private void notifyTCommConnected() {
        getPublisher().publish(new Message.Builder().setEventType(TCommEvent.CONNECT).build());
    }

    private void notifyTCommDisconnected() {
        getPublisher().publish(new Message.Builder().setEventType(TCommEvent.DISCONNECT).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTCommMessageReceived(amazon.communication.Message message) {
        if (message == null) {
            Log.w(TAG, "Received null message from TComm; ignoring");
            return;
        }
        InputStream payload = message.getPayload();
        if (payload == null) {
            Log.w(TAG, "Received message from TComm with null payload; ignoring");
        } else if (message.getPayloadSize() <= 0) {
            Log.w(TAG, "Message payload size <= 0; ignoring");
        } else {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(message.getPayloadSize());
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = payload.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        getPublisher().publish(new Message.Builder().setEventType("tcomm::message").setPayload(byteArrayOutputStream.toString()).build());
                        String str = "Publishing message: " + byteArrayOutputStream.toString();
                        return;
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException while reading TComm payload: " + e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionListenerClosed(ConnectionClosedDetails connectionClosedDetails) {
        String str = "onConnectionListenerClosed " + connectionClosedDetails;
        startStopLock.lock();
        try {
            connectedGuarded = false;
            canStartGuarded = true;
            startStopLock.unlock();
            notifyTCommDisconnected();
            if (connectionClosedDetails.getDetailsCode() == 2) {
                int i = this.failedConsecutiveConnectionAttempts + 1;
                this.failedConsecutiveConnectionAttempts = i;
                if (i <= 10) {
                    return;
                }
                Log.e(TAG, "Reached failed connection attempt threshold; bailing.");
                stopService();
            } else if (System.currentTimeMillis() - this.lastAcquiredConnectionTimestamp < 10000) {
                int i2 = this.failedConsecutiveConnectionAttempts + 1;
                this.failedConsecutiveConnectionAttempts = i2;
                if (i2 <= 10) {
                    return;
                }
                Log.e(TAG, "Reached failed connection attempt threshold; bailing.");
                stopService();
            } else {
                this.failedConsecutiveConnectionAttempts = 0;
            }
        } catch (Throwable th) {
            startStopLock.unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionListenerOpened(Connection connection) {
        startStopLock.lock();
        try {
            connectedGuarded = true;
            canStartGuarded = false;
            startStopLock.unlock();
            this.connection = connection;
            this.lastAcquiredConnectionTimestamp = System.currentTimeMillis();
            registerConnection();
            notifyTCommConnected();
        } catch (Throwable th) {
            startStopLock.unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerConnection() {
        try {
            if (this.failedConsecutiveConnectionAttempts >= 10) {
                Log.e(TAG, "Reached failed connection registration attempt limit; bailing.");
                stopService();
                return;
            }
            NullMetricEvent nullMetricEvent = getNullMetricEvent();
            if (this.connection != null) {
                this.connection.sendMessage(buildTCommMessage(DWMS_GATEWAY_CONNECTION_MSG), Channels.GW_CHANNEL, nullMetricEvent);
                return;
            }
            Log.w(TAG, "Connection ref was null. Retrying the connection.");
            this.failedConsecutiveConnectionAttempts++;
            deregisterMessageHandler();
            startTComm();
        } catch (Exception e) {
            Log.e(TAG, "Failed to register connection with DWMS. Will try again. Exception: " + e);
            Handler handler = new Handler(Looper.getMainLooper());
            this.failedConsecutiveConnectionAttempts = this.failedConsecutiveConnectionAttempts + 1;
            handler.postDelayed(new Runnable() { // from class: com.amazon.dee.core.tcomm.-$$Lambda$AbstractTCommService$9Ih4xfPJ8C16RjwCdZDlD9qeBbM
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractTCommService.this.registerConnection();
                }
            }, 1000L);
        }
    }

    private void registerMessageHandler() throws Exception {
        this.communicationManager.registerMessageHandler(Channels.DEE_WEBSITE_MESSAGING, this.messageHandler);
    }

    private static void sendCommand(Class<? extends AbstractTCommService> cls, Context context, int i) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("command", i);
        context.startService(intent);
    }

    public static void start(Class<? extends AbstractTCommService> cls, Context context) {
        startStopLock.lock();
        try {
            if (!canStartGuarded) {
                return;
            }
            canStartGuarded = false;
            sendCommand(cls, context, 0);
        } finally {
            startStopLock.unlock();
        }
    }

    public static void stop(Class<? extends AbstractTCommService> cls, Context context) {
        startStopLock.lock();
        try {
            Intent intent = new Intent(context, cls);
            intent.putExtra("command", 1);
            context.stopService(intent);
        } finally {
            startStopLock.unlock();
        }
    }

    private void stopTCommIfRunning() {
        startStopLock.lock();
        try {
            if (this.connection != null) {
                this.connection.release();
                this.connection = null;
                deregisterMessageHandler();
                notifyTCommDisconnected();
            }
            connectedGuarded = false;
            canStartGuarded = true;
        } finally {
            startStopLock.unlock();
        }
    }

    @VisibleForTesting
    protected ServiceIdentity createEndpointServiceIdentity(String str) {
        return EndpointIdentityFactory.createServiceIdentity(getEndpointServiceName(), str, "USAmazon");
    }

    @VisibleForTesting
    protected amazon.communication.Message createMessageFromFactory(ByteBuffer byteBuffer) {
        return MessageFactory.createMessage(byteBuffer);
    }

    public abstract String getBuildConfigStage();

    @VisibleForTesting
    CommunicationManager getCommunicationManager() {
        return CommunicationFactory.getCommunicationManager(this);
    }

    @NonNull
    String getEndpointServiceName() {
        return ANDROID_ENDPOINT_SERVICE_NAME;
    }

    @NonNull
    @VisibleForTesting
    protected NullMetricEvent getNullMetricEvent() {
        return MetricEventConverter.convertNullMetricEvent_fromCommonToFirstParty(new com.amazon.client.metrics.common.NullMetricEvent("PROGRAM", NodeType.SOURCE));
    }

    @VisibleForTesting
    protected Policy getPolicy() {
        return new Policy.Builder().setReconnectOnFailure(true).setIsClearText(false).setKeepAlive(KeepAlive.STATIC).setPurpose(Purpose.REGULAR).build();
    }

    public abstract Publisher getPublisher();

    public /* synthetic */ void lambda$startTComm$0$AbstractTCommService(EndpointIdentity endpointIdentity, Policy policy) {
        try {
            this.connection = this.communicationManager.acquireConnectedConnection(endpointIdentity, policy, this.connectionListener, 10000);
            registerMessageHandler();
            if (this.connection == null) {
                return;
            }
            onConnectionListenerOpened(this.connection);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Unable to connect to TComm. Will try again. Exception: ", e, TAG);
            stopTCommIfRunning();
        }
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
        this.lastAcquiredConnectionTimestamp = 0L;
        this.failedConsecutiveConnectionAttempts = 0;
        this.communicationManager = getCommunicationManager();
        this.connectionListener = new Connection.ConnectionListener() { // from class: com.amazon.dee.core.tcomm.AbstractTCommService.1
            @Override // amazon.communication.connection.Connection.ConnectionListener
            public void onClosed(Connection connection, ConnectionClosedDetails connectionClosedDetails) {
                AbstractTCommService.this.onConnectionListenerClosed(connectionClosedDetails);
            }

            @Override // amazon.communication.connection.Connection.ConnectionListener
            public void onOpened(Connection connection) {
                AbstractTCommService.this.onConnectionListenerOpened(connection);
            }
        };
        this.messageHandler = new MessageHandler() { // from class: com.amazon.dee.core.tcomm.AbstractTCommService.2
            @Override // amazon.communication.MessageHandler
            public void onMessage(EndpointIdentity endpointIdentity, amazon.communication.Message message) {
                String unused = AbstractTCommService.TAG;
                AbstractTCommService.this.notifyTCommMessageReceived(message);
            }

            @Override // amazon.communication.MessageHandler
            public void onMessageFragment(EndpointIdentity endpointIdentity, int i, amazon.communication.Message message, boolean z) {
                throw new UnsupportedOperationException("onMessageFragment is not implemented");
            }
        };
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        stopTCommIfRunning();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i(TAG, "onStartCommand");
        int i3 = 0;
        if (intent != null) {
            i3 = intent.getIntExtra("command", 0);
        }
        if (i3 != 1) {
            Log.i(TAG, "Starting");
            startTCommIfNeeded();
        } else {
            Log.i(TAG, "Stopping");
            stopService(i2);
        }
        return 1;
    }

    @VisibleForTesting
    void startTComm() throws Exception {
        final EndpointIdentity endpoint = getEndpoint();
        if (endpoint != null) {
            final Policy policy = getPolicy();
            Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.amazon.dee.core.tcomm.-$$Lambda$AbstractTCommService$qKSXTIMUwy4PJhelEIu6qg1peUY
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractTCommService.this.lambda$startTComm$0$AbstractTCommService(endpoint, policy);
                }
            });
            return;
        }
        stopTCommIfRunning();
        throw new Exception(String.format("No endpoint appropriate for the %s build stage", getBuildConfigStage()));
    }

    @VisibleForTesting
    void startTCommIfNeeded() {
        startStopLock.lock();
        try {
            try {
                if (this.connection == null || this.connection.getConnectionState() == 4) {
                    startTComm();
                }
            } catch (Exception e) {
                String str = TAG;
                Log.e(str, "Unable to start TComm: " + e);
                stopTCommIfRunning();
            }
        } finally {
            startStopLock.unlock();
        }
    }

    @VisibleForTesting
    void stopService() {
        stopSelf();
    }

    @VisibleForTesting
    void stopService(int i) {
        stopSelf(i);
    }
}
