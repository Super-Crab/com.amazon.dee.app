package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_LocalesPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
import java.util.Locale;
/* compiled from: LocalesPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class sGd implements Payload {
    public static sGd zZm(List<Locale> list) {
        return new AutoValue_LocalesPayload(list);
    }

    public static TypeAdapter<sGd> zZm(Gson gson) {
        return new AutoValue_LocalesPayload.GsonTypeAdapter(gson);
    }
}
