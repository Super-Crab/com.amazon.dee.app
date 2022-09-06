package com.amazon.alexa;

import com.amazon.alexa.LTt;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_ReportDiscoveredPlayersPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: ReportDiscoveredPlayersPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class vST implements Payload {

    /* compiled from: ReportDiscoveredPlayersPayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo {

        /* compiled from: ReportDiscoveredPlayersPayload.java */
        /* loaded from: classes.dex */
        public enum zZm {
            SIGNING_CERTIFICATE,
            GENERATED_CERTIFICATE,
            NONE
        }
    }

    /* compiled from: ReportDiscoveredPlayersPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new LTt.zZm();
    }

    public static TypeAdapter<vST> zZm(Gson gson) {
        return new AutoValue_ReportDiscoveredPlayersPayload.GsonTypeAdapter(gson);
    }
}
