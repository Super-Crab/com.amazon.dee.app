package com.amazon.deecomms.notifications.model;
/* loaded from: classes12.dex */
public interface PushNotificationAction {
    default boolean authenticate() {
        return true;
    }

    void doRouteAction();

    default boolean validatePath() {
        return true;
    }
}
