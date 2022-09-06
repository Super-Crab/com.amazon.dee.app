package com.amazon.alexa.mobilytics.event.serializer.handlers;

import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class MwsPivotsDataHandler_Factory implements Factory<MwsPivotsDataHandler> {
    private final Provider<ApplicationConfiguration> applicationConfigurationProvider;
    private final Provider<DeviceConfiguration> deviceConfigurationProvider;
    private final Provider<MobilyticsUserProvider> userProvider;

    public MwsPivotsDataHandler_Factory(Provider<ApplicationConfiguration> provider, Provider<DeviceConfiguration> provider2, Provider<MobilyticsUserProvider> provider3) {
        this.applicationConfigurationProvider = provider;
        this.deviceConfigurationProvider = provider2;
        this.userProvider = provider3;
    }

    public static MwsPivotsDataHandler_Factory create(Provider<ApplicationConfiguration> provider, Provider<DeviceConfiguration> provider2, Provider<MobilyticsUserProvider> provider3) {
        return new MwsPivotsDataHandler_Factory(provider, provider2, provider3);
    }

    public static MwsPivotsDataHandler newMwsPivotsDataHandler(ApplicationConfiguration applicationConfiguration, DeviceConfiguration deviceConfiguration, MobilyticsUserProvider mobilyticsUserProvider) {
        return new MwsPivotsDataHandler(applicationConfiguration, deviceConfiguration, mobilyticsUserProvider);
    }

    public static MwsPivotsDataHandler provideInstance(Provider<ApplicationConfiguration> provider, Provider<DeviceConfiguration> provider2, Provider<MobilyticsUserProvider> provider3) {
        return new MwsPivotsDataHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MwsPivotsDataHandler mo10268get() {
        return provideInstance(this.applicationConfigurationProvider, this.deviceConfigurationProvider, this.userProvider);
    }
}
