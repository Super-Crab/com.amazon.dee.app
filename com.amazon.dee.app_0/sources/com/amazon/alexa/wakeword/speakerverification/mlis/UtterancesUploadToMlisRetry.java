package com.amazon.alexa.wakeword.speakerverification.mlis;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentController;
/* loaded from: classes11.dex */
public class UtterancesUploadToMlisRetry {
    private static final long MINUTES_TO_WAIT_IN_MILLIS = 3600000;
    private static final int PENDING_INTENT_REQUEST_CODE = 1;
    private static final String TAG = "UtterancesUploadToMlisRetry";

    @VisibleForTesting
    PendingIntent getPendingIntent(@NonNull Context context, int i) {
        Intent intent = new Intent(context, UtterancesUploadToMlisReceiver.class);
        intent.putExtra(EnrollmentController.UPLOAD_UTTERANCES_RETRIES, i - 1);
        return PendingIntent.getBroadcast(context, 1, intent, 134217728);
    }

    public void scheduleRetry(@NonNull Context context, int i) {
        if (i > 0) {
            String str = "onHandleWork: Setting up alarm. Retries remaining: " + i;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (alarmManager == null) {
                Log.d(TAG, "onHandleWork: Alarm could not be set. Alarm manager is NULL");
                return;
            }
            PendingIntent pendingIntent = getPendingIntent(context, i);
            alarmManager.cancel(pendingIntent);
            alarmManager.setExactAndAllowWhileIdle(0, System.currentTimeMillis() + 3600000, pendingIntent);
            Log.d(TAG, "onHandleWork: Alarm is set, waiting for next upload attempt.");
            return;
        }
        Log.d(TAG, "onHandleWork: There are no more upload retries");
    }
}
