package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.audioplayer.AutoValue_AudioPlayerStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: AudioPlayerStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Vma implements ComponentStatePayload {
    public static Vma zZm(Puy puy, long j, AUQ auq) {
        return new AutoValue_AudioPlayerStatePayload(puy, j, auq);
    }

    public static TypeAdapter<Vma> zZm(Gson gson) {
        return new AutoValue_AudioPlayerStatePayload.GsonTypeAdapter(gson);
    }
}
