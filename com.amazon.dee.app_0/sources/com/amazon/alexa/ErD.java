package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload.AutoValue_SetSeekPositionPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SetSeekPositionPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class ErD implements Payload {
    public static TypeAdapter<ErD> zZm(Gson gson) {
        return new AutoValue_SetSeekPositionPayload.GsonTypeAdapter(gson);
    }
}
