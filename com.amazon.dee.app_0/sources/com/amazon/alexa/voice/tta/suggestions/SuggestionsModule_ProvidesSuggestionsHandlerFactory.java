package com.amazon.alexa.voice.tta.suggestions;

import com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SuggestionsModule_ProvidesSuggestionsHandlerFactory implements Factory<SuggestionsHandler> {
    private final SuggestionsModule module;
    private final Provider<TtaSuggestionHandler> ttaSuggestionHandlerProvider;

    public SuggestionsModule_ProvidesSuggestionsHandlerFactory(SuggestionsModule suggestionsModule, Provider<TtaSuggestionHandler> provider) {
        this.module = suggestionsModule;
        this.ttaSuggestionHandlerProvider = provider;
    }

    public static SuggestionsModule_ProvidesSuggestionsHandlerFactory create(SuggestionsModule suggestionsModule, Provider<TtaSuggestionHandler> provider) {
        return new SuggestionsModule_ProvidesSuggestionsHandlerFactory(suggestionsModule, provider);
    }

    public static SuggestionsHandler provideInstance(SuggestionsModule suggestionsModule, Provider<TtaSuggestionHandler> provider) {
        return proxyProvidesSuggestionsHandler(suggestionsModule, provider.mo10268get());
    }

    public static SuggestionsHandler proxyProvidesSuggestionsHandler(SuggestionsModule suggestionsModule, TtaSuggestionHandler ttaSuggestionHandler) {
        return (SuggestionsHandler) Preconditions.checkNotNull(suggestionsModule.providesSuggestionsHandler(ttaSuggestionHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SuggestionsHandler mo10268get() {
        return provideInstance(this.module, this.ttaSuggestionHandlerProvider);
    }
}
