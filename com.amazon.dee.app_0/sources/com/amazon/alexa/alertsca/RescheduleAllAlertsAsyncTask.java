package com.amazon.alexa.alertsca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.AsyncTask;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.alertsca.AlertsAnalyzer;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class RescheduleAllAlertsAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = RescheduleAllAlertsAsyncTask.class.getSimpleName();
    private final AlertsAnalyzer alertsAnalyzer;
    private final AlertsScheduler alertsScheduler;
    private final AlertsStore alertsStore;
    private final Context context;
    private final ExpireHandler expireHandler;
    private final LaunchHandler launchHandler;
    private final BroadcastReceiver.PendingResult pendingResult;
    private final ScheduleHandler scheduleHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ExpireHandler implements AlertsAnalyzer.AlertExpireHandler {
        private ExpireHandler() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertExpireHandler
        public void onExpire(AlertRecord alertRecord) {
            String unused = RescheduleAllAlertsAsyncTask.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlertRecord has expired. Will clean up when AlexaService runs again. token: ");
            outline107.append(alertRecord.getToken());
            outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class LaunchHandler implements AlertsAnalyzer.AlertLaunchHandler {
        private LaunchHandler() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertLaunchHandler
        public void onLaunch(AlertRecord alertRecord) {
            ContextCompat.startForegroundService(RescheduleAllAlertsAsyncTask.this.context, AlertsIntentFactory.createIntent(RescheduleAllAlertsAsyncTask.this.context, alertRecord));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ScheduleHandler implements AlertsAnalyzer.AlertScheduledHandler {
        private ScheduleHandler() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertScheduledHandler
        public void onSchedule(AlertRecord alertRecord) {
            RescheduleAllAlertsAsyncTask.this.alertsScheduler.schedule(RescheduleAllAlertsAsyncTask.this.context, alertRecord, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RescheduleAllAlertsAsyncTask(Context context, AlertsStore alertsStore, AlertsAnalyzer alertsAnalyzer, AlertsScheduler alertsScheduler, BroadcastReceiver.PendingResult pendingResult) {
        Preconditions.notNull(context, "Context is null");
        Preconditions.notNull(alertsStore, "AlertsStore is null");
        Preconditions.notNull(alertsAnalyzer, "AlertsAnalyzer is null");
        Preconditions.notNull(alertsScheduler, "AlertsScheduler is null");
        Preconditions.notNull(pendingResult, "PendingResult is null");
        this.context = context;
        this.alertsStore = alertsStore;
        this.alertsAnalyzer = alertsAnalyzer;
        this.alertsScheduler = alertsScheduler;
        this.pendingResult = pendingResult;
        this.scheduleHandler = new ScheduleHandler();
        this.expireHandler = new ExpireHandler();
        this.launchHandler = new LaunchHandler();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... voidArr) {
        for (AlertRecord alertRecord : this.alertsStore.getAll()) {
            this.alertsAnalyzer.analyze(alertRecord, this.scheduleHandler, this.launchHandler, this.expireHandler);
        }
        this.pendingResult.finish();
        return null;
    }
}
