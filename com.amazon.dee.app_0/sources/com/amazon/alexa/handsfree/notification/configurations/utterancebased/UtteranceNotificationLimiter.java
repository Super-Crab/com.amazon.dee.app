package com.amazon.alexa.handsfree.notification.configurations.utterancebased;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import com.amazon.alexa.handsfree.notification.NotificationType;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class UtteranceNotificationLimiter {
    private final Context mContext;
    private final NotificationOccurrenceCounter mNotificationOccurrenceCounter;
    private final NotificationType mNotificationType;
    private final UtteranceConfigHandler mUtteranceConfigHandler;

    public UtteranceNotificationLimiter(@NonNull Context context, @NonNull NotificationType notificationType) {
        this(context, notificationType, NotificationOccurrenceCounter.getInstance(), new UtteranceConfigHandler(context));
    }

    public boolean isMaxDaysPastFirstUtterance() {
        long utteranceNotificationTime = this.mNotificationOccurrenceCounter.getUtteranceNotificationTime(this.mContext, 1, this.mNotificationType);
        if (utteranceNotificationTime == 0) {
            return false;
        }
        return TimeUnit.DAYS.convert(new Date().getTime() - utteranceNotificationTime, TimeUnit.MILLISECONDS) > ((long) this.mUtteranceConfigHandler.getConfigDaysAfterFirstUtterance(this.mNotificationType));
    }

    public boolean isMinHrsFromLastUtterance() {
        NotificationOccurrenceCounter notificationOccurrenceCounter = this.mNotificationOccurrenceCounter;
        Context context = this.mContext;
        long utteranceNotificationTime = notificationOccurrenceCounter.getUtteranceNotificationTime(context, notificationOccurrenceCounter.getNotificationCount(context, this.mNotificationType), this.mNotificationType);
        if (utteranceNotificationTime == 0) {
            return true;
        }
        return TimeUnit.HOURS.convert(new Date().getTime() - utteranceNotificationTime, TimeUnit.MILLISECONDS) >= ((long) this.mUtteranceConfigHandler.getConfigHoursBeforeNextUtterance(this.mNotificationType));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public UtteranceNotificationLimiter(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationOccurrenceCounter notificationOccurrenceCounter, @NonNull UtteranceConfigHandler utteranceConfigHandler) {
        this.mContext = context;
        this.mNotificationType = notificationType;
        this.mNotificationOccurrenceCounter = notificationOccurrenceCounter;
        this.mUtteranceConfigHandler = utteranceConfigHandler;
    }
}
