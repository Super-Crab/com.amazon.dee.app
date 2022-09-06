package com.amazon.alexa.handsfree.latencyreporter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class LatencyReporter {
    public static final String EXTRA_LATENCY_DATA = "latency";
    static final String LATENCY_RECEIVER_CLASS_NAME = "com.amazon.alexa.handsfree.latencyreporter.LatencyReceiver";
    static final String LATENCY_RECEIVER_PACKAGE_NAME = "com.amazon.dee.app";
    static final String LATENCY_RECEIVER_PERMISSION = "com.amazon.alexa.handsfree.latencyreporter.LATENCY_PERMISSION";
    public static final String REPORT_LATENCY_DATA_ACTION = "com.amazon.alexa.handsfree.intent.action.REPORT_LATENCY_DATA_EVENT";
    private static final String TAG = "LatencyReporter";
    private final Context mContext;
    private final ArrayList<LatencyTimestamp> mLatencyTimestampArrayList;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LatencyReporter(@NonNull Context context, @NonNull ArrayList<LatencyTimestamp> arrayList) {
        this.mContext = context;
        this.mLatencyTimestampArrayList = arrayList;
    }

    public void report() {
        Intent intent = new Intent(REPORT_LATENCY_DATA_ACTION);
        intent.setComponent(new ComponentName("com.amazon.dee.app", LATENCY_RECEIVER_CLASS_NAME));
        intent.putParcelableArrayListExtra("latency", this.mLatencyTimestampArrayList);
        Log.d(TAG, "Send latency data broadcast");
        this.mContext.sendBroadcast(intent, "com.amazon.alexa.handsfree.latencyreporter.LATENCY_PERMISSION");
    }
}
