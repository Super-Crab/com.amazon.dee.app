package com.amazon.alexa;

import com.amazon.alexa.IHD;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_MultipleTargetsResponseEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: MultipleTargetsResponseEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Jqr implements Payload {

    /* compiled from: MultipleTargetsResponseEventPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new IHD.zZm();
    }

    public static TypeAdapter<Jqr> zZm(Gson gson) {
        return new AutoValue_MultipleTargetsResponseEventPayload.GsonTypeAdapter(gson);
    }
}
