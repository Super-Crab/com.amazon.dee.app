package com.amazon.alexa.handsfree.notification.receivers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.metrics.MetricType;
import com.amazon.alexa.handsfree.notification.services.PermissionNotifierService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class PermissionReceiver extends NotificationsBroadcastReceiver {
    private static final String TAG = PermissionReceiver.class.getSimpleName();

    public PermissionReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        Log.d(TAG, "onReceive");
        if (!isInitialized(context, MetricType.NOTIFICATION_MODULE_NOT_INITIALIZED_PERMISSION_RECEIVER)) {
            Log.e(TAG, "onReceive: Notification Module not initialized.");
        } else {
            PermissionNotifierService.enqueueWork(context, intent);
        }
    }

    @VisibleForTesting
    PermissionReceiver(@NonNull Initializer initializer, @NonNull NotificationModule notificationModule) {
        super(initializer, notificationModule);
    }
}
