package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.IntentActionParser;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.NotificationUtils;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.api.UVRSettingsProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.configurations.utterancebased.UtteranceConfigHandler;
import com.amazon.alexa.handsfree.notification.configurations.utterancebased.UtteranceNotificationLimiter;
import com.amazon.alexa.handsfree.notification.views.NotificationViewFactory;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class ShowOnLockScreenNotifier extends Notifier {
    private final Context mContext;
    private final IntentActionParser mIntentActionParser;
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final UVRSettingsProvider mUVRSettingsProvider;
    private final UtteranceNotificationLimiter mUtteranceNotificationLimiter;

    public ShowOnLockScreenNotifier(@NonNull Context context, @NonNull Intent intent) {
        super(context, NotificationType.SHOW_ON_LOCK_SCREEN, new NotificationViewFactory().getLockScreenNotification());
        this.mIntentActionParser = new IntentActionParser(intent);
        this.mContext = context;
        this.mUVRSettingsProvider = NotificationModule.getInstance().getContract().getUVRSettingsProvider();
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new UtteranceConfigHandler(context));
        this.mUtteranceNotificationLimiter = new UtteranceNotificationLimiter(context, NotificationType.SHOW_ON_LOCK_SCREEN);
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        return isHandsFreeSetupComplete() && this.mUVRSettingsProvider.isUVRAvailable() && this.mUVRSettingsProvider.isUVREnabled() && !this.mIntentActionParser.isUtteranceFailure() && this.mIntentActionParser.isUtteranceOnLockScreen() && !this.mUVRSettingsProvider.isShowOnLockscreenEnabled(this.mContext) && this.mNotificationQuotaManager.hasQuotaAvailable(NotificationType.SHOW_ON_LOCK_SCREEN) && this.mUtteranceNotificationLimiter.isMinHrsFromLastUtterance() && !this.mUtteranceNotificationLimiter.isMaxDaysPastFirstUtterance();
    }

    @VisibleForTesting
    ShowOnLockScreenNotifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull IntentActionParser intentActionParser, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull UVRSettingsProvider uVRSettingsProvider, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull UtteranceNotificationLimiter utteranceNotificationLimiter, @NonNull ConfigurationProvider configurationProvider, @NonNull Lazy<AlexaAppSignInContract> lazy) {
        super(context, notificationType, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mIntentActionParser = intentActionParser;
        this.mContext = context;
        this.mUVRSettingsProvider = uVRSettingsProvider;
        this.mNotificationQuotaManager = notificationQuotaManager;
        this.mUtteranceNotificationLimiter = utteranceNotificationLimiter;
    }
}
