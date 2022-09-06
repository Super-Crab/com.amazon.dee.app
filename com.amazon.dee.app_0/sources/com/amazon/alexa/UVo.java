package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_ClearQueuePayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: ClearQueuePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class UVo implements Payload {

    /* compiled from: ClearQueuePayload.java */
    /* loaded from: classes.dex */
    public enum zZm {
        CLEAR_ENQUEUED,
        CLEAR_ALL
    }

    public static TypeAdapter<UVo> zZm(Gson gson) {
        return new AutoValue_ClearQueuePayload.GsonTypeAdapter(gson);
    }
}
