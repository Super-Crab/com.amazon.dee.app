package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.iocomponent.payload.AutoValue_IOComponentsStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Set;
/* compiled from: IOComponentsStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Agi implements ComponentStatePayload {
    public static Agi zZm(Set<tWv> set, Set<tWv> set2) {
        return new AutoValue_IOComponentsStatePayload(set, set2);
    }

    public static TypeAdapter<Agi> zZm(Gson gson) {
        return new AutoValue_IOComponentsStatePayload.GsonTypeAdapter(gson);
    }
}
