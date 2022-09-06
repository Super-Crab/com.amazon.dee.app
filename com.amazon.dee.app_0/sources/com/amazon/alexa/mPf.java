package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_SetEndpointPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SetEndpointPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class mPf implements Payload {
    public static TypeAdapter<mPf> zZm(Gson gson) {
        return new AutoValue_SetEndpointPayload.GsonTypeAdapter(gson);
    }
}
