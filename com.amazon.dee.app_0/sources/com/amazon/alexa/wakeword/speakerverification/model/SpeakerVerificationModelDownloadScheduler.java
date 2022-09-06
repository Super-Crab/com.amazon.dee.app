package com.amazon.alexa.wakeword.speakerverification.model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
/* loaded from: classes11.dex */
public class SpeakerVerificationModelDownloadScheduler {
    private static final int DELAY_MILLIS = 86400000;
    private static final int PENDING_INTENT_REQUEST_CODE = 0;

    @VisibleForTesting
    PendingIntent getPendingIntent(@NonNull Context context, @NonNull Intent intent) {
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public void scheduleModelDownload(@NonNull Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            PendingIntent pendingIntent = getPendingIntent(context, new Intent(context, SpeakerVerificationModelDownloadReceiver.class));
            alarmManager.cancel(pendingIntent);
            alarmManager.set(3, SystemClock.elapsedRealtime() + 86400000, pendingIntent);
        }
    }
}
