package com.amazon.alexa.voice.tta.suggestions;

import com.amazon.alexa.voice.tta.suggestions.AutoValue_SimbaSuggestionQuery;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes11.dex */
public abstract class SimbaSuggestionQuery {
    public static SimbaSuggestionQuery create(String str, String str2) {
        return new AutoValue_SimbaSuggestionQuery(str, str2);
    }

    public static TypeAdapter<SimbaSuggestionQuery> typeAdapter(Gson gson) {
        return new AutoValue_SimbaSuggestionQuery.GsonTypeAdapter(gson);
    }

    public abstract String getQueryId();

    public abstract String getQueryText();
}
