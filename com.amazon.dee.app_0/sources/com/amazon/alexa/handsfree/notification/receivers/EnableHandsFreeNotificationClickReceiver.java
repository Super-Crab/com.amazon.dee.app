package com.amazon.alexa.handsfree.notification.receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.R;
import com.amazon.alexa.handsfree.notification.metrics.NotificationClickMetricMetadata;
import com.amazon.alexa.handsfree.notification.metrics.NotificationMetricReporter;
import com.amazon.alexa.handsfree.notification.services.EnableHandsFreeService;
import com.amazon.alexa.handsfree.notification.views.enablehandsfree.EnableHandsFreeNotification;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class EnableHandsFreeNotificationClickReceiver extends BroadcastReceiver {
    private static final String TAG = EnableHandsFreeNotificationClickReceiver.class.getSimpleName();
    private NotificationMetricReporter mNotificationMetricReporter;

    public EnableHandsFreeNotificationClickReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        Log.d(TAG, "onReceive");
        if (this.mNotificationMetricReporter == null) {
            this.mNotificationMetricReporter = new NotificationMetricReporter(context);
        }
        EnableHandsFreeNotification.ClickSource clickSource = (EnableHandsFreeNotification.ClickSource) intent.getSerializableExtra(EnableHandsFreeNotification.EXTRA_CLICK_SOURCE);
        if (clickSource == null) {
            return;
        }
        this.mNotificationMetricReporter.reportNotificationClickMetric(NotificationClickMetricMetadata.PageType.ENABLE_HANDS_FREE_NOTIFICATION, clickSource.getSubPageType(), context.getString(R.string.enable_hands_free_notification_text));
        ((NotificationManager) context.getSystemService(NotificationManager.class)).cancel(1008);
        if (clickSource == EnableHandsFreeNotification.ClickSource.LATER_BUTTON) {
            return;
        }
        startService(context);
    }

    @VisibleForTesting
    void startService(@NonNull Context context) {
        EnableHandsFreeService.enqueueWork(context, new Intent());
    }

    @VisibleForTesting
    EnableHandsFreeNotificationClickReceiver(@NonNull NotificationMetricReporter notificationMetricReporter) {
        this.mNotificationMetricReporter = notificationMetricReporter;
    }
}
