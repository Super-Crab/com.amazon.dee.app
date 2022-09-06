package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_LaunchTargetPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: LaunchTargetPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class mLq implements Payload {
    public static TypeAdapter<mLq> zZm(Gson gson) {
        return new AutoValue_LaunchTargetPayload.GsonTypeAdapter(gson);
    }
}
