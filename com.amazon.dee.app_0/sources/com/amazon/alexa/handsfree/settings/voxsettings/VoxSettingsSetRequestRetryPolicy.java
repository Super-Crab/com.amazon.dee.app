package com.amazon.alexa.handsfree.settings.voxsettings;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
class VoxSettingsSetRequestRetryPolicy {
    private static final long BASE_DELAY_MILLIS = 5000;
    private static final int MAX_RETRIES = 60;
    private static final int REQUEST_CODE = 1010;
    private static final String TAG = "VoxRequestRetryPolicy";
    private final Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoxSettingsSetRequestRetryPolicy(@NonNull Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scheduleRetry(int i) {
        if (i > 60) {
            Log.i(TAG, "Reached max number of retries (60). Stop retrying.");
            return;
        }
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            Log.i(TAG, String.format("Scheduling retry number %d for %d milliseconds from now", Integer.valueOf(i), 5000L));
            alarmManager.set(3, SystemClock.elapsedRealtime() + 5000, PendingIntent.getBroadcast(this.mContext, 1010, new Intent(this.mContext, VoxSettingsSetRetryAttemptReceiver.class).putExtra("retryAttempt", i), 134217728));
            return;
        }
        Log.w(TAG, "No AlarmManager available, failed to schedule retry attempt");
    }
}
