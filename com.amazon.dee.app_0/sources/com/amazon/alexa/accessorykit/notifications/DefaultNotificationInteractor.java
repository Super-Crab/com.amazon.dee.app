package com.amazon.alexa.accessorykit.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notifications.LocalNotification;
import com.amazon.alexa.accessory.notifications.NotificationInteractor;
import com.amazon.alexa.accessorykit.R;
import com.amazon.alexa.accessorykit.cbl.CblNotification;
import com.amazon.alexa.accessorykit.cbl.CblNotificationInteractor;
/* loaded from: classes6.dex */
public final class DefaultNotificationInteractor implements NotificationInteractor, CblNotificationInteractor {
    private static final String BASE_DEEP_LINK_URL = "https://alexa.amazon.com/?fragment=v2/";
    private static final String CBL_ACCESSORY = "accessory";
    private static final String CBL_ACCESSORY_ADDRESS = "address";
    private static final String CBL_ACCESSORY_NAME = "name";
    private static final String CBL_ACCESSORY_TYPE = "type";
    private static final String CBL_CHANNEL_ID = "ALEXA_CBL_CHANNEL";
    private static final String CBL_INTENT = "com.amazon.alexa.mode.cbl.START";
    private static final int CBL_REQUEST_CODE = 2000;
    private static final String CHANNEL_ID = "ALEXA_ACCESSORIES_CHANNEL";
    private static final String MAIN_MODE_ACTIVITY_NAME = "com.amazon.dee.app.ui.main.MainActivity";
    private static final String PACKAGE_NAME = "com.amazon.dee.app";
    private static final int REQUEST_CODE = 0;
    private static final ComponentName mainComponent = new ComponentName("com.amazon.dee.app", "com.amazon.dee.app.ui.main.MainActivity");
    private final Context applicationContext;
    private final NotificationManagerCompat notificationManager;

    public DefaultNotificationInteractor(Context context) {
        Preconditions.notNull(context, "context");
        this.applicationContext = context.getApplicationContext();
        this.notificationManager = NotificationManagerCompat.from(context);
    }

    @RequiresApi(api = 26)
    private NotificationChannel createAccessoriesNotificationChannel(String str) {
        String string = this.applicationContext.getString(R.string.accessories_notification_channel_name);
        String string2 = this.applicationContext.getString(R.string.accessories_notification_channel_description);
        NotificationChannel notificationChannel = new NotificationChannel(str, string, 4);
        notificationChannel.setDescription(string2);
        return notificationChannel;
    }

    private Notification createNotification(LocalNotification localNotification) {
        return new NotificationCompat.Builder(this.applicationContext, CHANNEL_ID).setSmallIcon(R.drawable.amazon_avs_alexaicon).setColor(ContextCompat.getColor(this.applicationContext, R.color.notification_color)).setContentTitle(localNotification.getTitle()).setContentText(localNotification.getText()).setStyle(new NotificationCompat.BigTextStyle().bigText(localNotification.getText())).setContentIntent(createPendingIntent(localNotification.getDeepLink())).setPriority(1).setVisibility(1).setCategory(NotificationCompat.CATEGORY_RECOMMENDATION).setAutoCancel(true).build();
    }

    @RequiresApi(api = 26)
    private void createNotificationChannelForOreoAndAbove(String str) {
        NotificationManager notificationManager = (NotificationManager) this.applicationContext.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(createAccessoriesNotificationChannel(str));
        }
    }

    private PendingIntent createPendingIntent(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(BASE_DEEP_LINK_URL + str));
        intent.setPackage(this.applicationContext.getPackageName());
        return PendingIntent.getActivity(this.applicationContext, 0, intent, 268435456);
    }

    @Override // com.amazon.alexa.accessory.notifications.NotificationInteractor, com.amazon.alexa.accessorykit.cbl.CblNotificationInteractor
    public boolean areNotificationsEnabled() {
        return this.notificationManager.areNotificationsEnabled();
    }

    @Override // com.amazon.alexa.accessory.notifications.NotificationInteractor, com.amazon.alexa.accessorykit.cbl.CblNotificationInteractor
    public void cancel(int i) {
        this.notificationManager.cancel(i);
    }

    @Override // com.amazon.alexa.accessory.notifications.NotificationInteractor
    public void show(int i, LocalNotification localNotification) {
        int i2 = Build.VERSION.SDK_INT;
        createNotificationChannelForOreoAndAbove(CHANNEL_ID);
        this.notificationManager.notify(i, createNotification(localNotification));
    }

    private PendingIntent createPendingIntent(Accessory accessory) {
        Intent intent = new Intent(CBL_INTENT);
        intent.setComponent(mainComponent);
        intent.setFlags(603979776);
        Bundle bundle = new Bundle();
        bundle.putString("name", accessory.getName());
        bundle.putString("address", accessory.getAddress());
        bundle.putString("type", accessory.getTransport().toString());
        intent.putExtra("accessory", bundle);
        return PendingIntent.getActivity(this.applicationContext, 2000, intent, 268435456);
    }

    @Override // com.amazon.alexa.accessorykit.cbl.CblNotificationInteractor
    public void show(int i, CblNotification cblNotification) {
        int i2 = Build.VERSION.SDK_INT;
        createNotificationChannelForOreoAndAbove(CBL_CHANNEL_ID);
        this.notificationManager.notify(i, createNotification(cblNotification));
    }

    private Notification createNotification(CblNotification cblNotification) {
        return new NotificationCompat.Builder(this.applicationContext, CBL_CHANNEL_ID).setSmallIcon(R.drawable.amazon_avs_alexaicon).setColor(ContextCompat.getColor(this.applicationContext, R.color.notification_color)).setContentTitle(cblNotification.getTitle()).setContentText(cblNotification.getText()).setStyle(new NotificationCompat.BigTextStyle().bigText(cblNotification.getText())).setContentIntent(createPendingIntent(cblNotification.getAccessory())).setPriority(1).setVisibility(1).setCategory(NotificationCompat.CATEGORY_RECOMMENDATION).setAutoCancel(true).build();
    }
}
