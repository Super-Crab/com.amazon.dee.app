package com.amazon.alexa.voice.ui.suggestions;

import java.util.List;
/* loaded from: classes11.dex */
public interface GetSuggestionsCallback {
    void onError(String str, Exception exc);

    void onResult(String str, List<SuggestionItem> list);
}
