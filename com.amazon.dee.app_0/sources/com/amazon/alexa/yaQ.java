package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_TimeZoneReportPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: TimeZoneReportPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class yaQ implements Payload {
    public static yaQ zZm(String str) {
        return new AutoValue_TimeZoneReportPayload(str);
    }

    public static TypeAdapter<yaQ> zZm(Gson gson) {
        return new AutoValue_TimeZoneReportPayload.GsonTypeAdapter(gson);
    }
}
