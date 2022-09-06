package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.NotificationUtils;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.api.DeviceInformationProvider;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.configurations.timebased.TimeIntervalConfigHandler;
import com.amazon.alexa.handsfree.notification.utils.SupportedLocales;
import com.amazon.alexa.handsfree.notification.views.NotificationViewFactory;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class PermissionNotifier extends Notifier {
    private final DeviceInformationProvider mDeviceInformationProvider;
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final SupportedLocales mSupportedLocales;

    public PermissionNotifier(@NonNull Context context) {
        super(context, NotificationType.PERMISSION_REQUEST, new NotificationViewFactory().getPermissionNotification());
        this.mSupportedLocales = new SupportedLocales(context);
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new TimeIntervalConfigHandler(context));
        this.mDeviceInformationProvider = NotificationModule.getInstance().getContract().getDeviceInformationProvider();
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        return this.mDeviceInformationProvider.isTrueTurnkey() && this.mSupportedLocales.isCurrentLocaleSupported() && this.mNotificationQuotaManager.hasQuotaAvailable(NotificationType.PERMISSION_REQUEST);
    }

    @VisibleForTesting
    PermissionNotifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull ConfigurationProvider configurationProvider, @NonNull SupportedLocales supportedLocales, @NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull DeviceInformationProvider deviceInformationProvider, @NonNull NotificationQuotaManager notificationQuotaManager) {
        super(context, notificationType, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mSupportedLocales = supportedLocales;
        this.mDeviceInformationProvider = deviceInformationProvider;
        this.mNotificationQuotaManager = notificationQuotaManager;
    }
}
