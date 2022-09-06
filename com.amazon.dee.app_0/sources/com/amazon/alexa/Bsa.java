package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.display.window.payload.AutoValue_DisplayWindowStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Set;
/* compiled from: DisplayWindowStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Bsa implements ComponentStatePayload {
    public static Bsa zZm(String str, Set<uEF> set) {
        return new AutoValue_DisplayWindowStatePayload(str, set);
    }

    public static TypeAdapter<Bsa> zZm(Gson gson) {
        return new AutoValue_DisplayWindowStatePayload.GsonTypeAdapter(gson);
    }
}
