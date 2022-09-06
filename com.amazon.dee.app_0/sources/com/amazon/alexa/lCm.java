package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_PlaybackEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: PlaybackEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class lCm implements Payload {
    public static lCm zZm(Puy puy, long j) {
        return new AutoValue_PlaybackEventPayload(puy, j);
    }

    public static TypeAdapter<lCm> zZm(Gson gson) {
        return new AutoValue_PlaybackEventPayload.GsonTypeAdapter(gson);
    }
}
