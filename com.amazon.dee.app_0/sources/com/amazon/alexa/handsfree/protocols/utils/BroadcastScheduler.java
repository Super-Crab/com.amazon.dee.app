package com.amazon.alexa.handsfree.protocols.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
/* loaded from: classes8.dex */
public abstract class BroadcastScheduler {
    @VisibleForTesting
    static final long LAST_SCHEDULE_CHECK_UNDEFINED = 0;
    private final Context mContext;

    public BroadcastScheduler(@NonNull Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    protected abstract int getDelayMillis();

    @NonNull
    protected abstract Intent getIntentToBroadcast();

    @VisibleForTesting
    public long getLastScheduleCheck() {
        return getSharedPreferences().getLong(getLastScheduleCheckTag(), 0L);
    }

    @NonNull
    protected abstract String getLastScheduleCheckTag();

    @NonNull
    protected abstract PendingIntent getPendingIntentToBroadcast();

    @NonNull
    @VisibleForTesting
    protected SharedPreferences getSharedPreferences() {
        return this.mContext.getSharedPreferences(getSharedPreferencesFile(), 0);
    }

    @NonNull
    protected abstract String getSharedPreferencesFile();

    @NonNull
    protected abstract String getTag();

    @VisibleForTesting
    public boolean isSchedulingRequired() {
        long lastScheduleCheck = getLastScheduleCheck();
        if (lastScheduleCheck != 0) {
            return SystemClock.elapsedRealtime() - lastScheduleCheck > ((long) getDelayMillis());
        }
        this.mContext.sendBroadcast(getIntentToBroadcast());
        return true;
    }

    public void scheduleNextCheck() {
        if (!isSchedulingRequired()) {
            return;
        }
        setLastScheduleCheck(SystemClock.elapsedRealtime());
        Log.d(getTag(), "scheduleNextCheck!");
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent pendingIntentToBroadcast = getPendingIntentToBroadcast();
        Log.v(getTag(), "Cancelling the current alarm(s), if set");
        alarmManager.cancel(pendingIntentToBroadcast);
        alarmManager.set(3, SystemClock.elapsedRealtime() + getDelayMillis(), pendingIntentToBroadcast);
        Log.v(getTag(), "scheduleNextCheck: Scheduled next check.");
    }

    @VisibleForTesting
    public void setLastScheduleCheck(long j) {
        getSharedPreferences().edit().putLong(getLastScheduleCheckTag(), j).apply();
    }
}
