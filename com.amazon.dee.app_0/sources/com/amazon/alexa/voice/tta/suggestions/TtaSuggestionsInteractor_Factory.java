package com.amazon.alexa.voice.tta.suggestions;

import android.content.Context;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TtaSuggestionsInteractor_Factory implements Factory<TtaSuggestionsInteractor> {
    private final Provider<Context> contextProvider;
    private final Provider<TtaSuggestionHandler> suggestionHandlerProvider;
    private final Provider<TtaSuggestionModel> suggestionModelProvider;
    private final Provider<TtaNavigator> ttaNavigatorProvider;

    public TtaSuggestionsInteractor_Factory(Provider<TtaSuggestionModel> provider, Provider<TtaSuggestionHandler> provider2, Provider<TtaNavigator> provider3, Provider<Context> provider4) {
        this.suggestionModelProvider = provider;
        this.suggestionHandlerProvider = provider2;
        this.ttaNavigatorProvider = provider3;
        this.contextProvider = provider4;
    }

    public static TtaSuggestionsInteractor_Factory create(Provider<TtaSuggestionModel> provider, Provider<TtaSuggestionHandler> provider2, Provider<TtaNavigator> provider3, Provider<Context> provider4) {
        return new TtaSuggestionsInteractor_Factory(provider, provider2, provider3, provider4);
    }

    public static TtaSuggestionsInteractor newTtaSuggestionsInteractor(TtaSuggestionModel ttaSuggestionModel, TtaSuggestionHandler ttaSuggestionHandler, TtaNavigator ttaNavigator, Context context) {
        return new TtaSuggestionsInteractor(ttaSuggestionModel, ttaSuggestionHandler, ttaNavigator, context);
    }

    public static TtaSuggestionsInteractor provideInstance(Provider<TtaSuggestionModel> provider, Provider<TtaSuggestionHandler> provider2, Provider<TtaNavigator> provider3, Provider<Context> provider4) {
        return new TtaSuggestionsInteractor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaSuggestionsInteractor mo10268get() {
        return provideInstance(this.suggestionModelProvider, this.suggestionHandlerProvider, this.ttaNavigatorProvider, this.contextProvider);
    }
}
