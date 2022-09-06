package com.amazon.alexa.externalnotifications.capability;

import com.amazon.alexa.externalnotifications.capability.models.Notification;
import java.util.List;
/* loaded from: classes7.dex */
public interface ExternalNotificationsProvider {
    List<Notification> getActiveNotifications();
}
