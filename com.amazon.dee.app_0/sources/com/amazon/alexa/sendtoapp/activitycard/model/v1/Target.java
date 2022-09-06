package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.sendtoapp.activitycard.model.v1.AutoValue_Target;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.C$AutoValue_Target;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes10.dex */
public abstract class Target {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Target build();

        public abstract Builder setCatalogId(String str);

        public abstract Builder setCatalogType(CatalogType catalogType);

        public abstract Builder setIdentifier(String str);

        public abstract Builder setIdentifierType(IdentifierType identifierType);

        public abstract Builder setLaunchConfig(LaunchConfig launchConfig);
    }

    public static Builder builder() {
        return new C$AutoValue_Target.Builder();
    }

    public static TypeAdapter<Target> typeAdapter(Gson gson) {
        return new AutoValue_Target.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract String getCatalogId();

    public abstract CatalogType getCatalogType();

    public abstract String getIdentifier();

    public abstract IdentifierType getIdentifierType();

    @Nullable
    public abstract LaunchConfig getLaunchConfig();
}
