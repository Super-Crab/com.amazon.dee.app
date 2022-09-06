package com.amazon.alexa.accessorykit.findmy.reporter;

import com.amazon.alexa.accessorykit.findmy.reporter.AutoValue_Speed;
import com.amazon.alexa.accessorykit.findmy.reporter.C$AutoValue_Speed;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Speed {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Speed build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setAccuracyInMetersPerSecond(double d);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setSpeedInMetersPerSecond(double d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
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
