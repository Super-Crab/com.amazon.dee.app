package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_PlaybackStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.xxR;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Collections;
import java.util.Set;
/* compiled from: PlaybackStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class GkO implements ComponentStatePayload {

    /* compiled from: PlaybackStatePayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract zZm BIo(Set<rjL> set);

        public abstract zZm zZm(long j);

        public abstract zZm zZm(AKJ akj);

        public abstract zZm zZm(MAh mAh);

        public abstract zZm zZm(NdN ndN);

        public abstract zZm zZm(XSR xsr);

        public abstract zZm zZm(AbstractC0197ddD abstractC0197ddD);

        public abstract zZm zZm(@Nullable Set<Kyp> set);

        public abstract GkO zZm();
    }

    public static zZm zZm() {
        return new xxR.zZm().zZm(NdN.IDLE).BIo(Collections.emptySet()).zZm(AbstractC0197ddD.zZm).zZm(0L).zZm(AKJ.NOT_SHUFFLED).zZm(XSR.NOT_REPEATED).zZm(MAh.NOT_RATED);
    }

    public static TypeAdapter<GkO> zZm(Gson gson) {
        return new AutoValue_PlaybackStatePayload.GsonTypeAdapter(gson);
    }
}
