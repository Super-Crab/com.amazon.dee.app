package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.NotificationUtils;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.api.DeviceInformationProvider;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.api.SettingsProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.configurations.quicksettings.QuickSettingsConfigHandler;
import com.amazon.alexa.handsfree.notification.utils.SupportedLocales;
import com.amazon.alexa.handsfree.notification.views.NotificationViewFactory;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class QuickSettingsNotifier extends Notifier {
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final DeviceInformationProvider mDeviceInformationProvider;
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final SettingsProvider mSettingsProvider;
    private final SupportedLocales mSupportedLocales;

    public QuickSettingsNotifier(@NonNull Context context) {
        super(context, NotificationType.QUICK_SETTINGS, new NotificationViewFactory().getQuickSettingsNotification());
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new QuickSettingsConfigHandler(context));
        this.mSupportedLocales = new SupportedLocales(context);
        this.mSettingsProvider = NotificationModule.getInstance().getContract().getSettingsProvider();
        this.mAMPDInformationProvider = AMPDInformationProvider.getInstance(context);
        this.mDeviceInformationProvider = NotificationModule.getInstance().getContract().getDeviceInformationProvider();
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        return isHandsFreeSetupComplete() && (this.mAMPDInformationProvider.isVesper() || this.mDeviceInformationProvider.isTrueTurnkey()) && ((!this.mAMPDInformationProvider.isHandsFreeXray() || this.mSettingsProvider.isQsTileInMainMenu()) && !this.mSettingsProvider.hasInteractedWithQsTile() && this.mNotificationQuotaManager.hasQuotaAvailable(NotificationType.QUICK_SETTINGS) && this.mSupportedLocales.isCurrentLocaleSupported());
    }

    @VisibleForTesting
    QuickSettingsNotifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull ConfigurationProvider configurationProvider, @NonNull SupportedLocales supportedLocales, @NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull SettingsProvider settingsProvider, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull DeviceInformationProvider deviceInformationProvider) {
        super(context, notificationType, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mNotificationQuotaManager = notificationQuotaManager;
        this.mSupportedLocales = supportedLocales;
        this.mSettingsProvider = settingsProvider;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mDeviceInformationProvider = deviceInformationProvider;
    }
}
