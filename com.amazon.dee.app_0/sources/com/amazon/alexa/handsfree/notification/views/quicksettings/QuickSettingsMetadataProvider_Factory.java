package com.amazon.alexa.handsfree.notification.views.quicksettings;

import com.amazon.alexa.handsfree.notification.views.NotificationStateProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class QuickSettingsMetadataProvider_Factory implements Factory<QuickSettingsMetadataProvider> {
    private final Provider<NotificationStateProvider> notificationStateProvider;

    public QuickSettingsMetadataProvider_Factory(Provider<NotificationStateProvider> provider) {
        this.notificationStateProvider = provider;
    }

    public static QuickSettingsMetadataProvider_Factory create(Provider<NotificationStateProvider> provider) {
        return new QuickSettingsMetadataProvider_Factory(provider);
    }

    public static QuickSettingsMetadataProvider newQuickSettingsMetadataProvider(NotificationStateProvider notificationStateProvider) {
        return new QuickSettingsMetadataProvider(notificationStateProvider);
    }

    public static QuickSettingsMetadataProvider provideInstance(Provider<NotificationStateProvider> provider) {
        return new QuickSettingsMetadataProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public QuickSettingsMetadataProvider mo10268get() {
        return provideInstance(this.notificationStateProvider);
    }
}
