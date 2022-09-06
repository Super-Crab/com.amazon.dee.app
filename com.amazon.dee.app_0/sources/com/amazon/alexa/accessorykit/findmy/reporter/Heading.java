package com.amazon.alexa.accessorykit.findmy.reporter;

import com.amazon.alexa.accessorykit.findmy.reporter.AutoValue_Heading;
import com.amazon.alexa.accessorykit.findmy.reporter.C$AutoValue_Heading;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Heading {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
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
