package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.sendtoapp.activitycard.model.v1.AutoValue_LaunchConfig;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.C$AutoValue_LaunchConfig;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes10.dex */
public abstract class LaunchConfig {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract LaunchConfig build();

        public abstract Builder isStoreLink(Boolean bool);

        public abstract Builder mustLaunchTargetInGivenApp(Boolean bool);
    }

    public static Builder builder() {
        return new C$AutoValue_LaunchConfig.Builder();
    }

    public static TypeAdapter<LaunchConfig> typeAdapter(Gson gson) {
        return new AutoValue_LaunchConfig.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract Boolean isStoreLink();

    @Nullable
    public abstract Boolean mustLaunchTargetInGivenApp();
}
