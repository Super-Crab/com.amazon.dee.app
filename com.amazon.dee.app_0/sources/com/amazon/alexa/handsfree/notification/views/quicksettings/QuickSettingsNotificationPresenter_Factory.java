package com.amazon.alexa.handsfree.notification.views.quicksettings;

import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.SettingsProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class QuickSettingsNotificationPresenter_Factory implements Factory<QuickSettingsNotificationPresenter> {
    private final Provider<DismissIntentProvider> dismissIntentProvider;
    private final Provider<NotificationMetadataProvider> metadataProvider;
    private final Provider<NotificationType> notificationTypeProvider;
    private final Provider<SettingsProvider> settingsProvider;

    public QuickSettingsNotificationPresenter_Factory(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3, Provider<SettingsProvider> provider4) {
        this.dismissIntentProvider = provider;
        this.notificationTypeProvider = provider2;
        this.metadataProvider = provider3;
        this.settingsProvider = provider4;
    }

    public static QuickSettingsNotificationPresenter_Factory create(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3, Provider<SettingsProvider> provider4) {
        return new QuickSettingsNotificationPresenter_Factory(provider, provider2, provider3, provider4);
    }

    public static QuickSettingsNotificationPresenter newQuickSettingsNotificationPresenter(DismissIntentProvider dismissIntentProvider, NotificationType notificationType, NotificationMetadataProvider notificationMetadataProvider, SettingsProvider settingsProvider) {
        return new QuickSettingsNotificationPresenter(dismissIntentProvider, notificationType, notificationMetadataProvider, settingsProvider);
    }

    public static QuickSettingsNotificationPresenter provideInstance(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3, Provider<SettingsProvider> provider4) {
        return new QuickSettingsNotificationPresenter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public QuickSettingsNotificationPresenter mo10268get() {
        return provideInstance(this.dismissIntentProvider, this.notificationTypeProvider, this.metadataProvider, this.settingsProvider);
    }
}
