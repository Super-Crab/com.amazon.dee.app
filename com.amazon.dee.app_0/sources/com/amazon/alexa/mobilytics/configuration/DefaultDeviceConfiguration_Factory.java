package com.amazon.alexa.mobilytics.configuration;

import android.content.Context;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultDeviceConfiguration_Factory implements Factory<DefaultDeviceConfiguration> {
    private final Provider<MobilyticsConfiguration> configProvider;
    private final Provider<Context> contextProvider;

    public DefaultDeviceConfiguration_Factory(Provider<Context> provider, Provider<MobilyticsConfiguration> provider2) {
        this.contextProvider = provider;
        this.configProvider = provider2;
    }

    public static DefaultDeviceConfiguration_Factory create(Provider<Context> provider, Provider<MobilyticsConfiguration> provider2) {
        return new DefaultDeviceConfiguration_Factory(provider, provider2);
    }

    public static DefaultDeviceConfiguration newDefaultDeviceConfiguration(Context context, MobilyticsConfiguration mobilyticsConfiguration) {
        return new DefaultDeviceConfiguration(context, mobilyticsConfiguration);
    }

    public static DefaultDeviceConfiguration provideInstance(Provider<Context> provider, Provider<MobilyticsConfiguration> provider2) {
        return new DefaultDeviceConfiguration(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultDeviceConfiguration mo10268get() {
        return provideInstance(this.contextProvider, this.configProvider);
    }
}
