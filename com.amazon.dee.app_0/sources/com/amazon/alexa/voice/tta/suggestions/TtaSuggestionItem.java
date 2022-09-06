package com.amazon.alexa.voice.tta.suggestions;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.tta.simba.Suggestion;
import com.amazon.alexa.voice.tta.suggestions.AutoValue_TtaSuggestionItem;
import com.amazon.alexa.voice.ui.suggestions.SuggestionItem;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes11.dex */
public abstract class TtaSuggestionItem implements SuggestionItem {
    public static TtaSuggestionItem create(String str, String str2, String str3, String str4, String str5) {
        return new AutoValue_TtaSuggestionItem(str, str2, str3, str4, str5);
    }

    public static TypeAdapter<TtaSuggestionItem> typeAdapter(Gson gson) {
        return new AutoValue_TtaSuggestionItem.GsonTypeAdapter(gson);
    }

    @Override // com.amazon.alexa.voice.ui.suggestions.SuggestionItem
    public abstract String getAction();

    @Override // com.amazon.alexa.voice.ui.suggestions.SuggestionItem
    @Nullable
    public abstract String getActionData();

    @Override // com.amazon.alexa.voice.ui.suggestions.SuggestionItem
    @Nullable
    public abstract String getDescription();

    @Override // com.amazon.alexa.voice.ui.suggestions.SuggestionItem
    public abstract String getId();

    @Override // com.amazon.alexa.voice.ui.suggestions.SuggestionItem
    public abstract String getTitle();

    public static TtaSuggestionItem create(Suggestion suggestion) {
        return create(suggestion.getTitle(), suggestion.getDescription(), suggestion.getResultId(), suggestion.getActionType(), suggestion.getActionData());
    }
}
