package com.amazon.alexa.voice.features;

import com.amazon.alexa.voice.alerts.AlertsFeatureEnabler;
import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FeaturesModule_ProvideFeatureAvailabilityCheckerFactory implements Factory<FeatureAvailabilityObserver> {
    private final Provider<AlertsFeatureEnabler> alertsFeatureEnablerProvider;
    private final Provider<DlsFeatureEnabler> dlsFeatureEnablerProvider;
    private final Provider<VoiceFeatureChecker> featureCheckerProvider;
    private final Provider<TypeToAlexaFeatureEnabler> ttaFeatureEnablerProvider;

    public FeaturesModule_ProvideFeatureAvailabilityCheckerFactory(Provider<VoiceFeatureChecker> provider, Provider<AlertsFeatureEnabler> provider2, Provider<TypeToAlexaFeatureEnabler> provider3, Provider<DlsFeatureEnabler> provider4) {
        this.featureCheckerProvider = provider;
        this.alertsFeatureEnablerProvider = provider2;
        this.ttaFeatureEnablerProvider = provider3;
        this.dlsFeatureEnablerProvider = provider4;
    }

    public static FeaturesModule_ProvideFeatureAvailabilityCheckerFactory create(Provider<VoiceFeatureChecker> provider, Provider<AlertsFeatureEnabler> provider2, Provider<TypeToAlexaFeatureEnabler> provider3, Provider<DlsFeatureEnabler> provider4) {
        return new FeaturesModule_ProvideFeatureAvailabilityCheckerFactory(provider, provider2, provider3, provider4);
    }

    public static FeatureAvailabilityObserver provideInstance(Provider<VoiceFeatureChecker> provider, Provider<AlertsFeatureEnabler> provider2, Provider<TypeToAlexaFeatureEnabler> provider3, Provider<DlsFeatureEnabler> provider4) {
        return proxyProvideFeatureAvailabilityChecker(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static FeatureAvailabilityObserver proxyProvideFeatureAvailabilityChecker(Object obj, AlertsFeatureEnabler alertsFeatureEnabler, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler, DlsFeatureEnabler dlsFeatureEnabler) {
        return (FeatureAvailabilityObserver) Preconditions.checkNotNull(FeaturesModule.provideFeatureAvailabilityChecker((VoiceFeatureChecker) obj, alertsFeatureEnabler, typeToAlexaFeatureEnabler, dlsFeatureEnabler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureAvailabilityObserver mo10268get() {
        return provideInstance(this.featureCheckerProvider, this.alertsFeatureEnablerProvider, this.ttaFeatureEnablerProvider, this.dlsFeatureEnablerProvider);
    }
}
