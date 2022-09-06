package com.amazon.alexa.externalnotifications.capability;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import androidx.core.util.Preconditions;
import com.amazon.alexa.externalnotifications.capability.directive.ExternalNotificationsDirectiveListener;
import com.amazon.alexa.externalnotifications.capability.directive.SetNotificationsReadDirectivePayload;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
/* loaded from: classes7.dex */
public class ExternalNotificationsDirectiveReceiver extends BroadcastReceiver {
    public static final String INTENT_ACTION_SET_READ_DIRECTIVE_RECEIVED = "intent_external_notifications_set_read_directive_received";
    public static final String INTENT_KEY_SET_READ_PAYLOAD_EXTRA = "intent_key_external_notifications_set_read_payload_extra";
    public static final String SECURE_PERMISSION = "com.amazon.alexa.externalnotifications.directivelistener";
    private static final String TAG = ExternalNotificationsDirectiveReceiver.class.getSimpleName();
    private final Context context;
    private final ExternalNotificationsDirectiveListener directiveListener;
    private final Gson gson;
    private final Handler handler;

    public ExternalNotificationsDirectiveReceiver(Gson gson, Context context, Handler handler, ExternalNotificationsDirectiveListener externalNotificationsDirectiveListener) {
        Preconditions.checkNotNull(gson, "Gson cannot be null");
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(handler, "Handler cannot be null");
        Preconditions.checkNotNull(externalNotificationsDirectiveListener, "Directive listener cannot be null");
        this.gson = gson;
        this.context = context;
        this.handler = handler;
        this.directiveListener = externalNotificationsDirectiveListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || Strings.isNullOrEmpty(intent.getAction()) || intent.getExtras() == null) {
            return;
        }
        if (INTENT_ACTION_SET_READ_DIRECTIVE_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG, "onReceive - SetRead directive intent");
            try {
                SetNotificationsReadDirectivePayload setNotificationsReadDirectivePayload = (SetNotificationsReadDirectivePayload) this.gson.fromJson(intent.getStringExtra(INTENT_KEY_SET_READ_PAYLOAD_EXTRA), (Class<Object>) SetNotificationsReadDirectivePayload.class);
                String str = TAG;
                Log.i(str, "Notifying listener of SetRead directive for notificationIds: " + setNotificationsReadDirectivePayload.getNotificationIds());
                this.directiveListener.onSetReadDirective(setNotificationsReadDirectivePayload.getNotificationIds());
                return;
            } catch (JsonSyntaxException e) {
                String str2 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceive JSON Exception: ");
                outline107.append(e.getMessage());
                Log.e(str2, outline107.toString());
                return;
            }
        }
        String str3 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unrecognized broadcast received with action: ");
        outline1072.append(intent.getAction());
        Log.w(str3, outline1072.toString());
    }

    public void register() {
        this.context.registerReceiver(this, GeneratedOutlineSupport1.outline10(INTENT_ACTION_SET_READ_DIRECTIVE_RECEIVED), "com.amazon.alexa.externalnotifications.directivelistener", this.handler);
        Log.i(TAG, "Registered receiver");
    }

    public void unregister() {
        this.context.unregisterReceiver(this);
        Log.i(TAG, "Unregistered receiver");
    }
}
