package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class MwsPivotsProtobufHandler_Factory implements Factory<MwsPivotsProtobufHandler> {
    private final Provider<ApplicationConfiguration> applicationConfigurationProvider;
    private final Provider<DeviceConfiguration> deviceConfigurationProvider;
    private final Provider<MobilyticsUserProvider> userProvider;

    public MwsPivotsProtobufHandler_Factory(Provider<ApplicationConfiguration> provider, Provider<DeviceConfiguration> provider2, Provider<MobilyticsUserProvider> provider3) {
        this.applicationConfigurationProvider = provider;
        this.deviceConfigurationProvider = provider2;
        this.userProvider = provider3;
    }

    public static MwsPivotsProtobufHandler_Factory create(Provider<ApplicationConfiguration> provider, Provider<DeviceConfiguration> provider2, Provider<MobilyticsUserProvider> provider3) {
        return new MwsPivotsProtobufHandler_Factory(provider, provider2, provider3);
    }

    public static MwsPivotsProtobufHandler newMwsPivotsProtobufHandler(ApplicationConfiguration applicationConfiguration, DeviceConfiguration deviceConfiguration, MobilyticsUserProvider mobilyticsUserProvider) {
        return new MwsPivotsProtobufHandler(applicationConfiguration, deviceConfiguration, mobilyticsUserProvider);
    }

    public static MwsPivotsProtobufHandler provideInstance(Provider<ApplicationConfiguration> provider, Provider<DeviceConfiguration> provider2, Provider<MobilyticsUserProvider> provider3) {
        return new MwsPivotsProtobufHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MwsPivotsProtobufHandler mo10268get() {
        return provideInstance(this.applicationConfigurationProvider, this.deviceConfigurationProvider, this.userProvider);
    }
}
