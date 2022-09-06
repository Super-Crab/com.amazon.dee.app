package com.amazon.alexa.handsfree.latencyreporter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.latencyreporter.db.LatencyDbMetricType;
import com.amazon.alexa.handsfree.latencyreporter.db.LatencyStorageHelper;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public class LatencyJobIntentService extends SafeDequeueJobIntentService {
    private static final int JOB_ID = 3204;
    private static final String TAG = LatencyJobIntentService.class.getSimpleName();
    private final Initializer mInitializer;
    private LatencyStorageHelper mLatencyStorageHelper;
    private MetricsBuilderProvider mMetricsBuilderProvider;

    public LatencyJobIntentService() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, LatencyJobIntentService.class, (int) JOB_ID, intent);
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
        this.mLatencyStorageHelper = new LatencyStorageHelper(this);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        if (this.mLatencyStorageHelper == null) {
            Log.w(TAG, "onHandleWork: LatencyDatabaseHelper not initialized.");
            return;
        }
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("latency");
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (parcelableArrayListExtra != null && !parcelableArrayListExtra.isEmpty()) {
            newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATA_RECEIVED.getValue());
            Iterator it2 = parcelableArrayListExtra.iterator();
            while (it2.hasNext()) {
                LatencyTimestamp latencyTimestamp = (LatencyTimestamp) it2.next();
                Latency latency = latencyTimestamp.getLatency();
                TimestampType timestampType = latencyTimestamp.getTimestampType();
                String identifier = latencyTimestamp.getIdentifier();
                long timestamp = latencyTimestamp.getTimestamp();
                try {
                    long queryAndRemovePairedTimestamp = this.mLatencyStorageHelper.queryAndRemovePairedTimestamp(latency, identifier, timestampType);
                    long j = timestampType == TimestampType.START ? queryAndRemovePairedTimestamp - timestamp : timestamp - queryAndRemovePairedTimestamp;
                    if (j < 0) {
                        Log.e(TAG, "onHandleWork: calculated latency smaller than 0.");
                        newBuilder.withPercentileMetricFailure(TAG, String.format(LatencyDbMetricType.VALID_LATENCY.getValue(), latency.name()));
                    } else {
                        newBuilder.withPercentileMetricSuccess(TAG, String.format(LatencyDbMetricType.VALID_LATENCY.getValue(), latency.name()));
                        String str = TAG;
                        Log.i(str, "Latency of [" + latency.name() + "]: " + j);
                        newBuilder.withLatencyMetric(TAG, latency.getMetricName(), j);
                    }
                } catch (NoSuchElementException unused) {
                    this.mLatencyStorageHelper.insertTimestamp(latency, identifier, timestampType, timestamp);
                    this.mLatencyStorageHelper.removeExpiredTimestamp(latency, timestamp);
                }
            }
            newBuilder.emit(this);
            return;
        }
        Log.w(TAG, "onHandleWork: no latency data found");
        newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATA_RECEIVED.getValue());
        newBuilder.emit(this);
    }

    @VisibleForTesting
    LatencyJobIntentService(@NonNull Initializer initializer, @NonNull MetricsBuilderProvider metricsBuilderProvider, @Nullable LatencyStorageHelper latencyStorageHelper) {
        this.mInitializer = initializer;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mLatencyStorageHelper = latencyStorageHelper;
    }
}
