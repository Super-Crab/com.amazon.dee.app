package com.amazon.alexa;

import com.amazon.alexa.QeM;
import com.amazon.alexa.client.alexaservice.speechsynthesizer.AutoValue_SpeechStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SpeechStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class tui implements ComponentStatePayload {
    public static tui zZm(C0176Pce c0176Pce, long j, QeM.zZm zzm) {
        return new AutoValue_SpeechStatePayload(c0176Pce, j, zzm);
    }

    public static TypeAdapter<tui> zZm(Gson gson) {
        return new AutoValue_SpeechStatePayload.GsonTypeAdapter(gson);
    }
}
