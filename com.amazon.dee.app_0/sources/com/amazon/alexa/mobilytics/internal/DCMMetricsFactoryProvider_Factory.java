package com.amazon.alexa.mobilytics.internal;

import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DCMMetricsFactoryProvider_Factory implements Factory<DCMMetricsFactoryProvider> {
    private final Provider<MobilyticsConfiguration> configurationProvider;

    public DCMMetricsFactoryProvider_Factory(Provider<MobilyticsConfiguration> provider) {
        this.configurationProvider = provider;
    }

    public static DCMMetricsFactoryProvider_Factory create(Provider<MobilyticsConfiguration> provider) {
        return new DCMMetricsFactoryProvider_Factory(provider);
    }

    public static DCMMetricsFactoryProvider newDCMMetricsFactoryProvider(MobilyticsConfiguration mobilyticsConfiguration) {
        return new DCMMetricsFactoryProvider(mobilyticsConfiguration);
    }

    public static DCMMetricsFactoryProvider provideInstance(Provider<MobilyticsConfiguration> provider) {
        return new DCMMetricsFactoryProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DCMMetricsFactoryProvider mo10268get() {
        return provideInstance(this.configurationProvider);
    }
}
