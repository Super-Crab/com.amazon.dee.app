package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_UserInactivityReportEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: UserInactivityReportEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class YiP implements Payload {
    public static YiP zZm(long j) {
        return new AutoValue_UserInactivityReportEventPayload(j);
    }

    public static TypeAdapter<YiP> zZm(Gson gson) {
        return new AutoValue_UserInactivityReportEventPayload.GsonTypeAdapter(gson);
    }
}
