package com.amazon.alexa.voice.tta;

import com.amazon.alexa.voice.locale.LocaleInteractor;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class TypeToAlexaModule {
    private TypeToAlexaModule() {
    }

    @Provides
    @Singleton
    public static TypeToAlexaFeatureEnabler provideTypeToAlexaFeatureEnabler(LocaleInteractor localeInteractor) {
        return new TypeToAlexaFeatureEnabler(localeInteractor);
    }
}
