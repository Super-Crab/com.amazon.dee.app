package com.amazon.alexa.handsfree.notification.views;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.metrics.MetricType;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class NotificationStateProvider {
    private static final String TAG = "NotificationStateProvider";
    private final ConfigurationProvider mConfigurationProvider;

    public NotificationStateProvider() {
        this(NotificationModule.getInstance().getContract().getConfigurationProvider());
    }

    public boolean isNotificationDisabled(@NonNull Context context) {
        MetricsBuilderProvider metricsBuilderProvider = MetricsBuilderProvider.getInstance(context);
        if (!this.mConfigurationProvider.isActive(context, ConfigurationProvider.ConfigComponent.HANDS_FREE)) {
            Log.d(TAG, "Remotely configured to be disabled");
            metricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, MetricType.HANDSFREE_NOT_ACTIVE.getValue()).emit(context);
            return true;
        } else if (this.mConfigurationProvider.isActive(context, ConfigurationProvider.ConfigComponent.NOTIFICATION)) {
            return false;
        } else {
            Log.d(TAG, "Remotely configured notifications turned off");
            metricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, MetricType.NOTIFICATION_NOT_ACTIVE.getValue()).emit(context);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public NotificationStateProvider(@NonNull ConfigurationProvider configurationProvider) {
        this.mConfigurationProvider = configurationProvider;
    }
}
