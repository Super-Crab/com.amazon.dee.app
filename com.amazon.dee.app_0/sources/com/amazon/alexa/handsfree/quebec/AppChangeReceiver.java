package com.amazon.alexa.handsfree.quebec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class AppChangeReceiver extends BroadcastReceiver {
    @VisibleForTesting
    static final String BROADCAST_RECEIVED_SUCCESS = "broadcastReceivedSuccess";
    private static final String TAG = AppChangeReceiver.class.getSimpleName();
    private final Initializer mInitializer;

    public AppChangeReceiver() {
        this(InitializerProvider.getInitializer());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        if (context == null) {
            Log.e(TAG, "onReceive: app change broadcast isn't received properly, context is null.");
            return;
        }
        Log.d(TAG, "onReceive: Initialize via app change broadcast receiver.");
        this.mInitializer.initialize(context);
        MetricsBuilder newBuilder = MetricsBuilderProvider.getInstance(context).newBuilder();
        if (intent == null) {
            Log.e(TAG, "onReceive: app change broadcast isn't received properly, intent is null.");
            newBuilder.withPercentileMetricFailure(TAG, BROADCAST_RECEIVED_SUCCESS);
            newBuilder.emit(context);
            return;
        }
        newBuilder.withPercentileMetricSuccess(TAG, BROADCAST_RECEIVED_SUCCESS);
        if (intent.hasExtra("EXTRA_STATE")) {
            String stringExtra = intent.getStringExtra("EXTRA_STATE");
            GeneratedOutlineSupport1.outline167("onReceive: app state changed: ", stringExtra, TAG);
            newBuilder.withPerformanceMetric(TAG, stringExtra);
        }
        newBuilder.emit(context);
    }

    @VisibleForTesting
    AppChangeReceiver(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
