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
import com.amazon.alexa.handsfree.notification.api.PFMProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.configurations.languageswitching.LanguageSwitchingConfigHandler;
import com.amazon.alexa.handsfree.notification.views.NotificationViewFactory;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.handsfree.protocols.utils.AlexaLocaleStore;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class LanguageSwitchingNotifier extends Notifier {
    private final AlexaLocaleStore mAlexaLocaleStore;
    private final Context mContext;
    private final DeviceInformationProvider mDeviceInformationProvider;
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final PFMProvider mPFMProvider;

    public LanguageSwitchingNotifier(@NonNull Context context) {
        super(context, NotificationType.LANGUAGE_SWITCHING, new NotificationViewFactory().getLanguageSwitchingNotification());
        this.mContext = context;
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new LanguageSwitchingConfigHandler(context));
        this.mDeviceInformationProvider = NotificationModule.getInstance().getContract().getDeviceInformationProvider();
        this.mPFMProvider = NotificationModule.getInstance().getContract().getPFMProvider();
        this.mAlexaLocaleStore = new AlexaLocaleStore(context);
    }

    private boolean isDLSSetupCompleted() {
        return this.mAlexaLocaleStore.getLocales().size() > 1;
    }

    private boolean isIndiaLocale() {
        return this.mPFMProvider.isIndianCustomers();
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        return isIndiaLocale() && this.mDeviceInformationProvider.isTrueTurnkey() && !isDLSSetupCompleted() && this.mNotificationQuotaManager.hasQuotaAvailable(NotificationType.LANGUAGE_SWITCHING);
    }

    @VisibleForTesting
    LanguageSwitchingNotifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull ConfigurationProvider configurationProvider, @NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull DeviceInformationProvider deviceInformationProvider, @NonNull PFMProvider pFMProvider, @NonNull AlexaLocaleStore alexaLocaleStore) {
        super(context, notificationType, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mContext = context;
        this.mNotificationQuotaManager = notificationQuotaManager;
        this.mDeviceInformationProvider = deviceInformationProvider;
        this.mPFMProvider = pFMProvider;
        this.mAlexaLocaleStore = alexaLocaleStore;
    }
}
