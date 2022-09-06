package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.interactionmodel.payload.AutoValue_NewDialogRequestPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: NewDialogRequestPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class JTD implements Payload {
    public static TypeAdapter<JTD> zZm(Gson gson) {
        return new AutoValue_NewDialogRequestPayload.GsonTypeAdapter(gson);
    }
}
