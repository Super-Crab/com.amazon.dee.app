package com.amazon.alexa.smarthomecameras.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.smarthomecameras.R;
/* loaded from: classes10.dex */
public class NotificationHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public Notification build(Context context, String str, String str2) {
        int i = Build.VERSION.SDK_INT;
        NotificationChannel notificationChannel = new NotificationChannel(str, str2, 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(notificationChannel);
        return new NotificationCompat.Builder(context, str).setOngoing(true).setSmallIcon(R.drawable.ic_play_button).setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build();
    }
}
