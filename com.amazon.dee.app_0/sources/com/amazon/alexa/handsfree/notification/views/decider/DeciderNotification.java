package com.amazon.alexa.handsfree.notification.views.decider;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.R;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class DeciderNotification extends NotificationView {
    public static final int NOTIFICATION_ID = 1001;
    private static final String TAG = "DeciderNotification";

    public DeciderNotification(@NonNull String str, @NonNull NotificationType notificationType) {
        this(str, new DeciderNotificationPresenter(notificationType), 1001);
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationView
    public Notification getNotification(@NonNull Context context) {
        NotificationViewPresenter presenter = getPresenter();
        NotificationMetadata notificationMetadata = presenter.getNotificationMetadata(context);
        if (notificationMetadata == null) {
            Log.d(TAG, "Notification metadata is null, no notification required");
            return null;
        }
        return getNotificationHelper(context, new Notification.Builder(context, getChannelId()), ContextCompat.getColor(context, R.color.handsfree_setup_notification), notificationMetadata, presenter);
    }

    @VisibleForTesting
    public Notification getNotificationHelper(@NonNull Context context, @NonNull Notification.Builder builder, @NonNull int i, @NonNull NotificationMetadata notificationMetadata, @NonNull NotificationViewPresenter notificationViewPresenter) {
        int notificationIconAsset = notificationMetadata.getNotificationIconAsset(context);
        String notificationText = notificationMetadata.getNotificationText(context);
        PendingIntent contentIntent = notificationViewPresenter.getContentIntent(context);
        PendingIntent dismissIntent = notificationViewPresenter.getDismissIntent(context, notificationText);
        Notification.BigTextStyle bigText = new Notification.BigTextStyle().bigText(notificationText);
        builder.setColor(i);
        builder.setSmallIcon(notificationIconAsset);
        builder.setContentText(notificationText);
        builder.setStyle(bigText);
        builder.setContentIntent(contentIntent);
        builder.setDeleteIntent(dismissIntent);
        builder.setAutoCancel(true);
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public DeciderNotification(@NonNull String str, @NonNull DeciderNotificationPresenter deciderNotificationPresenter, int i) {
        super(str, deciderNotificationPresenter, i);
    }
}
