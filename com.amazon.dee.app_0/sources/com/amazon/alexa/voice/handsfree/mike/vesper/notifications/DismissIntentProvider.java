package com.amazon.alexa.voice.handsfree.mike.vesper.notifications;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class DismissIntentProvider {
    public static final String EXTRA_NOTIFICATION_TEXT = "extra_notification_text";

    @NonNull
    public PendingIntent getDismissPendingIntent(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, NotificationDismissReceiver.class);
        intent.putExtra("extra_notification_text", str);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
