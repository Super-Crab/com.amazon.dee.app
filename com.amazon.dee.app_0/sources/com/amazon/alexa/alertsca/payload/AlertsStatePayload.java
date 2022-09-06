package com.amazon.alexa.alertsca.payload;

import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.alertsca.payload.AutoValue_AlertsStatePayload;
import com.amazon.alexa.alertsca.payload.C$AutoValue_AlertsStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Set;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertsStatePayload {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract AlertsStatePayload build();

        public abstract Builder setActiveAlerts(Set<AlertRecord> set);

        public abstract Builder setAllAlerts(Set<AlertRecord> set);
    }

    public static Builder builder() {
        return new C$AutoValue_AlertsStatePayload.Builder();
    }

    public static TypeAdapter<AlertsStatePayload> typeAdapter(Gson gson) {
        return new AutoValue_AlertsStatePayload.GsonTypeAdapter(gson);
    }

    public abstract Set<AlertRecord> getActiveAlerts();

    public abstract Set<AlertRecord> getAllAlerts();
}
