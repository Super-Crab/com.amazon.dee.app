package com.amazon.alexa.handsfree.protocols.metrics.interprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.operational.IPCPercentileMetric;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.operational.IPCPerformanceMetric;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public abstract class BroadcastingMetricsReporter extends InterProcessMetricReporter {
    @VisibleForTesting
    static final String METRIC_RECEIVER_CLASS_NAME = "com.amazon.alexa.handsfree.metrics.interprocess.InterProcessMetricsReceiver";
    @VisibleForTesting
    static final String RECEIVER_PACKAGE_NAME = "com.amazon.dee.app";
    public static final String REPORT_METRIC_DATA_ACTION = "com.amazon.alexa.handsfree.intent.action.REPORT_IPC_METRIC";
    private static final String TAG = "BroadcastMetricsReport";
    private final Context mContext;

    public BroadcastingMetricsReporter(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.interprocess.InterProcessMetricReporter
    public void sendMetric(@NonNull InterProcessMetric interProcessMetric) {
        Intent intent = new Intent(REPORT_METRIC_DATA_ACTION);
        intent.setComponent(new ComponentName("com.amazon.dee.app", METRIC_RECEIVER_CLASS_NAME));
        intent.putExtra(InterProcessMetricReporter.PAYLOAD_IDENTIFIER, interProcessMetric);
        Log.i(TAG, "Sending metric broadcast...");
        this.mContext.sendBroadcast(intent);
    }

    public void sendPercentileMetric(@NonNull String str, @NonNull String str2, boolean z) {
        Log.i(TAG, "emitting percentile metric with event name " + str + ", and source " + str2);
        sendMetric(new IPCPercentileMetric(str, str2, z ? 1 : 0));
    }

    public void sendPerformanceMetric(@NonNull String str, @NonNull String str2) {
        Log.i(TAG, "emitting performance metric with event name " + str + ", and source " + str2);
        sendMetric(new IPCPerformanceMetric(str, str2));
    }
}
