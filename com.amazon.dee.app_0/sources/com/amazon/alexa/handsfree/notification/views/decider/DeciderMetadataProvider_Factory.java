package com.amazon.alexa.handsfree.notification.views.decider;

import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import com.amazon.alexa.handsfree.notification.views.NotificationStateProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DeciderMetadataProvider_Factory implements Factory<DeciderMetadataProvider> {
    private final Provider<HandsFreeSetupStateProvider> handsFreeSetupStateProvider;
    private final Provider<NotificationStateProvider> notificationStateProvider;

    public DeciderMetadataProvider_Factory(Provider<NotificationStateProvider> provider, Provider<HandsFreeSetupStateProvider> provider2) {
        this.notificationStateProvider = provider;
        this.handsFreeSetupStateProvider = provider2;
    }

    public static DeciderMetadataProvider_Factory create(Provider<NotificationStateProvider> provider, Provider<HandsFreeSetupStateProvider> provider2) {
        return new DeciderMetadataProvider_Factory(provider, provider2);
    }

    public static DeciderMetadataProvider newDeciderMetadataProvider(NotificationStateProvider notificationStateProvider, HandsFreeSetupStateProvider handsFreeSetupStateProvider) {
        return new DeciderMetadataProvider(notificationStateProvider, handsFreeSetupStateProvider);
    }

    public static DeciderMetadataProvider provideInstance(Provider<NotificationStateProvider> provider, Provider<HandsFreeSetupStateProvider> provider2) {
        return new DeciderMetadataProvider(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeciderMetadataProvider mo10268get() {
        return provideInstance(this.notificationStateProvider, this.handsFreeSetupStateProvider);
    }
}
