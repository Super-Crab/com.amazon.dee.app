package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.drive.navigation.location.AutoValue_Altitude;
import com.amazon.alexa.drive.navigation.location.C$AutoValue_Altitude;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Altitude {

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Altitude build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setAccuracyInMeters(double d);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setAltitudeInMeters(double d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
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
