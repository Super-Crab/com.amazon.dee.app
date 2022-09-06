package com.amazon.alexa.handsfree.notification.views.killswitch;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class KillSwitchNotification_Factory implements Factory<KillSwitchNotification> {
    private final Provider<String> channelIdProvider;
    private final Provider<Integer> notificationIdProvider;
    private final Provider<KillSwitchNotificationPresenter> notificationPresenterProvider;

    public KillSwitchNotification_Factory(Provider<String> provider, Provider<KillSwitchNotificationPresenter> provider2, Provider<Integer> provider3) {
        this.channelIdProvider = provider;
        this.notificationPresenterProvider = provider2;
        this.notificationIdProvider = provider3;
    }

    public static KillSwitchNotification_Factory create(Provider<String> provider, Provider<KillSwitchNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new KillSwitchNotification_Factory(provider, provider2, provider3);
    }

    public static KillSwitchNotification newKillSwitchNotification(String str, Object obj, int i) {
        return new KillSwitchNotification(str, (KillSwitchNotificationPresenter) obj, i);
    }

    public static KillSwitchNotification provideInstance(Provider<String> provider, Provider<KillSwitchNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new KillSwitchNotification(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().intValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KillSwitchNotification mo10268get() {
        return provideInstance(this.channelIdProvider, this.notificationPresenterProvider, this.notificationIdProvider);
    }
}
