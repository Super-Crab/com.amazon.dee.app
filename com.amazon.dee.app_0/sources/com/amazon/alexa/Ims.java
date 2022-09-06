package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_AuthorizeDiscoveredPlayersPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: AuthorizeDiscoveredPlayersPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Ims implements Payload {

    /* compiled from: AuthorizeDiscoveredPlayersPayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm {

        /* compiled from: AuthorizeDiscoveredPlayersPayload.java */
        @AutoValue
        /* renamed from: com.amazon.alexa.Ims$zZm$zZm  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static abstract class AbstractC0011zZm {
        }
    }

    public static TypeAdapter<Ims> zZm(Gson gson) {
        return new AutoValue_AuthorizeDiscoveredPlayersPayload.GsonTypeAdapter(gson);
    }
}
