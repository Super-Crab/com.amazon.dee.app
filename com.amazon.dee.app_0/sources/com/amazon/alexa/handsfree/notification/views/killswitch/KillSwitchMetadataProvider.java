package com.amazon.alexa.handsfree.notification.views.killswitch;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.metrics.MetricType;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.Locale;
import javax.inject.Inject;
/* loaded from: classes8.dex */
class KillSwitchMetadataProvider implements NotificationMetadataProvider {
    private static final String TAG = "KillSwitchMetadataProvider";
    private final ConfigurationProvider mConfigurationProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KillSwitchMetadataProvider() {
        this(NotificationModule.getInstance().getContract().getConfigurationProvider());
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider
    @Nullable
    public NotificationMetadata getNotificationMetadata(@NonNull Context context) {
        MetricsBuilderProvider metricsBuilderProvider = MetricsBuilderProvider.getInstance(context);
        if (!this.mConfigurationProvider.isActive(context, ConfigurationProvider.ConfigComponent.HANDS_FREE)) {
            Log.d(TAG, "Remotely configured to be disabled");
            metricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, String.format(Locale.US, MetricType.HANDSFREE_NOT_ACTIVE.getValue(), "getNotificationMetadata")).emit(context);
            return NotificationMetadata.KILL_SWITCH_ON;
        } else if (!this.mConfigurationProvider.isActive(context, ConfigurationProvider.ConfigComponent.NOTIFICATION)) {
            Log.d(TAG, "Remotely configured notifications turned off");
            metricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, MetricType.NOTIFICATION_NOT_ACTIVE.getValue()).emit(context);
            return null;
        } else {
            return NotificationMetadata.KILL_SWITCH_OFF;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public KillSwitchMetadataProvider(@NonNull ConfigurationProvider configurationProvider) {
        this.mConfigurationProvider = configurationProvider;
    }
}
