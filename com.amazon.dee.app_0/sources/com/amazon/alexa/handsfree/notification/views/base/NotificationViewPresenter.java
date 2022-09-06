package com.amazon.alexa.handsfree.notification.views.base;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
/* loaded from: classes8.dex */
public abstract class NotificationViewPresenter {
    private final DismissIntentProvider mDismissIntentProvider;
    private final NotificationMetadataProvider mNotificationMetadataProvider;
    private final NotificationType mNotificationType;

    public NotificationViewPresenter(@NonNull DismissIntentProvider dismissIntentProvider, @NonNull NotificationType notificationType, @NonNull NotificationMetadataProvider notificationMetadataProvider) {
        this.mDismissIntentProvider = dismissIntentProvider;
        this.mNotificationType = notificationType;
        this.mNotificationMetadataProvider = notificationMetadataProvider;
    }

    public abstract PendingIntent getContentIntent(@NonNull Context context);

    public PendingIntent getDismissIntent(@NonNull Context context, @NonNull String str) {
        return this.mDismissIntentProvider.getDismissPendingIntent(context, this.mNotificationType, str);
    }

    public NotificationMetadata getNotificationMetadata(@NonNull Context context) {
        return this.mNotificationMetadataProvider.getNotificationMetadata(context);
    }

    public NotificationType getNotificationType() {
        return this.mNotificationType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void putNotificationIntentExtras(@NonNull Context context, @NonNull Intent intent) {
        intent.putExtra(DismissIntentProvider.EXTRA_NOTIFICATION_OPERATION, getNotificationType().toString()).putExtra("extra_notification_text", getNotificationMetadata(context).getNotificationText(context));
    }
}
