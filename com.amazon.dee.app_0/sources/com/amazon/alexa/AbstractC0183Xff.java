package com.amazon.alexa;

import com.amazon.alexa.cLd;
import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_RecognizePayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: RecognizePayload.java */
@AutoValue
/* renamed from: com.amazon.alexa.Xff  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0183Xff implements Payload {

    /* compiled from: RecognizePayload.java */
    @AutoValue.Builder
    /* renamed from: com.amazon.alexa.Xff$zZm */
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new cLd.zZm();
    }

    public static TypeAdapter<AbstractC0183Xff> zZm(Gson gson) {
        return new AutoValue_RecognizePayload.GsonTypeAdapter(gson);
    }
}
