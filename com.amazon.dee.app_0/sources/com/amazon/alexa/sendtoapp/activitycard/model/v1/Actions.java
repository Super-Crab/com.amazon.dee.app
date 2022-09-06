package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.sendtoapp.activitycard.model.v1.AutoValue_Actions;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.C$AutoValue_Actions;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes10.dex */
public abstract class Actions {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Actions build();

        public abstract Builder setFallback(Target target);

        public abstract Builder setPrimary(Target target);
    }

    public static Builder builder() {
        return new C$AutoValue_Actions.Builder();
    }

    public static TypeAdapter<Actions> typeAdapter(Gson gson) {
        return new AutoValue_Actions.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract Target getFallback();

    public abstract Target getPrimary();
}
