package com.amazon.alexa.handsfree.notification.views.languageswitching;

import com.amazon.alexa.handsfree.notification.views.NotificationStateProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class LanguageSwitchingMetadataProvider_Factory implements Factory<LanguageSwitchingMetadataProvider> {
    private final Provider<NotificationStateProvider> notificationStateProvider;

    public LanguageSwitchingMetadataProvider_Factory(Provider<NotificationStateProvider> provider) {
        this.notificationStateProvider = provider;
    }

    public static LanguageSwitchingMetadataProvider_Factory create(Provider<NotificationStateProvider> provider) {
        return new LanguageSwitchingMetadataProvider_Factory(provider);
    }

    public static LanguageSwitchingMetadataProvider newLanguageSwitchingMetadataProvider(NotificationStateProvider notificationStateProvider) {
        return new LanguageSwitchingMetadataProvider(notificationStateProvider);
    }

    public static LanguageSwitchingMetadataProvider provideInstance(Provider<NotificationStateProvider> provider) {
        return new LanguageSwitchingMetadataProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LanguageSwitchingMetadataProvider mo10268get() {
        return provideInstance(this.notificationStateProvider);
    }
}
