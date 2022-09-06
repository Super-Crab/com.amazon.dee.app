package com.amazon.communication.remotesetting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.amazon.dp.logger.DPLogger;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class PeriodicConfigSyncUtils {
    public static final long DEFAULT_FIRST_SYNC_INTERVAL_MILLIS = 900000;
    public static final long DEFAULT_SYNC_REPEAT_INTERVAL_MILLIS = 86400000;
    public static final int DEFAULT_SYNC_RETRY_ATTEMPT_LIMIT = 3;
    public static final String KEY_FIRST_SYNC_INTERVAL_MILLIS = "configSyncFirstIntervalMillis";
    public static final String KEY_SYNC_MAX_DURATION_MILLIS = "syncMaxDurationMillis";
    public static final String KEY_SYNC_REPEAT_INTERVAL_MILLIS = "configSyncRepeatIntervalMillis";
    public static final String KEY_SYNC_RETRY_ATTEMPT_LIMIT = "configSyncRetryAttemptLimit";
    public static final String KEY_SYNC_RETRY_INTERVAL_MILLIS = "configSyncRetryIntervalMillis";
    private static final String TCOMM_PACKAGE_NAME = "com.amazon.tcomm";
    private static final DPLogger log = new DPLogger("TComm.RemoteSetting.PeriodicConfigSyncUtils");
    public static final long DEFAULT_SYNC_RETRY_INTERVAL_MILLIS = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
    public static final long DEFAULT_SYNC_MAX_DURATION_MILLIS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES);

    private PeriodicConfigSyncUtils() {
    }

    private static synchronized void cancelPeriodicConfigSyncing(Context context) {
        synchronized (PeriodicConfigSyncUtils.class) {
            if (!isPeriodicConfigSyncingScheduled(context)) {
                log.verbose("cancelPeriodicConfigSyncing", "Periodic syncing is not scheduled. Skipping cancellation.", new Object[0]);
                return;
            }
            log.info("cancelPeriodicConfigSyncing", "Cancelling periodic configuration syncing.", new Object[0]);
            PendingIntent pendingIntent = getPendingIntent(context, new Intent(ConfigurationSyncReceiver.ACTION_PERIODIC_SYNC), 536870912);
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    private static PendingIntent getPendingIntent(Context context, Intent intent, int i) {
        intent.setPackage("com.amazon.tcomm");
        intent.setComponent(new ComponentName(context, ConfigurationSyncReceiver.class));
        int i2 = Build.VERSION.SDK_INT;
        return PendingIntent.getBroadcast(context, 0, intent, i | 67108864);
    }

    private static long getSyncFirstInterval() {
        RemoteConfigurationManager remoteConfigurationManager = RemoteConfigurationManager.getInstance(RemoteSettingManager.APP_REMOTESETTING_ID);
        if (remoteConfigurationManager == null) {
            log.debug("getSyncFirstInterval", "Unable to get configuration manager, returning default value", new Object[0]);
            return 900000L;
        }
        return remoteConfigurationManager.openConfiguration().getAsJsonObject().optLong(KEY_FIRST_SYNC_INTERVAL_MILLIS, 900000L);
    }

    private static long getSyncRepeatInterval() {
        RemoteConfigurationManager remoteConfigurationManager = RemoteConfigurationManager.getInstance(RemoteSettingManager.APP_REMOTESETTING_ID);
        if (remoteConfigurationManager == null) {
            log.debug("getSyncRepeatInterval", "Unable to get configuration manager, returning default value", new Object[0]);
            return 86400000L;
        }
        return remoteConfigurationManager.openConfiguration().getAsJsonObject().optLong(KEY_SYNC_REPEAT_INTERVAL_MILLIS, 86400000L);
    }

    private static long getSyncRetryTime(int i) {
        if (i >= 0) {
            RemoteConfigurationManager remoteConfigurationManager = RemoteConfigurationManager.getInstance(RemoteSettingManager.APP_REMOTESETTING_ID);
            if (remoteConfigurationManager == null) {
                log.debug("getSyncRetryTime", "Unable to get configuration manager, returning default value", new Object[0]);
                return DEFAULT_SYNC_RETRY_INTERVAL_MILLIS;
            }
            return SystemClock.elapsedRealtime() + (remoteConfigurationManager.openConfiguration().getAsJsonObject().optLong(KEY_SYNC_RETRY_INTERVAL_MILLIS, DEFAULT_SYNC_RETRY_INTERVAL_MILLIS) << i);
        }
        throw new IllegalArgumentException("The number of attempt may not be negative");
    }

    public static synchronized void initPeriodicConfigSyncing(Context context) {
        synchronized (PeriodicConfigSyncUtils.class) {
            if (isPeriodicConfigSyncingScheduled(context)) {
                log.verbose("initPeriodicConfigSyncing", "Periodic syncing of configurations has already been set. Skipping initialization.", new Object[0]);
                return;
            }
            log.verbose("initPeriodicConfigSyncing", "Initializing periodic syncing of configurations.", new Object[0]);
            schedulePeriodicConfigSyncing(context, SystemClock.elapsedRealtime() + getSyncFirstInterval(), getSyncRepeatInterval());
        }
    }

    private static synchronized boolean isPeriodicConfigSyncingScheduled(Context context) {
        boolean z;
        synchronized (PeriodicConfigSyncUtils.class) {
            z = getPendingIntent(context, new Intent(ConfigurationSyncReceiver.ACTION_PERIODIC_SYNC), 536870912) != null;
        }
        return z;
    }

    private static synchronized void schedulePeriodicConfigSyncing(Context context, long j, long j2) {
        synchronized (PeriodicConfigSyncUtils.class) {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setInexactRepeating(3, j, j2, getPendingIntent(context, new Intent(ConfigurationSyncReceiver.ACTION_PERIODIC_SYNC), 134217728));
        }
    }

    public static synchronized void scheduleSyncRetry(Context context, int i) {
        synchronized (PeriodicConfigSyncUtils.class) {
            log.debug("scheduleSyncRetry", "Scheduling configuration sync retry.", new Object[0]);
            Intent intent = new Intent(ConfigurationSyncReceiver.ACTION_RETRY_SYNC);
            intent.putExtra("com.amazon.communication.remotesetting.extra.SYNC_RETRY_ATTEMPT", i);
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, getSyncRetryTime(i), getPendingIntent(context, intent, 134217728));
        }
    }

    public static synchronized void updatePeriodicConfigSyncingSchedule(Context context) {
        synchronized (PeriodicConfigSyncUtils.class) {
            log.verbose("updatePeriodicConfigSyncingSchedule", "Start - update of periodic configuration syncing.", new Object[0]);
            cancelPeriodicConfigSyncing(context);
            initPeriodicConfigSyncing(context);
            log.verbose("updatePeriodicConfigSyncingSchedule", "End   - update of periodic configuration syncing.", new Object[0]);
            log.info("updatePeriodicConfigSyncingSchedule", "Updated periodic configuration syncing.", new Object[0]);
        }
    }
}
