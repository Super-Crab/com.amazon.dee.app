package com.amazon.alexa.accessory.notificationpublisher.voice;

import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.core.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsProvider;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class NotificationProvider implements ExternalNotificationsProvider {
    private static final String TAG = "NotificationProvider";
    private final NotificationProcessor notificationProcessor;

    public NotificationProvider(NotificationProcessor notificationProcessor) {
        Preconditions.checkNotNull(notificationProcessor, "NotificationProcessor cannot be null");
        this.notificationProcessor = notificationProcessor;
    }

    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsProvider
    public List<Notification> getActiveNotifications() {
        ArrayList arrayList = new ArrayList();
        NotificationServiceCommunicator notificationServiceCommunicator = DependencyProvider.getNotificationServiceCommunicator();
        if (notificationServiceCommunicator != null) {
            List<StatusBarNotification> safeCallGetActiveNotificationsWithKeys = notificationServiceCommunicator.safeCallGetActiveNotificationsWithKeys(null);
            if (safeCallGetActiveNotificationsWithKeys != null) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of active notifications: ");
                outline107.append(safeCallGetActiveNotificationsWithKeys.size());
                Log.i(str, outline107.toString());
                return this.notificationProcessor.processNotificationList(safeCallGetActiveNotificationsWithKeys);
            }
            Log.w(TAG, "Service returned a null value for active notifications list");
            return arrayList;
        }
        Log.w(TAG, "Cannot get active notifications, failed to communicate with service");
        return arrayList;
    }
}
