package com.amazon.alexa.handsfree.notification.views.lockscreen;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter;
import javax.inject.Inject;
/* loaded from: classes8.dex */
class ShowOnLockscreenNotificationPresenter extends NotificationViewPresenter {
    @VisibleForTesting
    static final String SETTINGS_INTENT = "com.amazon.alexa.handsfree.SETTINGS";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShowOnLockscreenNotificationPresenter() {
        this(new DismissIntentProvider(), new ShowOnLockscreenMetadataProvider(), NotificationType.SHOW_ON_LOCK_SCREEN);
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter
    public PendingIntent getContentIntent(@NonNull Context context) {
        Intent intent = new Intent("com.amazon.alexa.handsfree.SETTINGS");
        putNotificationIntentExtras(context, intent);
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public ShowOnLockscreenNotificationPresenter(@NonNull DismissIntentProvider dismissIntentProvider, @NonNull NotificationMetadataProvider notificationMetadataProvider, @NonNull NotificationType notificationType) {
        super(dismissIntentProvider, notificationType, notificationMetadataProvider);
    }
}
