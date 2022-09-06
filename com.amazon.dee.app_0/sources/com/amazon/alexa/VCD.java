package com.amazon.alexa;

import com.amazon.alexa.UzW;
import com.amazon.alexa.client.alexaservice.speaker.payload.AutoValue_VolumeEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: VolumeEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class VCD implements Payload {

    /* compiled from: VolumeEventPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract zZm zZm(long j);

        public abstract zZm zZm(boolean z);

        public abstract VCD zZm();
    }

    public static zZm zZm() {
        return new UzW.zZm();
    }

    public static TypeAdapter<VCD> zZm(Gson gson) {
        return new AutoValue_VolumeEventPayload.GsonTypeAdapter(gson);
    }
}
