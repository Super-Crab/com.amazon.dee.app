package com.amazon.alexa.handsfree.notification.views.killswitch;

import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class KillSwitchNotificationPresenter_Factory implements Factory<KillSwitchNotificationPresenter> {
    private final Provider<DismissIntentProvider> dismissIntentProvider;
    private final Provider<KillSwitchMetadataProvider> metadataProvider;
    private final Provider<NotificationType> notificationTypeProvider;

    public KillSwitchNotificationPresenter_Factory(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<KillSwitchMetadataProvider> provider3) {
        this.dismissIntentProvider = provider;
        this.notificationTypeProvider = provider2;
        this.metadataProvider = provider3;
    }

    public static KillSwitchNotificationPresenter_Factory create(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<KillSwitchMetadataProvider> provider3) {
        return new KillSwitchNotificationPresenter_Factory(provider, provider2, provider3);
    }

    public static KillSwitchNotificationPresenter newKillSwitchNotificationPresenter(DismissIntentProvider dismissIntentProvider, NotificationType notificationType, Object obj) {
        return new KillSwitchNotificationPresenter(dismissIntentProvider, notificationType, (KillSwitchMetadataProvider) obj);
    }

    public static KillSwitchNotificationPresenter provideInstance(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<KillSwitchMetadataProvider> provider3) {
        return new KillSwitchNotificationPresenter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KillSwitchNotificationPresenter mo10268get() {
        return provideInstance(this.dismissIntentProvider, this.notificationTypeProvider, this.metadataProvider);
    }
}
