package com.amazon.alexa.externalnotifications.capability.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.externalnotifications.capability.models.AutoValue_Source;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Source {
    public static Source create(Contact contact, SourceType sourceType) {
        return create(contact, null, sourceType);
    }

    public static TypeAdapter<Source> typeAdapter(Gson gson) {
        return new AutoValue_Source.GsonTypeAdapter(gson);
    }

    public abstract Contact getContact();

    @Nullable
    public abstract Group getGroup();

    public abstract SourceType getType();

    public static Source create(Contact contact, Group group, SourceType sourceType) {
        return new AutoValue_Source(contact, group, sourceType);
    }
}
