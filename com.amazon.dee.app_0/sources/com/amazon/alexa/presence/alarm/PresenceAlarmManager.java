package com.amazon.alexa.presence.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.presence.Presence;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class PresenceAlarmManager {
    public static final String ALARM_INTENT_ACTION = "presence_scan_check_alarm_action";
    private final AlarmManager alarmManager;
    private final Context context;
    private static final String TAG = Presence.tag();
    @VisibleForTesting
    static final long SCAN_CHECK_ALARM_INTERVAL_MILLIS = TimeUnit.MILLISECONDS.convert(25, TimeUnit.MINUTES);

    public PresenceAlarmManager(Context context, AlarmManager alarmManager) {
        this.context = context;
        this.alarmManager = alarmManager;
    }

    public void cancel() {
        this.alarmManager.cancel(PendingIntent.getBroadcast(this.context, 0, new Intent(ALARM_INTENT_ACTION), 0));
    }

    public void set() {
        PendingIntent broadcast = PendingIntent.getBroadcast(this.context, 0, new Intent(ALARM_INTENT_ACTION), 0);
        AlarmManager alarmManager = this.alarmManager;
        long j = SCAN_CHECK_ALARM_INTERVAL_MILLIS;
        alarmManager.setRepeating(0, j, j, broadcast);
    }
}
