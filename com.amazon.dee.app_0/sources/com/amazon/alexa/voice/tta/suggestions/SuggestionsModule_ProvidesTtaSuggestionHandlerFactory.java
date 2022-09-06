package com.amazon.alexa.voice.tta.suggestions;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class SuggestionsModule_ProvidesTtaSuggestionHandlerFactory implements Factory<TtaSuggestionHandler> {
    private final SuggestionsModule module;

    public SuggestionsModule_ProvidesTtaSuggestionHandlerFactory(SuggestionsModule suggestionsModule) {
        this.module = suggestionsModule;
    }

    public static SuggestionsModule_ProvidesTtaSuggestionHandlerFactory create(SuggestionsModule suggestionsModule) {
        return new SuggestionsModule_ProvidesTtaSuggestionHandlerFactory(suggestionsModule);
    }

    public static TtaSuggestionHandler provideInstance(SuggestionsModule suggestionsModule) {
        return proxyProvidesTtaSuggestionHandler(suggestionsModule);
    }

    public static TtaSuggestionHandler proxyProvidesTtaSuggestionHandler(SuggestionsModule suggestionsModule) {
        return (TtaSuggestionHandler) Preconditions.checkNotNull(suggestionsModule.providesTtaSuggestionHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaSuggestionHandler mo10268get() {
        return provideInstance(this.module);
    }
}
