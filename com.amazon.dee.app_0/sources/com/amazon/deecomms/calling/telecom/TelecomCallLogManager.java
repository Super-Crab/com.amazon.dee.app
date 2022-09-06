package com.amazon.deecomms.calling.telecom;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.telecom.PhoneAccountHandle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
@TargetApi(26)
@Deprecated
/* loaded from: classes12.dex */
public class TelecomCallLogManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TelecomCallLogManager.class);
    private static final long TIMEOUT = 8;
    private static final String TIMEOUT_METADATA = "timeout";
    private final ContentResolver contentResolver;
    private final Context context;
    private final PhoneAccountHandle phoneAccountHandle;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class CallLogObserverDeregistration implements Runnable {
        private final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TelecomCallLogObserver.class);
        final ScheduledExecutorService scheduledExecutorService;
        final String source;
        final TelecomCallLogObserver telecomCallLogObserver;

        public CallLogObserverDeregistration(@NonNull TelecomCallLogObserver telecomCallLogObserver, @NonNull String str, @NonNull ScheduledExecutorService scheduledExecutorService) {
            this.telecomCallLogObserver = telecomCallLogObserver;
            this.source = str;
            this.scheduledExecutorService = scheduledExecutorService;
        }

        @Override // java.lang.Runnable
        public void run() {
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("unregistering call log content content observer: "), this.source, this.LOG);
            TelecomCallLogManager.this.contentResolver.unregisterContentObserver(this.telecomCallLogObserver);
            this.scheduledExecutorService.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class TelecomCallLogObserver extends ContentObserver {
        private ScheduledFuture deregistrationTimeout;
        private final String[] logsParameter;
        private final String logsSelector;
        @VisibleForTesting
        final AtomicBoolean removedEntry;
        @VisibleForTesting
        final AtomicBoolean removingEntriesInProgress;
        private ScheduledExecutorService scheduledExecutorService;

        public TelecomCallLogObserver(@NonNull String str, @NonNull String[] strArr) {
            super(null);
            this.removingEntriesInProgress = new AtomicBoolean();
            this.removedEntry = new AtomicBoolean();
            this.logsSelector = str;
            this.logsParameter = strArr;
        }

        @VisibleForTesting
        AsyncTask<Void, Void, Void> createOnChangeTask(@NonNull final TelecomCallLogObserver telecomCallLogObserver) {
            return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.calling.telecom.TelecomCallLogManager.TelecomCallLogObserver.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    TelecomCallLogManager.LOG.i("onChange called");
                    TelecomCallLogObserver.this.removingEntriesInProgress.set(true);
                    TelecomCallLogObserver telecomCallLogObserver2 = TelecomCallLogObserver.this;
                    int removeCallsFromCallLog = TelecomCallLogManager.this.removeCallsFromCallLog(telecomCallLogObserver2.logsSelector, TelecomCallLogObserver.this.logsParameter);
                    if (removeCallsFromCallLog > 0) {
                        TelecomCallLogObserver.this.removedEntry.set(true);
                        CommsMetric.MetricType metricType = CommsMetric.MetricType.ClickStream;
                        MetricsHelper.recordCounterMetric(new CounterMetric(CommsMetric.MetricType.Operational, MetricKeys.REMOVE_CALL_LOG), Double.valueOf(removeCallsFromCallLog));
                        TelecomCallLogManager.this.contentResolver.unregisterContentObserver(telecomCallLogObserver);
                        CommsLogger commsLogger = TelecomCallLogManager.LOG;
                        commsLogger.i("unregistering call log content observer: entry removed after some time: " + removeCallsFromCallLog + " removed");
                        if (TelecomCallLogObserver.this.deregistrationTimeout != null) {
                            TelecomCallLogObserver.this.deregistrationTimeout.cancel(false);
                            TelecomCallLogObserver.this.scheduledExecutorService.shutdown();
                        }
                    }
                    TelecomCallLogObserver.this.removingEntriesInProgress.set(false);
                    return null;
                }
            };
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            onChange(z, null);
        }

        public void setDeregistrationTimeout(@NonNull ScheduledFuture scheduledFuture, @NonNull ScheduledExecutorService scheduledExecutorService) {
            this.deregistrationTimeout = scheduledFuture;
            this.scheduledExecutorService = scheduledExecutorService;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, @Nullable Uri uri) {
            if (this.removingEntriesInProgress.get() || this.removedEntry.get()) {
                return;
            }
            createOnChangeTask(this).execute(new Void[0]);
        }
    }

    public TelecomCallLogManager(@NonNull PhoneAccountHandle phoneAccountHandle, @NonNull Context context) {
        this.phoneAccountHandle = phoneAccountHandle;
        this.context = context;
        this.contentResolver = context.getContentResolver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int removeCallsFromCallLog(@NonNull String str, @NonNull String[] strArr) {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.WRITE_CALL_LOG") == 0) {
            return this.contentResolver.delete(CallLog.Calls.CONTENT_URI, str, strArr);
        }
        return 0;
    }

    @VisibleForTesting
    AsyncTask<Void, Void, Void> createRemovingCallFromCallLogTask(@NonNull final TelecomCallLogObserver telecomCallLogObserver, @NonNull final String str, @NonNull final String[] strArr) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.calling.telecom.TelecomCallLogManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                TelecomCallLogManager.LOG.i("removing log task started");
                if (ContextCompat.checkSelfPermission(TelecomCallLogManager.this.context, "android.permission.WRITE_CALL_LOG") == 0) {
                    TelecomCallLogManager.this.contentResolver.registerContentObserver(CallLog.Calls.CONTENT_URI, false, telecomCallLogObserver);
                    int removeCallsFromCallLog = TelecomCallLogManager.this.removeCallsFromCallLog(str, strArr);
                    if (removeCallsFromCallLog > 0) {
                        TelecomCallLogManager.this.contentResolver.unregisterContentObserver(telecomCallLogObserver);
                        CommsLogger commsLogger = TelecomCallLogManager.LOG;
                        commsLogger.i("unregistering call log content observer: entry removed right away:  " + removeCallsFromCallLog + " removed.");
                        CommsMetric.MetricType metricType = CommsMetric.MetricType.ClickStream;
                        MetricsHelper.recordCounterMetric(new CounterMetric(CommsMetric.MetricType.Operational, MetricKeys.REMOVE_CALL_LOG), Double.valueOf((double) removeCallsFromCallLog));
                    } else {
                        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                        telecomCallLogObserver.setDeregistrationTimeout(newSingleThreadScheduledExecutor.schedule(new CallLogObserverDeregistration(telecomCallLogObserver, "timeout", newSingleThreadScheduledExecutor), 8L, TimeUnit.SECONDS), newSingleThreadScheduledExecutor);
                    }
                    return null;
                }
                MetricsHelper.recordCounterMetricOperational(MetricKeys.REMOVE_CALL_LOG_PERMISSION_ERROR, 1.0d);
                return null;
            }
        };
    }

    public void removeEntriesFromCallLog() {
        String[] strArr = {this.phoneAccountHandle.getComponentName().flattenToString(), TelecomConstants.PHONE_HANDLE_ID};
        createRemovingCallFromCallLogTask(new TelecomCallLogObserver("subscription_component_name = ? AND subscription_id = ?", strArr), "subscription_component_name = ? AND subscription_id = ?", strArr).execute(new Void[0]);
    }
}
