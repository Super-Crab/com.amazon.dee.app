package com.amazon.alexa;

import com.amazon.alexa.XgI;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_AuthorizationCompletePayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: AuthorizationCompletePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class JGr implements Payload {

    /* compiled from: AuthorizationCompletePayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class BIo {
    }

    /* compiled from: AuthorizationCompletePayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zQM {
    }

    /* compiled from: AuthorizationCompletePayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static BIo zZm() {
        return new XgI.zZm();
    }

    public static TypeAdapter<JGr> zZm(Gson gson) {
        return new AutoValue_AuthorizationCompletePayload.GsonTypeAdapter(gson);
    }
}
