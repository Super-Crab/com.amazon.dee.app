package com.amazon.alexa.externalnotifications.capability.directive;

import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import java.util.List;
/* loaded from: classes7.dex */
public interface ExternalNotificationsDirectiveListener {
    void onSetReadDirective(List<NotificationId> list);
}
