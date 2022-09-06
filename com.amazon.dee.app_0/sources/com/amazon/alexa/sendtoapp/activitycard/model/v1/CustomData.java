package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.sendtoapp.activitycard.model.v1.AutoValue_CustomData;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.C$AutoValue_CustomData;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes10.dex */
public abstract class CustomData {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract CustomData build();

        public abstract Builder setActions(Actions actions);

        public abstract Builder setIcon(Icon icon);

        public abstract Builder setMetricId(String str);

        public abstract Builder setSubtitle(String str);

        public abstract Builder setTitle(String str);

        public abstract Builder setToken(String str);

        public abstract Builder setVersion(String str);
    }

    public static Builder builder() {
        return new C$AutoValue_CustomData.Builder();
    }

    public static TypeAdapter<CustomData> typeAdapter(Gson gson) {
        return new AutoValue_CustomData.GsonTypeAdapter(gson);
    }

    public abstract Actions getActions();

    public abstract Icon getIcon();

    public abstract String getMetricId();

    public abstract String getSubtitle();

    public abstract String getTitle();

    public abstract String getToken();

    public abstract String getVersion();
}
