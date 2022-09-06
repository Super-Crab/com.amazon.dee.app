package com.amazon.alexa.handsfree.notification.views.enablehandsfree;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.handsfree.notification.R;
import com.amazon.alexa.handsfree.notification.metrics.NotificationClickMetricMetadata;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.notification.receivers.EnableHandsFreeNotificationClickReceiver;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter;
/* loaded from: classes8.dex */
public class EnableHandsFreeNotification extends NotificationView {
    public static final String EXTRA_CLICK_SOURCE = "click_source";
    public static final int NOTIFICATION_ID = 1008;

    /* loaded from: classes8.dex */
    public enum ClickSource {
        MAIN_AREA(0, NotificationClickMetricMetadata.SubPageType.NONE),
        CONFIRM_BUTTON(1, NotificationClickMetricMetadata.SubPageType.ENABLE_HANDS_FREE_CONFIRM_BUTTON),
        LATER_BUTTON(2, NotificationClickMetricMetadata.SubPageType.ENABLE_HANDS_FREE_LATER_BUTTON);
        
        private final int mRequestCode;
        private final NotificationClickMetricMetadata.SubPageType mSubPageType;

        ClickSource(int i, @NonNull NotificationClickMetricMetadata.SubPageType subPageType) {
            this.mRequestCode = i;
            this.mSubPageType = subPageType;
        }

        public int getRequestCode() {
            return this.mRequestCode;
        }

        public NotificationClickMetricMetadata.SubPageType getSubPageType() {
            return this.mSubPageType;
        }
    }

    public EnableHandsFreeNotification(@NonNull String str) {
        super(str, new EnableHandsFreeNotificationPresenter(), 1008);
    }

    public static PendingIntent getActionPendingIntent(@NonNull Context context, @NonNull ClickSource clickSource) {
        return PendingIntent.getBroadcast(context, clickSource.getRequestCode(), new Intent(context, EnableHandsFreeNotificationClickReceiver.class).putExtra(EXTRA_CLICK_SOURCE, clickSource), 201326592);
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
        builder.setContentIntent(notificationViewPresenter.getContentIntent(context));
        builder.setDeleteIntent(dismissIntent);
        builder.setAutoCancel(true);
        builder.addAction(new Notification.Action.Builder((Icon) null, context.getString(R.string.enable_hands_free_notification_confirm_button_text), getActionPendingIntent(context, ClickSource.CONFIRM_BUTTON)).build()).addAction(new Notification.Action.Builder((Icon) null, context.getString(R.string.enable_hands_free_notification_later_button_text), getActionPendingIntent(context, ClickSource.LATER_BUTTON)).build());
        return builder.build();
    }
}
