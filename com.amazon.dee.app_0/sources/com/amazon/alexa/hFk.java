package com.amazon.alexa;

import com.amazon.alexa.AbstractC0181Rbt;
import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_PlaybackFailedEventPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: PlaybackFailedEventPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class hFk implements Payload {

    /* compiled from: PlaybackFailedEventPayload.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo {
    }

    /* compiled from: PlaybackFailedEventPayload.java */
    /* loaded from: classes.dex */
    public enum zQM {
        MEDIA_ERROR_UNKNOWN,
        MEDIA_ERROR_INVALID_REQUEST,
        MEDIA_ERROR_SERVICE_UNAVAILABLE,
        MEDIA_ERROR_INTERNAL_SERVER_ERROR,
        MEDIA_ERROR_INTERNAL_DEVICE_ERROR
    }

    /* compiled from: PlaybackFailedEventPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
    }

    public static zZm zZm() {
        return new AbstractC0181Rbt.zZm();
    }

    public static TypeAdapter<hFk> zZm(Gson gson) {
        return new AutoValue_PlaybackFailedEventPayload.GsonTypeAdapter(gson);
    }
}
