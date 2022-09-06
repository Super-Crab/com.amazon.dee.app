package com.amazon.alexa.location.networking.alps.models;

import com.amazon.alexa.location.networking.alps.models.AutoValue_Speed;
import com.amazon.alexa.location.networking.alps.models.C$AutoValue_Speed;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes9.dex */
public abstract class Speed {

    @AutoValue.Builder
    /* loaded from: classes9.dex */
    static abstract class Builder {
        abstract Speed build();

        abstract Builder setAccuracyInMetersPerSecond(double d);

        abstract Builder setSpeedInMetersPerSecond(double d);
    }

    static Builder builder() {
        return new C$AutoValue_Speed.Builder();
    }

    public static TypeAdapter<Speed> typeAdapter(Gson gson) {
        return new AutoValue_Speed.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getAccuracyInMetersPerSecond();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getSpeedInMetersPerSecond();
}
