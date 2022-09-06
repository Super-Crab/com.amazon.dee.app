package com.amazon.alexa.voice.handsfree.mike.vesper.notifications.configurations;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.NotificationOccurrenceCounter;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes11.dex */
class NotificationTimeResolver {
    private static final int HOUR_OF_NOON = 12;
    private static final int MIN_OF_NOON = 0;
    private static final int SEC_OF_NOON = 0;
    private static final String TAG = "NotificationTimeResolver";
    private final Context mContext;
    private final ScheduleTransformation mDefaultTransformation;
    private final NotificationOccurrenceCounter mNotificationOccurrenceCounter;

    /* loaded from: classes11.dex */
    public interface ScheduleTransformation {
        void transformScheduledTime(@NonNull Calendar calendar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationTimeResolver(@NonNull Context context) {
        this(context, NotificationOccurrenceCounter.getInstance(), new ScheduleTransformation() { // from class: com.amazon.alexa.voice.handsfree.mike.vesper.notifications.configurations.NotificationTimeResolver.1
            @Override // com.amazon.alexa.voice.handsfree.mike.vesper.notifications.configurations.NotificationTimeResolver.ScheduleTransformation
            public void transformScheduledTime(@NonNull Calendar calendar) {
                calendar.set(11, 12);
                calendar.set(12, 0);
                calendar.set(13, 0);
            }
        });
    }

    @NonNull
    List<Long> getConfigTimeIntervals(@NonNull Context context) {
        int[] intArray = context.getResources().getIntArray(R.array.notification_time_intervals_vesper);
        ArrayList arrayList = new ArrayList(intArray.length);
        for (int i : intArray) {
            arrayList.add(Long.valueOf(Integer.valueOf(i).longValue()));
        }
        Log.d(TAG, "Default configured notification intervals: " + arrayList);
        return arrayList;
    }

    @Nullable
    public Long getElapsedTimeUntilNextNotification() {
        return getElapsedTimeUntilNextNotification(this.mDefaultTransformation, SystemClock.elapsedRealtime(), Calendar.getInstance());
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasQuotaAvailable() {
        return this.mNotificationOccurrenceCounter.getNotificationCount(this.mContext) < getConfigTimeIntervals(this.mContext).size();
    }

    @VisibleForTesting
    NotificationTimeResolver(@NonNull Context context, @NonNull NotificationOccurrenceCounter notificationOccurrenceCounter, @NonNull ScheduleTransformation scheduleTransformation) {
        this.mContext = context;
        this.mNotificationOccurrenceCounter = notificationOccurrenceCounter;
        this.mDefaultTransformation = scheduleTransformation;
    }

    @Nullable
    @VisibleForTesting
    Long getElapsedTimeUntilNextNotification(@NonNull ScheduleTransformation scheduleTransformation, long j, @NonNull Calendar calendar) {
        int notificationCount = this.mNotificationOccurrenceCounter.getNotificationCount(this.mContext);
        List<Long> configTimeIntervals = getConfigTimeIntervals(this.mContext);
        if (!hasQuotaAvailable()) {
            Log.v(TAG, "No valid notification occurrence found.");
            return null;
        } else if (notificationCount == 0) {
            return Long.valueOf(configTimeIntervals.get(notificationCount).longValue() + j);
        } else {
            return Long.valueOf(j + getTransformedTimeInMillis(configTimeIntervals.get(notificationCount), scheduleTransformation, j, calendar));
        }
    }
}
