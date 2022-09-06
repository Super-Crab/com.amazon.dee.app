package com.amazon.deecomms.notifications.model;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.notifications.util.NotificationUtils;
/* loaded from: classes12.dex */
public class ConversationPushNotificationAction implements PushNotificationAction {
    private final ApplicationManager applicationManager;
    private String path;

    public ConversationPushNotificationAction(String str, ApplicationManager applicationManager) {
        this.path = str;
        this.applicationManager = applicationManager;
    }

    @Override // com.amazon.deecomms.notifications.model.PushNotificationAction
    public boolean authenticate() {
        String str = TextUtils.split(this.path, "/")[2];
        return str.equals(CommsInternal.getInstance().getCommsId()) || str.equals(CommsInternal.getInstance().getHomeGroupId());
    }

    @Override // com.amazon.deecomms.notifications.model.PushNotificationAction
    public void doRouteAction() {
        NotificationUtils.ExtractedInfo extractConversationAndRecipientFromPath = NotificationUtils.extractConversationAndRecipientFromPath(this.path);
        String str = extractConversationAndRecipientFromPath.sendAsCommsId;
        navigateToConversationRNView(extractConversationAndRecipientFromPath.recipientId, extractConversationAndRecipientFromPath.conversationId, str, new Bundle());
    }

    @VisibleForTesting
    protected void navigateToConversationRNView(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Bundle bundle) {
        if (!str2.contentEquals("UNKNOWN")) {
            bundle.putString(Constants.BUNDLE_KEY_CONVERSATION_ID, str2);
        }
        bundle.putString(Constants.BUNDLE_KEY_RECIPIENT_ID, str);
        bundle.putString(Constants.BUNDLE_KEY_SEND_AS_COMMS_ID, str3);
        this.applicationManager.navigateToView(CommsView.ReactNativeConversation, bundle, true);
    }

    @Override // com.amazon.deecomms.notifications.model.PushNotificationAction
    public boolean validatePath() {
        String[] split = TextUtils.split(this.path, "/");
        return split != null && split.length >= 6;
    }
}
