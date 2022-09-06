package com.amazon.alexa.handsfree.latencyreporter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class LatencyReceiver extends BroadcastReceiver {
    static final String EXTRA_IDENTIFIER = "identifier";
    static final String EXTRA_TIMESTAMP = "timestamp";
    static final String EXTRA_TIMESTAMP_NAME = "timestampName";
    static final String REPORT_TIMESTAMP_DATA_ACTION = "com.amazon.alexa.handsfree.intent.action.REPORT_TIMESTAMP_DATA_EVENT";
    private static final String TAG = LatencyReceiver.class.getSimpleName();
    private final TimestampIntentConverter mTimestampIntentConverter;

    public LatencyReceiver() {
        this(new TimestampIntentConverter());
    }

    void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        LatencyJobIntentService.enqueueWork(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (REPORT_TIMESTAMP_DATA_ACTION.equals(intent.getAction())) {
            enqueueWork(context, this.mTimestampIntentConverter.convertToIntent(intent.getStringExtra(EXTRA_TIMESTAMP_NAME), intent.getStringExtra("identifier"), intent.getLongExtra("timestamp", 0L)));
        } else if (!LatencyReporter.REPORT_LATENCY_DATA_ACTION.equals(intent.getAction())) {
        } else {
            Log.d(TAG, "onReceive: enqueue work to LatencyJobIntentService.");
            enqueueWork(context, intent);
        }
    }

    LatencyReceiver(@NonNull TimestampIntentConverter timestampIntentConverter) {
        this.mTimestampIntentConverter = timestampIntentConverter;
    }
}
