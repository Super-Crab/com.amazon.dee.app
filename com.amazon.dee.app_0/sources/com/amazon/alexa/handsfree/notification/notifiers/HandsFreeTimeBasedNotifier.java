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
import com.amazon.alexa.handsfree.notification.utils.SupportedLocales;
import com.amazon.alexa.handsfree.notification.views.NotificationViewFactory;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class HandsFreeTimeBasedNotifier extends Notifier {
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final SupportedLocales mSupportedLocales;

    public HandsFreeTimeBasedNotifier(@NonNull Context context) {
        super(context, NotificationType.TIME_BASED, new NotificationViewFactory().getDeciderNotification(NotificationType.TIME_BASED));
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new TimeIntervalConfigHandler(context));
        this.mSupportedLocales = new SupportedLocales(context);
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        return !isHandsFreeSetupComplete() && this.mNotificationQuotaManager.hasQuotaAvailable(NotificationType.TIME_BASED) && this.mSupportedLocales.isCurrentLocaleSupported();
    }

    @VisibleForTesting
    HandsFreeTimeBasedNotifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull ConfigurationProvider configurationProvider, @NonNull SupportedLocales supportedLocales, @NonNull Lazy<AlexaAppSignInContract> lazy) {
        super(context, notificationType, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mNotificationQuotaManager = notificationQuotaManager;
        this.mSupportedLocales = supportedLocales;
    }
}
