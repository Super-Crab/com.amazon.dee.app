package com.amazon.alexa.handsfree.notification.views.languageswitching;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class LanguageSwitchingNotification_Factory implements Factory<LanguageSwitchingNotification> {
    private final Provider<String> channelIdProvider;
    private final Provider<LanguageSwitchingNotificationPresenter> languageSwitchingNotificationPresenterProvider;
    private final Provider<Integer> notificationIdProvider;

    public LanguageSwitchingNotification_Factory(Provider<String> provider, Provider<LanguageSwitchingNotificationPresenter> provider2, Provider<Integer> provider3) {
        this.channelIdProvider = provider;
        this.languageSwitchingNotificationPresenterProvider = provider2;
        this.notificationIdProvider = provider3;
    }

    public static LanguageSwitchingNotification_Factory create(Provider<String> provider, Provider<LanguageSwitchingNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new LanguageSwitchingNotification_Factory(provider, provider2, provider3);
    }

    public static LanguageSwitchingNotification newLanguageSwitchingNotification(String str, LanguageSwitchingNotificationPresenter languageSwitchingNotificationPresenter, int i) {
        return new LanguageSwitchingNotification(str, languageSwitchingNotificationPresenter, i);
    }

    public static LanguageSwitchingNotification provideInstance(Provider<String> provider, Provider<LanguageSwitchingNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new LanguageSwitchingNotification(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().intValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LanguageSwitchingNotification mo10268get() {
        return provideInstance(this.channelIdProvider, this.languageSwitchingNotificationPresenterProvider, this.notificationIdProvider);
    }
}
