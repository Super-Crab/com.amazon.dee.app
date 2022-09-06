package com.amazon.comms.ringservice.authtoken;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.comms.log.CommsLogger;
import java.util.Locale;
/* loaded from: classes12.dex */
public class AlarmAuthTokenManager implements AuthTokenManager {
    public static final String ACTION_AUTH_TOKEN_ALARM = "com.amazon.comms.ringservice.ACTION_AUTH_TOKEN_ALARM";
    private static final long DEFAULT_ALARM_TIME = 60;
    private static final long REFRESH_BEFORE_IN_SECS = 300;
    private static final int REFRESH_RANDOM_INTERVAL_IN_SECS = 10;
    private static final int VALIDITY_BUFFER_IN_SECS = 10;
    private static final CommsLogger sLog = CommsLogger.getLogger(AlarmAuthTokenManager.class);
    private final AlarmManager alarmManager;
    private final Context context;
    private long expireTime;
    private PendingIntent pendingIntent;

    public AlarmAuthTokenManager(@NonNull Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private long generateAlarmTime(long j) {
        long random = (j - REFRESH_BEFORE_IN_SECS) - ((long) (Math.random() * 10.0d));
        if (random > 0) {
            return random;
        }
        return 60L;
    }

    @Override // com.amazon.comms.ringservice.authtoken.AuthTokenManager
    public void cancelAuthTokenAlarm() {
        if (this.pendingIntent != null) {
            getLogger().i("Cancelling alarm with actioncom.amazon.comms.ringservice.ACTION_AUTH_TOKEN_ALARM");
            this.alarmManager.cancel(this.pendingIntent);
            this.pendingIntent.cancel();
            this.pendingIntent = null;
        }
    }

    @VisibleForTesting
    long getExpireTime() {
        return this.expireTime;
    }

    @NonNull
    protected CommsLogger getLogger() {
        return sLog;
    }

    @Override // com.amazon.comms.ringservice.authtoken.AuthTokenManager
    public boolean isAuthTokenValid() {
        return this.expireTime - (SystemClock.elapsedRealtime() / 1000) > 10;
    }

    @Override // com.amazon.comms.ringservice.authtoken.AuthTokenManager
    public void setAuthTokenAlarm(int i, long j) {
        CommsLogger commsLogger = sLog;
        commsLogger.d("setAuthTokenAlarm: expiryInterval= " + i + " authTokenExpireTime= " + j);
        this.expireTime = j;
        this.pendingIntent = PendingIntent.getBroadcast(this.context, 0, new Intent(ACTION_AUTH_TOKEN_ALARM), 134217728);
        long generateAlarmTime = generateAlarmTime((long) i);
        this.alarmManager.setExact(2, (1000 * generateAlarmTime) + SystemClock.elapsedRealtime(), this.pendingIntent);
        getLogger().i(String.format(Locale.getDefault(), "Scheduled auth token alarm in %d seconds", Long.valueOf(generateAlarmTime)));
    }
}
