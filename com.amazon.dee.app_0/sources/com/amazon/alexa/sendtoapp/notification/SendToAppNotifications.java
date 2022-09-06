package com.amazon.alexa.sendtoapp.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.applink.metrics.MobilyticsMetricsRecorder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public class SendToAppNotifications {
    static final String NOTIFICATIONS_CHANNEL_ID = "ALEXA_CHANNEL_HIGH";
    @VisibleForTesting
    static final String SEND_TO_APP_NOTIFICATION_EXTRA = "sendToAppNotification";
    @VisibleForTesting
    static final int SEND_TO_APP_NOTIFICATION_ID = 0;
    private static final String TAG = "SendToAppNotifications";
    private Context context;
    static final Long EXPIRATION_TIME = Long.valueOf(TimeUnit.DAYS.toMillis(7));
    static final Long EXPIRATION_FLEX_TIME = Long.valueOf(TimeUnit.MINUTES.toMillis(1));

    public SendToAppNotifications(Context context) {
        this.context = context;
    }

    public void createSendToAppNotification(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        Bundle bundle;
        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService("notification");
        int i = Build.VERSION.SDK_INT;
        Log.i(TAG, "Getting notification channel status");
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(NOTIFICATIONS_CHANNEL_ID);
        if (notificationChannel != null) {
            int importance = notificationChannel.getImportance();
            if (importance != 0 && importance != -1000) {
                Log.i(TAG, String.format("Notification channel id %s is turned on with importance %d", NOTIFICATIONS_CHANNEL_ID, Integer.valueOf(importance)));
                MobilyticsMetricsRecorder.recordCounter(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", SendToAppNotificationsMetricsConstants.CHANNEL_ENABLED_EVENT_NAME, 1, str5);
            } else {
                Log.i(TAG, String.format("Notification channel id %s is turned off by the user, cannot display.", NOTIFICATIONS_CHANNEL_ID));
                MobilyticsMetricsRecorder.recordCounter(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", SendToAppNotificationsMetricsConstants.CHANNEL_ENABLED_EVENT_NAME, 0, str5);
                return;
            }
        }
        Log.i(TAG, "Adding a notifications channel");
        String string = this.context.getString(R.string.send_to_app_notifications_channel_name);
        String string2 = this.context.getString(R.string.send_to_app_notifications_channel_description);
        NotificationChannel notificationChannel2 = new NotificationChannel(NOTIFICATIONS_CHANNEL_ID, string, 4);
        notificationChannel2.setDescription(string2);
        notificationManager.createNotificationChannel(notificationChannel2);
        Uri.Builder appendQueryParameter = new Uri.Builder().scheme(this.context.getString(R.string.send_to_app_notifications_activity_scheme)).appendQueryParameter("creationTime", Long.valueOf(System.currentTimeMillis()).toString()).appendQueryParameter("metricId", str5);
        Intent data = new Intent().setPackage(this.context.getPackageName()).setAction(this.context.getString(R.string.send_to_app_notifications_create_action)).setData(appendQueryParameter.appendQueryParameter("actionsString", str3).appendQueryParameter("token", str4).build());
        NotificationCompat.Builder extras = new NotificationCompat.Builder(this.context, NOTIFICATIONS_CHANNEL_ID).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.ic_alexa_white).setContentIntent(PendingIntent.getActivity(this.context, 0, data, 134217728)).setDeleteIntent(PendingIntent.getActivity(this.context, 0, new Intent().setPackage(this.context.getPackageName()).setAction(this.context.getString(R.string.send_to_app_notifications_delete_action)).setData(appendQueryParameter.build()), 134217728)).setAutoCancel(true).setTimeoutAfter(EXPIRATION_TIME.longValue()).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).setExtras(GeneratedOutlineSupport1.outline13(SEND_TO_APP_NOTIFICATION_EXTRA, true));
        String uri = data.toUri(0);
        String.format("Intent contents: %s", uri);
        notificationManager.notify(uri, 0, extras.build());
        Log.i(TAG, "App link notification created and displayed.");
        MobilyticsMetricsRecorder.recordUserInteractionEvent(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", "Displayed", "view", str5);
        int i2 = Build.VERSION.SDK_INT;
        int i3 = 0;
        for (StatusBarNotification statusBarNotification : notificationManager.getActiveNotifications()) {
            Notification notification = statusBarNotification.getNotification();
            if (notification != null && (bundle = notification.extras) != null && bundle.getBoolean(SEND_TO_APP_NOTIFICATION_EXTRA, false)) {
                i3++;
            }
        }
        Log.i(TAG, String.format("There are %d S2A notifications visible to the user", Integer.valueOf(i3)));
        MobilyticsMetricsRecorder.recordCounter(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", SendToAppNotificationsMetricsConstants.DISPLAYED_COUNT_EVENT_NAME, i3, str5);
    }
}
