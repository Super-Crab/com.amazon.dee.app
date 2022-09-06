package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speaker.payload.AutoValue_SetVolumePayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SetVolumePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class tRN implements Payload {
    public static TypeAdapter<tRN> zZm(Gson gson) {
        return new AutoValue_SetVolumePayload.GsonTypeAdapter(gson);
    }
}
