package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_PlaybackStutterFinishedEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: PlaybackStutterFinishedEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class pLw implements Payload {
    public static pLw zZm(Puy puy, long j, long j2) {
        return new AutoValue_PlaybackStutterFinishedEventPayload(puy, j, j2);
    }

    public static TypeAdapter<pLw> zZm(Gson gson) {
        return new AutoValue_PlaybackStutterFinishedEventPayload.GsonTypeAdapter(gson);
    }
}
