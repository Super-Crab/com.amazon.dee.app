package com.amazon.alexa.location.networking.alps.models;

import com.amazon.alexa.location.networking.alps.models.AutoValue_Coordinate;
import com.amazon.alexa.location.networking.alps.models.C$AutoValue_Coordinate;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes9.dex */
public abstract class Coordinate {

    @AutoValue.Builder
    /* loaded from: classes9.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Coordinate build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setAccuracyInMeters(double d);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setLatitudeInDegrees(double d);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setLongitudeInDegrees(double d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
        return new C$AutoValue_Coordinate.Builder();
    }

    public static TypeAdapter<Coordinate> typeAdapter(Gson gson) {
        return new AutoValue_Coordinate.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getAccuracyInMeters();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getLatitudeInDegrees();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getLongitudeInDegrees();
}
