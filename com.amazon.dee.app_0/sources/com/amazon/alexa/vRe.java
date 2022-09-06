package com.amazon.alexa;

import com.amazon.alexa.WlV;
import com.amazon.alexa.client.alexaservice.speaker.payload.AutoValue_VolumeStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: VolumeStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class vRe implements ComponentStatePayload {

    /* compiled from: VolumeStatePayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new WlV.zZm();
    }

    public static TypeAdapter<vRe> zZm(Gson gson) {
        return new AutoValue_VolumeStatePayload.GsonTypeAdapter(gson);
    }
}
