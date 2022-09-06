package com.amazon.alexa.accessory.notificationpublisher.voice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationListenerConstants;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgent;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.Set;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
@Singleton
/* loaded from: classes.dex */
public class NotificationBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = NotificationBroadcastReceiver.class.getSimpleName();
    private final ExternalNotificationsCapabilityAgent capabilityAgent;
    private final Context context;
    private final Handler handler;
    private final NotificationKeyToIdsMap notificationKeyToIdsMap;
    private final NotificationProcessor notificationProcessor;

    public NotificationBroadcastReceiver(Context context, Handler handler, NotificationKeyToIdsMap notificationKeyToIdsMap, NotificationProcessor notificationProcessor, ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(handler, "Handler cannot be null");
        Preconditions.checkNotNull(externalNotificationsCapabilityAgent, "notificationKeyToIdsMap cannot be null");
        Preconditions.checkNotNull(notificationProcessor, "NotificationProcessor cannot be null");
        Preconditions.checkNotNull(externalNotificationsCapabilityAgent, "Capability agent cannot be null");
        this.context = context;
        this.handler = handler;
        this.notificationKeyToIdsMap = notificationKeyToIdsMap;
        this.notificationProcessor = notificationProcessor;
        this.capabilityAgent = externalNotificationsCapabilityAgent;
    }

    @Nullable
    private Notification getNotificationFromIntent(Intent intent) {
        if (intent.hasExtra(NotificationListenerConstants.INTENT_KEY_PARSED_NOTIFICATION)) {
            String stringExtra = intent.getStringExtra(NotificationListenerConstants.INTENT_KEY_PARSED_NOTIFICATION);
            if (Strings.isNullOrEmpty(stringExtra)) {
                return null;
            }
            try {
                return this.notificationProcessor.processParsedStatusBarNotification(new JSONObject(stringExtra));
            } catch (JSONException e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceive JSON Exception: ");
                outline107.append(e.getMessage());
                Log.e(str, outline107.toString());
                return null;
            }
        }
        return null;
    }

    @Nullable
    private String getNotificationKeyFromIntent(Intent intent) {
        if (intent.hasExtra(NotificationListenerConstants.INTENT_KEY_PARSED_NOTIFICATION)) {
            String stringExtra = intent.getStringExtra(NotificationListenerConstants.INTENT_KEY_PARSED_NOTIFICATION);
            if (Strings.isNullOrEmpty(stringExtra)) {
                return null;
            }
            try {
                return new JSONObject(stringExtra).optString(Constants.BUNDLE_KEY_NOTIFICATION_ID);
            } catch (JSONException e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceive JSON Exception: ");
                outline107.append(e.getMessage());
                Log.e(str, outline107.toString());
                return null;
            }
        }
        return null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || Strings.isNullOrEmpty(intent.getAction())) {
            return;
        }
        Notification notificationFromIntent = getNotificationFromIntent(intent);
        String notificationKeyFromIntent = getNotificationKeyFromIntent(intent);
        if (notificationFromIntent == null && !NotificationListenerConstants.INTENT_ACTION_BOUND_BY_SYSTEM.equals(intent.getAction())) {
            Log.w(TAG, "onReceive - processed notification is null");
            return;
        }
        String action = intent.getAction();
        char c = 65535;
        switch (action.hashCode()) {
            case 655222259:
                if (action.equals(NotificationListenerConstants.INTENT_ACTION_BOUND_BY_SYSTEM)) {
                    c = 3;
                    break;
                }
                break;
            case 829568049:
                if (action.equals(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED_ALL)) {
                    c = 2;
                    break;
                }
                break;
            case 1721783233:
                if (action.equals(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_LISTENER_CONNECTED)) {
                    c = 4;
                    break;
                }
                break;
            case 1874168527:
                if (action.equals(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED)) {
                    c = 1;
                    break;
                }
                break;
            case 2014499985:
                if (action.equals(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_POSTED)) {
                    c = 0;
                    break;
                }
                break;
        }
        if (c == 0) {
            Log.i(TAG, "onReceive - Notification post intent");
            this.notificationKeyToIdsMap.put(notificationKeyFromIntent, notificationFromIntent.getNotificationId());
            this.capabilityAgent.onNotificationReceived(notificationFromIntent);
        } else if (c == 1 || c == 2) {
            Log.i(TAG, "onReceive - Notification removed intent");
            Set<NotificationId> andRemove = this.notificationKeyToIdsMap.getAndRemove(notificationKeyFromIntent);
            andRemove.add(notificationFromIntent.getNotificationId());
            for (NotificationId notificationId : andRemove) {
                this.capabilityAgent.onNotificationDismissed(notificationId);
            }
        } else if (c == 3) {
            Log.i(TAG, "onReceive - Bound by system intent");
            this.capabilityAgent.uploadActiveNotifications();
        } else if (c != 4) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unrecognized intent received: ");
            outline107.append(intent.getAction());
            Log.e(str, outline107.toString());
        } else {
            Log.i(TAG, "onReceive - Listener connected intent");
            this.capabilityAgent.uploadActiveNotifications();
        }
    }

    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_POSTED);
        intentFilter.addAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED);
        intentFilter.addAction(NotificationListenerConstants.INTENT_ACTION_NOTIFICATION_REMOVED_ALL);
        intentFilter.addAction(NotificationListenerConstants.INTENT_ACTION_BOUND_BY_SYSTEM);
        this.context.registerReceiver(this, intentFilter, "com.amazon.alexa.accessory.notificationpublisher.notificationlistener", this.handler);
        Log.i(TAG, "Registered broadcast receiver");
    }

    public void unregister() {
        this.context.unregisterReceiver(this);
        Log.i(TAG, "Unregistered broadcast receiver");
    }
}
