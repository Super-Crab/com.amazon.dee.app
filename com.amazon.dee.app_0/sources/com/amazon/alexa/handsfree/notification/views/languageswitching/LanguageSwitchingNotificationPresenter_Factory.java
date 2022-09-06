package com.amazon.alexa.handsfree.notification.views.languageswitching;

import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.views.base.NotificationMetadataProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class LanguageSwitchingNotificationPresenter_Factory implements Factory<LanguageSwitchingNotificationPresenter> {
    private final Provider<DismissIntentProvider> dismissIntentProvider;
    private final Provider<HandsFreeSetupStateProvider> handsFreeSetupStateProvider;
    private final Provider<NotificationMetadataProvider> metadataProvider;
    private final Provider<NotificationType> notificationTypeProvider;

    public LanguageSwitchingNotificationPresenter_Factory(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3, Provider<HandsFreeSetupStateProvider> provider4) {
        this.dismissIntentProvider = provider;
        this.notificationTypeProvider = provider2;
        this.metadataProvider = provider3;
        this.handsFreeSetupStateProvider = provider4;
    }

    public static LanguageSwitchingNotificationPresenter_Factory create(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3, Provider<HandsFreeSetupStateProvider> provider4) {
        return new LanguageSwitchingNotificationPresenter_Factory(provider, provider2, provider3, provider4);
    }

    public static LanguageSwitchingNotificationPresenter newLanguageSwitchingNotificationPresenter(DismissIntentProvider dismissIntentProvider, NotificationType notificationType, NotificationMetadataProvider notificationMetadataProvider, HandsFreeSetupStateProvider handsFreeSetupStateProvider) {
        return new LanguageSwitchingNotificationPresenter(dismissIntentProvider, notificationType, notificationMetadataProvider, handsFreeSetupStateProvider);
    }

    public static LanguageSwitchingNotificationPresenter provideInstance(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<NotificationMetadataProvider> provider3, Provider<HandsFreeSetupStateProvider> provider4) {
        return new LanguageSwitchingNotificationPresenter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LanguageSwitchingNotificationPresenter mo10268get() {
        return provideInstance(this.dismissIntentProvider, this.notificationTypeProvider, this.metadataProvider, this.handsFreeSetupStateProvider);
    }
}
