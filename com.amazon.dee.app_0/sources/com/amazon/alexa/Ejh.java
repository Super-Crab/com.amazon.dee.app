package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_TimeZoneChangedPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: TimeZoneChangedPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Ejh implements Payload {
    public static Ejh zZm(SOo sOo) {
        return new AutoValue_TimeZoneChangedPayload(sOo);
    }

    public static TypeAdapter<Ejh> zZm(Gson gson) {
        return new AutoValue_TimeZoneChangedPayload.GsonTypeAdapter(gson);
    }
}
