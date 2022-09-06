package com.amazon.alexa.handsfree.notification.views.decider;

import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DeciderNotificationPresenter_Factory implements Factory<DeciderNotificationPresenter> {
    private final Provider<DeciderMetadataProvider> deciderMetadataProvider;
    private final Provider<DismissIntentProvider> dismissIntentProvider;
    private final Provider<HandsFreeSetupStateProvider> handsFreeSetupStateProvider;
    private final Provider<NotificationType> notificationTypeProvider;

    public DeciderNotificationPresenter_Factory(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<DeciderMetadataProvider> provider3, Provider<HandsFreeSetupStateProvider> provider4) {
        this.dismissIntentProvider = provider;
        this.notificationTypeProvider = provider2;
        this.deciderMetadataProvider = provider3;
        this.handsFreeSetupStateProvider = provider4;
    }

    public static DeciderNotificationPresenter_Factory create(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<DeciderMetadataProvider> provider3, Provider<HandsFreeSetupStateProvider> provider4) {
        return new DeciderNotificationPresenter_Factory(provider, provider2, provider3, provider4);
    }

    public static DeciderNotificationPresenter newDeciderNotificationPresenter(DismissIntentProvider dismissIntentProvider, NotificationType notificationType, DeciderMetadataProvider deciderMetadataProvider, HandsFreeSetupStateProvider handsFreeSetupStateProvider) {
        return new DeciderNotificationPresenter(dismissIntentProvider, notificationType, deciderMetadataProvider, handsFreeSetupStateProvider);
    }

    public static DeciderNotificationPresenter provideInstance(Provider<DismissIntentProvider> provider, Provider<NotificationType> provider2, Provider<DeciderMetadataProvider> provider3, Provider<HandsFreeSetupStateProvider> provider4) {
        return new DeciderNotificationPresenter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeciderNotificationPresenter mo10268get() {
        return provideInstance(this.dismissIntentProvider, this.notificationTypeProvider, this.deciderMetadataProvider, this.handsFreeSetupStateProvider);
    }
}
