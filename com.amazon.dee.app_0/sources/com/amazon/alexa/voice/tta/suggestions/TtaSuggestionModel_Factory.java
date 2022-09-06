package com.amazon.alexa.voice.tta.suggestions;

import android.content.Context;
import com.amazon.alexa.voice.tta.sdk.AlexaClientSdkApis;
import com.amazon.alexa.voice.tta.statemachine.SimbaMediator;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TtaSuggestionModel_Factory implements Factory<TtaSuggestionModel> {
    private final Provider<Context> contextProvider;
    private final Provider<AlexaClientSdkApis> sdkApisProvider;
    private final Provider<SimbaMediator> simbaMediatorProvider;

    public TtaSuggestionModel_Factory(Provider<SimbaMediator> provider, Provider<Context> provider2, Provider<AlexaClientSdkApis> provider3) {
        this.simbaMediatorProvider = provider;
        this.contextProvider = provider2;
        this.sdkApisProvider = provider3;
    }

    public static TtaSuggestionModel_Factory create(Provider<SimbaMediator> provider, Provider<Context> provider2, Provider<AlexaClientSdkApis> provider3) {
        return new TtaSuggestionModel_Factory(provider, provider2, provider3);
    }

    public static TtaSuggestionModel newTtaSuggestionModel(SimbaMediator simbaMediator, Context context, AlexaClientSdkApis alexaClientSdkApis) {
        return new TtaSuggestionModel(simbaMediator, context, alexaClientSdkApis);
    }

    public static TtaSuggestionModel provideInstance(Provider<SimbaMediator> provider, Provider<Context> provider2, Provider<AlexaClientSdkApis> provider3) {
        return new TtaSuggestionModel(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaSuggestionModel mo10268get() {
        return provideInstance(this.simbaMediatorProvider, this.contextProvider, this.sdkApisProvider);
    }
}
