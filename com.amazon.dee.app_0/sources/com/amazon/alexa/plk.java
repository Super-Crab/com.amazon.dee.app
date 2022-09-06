package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.interactions.ActivityTrackerChannelState;
import com.amazon.alexa.client.alexaservice.interactions.AutoValue_AudioActivityTrackerPayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.ezo;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: AudioActivityTrackerPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class plk implements ComponentStatePayload {

    /* compiled from: AudioActivityTrackerPayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract zZm BIo(@Nullable ActivityTrackerChannelState activityTrackerChannelState);

        public abstract zZm zQM(@Nullable ActivityTrackerChannelState activityTrackerChannelState);

        public abstract zZm zZm(@Nullable ActivityTrackerChannelState activityTrackerChannelState);

        public abstract plk zZm();

        public abstract zZm zyO(@Nullable ActivityTrackerChannelState activityTrackerChannelState);
    }

    public static zZm zZm() {
        return new ezo.zZm();
    }

    public static TypeAdapter<plk> zZm(Gson gson) {
        return new AutoValue_AudioActivityTrackerPayload.GsonTypeAdapter(gson);
    }
}
