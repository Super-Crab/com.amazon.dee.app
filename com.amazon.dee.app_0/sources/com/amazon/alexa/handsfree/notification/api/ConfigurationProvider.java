package com.amazon.alexa.handsfree.notification.api;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes8.dex */
public interface ConfigurationProvider {

    /* loaded from: classes8.dex */
    public enum ConfigComponent {
        HANDS_FREE,
        LOCK_SCREEN,
        NOTIFICATION
    }

    Integer getMaxAllowedNotificationsBeforeTerms(@NonNull Context context);

    Integer getMaxDaysAfterFirstUtterance(@NonNull Context context, @NonNull String str);

    Integer getMinHoursBeforeNextUtterance(@NonNull Context context, @NonNull String str);

    List<Long> getNotificationTimeIntervals(@NonNull Context context, @NonNull String str);

    Integer getUtteranceNotificationMaxCount(@NonNull Context context, @NonNull String str);

    boolean isActive(@NonNull Context context, @NonNull ConfigComponent configComponent);
}
