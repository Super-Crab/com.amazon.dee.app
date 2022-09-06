package com.amazon.alexa.handsfree.notification.views.permission;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter;
import javax.inject.Inject;
/* loaded from: classes8.dex */
class PermissionNotificationPresenter extends NotificationViewPresenter {
    private final HandsFreeSetupStateProvider mHandsFreeSetupStateProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PermissionNotificationPresenter() {
        this(new DismissIntentProvider(), new PermissionMetadataProvider(), NotificationModule.getInstance().getContract().getHandsFreeSetupStateProvider());
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter
    public PendingIntent getContentIntent(@NonNull Context context) {
        return PendingIntent.getService(context, 0, getSetupFlowServiceIntent(context), 134217728);
    }

    @NonNull
    @VisibleForTesting
    Intent getSetupFlowServiceIntent(@NonNull Context context) {
        Intent setupFlowIntent = this.mHandsFreeSetupStateProvider.getSetupFlowIntent(context);
        putNotificationIntentExtras(context, setupFlowIntent);
        return setupFlowIntent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public PermissionNotificationPresenter(@NonNull DismissIntentProvider dismissIntentProvider, @NonNull PermissionMetadataProvider permissionMetadataProvider, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider) {
        super(dismissIntentProvider, NotificationType.PERMISSION_REQUEST, permissionMetadataProvider);
        this.mHandsFreeSetupStateProvider = handsFreeSetupStateProvider;
    }
}
