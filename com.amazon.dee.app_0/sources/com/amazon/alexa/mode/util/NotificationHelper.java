package com.amazon.alexa.mode.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.alexa.mode.R;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes9.dex */
public class NotificationHelper {
    private static final String DRIVE_MODE_NOTIFICATION_INTENT = "com.amazon.alexa.mode.START_DRIVE_MODE";
    private static final String DRIVE_MODE_NOTIFICATION_SETTINGS_ROUTE_ALIAS = "drive-mode/notification";
    public static final int MAX_PRIMARY_NOTIFICATION_CONSECUTIVE_OCCURRENCE = 3;
    private static final String NOTIFICATION_CHANNEL_ID = "ALEXA_MODE_CHANNEL";
    private static final String NOTIFICATION_VIEW_INTENT_PREFIX = "http://alexa.amazon.com/spa/index.html#";
    private static final int SECONDARY_NOTIFICATION_ID_BASE = 100;
    private Context context;
    private Lazy<DriveModeMetrics> driveModeMetrics;
    private AtomicInteger primaryNotificationId = new AtomicInteger();
    private AtomicInteger secondarynotificationId = new AtomicInteger();

    public NotificationHelper(Context context, Lazy<DriveModeMetrics> lazy) {
        this.context = context;
        this.driveModeMetrics = lazy;
        int i = Build.VERSION.SDK_INT;
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        int i = Build.VERSION.SDK_INT;
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, this.context.getString(R.string.alexa_mode_notification_channel_name), 4);
        notificationChannel.setDescription(this.context.getString(R.string.alexa_mode_notification_channel_description));
        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void cancelNotification() {
        NotificationManagerCompat.from(this.context).cancel(this.primaryNotificationId.get());
        NotificationManagerCompat.from(this.context).cancel(this.secondarynotificationId.get() + 100);
        this.driveModeMetrics.mo358get().logNotificationRemoved();
    }

    public boolean sendNotification(@NonNull String str, @NonNull String str2) {
        NotificationCompat.Builder style = new NotificationCompat.Builder(this.context).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.ic_alexa_white).setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.app_icon)).setColor(this.context.getResources().getColor(R.color.notification_color)).setAutoCancel(true).setPriority(4).setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
        int i = Build.VERSION.SDK_INT;
        style.setChannelId(NOTIFICATION_CHANNEL_ID);
        cancelNotification();
        Intent intent = new Intent("com.amazon.alexa.mode.START_DRIVE_MODE");
        intent.setComponent(ModeUtil.componentNameFromMode());
        intent.setFlags(603979776);
        style.setContentIntent(PendingIntent.getActivity(this.context, 0, intent, 134217728));
        NotificationManagerCompat from = NotificationManagerCompat.from(this.context);
        if (from.areNotificationsEnabled()) {
            from.notify(this.primaryNotificationId.incrementAndGet(), style.build());
            this.driveModeMetrics.mo358get().logNotificationShown();
        } else {
            this.driveModeMetrics.mo358get().logNotificationNotShown(DriveModeMetrics.Reason.PLATFORMPERMISSIONOFF);
        }
        return true;
    }

    public boolean sendSecondaryNotification() {
        Resources resources = this.context.getResources();
        String string = resources.getString(R.string.notification_not_driving_title);
        String string2 = resources.getString(R.string.notification_turn_off_from_settings);
        NotificationCompat.Builder style = new NotificationCompat.Builder(this.context).setContentTitle(string).setContentText(string2).setSmallIcon(R.drawable.ic_alexa_white).setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.app_icon)).setColor(resources.getColor(R.color.notification_color)).setAutoCancel(true).setPriority(4).setStyle(new NotificationCompat.BigTextStyle().bigText(string2));
        int i = Build.VERSION.SDK_INT;
        style.setChannelId(NOTIFICATION_CHANNEL_ID);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://alexa.amazon.com/spa/index.html#drive-mode/notification"));
        intent.setComponent(ModeUtil.componentNameFromMode());
        intent.setFlags(603979776);
        style.setContentIntent(PendingIntent.getActivity(this.context, 0, intent, 134217728));
        NotificationManagerCompat from = NotificationManagerCompat.from(this.context);
        if (from.areNotificationsEnabled()) {
            from.notify(this.secondarynotificationId.incrementAndGet() + 100, style.build());
            this.driveModeMetrics.mo358get().logSecondaryNotificationShown();
        } else {
            this.driveModeMetrics.mo358get().logSecondaryNotificationNotShown(DriveModeMetrics.Reason.PLATFORMPERMISSIONOFF);
        }
        return true;
    }
}
