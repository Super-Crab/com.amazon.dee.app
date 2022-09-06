package com.amazon.alexa.handsfree.notification.receivers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.metrics.MetricType;
import com.amazon.alexa.handsfree.notification.services.UtteranceNotifierService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class UtteranceReceiver extends NotificationsBroadcastReceiver {
    private static final String TAG = UtteranceReceiver.class.getSimpleName();

    public UtteranceReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (!isInitialized(context, MetricType.NOTIFICATION_MODULE_NOT_INITIALIZED_UTTERANCE_RECEIVER)) {
            Log.e(TAG, "onReceive: Notification Module not initialized.");
        } else {
            UtteranceNotifierService.enqueueWork(context, intent);
        }
    }

    @VisibleForTesting
    UtteranceReceiver(@NonNull Initializer initializer, @NonNull NotificationModule notificationModule, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        super(initializer, notificationModule);
    }
}
