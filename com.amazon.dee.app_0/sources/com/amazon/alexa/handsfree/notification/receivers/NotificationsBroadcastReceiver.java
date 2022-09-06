package com.amazon.alexa.handsfree.notification.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.metrics.MetricType;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
/* loaded from: classes8.dex */
public abstract class NotificationsBroadcastReceiver extends BroadcastReceiver {
    @VisibleForTesting
    static final String TAG = NotificationsBroadcastReceiver.class.getSimpleName();
    private final Initializer mInitializer;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private final NotificationModule mNotificationModule;

    /* JADX INFO: Access modifiers changed from: protected */
    public NotificationsBroadcastReceiver() {
        this(InitializerProvider.getInitializer(), NotificationModule.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MetricsBuilderProvider getMetricsBuilderProvider() {
        return this.mMetricsBuilderProvider;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isInitialized(@NonNull Context context, @NonNull MetricType metricType) {
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(context);
        this.mInitializer.initialize(context);
        if (this.mNotificationModule.isInitialized()) {
            this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, metricType.getValue()).emit(context);
            return true;
        }
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, metricType.getValue()).emit(context);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public NotificationsBroadcastReceiver(Initializer initializer, NotificationModule notificationModule) {
        this.mInitializer = initializer;
        this.mNotificationModule = notificationModule;
    }
}
