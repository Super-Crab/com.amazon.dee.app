package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.drive.navigation.location.AutoValue_Heading;
import com.amazon.alexa.drive.navigation.location.C$AutoValue_Heading;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Heading {

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Heading build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setAccuracyInDegrees(double d);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setDirectionInDegrees(double d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
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
