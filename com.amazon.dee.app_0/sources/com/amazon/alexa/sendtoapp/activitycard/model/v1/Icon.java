package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.sendtoapp.activitycard.model.v1.AutoValue_Icon;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.C$AutoValue_Icon;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes10.dex */
public abstract class Icon {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Icon build();

        public abstract Builder setTintConfig(TintConfig tintConfig);

        public abstract Builder setUrl(String str);
    }

    public static Builder builder() {
        return new C$AutoValue_Icon.Builder();
    }

    public static TypeAdapter<Icon> typeAdapter(Gson gson) {
        return new AutoValue_Icon.GsonTypeAdapter(gson);
    }

    public abstract TintConfig getTintConfig();

    public abstract String getUrl();
}
