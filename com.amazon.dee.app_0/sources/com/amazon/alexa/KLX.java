package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.iqq;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: PhoneCallControllerStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class KLX implements ComponentStatePayload {

    /* compiled from: PhoneCallControllerStatePayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new iqq.zZm();
    }

    public static TypeAdapter<KLX> zZm(Gson gson) {
        return new AutoValue_PhoneCallControllerStatePayload.GsonTypeAdapter(gson);
    }
}
