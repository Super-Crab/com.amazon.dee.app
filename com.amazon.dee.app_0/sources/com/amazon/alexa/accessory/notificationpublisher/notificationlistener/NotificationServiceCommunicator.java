package com.amazon.alexa.accessory.notificationpublisher.notificationlistener;

import android.content.ServiceConnection;
import android.service.notification.StatusBarNotification;
import java.util.List;
/* loaded from: classes.dex */
public interface NotificationServiceCommunicator {
    ServiceConnection getServiceConnection();

    boolean safeCallDoesNotificationExistInStatusBar(String str);

    boolean safeCallDoesNotificationSupportReply(String str);

    List<StatusBarNotification> safeCallGetActiveNotificationsWithKeys(String[] strArr);

    int safeCallGetInterruptionFilter();

    boolean safeCallIsListenerConnected();

    boolean safeCallIsReplyNotification(String str, String str2, String str3);

    boolean safeCallOnDeviceConnectionChanged(boolean z);

    boolean safeCallSendReply(String str, String str2);

    boolean safeCallSetApplicationRunning(boolean z);
}
