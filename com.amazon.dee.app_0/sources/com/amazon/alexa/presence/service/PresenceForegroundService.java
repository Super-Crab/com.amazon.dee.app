package com.amazon.alexa.presence.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import com.amazon.alexa.presence.library.Compatibility;
import com.amazon.alexa.presence.library.MetricsRecorder;
import com.amazon.alexa.presence.logging.PresenceSlf4jAndroidLoggerFactory;
import com.amazon.alexa.presence.metrics.MetricsId;
import com.amazon.alexa.presence.metrics.MetricsMethod;
import com.amazon.alexa.presence.service.PresenceForegroundService;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.alexa.presence.service.receivers.PresenceForegroundServiceReceiver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.Callable;
import org.slf4j.impl.AlexaSlf4jAndroidLoggerFactory;
/* loaded from: classes9.dex */
public class PresenceForegroundService extends Service {
    public static final String ACTION_ROTATE_DATA = "com.amazon.alexa.intent.action.PRESENCE_ROTATE_DATA_ID";
    public static final String ACTION_START_SERVICE = "com.amazon.alexa.intent.action.PRESENCE_START";
    public static final String ACTION_STOP_SERVICE = "com.amazon.alexa.intent.action.PRESENCE_STOP";
    private static final String ALEXA_GENERAL_NOTIFICATION_CHANNEL = "alexa_notification_channel";
    private static final int SERVICE_ID = 24856;
    private static final String TAG = PresenceForegroundService.class.getName();
    private boolean isInitialized = false;
    private MetricsRecorder metrics;
    private PresenceBleService presenceBleService;
    private BroadcastReceiver receiver;

    /* loaded from: classes9.dex */
    public static class Controls {
        private final Context ctx;
        private final PresenceForegroundServiceStateAdviser serviceHelper;

        public Controls(Context context) {
            this.ctx = context.getApplicationContext();
            this.serviceHelper = new PresenceForegroundServiceStateAdviser(context);
        }

        private Intent startServiceIntent() {
            Intent intent = new Intent(this.ctx, PresenceForegroundService.class);
            intent.setAction(PresenceForegroundService.ACTION_START_SERVICE);
            return intent;
        }

        private Intent stopServiceIntent() {
            Intent intent = new Intent(this.ctx, PresenceForegroundService.class);
            intent.setAction(PresenceForegroundService.ACTION_STOP_SERVICE);
            return intent;
        }

        public /* synthetic */ ComponentName lambda$notifyPresenceServiceToRotateServiceData$2$PresenceForegroundService$Controls() throws Exception {
            return this.ctx.startForegroundService(rotateServiceDataIdIntent());
        }

        public /* synthetic */ ComponentName lambda$notifyPresenceServiceToRotateServiceData$3$PresenceForegroundService$Controls() throws Exception {
            return this.ctx.startService(rotateServiceDataIdIntent());
        }

        public /* synthetic */ ComponentName lambda$notifyPresenceServiceToRun$0$PresenceForegroundService$Controls() throws Exception {
            return this.ctx.startForegroundService(startServiceIntent());
        }

        public /* synthetic */ ComponentName lambda$notifyPresenceServiceToRun$1$PresenceForegroundService$Controls() throws Exception {
            return this.ctx.startService(startServiceIntent());
        }

