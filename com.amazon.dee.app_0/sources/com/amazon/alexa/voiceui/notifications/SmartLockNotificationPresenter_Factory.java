package com.amazon.alexa.voiceui.notifications;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SmartLockNotificationPresenter_Factory implements Factory<SmartLockNotificationPresenter> {
    private final Provider<SmartLockNotificationView> notificationViewProvider;
    private final Provider<SmartLockNotificationInteractor> notificationsInteractorProvider;

    public SmartLockNotificationPresenter_Factory(Provider<SmartLockNotificationView> provider, Provider<SmartLockNotificationInteractor> provider2) {
        this.notificationViewProvider = provider;
        this.notificationsInteractorProvider = provider2;
    }

    public static SmartLockNotificationPresenter_Factory create(Provider<SmartLockNotificationView> provider, Provider<SmartLockNotificationInteractor> provider2) {
        return new SmartLockNotificationPresenter_Factory(provider, provider2);
    }

    public static SmartLockNotificationPresenter newSmartLockNotificationPresenter(Object obj, Object obj2) {
        return new SmartLockNotificationPresenter((SmartLockNotificationView) obj, (SmartLockNotificationInteractor) obj2);
    }

    public static SmartLockNotificationPresenter provideInstance(Provider<SmartLockNotificationView> provider, Provider<SmartLockNotificationInteractor> provider2) {
        return new SmartLockNotificationPresenter(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SmartLockNotificationPresenter mo10268get() {
        return provideInstance(this.notificationViewProvider, this.notificationsInteractorProvider);
    }
}
