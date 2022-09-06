package com.amazon.alexa.handsfree.notification.receivers;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.channels.NotificationChannelProperties;
import com.amazon.alexa.handsfree.notification.metrics.MetricType;
import com.amazon.alexa.handsfree.notification.metrics.NotificationChannelMetricMetadata;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class NotificationStateChangeReceiver extends NotificationsBroadcastReceiver {
    private static final String TAG = NotificationStateChangeReceiver.class.getSimpleName();

    public NotificationStateChangeReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        if (!isInitialized(context, MetricType.NOTIFICATION_MODULE_NOT_INITIALIZED_STATE_CHANGE_RECEIVER)) {
            Log.e(TAG, "onReceive: Notification Module not initialized.");
            return;
        }
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("android.app.extra.NOTIFICATION_CHANNEL_ID");
        if ((action != null && !action.equals("android.app.action.NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED")) || !stringExtra.equals(NotificationChannelProperties.HANDS_FREE_SETUP.getChannelId())) {
            return;
        }
        getMetricsBuilderProvider().newBuilder().withClickMetric(TAG, NotificationChannelMetricMetadata.Component.ANDROID_SETTINGS, NotificationChannelMetricMetadata.PageType.HANDS_FREE_NOTIFICATIONS, intent.getBooleanExtra("android.app.extra.BLOCKED_STATE", false) ? NotificationChannelMetricMetadata.Action.BLOCK : NotificationChannelMetricMetadata.Action.UNBLOCK).emit(context);
    }

    @VisibleForTesting
    NotificationStateChangeReceiver(@NonNull Initializer initializer, @NonNull NotificationModule notificationModule) {
        super(initializer, notificationModule);
    }
}
