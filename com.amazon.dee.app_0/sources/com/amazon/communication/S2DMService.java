package com.amazon.communication;

import amazon.communication.CommunicationFactoryBase;
import amazon.communication.ICommunicationManager;
import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.RegistrationFailedException;
import amazon.communication.RemoteCommunicationManagerBase;
import amazon.communication.ServiceConnectivityListener;
import amazon.communication.identity.EndpointIdentity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.org.codehaus.jackson.map.ObjectMapper;
import com.dp.utils.DpBackgroundThreadFactory;
import com.dp.utils.DpExecutors;
import com.dp.utils.DpThreadPoolExecutor;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class S2DMService extends Service {
    private static final String BROADCAST_RECEIVER_DISPATCH_WAKELOCK_TIMEOUT_MILLIS_KEY = "Setting.BroadcastReceiver.DispatchWakelockTimeoutMillis";
    private static final String IDENTITY_EXTRA = "com.amazon.dcp.messaging.TCOMM_ENDPOINT_IDENTITY_PAYLOAD";
    private static final String INTENT_ACTION = "amazon.communication.action.HANDLE_TCOMM_MESSAGE";
    private static final String PAYLOAD_EXTRA = "com.amazon.dcp.messaging.TCOMM_MESSAGE_PAYLOAD";
    private static final String TAG = "TComm.S2DMService";
    private static final DPLogger log = new DPLogger(TAG);
    private ICommunicationManager mCommunicationManager;
    private DpThreadPoolExecutor mExecutor;
    private MessageHandler mMessageHandler;
    private ServiceConnectivityListener mServiceConnectivityListener;

    /* loaded from: classes12.dex */
    public static class InitializationReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            S2DMService.log.info("onReceive", MAPAccountManager.KEY_INTENT, intent);
            context.startService(new Intent(context, S2DMService.class));
        }
    }

    /* loaded from: classes12.dex */
    private class S2DMListener implements MessageHandler {
        private S2DMListener() {
        }

        private String getMessageIdFromMessagePayload(byte[] bArr) {
            Object obj;
            try {
                obj = ((Map) ((Map) new ObjectMapper().readValue(bArr, HashMap.class)).get("message")).get("id");
            } catch (Exception e) {
                S2DMService.log.error("onMessage", "Failed to get Message Id from message payload", e);
                obj = null;
            }
            return String.valueOf(obj);
        }

        private void sendBroadcastToS2DMReceiver(Intent intent) {
            PowerManager.WakeLock newWakeLock = ((PowerManager) S2DMService.this.getSystemService("power")).newWakeLock(1, S2DMService.TAG);
            newWakeLock.setReferenceCounted(false);
            newWakeLock.acquire(S2DMService.getBroadcastReceiverDispatchWakelockTimeoutMillis());
            try {
                S2DMService.this.getApplicationContext().sendOrderedBroadcast(intent, "amazon.permission.USE_TCOMM", new WakefulBroadcastResultReceiver(newWakeLock), null, 0, null, null);
            } catch (RuntimeException e) {
                if (newWakeLock.isHeld()) {
                    newWakeLock.release();
                }
                throw e;
            }
        }

        @Override // amazon.communication.MessageHandler
        public void onMessage(EndpointIdentity endpointIdentity, Message message) {
            S2DMService.log.debug("onMessage", "got message", "source", EndpointIdentity.logSafe(endpointIdentity));
            Intent intent = new Intent();
            try {
                InputStream payload = message.getPayload();
                int available = payload.available();
                byte[] bArr = new byte[payload.available()];
                int read = payload.read(bArr);
                String messageIdFromMessagePayload = getMessageIdFromMessagePayload(bArr);
                if (read != available) {
                    S2DMService.log.warn("onMessage", "did not read all byes from payload", "expected", Integer.valueOf(available), "read", Integer.valueOf(read), "id", messageIdFromMessagePayload);
                } else {
                    intent.setAction(S2DMService.INTENT_ACTION);
                    intent.putExtra(S2DMService.IDENTITY_EXTRA, endpointIdentity.toString());
                    intent.putExtra(S2DMService.PAYLOAD_EXTRA, bArr);
                    sendBroadcastToS2DMReceiver(intent);
                    S2DMService.log.info("onMessage", "intent sent", "id", messageIdFromMessagePayload);
                }
            } catch (IOException e) {
                S2DMService.log.error("onMessage", "IOException extracting payload from message", e);
            }
        }

        @Override // amazon.communication.MessageHandler
        public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
            throw new UnsupportedOperationException("onMessageFragment not implemented");
        }
    }

    /* loaded from: classes12.dex */
    public class WakefulBroadcastResultReceiver extends BroadcastReceiver {
        private final PowerManager.WakeLock mWakeLock;

        public WakefulBroadcastResultReceiver(PowerManager.WakeLock wakeLock) {
            this.mWakeLock = wakeLock;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            this.mWakeLock.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enqueueDeregisterMessageHandler() {
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.S2DMService.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    S2DMService.this.mCommunicationManager.deregisterMessageHandler(480);
                } catch (RegistrationFailedException e) {
                    S2DMService.log.error("deregisterMessageHandler", "error deregistering message handler", e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enqueueRegisterMessageHandler() {
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.communication.S2DMService.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    S2DMService.this.mCommunicationManager.registerMessageHandler(480, S2DMService.this.mMessageHandler);
                } catch (RegistrationFailedException e) {
                    S2DMService.log.error("registerMessageHandler", "error registering message handler", e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getBroadcastReceiverDispatchWakelockTimeoutMillis() {
        return RemoteSettingManager.getOptLongValue(BROADCAST_RECEIVER_DISPATCH_WAKELOCK_TIMEOUT_MILLIS_KEY, Long.valueOf(TimeUnit.MILLISECONDS.convert(30L, TimeUnit.SECONDS))).longValue();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        log.info("onCreate", "service created", new Object[0]);
        this.mMessageHandler = new S2DMListener();
        this.mExecutor = DpExecutors.newFixedThreadPool(1, new DpBackgroundThreadFactory("S2DMServiceThread"), new TCommUncaughtExceptionHandler());
        this.mCommunicationManager = CommunicationFactoryBase.getCommunicationManager(this);
        this.mServiceConnectivityListener = new ServiceConnectivityListener() { // from class: com.amazon.communication.S2DMService.1
            @Override // amazon.communication.ServiceConnectivityListener
            public void onServiceConnected() {
                S2DMService.this.enqueueRegisterMessageHandler();
            }

            @Override // amazon.communication.ServiceConnectivityListener
            public void onServiceDisconnected() {
                S2DMService.this.enqueueDeregisterMessageHandler();
            }
        };
        ((RemoteCommunicationManagerBase) this.mCommunicationManager).registerServiceConnectivityListener(this.mServiceConnectivityListener);
    }

    @Override // android.app.Service
    public void onDestroy() {
        enqueueDeregisterMessageHandler();
        this.mExecutor.shutdown();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        log.info("onStartCommand", "service started", MAPAccountManager.KEY_INTENT, intent, "flags", Integer.valueOf(i), AppUrl.ACMS.QueryParam.Keys.MESSAGE_START_ID, Integer.valueOf(i2));
        return 1;
    }
}
