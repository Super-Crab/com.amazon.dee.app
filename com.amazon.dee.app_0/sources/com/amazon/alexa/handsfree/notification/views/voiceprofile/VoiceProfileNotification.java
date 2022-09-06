package com.amazon.alexa.handsfree.notification.views.voiceprofile;

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
public class VoiceProfileNotification extends NotificationView {
    private static final int NOTIFICATION_ID = 1004;

    public VoiceProfileNotification(@NonNull String str) {
        this(str, new VoiceProfileNotificationPresenter(), 1004);
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
    public VoiceProfileNotification(@NonNull String str, @NonNull VoiceProfileNotificationPresenter voiceProfileNotificationPresenter, int i) {
        super(str, voiceProfileNotificationPresenter, i);
    }
}
