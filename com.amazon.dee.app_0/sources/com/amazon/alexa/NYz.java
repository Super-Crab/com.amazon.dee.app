package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_SetEndOfSpeechOffsetPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SetEndOfSpeechOffsetPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class NYz implements Payload {
    public static TypeAdapter<NYz> zZm(Gson gson) {
        return new AutoValue_SetEndOfSpeechOffsetPayload.GsonTypeAdapter(gson);
    }
}
