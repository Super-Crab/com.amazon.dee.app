package com.amazon.alexa.handsfree.notification.views.languageswitching;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter;
import com.amazon.alexa.handsfree.notification.views.decider.DeciderMetadataProvider;
import com.amazon.alexa.handsfree.notification.views.decider.DeciderNotificationPresenter;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class LanguageSwitchingNotificationPresenter extends NotificationViewPresenter {
    public static final String IS_LS_PAGE_LAUNCHED_FROM_NOTIFICATION = "isLSPageLaunchedFromNotification";
    @VisibleForTesting
    static final String LANGUAGE_SWITCHING_INTENT = "com.amazon.alexa.handsfree.DYNAMICLANGUAGESWITCHING";
    private final DismissIntentProvider mDismissIntentProvider;
    private final HandsFreeSetupStateProvider mHandsFreeSetupStateProvider;
    private final NotificationMetadataProvider mNotificationMetadataProvider;
    private final NotificationType mNotificationType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LanguageSwitchingNotificationPresenter() {
        this(new DismissIntentProvider(), NotificationType.LANGUAGE_SWITCHING, new LanguageSwitchingMetadataProvider(), NotificationModule.getInstance().getContract().getHandsFreeSetupStateProvider());
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter
    public PendingIntent getContentIntent(@NonNull Context context) {
        if (this.mHandsFreeSetupStateProvider.isHandsFreeSetupComplete(context)) {
            Intent intent = new Intent(LANGUAGE_SWITCHING_INTENT);
            putNotificationIntentExtras(context, intent);
            return PendingIntent.getActivity(context, 0, intent, 134217728);
        }
        return new DeciderNotificationPresenter(this.mDismissIntentProvider, this.mNotificationType, new DeciderMetadataProvider(), this.mHandsFreeSetupStateProvider).getContentIntent(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter
    public void putNotificationIntentExtras(@NonNull Context context, @NonNull Intent intent) {
        super.putNotificationIntentExtras(context, intent);
        intent.putExtra(IS_LS_PAGE_LAUNCHED_FROM_NOTIFICATION, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public LanguageSwitchingNotificationPresenter(@NonNull DismissIntentProvider dismissIntentProvider, @NonNull NotificationType notificationType, @NonNull NotificationMetadataProvider notificationMetadataProvider, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider) {
        super(dismissIntentProvider, notificationType, notificationMetadataProvider);
        this.mDismissIntentProvider = dismissIntentProvider;
        this.mNotificationType = notificationType;
        this.mNotificationMetadataProvider = notificationMetadataProvider;
        this.mHandsFreeSetupStateProvider = handsFreeSetupStateProvider;
    }
}
