package com.amazon.alexa.handsfree.notification.views.killswitch;

import com.amazon.alexa.handsfree.notification.api.ConfigurationProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class KillSwitchMetadataProvider_Factory implements Factory<KillSwitchMetadataProvider> {
    private final Provider<ConfigurationProvider> configurationProvider;

    public KillSwitchMetadataProvider_Factory(Provider<ConfigurationProvider> provider) {
        this.configurationProvider = provider;
    }

    public static KillSwitchMetadataProvider_Factory create(Provider<ConfigurationProvider> provider) {
        return new KillSwitchMetadataProvider_Factory(provider);
    }

    public static KillSwitchMetadataProvider newKillSwitchMetadataProvider(ConfigurationProvider configurationProvider) {
        return new KillSwitchMetadataProvider(configurationProvider);
    }

    public static KillSwitchMetadataProvider provideInstance(Provider<ConfigurationProvider> provider) {
        return new KillSwitchMetadataProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KillSwitchMetadataProvider mo10268get() {
        return provideInstance(this.configurationProvider);
    }
}
