package com.amazon.alexa.handsfree.notification.views.permission;

import com.amazon.alexa.handsfree.notification.DismissIntentProvider;
import com.amazon.alexa.handsfree.notification.api.HandsFreeSetupStateProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class PermissionNotificationPresenter_Factory implements Factory<PermissionNotificationPresenter> {
    private final Provider<DismissIntentProvider> dismissIntentProvider;
    private final Provider<HandsFreeSetupStateProvider> handsFreeSetupStateProvider;
    private final Provider<PermissionMetadataProvider> permissionMetadataProvider;

    public PermissionNotificationPresenter_Factory(Provider<DismissIntentProvider> provider, Provider<PermissionMetadataProvider> provider2, Provider<HandsFreeSetupStateProvider> provider3) {
        this.dismissIntentProvider = provider;
        this.permissionMetadataProvider = provider2;
        this.handsFreeSetupStateProvider = provider3;
    }

    public static PermissionNotificationPresenter_Factory create(Provider<DismissIntentProvider> provider, Provider<PermissionMetadataProvider> provider2, Provider<HandsFreeSetupStateProvider> provider3) {
        return new PermissionNotificationPresenter_Factory(provider, provider2, provider3);
    }

    public static PermissionNotificationPresenter newPermissionNotificationPresenter(DismissIntentProvider dismissIntentProvider, PermissionMetadataProvider permissionMetadataProvider, HandsFreeSetupStateProvider handsFreeSetupStateProvider) {
        return new PermissionNotificationPresenter(dismissIntentProvider, permissionMetadataProvider, handsFreeSetupStateProvider);
    }

    public static PermissionNotificationPresenter provideInstance(Provider<DismissIntentProvider> provider, Provider<PermissionMetadataProvider> provider2, Provider<HandsFreeSetupStateProvider> provider3) {
        return new PermissionNotificationPresenter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PermissionNotificationPresenter mo10268get() {
        return provideInstance(this.dismissIntentProvider, this.permissionMetadataProvider, this.handsFreeSetupStateProvider);
    }
}
