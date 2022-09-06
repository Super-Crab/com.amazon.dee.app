package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_StateReportEventPayload;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
/* compiled from: StateReportEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class rff implements Payload {
    public static rff zZm(List<Message> list) {
        return new AutoValue_StateReportEventPayload(list);
    }

    public static TypeAdapter<rff> zZm(Gson gson) {
        return new AutoValue_StateReportEventPayload.GsonTypeAdapter(gson);
    }
}
