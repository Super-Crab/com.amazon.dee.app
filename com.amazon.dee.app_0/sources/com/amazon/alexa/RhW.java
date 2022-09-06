package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speaker.payload.AutoValue_SetMutePayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SetMutePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class RhW implements Payload {
    public static TypeAdapter<RhW> zZm(Gson gson) {
        return new AutoValue_SetMutePayload.GsonTypeAdapter(gson);
    }
}
