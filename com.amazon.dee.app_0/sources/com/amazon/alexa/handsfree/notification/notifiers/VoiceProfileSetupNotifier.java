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
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class VoiceProfileSetupNotifier extends Notifier {
    private final IntentActionParser mIntentActionParser;
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final UVRSettingsProvider mUVRSettingsProvider;
    private final UtteranceNotificationLimiter mUtteranceNotificationLimiter;

    public VoiceProfileSetupNotifier(@NonNull Context context, @NonNull Intent intent) {
        this(context, new IntentActionParser(intent), NotificationModule.getInstance().getContract().getUVRSettingsProvider());
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        return isHandsFreeSetupComplete() && this.mUVRSettingsProvider.isUVRAvailable() && !this.mIntentActionParser.isUtteranceFailure() && !this.mUVRSettingsProvider.isUVREnabled() && this.mNotificationQuotaManager.hasQuotaAvailable(getNotificationType()) && this.mUtteranceNotificationLimiter.isMinHrsFromLastUtterance() && !this.mUtteranceNotificationLimiter.isMaxDaysPastFirstUtterance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public VoiceProfileSetupNotifier(@NonNull Context context, @NonNull IntentActionParser intentActionParser, @NonNull UVRSettingsProvider uVRSettingsProvider) {
        super(context, intentActionParser.isUtteranceOnLockScreen() ? NotificationType.VOICE_PROFILE_ON_LOCK_SCREEN : NotificationType.VOICE_PROFILE, new NotificationViewFactory().getVoiceProfileNotification());
        this.mIntentActionParser = intentActionParser;
        this.mUVRSettingsProvider = uVRSettingsProvider;
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new UtteranceConfigHandler(context));
        this.mUtteranceNotificationLimiter = new UtteranceNotificationLimiter(context, getNotificationType());
    }

    @VisibleForTesting
    VoiceProfileSetupNotifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull IntentActionParser intentActionParser, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull UVRSettingsProvider uVRSettingsProvider, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull UtteranceNotificationLimiter utteranceNotificationLimiter, @NonNull ConfigurationProvider configurationProvider, @NonNull Lazy<AlexaAppSignInContract> lazy) {
        super(context, notificationType, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mIntentActionParser = intentActionParser;
        this.mUVRSettingsProvider = uVRSettingsProvider;
        this.mNotificationQuotaManager = notificationQuotaManager;
        this.mUtteranceNotificationLimiter = utteranceNotificationLimiter;
    }
}
