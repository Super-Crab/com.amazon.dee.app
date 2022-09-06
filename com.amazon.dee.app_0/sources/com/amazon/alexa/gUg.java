package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_DisambiguateAndLaunchTargetPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: DisambiguateAndLaunchTargetPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class gUg implements Payload {
    public static TypeAdapter<gUg> zZm(Gson gson) {
        return new AutoValue_DisambiguateAndLaunchTargetPayload.GsonTypeAdapter(gson);
    }
}
