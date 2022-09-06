package com.amazon.alexa.handsfree.notification.views.permission;

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
import com.amazon.alexa.handsfree.protocols.utils.Log;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class PermissionNotification extends NotificationView {
    public static final int NOTIFICATION_ID = 1007;
    private static final String TAG = "PermissionNotification";

    public PermissionNotification(@NonNull String str) {
        this(str, new PermissionNotificationPresenter(), 1007);
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
        String notificationTitle = notificationMetadata.getNotificationTitle(context);
        PendingIntent contentIntent = notificationViewPresenter.getContentIntent(context);
        PendingIntent dismissIntent = notificationViewPresenter.getDismissIntent(context, notificationText);
        Notification.BigTextStyle bigText = new Notification.BigTextStyle().bigText(notificationText);
        builder.setColor(i);
        builder.setSmallIcon(notificationIconAsset);
        builder.setContentText(notificationText);
        builder.setContentTitle(notificationTitle);
        builder.setStyle(bigText);
        builder.setContentIntent(contentIntent);
        builder.setDeleteIntent(dismissIntent);
        builder.setAutoCancel(true);
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public PermissionNotification(@NonNull String str, @NonNull PermissionNotificationPresenter permissionNotificationPresenter, int i) {
        super(str, permissionNotificationPresenter, i);
    }
}
