package com.amazon.alexa.handsfree.notification.views.lockscreen;

import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class ShowOnLockscreenNotificationPresenter_Factory implements Factory<ShowOnLockscreenNotificationPresenter> {
    private final Provider<DismissIntentProvider> dismissIntentProvider;
    private final Provider<NotificationMetadataProvider> metadataProvider;
    private final Provider<NotificationType> notificationTypeProvider;

    public ShowOnLockscreenNotificationPresenter_Factory(Provider<DismissIntentProvider> provider, Provider<NotificationMetadataProvider> provider2, Provider<NotificationType> provider3) {
        this.dismissIntentProvider = provider;
        this.metadataProvider = provider2;
        this.notificationTypeProvider = provider3;
    }

    public static ShowOnLockscreenNotificationPresenter_Factory create(Provider<DismissIntentProvider> provider, Provider<NotificationMetadataProvider> provider2, Provider<NotificationType> provider3) {
        return new ShowOnLockscreenNotificationPresenter_Factory(provider, provider2, provider3);
    }

    public static ShowOnLockscreenNotificationPresenter newShowOnLockscreenNotificationPresenter(DismissIntentProvider dismissIntentProvider, NotificationMetadataProvider notificationMetadataProvider, NotificationType notificationType) {
        return new ShowOnLockscreenNotificationPresenter(dismissIntentProvider, notificationMetadataProvider, notificationType);
    }

    public static ShowOnLockscreenNotificationPresenter provideInstance(Provider<DismissIntentProvider> provider, Provider<NotificationMetadataProvider> provider2, Provider<NotificationType> provider3) {
        return new ShowOnLockscreenNotificationPresenter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ShowOnLockscreenNotificationPresenter mo10268get() {
        return provideInstance(this.dismissIntentProvider, this.metadataProvider, this.notificationTypeProvider);
    }
}
