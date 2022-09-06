package com.amazon.alexa.voice.locale;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class LocaleModule_ProvideLocaleInteractorFactory implements Factory<LocaleInteractor> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<DlsFeatureEnabler> dlsFeatureEnablerProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<LocaleAPI> localeAPIProvider;
    private final Provider<LocalePreference> localePreferenceProvider;
    private final Provider<VoxMetricEventProcessingService> voxMetricEventProcessingServiceProvider;

    public LocaleModule_ProvideLocaleInteractorFactory(Provider<AlexaServicesConnection> provider, Provider<LocaleAPI> provider2, Provider<LocalePreference> provider3, Provider<IdentityService> provider4, Provider<VoxMetricEventProcessingService> provider5, Provider<DlsFeatureEnabler> provider6) {
        this.alexaServicesConnectionProvider = provider;
        this.localeAPIProvider = provider2;
        this.localePreferenceProvider = provider3;
        this.identityServiceProvider = provider4;
        this.voxMetricEventProcessingServiceProvider = provider5;
        this.dlsFeatureEnablerProvider = provider6;
    }

    public static LocaleModule_ProvideLocaleInteractorFactory create(Provider<AlexaServicesConnection> provider, Provider<LocaleAPI> provider2, Provider<LocalePreference> provider3, Provider<IdentityService> provider4, Provider<VoxMetricEventProcessingService> provider5, Provider<DlsFeatureEnabler> provider6) {
        return new LocaleModule_ProvideLocaleInteractorFactory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static LocaleInteractor provideInstance(Provider<AlexaServicesConnection> provider, Provider<LocaleAPI> provider2, Provider<LocalePreference> provider3, Provider<IdentityService> provider4, Provider<VoxMetricEventProcessingService> provider5, Provider<DlsFeatureEnabler> provider6) {
        return proxyProvideLocaleInteractor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static LocaleInteractor proxyProvideLocaleInteractor(AlexaServicesConnection alexaServicesConnection, LocaleAPI localeAPI, LocalePreference localePreference, IdentityService identityService, VoxMetricEventProcessingService voxMetricEventProcessingService, DlsFeatureEnabler dlsFeatureEnabler) {
        return (LocaleInteractor) Preconditions.checkNotNull(LocaleModule.provideLocaleInteractor(alexaServicesConnection, localeAPI, localePreference, identityService, voxMetricEventProcessingService, dlsFeatureEnabler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocaleInteractor mo10268get() {
        return provideInstance(this.alexaServicesConnectionProvider, this.localeAPIProvider, this.localePreferenceProvider, this.identityServiceProvider, this.voxMetricEventProcessingServiceProvider, this.dlsFeatureEnablerProvider);
    }
}
