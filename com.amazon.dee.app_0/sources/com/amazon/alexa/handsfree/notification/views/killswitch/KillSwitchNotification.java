package com.amazon.alexa.handsfree.notification.views.killswitch;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.handsfree.notification.R;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class KillSwitchNotification extends NotificationView {
    private static final int NOTIFICATION_ID = 1002;

    public KillSwitchNotification(@NonNull String str) {
        this(str, new KillSwitchNotificationPresenter(), 1002);
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationView
    public Notification getNotification(@NonNull Context context) {
        NotificationViewPresenter presenter = getPresenter();
        return getNotificationHelper(context, new Notification.Builder(context, getChannelId()), ContextCompat.getColor(context, R.color.handsfree_setup_notification), presenter.getNotificationMetadata(context), presenter);
    }

    @VisibleForTesting
    public Notification getNotificationHelper(@NonNull Context context, @NonNull Notification.Builder builder, @NonNull int i, @NonNull NotificationMetadata notificationMetadata, @NonNull NotificationViewPresenter notificationViewPresenter) {
        int notificationIconAsset = notificationMetadata.getNotificationIconAsset(context);
        String notificationText = notificationMetadata.getNotificationText(context);
        PendingIntent dismissIntent = notificationViewPresenter.getDismissIntent(context, notificationText);
        Notification.BigTextStyle bigText = new Notification.BigTextStyle().bigText(notificationText);
        builder.setColor(i);
        builder.setSmallIcon(notificationIconAsset);
        builder.setContentText(notificationText);
        builder.setStyle(bigText);
        builder.setDeleteIntent(dismissIntent);
        builder.setAutoCancel(true);
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public KillSwitchNotification(@NonNull String str, @NonNull KillSwitchNotificationPresenter killSwitchNotificationPresenter, int i) {
        super(str, killSwitchNotificationPresenter, i);
    }
}
