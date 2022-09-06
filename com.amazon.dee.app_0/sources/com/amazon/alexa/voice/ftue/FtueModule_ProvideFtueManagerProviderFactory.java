package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.model.HandsFreeSupportChecker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FtueModule_ProvideFtueManagerProviderFactory implements Factory<FtueManagerProvider> {
    private final Provider<DlsFeatureEnabler> dlsFeatureEnablerProvider;
    private final Provider<FtuePreference> ftuePreferenceProvider;
    private final Provider<HandsFreeSupportChecker> handsFreeSupportCheckerProvider;
    private final Provider<LocaleInteractor> localeInteractorProvider;
    private final Provider<VoxMetricEventProcessingService> voxMetricEventProcessingServiceProvider;

    public FtueModule_ProvideFtueManagerProviderFactory(Provider<HandsFreeSupportChecker> provider, Provider<FtuePreference> provider2, Provider<VoxMetricEventProcessingService> provider3, Provider<LocaleInteractor> provider4, Provider<DlsFeatureEnabler> provider5) {
        this.handsFreeSupportCheckerProvider = provider;
        this.ftuePreferenceProvider = provider2;
        this.voxMetricEventProcessingServiceProvider = provider3;
        this.localeInteractorProvider = provider4;
        this.dlsFeatureEnablerProvider = provider5;
    }

    public static FtueModule_ProvideFtueManagerProviderFactory create(Provider<HandsFreeSupportChecker> provider, Provider<FtuePreference> provider2, Provider<VoxMetricEventProcessingService> provider3, Provider<LocaleInteractor> provider4, Provider<DlsFeatureEnabler> provider5) {
        return new FtueModule_ProvideFtueManagerProviderFactory(provider, provider2, provider3, provider4, provider5);
    }

    public static FtueManagerProvider provideInstance(Provider<HandsFreeSupportChecker> provider, Provider<FtuePreference> provider2, Provider<VoxMetricEventProcessingService> provider3, Provider<LocaleInteractor> provider4, Provider<DlsFeatureEnabler> provider5) {
        return proxyProvideFtueManagerProvider(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static FtueManagerProvider proxyProvideFtueManagerProvider(HandsFreeSupportChecker handsFreeSupportChecker, FtuePreference ftuePreference, VoxMetricEventProcessingService voxMetricEventProcessingService, LocaleInteractor localeInteractor, DlsFeatureEnabler dlsFeatureEnabler) {
        return (FtueManagerProvider) Preconditions.checkNotNull(FtueModule.provideFtueManagerProvider(handsFreeSupportChecker, ftuePreference, voxMetricEventProcessingService, localeInteractor, dlsFeatureEnabler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FtueManagerProvider mo10268get() {
        return provideInstance(this.handsFreeSupportCheckerProvider, this.ftuePreferenceProvider, this.voxMetricEventProcessingServiceProvider, this.localeInteractorProvider, this.dlsFeatureEnablerProvider);
    }
}
