package com.amazon.dee.app.dependencies;

import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.dee.app.services.appreviewrequest.AppReviewRequestService;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.services.testing.TestConfigurationService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideTestConfigurationServiceFactory implements Factory<TestConfigurationService> {
    private final Provider<AppReviewRequestService> appReviewRequestServiceProvider;
    private final Provider<CertificateReaderService> certificateReaderServiceProvider;
    private final Provider<CrashReportingService> crashServiceProvider;
    private final Provider<FeatureServiceConfiguration> featureServiceConfigurationLazyProvider;
    private final MainModule module;
    private final Provider<TestArgumentsService> testArgumentsProvider;

    public MainModule_ProvideTestConfigurationServiceFactory(MainModule mainModule, Provider<TestArgumentsService> provider, Provider<CrashReportingService> provider2, Provider<CertificateReaderService> provider3, Provider<AppReviewRequestService> provider4, Provider<FeatureServiceConfiguration> provider5) {
        this.module = mainModule;
        this.testArgumentsProvider = provider;
        this.crashServiceProvider = provider2;
        this.certificateReaderServiceProvider = provider3;
        this.appReviewRequestServiceProvider = provider4;
        this.featureServiceConfigurationLazyProvider = provider5;
    }

    public static MainModule_ProvideTestConfigurationServiceFactory create(MainModule mainModule, Provider<TestArgumentsService> provider, Provider<CrashReportingService> provider2, Provider<CertificateReaderService> provider3, Provider<AppReviewRequestService> provider4, Provider<FeatureServiceConfiguration> provider5) {
        return new MainModule_ProvideTestConfigurationServiceFactory(mainModule, provider, provider2, provider3, provider4, provider5);
    }

    public static TestConfigurationService provideInstance(MainModule mainModule, Provider<TestArgumentsService> provider, Provider<CrashReportingService> provider2, Provider<CertificateReaderService> provider3, Provider<AppReviewRequestService> provider4, Provider<FeatureServiceConfiguration> provider5) {
        return proxyProvideTestConfigurationService(mainModule, provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static TestConfigurationService proxyProvideTestConfigurationService(MainModule mainModule, TestArgumentsService testArgumentsService, Lazy<CrashReportingService> lazy, Lazy<CertificateReaderService> lazy2, Lazy<AppReviewRequestService> lazy3, Lazy<FeatureServiceConfiguration> lazy4) {
        return (TestConfigurationService) Preconditions.checkNotNull(mainModule.provideTestConfigurationService(testArgumentsService, lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TestConfigurationService mo10268get() {
        return provideInstance(this.module, this.testArgumentsProvider, this.crashServiceProvider, this.certificateReaderServiceProvider, this.appReviewRequestServiceProvider, this.featureServiceConfigurationLazyProvider);
    }
}
