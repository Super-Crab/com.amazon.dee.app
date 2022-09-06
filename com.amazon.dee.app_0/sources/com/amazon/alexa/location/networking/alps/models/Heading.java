package com.amazon.alexa.location.networking.alps.models;

import com.amazon.alexa.location.networking.alps.models.AutoValue_Heading;
import com.amazon.alexa.location.networking.alps.models.C$AutoValue_Heading;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes9.dex */
public abstract class Heading {

    @AutoValue.Builder
    /* loaded from: classes9.dex */
    static abstract class Builder {
        abstract Heading build();

        abstract Builder setAccuracyInDegrees(double d);

        abstract Builder setDirectionInDegrees(double d);
    }

    static Builder builder() {
        return new C$AutoValue_Heading.Builder();
    }

    public static TypeAdapter<Heading> typeAdapter(Gson gson) {
        return new AutoValue_Heading.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getAccuracyInDegrees();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getDirectionInDegrees();
}
