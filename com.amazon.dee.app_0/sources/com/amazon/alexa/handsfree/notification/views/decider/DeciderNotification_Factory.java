package com.amazon.alexa.handsfree.notification.views.decider;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DeciderNotification_Factory implements Factory<DeciderNotification> {
    private final Provider<String> channelIdProvider;
    private final Provider<DeciderNotificationPresenter> deciderNotificationPresenterProvider;
    private final Provider<Integer> notificationIdProvider;

    public DeciderNotification_Factory(Provider<String> provider, Provider<DeciderNotificationPresenter> provider2, Provider<Integer> provider3) {
        this.channelIdProvider = provider;
        this.deciderNotificationPresenterProvider = provider2;
        this.notificationIdProvider = provider3;
    }

    public static DeciderNotification_Factory create(Provider<String> provider, Provider<DeciderNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new DeciderNotification_Factory(provider, provider2, provider3);
    }

    public static DeciderNotification newDeciderNotification(String str, DeciderNotificationPresenter deciderNotificationPresenter, int i) {
        return new DeciderNotification(str, deciderNotificationPresenter, i);
    }

    public static DeciderNotification provideInstance(Provider<String> provider, Provider<DeciderNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new DeciderNotification(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().intValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeciderNotification mo10268get() {
        return provideInstance(this.channelIdProvider, this.deciderNotificationPresenterProvider, this.notificationIdProvider);
    }
}
