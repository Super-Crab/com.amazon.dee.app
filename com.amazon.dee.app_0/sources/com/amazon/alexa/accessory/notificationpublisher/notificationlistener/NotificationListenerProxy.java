package com.amazon.alexa.accessory.notificationpublisher.notificationlistener;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyClientHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class NotificationListenerProxy {
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String TAG = "NotificationListenerProxy";
    private static NotificationListenerProxy proxyInstance;
    private Handler handler;
    private NotificationServiceCommunicator serviceCommunicator;
    private final Runnable bindServiceRunnable = new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.notificationlistener.-$$Lambda$NotificationListenerProxy$UsNWw3T8v4vP4vnTZ3hkNk_rNCE
        @Override // java.lang.Runnable
        public final void run() {
            NotificationListenerProxy.this.lambda$new$0$NotificationListenerProxy();
        }
    };
    private NotificationBroadcastReceiver notificationReceiver = new NotificationBroadcastReceiver();

    /* loaded from: classes.dex */
    public final class NotificationBroadcastReceiver extends BroadcastReceiver {
        public NotificationBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(NotificationListenerProxy.TAG, "NotificationBroadcastReceiver.onReceive");
            NotificationListenerProxy.this.proceedReceivedIntent(context, intent);
        }
    }

    private NotificationListenerProxy() {
        HandlerThread handlerThread = new HandlerThread("Zion_NLS_BindThread");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper());
    }

    private void bindAppToService() {
        Log.d(TAG, "bindAppToService");
        this.handler.post(this.bindServiceRunnable);
    }

    public static void create() {
        boolean z;
        Log.i(TAG, "create");
        if (proxyInstance != null) {
            Log.i(TAG, "create - Already created, check if service is still bound");
            try {
                z = DependencyProvider.getNotificationServiceCommunicator().safeCallIsListenerConnected();
                String str = TAG;
                Log.i(str, "create - serviceBound = " + z);
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline157("create - Exception when checking for service bound: ", e, TAG);
                z = false;
            }
            if (z) {
                return;
            }
            Log.i(TAG, "create - Service is not bound, recreate binding");
            recreate();
            return;
        }
        Log.d(TAG, "create - Registering Receivers");
        proxyInstance = new NotificationListenerProxy();
        proxyInstance.bindAppToService();
        Context context = DependencyProvider.getContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_POSTED);
        intentFilter.addAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED);
        intentFilter.addAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED_ALL);
        intentFilter.addAction(NotificationListenerConstants.INTENT_ACTION_INTERRUPTION_FILTER_CHANGED);
        NotificationListenerProxy notificationListenerProxy = proxyInstance;
        context.registerReceiver(notificationListenerProxy.notificationReceiver, intentFilter, "com.amazon.alexa.accessory.notificationpublisher.notificationlistener", notificationListenerProxy.handler);
    }

    static void destroy() {
        Log.i(TAG, "destroy");
        try {
        } catch (Exception e) {
            Log.w(TAG, "destroy failed", e);
        }
        if (proxyInstance == null) {
            Log.d(TAG, "destroy - No instance created, early return");
            return;
        }
        proxyInstance.unbindAppFromService();
        if (proxyInstance.notificationReceiver != null) {
            DependencyProvider.getContext().unregisterReceiver(proxyInstance.notificationReceiver);
        }
        proxyInstance.notificationReceiver = null;
        proxyInstance = null;
    }

    private int getCurrentInterruptionFilter() {
        Log.d(TAG, "getCurrentInterruptionFilter");
        NotificationServiceCommunicator notificationServiceCommunicator = this.serviceCommunicator;
        if (notificationServiceCommunicator != null) {
            return notificationServiceCommunicator.safeCallGetInterruptionFilter();
        }
        return 3;
    }

    @VisibleForTesting
    static NotificationListenerProxy getProxyInstance() {
        return proxyInstance;
    }

    public static boolean hasNotificationListenerPermissions(Context context) throws Exception {
        ComponentName componentName = new ComponentName(context, PhoneNotificationListenerService.class);
        String string = Settings.Secure.getString(context.getContentResolver(), ENABLED_NOTIFICATION_LISTENERS);
        return string != null && string.contains(componentName.flattenToString());
    }

    private boolean isNotificationSameAsSentReply(JSONObject jSONObject) {
        return ReplyClientHelper.isReplyNotification(jSONObject.optString(Constants.BUNDLE_KEY_NOTIFICATION_ID), jSONObject.optString("title"), jSONObject.optString("text"));
    }

    private void publishNotificationEvent(String str, String str2) {
        if (!FeatureToggleModule.getInstance().hasConnectedEnabledDevices()) {
            String str3 = TAG;
            Log.d(str3, "publishNotificationEvent - " + str + " ignored as no supported accessory is connected");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (!str.equals(NotificationListenerConstants.NOTIFICATION_REMOVED) && !str.equals(NotificationListenerConstants.NOTIFICATION_REMOVED_ALL)) {
                boolean z = DistractionModeProvider.getCurrentDistractionMode() == 4;
                String str4 = TAG;
                Log.d(str4, "publishNotificationEvent - Event is a notification post, No distraction/Accessory DND enabled = " + z);
                if (z) {
                    return;
                }
                validateAndBroadcastNotification(str, jSONObject);
                return;
            }
            Log.d(TAG, "publishNotificationEvent - Even is notification dismissed, process immediately");
            validateAndBroadcastNotification(str, jSONObject);
        } catch (Exception e) {
            String str5 = TAG;
            Log.e(str5, "publishNotificationEvent - Exception: " + e);
        }
    }

    static void recreate() {
        Log.i(TAG, "recreate - destroy and create");
        try {
            destroy();
            create();
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "recreate - Exception: " + e);
        }
    }

    private void unbindAppFromService() {
        Log.d(TAG, "unbindAppFromService");
        if (this.serviceCommunicator != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unbindAppFromService - Result of set application running is: ");
            outline107.append(this.serviceCommunicator.safeCallSetApplicationRunning(false));
            Log.i(str, outline107.toString());
            DependencyProvider.getContext().unbindService(this.serviceCommunicator.getServiceConnection());
            this.serviceCommunicator = null;
            DependencyProvider.setNotificationServiceCommunicator(null);
        }
    }

    @VisibleForTesting
    void broadcastNotification(String str, @NonNull JSONObject jSONObject) {
        try {
            String str2 = TAG;
            Log.i(str2, "broadcastNotification Event: " + str + " uuid: " + jSONObject.optString("uuid"));
            String jSONObject2 = jSONObject.toString();
            String str3 = TAG;
            Log.d(str3, "broadcastNotification - json: " + jSONObject2);
            Intent intent = new Intent(str);
            intent.putExtra(NotificationListenerConstants.INTENT_KEY_NOTIFICATION_JSON_EXTRA, jSONObject2);
            LocalBroadcastManager.getInstance(DependencyProvider.getContext()).sendBroadcast(intent);
        } catch (Exception e) {
            Log.w(TAG, "broadcastNotification - Exception: ", e);
        }
    }

    public /* synthetic */ void lambda$new$0$NotificationListenerProxy() {
        startBindToService(new Intent(NotificationListenerConstants.INTENT_ACTION_BIND_TO_APPLICATION));
    }

    @VisibleForTesting
    void onInterruptionFilterChanged(int i) {
        Log.d(TAG, "onInterruptionFilterChanged");
        StatusEventManager statusEventManager = StatusEventManager.getInstance();
        boolean z = true;
        if (i == 1) {
            z = false;
        }
        statusEventManager.onPhoneDNDChanged(z);
    }

    @VisibleForTesting
    void proceedReceivedIntent(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        if (intent == null || Strings.isNullOrEmpty(intent.getAction()) || intent.getExtras() == null) {
            return;
        }
        char c = 65535;
        if (intent.hasExtra(NotificationListenerConstants.INTENT_KEY_PARSED_NOTIFICATION)) {
            String stringExtra = intent.getStringExtra(NotificationListenerConstants.INTENT_KEY_PARSED_NOTIFICATION);
            if (!Strings.isNullOrEmpty(stringExtra)) {
                String action = intent.getAction();
                int hashCode = action.hashCode();
                if (hashCode != 829568049) {
                    if (hashCode != 1874168527) {
                        if (hashCode == 2014499985 && action.equals(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_POSTED)) {
                            c = 0;
                        }
                    } else if (action.equals(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED)) {
                        c = 1;
                    }
                } else if (action.equals(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED_ALL)) {
                    c = 2;
                }
                if (c == 0) {
                    Log.i(TAG, "onReceive - Notification post intent");
                    proxyInstance.publishNotificationEvent(NotificationListenerConstants.NOTIFICATION_POST, stringExtra);
                    return;
                } else if (c == 1) {
                    Log.i(TAG, "onReceive - Notification removed intent");
                    proxyInstance.publishNotificationEvent(NotificationListenerConstants.NOTIFICATION_REMOVED, stringExtra);
                    return;
                } else if (c != 2) {
                    return;
                } else {
                    Log.i(TAG, "onReceive - Notification removed all intent");
                    proxyInstance.publishNotificationEvent(NotificationListenerConstants.NOTIFICATION_REMOVED_ALL, stringExtra);
                    return;
                }
            }
            Log.e(TAG, "onReceive - Intent does not have valid parsed SBN");
        } else if (!intent.getAction().equals(NotificationListenerConstants.INTENT_ACTION_INTERRUPTION_FILTER_CHANGED) || !intent.hasExtra(NotificationListenerConstants.INTENT_KEY_INTERRUPTION_FILTER)) {
        } else {
            Log.d(TAG, "onReceive - Interruption filter changed intent");
            proxyInstance.onInterruptionFilterChanged(intent.getIntExtra(NotificationListenerConstants.INTENT_KEY_INTERRUPTION_FILTER, -1));
        }
    }

    @VisibleForTesting
    void startBindToService(Intent intent) {
        try {
            Context context = DependencyProvider.getContext();
            intent.setClassName(context, PhoneNotificationListenerService.class.getName());
            NotificationServiceConnection notificationServiceConnection = new NotificationServiceConnection();
            boolean bindService = context.bindService(intent, notificationServiceConnection, 1);
            String str = TAG;
            Log.i(str, "startBindToService - bindResult = " + bindService);
            if (!bindService) {
                Log.e(TAG, "startBindToService - FATAL ERROR - Service Connection Failed - Focus Filer will not work");
                this.serviceCommunicator = null;
                DependencyProvider.setNotificationServiceCommunicator(null);
            } else {
                this.serviceCommunicator = notificationServiceConnection;
                DependencyProvider.setNotificationServiceCommunicator(this.serviceCommunicator);
                String str2 = TAG;
                Log.i(str2, "startBindToService - Result of set application running is: " + this.serviceCommunicator.safeCallSetApplicationRunning(true));
            }
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "startBindToService - Exception: " + e);
        }
    }

    @VisibleForTesting
    void validateAndBroadcastNotification(String str, JSONObject jSONObject) {
        GeneratedOutlineSupport1.outline165("validateAndBroadcastNotification - ", str, TAG);
        if (jSONObject != null && !Strings.isNullOrEmpty(jSONObject.optString("uuid"))) {
            if (isNotificationSameAsSentReply(jSONObject)) {
                Log.d(TAG, "validateAndBroadcastNotification - Notification is same as sent reply, ignore");
                return;
            }
            int currentInterruptionFilter = getCurrentInterruptionFilter();
            if (currentInterruptionFilter != 1) {
                String str2 = TAG;
                Log.i(str2, "validateAndBroadcastNotification - ignored - interruptionFilter: " + currentInterruptionFilter);
                return;
            }
            broadcastNotification(str, jSONObject);
            return;
        }
        Log.i(TAG, "validateAndBroadcastNotification - Invalid notification json");
    }
}
