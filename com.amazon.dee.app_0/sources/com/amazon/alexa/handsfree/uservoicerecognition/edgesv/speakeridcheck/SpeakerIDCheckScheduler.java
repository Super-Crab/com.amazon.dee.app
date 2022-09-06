package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.speakeridcheck;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
/* loaded from: classes8.dex */
public class SpeakerIDCheckScheduler {
    private static final int DELAY_MILLIS = 86400000;
    private static final int PENDING_INTENT_REQUEST_CODE = 4;

    @VisibleForTesting
    PendingIntent getPendingIntent(@NonNull Context context) {
        return PendingIntent.getBroadcast(context, 4, new Intent(context, SpeakerIDCheckReceiver.class), 0);
    }

    public void scheduleChecker(@NonNull Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            PendingIntent pendingIntent = getPendingIntent(context);
            alarmManager.cancel(pendingIntent);
            alarmManager.set(3, SystemClock.elapsedRealtime() + 86400000, pendingIntent);
        }
    }
}
