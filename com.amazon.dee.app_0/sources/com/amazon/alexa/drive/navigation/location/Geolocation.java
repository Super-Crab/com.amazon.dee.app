package com.amazon.alexa.drive.navigation.location;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.navigation.location.AutoValue_Geolocation;
import com.amazon.alexa.drive.navigation.location.C$AutoValue_Geolocation;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Geolocation {

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Geolocation build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setAltitude(Altitude altitude);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setCoordinate(Coordinate coordinate);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setHeading(Heading heading);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setSpeed(Speed speed);
    }

    public static Builder builder() {
        return new C$AutoValue_Geolocation.Builder();
    }

    public static TypeAdapter<Geolocation> typeAdapter(Gson gson) {
        return new AutoValue_Geolocation.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract Altitude getAltitude();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Coordinate getCoordinate();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract Heading getHeading();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract Speed getSpeed();
}
