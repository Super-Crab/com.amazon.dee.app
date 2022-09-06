package com.amazon.alexa.accessory;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.accessory.internal.util.Logger;
/* loaded from: classes.dex */
public final class AccessoryService extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "ALEXA_ACCESSORY_SERVICE_CHANNEL";
    public static final String NOTIFICATION_CHANNEL_ID_HIGH = "ALEXA_ACCESSORY_SERVICE_CHANNEL_HIGH";
    private static final int ONGOING_NOTIFICATION_ID = 703697;
    private Accessories accessories;
    private AccessoryServiceConfigurationSupplier accessoryServiceConfigurationSupplier;
    private Messenger messenger;
    private AccessorySessionListener sessionListener;

    private Notification createAccessoryServiceForegroundNotification(int i) {
        NotificationCompat.Builder contentText = new NotificationCompat.Builder(this, this.accessoryServiceConfigurationSupplier.lowPriorityChannelId()).setContentTitle(this.accessoryServiceConfigurationSupplier.getNotificationContentTitle()).setVisibility(-1).setSmallIcon(this.accessoryServiceConfigurationSupplier.getNotificationSmallIcon()).setColor(this.accessoryServiceConfigurationSupplier.getNotificationColor()).setAutoCancel(true).setContentText(this.accessoryServiceConfigurationSupplier.getNotificationContentText(i));
        Bitmap notificationLargeIcon = this.accessoryServiceConfigurationSupplier.getNotificationLargeIcon();
        if (notificationLargeIcon != null) {
            contentText.setLargeIcon(notificationLargeIcon);
        }
        return contentText.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureForeground() {
        try {
            int connectedSessionsCount = getConnectedSessionsCount();
            startForeground(ONGOING_NOTIFICATION_ID, createAccessoryServiceForegroundNotification(connectedSessionsCount));
            Logger.d("Accessory service moved to foreground as there are %d active sessions", Integer.valueOf(connectedSessionsCount));
        } catch (Exception e) {
            Logger.e("Accessory service could not be moved to foreground", e);
        }
    }

    private int getConnectedSessionsCount() {
        int i = 0;
        for (AccessorySession accessorySession : this.accessories.getActiveSessions()) {
            if (accessorySession.isConnected()) {
                i++;
            }
        }
        return i;
    }

    private void removeSessionListener() {
        AccessorySessionListener accessorySessionListener = this.sessionListener;
        if (accessorySessionListener == null) {
            return;
        }
        this.accessories.removeSessionListener(accessorySessionListener);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Logger.d("Accessory service is being bound");
        this.messenger = new Messenger(new Handler(Looper.myLooper()));
        return this.messenger.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Logger.d("Accessory service is running");
        this.accessories = Accessories.getSharedInstance();
        this.accessoryServiceConfigurationSupplier = this.accessories.getAccessoryServiceConfigurationSupplier();
        ensureForeground();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Logger.d("Accessory service is stopping");
        removeSessionListener();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        Logger.e("Accessory service received on low memory");
        super.onLowMemory();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Logger.d("Accessory service started id=%d", Integer.valueOf(i2));
        this.sessionListener = new AccessorySessionListener() { // from class: com.amazon.alexa.accessory.AccessoryService.1
            @Override // com.amazon.alexa.accessory.AccessorySessionListener
            public void onAccessorySessionConnected(Accessory accessory) {
                AccessoryService.this.ensureForeground();
            }

            @Override // com.amazon.alexa.accessory.AccessorySessionListener
            public void onAccessorySessionReleased(Accessory accessory) {
                AccessoryService.this.ensureForeground();
            }
        };
        this.accessories.addSessionListener(this.sessionListener);
        ensureForeground();
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        Logger.d("Accessory service received task removed for intent=%s", intent);
        super.onTaskRemoved(intent);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        Logger.d("Accessory service received trim memory level=%d", Integer.valueOf(i));
        super.onTrimMemory(i);
    }
}
