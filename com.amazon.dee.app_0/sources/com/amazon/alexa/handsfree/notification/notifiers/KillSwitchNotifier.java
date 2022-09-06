package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.NotificationUtils;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.configurations.timebased.TimeIntervalConfigHandler;
import com.amazon.alexa.handsfree.notification.views.NotificationViewFactory;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class KillSwitchNotifier extends Notifier {
    private final NotificationQuotaManager mNotificationQuotaManager;

    public KillSwitchNotifier(@NonNull Context context) {
        super(context, NotificationType.KILL_SWITCH, new NotificationViewFactory().getKillSwitchNotification());
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new TimeIntervalConfigHandler(context));
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        return this.mNotificationQuotaManager.hasQuotaAvailable(NotificationType.KILL_SWITCH);
    }

    @VisibleForTesting
    KillSwitchNotifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull ConfigurationProvider configurationProvider, @NonNull Lazy<AlexaAppSignInContract> lazy) {
        super(context, notificationType, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mNotificationQuotaManager = notificationQuotaManager;
    }
}
