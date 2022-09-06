package com.amazon.alexa.location.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.amazon.alexa.location.R;
/* loaded from: classes9.dex */
public final class NotificationUtil {
    public static final String CHANNEL_ID = "GeofenceTriggerChannelId";
    public static final String CHANNEL_NAME = "Location Tasks";

    private NotificationUtil() {
    }

    public static Notification getNotificationForBackgroundTasks(Context context, String str) {
        int i = Build.VERSION.SDK_INT;
        ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 0));
        return new Notification.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.app_icon).setContentTitle("Alexa Location").setContentText(str).build();
    }
}
