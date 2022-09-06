package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speechsynthesizer.payload.AutoValue_SpeakPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: SpeakPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class DEe implements Payload {

    /* compiled from: SpeakPayload.java */
    /* loaded from: classes.dex */
    public enum zZm {
        AUDIO_MPEG
    }

    public static TypeAdapter<DEe> zZm(Gson gson) {
        return new AutoValue_SpeakPayload.GsonTypeAdapter(gson);
    }
}
