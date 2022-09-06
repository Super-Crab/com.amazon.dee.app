package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_ExternalMediaPlayerStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Set;
/* compiled from: ExternalMediaPlayerStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class BGK implements ComponentStatePayload {
    public static BGK zZm(String str, AbstractC0188bKf abstractC0188bKf, vQe vqe, Set<HkJ> set) {
        return new AutoValue_ExternalMediaPlayerStatePayload(str, abstractC0188bKf, vqe, set);
    }

    public static TypeAdapter<BGK> zZm(Gson gson) {
        return new AutoValue_ExternalMediaPlayerStatePayload.GsonTypeAdapter(gson);
    }
}
