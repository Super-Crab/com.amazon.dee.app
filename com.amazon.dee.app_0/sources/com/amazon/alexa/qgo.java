package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.cardrenderer.payload.AutoValue_PlayerInfoPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: PlayerInfoPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class qgo implements Payload {

    /* compiled from: PlayerInfoPayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo {
    }

    /* compiled from: PlayerInfoPayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zQM {
    }

    /* compiled from: PlayerInfoPayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static TypeAdapter<qgo> zZm(Gson gson) {
        return new AutoValue_PlayerInfoPayload.GsonTypeAdapter(gson);
    }
}
