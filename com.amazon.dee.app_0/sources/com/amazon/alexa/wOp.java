package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_SoftwareInfoPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SoftwareInfoPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class wOp implements Payload {
    public static wOp zZm(int i) {
        return new AutoValue_SoftwareInfoPayload(i);
    }

    public static TypeAdapter<wOp> zZm(Gson gson) {
        return new AutoValue_SoftwareInfoPayload.GsonTypeAdapter(gson);
    }
}
