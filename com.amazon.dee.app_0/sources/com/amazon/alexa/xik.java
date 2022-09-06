package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_PlayPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: PlayPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class xik implements Payload {

    /* compiled from: PlayPayload.java */
    /* loaded from: classes.dex */
    public enum zZm {
        REPLACE_ALL,
        ENQUEUE,
        REPLACE_ENQUEUED
    }

    public static TypeAdapter<xik> zZm(Gson gson) {
        return new AutoValue_PlayPayload.GsonTypeAdapter(gson);
    }
}
