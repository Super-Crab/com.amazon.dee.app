package com.amazon.alexa;

import com.amazon.alexa.FPq;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_SingleTargetResponseEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SingleTargetResponseEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class UTs implements Payload {

    /* compiled from: SingleTargetResponseEventPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new FPq.zZm();
    }

    public static TypeAdapter<UTs> zZm(Gson gson) {
        return new AutoValue_SingleTargetResponseEventPayload.GsonTypeAdapter(gson);
    }
}
