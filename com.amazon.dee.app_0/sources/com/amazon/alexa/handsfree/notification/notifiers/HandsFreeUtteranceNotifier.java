package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.IntentActionParser;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.NotificationUtils;
import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.configurations.utterancebased.UtteranceConfigHandler;
import com.amazon.alexa.handsfree.notification.configurations.utterancebased.UtteranceNotificationLimiter;
import com.amazon.alexa.handsfree.notification.utils.SupportedLocales;
import com.amazon.alexa.handsfree.notification.views.NotificationViewFactory;
import com.amazon.alexa.handsfree.notification.views.base.NotificationView;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class HandsFreeUtteranceNotifier extends Notifier {
    private final IntentActionParser mIntentActionParser;
    private final NotificationQuotaManager mNotificationQuotaManager;
    private final SupportedLocales mSupportedLocales;
    private final UtteranceNotificationLimiter mUtteranceNotificationLimiter;

    public HandsFreeUtteranceNotifier(@NonNull Context context, @NonNull Intent intent) {
        super(context, NotificationType.UTTERANCE, new NotificationViewFactory().getDeciderNotification(NotificationType.UTTERANCE));
        this.mIntentActionParser = new IntentActionParser(intent);
        this.mNotificationQuotaManager = new NotificationQuotaManager(context, new UtteranceConfigHandler(context));
        this.mUtteranceNotificationLimiter = new UtteranceNotificationLimiter(context, NotificationType.UTTERANCE);
        this.mSupportedLocales = new SupportedLocales(context);
    }

    @Override // com.amazon.alexa.handsfree.notification.notifiers.Notifier
    public boolean isNotificationRequired() {
        return !isHandsFreeSetupComplete() && this.mIntentActionParser.isUtteranceFailure() && this.mNotificationQuotaManager.hasQuotaAvailable(NotificationType.UTTERANCE) && this.mUtteranceNotificationLimiter.isMinHrsFromLastUtterance() && !this.mUtteranceNotificationLimiter.isMaxDaysPastFirstUtterance() && this.mSupportedLocales.isCurrentLocaleSupported();
    }

    @VisibleForTesting
    HandsFreeUtteranceNotifier(@NonNull Context context, @NonNull NotificationType notificationType, @NonNull NotificationQuotaManager notificationQuotaManager, @NonNull NotificationUtils notificationUtils, @NonNull NotificationView notificationView, @NonNull IntentActionParser intentActionParser, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull UtteranceNotificationLimiter utteranceNotificationLimiter, @NonNull ConfigurationProvider configurationProvider, @NonNull SupportedLocales supportedLocales, @NonNull Lazy<AlexaAppSignInContract> lazy) {
        super(context, notificationType, notificationUtils, notificationView, handsFreeSetupStateProvider, configurationProvider, lazy);
        this.mIntentActionParser = intentActionParser;
        this.mNotificationQuotaManager = notificationQuotaManager;
        this.mUtteranceNotificationLimiter = utteranceNotificationLimiter;
        this.mSupportedLocales = supportedLocales;
    }
}
