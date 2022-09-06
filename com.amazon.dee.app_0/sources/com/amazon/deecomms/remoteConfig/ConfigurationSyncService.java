package com.amazon.deecomms.remoteConfig;

import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.auth.Stage;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.CommsJobIntentService;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Preconditions;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ConfigurationSyncService extends CommsJobIntentService {
    public static final String ACTION_SYNC_CONFIG = "com.amazon.remoteconfig.demo.action.SYNC_CONFIG";
    public static final String EXTRA_RETRY_ATTEMPT = "com.amazon.deecomms.receiver.SYNC_RETRY_ATTEMPT";
    public static final String PREF_LAST_SUCCESSFUL_SYNC = "com.amazon.deecomms.pref.LAST_SUCCESSFUL_SYNC";
    public static final String PREF_LAST_SYNC_ATTEMPT = "com.amazon.deecomms.pref.LAST_SYNC_ATTEMPT";
    private static final String SUCCESSFUL_MODIFIED = "successful_modified";
    private static final String SUCCESSFUL_UNMODIFIED = "successful_unmodified";
    @Inject
    CommsConnectivityMonitor commsConnectivityMonitor;
    @Inject
    Stage stage;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConfigurationSyncService.class);
    private static final int JOB_ID = CommsJobIntentService.generateJobId(ConfigurationSyncService.class);
    private static final AtomicBoolean mJobRunning = new AtomicBoolean(false);

    /* loaded from: classes12.dex */
    private static class SyncServiceConfigurationCallback implements ConfigurationSyncCallback {
        private final int mAttemptNumber;
        private final Context mContext;
        final CountDownLatch mLatch;
        private final int mMaxAttempts;

        public SyncServiceConfigurationCallback(@NonNull Context context, @NonNull CountDownLatch countDownLatch, int i, int i2) {
            Preconditions.checkNotNull(context, "The Context may not be null.");
            Preconditions.checkNotNull(countDownLatch, "The CountDownLatch may not be null");
            this.mContext = context.getApplicationContext();
            this.mLatch = countDownLatch;
            this.mAttemptNumber = i;
            this.mMaxAttempts = i2;
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onConfigurationModified(Configuration configuration) {
            ConfigurationSyncService.LOG.i("Configuration was modified");
            ConfigurationSyncService.registerLastSyncTime(this.mContext, true);
            PeriodicConfigSyncUtils.updatePeriodicConfigSyncingSchedule(this.mContext);
            this.mLatch.countDown();
            ConfigurationSyncService.stopArcusLatencyTimer();
            ConfigurationSyncService.recordSyncMetric(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, ConfigurationSyncService.SUCCESSFUL_MODIFIED, null);
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onConfigurationUnmodified(Configuration configuration) {
            ConfigurationSyncService.LOG.i("Configuration has not been modified on the server");
            ConfigurationSyncService.registerLastSyncTime(this.mContext, true);
            this.mLatch.countDown();
            ConfigurationSyncService.stopArcusLatencyTimer();
            ConfigurationSyncService.recordSyncMetric(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, ConfigurationSyncService.SUCCESSFUL_UNMODIFIED, null);
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onFailure(Exception exc) {
            ConfigurationSyncService.LOG.i("Failure to sync configuration", exc);
            ConfigurationSyncService.registerLastSyncTime(this.mContext, false);
            ConfigurationSyncService.scheduleSyncRetry(this.mContext, this.mAttemptNumber, this.mMaxAttempts);
            this.mLatch.countDown();
            ConfigurationSyncService.stopArcusLatencyTimer();
            ConfigurationSyncService.recordSyncMetric(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 1.0d, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, "error", " " + exc + " cause: " + exc.getCause());
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onThrottle(long j) {
            ConfigurationSyncService.LOG.i("Configuration Sync request was throttled");
            ConfigurationSyncService.registerLastSyncTime(this.mContext, false);
            ConfigurationSyncService.scheduleSyncRetry(this.mContext, this.mAttemptNumber, this.mMaxAttempts);
            this.mLatch.countDown();
            ConfigurationSyncService.stopArcusLatencyTimer();
            ConfigurationSyncService.recordSyncMetric(1.0d, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, null, null);
        }
    }

    public ConfigurationSyncService() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    private static boolean arcusSyncRequired() {
        ArcusConfig arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
        Context context = CommsDaggerWrapper.getComponent().getContext();
        long intValue = arcusConfig.getConfigInteger(RemoteConfigKeys.ARCUS_SYNC_REPEAT_INTERVAL_MILLISEC).intValue();
        boolean isLastAttemptSyncCurrent = isLastAttemptSyncCurrent(context, arcusConfig.getConfigInteger(RemoteConfigKeys.ARCUS_SYNC_RETRY_INTERVAL_MILLISEC).intValue());
        boolean isLastSuccessfulSyncCurrent = isLastSuccessfulSyncCurrent(context, intValue);
        GeneratedOutlineSupport.outline5("lastAttemptedSyncIsRecent ", isLastAttemptSyncCurrent, LOG);
        GeneratedOutlineSupport.outline5("lastSuccessfulSyncIsRecent ", isLastSuccessfulSyncCurrent, LOG);
        return !isLastAttemptSyncCurrent && !isLastSuccessfulSyncCurrent;
    }

    public static synchronized void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        synchronized (ConfigurationSyncService.class) {
            if (!arcusSyncRequired()) {
                LOG.i("Configuration Sync Service work not enqueued, Configuration Sync was recently completed.");
            } else if (!mJobRunning.compareAndSet(false, true)) {
                LOG.i("Configuration Sync Service work not enqueued, job is already running.");
            } else {
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
                int size = jobScheduler.getAllPendingJobs().size();
                CommsLogger commsLogger = LOG;
                commsLogger.d("Number of scheduled jobs: " + size);
                if (size >= 99) {
                    jobScheduler.cancelAll();
                }
                JobIntentService.enqueueWork(context, ConfigurationSyncService.class, JOB_ID, intent);
            }
        }
    }

    private static boolean isLastAttemptSyncCurrent(Context context, long j) {
        boolean z = j > System.currentTimeMillis() - PreferenceManager.getDefaultSharedPreferences(context).getLong(PREF_LAST_SYNC_ATTEMPT, 0L);
        CommsLogger commsLogger = LOG;
        commsLogger.d("Last sync attempt is still recent: " + z);
        return z;
    }

    private static boolean isLastSuccessfulSyncCurrent(Context context, long j) {
        boolean z = j > System.currentTimeMillis() - PreferenceManager.getDefaultSharedPreferences(context).getLong(PREF_LAST_SUCCESSFUL_SYNC, 0L);
        CommsLogger commsLogger = LOG;
        commsLogger.d("Last successful sync is still current: " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordSyncMetric(double d, double d2, double d3, @Nullable String str, @Nullable String str2) {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.ARCUS_LOAD_CANCEL);
        CounterMetric generateClickstream2 = CounterMetric.generateClickstream(MetricKeys.ARCUS_LOAD_FAIL);
        CounterMetric generateClickstream3 = CounterMetric.generateClickstream(MetricKeys.ARCUS_LOAD_NETWORK);
        if (str != null) {
            generateClickstream.getMetadata().put("source", str);
            generateClickstream2.getMetadata().put("source", str);
            generateClickstream3.getMetadata().put("source", str);
        }
        if (str2 != null) {
            generateClickstream.getMetadata().put("errorSource", str2);
            generateClickstream2.getMetadata().put("errorSource", str2);
            generateClickstream3.getMetadata().put("errorSource", str2);
        }
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(d));
        MetricsHelper.recordCounterMetric(generateClickstream2, Double.valueOf(d2));
        MetricsHelper.recordCounterMetric(generateClickstream3, Double.valueOf(d3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void registerLastSyncTime(Context context, boolean z) {
        LOG.i("register last sync time");
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
        edit.putLong(PREF_LAST_SYNC_ATTEMPT, currentTimeMillis);
        if (z) {
            edit.putLong(PREF_LAST_SUCCESSFUL_SYNC, currentTimeMillis);
        }
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void scheduleSyncRetry(Context context, int i, int i2) {
        int i3 = i + 1;
        if (i3 <= i2) {
            LOG.i("Scheduling retry...");
            CommsLogger commsLogger = LOG;
            commsLogger.i("Next attempt number is: " + i3);
            PeriodicConfigSyncUtils.scheduleSyncRetry(context, i3);
            return;
        }
        LOG.w("Retry limit reached, no more retries scheduled. Waiting for next regular sync.");
    }

    public static void startService(Context context) {
        enqueueWork(context, new Intent(context, ConfigurationSyncService.class));
        PeriodicConfigSyncUtils.initPeriodicConfigSyncing(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void stopArcusLatencyTimer() {
        MetricsHelper.stopTimerMetric(TimerMetric.generateOperational(MetricKeys.ARCUS_LOAD_LATENCY));
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        try {
            try {
                try {
                    LOG.i("Reached Configuration Sync Service");
                } catch (RuntimeException e) {
                    LOG.e("Configuration Sync Service encountered a runtime error");
                    recordSyncMetric(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 1.0d, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, MetricKeys.VALUE_ARCUS_FAILED_TO_INITIALIZE_EXCEPTION, e.toString());
                }
            } catch (InterruptedException e2) {
                LOG.e("Configuration Sync Service - thread interrupted while waiting for the sync to end.", e2);
            }
            if (!arcusSyncRequired()) {
                LOG.i("Not trying to resync, Configuration Sync was recently completed.");
                return;
            }
            getApplicationContext();
            LOG.i("Trying to resync");
            if (!this.commsConnectivityMonitor.isConnected()) {
                LOG.w("Skipping this ARCUS sync attempt, due to lack of network access");
                recordSyncMetric(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 1.0d, MetricKeys.VALUE_ARCUS_SYNC_SKIPPED_NO_NETWORK, null);
                return;
            }
            RemoteConfigurationManager remoteConfigurationManager = RemoteConfigurationManager.getInstance(this.stage.getArcusConfigId());
            if (remoteConfigurationManager == null) {
                LOG.w("Remote config manager not initialized prior to sync");
                return;
            }
            int intExtra = intent.getIntExtra(EXTRA_RETRY_ATTEMPT, 0);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ArcusConfig arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
            MetricsHelper.startTimerMetric(TimerMetric.generateOperational(MetricKeys.ARCUS_LOAD_LATENCY));
            remoteConfigurationManager.sync(new SyncServiceConfigurationCallback(getApplicationContext(), countDownLatch, intExtra, arcusConfig.getConfigInteger(RemoteConfigKeys.ARCUS_RETY_MAX_ATTEMPTS).intValue()));
            if (!countDownLatch.await(arcusConfig.getConfigInteger(RemoteConfigKeys.ARCUS_MAX_SYNC_DURATION_MILLISEC).intValue(), TimeUnit.MILLISECONDS)) {
                LOG.e("Timed out while waiting for the sync to end.");
            } else {
                LOG.i("Configuration Sync Service success");
            }
        } finally {
            LOG.i("Configuration Sync Service job completed.");
            mJobRunning.set(false);
        }
    }
}
