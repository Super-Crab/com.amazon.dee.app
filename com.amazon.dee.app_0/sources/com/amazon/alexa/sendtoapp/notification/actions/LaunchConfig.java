package com.amazon.alexa.sendtoapp.notification.actions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sendtoapp.notification.actions.AutoValue_LaunchConfig;
import com.amazon.alexa.sendtoapp.notification.actions.C$AutoValue_LaunchConfig;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
@AutoValue
/* loaded from: classes10.dex */
public abstract class LaunchConfig {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract LaunchConfig build();

        public abstract Builder setIsStoreLink(@Nullable Boolean bool);

        public abstract Builder setMustLaunchTargetInGivenApp(@Nullable Boolean bool);
    }

    public static Builder builder() {
        return new C$AutoValue_LaunchConfig.Builder();
    }

    @NonNull
    public static TypeAdapter<LaunchConfig> typeAdapter() {
        return new AutoValue_LaunchConfig.GsonTypeAdapter(new Gson());
    }

    @Nullable
    @SerializedName("isStoreLink")
    public abstract Boolean isStoreLink();

    @Nullable
    @SerializedName("mustLaunchTargetInGivenApp")
    public abstract Boolean mustLaunchTargetInGivenApp();
}
