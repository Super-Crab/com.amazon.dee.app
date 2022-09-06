package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_ExceptionPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: ExceptionPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class pTS implements Payload {
    public static TypeAdapter<pTS> zZm(Gson gson) {
        return new AutoValue_ExceptionPayload.GsonTypeAdapter(gson);
    }
}
