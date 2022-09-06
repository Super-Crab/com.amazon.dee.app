package com.amazon.alexa.voice.tta.suggestions;

import com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public class SuggestionsModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public SuggestionsHandler providesSuggestionsHandler(TtaSuggestionHandler ttaSuggestionHandler) {
        return ttaSuggestionHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaSuggestionHandler providesTtaSuggestionHandler() {
        return new TtaSuggestionHandler();
    }
}
