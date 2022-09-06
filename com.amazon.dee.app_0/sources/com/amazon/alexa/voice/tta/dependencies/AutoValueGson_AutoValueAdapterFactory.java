package com.amazon.alexa.voice.tta.dependencies;

import com.amazon.alexa.voice.tta.suggestions.SimbaResponse;
import com.amazon.alexa.voice.tta.suggestions.SimbaSuggestionQuery;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionItem;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
/* loaded from: classes11.dex */
public final class AutoValueGson_AutoValueAdapterFactory extends AutoValueAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (SimbaResponse.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SimbaResponse.typeAdapter(gson);
        }
        if (SimbaSuggestionQuery.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SimbaSuggestionQuery.typeAdapter(gson);
        }
        if (!TtaSuggestionItem.class.isAssignableFrom(rawType)) {
            return null;
        }
        return (TypeAdapter<T>) TtaSuggestionItem.typeAdapter(gson);
    }
}
