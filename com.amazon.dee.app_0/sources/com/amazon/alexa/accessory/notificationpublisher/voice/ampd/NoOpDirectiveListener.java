package com.amazon.alexa.accessory.notificationpublisher.voice.ampd;

import com.amazon.alexa.externalnotifications.capability.directive.ExternalNotificationsDirectiveListener;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import java.util.List;
/* loaded from: classes.dex */
public class NoOpDirectiveListener implements ExternalNotificationsDirectiveListener {
    @Override // com.amazon.alexa.externalnotifications.capability.directive.ExternalNotificationsDirectiveListener
    public void onSetReadDirective(List<NotificationId> list) {
    }
}
