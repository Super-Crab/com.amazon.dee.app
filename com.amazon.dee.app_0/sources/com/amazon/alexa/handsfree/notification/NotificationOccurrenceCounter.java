package com.amazon.alexa.handsfree.notification;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes8.dex */
public class NotificationOccurrenceCounter {
    static final String NOTIFICATION_TIMESTAMP_SHARED_PREFS_KEY_TEMPLATE = "%s_NOTIFICATION_TIMESTAMP_%d";
    @VisibleForTesting
    static final String SHARED_PREFERENCE_FILE_NAME = "NotificationOccurrenceCounter";

    /* loaded from: classes8.dex */
    private static class LazyHolder {
        private static final NotificationOccurrenceCounter INSTANCE = new NotificationOccurrenceCounter();

        private LazyHolder() {
        }
    }

    @NonNull
    public static NotificationOccurrenceCounter getInstance() {
        return LazyHolder.INSTANCE;
    }

    public int getNotificationCount(@NonNull Context context, @NonNull NotificationType notificationType) {
        return context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, 0).getInt(notificationType.getSharedPreferencesKey(), 0);
    }

    public long getUtteranceNotificationTime(@NonNull Context context, int i, @NonNull NotificationType notificationType) {
        return context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, 0).getLong(String.format(Locale.getDefault(), NOTIFICATION_TIMESTAMP_SHARED_PREFS_KEY_TEMPLATE, notificationType, Integer.valueOf(i)), 0L);
    }

    public void incrementNotificationCount(@NonNull Context context, @NonNull NotificationType notificationType) {
        context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, 0).edit().putInt(notificationType.getSharedPreferencesKey(), getNotificationCount(context, notificationType) + 1).apply();
    }

    public void saveUtteranceNotificationTime(@NonNull Context context, @NonNull NotificationType notificationType) {
        if (!notificationType.isTriggeredByUtterance()) {
            return;
        }
        int notificationCount = getNotificationCount(context, notificationType);
        context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, 0).edit().putLong(String.format(Locale.getDefault(), NOTIFICATION_TIMESTAMP_SHARED_PREFS_KEY_TEMPLATE, notificationType, Integer.valueOf(notificationCount)), new Date().getTime()).apply();
    }

    private NotificationOccurrenceCounter() {
    }
}
