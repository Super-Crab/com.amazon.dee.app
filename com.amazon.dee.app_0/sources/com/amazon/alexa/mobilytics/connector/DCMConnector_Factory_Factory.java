package com.amazon.alexa.mobilytics.connector;

import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import com.amazon.alexa.mobilytics.connector.DCMConnector;
import com.amazon.client.metrics.common.MetricsFactory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DCMConnector_Factory_Factory implements Factory<DCMConnector.Factory> {
    private final Provider<ApplicationConfiguration> applicationConfigurationProvider;
    private final Provider<DefaultRecordChecker> defaultRecordCheckerProvider;
    private final Provider<DeviceConfiguration> deviceConfigurationProvider;
    private final Provider<MetricsFactory> metricsFactoryLazyProvider;

    public DCMConnector_Factory_Factory(Provider<DeviceConfiguration> provider, Provider<ApplicationConfiguration> provider2, Provider<MetricsFactory> provider3, Provider<DefaultRecordChecker> provider4) {
        this.deviceConfigurationProvider = provider;
        this.applicationConfigurationProvider = provider2;
        this.metricsFactoryLazyProvider = provider3;
        this.defaultRecordCheckerProvider = provider4;
    }

    public static DCMConnector_Factory_Factory create(Provider<DeviceConfiguration> provider, Provider<ApplicationConfiguration> provider2, Provider<MetricsFactory> provider3, Provider<DefaultRecordChecker> provider4) {
        return new DCMConnector_Factory_Factory(provider, provider2, provider3, provider4);
    }

    public static DCMConnector.Factory newFactory(DeviceConfiguration deviceConfiguration, ApplicationConfiguration applicationConfiguration, Lazy<MetricsFactory> lazy, DefaultRecordChecker defaultRecordChecker) {
        return new DCMConnector.Factory(deviceConfiguration, applicationConfiguration, lazy, defaultRecordChecker);
    }

    public static DCMConnector.Factory provideInstance(Provider<DeviceConfiguration> provider, Provider<ApplicationConfiguration> provider2, Provider<MetricsFactory> provider3, Provider<DefaultRecordChecker> provider4) {
        return new DCMConnector.Factory(provider.mo10268get(), provider2.mo10268get(), DoubleCheck.lazy(provider3), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DCMConnector.Factory mo10268get() {
        return provideInstance(this.deviceConfigurationProvider, this.applicationConfigurationProvider, this.metricsFactoryLazyProvider, this.defaultRecordCheckerProvider);
    }
}
