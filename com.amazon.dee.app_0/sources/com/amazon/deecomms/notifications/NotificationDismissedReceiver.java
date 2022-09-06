package com.amazon.deecomms.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public class NotificationDismissedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        CommsNotificationManager commsNotificationManager = CommsDaggerWrapper.getComponent().getCommsNotificationManager();
        String stringExtra = intent.getStringExtra("target");
        int intExtra = intent.getIntExtra(Constants.BUNDLE_KEY_NOTIFICATION_ID, -1);
        if (Constants.NOTIFICATION_TARGET_ANNOUNCEMENT.equals(stringExtra)) {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_ANN_CLEARED);
            if (intExtra == -1) {
                commsNotificationManager.clearAnnouncements();
                return;
            } else {
                commsNotificationManager.removeAnnouncementNotification(intExtra);
                return;
            }
        }
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_MESSAGE_CLEARED);
        if (intExtra == -1) {
            commsNotificationManager.clearMessages();
        } else {
            commsNotificationManager.removeNotificationMessage(intExtra);
        }
    }
}
