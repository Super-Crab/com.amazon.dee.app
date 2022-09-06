package com.amazon.alexa.accessory.notificationpublisher.voice;

import com.amazon.alexa.externalnotifications.capability.directive.ExternalNotificationsDirectiveListener;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import java.util.List;
/* loaded from: classes.dex */
public class NotificationsDirectiveListener implements ExternalNotificationsDirectiveListener {
    private static final String TAG = "NotificationsDirectiveListener";

    @Override // com.amazon.alexa.externalnotifications.capability.directive.ExternalNotificationsDirectiveListener
    public void onSetReadDirective(List<NotificationId> list) {
        String str = "onSetReadDirective: " + list;
    }
}
