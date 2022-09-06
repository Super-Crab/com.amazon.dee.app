package com.amazon.alexa.handsfree.metrics.interprocess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.BroadcastingMetricsReporter;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class InterProcessMetricsReceiver extends BroadcastReceiver {
    private static final String TAG = "IPCMetricReceiver";

    void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        InterProcessMetricReportingJobIntentService.enqueueWork(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "intent action: " + action);
        if (BroadcastingMetricsReporter.REPORT_METRIC_DATA_ACTION.equals(action)) {
            enqueueWork(context, intent);
            return;
        }
        Log.w(TAG, "not enqueueing work because we do not handle this intent action: " + action);
    }
}
