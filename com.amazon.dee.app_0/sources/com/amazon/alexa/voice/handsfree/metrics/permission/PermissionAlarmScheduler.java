package com.amazon.alexa.voice.handsfree.metrics.permission;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes11.dex */
public class PermissionAlarmScheduler {
    static final String EXTRA_QUOTA_ID = "ExtraQuotaId";
    static final int INITIAL_QUOTA_ID = 0;
    private static final int PENDING_INTENT_REQUEST_CODE = 1;
    private static final String TAG = "PermissionAlarmScheduler";
    private static final int[] DELAY_MILLIS = {1800000, 5400000, 14400000, 21600000, 43200000};
    static final int QUOTA_COUNT = DELAY_MILLIS.length;

    @VisibleForTesting
    PendingIntent getPendingIntent(@NonNull Context context, int i) {
        Intent intent = new Intent(context, PermissionCheckReceiver.class);
        intent.putExtra(EXTRA_QUOTA_ID, i);
        return PendingIntent.getBroadcast(context, 1, intent, 0);
    }

    public void schedulePermissionChecker(@NonNull Context context) {
        schedulePermissionChecker(context, 0);
    }

    public void schedulePermissionChecker(@NonNull Context context, int i) {
        if (i >= 0 && i < QUOTA_COUNT) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (alarmManager == null) {
                return;
            }
            PendingIntent pendingIntent = getPendingIntent(context, i);
            alarmManager.cancel(pendingIntent);
            alarmManager.set(3, SystemClock.elapsedRealtime() + DELAY_MILLIS[i], pendingIntent);
            return;
        }
        Log.i(TAG, String.format("schedulePermissionChecker: not scheduling alarm for quota id: %d", Integer.valueOf(i)));
    }
}
