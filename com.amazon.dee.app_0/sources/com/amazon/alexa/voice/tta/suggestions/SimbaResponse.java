package com.amazon.alexa.voice.tta.suggestions;

import com.amazon.alexa.voice.tta.suggestions.AutoValue_SimbaResponse;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
@AutoValue
/* loaded from: classes11.dex */
public abstract class SimbaResponse {
    public static SimbaResponse create(List<TtaSuggestionItem> list, SimbaSuggestionQuery simbaSuggestionQuery) {
        return new AutoValue_SimbaResponse(list, simbaSuggestionQuery);
    }

    public static TypeAdapter<SimbaResponse> typeAdapter(Gson gson) {
        return new AutoValue_SimbaResponse.GsonTypeAdapter(gson);
    }

    public abstract SimbaSuggestionQuery getSuggestionQuery();

    public abstract List<TtaSuggestionItem> getSuggestionResults();
}
