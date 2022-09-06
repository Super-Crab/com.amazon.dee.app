package com.amazon.alexa.mobilytics.configuration;

import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultApplicationConfiguration_Factory implements Factory<DefaultApplicationConfiguration> {
    private final Provider<MobilyticsConfiguration> configProvider;

    public DefaultApplicationConfiguration_Factory(Provider<MobilyticsConfiguration> provider) {
        this.configProvider = provider;
    }

    public static DefaultApplicationConfiguration_Factory create(Provider<MobilyticsConfiguration> provider) {
        return new DefaultApplicationConfiguration_Factory(provider);
    }

    public static DefaultApplicationConfiguration newDefaultApplicationConfiguration(MobilyticsConfiguration mobilyticsConfiguration) {
        return new DefaultApplicationConfiguration(mobilyticsConfiguration);
    }

    public static DefaultApplicationConfiguration provideInstance(Provider<MobilyticsConfiguration> provider) {
        return new DefaultApplicationConfiguration(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultApplicationConfiguration mo10268get() {
        return provideInstance(this.configProvider);
    }
}
