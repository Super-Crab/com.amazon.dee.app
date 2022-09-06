package com.amazon.alexa.handsfree.notification.views.quicksettings;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.SettingsProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class QuickSettingsNotificationPresenter extends NotificationViewPresenter {
    public static final String EXTRA_IS_QS_TILE_IN_MAIN_MENU = "isQsTileInMainMenu";
    @VisibleForTesting
    static final String QUICK_SETTINGS_ACTIVITY_INTENT = "com.amazon.alexa.handsfree.QUICK_SETTINGS_EDUCATION";
    private final SettingsProvider mSettingsProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QuickSettingsNotificationPresenter() {
        this(new DismissIntentProvider(), NotificationType.QUICK_SETTINGS, new QuickSettingsMetadataProvider(), NotificationModule.getInstance().getContract().getSettingsProvider());
    }

    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter
    public PendingIntent getContentIntent(@NonNull Context context) {
        Intent intent = new Intent(QUICK_SETTINGS_ACTIVITY_INTENT);
        putNotificationIntentExtras(context, intent);
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.handsfree.notification.views.base.NotificationViewPresenter
    public void putNotificationIntentExtras(@NonNull Context context, @NonNull Intent intent) {
        super.putNotificationIntentExtras(context, intent);
        intent.putExtra(EXTRA_IS_QS_TILE_IN_MAIN_MENU, this.mSettingsProvider.isQsTileInMainMenu());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public QuickSettingsNotificationPresenter(@NonNull DismissIntentProvider dismissIntentProvider, @NonNull NotificationType notificationType, @NonNull NotificationMetadataProvider notificationMetadataProvider, @NonNull SettingsProvider settingsProvider) {
        super(dismissIntentProvider, notificationType, notificationMetadataProvider);
        this.mSettingsProvider = settingsProvider;
    }
}
