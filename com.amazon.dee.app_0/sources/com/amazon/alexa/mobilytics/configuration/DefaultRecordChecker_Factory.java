package com.amazon.alexa.mobilytics.configuration;

import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultRecordChecker_Factory implements Factory<DefaultRecordChecker> {
    private final Provider<ApplicationConfiguration> applicationConfigurationProvider;
    private final Provider<ConfigManager> configManagerProvider;
    private final Provider<DefaultDeviceConfiguration> defaultDeviceConfigurationProvider;
    private final Provider<MobilyticsUserProvider> userProvider;

    public DefaultRecordChecker_Factory(Provider<DefaultDeviceConfiguration> provider, Provider<ApplicationConfiguration> provider2, Provider<ConfigManager> provider3, Provider<MobilyticsUserProvider> provider4) {
        this.defaultDeviceConfigurationProvider = provider;
        this.applicationConfigurationProvider = provider2;
        this.configManagerProvider = provider3;
        this.userProvider = provider4;
    }

    public static DefaultRecordChecker_Factory create(Provider<DefaultDeviceConfiguration> provider, Provider<ApplicationConfiguration> provider2, Provider<ConfigManager> provider3, Provider<MobilyticsUserProvider> provider4) {
        return new DefaultRecordChecker_Factory(provider, provider2, provider3, provider4);
    }

    public static DefaultRecordChecker newDefaultRecordChecker(DefaultDeviceConfiguration defaultDeviceConfiguration, ApplicationConfiguration applicationConfiguration, ConfigManager configManager, MobilyticsUserProvider mobilyticsUserProvider) {
        return new DefaultRecordChecker(defaultDeviceConfiguration, applicationConfiguration, configManager, mobilyticsUserProvider);
    }

    public static DefaultRecordChecker provideInstance(Provider<DefaultDeviceConfiguration> provider, Provider<ApplicationConfiguration> provider2, Provider<ConfigManager> provider3, Provider<MobilyticsUserProvider> provider4) {
        return new DefaultRecordChecker(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultRecordChecker mo10268get() {
        return provideInstance(this.defaultDeviceConfigurationProvider, this.applicationConfigurationProvider, this.configManagerProvider, this.userProvider);
    }
}
