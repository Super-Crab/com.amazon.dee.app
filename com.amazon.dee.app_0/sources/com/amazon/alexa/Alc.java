package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.navigation.AutoValue_SetDestinationPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SetDestinationPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Alc implements Payload {

    /* compiled from: SetDestinationPayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm {

        /* compiled from: SetDestinationPayload.java */
        @AutoValue
        /* renamed from: com.amazon.alexa.Alc$zZm$zZm  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static abstract class AbstractC0010zZm {
        }
    }

    public static TypeAdapter<Alc> zZm(Gson gson) {
        return new AutoValue_SetDestinationPayload.GsonTypeAdapter(gson);
    }
}
