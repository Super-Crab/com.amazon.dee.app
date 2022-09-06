package com.amazon.alexa.location.networking.alps.models;

import com.amazon.alexa.location.networking.alps.models.AutoValue_Altitude;
import com.amazon.alexa.location.networking.alps.models.C$AutoValue_Altitude;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes9.dex */
public abstract class Altitude {

    @AutoValue.Builder
    /* loaded from: classes9.dex */
    static abstract class Builder {
        abstract Altitude build();

        abstract Builder setAccuracyInMeters(double d);

        abstract Builder setAltitudeInMeters(double d);
    }

    static Builder builder() {
        return new C$AutoValue_Altitude.Builder();
    }

    public static TypeAdapter<Altitude> typeAdapter(Gson gson) {
        return new AutoValue_Altitude.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getAccuracyInMeters();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getAltitudeInMeters();
}
