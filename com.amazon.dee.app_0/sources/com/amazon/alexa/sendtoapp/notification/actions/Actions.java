package com.amazon.alexa.sendtoapp.notification.actions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sendtoapp.notification.actions.AutoValue_Actions;
import com.amazon.alexa.sendtoapp.notification.actions.C$AutoValue_Actions;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
@AutoValue
/* loaded from: classes10.dex */
public abstract class Actions {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Actions build();

        public abstract Builder setFallback(@Nullable Action action);

        public abstract Builder setPrimary(Action action);
    }

    public static Builder builder() {
        return new C$AutoValue_Actions.Builder();
    }

    @NonNull
    public static TypeAdapter<Actions> typeAdapter() {
        return new AutoValue_Actions.GsonTypeAdapter(new GsonBuilder().registerTypeAdapter(Action.class, Action.typeAdapter()).create());
    }

    @Nullable
    @SerializedName("fallback")
    public abstract Action fallback();

    @NonNull
    @SerializedName("primary")
    public abstract Action primary();
}
