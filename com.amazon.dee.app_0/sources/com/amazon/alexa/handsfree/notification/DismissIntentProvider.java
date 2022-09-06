package com.amazon.alexa.handsfree.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public class DismissIntentProvider {
    public static final String EXTRA_NOTIFICATION_OPERATION = "extra_notification_operation";
    public static final String EXTRA_NOTIFICATION_TEXT = "extra_notification_text";

    @NonNull
    public PendingIntent getDismissPendingIntent(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull String str) {
        Intent intent = new Intent(context, NotificationDismissReceiver.class);
        intent.putExtra(EXTRA_NOTIFICATION_OPERATION, notificationType.toString());
        intent.putExtra("extra_notification_text", str);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
