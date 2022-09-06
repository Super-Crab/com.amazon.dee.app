package com.amazon.alexa.externalnotifications.capability.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.externalnotifications.capability.models.AutoValue_Content;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Content {
    public static Content create(String str) {
        return create(null, str);
    }

    public static TypeAdapter<Content> typeAdapter(Gson gson) {
        return new AutoValue_Content.GsonTypeAdapter(gson);
    }

    public abstract String getBody();

    @Nullable
    public abstract String getTitle();

    public static Content create(String str, String str2) {
        return new AutoValue_Content(str, str2);
    }
}
