package com.amazon.alexa.alertsca.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.widget.RemoteViews;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.alertsca.AlertLabel;
import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
import com.amazon.alexa.alertsca.R;
import com.amazon.alexa.alertsca.utils.datetime.AlertsDateTimeFormatter;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.amazon.alexa.device.api.DeviceInformation;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class AlexaAlertsNotificationBuilderFactory {
    private static final String ALERT_NOTIFICATION_GROUP_KEY = "ALERT_NOTIFICATION_GROUP_KEY";

    /* renamed from: com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationBuilderFactory$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType = new int[AlertType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.TIMER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.REMINDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.ALARM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes6.dex */
    static class NotificationBuilder {
        @IdRes
        private final int chronometerResourceId;
        private final NotificationCompat.Builder notificationBuilder;
        private final RemoteViews view;

        /* synthetic */ NotificationBuilder(NotificationCompat.Builder builder, RemoteViews remoteViews, int i, AnonymousClass1 anonymousClass1) {
            this(builder, remoteViews, i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Notification create() {
            return this.notificationBuilder.build();
        }

        public void setFullScreenIntent(PendingIntent pendingIntent, boolean z) {
            this.notificationBuilder.setFullScreenIntent(pendingIntent, z);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setTime(String str) {
            if (this.view != null) {
                int i = Build.VERSION.SDK_INT;
            }
        }

        private NotificationBuilder(NotificationCompat.Builder builder, @Nullable RemoteViews remoteViews, @IdRes int i) {
            this.notificationBuilder = builder;
            this.view = remoteViews;
            this.chronometerResourceId = i;
        }
    }

    /* loaded from: classes6.dex */
    interface NotificationUpdateHandler {
        void onUpdate(AlertRecord alertRecord, String str);
    }

    public static NotificationBuilder create(AlertRecord alertRecord, Context context, String str, @Nullable DeviceInformation deviceInformation, boolean z) {
        RemoteViews remoteViews;
        String str2;
        String string;
        int color = ContextCompat.getColor(context, R.color.neutral10);
        int ordinal = alertRecord.getType().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.amazon_avs_notification_timer);
                remoteViews.setTextColor(R.id.amazon_avs_timer_name, color);
                remoteViews.setTextColor(R.id.amazon_avs_timer_chronometer, color);
                string = context.getString(R.string.amazon_avs_timer_label);
                remoteViews.setTextViewText(R.id.amazon_avs_timer_name, !AlertLabel.NONE.equals(alertRecord.getLabel()) ? alertRecord.getLabel().getValue() : string);
                int i = Build.VERSION.SDK_INT;
                remoteViews.setChronometer(R.id.amazon_avs_timer_chronometer, SystemClock.elapsedRealtime() + (alertRecord.getScheduledTime().getTime() - System.currentTimeMillis()), null, true);
                remoteViews.setChronometerCountDown(R.id.amazon_avs_timer_chronometer, true);
            } else if (ordinal == 2) {
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.amazon_avs_notification_alarm);
                remoteViews.setTextColor(R.id.amazon_avs_alarm_time, color);
                remoteViews.setTextColor(R.id.amazon_avs_alarm, color);
                string = context.getString(R.string.amazon_avs_reminder_label);
                remoteViews.setTextViewText(R.id.amazon_avs_alarm_time, !AlertLabel.NONE.equals(alertRecord.getLabel()) ? alertRecord.getLabel().getValue() : string);
                remoteViews.setTextViewText(R.id.amazon_avs_alarm, string);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Alert type: ");
                outline107.append(alertRecord.getType());
                outline107.append(" is not supported yet.");
                throw new UnsupportedOperationException(outline107.toString());
            }
            str2 = string;
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.amazon_avs_notification_alarm);
            remoteViews.setTextColor(R.id.amazon_avs_alarm_time, color);
            remoteViews.setTextColor(R.id.amazon_avs_alarm, color);
            String formatTime = new AlertsDateTimeFormatter(context).formatTime(alertRecord.getScheduledTime());
            String string2 = context.getString(R.string.amazon_avs_alarm_label);
            remoteViews.setTextViewText(R.id.amazon_avs_alarm, string2);
            remoteViews.setTextViewText(R.id.amazon_avs_alarm_time, formatTime);
            str2 = string2;
        }
        return new NotificationBuilder(createNotificationBuilder(remoteViews, str2, context, str, alertRecord, deviceInformation, z), remoteViews, R.id.amazon_avs_timer_chronometer, null);
    }

    private static NotificationCompat.Builder createNotificationBuilder(RemoteViews remoteViews, String str, Context context, String str2, AlertRecord alertRecord, @Nullable DeviceInformation deviceInformation, boolean z) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, str2);
        builder.setVisibility(1).setContentTitle(str).setSmallIcon(R.drawable.amazon_avs_alexaicon).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setCustomContentView(remoteViews).setColor(ContextCompat.getColor(context, R.color.neutral10)).setGroup(ALERT_NOTIFICATION_GROUP_KEY).setCategory(NotificationCompat.CATEGORY_ALARM).addAction(R.drawable.amazon_avs_alexaicon, context.getString(R.string.amazon_avs_dismiss_text), AlertsIntentFactory.createNotificationStopIntent(context, alertRecord));
        if (z) {
            builder.setPriority(1);
            builder.setDefaults(-1);
        }
        if (alertRecord.isReminder()) {
            builder.setContentIntent(AlertsIntentFactory.createNavigationPendingIntent(context, alertRecord, deviceInformation));
            builder.setOngoing(false).setDeleteIntent(AlertsIntentFactory.createNotificationStopIntent(context, alertRecord));
        } else {
            builder.setOngoing(true);
        }
        return builder;
    }
}
