package com.amazon.alexa.sendtoapp.activitycard.model;

import com.amazon.alexa.sendtoapp.activitycard.model.AutoValue_P13NMetadata;
import com.amazon.alexa.sendtoapp.activitycard.model.C$AutoValue_P13NMetadata;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes10.dex */
public abstract class P13NMetadata {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract P13NMetadata build();

        public abstract Builder setSection(String str);
    }

    public static Builder builder() {
        return new C$AutoValue_P13NMetadata.Builder();
    }

    public static TypeAdapter<P13NMetadata> typeAdapter(Gson gson) {
        return new AutoValue_P13NMetadata.GsonTypeAdapter(gson);
    }

    public abstract String getSection();
}
