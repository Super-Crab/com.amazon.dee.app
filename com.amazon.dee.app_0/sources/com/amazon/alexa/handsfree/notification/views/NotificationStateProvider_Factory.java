package com.amazon.alexa.handsfree.notification.views;

import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class NotificationStateProvider_Factory implements Factory<NotificationStateProvider> {
    private final Provider<ConfigurationProvider> configurationProvider;

    public NotificationStateProvider_Factory(Provider<ConfigurationProvider> provider) {
        this.configurationProvider = provider;
    }

    public static NotificationStateProvider_Factory create(Provider<ConfigurationProvider> provider) {
        return new NotificationStateProvider_Factory(provider);
    }

    public static NotificationStateProvider newNotificationStateProvider(ConfigurationProvider configurationProvider) {
        return new NotificationStateProvider(configurationProvider);
    }

    public static NotificationStateProvider provideInstance(Provider<ConfigurationProvider> provider) {
        return new NotificationStateProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationStateProvider mo10268get() {
        return provideInstance(this.configurationProvider);
    }
}
