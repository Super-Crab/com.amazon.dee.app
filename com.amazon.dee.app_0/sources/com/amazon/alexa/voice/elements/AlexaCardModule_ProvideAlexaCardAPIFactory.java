package com.amazon.alexa.voice.elements;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class AlexaCardModule_ProvideAlexaCardAPIFactory implements Factory<AlexaCardAPI> {
    private static final AlexaCardModule_ProvideAlexaCardAPIFactory INSTANCE = new AlexaCardModule_ProvideAlexaCardAPIFactory();

    public static AlexaCardModule_ProvideAlexaCardAPIFactory create() {
        return INSTANCE;
    }

    public static AlexaCardAPI provideInstance() {
        return proxyProvideAlexaCardAPI();
    }

    public static AlexaCardAPI proxyProvideAlexaCardAPI() {
        return (AlexaCardAPI) Preconditions.checkNotNull(AlexaCardModule.provideAlexaCardAPI(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCardAPI mo10268get() {
        return provideInstance();
    }
}
