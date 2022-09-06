package com.amazon.alexa.presence.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;
import com.amazon.alexa.presence.FeatureGating;
import com.amazon.alexa.presence.bleconn.identity.IdentityController;
import com.amazon.alexa.presence.bleconn.service.BleConnIdentityComponent;
import com.amazon.alexa.presence.library.Compatibility;
import com.amazon.alexa.presence.library.MetricsRecorder;
import com.amazon.alexa.presence.metrics.MetricsId;
import com.amazon.alexa.presence.metrics.MetricsMethod;
import com.amazon.alexa.presence.service.PresenceForegroundService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes9.dex */
public class PresenceJobService extends JobService {
    public static final String ACTION_REFRESH_FLUSH_KEY = "flush";
    public static final String ACTION_REFRESH_IDENTITY = "com.amazon.alexa.intent.action.PRESENCE_REFRESH_IDENTITY";
    private static final String EXTRAS_ACTION_KEY = "Action";
    private static final int IDENTITY_REFRESHER_JOB_ID = 605002;
    private static final int IDENTITY_REFRESH_CHECK_PERIOD_MS = 3600000;
    private static final String JST_FEATURE_GATE = "JST_DISCOVERABILITY_FEATURE";
    private static final int ROTATOR_JOB_ID = 200506;
    private static final int SERVICE_CONTROL_JOB_ID = 200500;
    private static final int SERVICE_DATA_ROTATION_PERIOD_MS = 1800000;
    private MetricsRecorder metrics;
    private static final String TAG = PresenceJobService.class.getName();
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(4);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            this.metrics = MetricsRecorder.getMetricsUtil();
        } catch (Throwable unused) {
            Log.w(TAG, "Unable to setup metrics service.");
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        final Context applicationContext = getApplicationContext();
        EXECUTOR_SERVICE.submit(new Runnable() { // from class: com.amazon.alexa.presence.service.PresenceJobService.1
            @Override // java.lang.Runnable
            public void run() {
                Boolean valueOf;
                PersistableBundle extras = jobParameters.getExtras();
                String string = extras == null ? null : extras.getString("Action");
                if (string == null) {
                    return;
                }
                String unused = PresenceJobService.TAG;
                String str = "Action = " + string;
                char c = 65535;
                switch (string.hashCode()) {
                    case -1286898152:
                        if (string.equals(PresenceForegroundService.ACTION_STOP_SERVICE)) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1239150356:
                        if (string.equals(PresenceForegroundService.ACTION_START_SERVICE)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1083720306:
                        if (string.equals(PresenceSuggestGuestConnect.PRESENCE_BASED_SUGGESTION)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -444091006:
                        if (string.equals(PresenceForegroundService.ACTION_ROTATE_DATA)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -189340968:
                        if (string.equals(PresenceJobService.ACTION_REFRESH_IDENTITY)) {
                            c = 4;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    new PresenceForegroundService.Controls(applicationContext).notifyPresenceServiceToRun();
                } else if (c == 1) {
                    new PresenceForegroundService.Controls(applicationContext).notifyPresenceServiceToStop();
                } else if (c == 2) {
                    new PresenceForegroundService.Controls(applicationContext).notifyPresenceServiceToRotateServiceData();
                } else if (c != 3) {
                    if (c != 4) {
                        return;
                    }
                    IdentityController identityController = new BleConnIdentityComponent(applicationContext).getIdentityController();
                    Boolean.valueOf(false);
                    if (extras.getBoolean(PresenceJobService.ACTION_REFRESH_FLUSH_KEY, false)) {
                        Log.i(PresenceJobService.TAG, "Executing a hard identity refresh.");
                        valueOf = Boolean.valueOf(identityController.refresh(true));
                    } else {
                        Log.i(PresenceJobService.TAG, "Executing a soft identity refresh.");
                        valueOf = Boolean.valueOf(identityController.refresh(false));
                    }
                    if (!valueOf.booleanValue()) {
                        return;
                    }
                    PresenceJobService.this.metrics.recordCount(MetricsId.IDENTITY_REFRESH, MetricsMethod.PHONE_ID);
                    Log.i(PresenceJobService.TAG, "Identity refreshed, scheduling service data rotation.");
                    new Helper(applicationContext).scheduleServiceDataRotation();
                } else {
                    try {
                        if (!FeatureGating.isEnabled(PresenceJobService.JST_FEATURE_GATE)) {
                            String unused2 = PresenceJobService.TAG;
                        } else {
                            PresenceSuggestGuestConnect presenceSuggestGuestConnect = new PresenceSuggestGuestConnect(applicationContext);
                            PresenceJobService.this.metrics.recordCount(MetricsId.CHECK_FEATURE_SUGGEST, MetricsMethod.GUARDRAILS);
                            if (presenceSuggestGuestConnect.shouldNotify()) {
                                presenceSuggestGuestConnect.sendPresenceFeatureSuggestNotification();
                                PresenceJobService.this.metrics.recordCount(MetricsId.SHOWED_NOTIFICATION, MetricsMethod.GUEST_CONNECT);
                            }
                        }
                    } catch (Exception e) {
                        Log.w(PresenceJobService.TAG, "Feature suggestion failed with exception: " + e);
                    }
                }
            }
        });
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    /* loaded from: classes9.dex */
    public static class Helper {
        private static final String TAG = "com.amazon.alexa.presence.service.PresenceJobService$Helper";
        private Context ctx;
        private JobScheduler jobScheduler;

        public Helper(Context context) {
            this.ctx = context.getApplicationContext();
            this.jobScheduler = (JobScheduler) this.ctx.getSystemService("jobscheduler");
        }

        private JobInfo.Builder jobInfoBuilder(String str, int i) {
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("Action", str);
            return jobInfoBuilder(i, persistableBundle);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Integer lambda$scheduleIdentityRefresh$1() throws Exception {
            return 3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Integer lambda$startMonitoringTokenFreshness$0() throws Exception {
            return 3;
        }

        public void scheduleForegroundServiceStart() {
            this.jobScheduler.schedule(jobInfoBuilder(PresenceForegroundService.ACTION_START_SERVICE, PresenceJobService.SERVICE_CONTROL_JOB_ID).setOverrideDeadline(0L).build());
        }

        public void scheduleForegroundServiceStop() {
            this.jobScheduler.schedule(jobInfoBuilder(PresenceForegroundService.ACTION_STOP_SERVICE, PresenceJobService.SERVICE_CONTROL_JOB_ID).setOverrideDeadline(0L).build());
        }

        public void scheduleIdentityRefresh(boolean z) {
            int intValue = Compatibility.isAndroidNOrLater() ? ((Integer) Compatibility.ifAndroidNOrLater($$Lambda$PresenceJobService$Helper$hCG9UGCp1SFFZ4TCdzEUJl8zq0M.INSTANCE)).intValue() : 1;
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("Action", PresenceJobService.ACTION_REFRESH_IDENTITY);
            persistableBundle.putBoolean(PresenceJobService.ACTION_REFRESH_FLUSH_KEY, z);
            this.jobScheduler.schedule(jobInfoBuilder(PresenceJobService.IDENTITY_REFRESHER_JOB_ID, persistableBundle).setOverrideDeadline(0L).setRequiredNetworkType(intValue).build());
        }

        public void schedulePresenceFeatureSuggestNotification() {
            this.jobScheduler.schedule(jobInfoBuilder(PresenceSuggestGuestConnect.PRESENCE_BASED_SUGGESTION, PresenceSuggestGuestConnect.ROAMING_SUGGESTION_NOTIFICATION_ID).setOverrideDeadline(0L).build());
        }

        public void scheduleServiceDataRotation() {
            Log.i(TAG, "Scheduling on demand service data rotation.");
            this.jobScheduler.schedule(jobInfoBuilder(PresenceForegroundService.ACTION_ROTATE_DATA, PresenceJobService.ROTATOR_JOB_ID).setMinimumLatency(100L).setOverrideDeadline(500L).build());
        }

        public void startMonitoringTokenFreshness() {
            this.jobScheduler.schedule(jobInfoBuilder(PresenceJobService.ACTION_REFRESH_IDENTITY, PresenceJobService.IDENTITY_REFRESHER_JOB_ID).setPeriodic(3600000L).setRequiredNetworkType(Compatibility.isAndroidNOrLater() ? ((Integer) Compatibility.ifAndroidNOrLater($$Lambda$PresenceJobService$Helper$fyXSZ7StVoHl3DcWKKvEapVKg1g.INSTANCE)).intValue() : 1).build());
        }

        public void startRotatingServiceData() {
            this.jobScheduler.schedule(jobInfoBuilder(PresenceForegroundService.ACTION_ROTATE_DATA, PresenceJobService.ROTATOR_JOB_ID).setPeriodic(1800000L).build());
        }

        public void stopMonitoringTokenFreshness() {
            this.jobScheduler.cancel(PresenceJobService.IDENTITY_REFRESHER_JOB_ID);
        }

        public void stopRotatingServiceData() {
            this.jobScheduler.cancel(PresenceJobService.ROTATOR_JOB_ID);
        }

        private JobInfo.Builder jobInfoBuilder(int i, PersistableBundle persistableBundle) {
            return new JobInfo.Builder(i, new ComponentName(this.ctx, PresenceJobService.class)).setExtras(persistableBundle).setRequiredNetworkType(0).setRequiresDeviceIdle(false);
        }
    }
}
