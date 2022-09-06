package com.amazon.communication.remotesetting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import com.amazon.communication.support.JobIntentService;
import com.amazon.dp.logger.DPLogger;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class ConfigurationSyncService extends JobIntentService {
    public static final String ACTION_SYNC_CONFIG = "com.amazon.communication.remotesetting.action.SYNC_CONFIG";
    public static final String EXTRA_RETRY_ATTEMPT = "com.amazon.communication.remotesetting.extra.SYNC_RETRY_ATTEMPT";
    public static final String PREF_LAST_SUCCESSFUL_SYNC = "com.amazon.communication.remotesetting.pref.LAST_SUCCESSFUL_SYNC";
    public static final String PREF_LAST_SYNC_ATTEMPT = "com.amazon.communication.remotesetting.pref.LAST_SYNC_ATTEMPT";
    public static final String PREF_SYNC_ENABLED = "com.amazon.communication.remoteconfig.pref.LAST_SYNC_ENABLED";
    private static final DPLogger log = new DPLogger("TComm.ConfigurationSyncService");
    private static final String WORKER_THREAD_NAME = ConfigurationSyncService.class.getSimpleName();
    private static RemoteConfigurationManager mRemoteConfigurationManager = null;
    private static List<SettingUpdateListener> mSettingUpdateListeners = new ArrayList();
    private static final Object mListenersLock = new Object();

    /* loaded from: classes12.dex */
    private static class SyncServiceConfigurationCallback implements ConfigurationSyncCallback {
        private static final DPLogger log = new DPLogger("SyncServiceConfigurationCallback");
        private final int mAttemptNumber;
        private final Context mContext;
        final CountDownLatch mLatch;
        private final int mMaxAttempts;

        public SyncServiceConfigurationCallback(Context context, CountDownLatch countDownLatch, int i, int i2) {
            if (context != null) {
                if (countDownLatch != null) {
                    this.mContext = context.getApplicationContext();
                    this.mLatch = countDownLatch;
                    this.mAttemptNumber = i;
                    this.mMaxAttempts = i2;
                    return;
                }
                throw new NullPointerException("The CountDownLatch may not be null");
            }
            throw new NullPointerException("The Context may not be null.");
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onConfigurationModified(Configuration configuration) {
            log.debug("syncCallback", "Configuraton was modified", new Object[0]);
            DPLogger dPLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Configuraton received: ");
            outline107.append(configuration.getAsJsonString());
            dPLogger.debug("syncCallback", outline107.toString(), new Object[0]);
            RemoteSettingManager.setCachedConfiguration(configuration);
            RemoteSettingManager.setCachedMap();
            ConfigurationSyncService.registerLastSyncTime(this.mContext, true);
            PeriodicConfigSyncUtils.updatePeriodicConfigSyncingSchedule(this.mContext);
            ConfigurationSyncService.updateListeners();
            this.mLatch.countDown();
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onConfigurationUnmodified(Configuration configuration) {
            log.debug("syncCallback", "Configuration has not been modified on the server", new Object[0]);
            DPLogger dPLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Configuration received: ");
            outline107.append(configuration.getAsJsonString());
            dPLogger.debug("syncCallback", outline107.toString(), new Object[0]);
            ConfigurationSyncService.registerLastSyncTime(this.mContext, true);
            ConfigurationSyncService.notifySettingUnchanged();
            this.mLatch.countDown();
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onFailure(Exception exc) {
            log.debug("syncCallback", "Failure to sync", exc);
            ConfigurationSyncService.registerLastSyncTime(this.mContext, false);
            ConfigurationSyncService.scheduleSyncRetry(this.mContext, this.mAttemptNumber, this.mMaxAttempts);
            ConfigurationSyncService.notifySyncFailed();
            this.mLatch.countDown();
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onThrottle(long j) {
            log.debug("syncCallback", "Sync request was throttled", new Object[0]);
            ConfigurationSyncService.registerLastSyncTime(this.mContext, false);
            ConfigurationSyncService.scheduleSyncRetry(this.mContext, this.mAttemptNumber, this.mMaxAttempts);
            ConfigurationSyncService.notifySyncFailed();
            this.mLatch.countDown();
        }
    }

    public static void addSettingUpdateListener(SettingUpdateListener settingUpdateListener) {
        synchronized (mListenersLock) {
            mSettingUpdateListeners.add(settingUpdateListener);
        }
    }

    private boolean isLastSuccessfulSyncCurrent(long j) {
        long j2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong(PREF_LAST_SUCCESSFUL_SYNC, 0L);
        boolean z = j > System.currentTimeMillis() - j2;
        DPLogger dPLogger = log;
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Last sync time: ", j2, "Current time: ");
        outline111.append(System.currentTimeMillis());
        dPLogger.debug("isLastSuccessfulSyncCurrent", outline111.toString(), new Object[0]);
        DPLogger dPLogger2 = log;
        dPLogger2.debug("isLastSuccessfulSyncCurrent", "Last successful sync is still current: " + z, new Object[0]);
        return z;
    }

    private boolean isLastSyncAttemptRecent(long j) {
        long j2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong(PREF_LAST_SYNC_ATTEMPT, 0L);
        boolean z = j > System.currentTimeMillis() - j2;
        DPLogger dPLogger = log;
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Last sync time: ", j2, "Current time: ");
        outline111.append(System.currentTimeMillis());
        dPLogger.debug("isLastSyncAttemptRecent", outline111.toString(), new Object[0]);
        DPLogger dPLogger2 = log;
        dPLogger2.debug("isLastSyncAttemptRecent", "Last sync attempt is still recent: " + z, new Object[0]);
        return z;
    }

    public static boolean isSyncEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getBoolean(PREF_SYNC_ENABLED, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifySettingUnchanged() {
        Handler handler = new Handler(Looper.getMainLooper());
        synchronized (mListenersLock) {
            for (final SettingUpdateListener settingUpdateListener : mSettingUpdateListeners) {
                if (settingUpdateListener == null) {
                    log.info("notifySettingUnchanged", "update Listener- Null Listener.", new Object[0]);
                } else {
                    handler.post(new Runnable() { // from class: com.amazon.communication.remotesetting.ConfigurationSyncService.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SettingUpdateListener.this.onSettingUnchanged();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifySyncFailed() {
        Handler handler = new Handler(Looper.getMainLooper());
        synchronized (mListenersLock) {
            for (final SettingUpdateListener settingUpdateListener : mSettingUpdateListeners) {
                if (settingUpdateListener == null) {
                    log.info("notifySyncFailed", "update Listener- Null Listener.", new Object[0]);
                } else {
                    handler.post(new Runnable() { // from class: com.amazon.communication.remotesetting.ConfigurationSyncService.3
                        @Override // java.lang.Runnable
                        public void run() {
                            SettingUpdateListener.this.onSyncFailed();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void registerLastSyncTime(Context context, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
        edit.putLong(PREF_LAST_SYNC_ATTEMPT, currentTimeMillis);
        if (z) {
            log.info("registerLastSyncTime", GeneratedOutlineSupport1.outline56("Update successful sync time to ", currentTimeMillis), new Object[0]);
            edit.putLong(PREF_LAST_SUCCESSFUL_SYNC, currentTimeMillis);
        }
        edit.commit();
    }

    public static void removeSettingUpdateListener(SettingUpdateListener settingUpdateListener) {
        synchronized (mListenersLock) {
            mSettingUpdateListeners.remove(settingUpdateListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void scheduleSyncRetry(Context context, int i, int i2) {
        int i3 = i + 1;
        if (i3 <= i2) {
            PeriodicConfigSyncUtils.scheduleSyncRetry(context, i3);
        } else {
            log.warn("scheduleSyncRetry", "Retry limit reached, no more retries scheduled. Waiting for next regular sync.", new Object[0]);
        }
    }

    public static void setRemoteConfigurationManager(RemoteConfigurationManager remoteConfigurationManager) {
        mRemoteConfigurationManager = remoteConfigurationManager;
    }

    public static void setSyncEnabled(Context context, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
        edit.putBoolean(PREF_SYNC_ENABLED, z);
        edit.commit();
        DPLogger dPLogger = log;
        dPLogger.info("setSyncEnabled", "Configuration syncing enabled set to: " + z, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateListeners() {
        Handler handler = new Handler(Looper.getMainLooper());
        synchronized (mListenersLock) {
            for (final SettingUpdateListener settingUpdateListener : mSettingUpdateListeners) {
                if (settingUpdateListener == null) {
                    log.info("updateListeners", "update Listener- Null Listener.", new Object[0]);
                } else {
                    handler.post(new Runnable() { // from class: com.amazon.communication.remotesetting.ConfigurationSyncService.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SettingUpdateListener.this.onSettingUpdated();
                        }
                    });
                }
            }
        }
    }

    @Override // com.amazon.communication.support.JobIntentService
    protected void onHandleWork(Intent intent) {
        if (!isSyncEnabled(getApplicationContext())) {
            log.info("onHandleIntent", "Remote settings sync is not enabled, skipping sync attempt.", new Object[0]);
            return;
        }
        RemoteConfigurationManager remoteConfigurationManager = mRemoteConfigurationManager;
        log.info("onHandleIntent", "SyncRequestIsReceived.", new Object[0]);
        if (remoteConfigurationManager == null) {
            log.error("onHandleIntent", "RemoteConfigurationManager is not set up yet.", new Object[0]);
            return;
        }
        JSONObject asJsonObject = remoteConfigurationManager.openConfiguration().getAsJsonObject();
        long optLong = asJsonObject.optLong(PeriodicConfigSyncUtils.KEY_SYNC_RETRY_INTERVAL_MILLIS, PeriodicConfigSyncUtils.DEFAULT_SYNC_RETRY_INTERVAL_MILLIS);
        long optLong2 = asJsonObject.optLong(PeriodicConfigSyncUtils.KEY_SYNC_REPEAT_INTERVAL_MILLIS, 86400000L);
        int optInt = asJsonObject.optInt(PeriodicConfigSyncUtils.KEY_SYNC_RETRY_ATTEMPT_LIMIT, 3);
        long optLong3 = asJsonObject.optLong(PeriodicConfigSyncUtils.KEY_SYNC_MAX_DURATION_MILLIS, PeriodicConfigSyncUtils.DEFAULT_SYNC_MAX_DURATION_MILLIS);
        if (!isLastSyncAttemptRecent(optLong) && !isLastSuccessfulSyncCurrent(optLong2)) {
            log.info("onHandleIntent", "Began Syncing...", new Object[0]);
            int intExtra = intent.getIntExtra("com.amazon.communication.remotesetting.extra.SYNC_RETRY_ATTEMPT", 0);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            remoteConfigurationManager.sync(new SyncServiceConfigurationCallback(getApplicationContext(), countDownLatch, intExtra, optInt));
            try {
                if (countDownLatch.await(optLong3, TimeUnit.MILLISECONDS)) {
                    return;
                }
                log.error("onHandleIntent", "Timed out while waiting for the sync to end.", new Object[0]);
                return;
            } catch (InterruptedException e) {
                log.error("onHandleIntent", "Worker thread interrupted while waiting for the sync to end.", e);
                return;
            }
        }
        log.info("onHandleIntent", "Skipping premature sync attempt.", new Object[0]);
    }
}
