package com.amazon.deecomms.notifications.model;

import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public class AnnouncementPushNotificationAction implements PushNotificationAction {
    private String announcementId = getAnnouncementIdFromPath();
    private final ApplicationManager applicationManager;
    private CommsView commsView;
    private String path;
    private String[] pathTokens;

    public AnnouncementPushNotificationAction(String str, ApplicationManager applicationManager, CommsView commsView) {
        this.path = str;
        this.commsView = commsView;
        this.applicationManager = applicationManager;
        this.pathTokens = str.split("/");
    }

    @Override // com.amazon.deecomms.notifications.model.PushNotificationAction
    public void doRouteAction() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_ANNOUNCEMENT_ID, this.announcementId);
        this.applicationManager.navigateToView(this.commsView, bundle, true);
    }

    @VisibleForTesting
    public String getAnnouncementIdFromPath() {
        String[] strArr = this.pathTokens;
        return strArr.length > 3 ? strArr[3] : "";
    }

    @Override // com.amazon.deecomms.notifications.model.PushNotificationAction
    public boolean validatePath() {
        return this.pathTokens.length >= 3;
    }
}
