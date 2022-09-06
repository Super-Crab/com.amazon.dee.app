package com.amazon.alexa;

import com.amazon.alexa.aQE;
import com.amazon.alexa.client.alexaservice.interactions.AutoValue_VisualActivityTrackerPayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: VisualActivityTrackerPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Gzu implements ComponentStatePayload {

    /* compiled from: VisualActivityTrackerPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new aQE.zZm();
    }

    public static TypeAdapter<Gzu> zZm(Gson gson) {
        return new AutoValue_VisualActivityTrackerPayload.GsonTypeAdapter(gson);
    }
}
