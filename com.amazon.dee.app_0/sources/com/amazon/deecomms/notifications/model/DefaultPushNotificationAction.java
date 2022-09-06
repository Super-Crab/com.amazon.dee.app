package com.amazon.deecomms.notifications.model;

import android.os.Bundle;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.ApplicationManager;
/* loaded from: classes12.dex */
public class DefaultPushNotificationAction implements PushNotificationAction {
    private final ApplicationManager applicationManager;

    public DefaultPushNotificationAction(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    @Override // com.amazon.deecomms.notifications.model.PushNotificationAction
    public void doRouteAction() {
        this.applicationManager.navigateToView(CommsView.ReactNativeConversationList, new Bundle(), true);
    }
}
