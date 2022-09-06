package com.amazon.alexa.voice.tta;

import com.amazon.alexa.voice.locale.LocaleInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypeToAlexaModule_ProvideTypeToAlexaFeatureEnablerFactory implements Factory<TypeToAlexaFeatureEnabler> {
    private final Provider<LocaleInteractor> localeInteractorProvider;

    public TypeToAlexaModule_ProvideTypeToAlexaFeatureEnablerFactory(Provider<LocaleInteractor> provider) {
        this.localeInteractorProvider = provider;
    }

    public static TypeToAlexaModule_ProvideTypeToAlexaFeatureEnablerFactory create(Provider<LocaleInteractor> provider) {
        return new TypeToAlexaModule_ProvideTypeToAlexaFeatureEnablerFactory(provider);
    }

    public static TypeToAlexaFeatureEnabler provideInstance(Provider<LocaleInteractor> provider) {
        return proxyProvideTypeToAlexaFeatureEnabler(provider.mo10268get());
    }

    public static TypeToAlexaFeatureEnabler proxyProvideTypeToAlexaFeatureEnabler(LocaleInteractor localeInteractor) {
        return (TypeToAlexaFeatureEnabler) Preconditions.checkNotNull(TypeToAlexaModule.provideTypeToAlexaFeatureEnabler(localeInteractor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TypeToAlexaFeatureEnabler mo10268get() {
        return provideInstance(this.localeInteractorProvider);
    }
}
