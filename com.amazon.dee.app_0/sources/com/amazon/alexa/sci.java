package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speaker.payload.AutoValue_AdjustVolumePayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: AdjustVolumePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class sci implements Payload {
    public static TypeAdapter<sci> zZm(Gson gson) {
        return new AutoValue_AdjustVolumePayload.GsonTypeAdapter(gson);
    }
}
