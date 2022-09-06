package com.amazon.alexa.voice.tta;

import com.amazon.alexa.voice.locale.LocaleInteractor;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypeToAlexaFeatureEnabler_Factory implements Factory<TypeToAlexaFeatureEnabler> {
    private final Provider<LocaleInteractor> localeInteractorProvider;

    public TypeToAlexaFeatureEnabler_Factory(Provider<LocaleInteractor> provider) {
        this.localeInteractorProvider = provider;
    }

    public static TypeToAlexaFeatureEnabler_Factory create(Provider<LocaleInteractor> provider) {
        return new TypeToAlexaFeatureEnabler_Factory(provider);
    }

    public static TypeToAlexaFeatureEnabler newTypeToAlexaFeatureEnabler(LocaleInteractor localeInteractor) {
        return new TypeToAlexaFeatureEnabler(localeInteractor);
    }

    public static TypeToAlexaFeatureEnabler provideInstance(Provider<LocaleInteractor> provider) {
        return new TypeToAlexaFeatureEnabler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TypeToAlexaFeatureEnabler mo10268get() {
        return provideInstance(this.localeInteractorProvider);
    }
}
