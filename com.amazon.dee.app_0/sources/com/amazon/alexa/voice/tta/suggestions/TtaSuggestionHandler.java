package com.amazon.alexa.voice.tta.suggestions;

import androidx.annotation.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler;
import com.amazon.alexa.voice.ui.tta.TtaSuggestionsListener;
import java.lang.ref.WeakReference;
/* loaded from: classes11.dex */
public class TtaSuggestionHandler implements SuggestionsHandler {
    private boolean isAppSearchEnabled;
    private WeakReference<TtaSuggestionsInteractor> ttaSuggestionsInteractorReference;

    @Override // com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler
    public void getSuggestions(String str) {
        if (!this.isAppSearchEnabled) {
            return;
        }
        Preconditions.notNull(this.ttaSuggestionsInteractorReference, "TtaSuggestionsHandler is not initialized");
        TtaSuggestionsInteractor ttaSuggestionsInteractor = this.ttaSuggestionsInteractorReference.get();
        if (ttaSuggestionsInteractor == null) {
            return;
        }
        ttaSuggestionsInteractor.getSuggestions(str);
    }

    public void initialize(@NonNull TtaSuggestionsInteractor ttaSuggestionsInteractor, @NonNull boolean z) {
        this.ttaSuggestionsInteractorReference = new WeakReference<>(ttaSuggestionsInteractor);
        this.isAppSearchEnabled = z;
    }

    @Override // com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler
    public void onSuggestionSelected(String str, String str2, String str3) {
        if (!this.isAppSearchEnabled) {
            return;
        }
        Preconditions.notNull(this.ttaSuggestionsInteractorReference, "TtaSuggestionsHandler is not initialized");
        TtaSuggestionsInteractor ttaSuggestionsInteractor = this.ttaSuggestionsInteractorReference.get();
        if (ttaSuggestionsInteractor == null) {
            return;
        }
        ttaSuggestionsInteractor.onSuggestionSelected(str, str2, str3);
    }

    @Override // com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler
    public void setSuggestionListener(TtaSuggestionsListener ttaSuggestionsListener) {
        if (!this.isAppSearchEnabled) {
            return;
        }
        Preconditions.notNull(this.ttaSuggestionsInteractorReference, "TtaSuggestionsHandler is not initialized");
        TtaSuggestionsInteractor ttaSuggestionsInteractor = this.ttaSuggestionsInteractorReference.get();
        if (ttaSuggestionsInteractor == null) {
            return;
        }
        ttaSuggestionsInteractor.setSuggestionListener(ttaSuggestionsListener);
    }
}
