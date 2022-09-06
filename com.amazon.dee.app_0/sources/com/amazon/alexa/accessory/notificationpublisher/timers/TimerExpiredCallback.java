package com.amazon.alexa.accessory.notificationpublisher.timers;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource;
/* loaded from: classes.dex */
public interface TimerExpiredCallback {
    void onTimerExpired(int i, @Nullable NotificationSource notificationSource);
}
