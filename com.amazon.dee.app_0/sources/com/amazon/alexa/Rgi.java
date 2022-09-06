package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_PlayerErrorPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.qQM;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: PlayerErrorPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Rgi implements Payload {

    /* compiled from: PlayerErrorPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new qQM.zZm();
    }

    public static TypeAdapter<Rgi> zZm(Gson gson) {
        return new AutoValue_PlayerErrorPayload.GsonTypeAdapter(gson);
    }
}
