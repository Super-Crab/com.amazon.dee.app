package com.amazon.alexa.sendtoapp.notification.actions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sendtoapp.notification.actions.AutoValue_Action;
import com.amazon.alexa.sendtoapp.notification.actions.C$AutoValue_Action;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
@AutoValue
/* loaded from: classes10.dex */
public abstract class Action {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Action build();

        public abstract Builder setCatalogId(String str);

        public abstract Builder setCatalogType(String str);

        public abstract Builder setIdentifier(String str);

        public abstract Builder setIdentifierType(String str);

        public abstract Builder setLaunchConfig(LaunchConfig launchConfig);
    }

    public static Builder builder() {
        return new C$AutoValue_Action.Builder();
    }

    @NonNull
    public static TypeAdapter<Action> typeAdapter() {
        return new AutoValue_Action.GsonTypeAdapter(new GsonBuilder().registerTypeAdapter(LaunchConfig.class, LaunchConfig.typeAdapter()).create());
    }

    @Nullable
    @SerializedName("catalogId")
    public abstract String catalogId();

    @NonNull
    @SerializedName("catalogType")
    public abstract String catalogType();

    @NonNull
    @SerializedName("identifier")
    public abstract String identifier();

    @NonNull
    @SerializedName("identifierType")
    public abstract String identifierType();

    @Nullable
    @SerializedName("launchConfig")
    public abstract LaunchConfig launchConfig();
}
