package com.amazon.deecomms.remoteConfig;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public final class PeriodicConfigSyncUtils {
    private static final long DEFAULT_SYNC_REPEAT_INTERVAL_MILLIS = 180000;
    private static final long DEFAULT_SYNC_RETRY_INTERVAL_MILLIS = 180000;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PeriodicConfigSyncUtils.class);

    private PeriodicConfigSyncUtils() {
    }

    public static synchronized void cancelPeriodicConfigSyncing(Context context) {
        synchronized (PeriodicConfigSyncUtils.class) {
            if (!isPeriodicConfigSyncingScheduled(context)) {
                LOG.i("Periodic syncing is not scheduled. Skipping cancellation.");
                return;
            }
            LOG.i("Cancelling periodic configuration syncing.");
            Intent intent = new Intent(context, ConfigurationSyncReceiver.class);
            intent.setAction(ConfigurationSyncReceiver.CONFIG_SYNC_ALARM_ACTION);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 536870912);
            if (broadcast != null) {
                ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(broadcast);
                broadcast.cancel();
            }
        }
    }

    private static long getSyncRepeatInterval() {
        try {
            return CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.ARCUS_SYNC_REPEAT_INTERVAL_MILLISEC).intValue();
        } catch (Exception e) {
            LOG.e("Exception in returning sync repeat interval", e);
            return 180000L;
        }
    }

    private static long getSyncRetryTime(int i) {
        if (i >= 0) {
            try {
                return SystemClock.elapsedRealtime() + (CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.ARCUS_SYNC_RETRY_INTERVAL_MILLISEC).intValue() << i);
            } catch (Exception e) {
                LOG.e("Exception in returning sync retry time", e);
                return 180000L;
            }
        }
        throw new IllegalArgumentException("The number of attempt may not be negative");
    }

    public static synchronized void initPeriodicConfigSyncing(Context context) {
        synchronized (PeriodicConfigSyncUtils.class) {
            if (isPeriodicConfigSyncingScheduled(context)) {
                LOG.i("Periodic syncing of configurations has already been set. Skipping initialization.");
                return;
            }
            LOG.i("Initializing periodic syncing of configurations.");
            long syncRepeatInterval = getSyncRepeatInterval();
            schedulePeriodicConfigSyncing(context, SystemClock.elapsedRealtime() + syncRepeatInterval, syncRepeatInterval);
        }
    }

    private static synchronized boolean isPeriodicConfigSyncingScheduled(Context context) {
        boolean z;
        synchronized (PeriodicConfigSyncUtils.class) {
            Intent intent = new Intent(context, ConfigurationSyncReceiver.class);
            intent.setAction(ConfigurationSyncReceiver.CONFIG_SYNC_ALARM_ACTION);
            z = false;
            if (PendingIntent.getBroadcast(context, 0, intent, 536870912) != null) {
                z = true;
            }
        }
        return z;
    }

    private static synchronized void schedulePeriodicConfigSyncing(Context context, long j, long j2) {
        synchronized (PeriodicConfigSyncUtils.class) {
            Intent intent = new Intent(context, ConfigurationSyncReceiver.class);
            intent.setAction(ConfigurationSyncReceiver.CONFIG_SYNC_ALARM_ACTION);
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setInexactRepeating(3, j, j2, PendingIntent.getBroadcast(context, 0, intent, 134217728));
        }
    }

    public static synchronized void scheduleSyncRetry(Context context, int i) {
        synchronized (PeriodicConfigSyncUtils.class) {
            CommsLogger commsLogger = LOG;
            commsLogger.i("Scheduling configuration sync retry. ATTEMPT NUMBER: " + i);
            Intent intent = new Intent(ConfigurationSyncReceiver.ACTION_RETRY_SYNC);
            intent.putExtra(ConfigurationSyncService.EXTRA_RETRY_ATTEMPT, i);
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, getSyncRetryTime(i), PendingIntent.getBroadcast(context, 0, intent, 134217728));
        }
    }

    public static synchronized void updatePeriodicConfigSyncingSchedule(Context context) {
        synchronized (PeriodicConfigSyncUtils.class) {
            cancelPeriodicConfigSyncing(context);
            initPeriodicConfigSyncing(context);
            LOG.i("Updated periodic configuration syncing.");
        }
    }
}
