package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speechsynthesizer.payload.AutoValue_SpeechEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SpeechEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class yXU implements Payload {
    public static yXU zZm(C0176Pce c0176Pce) {
        return new AutoValue_SpeechEventPayload(c0176Pce);
    }

    public static TypeAdapter<yXU> zZm(Gson gson) {
        return new AutoValue_SpeechEventPayload.GsonTypeAdapter(gson);
    }
}