        public void notifyPresenceServiceToRotateServiceData() {
            if (!this.serviceHelper.serviceShouldBeRunning()) {
                Log.w(PresenceForegroundService.TAG, "Presence service requested to rotate data, but it should not be running right now.");
            } else {
                Compatibility.ifAndroidOOrLater(new Callable() { // from class: com.amazon.alexa.presence.service.-$$Lambda$PresenceForegroundService$Controls$ilqUrv2fASSecg75XPpAYdjQt18
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return PresenceForegroundService.Controls.this.lambda$notifyPresenceServiceToRotateServiceData$2$PresenceForegroundService$Controls();
                    }
                }, new Callable() { // from class: com.amazon.alexa.presence.service.-$$Lambda$PresenceForegroundService$Controls$U1WIfanTTJzFRbsZLp1zWxdfVPQ
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return PresenceForegroundService.Controls.this.lambda$notifyPresenceServiceToRotateServiceData$3$PresenceForegroundService$Controls();
                    }
                });
            }
        }

        public void notifyPresenceServiceToRun() {
            if (!this.serviceHelper.serviceShouldBeRunning()) {
                Log.w(PresenceForegroundService.TAG, "Presence service requested to start, but not in a state where it can run.");
            } else {
                Compatibility.ifAndroidOOrLater(new Callable() { // from class: com.amazon.alexa.presence.service.-$$Lambda$PresenceForegroundService$Controls$35v3t3C4aaaoLOXYGDQlu8xFWEo
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return PresenceForegroundService.Controls.this.lambda$notifyPresenceServiceToRun$0$PresenceForegroundService$Controls();
                    }
                }, new Callable() { // from class: com.amazon.alexa.presence.service.-$$Lambda$PresenceForegroundService$Controls$wN7LMI0K8YAfbHotruX5CV3Xbjw
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return PresenceForegroundService.Controls.this.lambda$notifyPresenceServiceToRun$1$PresenceForegroundService$Controls();
                    }
                });
            }
        }

        public void notifyPresenceServiceToStop() {
            this.ctx.stopService(stopServiceIntent());
        }

        public Intent rotateServiceDataIdIntent() {
            Intent intent = new Intent(this.ctx, PresenceForegroundService.class);
            intent.setAction(PresenceForegroundService.ACTION_ROTATE_DATA);
            return intent;
        }
    }

    static {
        AlexaSlf4jAndroidLoggerFactory.subscribe(PresenceSlf4jAndroidLoggerFactory.DEFAULT);
    }

    @RequiresApi(api = 26)
    private NotificationChannel getAlexaGeneralNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(ALEXA_GENERAL_NOTIFICATION_CHANNEL, getResources().getString(R.string.presence_service_notification_general_channel_title), 2);
        notificationChannel.setShowBadge(false);
        return notificationChannel;
    }

    private void handleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        char c = 65535;
        int hashCode = action.hashCode();
        if (hashCode != -1286898152) {
            if (hashCode != -1239150356) {
                if (hashCode == -444091006 && action.equals(ACTION_ROTATE_DATA)) {
                    c = 0;
                }
            } else if (action.equals(ACTION_START_SERVICE)) {
                c = 1;
            }
        } else if (action.equals(ACTION_STOP_SERVICE)) {
            c = 2;
        }
        if (c == 0) {
            if (!this.presenceBleService.isRunning()) {
                return;
            }
            Log.i(TAG, "Requested to rotate service data.");
            this.metrics.recordCount(MetricsId.PHONE_ID_ROTATED, MetricsMethod.PHONE_ID);
            this.presenceBleService.rotateServiceData();
            Log.i(TAG, "Service data rotated.");
        } else if (c == 1) {
            startBleService();
        } else if (c != 2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unhandled action received ");
            outline107.append(intent.getAction());
            outline107.toString();
        } else {
            stopBleService();
        }
    }

    private void setupForegroundServiceNotification() {
        setupNotificationChannel();
        String string = getResources().getString(R.string.presence_service_notification_title);
        startForeground(SERVICE_ID, new NotificationCompat.Builder(this, ALEXA_GENERAL_NOTIFICATION_CHANNEL).setContentTitle(string).setContentText(getResources().getString(R.string.presence_service_notification_text)).setSmallIcon(R.drawable.ic_alexaicon).setColor(getResources().getColor(R.color.notification_color)).setPriority(-1).setVisibility(-1).setShowWhen(false).build());
    }

    private void setupNotificationChannel() {
        Compatibility.ifAndroidOOrLater(new Callable() { // from class: com.amazon.alexa.presence.service.-$$Lambda$PresenceForegroundService$T_acYfVRB5ljrYCZvt9xp-rrVAs
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return PresenceForegroundService.this.lambda$setupNotificationChannel$0$PresenceForegroundService();
            }
        });
    }

    private void startBleService() {
        Log.i(TAG, "Presence Foreground Service initializing....");
        this.presenceBleService.start();
        PresenceJobService.Helper helper = new PresenceJobService.Helper(this);
        helper.startRotatingServiceData();
        helper.startMonitoringTokenFreshness();
        Log.i(TAG, "Presence Foreground Service initialized.");
    }

    private void stopAndroidForegroundService() {
        stopForeground(true);
        stopSelf();
    }

    private void stopBleService() {
        PresenceJobService.Helper helper = new PresenceJobService.Helper(this);
        helper.stopRotatingServiceData();
        helper.stopMonitoringTokenFreshness();
        this.presenceBleService.stop();
    }

    public /* synthetic */ Object lambda$setupNotificationChannel$0$PresenceForegroundService() throws Exception {
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(getAlexaGeneralNotificationChannel());
        return null;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        BluetoothManager bluetoothManager = (BluetoothManager) getApplicationContext().getSystemService("bluetooth");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothManager != null && defaultAdapter != null) {
            this.presenceBleService = new PresenceBleService(getApplicationContext(), bluetoothManager, defaultAdapter);
            this.isInitialized = true;
        } else {
            Log.w(TAG, "Unable to locate required Bluetooth management APIs. Will not be able to start GATT server.");
        }
        try {
            this.metrics = MetricsRecorder.getMetricsUtil();
        } catch (Throwable unused) {
            Log.w(TAG, "Unable to setup metrics service.");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            if (this.isInitialized) {
                PresenceJobService.Helper helper = new PresenceJobService.Helper(this);
                helper.stopRotatingServiceData();
                helper.stopMonitoringTokenFreshness();
                if (this.presenceBleService.currentState() == PresenceBleService.ServiceState.RUNNING) {
                    this.presenceBleService.stop();
                }
            }
        } catch (Throwable th) {
            Log.w(TAG, "Error encountered while trying to stop GATT service", th);
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received intent w/ action ");
        outline107.append(intent.getAction());
        outline107.toString();
        PresenceForegroundServiceReceiver.registerReceivers(this);
        handleIntent(intent);
        setupForegroundServiceNotification();
        if (!this.presenceBleService.isRunning()) {
            stopAndroidForegroundService();
            return 0;
        }
        return 0;
    }
}
