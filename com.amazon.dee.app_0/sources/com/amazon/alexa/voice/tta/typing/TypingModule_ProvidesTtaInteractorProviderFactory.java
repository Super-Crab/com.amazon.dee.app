package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.tta.search.SearchInteractor;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionsInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesTtaInteractorProviderFactory implements Factory<TtaInteractorProvider> {
    private final TypingModule module;
    private final Provider<SearchInteractor> searchInteractorProvider;
    private final Provider<TtaSuggestionsInteractor> suggestionsInteractorProvider;
    private final Provider<TypingInteractor> typingInteractorProvider;

    public TypingModule_ProvidesTtaInteractorProviderFactory(TypingModule typingModule, Provider<TypingInteractor> provider, Provider<SearchInteractor> provider2, Provider<TtaSuggestionsInteractor> provider3) {
        this.module = typingModule;
        this.typingInteractorProvider = provider;
        this.searchInteractorProvider = provider2;
        this.suggestionsInteractorProvider = provider3;
    }

    public static TypingModule_ProvidesTtaInteractorProviderFactory create(TypingModule typingModule, Provider<TypingInteractor> provider, Provider<SearchInteractor> provider2, Provider<TtaSuggestionsInteractor> provider3) {
        return new TypingModule_ProvidesTtaInteractorProviderFactory(typingModule, provider, provider2, provider3);
    }

    public static TtaInteractorProvider provideInstance(TypingModule typingModule, Provider<TypingInteractor> provider, Provider<SearchInteractor> provider2, Provider<TtaSuggestionsInteractor> provider3) {
        return proxyProvidesTtaInteractorProvider(typingModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static TtaInteractorProvider proxyProvidesTtaInteractorProvider(TypingModule typingModule, TypingInteractor typingInteractor, SearchInteractor searchInteractor, TtaSuggestionsInteractor ttaSuggestionsInteractor) {
        return (TtaInteractorProvider) Preconditions.checkNotNull(typingModule.providesTtaInteractorProvider(typingInteractor, searchInteractor, ttaSuggestionsInteractor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaInteractorProvider mo10268get() {
        return provideInstance(this.module, this.typingInteractorProvider, this.searchInteractorProvider, this.suggestionsInteractorProvider);
    }
}
