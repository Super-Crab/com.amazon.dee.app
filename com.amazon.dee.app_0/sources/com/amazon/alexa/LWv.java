package com.amazon.alexa;

import com.amazon.alexa.AgQ;
import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_ExceptionEncounteredPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: ExceptionEncounteredPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class LWv implements Payload {

    /* compiled from: ExceptionEncounteredPayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo {
    }

    /* compiled from: ExceptionEncounteredPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new AgQ.zZm();
    }

    public static TypeAdapter<LWv> zZm(Gson gson) {
        return new AutoValue_ExceptionEncounteredPayload.GsonTypeAdapter(gson);
    }
}
