package com.amazon.alexa.handsfree.notification.views.quicksettings;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class QuickSettingsNotification_Factory implements Factory<QuickSettingsNotification> {
    private final Provider<String> channelIdProvider;
    private final Provider<Integer> notificationIdProvider;
    private final Provider<QuickSettingsNotificationPresenter> quickSettingsNotificationPresenterProvider;

    public QuickSettingsNotification_Factory(Provider<String> provider, Provider<QuickSettingsNotificationPresenter> provider2, Provider<Integer> provider3) {
        this.channelIdProvider = provider;
        this.quickSettingsNotificationPresenterProvider = provider2;
        this.notificationIdProvider = provider3;
    }

    public static QuickSettingsNotification_Factory create(Provider<String> provider, Provider<QuickSettingsNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new QuickSettingsNotification_Factory(provider, provider2, provider3);
    }

    public static QuickSettingsNotification newQuickSettingsNotification(String str, QuickSettingsNotificationPresenter quickSettingsNotificationPresenter, int i) {
        return new QuickSettingsNotification(str, quickSettingsNotificationPresenter, i);
    }

    public static QuickSettingsNotification provideInstance(Provider<String> provider, Provider<QuickSettingsNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new QuickSettingsNotification(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().intValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public QuickSettingsNotification mo10268get() {
        return provideInstance(this.channelIdProvider, this.quickSettingsNotificationPresenterProvider, this.notificationIdProvider);
    }
}
