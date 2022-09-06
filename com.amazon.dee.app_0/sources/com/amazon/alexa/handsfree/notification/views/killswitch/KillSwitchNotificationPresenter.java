package com.amazon.alexa.handsfree.notification.views.killswitch;

import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter;
import javax.inject.Inject;
/* loaded from: classes8.dex */
class KillSwitchNotificationPresenter extends NotificationViewPresenter {
    /* JADX INFO: Access modifiers changed from: package-private */
    public KillSwitchNotificationPresenter() {
        this(new DismissIntentProvider(), NotificationType.KILL_SWITCH, new KillSwitchMetadataProvider());
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter
    public PendingIntent getContentIntent(@NonNull Context context) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public KillSwitchNotificationPresenter(@NonNull DismissIntentProvider dismissIntentProvider, @NonNull NotificationType notificationType, @NonNull KillSwitchMetadataProvider killSwitchMetadataProvider) {
        super(dismissIntentProvider, notificationType, killSwitchMetadataProvider);
    }
}
