package com.amazon.alexa.handsfree.notification.views.lockscreen;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class ShowOnLockscreenNotification_Factory implements Factory<ShowOnLockscreenNotification> {
    private final Provider<String> channelIdProvider;
    private final Provider<Integer> notificationIdProvider;
    private final Provider<ShowOnLockscreenNotificationPresenter> notificationPresenterProvider;

    public ShowOnLockscreenNotification_Factory(Provider<String> provider, Provider<ShowOnLockscreenNotificationPresenter> provider2, Provider<Integer> provider3) {
        this.channelIdProvider = provider;
        this.notificationPresenterProvider = provider2;
        this.notificationIdProvider = provider3;
    }

    public static ShowOnLockscreenNotification_Factory create(Provider<String> provider, Provider<ShowOnLockscreenNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new ShowOnLockscreenNotification_Factory(provider, provider2, provider3);
    }

    public static ShowOnLockscreenNotification newShowOnLockscreenNotification(String str, Object obj, int i) {
        return new ShowOnLockscreenNotification(str, (ShowOnLockscreenNotificationPresenter) obj, i);
    }

    public static ShowOnLockscreenNotification provideInstance(Provider<String> provider, Provider<ShowOnLockscreenNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new ShowOnLockscreenNotification(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().intValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ShowOnLockscreenNotification mo10268get() {
        return provideInstance(this.channelIdProvider, this.notificationPresenterProvider, this.notificationIdProvider);
    }
}
