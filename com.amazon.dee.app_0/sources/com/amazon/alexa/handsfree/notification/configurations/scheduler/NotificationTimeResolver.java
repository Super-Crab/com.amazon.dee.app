package com.amazon.alexa.handsfree.notification.configurations.scheduler;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class NotificationTimeResolver {
    private static final int HOUR_OF_MORNING = 8;
    private static final int HOUR_OF_NOON = 12;
    private static final int MIN_OF_NOON = 0;
    private static final int SEC_OF_NOON = 0;
    private static final String TAG = "NotificationTimeResolver";
    private final NotificationQuotaManager.QuotaConstraintsHandler mConfigIntervalHandler;
    private final Context mContext;
    private final ScheduleTransformation mDefaultTransformation;
    private final NotificationOccurrenceCounter mNotificationOccurrenceCounter;

    /* loaded from: classes8.dex */
    public interface ScheduleTransformation {
        void transformScheduledTime(@NonNull Calendar calendar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationTimeResolver(@NonNull Context context, @NonNull NotificationQuotaManager.QuotaConstraintsHandler quotaConstraintsHandler) {
        this(context, quotaConstraintsHandler, NotificationOccurrenceCounter.getInstance(), new ScheduleTransformation() { // from class: com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationTimeResolver.1
            @Override // com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationTimeResolver.ScheduleTransformation
            public void transformScheduledTime(@NonNull Calendar calendar) {
                calendar.set(11, 12);
                calendar.set(12, 0);
                calendar.set(13, 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public Calendar getCalendarForOneTimeNotification(@NonNull NotificationType notificationType) {
        if (isValidOccurrenceCount(this.mNotificationOccurrenceCounter.getNotificationCount(this.mContext, notificationType), notificationType)) {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar.set(11, 8);
            calendar.set(12, 0);
            calendar.set(13, 0);
            if (calendar2.after(calendar)) {
                calendar.add(5, 1);
            }
            return calendar;
        }
        Log.v(TAG, "No valid notification occurrence found.");
        return null;
    }

    @Nullable
    public Long getElapsedTimeUntilNextNotification(@NonNull NotificationType notificationType) {
        return getElapsedTimeUntilNextNotification(notificationType, this.mDefaultTransformation, SystemClock.elapsedRealtime(), Calendar.getInstance());
    }

    @VisibleForTesting
    long getTransformedTimeInMillis(@NonNull Long l, @NonNull ScheduleTransformation scheduleTransformation, long j, @NonNull Calendar calendar) {
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.setTimeInMillis(l.longValue() + calendar2.getTimeInMillis());
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Raw next notification schedule calendar time: ");
        outline107.append(calendar2.getTimeInMillis());
        outline107.append(" Raw next notification schedule elapsed real time: ");
        outline107.append(j);
        outline107.append(l);
        Log.d(str, outline107.toString());
        scheduleTransformation.transformScheduledTime(calendar2);
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Adjusted next notification schedule calendar time: ");
        outline1072.append(calendar2.getTimeInMillis());
        outline1072.append(" Adjusted next notification schedule elapsed real time: ");
        outline1072.append((calendar2.getTimeInMillis() + j) - calendar.getTimeInMillis());
        Log.d(str2, outline1072.toString());
        return calendar2.getTimeInMillis() - calendar.getTimeInMillis();
    }

    @VisibleForTesting
    boolean isValidOccurrenceCount(int i, @NonNull NotificationType notificationType) {
        return i < this.mConfigIntervalHandler.getTotalNotifications(notificationType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public NotificationTimeResolver(@NonNull Context context, @NonNull NotificationQuotaManager.QuotaConstraintsHandler quotaConstraintsHandler, @NonNull NotificationOccurrenceCounter notificationOccurrenceCounter, @NonNull ScheduleTransformation scheduleTransformation) {
        this.mContext = context;
        this.mConfigIntervalHandler = quotaConstraintsHandler;
        this.mNotificationOccurrenceCounter = notificationOccurrenceCounter;
        this.mDefaultTransformation = scheduleTransformation;
    }

    @Nullable
    @VisibleForTesting
    Long getElapsedTimeUntilNextNotification(@NonNull NotificationType notificationType, @NonNull ScheduleTransformation scheduleTransformation, long j, @NonNull Calendar calendar) {
        int notificationCount = this.mNotificationOccurrenceCounter.getNotificationCount(this.mContext, notificationType);
        List<Long> configTimeIntervals = this.mConfigIntervalHandler.getConfigTimeIntervals(notificationType);
        if (!isValidOccurrenceCount(notificationCount, notificationType)) {
            Log.v(TAG, "No valid notification occurrence found.");
            return null;
        } else if (notificationCount == 0) {
            return Long.valueOf(configTimeIntervals.get(notificationCount).longValue() + j);
        } else {
            return Long.valueOf(j + getTransformedTimeInMillis(configTimeIntervals.get(notificationCount), scheduleTransformation, j, calendar));
        }
    }
}
