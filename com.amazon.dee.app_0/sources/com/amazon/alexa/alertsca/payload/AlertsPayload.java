package com.amazon.alexa.alertsca.payload;

import com.amazon.alexa.alertsca.AlertsToken;
import com.amazon.alexa.alertsca.payload.AutoValue_AlertsPayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertsPayload {
    public static AlertsPayload create(AlertsToken alertsToken) {
        return new AutoValue_AlertsPayload(alertsToken);
    }

    public static TypeAdapter<AlertsPayload> typeAdapter(Gson gson) {
        return new AutoValue_AlertsPayload.GsonTypeAdapter(gson);
    }

    public abstract AlertsToken getToken();
}
