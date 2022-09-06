package com.amazon.alexa.accessory.notificationpublisher.notificationlistener;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyServiceHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.ComponentEnabler;
import com.amazon.alexa.accessory.notificationpublisher.voice.AmpdVoiceModule;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationCapabilityAgentService;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class PhoneNotificationListenerService extends NotificationListenerService {
    private static final String TAG = PhoneNotificationListenerService.class.getSimpleName();
    private AmpdVoiceModule ampdVoiceModule;
    private boolean isAMPDDevice;
    private StatusBarNotificationParser sbnParser;
    private boolean listenerConnected = false;
    private boolean applicationRunning = false;
    private boolean zionAccessoryConnected = false;
    private boolean isNotificationCapabilityAgentEnabled = false;
    private final INotificationListenerServiceBridge.Stub serviceBridge = new INotificationListenerServiceBridge.Stub() { // from class: com.amazon.alexa.accessory.notificationpublisher.notificationlistener.PhoneNotificationListenerService.1
        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean doesNotificationExistInStatusBar(String str) {
            GeneratedOutlineSupport1.outline163("doesNotificationExistInStatusBar - ", str, PhoneNotificationListenerService.TAG);
            return PhoneNotificationListenerService.this.doesNotificationExistInStatusBar(str);
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean doesNotificationSupportReply(String str) {
            String str2 = PhoneNotificationListenerService.TAG;
            Log.i(str2, "doesNotificationSupportReply - " + str);
            return ReplyServiceHelper.doesNotificationSupportReply(str);
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public List<StatusBarNotification> getActiveNotificationsWithKeys(String[] strArr) {
            Log.i(PhoneNotificationListenerService.TAG, "getActiveNotificationsWithKeys");
            if (PhoneNotificationListenerService.this.listenerConnected) {
                return new ArrayList(Arrays.asList(PhoneNotificationListenerService.this.getActiveNotifications(strArr)));
            }
            return null;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public int getInterruptionFilter() throws RemoteException {
            int currentInterruptionFilter = PhoneNotificationListenerService.this.getCurrentInterruptionFilter();
            String str = PhoneNotificationListenerService.TAG;
            Log.i(str, "getInterruptionFilter - " + currentInterruptionFilter);
            return currentInterruptionFilter;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean isListenerConnected() throws RemoteException {
            String str = PhoneNotificationListenerService.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isListenerConnected - ");
            outline107.append(PhoneNotificationListenerService.this.listenerConnected);
            Log.i(str, outline107.toString());
            return PhoneNotificationListenerService.this.listenerConnected;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean isReplyNotification(String str, String str2, String str3) {
            String str4 = PhoneNotificationListenerService.TAG;
            Log.i(str4, "isReplyNotification - " + str);
            return ReplyServiceHelper.isReplyNotification(str, str2, str3);
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public void onDeviceConnectionChanged(boolean z) {
            GeneratedOutlineSupport1.outline173("onDeviceConnectionChanged - ", z, PhoneNotificationListenerService.TAG);
            PhoneNotificationListenerService.this.zionAccessoryConnected = z;
            PhoneNotificationListenerService.this.sbnParser.updateLastZionConnectedTimestamp(System.currentTimeMillis());
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public boolean sendReply(String str, String str2) {
            GeneratedOutlineSupport1.outline163("sendReply - ", str, PhoneNotificationListenerService.TAG);
            return ReplyServiceHelper.sendReply(PhoneNotificationListenerService.this, str, str2);
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge
        public void setApplicationRunning(boolean z) throws RemoteException {
            GeneratedOutlineSupport1.outline173("setApplicationRunning - ", z, PhoneNotificationListenerService.TAG);
            PhoneNotificationListenerService.this.applicationRunning = z;
        }
    };

    private void broadcastIntent(String str, StatusBarNotification statusBarNotification) {
        Log.i(TAG, String.format("broadcastIntent - eventName = %s, SBN Package = %s", str, statusBarNotification.getPackageName()));
        try {
            Intent createNotificationIntent = createNotificationIntent(str, statusBarNotification);
            if (createNotificationIntent == null) {
                return;
            }
            String str2 = TAG;
            Log.i(str2, "broadcastIntent - Sending broadcast for SBN Package = " + statusBarNotification.getPackageName());
            sendBroadcast(createNotificationIntent, "com.amazon.alexa.accessory.notificationpublisher.notificationlistener");
        } catch (Exception e) {
            String str3 = TAG;
            Log.w(str3, "broadcastIntent - Exception: " + e);
        }
    }

    private void broadcastListenerConnectedIntent() {
        Log.i(TAG, "broadcastListenerConnectedIntent");
        try {
            Intent intent = new Intent();
            intent.setAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_LISTENER_CONNECTED);
            sendBroadcast(intent, "com.amazon.alexa.accessory.notificationpublisher.notificationlistener");
        } catch (Exception e) {
            String str = TAG;
            Log.w(str, "broadcastListenerConnectedIntent - Exception: " + e);
        }
    }

    private void broadcastServiceBoundIntent() {
        Log.i(TAG, "broadcastServiceBoundIntent");
        try {
            Intent intent = new Intent();
            intent.setAction(NotificationListenerConstants.INTENT_ACTION_BOUND_BY_SYSTEM);
            sendBroadcast(intent, "com.amazon.alexa.accessory.notificationpublisher.notificationlistener");
        } catch (Exception e) {
            String str = TAG;
            Log.w(str, "broadcastServiceBoundIntent - Exception: " + e);
        }
    }

    private void uploadNotificationDismissed(StatusBarNotification statusBarNotification) {
        this.isNotificationCapabilityAgentEnabled = ComponentEnabler.checkIfServiceIsEnabled(this, ExternalNotificationCapabilityAgentService.class);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isNotificationCapabilityAgentEnabled: ");
        outline107.append(this.isNotificationCapabilityAgentEnabled);
        Log.i(str, outline107.toString());
        if (this.isNotificationCapabilityAgentEnabled) {
            if (this.ampdVoiceModule == null) {
                this.ampdVoiceModule = new AmpdVoiceModule(this);
                this.ampdVoiceModule.initialize();
            }
            this.ampdVoiceModule.onNotificationRemoved(statusBarNotification);
            return;
        }
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("uploadNotificationDismissed: not updating dismissed notification due to isNotificationCapabilityAgentEnabled: ");
        outline1072.append(this.isNotificationCapabilityAgentEnabled);
        Log.i(str2, outline1072.toString());
    }

    private void uploadNotificationPosted(StatusBarNotification statusBarNotification) {
        this.isNotificationCapabilityAgentEnabled = ComponentEnabler.checkIfServiceIsEnabled(this, ExternalNotificationCapabilityAgentService.class);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isNotificationCapabilityAgentEnabled: ");
        outline107.append(this.isNotificationCapabilityAgentEnabled);
        Log.i(str, outline107.toString());
        if (this.isNotificationCapabilityAgentEnabled) {
            AmpdVoiceModule ampdVoiceModule = this.ampdVoiceModule;
            if (ampdVoiceModule == null) {
                this.ampdVoiceModule = new AmpdVoiceModule(this);
                this.ampdVoiceModule.initialize();
                this.ampdVoiceModule.uploadActiveNotifications(getActiveNotifications());
                return;
            }
            ampdVoiceModule.onNotificationPosted(statusBarNotification);
            return;
        }
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("uploadNotificationPosted: not uploading notification due to isNotificationCapabilityAgentEnabled: ");
        outline1072.append(this.isNotificationCapabilityAgentEnabled);
        Log.i(str2, outline1072.toString());
    }

    @VisibleForTesting
    Intent createNotificationIntent(String str, StatusBarNotification statusBarNotification) {
        String str2 = "createNotificationIntent - eventName = " + str;
        if (!Strings.isNullOrEmpty(str) && statusBarNotification != null && (str.equals(NotificationListenerConstants.NOTIFICATION_POST) || str.equals(NotificationListenerConstants.NOTIFICATION_REMOVED) || str.equals(NotificationListenerConstants.NOTIFICATION_REMOVED_ALL))) {
            JSONObject parseNotification = this.sbnParser.parseNotification(str, statusBarNotification);
            if (parseNotification == null) {
                String str3 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createNotificationIntent - Parsed notification is null - SBN Package: ");
                outline107.append(statusBarNotification.getPackageName());
                Log.i(str3, outline107.toString());
                return null;
            }
            Intent intent = new Intent();
            if (str.equals(NotificationListenerConstants.NOTIFICATION_POST)) {
                intent.setAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_POSTED);
            } else if (str.equals(NotificationListenerConstants.NOTIFICATION_REMOVED)) {
                intent.setAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED);
            } else {
                intent.setAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED_ALL);
            }
            intent.putExtra(NotificationListenerConstants.INTENT_KEY_PARSED_NOTIFICATION, parseNotification.toString());
            return intent;
        }
        Log.w(TAG, "createNotificationIntent - Invalid inputs - return");
        return null;
    }

    @VisibleForTesting
    boolean doesNotificationExistInStatusBar(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return false;
        }
        GeneratedOutlineSupport1.outline163("doesNotificationExistInStatusBar - ", str, TAG);
        StatusBarNotification[] activeNotifications = getActiveNotifications(new String[]{str});
        return activeNotifications != null && activeNotifications.length > 0;
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent != null && !Strings.isNullOrEmpty(intent.getAction()) && intent.getAction().equals(NotificationListenerConstants.INTENT_ACTION_BIND_TO_APPLICATION)) {
            Log.i(TAG, "onBind - Application is binding to Service");
            return this.serviceBridge;
        }
        Log.i(TAG, "onBind - System service is binding to Service");
        IBinder onBind = super.onBind(intent);
        if (onBind != null) {
            broadcastServiceBoundIntent();
        }
        return onBind;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.isAMPDDevice = AMPDInformationProvider.getInstance(this).isAMPDDevice();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCreate, isAMPDDevice: ");
        outline107.append(this.isAMPDDevice);
        Log.i(str, outline107.toString());
        this.sbnParser = new StatusBarNotificationParser(this);
        ReplyServiceHelper.init(this);
    }

    @Override // android.service.notification.NotificationListenerService
    public void onInterruptionFilterChanged(int i) {
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("onInterruptionFilterChanged - New filter = ", i, " Application Running: ");
        outline109.append(this.applicationRunning);
        outline109.toString();
        Intent intent = new Intent(NotificationListenerConstants.INTENT_ACTION_INTERRUPTION_FILTER_CHANGED);
        intent.putExtra(NotificationListenerConstants.INTENT_KEY_INTERRUPTION_FILTER, i);
        sendBroadcast(intent, "com.amazon.alexa.accessory.notificationpublisher.notificationlistener");
        super.onInterruptionFilterChanged(i);
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerConnected() {
        this.listenerConnected = true;
        broadcastListenerConnectedIntent();
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerDisconnected() {
        this.listenerConnected = false;
    }

    @Override // android.service.notification.NotificationListenerService
    public synchronized void onNotificationPosted(StatusBarNotification statusBarNotification) {
        if (statusBarNotification != null) {
            if (!Strings.isNullOrEmpty(statusBarNotification.getKey())) {
                Log.i(TAG, String.format(Locale.US, "onNotificationPosted. Key: %s, PostTime: %d, Application Running: %s, zionAccessoryConnected: %s, ampdDevice: %s", statusBarNotification.getKey(), Long.valueOf(statusBarNotification.getPostTime()), Boolean.valueOf(this.applicationRunning), Boolean.valueOf(this.zionAccessoryConnected), Boolean.valueOf(this.isAMPDDevice)));
                ReplyServiceHelper.updateReplyActionMap(statusBarNotification);
                if (this.isAMPDDevice && !this.applicationRunning) {
                    uploadNotificationPosted(statusBarNotification);
                }
                broadcastIntent(NotificationListenerConstants.NOTIFICATION_POST, statusBarNotification);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006f A[Catch: all -> 0x0080, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000d, B:17:0x005c, B:19:0x0065, B:21:0x0069, B:23:0x006f, B:25:0x0078, B:27:0x007c), top: B:35:0x0003 }] */
    @Override // android.service.notification.NotificationListenerService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void onNotificationRemoved(android.service.notification.StatusBarNotification r9, android.service.notification.NotificationListenerService.RankingMap r10, int r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            if (r9 == 0) goto L83
            java.lang.String r10 = r9.getKey()     // Catch: java.lang.Throwable -> L80
            boolean r10 = com.google.common.base.Strings.isNullOrEmpty(r10)     // Catch: java.lang.Throwable -> L80
            if (r10 != 0) goto L83
            java.lang.String r10 = com.amazon.alexa.accessory.notificationpublisher.notificationlistener.PhoneNotificationListenerService.TAG     // Catch: java.lang.Throwable -> L80
            java.util.Locale r0 = java.util.Locale.US     // Catch: java.lang.Throwable -> L80
            java.lang.String r1 = "onNotificationRemoved. Key: %s, PostTime: %d, Application Running: %s, zionAccessoryConnected: %s, Reason: %d"
            r2 = 5
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L80
            java.lang.String r3 = r9.getKey()     // Catch: java.lang.Throwable -> L80
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.Throwable -> L80
            long r5 = r9.getPostTime()     // Catch: java.lang.Throwable -> L80
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Throwable -> L80
            r5 = 1
            r2[r5] = r3     // Catch: java.lang.Throwable -> L80
            r3 = 2
            boolean r6 = r8.applicationRunning     // Catch: java.lang.Throwable -> L80
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Throwable -> L80
            r2[r3] = r6     // Catch: java.lang.Throwable -> L80
            boolean r3 = r8.zionAccessoryConnected     // Catch: java.lang.Throwable -> L80
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.Throwable -> L80
            r6 = 3
            r2[r6] = r3     // Catch: java.lang.Throwable -> L80
            r3 = 4
            java.lang.Integer r7 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Throwable -> L80
            r2[r3] = r7     // Catch: java.lang.Throwable -> L80
            java.lang.String r0 = java.lang.String.format(r0, r1, r2)     // Catch: java.lang.Throwable -> L80
            android.util.Log.i(r10, r0)     // Catch: java.lang.Throwable -> L80
            r10 = 8
            if (r11 == r10) goto L54
            r10 = 9
            if (r11 != r10) goto L52
            goto L54
        L52:
            r10 = r4
            goto L55
        L54:
            r10 = r5
        L55:
            if (r11 != r6) goto L58
            r4 = r5
        L58:
            if (r10 != 0) goto L6d
            if (r4 != 0) goto L6d
            java.lang.String r10 = "NOTIFICATION_REMOVED"
            r8.broadcastIntent(r10, r9)     // Catch: java.lang.Throwable -> L80
            boolean r10 = r8.isAMPDDevice     // Catch: java.lang.Throwable -> L80
            if (r10 == 0) goto L83
            boolean r10 = r8.applicationRunning     // Catch: java.lang.Throwable -> L80
            if (r10 != 0) goto L83
            r8.uploadNotificationDismissed(r9)     // Catch: java.lang.Throwable -> L80
            goto L83
        L6d:
            if (r4 == 0) goto L83
            java.lang.String r10 = "NOTIFICATION_REMOVED_ALL"
            r8.broadcastIntent(r10, r9)     // Catch: java.lang.Throwable -> L80
            boolean r10 = r8.isAMPDDevice     // Catch: java.lang.Throwable -> L80
            if (r10 == 0) goto L83
            boolean r10 = r8.applicationRunning     // Catch: java.lang.Throwable -> L80
            if (r10 != 0) goto L83
            r8.uploadNotificationDismissed(r9)     // Catch: java.lang.Throwable -> L80
            goto L83
        L80:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        L83:
            monitor-exit(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.notificationlistener.PhoneNotificationListenerService.onNotificationRemoved(android.service.notification.StatusBarNotification, android.service.notification.NotificationListenerService$RankingMap, int):void");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String str = "onStartCommand " + intent + " " + i + " " + i2;
        return 1;
    }

    @Override // android.service.notification.NotificationListenerService
    public synchronized void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        if (statusBarNotification != null) {
            if (!Strings.isNullOrEmpty(statusBarNotification.getKey())) {
                Log.i(TAG, String.format(Locale.US, "onNotificationRemoved. Key: %s, PostTime: %d, Application Running: %s, zionAccessoryConnected: %s", statusBarNotification.getKey(), Long.valueOf(statusBarNotification.getPostTime()), Boolean.valueOf(this.applicationRunning), Boolean.valueOf(this.zionAccessoryConnected)));
                broadcastIntent(NotificationListenerConstants.NOTIFICATION_REMOVED, statusBarNotification);
            }
        }
    }
}
