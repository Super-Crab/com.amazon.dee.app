package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload.AutoValue_AdjustSeekPositionPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: AdjustSeekPositionPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class STS implements Payload {
    public static TypeAdapter<STS> zZm(Gson gson) {
        return new AutoValue_AdjustSeekPositionPayload.GsonTypeAdapter(gson);
    }
}
