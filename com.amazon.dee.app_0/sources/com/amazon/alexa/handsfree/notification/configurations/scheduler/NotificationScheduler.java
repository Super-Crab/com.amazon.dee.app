package com.amazon.alexa.handsfree.notification.configurations.scheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.receivers.EnableHandsFreeReceiver;
import com.amazon.alexa.handsfree.notification.receivers.KillSwitchReceiver;
import com.amazon.alexa.handsfree.notification.receivers.LanguageSwitchingReceiver;
import com.amazon.alexa.handsfree.notification.receivers.PermissionReceiver;
import com.amazon.alexa.handsfree.notification.receivers.QuickSettingsReceiver;
import com.amazon.alexa.handsfree.notification.receivers.TimeBasedReceiver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Calendar;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class NotificationScheduler {
    private static final int PENDING_INTENT_REQUEST_CODE = 0;
    private static final String TAG = "NotificationScheduler";
    private final Context mContext;
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final NotificationTimeResolver mNotificationTimeResolver;
    private final NotificationType mNotificationType;

    /* renamed from: com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationScheduler$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType = new int[NotificationType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.TIME_BASED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.KILL_SWITCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.QUICK_SETTINGS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.PERMISSION_REQUEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.ENABLE_HANDS_FREE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$notification$NotificationType[NotificationType.LANGUAGE_SWITCHING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public NotificationScheduler(@NonNull NotificationType notificationType, @NonNull Context context, @NonNull NotificationQuotaManager.QuotaConstraintsHandler quotaConstraintsHandler) {
        this(notificationType, context, new NotificationQuotaManager(context, quotaConstraintsHandler), new NotificationTimeResolver(context, quotaConstraintsHandler));
    }

    private PendingIntent getPendingIntent() {
        Context context = this.mContext;
        return PendingIntent.getBroadcast(context, 0, getIntent(this.mNotificationType, context), 134217728);
    }

    public void cancelScheduledNotifications() {
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(getPendingIntent());
    }

    @NonNull
    @VisibleForTesting
    Intent getIntent(@NonNull NotificationType notificationType, @NonNull Context context) {
        int ordinal = notificationType.ordinal();
        if (ordinal != 0) {
            if (ordinal != 2) {
                switch (ordinal) {
                    case 6:
                        return new Intent(context, QuickSettingsReceiver.class);
                    case 7:
                        return new Intent(context, PermissionReceiver.class);
                    case 8:
                        return new Intent(context, EnableHandsFreeReceiver.class);
                    case 9:
                        return new Intent(context, LanguageSwitchingReceiver.class);
                    default:
                        return new Intent();
                }
            }
            return new Intent(context, KillSwitchReceiver.class);
        }
        return new Intent(context, TimeBasedReceiver.class);
    }

    public boolean isSchedulingRequired() {
        return this.mNotificationQuotaManager.hasQuotaAvailable(this.mNotificationType);
    }

    public void scheduleNextNotification() {
        if (!isSchedulingRequired()) {
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("scheduleNextNotification! For ");
        outline107.append(this.mNotificationType.name());
        Log.d(str, outline107.toString());
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent pendingIntent = getPendingIntent();
        NotificationType notificationType = this.mNotificationType;
        if (notificationType == NotificationType.LANGUAGE_SWITCHING) {
            Calendar calendarForOneTimeNotification = this.mNotificationTimeResolver.getCalendarForOneTimeNotification(notificationType);
            if (calendarForOneTimeNotification != null) {
                alarmManager.cancel(pendingIntent);
                alarmManager.set(0, calendarForOneTimeNotification.getTimeInMillis(), pendingIntent);
            }
        } else {
            Long elapsedTimeUntilNextNotification = this.mNotificationTimeResolver.getElapsedTimeUntilNextNotification(notificationType);
            if (elapsedTimeUntilNextNotification == null) {
                Log.v(TAG, "No valid notification occurrence found to schedule.");
                return;
            }
            String str2 = TAG;
            Log.d(str2, "onReceive: scheduleNextNotification " + elapsedTimeUntilNextNotification);
            Log.v(TAG, "Cancelling the current alarm(s), if set");
            alarmManager.cancel(pendingIntent);
            String str3 = TAG;
            Log.v(str3, "Scheduling the next automatic notification for " + elapsedTimeUntilNextNotification + " since boot");
            alarmManager.setExact(2, elapsedTimeUntilNextNotification.longValue(), pendingIntent);
        }
        Log.v(TAG, "scheduleNextNotification: Scheduled next notification.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public NotificationScheduler(@NonNull NotificationType notificationType, @NonNull Context context, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull NotificationTimeResolver notificationTimeResolver) {
        this.mNotificationType = notificationType;
        this.mContext = context;
        this.mNotificationQuotaManager = notificationQuotaManager;
        this.mNotificationTimeResolver = notificationTimeResolver;
    }
}
