package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.applicationmanager.payload.AutoValue_NavigationPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: NavigationPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class zAH implements Payload {
    public static TypeAdapter<zAH> zZm(Gson gson) {
        return new AutoValue_NavigationPayload.GsonTypeAdapter(gson);
    }
}
