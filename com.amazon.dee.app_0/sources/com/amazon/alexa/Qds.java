package com.amazon.alexa;

import com.amazon.alexa.bXd;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_PlayerEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: PlayerEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Qds implements Payload {

    /* compiled from: PlayerEventPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new bXd.zZm();
    }

    public static TypeAdapter<Qds> zZm(Gson gson) {
        return new AutoValue_PlayerEventPayload.GsonTypeAdapter(gson);
    }
}
