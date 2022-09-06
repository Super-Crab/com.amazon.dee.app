package com.amazon.alexa.alertsca.payload;

import com.amazon.alexa.alertsca.AlertsToken;
import com.amazon.alexa.alertsca.payload.AutoValue_DeleteAlertsPayload;
import com.amazon.alexa.alertsca.payload.C$AutoValue_DeleteAlertsPayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Set;
@AutoValue
/* loaded from: classes6.dex */
public abstract class DeleteAlertsPayload {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract DeleteAlertsPayload build();

        public abstract Builder setTokens(Set<AlertsToken> set);
    }

    public static Builder builder() {
        return new C$AutoValue_DeleteAlertsPayload.Builder();
    }

    public static TypeAdapter<DeleteAlertsPayload> typeAdapter(Gson gson) {
        return new AutoValue_DeleteAlertsPayload.GsonTypeAdapter(gson);
    }

    public abstract Set<AlertsToken> getTokens();
}
