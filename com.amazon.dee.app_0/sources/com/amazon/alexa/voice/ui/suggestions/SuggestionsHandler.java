package com.amazon.alexa.voice.ui.suggestions;

import com.amazon.alexa.voice.ui.tta.TtaSuggestionsListener;
/* loaded from: classes11.dex */
public interface SuggestionsHandler {
    void getSuggestions(String str);

    void onSuggestionSelected(String str, String str2, String str3);

    void setSuggestionListener(TtaSuggestionsListener ttaSuggestionsListener);
}
