package com.amazon.alexa.sendtoapp.activitycard.model;

import com.amazon.alexa.sendtoapp.activitycard.model.AutoValue_Card;
import com.amazon.alexa.sendtoapp.activitycard.model.C$AutoValue_Card;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes10.dex */
public abstract class Card {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Card build();

        public abstract Builder setClickToDismiss(Boolean bool);

        public abstract Builder setContentId(String str);

        public abstract Builder setContentProvider(String str);

        public abstract Builder setContentSource(String str);

        public abstract Builder setContentType(String str);

        public abstract Builder setCustomData(CustomData customData);

        public abstract Builder setCustomTemplateRoute(String str);

        public abstract Builder setP13nMetadata(P13NMetadata p13NMetadata);

        public abstract Builder setTemplateType(String str);
    }

    public static Builder builder() {
        return new C$AutoValue_Card.Builder();
    }

    public static TypeAdapter<Card> typeAdapter(Gson gson) {
        return new AutoValue_Card.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract Boolean getClickToDismiss();

    public abstract String getContentId();

    public abstract String getContentProvider();

    public abstract String getContentSource();

    public abstract String getContentType();

    public abstract CustomData getCustomData();

    public abstract String getCustomTemplateRoute();

    public abstract P13NMetadata getP13nMetadata();

    public abstract String getTemplateType();
}
