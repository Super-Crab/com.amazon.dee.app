package com.amazon.alexa.voice.handsfree.mike.vesper.notifications.configurations;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.notifiers.AppDownloadNotifier;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.receivers.AppDownloadTimeBasedReceiver;
/* loaded from: classes11.dex */
public class NotificationScheduler {
    private static final int PENDING_INTENT_REQUEST_CODE = 0;
    private static final String TAG = "NotificationScheduler";
    private final AppDownloadNotifier mAppDownloadNotifier;
    private final Context mContext;
    private final NotificationTimeResolver mNotificationTimeResolver;

    public NotificationScheduler(@NonNull Context context) {
        this(context, new NotificationTimeResolver(context), new AppDownloadNotifier(context));
    }

    @NonNull
    private Intent getIntent(@NonNull Context context) {
        return new Intent(context, AppDownloadTimeBasedReceiver.class);
    }

    private PendingIntent getPendingIntent() {
        Context context = this.mContext;
        return PendingIntent.getBroadcast(context, 0, getIntent(context), 134217728);
    }

    private boolean isSchedulingRequired() {
        return this.mNotificationTimeResolver.hasQuotaAvailable() && this.mAppDownloadNotifier.isNotificationRequired();
    }

    public void cancelScheduledNotifications() {
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(getPendingIntent());
    }

    public void scheduleNextNotification() {
        if (!isSchedulingRequired()) {
            Log.d(TAG, "scheduleNextNotification: scheduling not required.");
            return;
        }
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent pendingIntent = getPendingIntent();
        Long elapsedTimeUntilNextNotification = this.mNotificationTimeResolver.getElapsedTimeUntilNextNotification();
        if (elapsedTimeUntilNextNotification == null) {
            Log.v(TAG, "No valid notification occurrence found to schedule.");
            return;
        }
        String str = TAG;
        Log.d(str, "onReceive: scheduleNextNotification " + elapsedTimeUntilNextNotification);
        Log.v(TAG, "Cancelling the current alarm(s), if set");
        alarmManager.cancel(pendingIntent);
        String str2 = TAG;
        Log.v(str2, "Scheduling the next automatic notification for " + elapsedTimeUntilNextNotification + " since boot");
        alarmManager.setExact(2, elapsedTimeUntilNextNotification.longValue(), pendingIntent);
        Log.v(TAG, "scheduleNextNotification: Scheduled next notification.");
    }

    @VisibleForTesting
    NotificationScheduler(@NonNull Context context, @NonNull NotificationTimeResolver notificationTimeResolver, @NonNull AppDownloadNotifier appDownloadNotifier) {
        this.mContext = context;
        this.mNotificationTimeResolver = notificationTimeResolver;
        this.mAppDownloadNotifier = appDownloadNotifier;
    }
}
