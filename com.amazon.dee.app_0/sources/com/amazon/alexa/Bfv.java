package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speaker.payload.AutoValue_RecognizerStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.comms.device.KnightDeviceFacade;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: RecognizerStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Bfv implements ComponentStatePayload {
    public static final Bfv zZm = new AutoValue_RecognizerStatePayload(new eYr(KnightDeviceFacade.WakeWordObserver.DEFAULT_WAKEWORD));

    public static TypeAdapter<Bfv> zZm(Gson gson) {
        return new AutoValue_RecognizerStatePayload.GsonTypeAdapter(gson);
    }
}
