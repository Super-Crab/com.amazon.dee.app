package com.amazon.alexa.handsfree.storage.metrics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.storage.dependencies.FalcoStorageComponent;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class EnableMetricsBroadcastReceiver extends BroadcastReceiver {
    public static final String ENABLE_METRICS_BROADCAST = "com.amazon.alexa.handsfree.metrics.ENABLE_METRICS";
    private static final String TAG = "EnableMetricsBrcstRcvr";
    @Inject
    MetricsEnabledStatusStore mMetricsEnabledStatusStore;

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        ((FalcoStorageComponent) AhfComponentsProvider.getComponent(context, FalcoStorageComponent.class)).inject(this);
        Log.i(TAG, "Broadcast received, enabling metrics.");
        this.mMetricsEnabledStatusStore.setEnabled(context, true);
        this.mMetricsEnabledStatusStore.emitMetricsInCache(context);
    }
}
