package com.amazon.alexa.handsfree.notification.views.permission;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class PermissionNotification_Factory implements Factory<PermissionNotification> {
    private final Provider<String> channelIdProvider;
    private final Provider<Integer> notificationIdProvider;
    private final Provider<PermissionNotificationPresenter> permissionNotificationPresenterProvider;

    public PermissionNotification_Factory(Provider<String> provider, Provider<PermissionNotificationPresenter> provider2, Provider<Integer> provider3) {
        this.channelIdProvider = provider;
        this.permissionNotificationPresenterProvider = provider2;
        this.notificationIdProvider = provider3;
    }

    public static PermissionNotification_Factory create(Provider<String> provider, Provider<PermissionNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new PermissionNotification_Factory(provider, provider2, provider3);
    }

    public static PermissionNotification newPermissionNotification(String str, Object obj, int i) {
        return new PermissionNotification(str, (PermissionNotificationPresenter) obj, i);
    }

    public static PermissionNotification provideInstance(Provider<String> provider, Provider<PermissionNotificationPresenter> provider2, Provider<Integer> provider3) {
        return new PermissionNotification(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().intValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PermissionNotification mo10268get() {
        return provideInstance(this.channelIdProvider, this.permissionNotificationPresenterProvider, this.notificationIdProvider);
    }
}
