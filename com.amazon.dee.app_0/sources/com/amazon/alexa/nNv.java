package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_ExpectSpeechPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: ExpectSpeechPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class nNv implements Payload {
    public static TypeAdapter<nNv> zZm(Gson gson) {
        return new AutoValue_ExpectSpeechPayload.GsonTypeAdapter(gson);
    }
}
